
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
//import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
/**
 * @version 3- Employee has been modified three times
 * This class holds all attributes and methods needed for the functionalities of an employee
 * This class inherits form Person
 */
public class Employee extends Person{
    //Attributes
    private int employeeID;
    private double moneyAvailable;
    private int flightsPurchasedNum;
    private boolean hasMembership;
    private String username;
    private String password;
    private String role;
    //manager menu instance so that only the employee can use that menu
    //shows Composition realtionship
    private ManagerMenu menu = new ManagerMenu();
    //for employee flight purchase
    //stores the customers ticket transactions made
    private HashMap<Integer, Ticket> employeeTickets;
    //these attributes are useful when returning money to the customer or computing information
    private int mainSeatsBought;
    private int businessSeatsBought;
    private int firstSeatsBought;
    private double totalMoneyWasted;
    private double savings;

    //Constructors
    /**
     * Default Constructor
     */
    public Employee(){
    }
    /**
     * This is the main constructor of the Employee class
     * The following are attributes that make an employee
     * @param employeeIDIn - ID
     * @param firstNameIn - inherited form Person
     * @param lastNameIn - inhertied form Person
     * @param DOBIn - inherited form Person
     * @param roleIn - always employee
     * @param moneyAvailableIn
     * @param flightsPurchasedIn
     * @param hasMembershipIn
     * @param usernameIn
     * @param passwordIn
     */
    public Employee(int employeeIDIn, String firstNameIn, String lastNameIn, String DOBIn, String roleIn, double moneyAvailableIn, 
    int flightsPurchasedIn, boolean hasMembershipIn, String usernameIn, String passwordIn){
        //obtaining name attributes form parent class- Person
        super(firstNameIn, lastNameIn, DOBIn, employeeIDIn, roleIn);
        this.employeeID = employeeIDIn;
        this.moneyAvailable = moneyAvailableIn;
        this.flightsPurchasedNum = flightsPurchasedIn;
        this.hasMembership = hasMembershipIn;
        this.username = usernameIn;
        this.password = passwordIn;
        this.role = roleIn;
        //new instance of customer ticket list per customer 
        this.employeeTickets = new HashMap<Integer, Ticket>();
        this.savings=0;
    }

    //setters
    /**
     * Sets the employee ID.
     * @param employeeIDIn
     */
    public void setEmployeeID(int employeeIDIn){
        this.employeeID = employeeIDIn;
    }
    /**
     * Sets the employees money available.
     * @param moneyAvailableIn
     */
    public void setMoneyAvailable(double moneyAvailableIn){
        this.moneyAvailable = moneyAvailableIn;
    }
    /**
     * Sets the amount of flights purchased.
     * @param flightsPurchasedIn
     */
    public void setFlightsPurchased(int flightsPurchasedIn){
        this.flightsPurchasedNum = flightsPurchasedIn;
    }
    /**
     * Sets status of employee membership.
     * @param hasMembershipIn
     */
    public void setHasMembership(boolean hasMembershipIn){
        this.hasMembership = hasMembershipIn;
    }
    /**
     * Sets the employees username.
     * @param usernameIn
     */
    public void setUsername(String usernameIn){
        this.username = usernameIn;
    }
    /**
     * Sets the employee password.
     * @param passwordIn
     */
    public void setPassword(String passwordIn){
        this.password = passwordIn;
    }
    /**
     * Sets the employees role.
     * @param roleIn
     */
    public void setRole(String roleIn){
        this.role = roleIn;
    }
    /**
     * Sets the employee hashmap with the tickets.
     * @param ticketsIn
     */
    public void setEmployeeTickets(HashMap<Integer, Ticket> ticketsIn){
        this.employeeTickets = ticketsIn;
    }
    /**
     * Sets the total main seats purchased.
     * @param mainSeatsBoughtIn
     */
    public void setMainSeatsBought(int mainSeatsBoughtIn){
        this.mainSeatsBought = mainSeatsBoughtIn;
    }
    /**
     * Sets the total business seats purchased.
     * @param businessSeatsBoughtIn
     */
    public void setBusinessSeatsBought(int businessSeatsBoughtIn){
        this.businessSeatsBought = businessSeatsBoughtIn;
    }
    /**
     * Sets the total first class seats purchased.
     * @param firstSeatsBoughtIn
     */
    public void setFirstSeatsBought(int firstSeatsBoughtIn){
        this.firstSeatsBought = firstSeatsBoughtIn;
    }
    /**
     * Sets total money wasted by employee.
     * @param moneyWastedIn
     */
    public void setMoneyWasted(double moneyWastedIn){
        this.totalMoneyWasted = moneyWastedIn;
    }
    /**
     * Sets employee savings.
     * @param savingsIn
     */
    public void setSavings(double savingsIn){
        this.savings=savingsIn;
    }

