

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

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
/**
 * @version 3- Customer has been modified each PA in order to include new attributes and meet requirements
 * This class inherits from the class Person and it has attributes customer objects will need
 */
public class Customer extends Person{
    //Attributes
    private int customerID;
    private double moneyAvailable;
    private int flightsPurchased;
    private boolean hasMembership;
    private String username;
    private String password;
    private String role;
    //stores the customers ticket transactions made
    private HashMap<Integer, Ticket> customerTickets;
    //these attributes are useful when returning money to the customer or computing information
    private int mainSeatsBought;
    private int businessSeatsBought;
    private int firstSeatsBought;
    private double totalMoneyWasted;
    private double savings;

    //constructors
    /**
     * Default Constructor
     */
    public Customer(){
    }
    /**
     * This is the main constructor for a Customer Object
     * @param customerIDIn 
     * @param firstNameIn - inherited from parent Person
     * @param lastNameIn - inherited form parent Person
     * @param DOBIn - inherited form parent Person
     * @param roleIn - customer 
     * @param moneyAvailableIn 
     * @param flightsPurchasedIn
     * @param hasMembershipIn
     * @param usernameIn
     * @param passwordIn
     */
    public Customer(int customerIDIn, String firstNameIn, String lastNameIn, String DOBIn, String roleIn, double moneyAvailableIn, 
    int flightsPurchasedIn, boolean hasMembershipIn, String usernameIn, String passwordIn){
        //obtaining name attributes form parent class- Person
        super(firstNameIn,lastNameIn, DOBIn, customerIDIn, roleIn);
        this.customerID = customerIDIn;
        this.moneyAvailable = moneyAvailableIn;
        this.flightsPurchased = flightsPurchasedIn;
        this.hasMembership = hasMembershipIn;
        this.username = usernameIn;
        this.password = passwordIn;
        this.role = roleIn;
        //new instance of customer ticket list per customer 
        this.customerTickets = new HashMap<Integer, Ticket>();
        this.savings=0;
    }

    //setters
    /**
     * Sets the customer ID.
     * @param customerIDIn
     */
    public void setCustomerID(int customerIDIn){
        this.customerID = customerIDIn;
    }
    /**
     * Sets the money available for the customer.
     * @param moneyAvailableIn
     */
    public void setMoneyAvailable(double moneyAvailableIn){
        this.moneyAvailable = moneyAvailableIn;
    }
    /**
     * Sets the number of flights the customer purchased.
     * @param flightsPurchasedIn
     */
    public void setFlightsPurchased(int flightsPurchasedIn){
        this.flightsPurchased = flightsPurchasedIn;
    }
    /**
     * Sets the status of the customer membership.
     * @param hasMembershipIn
     */
    public void setHasMembership(boolean hasMembershipIn){
        this.hasMembership = hasMembershipIn;
    }
    /**
     * Sets the customers username.
     * @param usernameIn
     */
    public void setUsername(String usernameIn){
        this.username = usernameIn;
    }
    /**
     * Sets the customer password. 
     * @param passwordIn
     */
    public void setPassword(String passwordIn){
        this.password = passwordIn;
    }
    /**
     * Sets the role of the customer
     * @param roleIn
     */
    public void setRole(String roleIn){
        this.role = roleIn;
    }
    /**
     * Sets the hashmap for the tickets of the customer. 
     * @param ticketsIn
     */
    public void setCustomerTickets(HashMap<Integer, Ticket> ticketsIn){
        this.customerTickets = ticketsIn;
    }
    /**
     * Sets the main seats bought by the customer.
     * @param mainSeatsBoughtIn
     */
    public void setMainSeatsBought(int mainSeatsBoughtIn){
        this.mainSeatsBought = mainSeatsBoughtIn;
    }
    /**
     * Sets the business seats bought by the customer.
     * @param businessSeatsBoughtIn
     */
    public void setBusinessSeatsBought(int businessSeatsBoughtIn){
        this.businessSeatsBought = businessSeatsBoughtIn;
    }
    /**
     * Sets the first class seats bought by the customer.
     * @param firstSeatsBoughtIn
     */
    public void setFirstSeatsBought(int firstSeatsBoughtIn){
        this.firstSeatsBought = firstSeatsBoughtIn;
    }
    /**
     * Sets the total money the customer has wasted.
     * @param moneyWastedIn
     */
    public void setMoneyWasted(double moneyWastedIn){
        this.totalMoneyWasted = moneyWastedIn;
    }
    /**
     * Sets the total savings of customer.
     * @param savingsIn
     */
    public void setSavings(double savingsIn){
        this.savings=savingsIn;
    }

