import java.io.File;
/*Ashley Magallanes
  February 13, 2023
  CS 3331 – Advanced Object-Oriented Programming – Spring 2023
  Professor Mejia
  Programming Assignment 1

  This work was done individually and completely on my own. I did not share, reproduce, or alter any part of this assignment for
  any purpose. I did not share code, upload this assignment online in any form, or view/received/modified code written from 
  anyone else. All deliverables were produced entirely on my own. This assignment is part of an academic course at The University
  of Texas at El Paso and a grade will be assigned for the work I produced.
 */
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.Set;
/**
 * @version 4- this class has been modified four times since PA0
 * Main Class of program
 */
public class RunFlight{
    public static void main (String[] args){
        try{
            //creating log file
            FileWriter Log = new FileWriter("Log.txt");

            //Design pattern: Singleton (single fileReader instance needed) if more creations will just point to the same original one!
            ReadFiles read = ReadFiles.getInstance();
            read.fillCustomerList("CustomerListPA4(1).csv");
            read.fillFlightList("FlightSchedule(7).csv");
            read.fillEmployeeList("CustomerListPA4(1).csv");
            read.fillAutomatedList("AutoPurchaser400K.csv");

            //Instance of class where selling flights takes place
            SellFlights purchase1 = new SellFlights();

            //starting the user interaction and placing a while loop to continue making transactions
            boolean moreTransactions = true;
            Scanner input = new Scanner(System.in);
            while(moreTransactions == true){
                userInteraction(Log, input, read.getEmployeeList(), read.getCustomerList(), read.getDataList(), moreTransactions, purchase1, read.getAutomatedList(), read.getPeopleList());
                System.out.println("To go into MAIN MENU/LOG IN type [1] to terminate try [EXIT]");
                String num = input.nextLine();
                //termination happens if this if statement goes through
                if(num.toUpperCase().equals("EXIT")){
                    moreTransactions = false;
                    Log.flush();
                    input.close();
                }else if(!(num.equals("1"))){
                    Log.flush();
                    input.close();
                    break;
                }
            }

            //updated csv file creations 
            //since the while loop was broken the termination is about to process so the new files are implemented
            File newFlightInfo = new File("updatedFlightSheet.csv");
            File newCustomerSheet = new File("updatedCustomerSheet.csv");
            read.makeFlightCSV(newFlightInfo,read.getDataList());
            read.makeCustomerCSV(newCustomerSheet,read.getCustomerList(), read.getEmployeeList());
            
            
        }catch(FileNotFoundException e){
            System.out.println("The file is not found!");
            System.out.println(e.getMessage());
        }catch(IOException e){
            System.out.println("Log file is not found!");
            System.out.println(e.getMessage());
        }catch(IllegalArgumentException e){
            System.out.println("There was an issue with your input!");
            System.out.println(e.getMessage());
        }catch(IllegalStateException e){
            System.out.println("The user interaction has terminated!");
            System.out.println(e.getMessage());
        }

    }
    /**
     * Method to start user interaction, asks role, first name and last name
     * @param Log - file writter to log actions
     * @param input - scanner for user interaction
     * @param employeeList - hashMap of employee objects
     * @param customerList - hashMap of customer objects
     * @param dataList - hashMap of flight objects
     * @param moreTransactions - flag to keep system continuesly
     */
    public static void userInteraction(FileWriter Log, Scanner input, HashMap<Integer, Employee> employeeList, HashMap<Integer, Customer> customerList, HashMap<Integer, Flight> dataList, boolean moreTransactions, SellFlights purchase1, LinkedHashMap<Integer, AutoPurchases> autoPurchasesList, HashMap<Integer,Person> peopleList){
        try{
            boolean exitInput = false;
            String role = null;
            while(exitInput == false){
                //selecting who will log into the system
                System.out.println("Select your role:\n[1]Customer\n[2]Employee\n(Type 1 or 2)");
                String roleNum = input.nextLine();
                //both need to log in but their role attribute will be different
                if(roleNum.equals("1") | roleNum.equals("2")){
                    System.out.println("----Log In----");
                    //asks for first name
                    System.out.println("Type in your First Name: ");
                    String firstName = input.nextLine();
                    if(firstName.equals("EXIT")){
                        exitTerminal(input, Log, moreTransactions);
                        break;
                    }
                    //asks for last name
                    System.out.println("Type in your Last Name: ");
                    String lastName = input.nextLine();
                    if(lastName.equals("EXIT")){
                        exitTerminal(input, Log, moreTransactions);
                        break;
                    }
                    //the next method to check their info and continue with their username and password is passed
                    if(roleNum.equals("1")){
                        role = "Customer";
                        logIn(role, Log, employeeList, customerList, dataList, input, firstName, lastName, moreTransactions, purchase1, autoPurchasesList, peopleList);

                    }else if(lastName.equals("EXIT")){
                        exitTerminal(input, Log, moreTransactions);
                    //PA0 menu will be given if the user is not a customer
                    }else if(roleNum.equals("2")){
                        role = "Employee";
                        logIn(role, Log, employeeList, customerList, dataList, input, firstName, lastName, moreTransactions, purchase1, autoPurchasesList, peopleList);
                    }else{
                        break;
                    }
                }
                exitInput = true;
            }
        }catch(IllegalArgumentException e){
            System.out.println("There was an issue with your input!");
            System.out.println(e.getMessage());
        }catch(IllegalStateException e){
            System.out.println("The user interaction has terminated!");
            System.out.println(e.getMessage());
        }catch(IOException e){
            System.out.println("Log file is not found!");
            System.out.println(e.getMessage());
        }
    }
    /**
     * Method to promp user to log in and use that info to check if they exist 
     * @param role - customer or employee (determined form first name and last name)
     * @param Log - file writter to log actions
     * @param employeeList - hashMap of employee objects
     * @param customerList - hashMap of customer objects
     * @param dataList - hashMap of flight objects
     * @param input - scanner for user input
     * @param firstName - used to confirm username/password
     * @param lastName - used to confirm username/password
     * @param moreTransactions - flag to keep system continuesly
     */
    public static void logIn(String role, FileWriter Log, HashMap<Integer, Employee> employeeList, HashMap<Integer, Customer> customerList, HashMap<Integer, Flight> dataList, Scanner input, String firstName, String lastName, boolean moreTransactions, SellFlights purchase1, LinkedHashMap<Integer,AutoPurchases> autoPurchasesList, HashMap<Integer,Person> peopleList){
        int customerIndexInList = -1;
        int employeeIndexInList = -1;
        boolean isCustomer = false;
        boolean isEmployee = false;
        try{
            //Getting the correct customer data
            if(role.equals("Customer")){
                //getting the ID of customer and storing it
                for(Customer value: customerList.values()){
                    if((value.getFirstName()).equals(firstName) & (value.getLastName()).equals(lastName)){
                        customerIndexInList = value.getCustomerID();
                    }
                }
                //Name and LastName don't match from customer list
                isCustomer = true;
                if(customerIndexInList == -1){
                    System.out.println("The first name and last name don't match any member in the customer list!\n");
                    Log.write(firstName+ " " +lastName+ "tried logging in but their name is not in the customer list!\n");
                    isCustomer = false;
                }
            }
            //Getting correct employee data
            if(role.equals("Employee")){
                for(Employee value: employeeList.values()){
                    if((value.getFirstName()).equals(firstName) & (value.getLastName()).equals(lastName)){
                        employeeIndexInList = value.getEmployeeID();
                    }
                }
                //Name and LastName don't match from customer list
                isEmployee = true;
                if(employeeIndexInList == -1){
                    System.out.println("The first name and last name don't match any member in the employee list!\n");
                    Log.write(firstName+ " " +lastName+ "tried logging in but their name is not in the employee list!\n");
                    isEmployee = false;
                }
            }

            
            boolean again = true;
            //customer process
            while(again == true & isCustomer == true){
                System.out.println("Type in your Username: ");
                String username = input.nextLine();
                if(username.equals("EXIT")){
                    exitTerminal(input, Log, moreTransactions);
                    break;
                }
                System.out.println("Type in your Password: ");
                String password = input.nextLine();
                if(password.equals("EXIT")){
                    exitTerminal(input, Log, moreTransactions);
                    break;
                }
                //password and username matched the first name and last name given by customer
                if((customerList.get(customerIndexInList).getUsername()).equals(username) & (customerList.get(customerIndexInList).getPassword()).equals(password)){
                    System.out.println("You have succesfully logged into your account!");
                    Log.write(firstName+ " " +lastName+ ": "+role+" has succesfully logged into their account!\n");
                    again = false;
                    //propmts the customer on what they want to do now that they have logged in
                    System.out.println("What do you want to do: \n\t[1]Purchase Flight \n\t[2]Cancel Ticket \n\t[3]See Your Tickets\n\t[4]Check Savings\n\t[5]Search Flight by ID\n\t[6]Search Flight by Airport Codes\nType the number of your action: ");
                    String  customerOption = input.nextLine();
                    //purchase tickets
                    if(customerOption.equals("1")){
                        purchase1.sellFlightTickets(Log, dataList, input, customerIndexInList, customerList, moreTransactions,false);   
                    //Cancel Tickets
                    }else if(customerOption.equals("2")){
                        //checks and prints the tickets the customer has so that customer can chose the one to cancel
                        if(customerList.get(customerIndexInList).getCustomerTickets().size()>0){
                            System.out.println("These are your tickets purchased: ");
                            for(Ticket value: customerList.get(customerIndexInList).getCustomerTickets().values()){
                                System.out.println("NUMBER:["+value.getConfirmationNum()+"]" +"\n"+ value.printFlight(value.getFlightID(), dataList)+
                                "\n"+value.printTicket());
                                System.out.println("--------------------------------------------------------------------------------");
                            }
                            System.out.println("Which flight ticket would you like to cancel? (Type the NUMBER of your flight ticket)");
                            String numCancel = input.nextLine();
                            //checking if the number flight the user wants to cancel exists
                            if(customerList.get(customerIndexInList).getCustomerTickets().containsKey(Integer.parseInt(numCancel))){
                                //cancelling tickets method for customer
                                customerList.get(customerIndexInList).cancelTicket(Integer.parseInt(numCancel), dataList, Log);
                            }else{
                                System.out.println("That ticket number doesn't exist!");
                            }
                            Log.write(firstName+ " " +lastName+ ": "+role+" selected the option to cancel a ticket!\n");
                        }else{
                            System.out.println("You have not bought any flights yet!");
                        }
                    //option to let customer just see their tickets purchased
                    }else if(customerOption.equals("3")){
                        if(customerList.get(customerIndexInList).getCustomerTickets().size()>0){
                            System.out.println("------------------Tickets------------------");
                            customerList.get(customerIndexInList).printTickets();
                        }else{
                            System.out.println("You haven't bought any tickets yet!");
                        }
                        Log.write(firstName+ " " +lastName+ ": "+role+" selected the option to see all tickets purchased!\n");
                    }else if(customerOption.equals("4")){
                        if(customerList.get(customerIndexInList).getSavings()>0){
                            System.out.println("Total savings: $"+customerList.get(customerIndexInList).getSavings());
                        }else{
                            System.out.println("You don't have any savings!");
                        }
                        Log.write(firstName+ " " +lastName+ ": "+role+" selected the option to see total savings!\n");
                    }else if(customerOption.equals("5")){
                        System.out.println("Type in the flight ID: ");
                        String searchID = input.nextLine();
                        if(Integer.parseInt(searchID)>0 & Integer.parseInt(searchID)<=dataList.size()){
                            System.out.println(dataList.get(Integer.parseInt(searchID)).printFlight());
                            System.out.println("Do you want to purchase a flight? [1]YES [2]NO");
                            String makePurchase = input.nextLine();
                            if(Integer.parseInt(makePurchase)==1){
                                purchase1.sellFlightTickets(Log, dataList, input, customerIndexInList, customerList, moreTransactions, true);
                            }else if(Integer.parseInt(makePurchase)==2){
                                System.out.println("Okay!");
                            }else{
                                System.out.println("That was not an option!");
                            }
                        }else{
                            System.out.println("There is no flight with that ID!");
                        }
                    }else if(customerOption.equals("6")){
                        System.out.println("Type in the origin airport code: ");
                        String searchOrigin = input.nextLine();
                        System.out.println("Type in the destination airport code: ");
                        String searchDest = input.nextLine();
                        System.out.println("Do you know the departure date? [1]YES [2]NO");
                        String depDateSearch = input.nextLine();
                        boolean foundFlight = false;
                        if(Integer.parseInt(depDateSearch)==2){
                            for(Flight value:dataList.values()){
                                if(value.getOriginAirportCode().equals(searchOrigin)&&value.getDestinationAirportCode().equals(searchDest)){
                                    foundFlight = true;
                                    System.out.println("Flight ID: "+dataList.get(value.getID()).getID()+dataList.get(value.getID()).printFlight()+"\n");
                                }
                            }
                            if(foundFlight==false){
                                System.out.println("There are no flights with those specific properties.");
                            }
                        }else if(Integer.parseInt(depDateSearch)==1){
                            System.out.println("Type the departure date (MM/DD/YY): ");
                            String depDate = input.nextLine();
                            for(Flight value:dataList.values()){
                                if(value.getOriginAirportCode().equals(searchOrigin)&&value.getDestinationAirportCode().equals(searchDest)&&value.getDepartureDate().equals(depDate)){
                                    foundFlight = true;
                                    System.out.println("Flight ID: "+dataList.get(value.getID()).getID()+dataList.get(value.getID()).printFlight()+"\n");
                                }
                            }
                            if(foundFlight==false){
                                System.out.println("There are no flights with those specific properties.");
                            }
                        }else{
                            System.out.println("That was not an option.");
                        }
                        if(foundFlight==true){
                            System.out.println("Do you want to purchase a flight? [1]YES [2]NO");
                            String makePurchase = input.nextLine();
                            if(Integer.parseInt(makePurchase)==1){
                                purchase1.sellFlightTickets(Log, dataList, input, customerIndexInList, customerList, moreTransactions, true);
                            }else if(Integer.parseInt(makePurchase)==2){
                                System.out.println("Okay!");
                            }else{
                                System.out.println("That was not an option!");
                            }
                        }
                    }else{
                        System.out.println("That option doesn't exist, please try again!");
                        break;
                    }
                //password and username dont match the names given
                }else{
                    System.out.println("Either the password or username are incorrect! Try again or type (EXIT) to end.");
                    again = true;
                }
            }
            //employee process
            while(again == true & isEmployee == true){
                System.out.println("Type in your Username: ");
                String username = input.nextLine();
                if(username.equals("EXIT")){
                    exitTerminal(input, Log, moreTransactions);
                    break;
                }
                System.out.println("Type in your Password: ");
                String password = input.nextLine();
                if(password.equals("EXIT")){
                    exitTerminal(input, Log, moreTransactions);
                    break;
                }
                //password and username matched the first name and last name given by customer
                if((employeeList.get(employeeIndexInList).getUsername()).equals(username) & (employeeList.get(employeeIndexInList).getPassword()).equals(password)){
                    System.out.println("You have succesfully logged into your account!");
                    Log.write(firstName+ " " +lastName+ ": "+role+" has succesfully logged into their account!\n");
                    again = false;
                    System.out.println("What do you want to do: \n\t[1]Inquire Information \n\t[2]Cancel Flight \n\t[3]Purchase Flight\n\t[4]See Tickets\n\t[5]Cancel Personal Ticket\n\t[6]Inquire Airport Info\n\t[7]Automatic Purchasing\n\t[8]Customer Electronic Ticket Summary\nType the number of your action: ");
                    String infoOrCancel = input.nextLine();
                    //buys ticket from employee depending on what classification they want
                    if(infoOrCancel.equals("1") | infoOrCancel.equals("2")){
                        //employee can open the menu and inquire/cancel (COMPOSITION menu cannot exist without employee)
                        employeeList.get(employeeIndexInList).openManagerMenu(input, Log, dataList, customerList, employeeList);  
                    }else if(infoOrCancel.equals("3")){
                        //purchasing flights 
                        employeeList.get(employeeIndexInList).sellFlightTicketsEmployee(Log, dataList, input, employeeIndexInList, employeeList, moreTransactions); 

                    }else if(infoOrCancel.equals("4")){
                        if(employeeList.get(employeeIndexInList).getEmployeeTickets().size()>0){
                            System.out.println("------------------Tickets------------------");
                            employeeList.get(employeeIndexInList).printTickets();
                        }else{
                            System.out.println("You haven't bought any tickets yet!");
                        }
                        Log.write(firstName+ " " +lastName+ ": "+role+" selected the option to see all tickets purchased!\n");
                    }else if(infoOrCancel.equals("5")){
                        //checks and prints the tickets the customer has so that customer can chose the one to cancel
                        if(employeeList.get(employeeIndexInList).getEmployeeTickets().size()>0){
                            System.out.println("These are your tickets purchased: ");
                            for(Ticket value: employeeList.get(employeeIndexInList).getEmployeeTickets().values()){
                                System.out.println("NUMBER:["+value.getConfirmationNum()+"]" +"\n"+ value.printFlight(value.getFlightID(), dataList)+
                                "\n"+value.printTicket());
                                System.out.println("--------------------------------------------------------------------------------");
                            }
                            System.out.println("Which flight ticket would you like to cancel? (Type the NUMBER of your flight ticket)");
                            String numCancel = input.nextLine();
                            //checking if the number flight the user wants to cancel exists
                            if(employeeList.get(employeeIndexInList).getEmployeeTickets().containsKey(Integer.parseInt(numCancel))){
                                //cancelling tickets method for customer
                                employeeList.get(employeeIndexInList).cancelTicket(Integer.parseInt(numCancel), dataList, Log);
                            }else{
                                System.out.println("That ticket number doesn't exist!");
                            }
                            Log.write(firstName+ " " +lastName+ ": "+role+" selected the option to cancel a ticket!\n");
                        }else{
                            System.out.println("You haven't purchased any tickets yet!");
                        }
                    }else if(infoOrCancel.equals("6")){
                        //employee can open the menu and inquire/cancel (COMPOSITION menu cannot exist without employee)
                        System.out.println("Which airport do you want to inquire information about? (Type the CODE of the airport)");
                        String airportCode = input.nextLine();
                        employeeList.get(employeeIndexInList).airportInformation(dataList, airportCode); 
                    }else if(infoOrCancel.equals("7")){
                        /*Set<Integer> keys = autoPurchasesList.keySet();
                        for(Integer key: keys){
                            purchase1.autoPurchase(autoPurchasesList.get(key).getFirstName(), autoPurchasesList.get(key).getLastName(), autoPurchasesList.get(key).getAction(), autoPurchasesList.get(key).getID(), autoPurchasesList.get(key).getOriginCode(), autoPurchasesList.get(key).getDestCode(), autoPurchasesList.get(key).getType(), autoPurchasesList.get(key).getQuantity(), customerList, dataList, Log, employeeList);
                        }*/
                        for(AutoPurchases value: autoPurchasesList.values()){
                            purchase1.autoPurchase(value.getFirstName(), value.getLastName(), value.getAction(), value.getID(), value.getOriginCode(), value.getDestCode(), value.getType(), value.getQuantity(), customerList, dataList, Log, employeeList);
                        }
                    }else if(infoOrCancel.equals("8")){
                        System.out.println("Customer ID: ");
                        String customerID = input.nextLine();
                        if(customerList.containsKey(Integer.parseInt(customerID))){
                            //method that handles the tickt summary
                            //for customers only
                            File electronicTicket = new File("electronicTicketSummary"+customerList.get(Integer.parseInt(customerID)).getLastName()+customerList.get(Integer.parseInt(customerID)).getFirstName()+".txt");
                            employeeList.get(employeeIndexInList).ticketSummary(electronicTicket, Integer.parseInt(customerID), customerList, dataList);
                        }else if(employeeList.containsKey(Integer.parseInt(customerID))){
                            //for employees only
                            File electronicTicket = new File("electronicTicketSummary"+employeeList.get(Integer.parseInt(customerID)).getLastName()+employeeList.get(Integer.parseInt(customerID)).getFirstName()+".txt");
                            employeeList.get(employeeIndexInList).ticketSummaryEmployee(electronicTicket, Integer.parseInt(customerID), employeeList, dataList);
                       
                        }
                    }else{
                        System.out.println("That option doesn't exist, please try again!");
                        break;
                    }
                }
                else{
                    System.out.println("Either the password or username are incorrect! Try again or type (EXIT) to end.");
                    again = true;
                }
            }
        }catch(IllegalArgumentException e){
            System.out.println("There was an issue with your input!");
            System.out.println(e.getMessage());
        
        }catch(IllegalStateException e){
            System.out.println("The user interaction has terminated!");
            System.out.println(e.getMessage());
        }catch(IOException e){
            System.out.println("The Log file was not found!");
            System.out.println(e.getMessage());
        }
    
    }

    /**
     * Method for "EXIT" that breaks terminal-user interface and prepares system for termination process
     * @param input - scanner being terminated 
     * @param Log - file writter to log actio
     * @param moreTransactions - flag to continue system actions (false)
     * @throws IOException 
     */
    public static void exitTerminal(Scanner input, FileWriter Log, boolean moreTransactions) throws IOException{
        Log.write("EXIT\n");
        moreTransactions = false;
        
    }
    

}