    //getters
    /**
     * Gets employee ID.
     * @return - int
     */
    public int getEmployeeID(){
        return this.employeeID;
    }
    /**
     * Gets employee money available.
     * @return - double
     */
    public double getMoneyAvailable(){
        return Math.round(this.moneyAvailable *100.0)/100.0;
    }
    /**
     * Gets number of flights purchased by the employee.
     * @return - int 
     */
    public int getFlightsPurchasedNum(){
        return this.flightsPurchasedNum;
    }
    /**
     * Gets status of employee membership.
     * @return - boolean
     */
    public boolean getHasMembership(){
        return this.hasMembership;
    }
    /**
     * Gets employee username.
     * @return - string
     */
    public String getUsername(){
        return this.username;
    }
    /**
     * Gets employee password.
     * @return - string
     */
    public String getPassword(){
        return this.password;
    }
    /**
     * Gets employee role, which is employee!
     * @return - string
     */
    public String getRole(){
        return this.role;
    }
    /**
     * Gets employee hashmap of tickets purchased.
     * @return - hashmap
     */
    public HashMap<Integer,Ticket> getEmployeeTickets(){
        return this.employeeTickets;
    }
    /**
     * Gets total main seats bought by employee.
     * @return
     */
    public int getMainSeatsBought(){
        return this.mainSeatsBought;
    }
    /**
     *  Gets total business seats bought by employee.
     * @return - int
     */
    public int getBusinessSeatsBought(){
        return this.businessSeatsBought;
    }
    /**
     *  Gets total first class seats bought by employee.
     * @return - int
     */
    public int getFirstSeatsBought(){
        return this.firstSeatsBought;
    }
    /**
     * Gets total money wated by employee.
     * @return - double
     */
    public double getMoneyWasted(){
        return Math.round(this.totalMoneyWasted *100.0)/100.0;
    }
    /**
     * Gets total savings of employee.
     * @return - double
     */
    public double getSavings(){
        return Math.round(this.savings *100.0)/100.0;
    }

   /**
    * Adds ticket objects to the hashMap of tickets that the eployee has purchased
    * @param newTicket - Ticket object being added
    * @param ticketCountIn - used for the key on the hashMap(random number)
    */
    public void addTicket(Ticket newTicket, int ticketCountIn){
        this.employeeTickets.put(ticketCountIn, newTicket);   
    }
    /**
     * Prints all the tickets in the employee list of tickets
     */
    public void printTickets(){
        for(Ticket value: employeeTickets.values()){
            System.out.println(value.printTicket());
        }
    }

