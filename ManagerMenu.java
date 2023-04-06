
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
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
/**
 * @version 3- this class has been modified three times
 * This class has all the functionality options an employee can handle based on flight ID
 */
public class ManagerMenu{
    //attributes
    private static int objectLocation;
    private HashMap<Integer, Customer> customerList;
    private HashMap<Integer, Employee> employeeList;

    //constructor
    public ManagerMenu(){
    }
    //setter
    public static void setObjectLocation(int objectLocationIn){
        objectLocation = objectLocationIn;
    }
    //getter
    public static int getObjectLocation(){
        return objectLocation;
    }
    /**
     * This is like the main menu of the employee it gives options and based on input other methods are called
     * @param userInput - scanner for employee input
     * @param Log - file writter used to log actions
     * @param dataList - hashMap of flight objects
     * @param customerList - hasMap of customer objects
     * @param employeeList - hashMap of employee objects
     * @throws IOException - handled in method signature since it is overwritten by other exeptions on method
     */
    public void managerMenu(Scanner userInput, FileWriter Log, HashMap<Integer, Flight> dataList, HashMap<Integer, Customer> customerList, HashMap<Integer,Employee> employeeList) throws IOException{
        this.customerList = customerList;
        this.employeeList = employeeList;
        boolean keepGoing = true;
        
            while(keepGoing == true){
                System.out.println("Type in ID: ");
                String usersID = userInput.nextLine();
                //making sure the ID is valid to avoid errors
                if(Integer.parseInt(usersID) <= dataList.size()){
                    System.out.println("This is the flight's ID: "+usersID);
                    //finding where in the hashmap is the wanted object based on the id 
                    if(dataList.containsKey(Integer.parseInt(usersID))){  
                        objectLocation = Integer.parseInt(usersID);
                    }
                    System.out.println("What do you want to do\n\t[1]Update Information\n\t[2]Obtain Information\n\t[3]Print all details\n\t[4]Cancel Flight\n(Type 1 or 2 or 3)");
                    String updateObtain = userInput.nextLine();
                    //There are 3 options (make update/just obtain details/show all details)
                    //Menu for updates that can be made
                    if(updateObtain.equals("1")){
                        System.out.println("------------ UPDATE INFORMATION OPTIONS MENU ------------");
                        System.out.println("\t(A) Update Origin Airport");
                        System.out.println("\t(B) Update Origin Code");
                        System.out.println("\t(C) Update Destination Airport");
                        System.out.println("\t(D) Update Destination Code");
                        System.out.println("\t(E) Update Departure Date");
                        System.out.println("\t(F) Update Departure Time");
                        System.out.println("\t(G) Update First Class Price");
                        System.out.println("\t(H) Update Business Class Price");
                        System.out.println("\t(I) Update Main Cabin Price");
                        System.out.print("Input the letter(A-I) for the option wanted to be process: ");
                        String makeUpdate = userInput.nextLine();
                        try{
                            updateInfo(Log, userInput, dataList, makeUpdate, objectLocation);
                        }catch(IOException e){
                            System.out.println("The Log file was not found!");
                            System.out.println(e.getMessage());
                        }
                    
                    //Menu for details that can be returned 
                    }else if(updateObtain.equals("2")){
                    //arrival date/time are modified if departure time/date are modified
                        System.out.println("------------ OBTAIN INFORMATION OPTIONS MENU ------------");
                        System.out.println("\t(A) Get Origin Airport");
                        System.out.println("\t(B) Get Origin Code");
                        System.out.println("\t(C) Get Destination Airport");
                        System.out.println("\t(D) Get Destination Code");
                        System.out.println("\t(E) Get Departure Date");
                        System.out.println("\t(F) Get Departure Time");
                        System.out.println("\t(G) Get Arrival Date");
                        System.out.println("\t(H) Get Arrival Time");
                        System.out.println("\t(I) Get Duration");
                        System.out.println("\t(J) Get Distance");
                        System.out.println("\t(K) Get Time Zone Difference");
                        System.out.println("\t(L) Get First Class Price");
                        System.out.println("\t(M) Get Business Class Price");
                        System.out.println("\t(N) Get Main Cabin Price");
                        //menu items added for PA2
                        System.out.println("\t(O) Get First Class Seats Remaining");
                        System.out.println("\t(P) Get Business Class Seats Remaining");
                        System.out.println("\t(Q) Get Main Cabin Seats Remaining");
                        System.out.println("\t(R) Get Total Seats Remaining");
                        System.out.println("\t(S) Get Customers on flight");
                        System.out.println("\t(T) Get First Class Profit");
                        System.out.println("\t(U) Get Business Class Profit");
                        System.out.println("\t(V) Get Main Class Profit");
                        System.out.println("\t(W) Get Current Profit(all seat types)");
                        System.out.println("\t(X) Get Total Expected Profit");
                        System.out.println("\t(Y) Get Total Discounted Amount");
                        System.out.print("Input the letter(A-X) for the option wanted to process: ");
                        String getInf = userInput.nextLine();
                        try{
                            System.out.println(getInformation(Log, dataList, getInf, objectLocation));
                        }catch(IOException e){
                            System.out.println("The Log file was not found!");
                            System.out.println(e.getMessage());
                        }
                    
                    //Accesing all details through "printFlight()" method
                    }else if(updateObtain.equals("3")){
                        System.out.println(dataList.get(objectLocation).printFlight());
                        System.out.println("Total Seats Remaining: "+dataList.get(objectLocation).getTotalSeats());
                        try{
                            Log.write("Flight ID: " + dataList.get(objectLocation).getID() +" viewed All Details\n");
                        }catch(IOException e){
                            System.out.println("The Log file was not found!");
                            System.out.println(e.getMessage());
                        }
                    //calling the cancel method for the wanted id
                    }else if(updateObtain.equals("4")){
                        System.out.println(cancelFlight(dataList, Log));
                    }else{
                        System.out.println("Can't process your input, please try again...");
                    }
                    //the employee can continue staying in the manager menu as long as they want
                    System.out.println("Do you want to keep making changes or obtaining information? (Type YES or NO)");
                    String again = userInput.nextLine();

                    //continue process
                    if(!(again.toLowerCase()).equals("yes")){
                        keepGoing = false;
                        System.out.print("Okay!\n");
                    }
                    
                                 
                }else{
                    System.out.println("That flight ID is invalid!");         
                }
            }
    }
    /**
     * Method to make update on flight attributes
     * @param Log - file writter to log actions
     * @param userInput - scanner to continue employee input
     * @param dataList - hashMap with flight objects
     * @param letter - option chosen on main menu by employee
     * @param location - ID of flight being handleed by employee
     * @throws IOException - handled in method signature since it is overwritten by other exeptions on method
     */
    public static void updateInfo(FileWriter Log, Scanner userInput, HashMap<Integer, Flight> dataList, String letter, int location) throws IOException{
        
        if(letter.equals("A")){
            System.out.print("Update Origin Airport to: ");
            String input = userInput.nextLine();
            dataList.get(location).setOriginAirportName(input);
            Log.write("Flight ID: " + dataList.get(location).getID() +" updated Origin Airport to "+input+"\n");
        }
        if(letter.equals("B")){
            System.out.print("Update Origin Code to: ");
            String input = userInput.nextLine();
            dataList.get(location).setOriginCode(input);
            Log.write("Flight ID: " + dataList.get(location).getID() +" updated Origin Code to "+input+"\n");
        }
        if(letter.equals("C")){
            System.out.print("Update Destination Airport to: ");
            String input = userInput.nextLine();
            dataList.get(location).setDestinationName(input);
            Log.write("Flight ID: " + dataList.get(location).getID() +" updated Destination Airport to "+input+"\n");
        }
        if(letter.equals("D")){
            System.out.print("Update Destination Code to: ");
            String input = userInput.nextLine();
            dataList.get(location).setDestinationCode(input);
            Log.write("Flight ID: " + dataList.get(location).getID() +" updated Destination Code to "+input+"\n");
        }
        if(letter.equals("E")){
            System.out.print("Update Departure Date to: ");
            String input = userInput.nextLine();
            dataList.get(location).setDepartureDate(input);
            Log.write("Flight ID: " + dataList.get(location).getID() +" updated Departure Date to "+input+"\n");
        }
        if(letter.equals("F")){
            System.out.print("Update Departure Time to: ");
            String input = userInput.nextLine();
            dataList.get(location).setDepartureTime(input);
            Log.write("Flight ID: " + dataList.get(location).getID() +" updated Departure Time to  "+input+"\n");
        }
        if(letter.equals("G")){
            System.out.print("Update First Class Price to: ");
            String input = userInput.nextLine();
            dataList.get(location).setFirstClassPrice(Integer.parseInt(input));
            Log.write("Flight ID: " + dataList.get(location).getID() +" updated First Class Price to "+input+"\n");
        }
        if(letter.equals("H")){
            System.out.print("Update Business Class Price to: ");
            String input = userInput.nextLine();
            dataList.get(location).setBusinessClassPrice(Integer.parseInt(input));
            Log.write("Flight ID: " + dataList.get(location).getID() +" updated Business Class Price to "+input+"\n");
        }
        if(letter.equals("I")){
            System.out.print("Update Main Cabin Price to: ");
            String input = userInput.nextLine();
            dataList.get(location).setMainCabinPrice(Integer.parseInt(input));
            Log.write("Flight ID: " + dataList.get(location).getID() +" updated Main Cabin Price to "+input+"\n");
        }
    }
    /**
     * Method for employee to just obtain or see information of flight
     * @param Log - file writter to log actions 
     * @param dataList - hashMap of flight attributes
     * @param letter - option chosen on main menu by employee
     * @param location - ID of flight being handdled
     * @return - string with attribute wanted to be obtained about flight
     * @throws IOException - handled in method signature since it is overwritten by other exeptions on method
     */
    public static String getInformation(FileWriter Log, HashMap<Integer, Flight> dataList, String letter, int location) throws IOException{
        if(letter.equals("A")){
            Log.write("Flight ID: " + dataList.get(location).getID() +" viewed Origin Airport\n");
            return dataList.get(location).getOriginAirportName();
        }
        if(letter.equals("B")){
            Log.write("Flight ID: " + dataList.get(location).getID() +" viewed Origin Code\n");
            return dataList.get(location).getOriginAirportCode();
        }
        if(letter.equals("C")){
            Log.write("Flight ID: " + dataList.get(location).getID() +" viewed Destination Airport\n");
            return dataList.get(location).getDestinationAirportName();
        }
        if(letter.equals("D")){
            Log.write("Flight ID: " + dataList.get(location).getID() +" viewed Destination Code\n");
            return dataList.get(location).getDestinationAirportCode();
        }
        if(letter.equals("E")){
            Log.write("Flight ID: " + dataList.get(location).getID() +" viewed Departure Date\n");
            return dataList.get(location).getDepartureDate();
        }
        if(letter.equals("F")){
            Log.write("Flight ID: " + dataList.get(location).getID() +" viewed Departure Time\n");
            return dataList.get(location).getDepartureTime();
        }
        if(letter.equals("G")){
            Log.write("Flight ID: " + dataList.get(location).getID() +" viewed Arrival Date\n");
            return dataList.get(location).getArrivalDate();
        }
        if(letter.equals("H")){
            Log.write("Flight ID: " + dataList.get(location).getID() +" viewed Arrival Time\n");
            return dataList.get(location).getArrivalTime();
        }
        if(letter.equals("I")){
            Log.write("Flight ID: " + dataList.get(location).getID() +" viewed Duration\n");
            return String.valueOf(dataList.get(location).getDuration());
        }
        if(letter.equals("J")){
            Log.write("Flight ID: " + dataList.get(location).getID() +" viewed Distance\n");
            return String.valueOf(dataList.get(location).getDistance());
        }
        if(letter.equals("K")){
            Log.write("Flight ID: " + dataList.get(location).getID() +" viewed Time Zone Difference\n");
            return String.valueOf(dataList.get(location).getTimeZoneDiff());
        }
        if(letter.equals("L")){
            Log.write("Flight ID: " + dataList.get(location).getID() +" viewed First Class Price\n");
            return String.valueOf(dataList.get(location).getFirstClassPrice());
        }
        if(letter.equals("M")){
            Log.write("Flight ID: " + dataList.get(location).getID() +" viewed Business Class Price\n");
            return String.valueOf(dataList.get(location).getBusinessClassPrice());
        }
        if(letter.equals("N")){
            Log.write("Flight ID: " + dataList.get(location).getID() +" viewed Main Cabin Price\n");
            return String.valueOf(dataList.get(location).getMainCabinPrice());
        }
        if(letter.equals("O")){
            Log.write("Flight ID: " + dataList.get(location).getID() +" viewed First Class Seats\n");
            return String.valueOf(dataList.get(location).getFirstClassSeats());
        }
        if(letter.equals("P")){
            Log.write("Flight ID: " + dataList.get(location).getID() +" viewed Business Class Seats\n");
            return String.valueOf(dataList.get(location).getBusinessClassSeats());
        }
        if(letter.equals("Q")){
            Log.write("Flight ID: " + dataList.get(location).getID() +" viewed Main Cabin Seats\n");
            return String.valueOf(dataList.get(location).getMainCabinSeats());
        }
        if(letter.equals("R")){
            Log.write("Flight ID: " + dataList.get(location).getID() +" viewed Total Seats\n");
            return String.valueOf(dataList.get(location).getTotalSeats());
        }
        if(letter.equals("S")){
            Log.write("Flight ID: " + dataList.get(location).getID() +" viewed Customers in flight\n");
            return dataList.get(location).printCustomersInFlight();
        }
        if(letter.equals("T")){
            Log.write("Flight ID: " + dataList.get(location).getID() +" viewed First Class Profit\n");
            return "$"+String.valueOf(computeFirstClassProfit(dataList));
        }
        if(letter.equals("U")){
            Log.write("Flight ID: " + dataList.get(location).getID() +" viewed Business Class Profit\n");
            return "$"+String.valueOf(computeBusinessClassProfit(dataList));
        }
        if(letter.equals("V")){
            Log.write("Flight ID: " + dataList.get(location).getID() +" viewed First Main Class Profit\n");
            return "$"+String.valueOf(computeMainClassProfit(dataList));
        }
        if(letter.equals("W")){
            Log.write("Flight ID: " + dataList.get(location).getID() +" viewed Total Current Profit\n");
            return "$"+String.valueOf(computeMainClassProfit(dataList)+computeBusinessClassProfit(dataList)+computeFirstClassProfit(dataList));
        }
        if(letter.equals("X")){
            Log.write("Flight ID: " + dataList.get(location).getID() +" viewed Total Expected Profit\n");
            return "$"+String.valueOf(computeExpectedProfit(dataList));
        }
        if(letter.equals("Y")){
            Log.write("Flight ID: " + dataList.get(location).getID() +" viewed total flight discounts\n");
            return "$"+String.valueOf(dataList.get(location).getTotalFlightDiscount());
        }
        else{
            return "Something went wrong with the input... Please try again!";
        }
    }
    /**
     * Method that computes first class seats sold profit
     * @param dataList - hashMap of flight objects
     * @return - integer of computed profit
     */
    public static int computeFirstClassProfit(HashMap<Integer, Flight> dataList){
        return (dataList.get(objectLocation).getFirstClassPrice()+dataList.get(objectLocation).getSurcharge()) * dataList.get(objectLocation).getFirstClassSeatsSold();
    }
    /**
     * Method that computes business class seats sold profit
     * @param dataList - hashMap of flight objects
     * @return - integer of computed profit
     */
    public static int computeBusinessClassProfit(HashMap<Integer, Flight> dataList){
        return (dataList.get(objectLocation).getBusinessClassPrice()+dataList.get(objectLocation).getSurcharge()) * dataList.get(objectLocation).getBusinessClassSeatsSold();
    }
    /**
     * Method that computes main class seats sold profit
     * @param dataList - hashMap of flight objects
     * @return - integer of computed profit
     */
    public static int computeMainClassProfit(HashMap<Integer, Flight> dataList){
        return (dataList.get(objectLocation).getMainCabinPrice()+dataList.get(objectLocation).getSurcharge()) * dataList.get(objectLocation).getMainCabinSeatsSold();
    }
    /**
     * Method that computes expected profit of all seats being sold out of flight
     * @param dataList - hashMap of flight objects
     * @return - integer of computed profit
     */
    public static int computeExpectedProfit(HashMap<Integer, Flight> dataList){
        int totalProfit = (dataList.get(objectLocation).getMainCabinSeats() *(dataList.get(objectLocation).getSurcharge() + dataList.get(objectLocation).getMainCabinPrice()))
        + (dataList.get(objectLocation).getBusinessClassSeats()*(dataList.get(objectLocation).getBusinessClassPrice()+dataList.get(objectLocation).getSurcharge()))+
        (dataList.get(objectLocation).getFirstClassSeats()*(dataList.get(objectLocation).getFirstClassPrice()+dataList.get(objectLocation).getSurcharge()));
        return totalProfit;
    }
    /**
     * Method for canceling the flight, it will return money to all customers who bought that ticket and set status to cancelled
     * @param dataList - hashMap of flight objects 
     * @param log - file writter to log actions
     * @return - string saying that the flight was cancelled
     */
    public String cancelFlight(HashMap<Integer, Flight> dataList, FileWriter log){
        String cancel = "CANCELED FLIGHT";
        //checking if flight hasn't been cancelled
        if(!dataList.get(objectLocation).getStatus().equals("CANCELLED")){
            for(Ticket value: dataList.get(objectLocation).getTicketsPurchased().values()){
                //check if is a "customer" *employees are not considered customers
                if(customerList.containsKey(value.getCustomerID())){
                    //returning money to customer
                    double moneyToReturn = value.getTotalPrice();
                    customerList.get(value.getCustomerID()).setMoneyAvailable(moneyToReturn+customerList.get(value.getCustomerID()).getMoneyAvailable());
                    System.out.println("A total of $"+moneyToReturn+" were returned. "+customerList.get(value.getCustomerID()).getFirstName()+" now has a total of $"+customerList.get(value.getCustomerID()).getMoneyAvailable()+"\n");
                    //setting the ticket to CANCEL
                    value.setStatus("*****CANCELLED*****");
                    value.setTotalPrice(0);
                    value.setNumSeatsPurchased(0);
                    //write action in log
                    try {
                        log.write("Flight "+value.getFlightID()+" Cancelled:\n");
                        log.write("A total of $"+moneyToReturn+" were returned. "+customerList.get(value.getCustomerID()).getFirstName()+" now has a total of $"+customerList.get(value.getCustomerID()).getMoneyAvailable()+"\n");
                    } catch (IOException e) {
                        System.out.println("The Log file was not found!");
                        System.out.println(e.getMessage());
                    }

                    //returning to total seats
                    dataList.get(objectLocation).setTotalSeats(dataList.get(objectLocation).getTotalSeats()+value.getQuantityTickets());
                    //returning to type of seat
                    if(value.getTypeSeat().equals("Main")){
                        dataList.get(objectLocation).setMainCabinSeats(dataList.get(objectLocation).getMainCabinSeats()+value.getQuantityTickets());
                    }else if(value.getTypeSeat().equals("Business")){
                        dataList.get(objectLocation).setBusinessClassSeats(dataList.get(objectLocation).getBusinessClassSeats()+value.getQuantityTickets());
                    }else if(value.getTypeSeat().equals("First")){
                        dataList.get(objectLocation).setFirstClassSeats(dataList.get(objectLocation).getFirstClassSeats()+value.getQuantityTickets());
                    }

                    //remove form tickets purchased customer
                    customerList.get(value.getCustomerID()).setFlightsPurchased(customerList.get(value.getCustomerID()).getFlightsPurchased()-1);
                }
                else{
                    //returning money to customer
                    double moneyToReturn = value.getTotalPrice();
                    employeeList.get(value.getCustomerID()).setMoneyAvailable(moneyToReturn+employeeList.get(value.getCustomerID()).getMoneyAvailable());
                    System.out.println("A total of $"+moneyToReturn+" were returned. "+employeeList.get(value.getCustomerID()).getFirstName()+" now has a total of $"+employeeList.get(value.getCustomerID()).getMoneyAvailable()+"\n");
                    //setting the ticket to CANCEL
                    value.setStatus("*****CANCELLED*****");
                    value.setTotalPrice(0);
                    value.setNumSeatsPurchased(0);
                    //write action in log
                    try {
                        log.write("Flight "+value.getFlightID()+" Cancelled:\n");
                        log.write("A total of $"+moneyToReturn+" were returned. "+employeeList.get(value.getCustomerID()).getFirstName()+" now has a total of $"+employeeList.get(value.getCustomerID()).getMoneyAvailable()+"\n");
                    } catch (IOException e) {
                        System.out.println("The Log file was not found!");
                        System.out.println(e.getMessage());
                    }

                    //returning to total seats
                    dataList.get(objectLocation).setTotalSeats(dataList.get(objectLocation).getTotalSeats()+value.getQuantityTickets());
                    //returning to type of seat
                    if(value.getTypeSeat().equals("Main")){
                        dataList.get(objectLocation).setMainCabinSeats(dataList.get(objectLocation).getMainCabinSeats()+value.getQuantityTickets());
                    }else if(value.getTypeSeat().equals("Business")){
                        dataList.get(objectLocation).setBusinessClassSeats(dataList.get(objectLocation).getBusinessClassSeats()+value.getQuantityTickets());
                    }else if(value.getTypeSeat().equals("First")){
                        dataList.get(objectLocation).setFirstClassSeats(dataList.get(objectLocation).getFirstClassSeats()+value.getQuantityTickets());
                    }

                    //remove form tickets purchased customer
                    employeeList.get(value.getCustomerID()).setFlightsPurchased(employeeList.get(value.getCustomerID()).getFlightsPurchasedNum()-1);
                }
            }
            //removing all customers form the customer list for that flight
            dataList.get(objectLocation).getCustomersInFlight().clear();
            dataList.get(objectLocation).setFlightSatus("CANCELLED");
            return cancel;
        }else{
            return "******This flight has already been cancelled!******";
        }
    }
}