    //getters
    /**
     * Gets the customer ID.
     * @return
     */
    public int getCustomerID(){
        return this.customerID;
    }
    /**
     * Gets the total money the customer has available.
     * @return
     */
    public double getMoneyAvailable(){
        return Math.round(this.moneyAvailable *100.0)/100.0;
    }
    /**
     * Gets the number of flight purchased by the customer.
     * @return
     */
    public int getFlightsPurchased(){
        return this.flightsPurchased;
    }
    /**
     * Gets the status of the customer membership.
     * @return
     */
    public boolean getHasMembership(){
        return this.hasMembership;
    }
    /**
     * Gets the customers username.
     * @return
     */
    public String getUsername(){
        return this.username;
    }
    /**
     * Gets the customers password.
     * @return
     */
    public String getPassword(){
        return this.password;
    }
    /**
     * Gets the customers role, which is customer!
     * @return
     */
    public String getRole(){
        return this.role;
    }
    /**
     * Gets the hashmap with the tickets the customer has purchased.
     * @return
     */
    public HashMap<Integer,Ticket> getCustomerTickets(){
        return this.customerTickets;
    }
    /**
     * Gets the number of main seats purchased by the customer.
     * @return
     */
    public int getMainSeatsBought(){
        return this.mainSeatsBought;
    }
    /**
     * Gets the number of business seats purchased by the customer.
     * @return
     */
    public int getBusinessSeatsBought(){
        return this.businessSeatsBought;
    }
    /**
     * Gets the number of first class seats purchased by the customer.
     * @return
     */
    public int getFirstSeatsBought(){
        return this.firstSeatsBought;
    }
    /**
     * Gets the total money the customer has wasted.
     * @return
     */
    public double getMoneyWasted(){
        return Math.round(this.totalMoneyWasted *100.0)/100.0;
    }
    /**
     * Gets the total savings of the customer.
     * @return
     */
    public double getSavings(){
        return Math.round(this.savings *100.0)/100.0;
    }

    //adds to customerticket
    /**
     * Adds ticket to the hashmap of tickets belonging to the customer.
     * @param newTicket - ticket being added
     * @param ticketCountIn - used for the ID (confirmation number)
     */
    public void addTicket(Ticket newTicket, int ticketCountIn){
        this.customerTickets.put(ticketCountIn, newTicket);   
    } 
    /**
     * toString for customer
     * @return - string of all the attributes of a customer nicely written
     */
    public String printCustomer(){
        return "\n*************** Customer Information ****************\nCustomer ID: " + this.customerID + 
        "\nFirst Name: "+getFirstName() + "\nLast Name: " + getLastName() + "\nMoney Available: " +Math.round(this.moneyAvailable*100.0)/100.0
        + "\nFlights Purchased: " + this.flightsPurchased + "\nHas Membership: "+this.hasMembership+ 
        "\nUsername: "+this.username + "\nPassword: " + this.password;
    }
    /**
     * prints all the tickets in the customer list of tickets
     */
    public void printTickets(){
        for(Ticket value: customerTickets.values()){
            System.out.println(value.printTicket());
        }
    }
    /**
     * This method is used for the creation of the updated csv file at the termination of the program
     * @param header - array of strings from the first line in the customer file
     * @return - string of attributes in the order of the header
     */
    public String printCustomerLine(String[] header){
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
                temp+=this.customerID+",";
            }else if(header[i].equals("First Name")){
                temp+=getFirstName()+",";
            }else if(header[i].equals("Flights Purchased")){
                temp+=this.flightsPurchased+",";
            }else if(header[i].equals("MinerAirlines Membership")){
                temp+=this.hasMembership+",";
            }
        }
        return temp;
    }

    /**
     * Method used to cancel customer ticket
     * Intakes the ticket number of the transaction the customer wants to cancel and returns seats back as well as customer money
     * @param ticketNum - number of ticket wanted to be cancelled
     * @param flightsList - the list of all flight objects 
     * @param log - file writter used to log actions
     * @throws IOException
     */
    public void cancelTicket(int ticketNum, HashMap<Integer,Flight> flightsList, FileWriter log) throws IOException{
        try{
        //flight where the ticket is located
        int ticketID = customerTickets.get(ticketNum).getFlightID();
        //used to check if ID coresponding to ticket is existant 
        Boolean notId = false;
        int seatsPurchasedInTicket = this.customerTickets.get(ticketNum).getNumSeatsPurchased();
        if(flightsList.containsKey(ticketID)){
            //removing money from customers in flight 
            flightsList.get(ticketID).getCustomersInFlight().get(customerID).set(4, "Total Price: $"+(this.totalMoneyWasted-customerTickets.get(ticketNum).getTotalPrice()));
            //returning to total seats 
            flightsList.get(ticketID).setTotalSeats(flightsList.get(ticketID).getTotalSeats()+seatsPurchasedInTicket);
            //returning to type of seat and removing seat from customers in flight list
            if(customerTickets.get(ticketNum).getTypeSeat().equals("Main")){
                flightsList.get(ticketID).getCustomersInFlight().get(customerID).set(1, "Main Class: "+(this.mainSeatsBought-seatsPurchasedInTicket));
                flightsList.get(ticketID).setMainCabinSeats(flightsList.get(ticketID).getMainCabinSeats()+seatsPurchasedInTicket);
            }else if(customerTickets.get(ticketNum).getTypeSeat().equals("Business")){
                flightsList.get(ticketID).getCustomersInFlight().get(customerID).set(2, "Business Class: "+(this.businessSeatsBought-seatsPurchasedInTicket));
                flightsList.get(ticketID).setBusinessClassSeats(flightsList.get(ticketID).getBusinessClassSeats()+seatsPurchasedInTicket);
            }else if(customerTickets.get(ticketNum).getTypeSeat().equals("First")){
                flightsList.get(ticketID).getCustomersInFlight().get(customerID).set(3, "First Class: "+(this.businessSeatsBought-seatsPurchasedInTicket));
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
            setFlightsPurchased(getFlightsPurchased()-1);
            //Miner Airlines keeps the fee!
            double moneyToReturn = customerTickets.get(ticketNum).getTotalPrice()-flightsList.get(ticketID).getMinerAirlinesFee();
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
            this.customerTickets.remove(ticketNum);
            flightsList.get(ticketID).getTicketsPurchased().remove(ticketNum);     
        }
        //exception for customer canceling ticket that was already cancelled
        }catch(NullPointerException e){
            System.out.println("The flight was already cancelled!");
            System.out.println(e.getMessage());
        }

    }
}