    /**
     * Method used to open manager menu which allows employee to inquire information or cancel flight based on ID of flight
     * @param input - scanner object for input
     * @param Log - file writter to log actions
     * @param dataList - hashMap of flight objects
     * @param customerList - hashMap of customer objects
     * @param employeeList - hashMap of employee objects
     */
    public void openManagerMenu(Scanner input, FileWriter Log, HashMap<Integer,Flight> dataList, HashMap<Integer,Customer> customerList, HashMap<Integer, Employee> employeeList){
        try {
            //opening menu
            this.menu.managerMenu(input, Log, dataList, customerList, employeeList);
        }catch(IllegalArgumentException e){
            System.out.println("There was an issue with your input!");
            System.out.println(e.getMessage());
        }catch(IllegalStateException e){
            System.out.println("The user interaction has terminated!");
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("Log file is not found!");
            e.printStackTrace();
        }
    }
    /**
     * Method that allows employee functionality of obtaining airport information by code
     * @param flightList - hashMap with flight objects
     * @param airportCode - string of airport code
     */
    public void airportInformation(HashMap<Integer, Flight> flightList,String airportCode){
        int flightIDwithAirport = 0;
        String airportType = null;
        boolean change = false;
        //finds flight with that airport to get airport because we don't have a list of airports
        for(Flight value:flightList.values()){
            if(change==false){
                if(value.getDestinationAirportCode().equals(airportCode)){
                    flightIDwithAirport=value.getID();
                    airportType = "dest";
                    change = true;
                }else if(value.getOriginAirportCode().equals(airportCode)){
                    flightIDwithAirport=value.getID();
                    airportType = "origin";
                    change = true;
                }
            }
        }
        double totalEarnings = 0.0;
        for(Flight value: flightList.values()){
            if(value.getDestinationAirportCode().equals(airportCode)){
                totalEarnings += value.getDestinationAirport().getMoneyEarned();
            }
            if(value.getOriginAirportCode().equals(airportCode)){
                totalEarnings += value.getOriginAirport().getMoneyEarned();
            }
            
        }
        //printing data
        if(flightIDwithAirport!=0 & airportType!=null){
            if(airportType.equals("origin")){
                System.out.println("Airport Code: "+airportCode);
                System.out.println("Airport Name: "+flightList.get(flightIDwithAirport).getOriginAirportName());
                System.out.println("Airport City: "+flightList.get(flightIDwithAirport).getOriginAirport().getCity());
                System.out.println("Airport State: "+flightList.get(flightIDwithAirport).getOriginAirport().getState());
                System.out.println("Airport Country: "+flightList.get(flightIDwithAirport).getOriginAirport().getCountry());
                System.out.println("Airport Fee: "+flightList.get(flightIDwithAirport).getOriginAirport().getFee());
                System.out.println("Airport Lounge Status: "+flightList.get(flightIDwithAirport).getOriginAirport().getLounge());
                System.out.println("Money Earned: "+totalEarnings);
            }
            if(airportType.equals("dest")){
                System.out.println("Airport Code: "+airportCode);
                System.out.println("Airport Name: "+flightList.get(flightIDwithAirport).getDestinationAirportName());
                System.out.println("Airport City: "+flightList.get(flightIDwithAirport).getDestinationAirport().getCity());
                System.out.println("Airport State: "+flightList.get(flightIDwithAirport).getDestinationAirport().getState());
                System.out.println("Airport Country: "+flightList.get(flightIDwithAirport).getDestinationAirport().getCountry());
                System.out.println("Airport Fee: "+flightList.get(flightIDwithAirport).getDestinationAirport().getFee());
                System.out.println("Airport Lounge Status: "+flightList.get(flightIDwithAirport).getDestinationAirport().getLounge());
                System.out.println("Money Earned: "+totalEarnings);
            }
        }else{
            System.out.println("Invalid Airport Code!");
        }
    }
    /**
     * Method that handles employee fnctionality of generating a ticket summary of a certain customer only
     * @param newInfo - the text file in which the electronic ticket will be outputed
     * @param customerID - the id of the customer for which the electronic ticket summary will be generated
     * @param customerList - hashMap of customer objects
     * @param flightList - hashMp of flight objects
     */
    public void ticketSummary(File newInfo, int customerID, HashMap<Integer,Customer> customerList, HashMap<Integer,Flight> flightList){
        try {
            FileWriter fileWriter = new FileWriter(newInfo);
            if(customerList.get(customerID).getCustomerTickets().size()>0){
                fileWriter.write("Name: "+customerList.get(customerID).getFirstName()+" "+customerList.get(customerID).getLastName()+"\n");
                fileWriter.write("******************** Ticket Summary ********************\n");
                for(Ticket ticket:customerList.get(customerID).getCustomerTickets().values()){
                    fileWriter.write("1.Confirmation Number: "+ticket.getConfirmationNum()+"\n");
                    fileWriter.write("2.Flight Origin Airport Code: "+flightList.get(ticket.getFlightID()).getOriginAirportCode()+"\n");
                    fileWriter.write("3.Flight Origin Airport Name: "+flightList.get(ticket.getFlightID()).getOriginAirportName()+"\n");
                    fileWriter.write("4.Flight Destination Airport Code: "+flightList.get(ticket.getFlightID()).getDestinationAirportCode()+"\n");
                    fileWriter.write("5.Flight Destination Airport Name: "+flightList.get(ticket.getFlightID()).getDestinationAirportName()+"\n");
                    fileWriter.write("6.Departure Date: "+flightList.get(ticket.getFlightID()).getDepartureDate()+"\n");
                    fileWriter.write("7.Departure Time: "+flightList.get(ticket.getFlightID()).getDepartureTime()+"\n");
                    fileWriter.write("8.Arrival Date: "+flightList.get(ticket.getFlightID()).getArrivalDate()+"\n");
                    fileWriter.write("9.Arrival Time: "+flightList.get(ticket.getFlightID()).getArrivalTime()+"\n");
                    fileWriter.write("10.Ticket Type: "+ticket.getTypeSeat()+"\n");
                    fileWriter.write("11.Ticket Quantity: "+ticket.getQuantityTickets()+"\n");
                    fileWriter.write("12.Total Cost: "+ticket.getTotalPrice()+"\n");
                }
                fileWriter.flush();
            }else{
                fileWriter.write("No Tickets!\n");
                fileWriter.flush();
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("The file couldn't be found!");
            e.printStackTrace();
        }
    }
    /**
     * Method that handles employee fnctionality of generating a ticket summary of a certain employee only
     * @param newInfo - text file in which the electronic ticket summary will be outputed
     * @param customerID - the id of the employee wanted to purchase the flight
     * @param customerList - hashMap of employee objects
     * @param flightList - hashMap of flight objects
     */
    public void ticketSummaryEmployee(File newInfo, int customerID, HashMap<Integer,Employee> customerList, HashMap<Integer,Flight> flightList){
        try {
            FileWriter fileWriter = new FileWriter(newInfo);
            if(customerList.get(customerID).getEmployeeTickets().size()>0){
                fileWriter.write("Name: "+customerList.get(customerID).getFirstName()+" "+customerList.get(customerID).getLastName()+"\n");
                fileWriter.write("******************** Ticket Summary ********************\n");
                for(Ticket ticket:customerList.get(customerID).getEmployeeTickets().values()){
                    fileWriter.write("1.Confirmation Number: "+ticket.getConfirmationNum()+"\n");
                    fileWriter.write("2.Flight Origin Airport Code: "+flightList.get(ticket.getFlightID()).getOriginAirportCode()+"\n");
                    fileWriter.write("3.Flight Origin Airport Name: "+flightList.get(ticket.getFlightID()).getOriginAirportName()+"\n");
                    fileWriter.write("4.Flight Destination Airport Code: "+flightList.get(ticket.getFlightID()).getDestinationAirportCode()+"\n");
                    fileWriter.write("5.Flight Destination Airport Name: "+flightList.get(ticket.getFlightID()).getDestinationAirportName()+"\n");
                    fileWriter.write("6.Departure Date: "+flightList.get(ticket.getFlightID()).getDepartureDate()+"\n");
                    fileWriter.write("7.Departure Time: "+flightList.get(ticket.getFlightID()).getDepartureTime()+"\n");
                    fileWriter.write("8.Arrival Date: "+flightList.get(ticket.getFlightID()).getArrivalDate()+"\n");
                    fileWriter.write("9.Arrival Time: "+flightList.get(ticket.getFlightID()).getArrivalTime()+"\n");
                    fileWriter.write("10.Ticket Type: "+ticket.getTypeSeat()+"\n");
                    fileWriter.write("11.Ticket Quantity: "+ticket.getQuantityTickets()+"\n");
                    fileWriter.write("12.Total Cost: "+ticket.getTotalPrice()+"\n");
                }
                fileWriter.flush();
            }else{
                fileWriter.write("No Tickets!\n");
                fileWriter.flush();
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("The file couldn't be found!");
            e.printStackTrace();
        }
    }
    /**
     * Method for allowing employees to purchase flights and recieve tickets just like customer
     * @param Log - file writer to log actions
     * @param flightList - hashMap of flight objects
     * @param input - scanner for input
     * @param customerIndexInList - the employee ID
     * @param customerList - the list of employees
     * @param moreTransactions - used for continuesly usage of the overall system
     * @throws IOException - on method signature bacause all other exceptions overwrite it
     */
    public void sellFlightTicketsEmployee(FileWriter Log, HashMap<Integer, Flight> flightList, Scanner input, int customerIndexInList, HashMap<Integer, Employee> customerList, boolean moreTransactions) throws IOException{
        try{
            //showcases the domestic flights
            System.out.println("These are some of the flights available:");
            for(Flight value: flightList.values()){
                System.out.println("ID: "+(value.getID())+", "+(value.printFlightLine()));
                System.out.println("----------------------------------------------------------------------------");
            }
            boolean again = true;
            boolean change = false;
            int flightID = 0;
            boolean exit = false;
            //as long as ID is valid it will proceed or the customer can also EXIT
            while(again == true){
                System.out.println("Type the ID number of the flight you would like to purchase: ");
                String flightId = input.nextLine();
                if(flightId.equals("EXIT")){
                    Log.write("EXIT\n");
                    exit=true;
                    break;
                }
                flightID = Integer.parseInt(flightId);
                //checks if ID exists or flight is OPEN (not cancelled)
                if(flightList.containsKey(flightID) && flightList.get(flightID).getStatus().equals("OPEN")){  
                    again = false;
                    change = true;
                }else if(change == false){
                    System.out.println("That ID is invalid for these set of flights! Try again or type EXIT.");
                }
                
            }
            if(exit==false){
            //shows them their flight, in case it is not correct they can EXIT and log in again
                System.out.println("This is the flight you choose: " + (flightList.get(flightID)).printFlight());
                again = true;
                String numTickets = null;
                //if less than 1 or more than 8 tickets are wanted it will return the prompt
                while(again == true){
                    System.out.println("How many tickets would you like for that flight? (1-8)");
                    numTickets = input.nextLine();
                    //can only purchase 1-8 tickets in one transaction
                    if(numTickets.equals("EXIT")){
                        Log.write("EXIT\n");
                        exit = true;
                        break;
                    }
                    if(Integer.parseInt(numTickets) < 1 | Integer.parseInt(numTickets) > 8){
                        System.out.println("You can only buy 1 to 8 tickets per transaction! You can try again or type EXIT to end.");
                    }else{
                        again = false;
                    }
                }
                //showcasing prices
                again = true;
                String typeTicket = null;
                int totalPrice = 0;
                
                //checking if seats are sold out
                String mainSoldOut = "";
                if(flightList.get(flightID).getMainCabinSeats()<1){
                    mainSoldOut = " -SOLD OUT-";
                }
                String businessSoldOut = "";
                if(flightList.get(flightID).getBusinessClassSeats()<1){
                    businessSoldOut = " -SOLD OUT-";
                }
                String firstSoldOut = "";
                if(flightList.get(flightID).getFirstClassSeats()<1){
                    firstSoldOut = " -SOLD OUT-";
                }
                //print the class prices and ask for one
                if(exit== false){
                    System.out.println("These are the prices for your flight:\n[1] Main Cabin Price: $"+flightList.get(flightID).getMainCabinPrice()+mainSoldOut+
                    "\n[2] Business Class Price: $"+flightList.get(flightID).getBusinessClassPrice()+businessSoldOut+"\n[3] First Class Price: $"+
                    flightList.get(flightID).getFirstClassPrice()+firstSoldOut);
                    if(flightList.get(flightID).getFlightType().equals("International")){
                        System.out.println("Since it is an international flight it has a surcharge per ticket of: $"+flightList.get(flightID).getSurcharge());
                    }
                }  
                while(again == true && exit==false){
                    System.out.println("Which type of flight would you want your tickets to be? (Type 1, 2, or 3)");
                    typeTicket = input.nextLine();
                    //EXIT
                    if(typeTicket.equals("EXIT")){
                        Log.write("EXIT\n");
                        break;
                    }
                    //membership discount
                    double discount=0;
                    //EMPLOYEE discount
                    double employeeMainBusinessDiscount=0;
                    double employeeFirstClassDiscount=0;
                    //seeting the type of seat wanted to use in ticket
                    String typeSeat = null;
                    //if wrong option is chosen then can't procced since there are only options 1, 2, and 3
                    boolean wrongOption = false;
                    //1- main cabin
                    if(typeTicket.equals("1")){
                        again = false;
                        typeSeat = "Main";
                        //handeling customer discount
                        if(customerList.get(customerIndexInList).getHasMembership()==true){
                            discount = ((flightList.get(flightID).getMemberDiscount()*flightList.get(flightID).getMainCabinPrice())-flightList.get(flightID).getMainCabinPrice())*Integer.parseInt(numTickets);
                            discount = Math.round(discount * 100.0)/100.0;
                        }
                        employeeMainBusinessDiscount=((flightList.get(flightID).getMainCabinPrice()/4.0)*3.0)*Integer.parseInt(numTickets);
                        totalPrice = (Integer.parseInt(numTickets) * flightList.get(flightID).getMainCabinPrice()) + (flightList.get(flightID).getSurcharge() *Integer.parseInt(numTickets));
                        //checking there are enough seats
                        if(flightList.get(flightID).getMainCabinSeats()- Integer.parseInt(numTickets) < 0){
                            System.out.println("There aren't enough seats of that type available, try another type of flight or type EXIT to end");
                            Log.write(customerList.get(customerIndexInList).getFirstName()+" " +customerList.get(customerIndexInList).getLastName()+" tried buying tickets but there weren't enough seats!\n");
                            again = true;
                        }
                    }
                    //2- business class
                    else if(typeTicket.equals("2")){
                        again = false;
                        typeSeat = "Business";
                        //handeling customer discount
                        if(customerList.get(customerIndexInList).getHasMembership()==true){
                            discount = ((flightList.get(flightID).getMemberDiscount()*flightList.get(flightID).getBusinessClassPrice())-flightList.get(flightID).getBusinessClassPrice())*Integer.parseInt(numTickets);
                            discount = Math.round(discount * 100.0)/100.0;
                        }
                        employeeMainBusinessDiscount=((flightList.get(flightID).getBusinessClassPrice()/4)*3)*Integer.parseInt(numTickets);
                        totalPrice = (Integer.parseInt(numTickets) * flightList.get(flightID).getBusinessClassPrice()) +(flightList.get(flightID).getSurcharge()*Integer.parseInt(numTickets));
                        //checking there are enough seats
                        if(flightList.get(flightID).getBusinessClassSeats()- Integer.parseInt(numTickets) < 0){
                            System.out.println("There aren't enough seats of that type available, try another type of flight or type EXIT to end");
                            Log.write(customerList.get(customerIndexInList).getFirstName()+" " +customerList.get(customerIndexInList).getLastName()+" tried buying tickets but there weren't enough seats!\n");
                            again = true;
                        }
                    }
                    //3- first class
                    else if(typeTicket.equals("3")){
                        again = false;
                        typeSeat = "First";
                        //employee class discount 50% off
                        employeeFirstClassDiscount=(flightList.get(flightID).getMainCabinPrice()/2)*Integer.parseInt(numTickets);
                        //handeling customer discount
                        if(customerList.get(customerIndexInList).getHasMembership()==true){
                            discount = ((flightList.get(flightID).getMemberDiscount()*flightList.get(flightID).getFirstClassPrice())-flightList.get(flightID).getFirstClassPrice())*Integer.parseInt(numTickets);
                            discount = Math.round(discount * 100.0)/100.0;
                        }
                        totalPrice = (Integer.parseInt(numTickets) * flightList.get(flightID).getFirstClassPrice()) +(flightList.get(flightID).getSurcharge()*Integer.parseInt(numTickets));
                        //checking there are enough seats
                        if(flightList.get(flightID).getFirstClassSeats()- Integer.parseInt(numTickets) < 0){
                            System.out.println("There aren't enough seats of that type available, try another type of flight or type EXIT to end");
                            Log.write(customerList.get(customerIndexInList).getFirstName()+" " +customerList.get(customerIndexInList).getLastName()+" tried buying tickets but there weren't enough seats!\n");
                            again = true;
                        }

                    }else{
                        //in case a different input is placed
                        System.out.println("You only have option 1, 2, or 3! You can try again or type EXIT to end.");
                        wrongOption = true;
                    }

                    //FEES/DISCOUNTS/TAXES
                    //miner airlines fee
                    System.out.println("\tMiner Airlines Fee: $"+flightList.get(flightID).getMinerAirlinesFee());
                    //security
                    double securityFee=flightList.get(flightID).getSecurityFee()*Integer.parseInt(numTickets);
                    securityFee = Math.round(securityFee * 100.0)/100.0;
                    System.out.println("\tSecurity Fee: $"+securityFee);
                    //airport fees
                    //double airportFees=(flightList.get(flightID).getOriginAirportFee()*Integer.parseInt(numTickets)) + (flightList.get(flightID).getDestinationAirportFee()*Integer.parseInt(numTickets));
                    double airportFees=flightList.get(flightID).getOriginAirportFee() + flightList.get(flightID).getDestinationAirportFee();
                    airportFees = Math.round(airportFees * 100.0)/100.0;
                    System.out.println("\tAirport Fees: $"+airportFees);
                    //discount only if member
                    if(customerList.get(customerIndexInList).getHasMembership()==true){
                        System.out.println("\tMember Total Discount: $"+discount);
                    }
                    System.out.println("\tEmployee Discount Total: $"+(employeeFirstClassDiscount+employeeMainBusinessDiscount));
                    double subtotalBeforeTax= totalPrice+flightList.get(flightID).getMinerAirlinesFee()+securityFee+airportFees-(discount+employeeFirstClassDiscount+employeeMainBusinessDiscount);
                    subtotalBeforeTax = Math.round(subtotalBeforeTax * 100.0)/100.0;
                    //tax
                    double tax= (subtotalBeforeTax*flightList.get(flightID).getTax())-subtotalBeforeTax;
                    tax = Math.round(tax * 100.0)/100.0;
                    System.out.println("\tTax: $"+tax);
                    //total
                    double total = (subtotalBeforeTax+ tax);
                    total = Math.round(total * 100.0)/100.0;
                    System.out.println("\tThis is the TOTAL transaction price: $"+total);
                
                    //checking if customer has enough money available/ modifing money and seats
                    if((customerList.get(customerIndexInList).getMoneyAvailable()-total>=0) && wrongOption==false){
                        //confirming purchase
                        System.out.println("Do you want to confirm the purchase [1]YES [2]NO (type 1 or 2)");
                        String confirm = input.nextLine();
                        if(confirm.equals("1")){
                            //setting discount to customer savings/if they are not memebers discount is zero
                            customerList.get(customerIndexInList).setSavings(customerList.get(customerIndexInList).getSavings()+discount+employeeFirstClassDiscount+employeeMainBusinessDiscount);
                            //adding any discounts to the flight total discounted amount
                            flightList.get(flightID).setTotalFlightDiscount(flightList.get(flightID).getTotalFlightDiscount()+discount+employeeFirstClassDiscount+employeeMainBusinessDiscount);
                            //setting the money earned of airports
                            flightList.get(flightID).getOriginAirport().setMoneyEarned(flightList.get(flightID).getOriginAirport().getMoneyEarned()+flightList.get(flightID).getOriginAirport().getFee());
                            flightList.get(flightID).getDestinationAirport().setMoneyEarned(flightList.get(flightID).getDestinationAirport().getMoneyEarned()+flightList.get(flightID).getDestinationAirport().getFee());
                    
                            customerList.get(customerIndexInList).setFlightsPurchased(customerList.get(customerIndexInList).getFlightsPurchasedNum()+1);
                            System.out.println("*Purchase Confirmed*");
                            //setting the seats bought for the customer
                            if(typeSeat.equals("Main")){
                                customerList.get(customerIndexInList).setMainSeatsBought(customerList.get(customerIndexInList).getMainSeatsBought()+Integer.parseInt(numTickets));
                            }
                            if(typeSeat.equals("Business")){
                                customerList.get(customerIndexInList).setBusinessSeatsBought(customerList.get(customerIndexInList).getBusinessSeatsBought()+Integer.parseInt(numTickets));
                            }
                            if(typeSeat.equals("First")){
                                customerList.get(customerIndexInList).setFirstSeatsBought(customerList.get(customerIndexInList).getFirstSeatsBought()+Integer.parseInt(numTickets));
                            }
                            customerList.get(customerIndexInList).setMoneyWasted(customerList.get(customerIndexInList).getMoneyWasted()+total);
                
                            //for confirmation number
                            Random rand = new Random();
                            int randomNum = rand.nextInt(10000);
                            //creating instance of ticket
                            Ticket customerTicket = new Ticket(flightID, Integer.parseInt(numTickets), total, randomNum, Integer.parseInt(numTickets), typeSeat, customerList.get(customerIndexInList).getEmployeeID());
                            //adding ticket to the customer list of tickets
                            customerList.get(customerIndexInList).addTicket(customerTicket,randomNum);
                            customerList.get(customerIndexInList).printTickets();
                            //adding to the flight total tickets purchased
                            flightList.get(flightID).addTicket(customerTicket, randomNum);
                            //adding customer to list of customers per flight
                            if(flightList.get(flightID).getCustomersInFlight().containsKey(customerIndexInList)){
                                if(typeSeat.equals("Main")){
                                    flightList.get(flightID).getCustomersInFlight().get(customerIndexInList).set(1,"Main Class: "+customerList.get(customerIndexInList).getMainSeatsBought());
                                }else if (typeSeat.equals("Business")){
                                    flightList.get(flightID).getCustomersInFlight().get(customerIndexInList).set(2,"Business Class: "+customerList.get(customerIndexInList).getBusinessSeatsBought());
                                }else if(typeSeat.equals("First")){
                                    flightList.get(flightID).getCustomersInFlight().get(customerIndexInList).set(3, "First Class: "+customerList.get(customerIndexInList).getFirstSeatsBought());
                                }
                                flightList.get(flightID).getCustomersInFlight().get(customerIndexInList).set(4,"Total Price: $"+customerList.get(customerIndexInList).getMoneyWasted());
                            }else{
                                //creating the customer information space if the customer was not already on the list of customers for the flight
                                ArrayList<String> customerInfo = new ArrayList<String>();
                                customerInfo.add(customerList.get(customerIndexInList).getFirstName()+" "+customerList.get(customerIndexInList).getLastName());
                                customerInfo.add("Main Class: "+customerList.get(customerIndexInList).getMainSeatsBought());
                                customerInfo.add("Business Class: "+customerList.get(customerIndexInList).getBusinessSeatsBought());
                                customerInfo.add("First Class: "+customerList.get(customerIndexInList).getFirstSeatsBought());
                                customerInfo.add("Total Price: $"+customerList.get(customerIndexInList).getMoneyWasted());
                                flightList.get(flightID).addCustomer(customerInfo, customerIndexInList);
                            }
                            //writing actions in log
                            Log.write(customerList.get(customerIndexInList).getFirstName()+" " +customerList.get(customerIndexInList).getLastName()+" recieved this ticket for the transaction made:\n"+customerTicket.printTicket()+"\n");
                            //updating customers money
                            customerList.get(customerIndexInList).setMoneyAvailable((Math.round(customerList.get(customerIndexInList).getMoneyAvailable()* 100.0)/100.0)-total);
                            Log.write(customerList.get(customerIndexInList).getFirstName()+" " +customerList.get(customerIndexInList).getLastName()+" money available is now: "+(Math.round(customerList.get(customerIndexInList).getMoneyAvailable()* 100.0)/100.0)+"\n");
                            //modifing seats bought
                            if(typeTicket.equals("1")){
                                flightList.get(flightID).setMainCabinSeats((flightList.get(flightID).getMainCabinSeats())-Integer.parseInt(numTickets));
                                Log.write("Main Cabin seats for flight ID: "+flightList.get(flightID).getID()+ " is now " +flightList.get(flightID).getMainCabinSeats()+"\n");
                            }else if(typeTicket.equals("2")){
                                flightList.get(flightID).setBusinessClassSeats((flightList.get(flightID).getBusinessClassSeats())-Integer.parseInt(numTickets));
                                Log.write("Business Cabin seats for flight ID: "+flightList.get(flightID).getID()+ " is now " +flightList.get(flightID).getBusinessClassSeats()+"\n");
                            }else if(typeTicket.equals("3")){
                                flightList.get(flightID).setFirstClassSeats((flightList.get(flightID).getFirstClassSeats())-Integer.parseInt(numTickets));
                                Log.write("First Class seats for flight ID: "+flightList.get(flightID).getID()+ " is now " +flightList.get(flightID).getFirstClassSeats()+"\n");
                            }
                            //modifing total seats
                            flightList.get(flightID).setTotalSeats(flightList.get(flightID).getTotalSeats()-Integer.parseInt(numTickets));
                        }else if(confirm.equals("2")){
                            System.out.println("Sorry to hear that, you won't be charged!");
                        }else{
                            System.out.println("Incorrect option was chosen!");
                        }
                        
                    }else{
                        System.out.println("You don't have enough money available to make the transaction, try again or type EXIT to end.");
                        Log.write(customerList.get(customerIndexInList).getFirstName()+" " +customerList.get(customerIndexInList).getLastName()+" tried buying tickets but didn't had enough money available!\n");
                        again = true;
                    }
                }
            }

        }catch(IllegalArgumentException e){
            System.out.println("There was an issue with your input!");
            System.out.println(e.getMessage());
        }catch(IndexOutOfBoundsException e){
            System.out.println("That ID doesn't exists!");
            System.out.println(e.getMessage());
        }catch(IllegalStateException e){
            System.out.println("The user interaction has terminated!");
            System.out.println(e.getMessage());
        }
    }

    /**
     * Method used to cancel employees tickets
     * Intakes the ticket number of the transaction the employee wants to cancel and returns seats back to flight
     * @param ticketNum - number of ticket wanted to be cancelled
     * @param flightsList - hashMap of flight objects
     * @param log - file writter to log actions
     * @throws IOException - on method signature bacause all other exceptions overwrite it
     */
    public void cancelTicket(int ticketNum, HashMap<Integer,Flight> flightsList, FileWriter log) throws IOException{
        try{
        //flight where the ticket is located
        int ticketID = employeeTickets.get(ticketNum).getFlightID();
        //used to check if ID coresponding to ticket is existant 
        Boolean notId = false;
        int seatsPurchasedInTicket = this.employeeTickets.get(ticketNum).getNumSeatsPurchased();
        if(flightsList.containsKey(ticketID)){
            //removing money from customers in flight 
            flightsList.get(ticketID).getCustomersInFlight().get(employeeID).set(4, "Total Price: $"+(this.totalMoneyWasted-employeeTickets.get(ticketNum).getTotalPrice()));
            //returning to total seats 
            flightsList.get(ticketID).setTotalSeats(flightsList.get(ticketID).getTotalSeats()+seatsPurchasedInTicket);
            //returning to type of seat and removing seat from customers in flight list
            if(employeeTickets.get(ticketNum).getTypeSeat().equals("Main")){
                flightsList.get(ticketID).getCustomersInFlight().get(employeeID).set(1, "Main Class: "+(this.mainSeatsBought-seatsPurchasedInTicket));
                flightsList.get(ticketID).setMainCabinSeats(flightsList.get(ticketID).getMainCabinSeats()+seatsPurchasedInTicket);
            }else if(employeeTickets.get(ticketNum).getTypeSeat().equals("Business")){
                flightsList.get(ticketID).getCustomersInFlight().get(employeeID).set(2, "Business Class: "+(this.businessSeatsBought-seatsPurchasedInTicket));
                flightsList.get(ticketID).setBusinessClassSeats(flightsList.get(ticketID).getBusinessClassSeats()+seatsPurchasedInTicket);
            }else if(employeeTickets.get(ticketNum).getTypeSeat().equals("First")){
                flightsList.get(ticketID).getCustomersInFlight().get(employeeID).set(3, "First Class: "+(this.businessSeatsBought-seatsPurchasedInTicket));
                flightsList.get(ticketID).setFirstClassSeats(flightsList.get(ticketID).getFirstClassSeats()+seatsPurchasedInTicket);
            }
            log.write("A total of "+seatsPurchasedInTicket+" were returned to flight ID: "+ticketID+" this flight now has "+flightsList.get(ticketID).getTotalSeats()+" tickets\n");
            System.out.println("A total of "+seatsPurchasedInTicket+" seats were returned to flight ID: "+ticketID+" this flight now has "+flightsList.get(ticketID).getTotalSeats()+" seats");
        }else{
            //the given ticket number and id correlation doesn't exist
            System.out.println("You didn't buy that flight!");
            notId = true;
        }
        //return money to customer
        if(notId==false){
            //remove form tickets purchased
            setFlightsPurchased(getFlightsPurchasedNum()-1);
            //Miner Airlines keeps the fee!
            double moneyToReturn = employeeTickets.get(ticketNum).getTotalPrice()-flightsList.get(ticketID).getMinerAirlinesFee();
            this.moneyAvailable = this.moneyAvailable + moneyToReturn;
            System.out.println("A total of $"+moneyToReturn+" were returned. "+this.getFirstName()+" now has a total of $"+this.moneyAvailable);
            //write action in log
            try {
                log.write("A total of $"+moneyToReturn+" were returned. "+this.getFirstName()+" now has a total of $"+this.moneyAvailable+"\n");
            } catch (IOException e) {
                System.out.println("The Log file was not found!");
                System.out.println(e.getMessage());
            }
            //removing ticket from list of tikets purchased in customer and in flight
            this.employeeTickets.remove(ticketNum);
            flightsList.get(ticketID).getTicketsPurchased().remove(ticketNum);     
        }
        //exception for customer canceling ticket that was already cancelled
        }catch(NullPointerException e){
            System.out.println("The flight was already cancelled!");
            System.out.println(e.getMessage());
        }

    }

    /**
     * Method used for the creation of the updated csv file of Customers at the termination of the program
     * @param header - string array of attributes order in file
     * @return - String of employee attributes in the order of the orginal csv header
     */
    public String printEmployeeLine(String[] header){
        String temp = "";
        for(int i=0; i<header.length; i++){
            if(header[i].equals("DOB")){
                temp+=getDOB()+",";
            }else if(header[i].equals("Username")){
                temp+=this.username+",";
            }else if(header[i].equals("Money Available")){
                temp+=Math.round(this.moneyAvailable*100.0)/100.0+",";
            }else if(header[i].equals("Last Name")){
                temp+=getLastName()+",";
            }else if(header[i].equals("Password")){
                temp+=this.password+",";
            }else if(header[i].equals("Role")){
                temp+=this.role+",";
            }else if(header[i].equals("ID")){
                temp+=this.employeeID+",";
            }else if(header[i].equals("First Name")){
                temp+=getFirstName()+",";
            }else if(header[i].equals("Flights Purchased")){
                temp+=this.flightsPurchasedNum+",";
            }else if(header[i].equals("MinerAirlines Membership")){
                temp+=this.hasMembership+",";
            }
        }
        return temp;
    }
}

