

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
 * @version 3- this class has been modified since PA1
 */
public class Ticket {
    //attributes
    private int flightID;
    private int quantityTickets;
    private double totalPrice;
    private int confirmationNumber;
    private int numberSeatsPurchased;
    private String typeSeatPurchased;
    private int customerID;
    private int ticketNumber;
    private String status;

    //contructor
    /**
     * Default Constructor
     */
    public Ticket(){
    }
    /**
     * constructor for creating ticket objects
     * @param flightIDIn - flight ID
     * @param quantityTicketsIn - how many seats were purchased
     * @param totalPriceIn - total customer paid
     * @param confirmationNumIn - random
     * @param numSeatsIn - how many seats were purchased
     * @param typeSeatIn - seat classification (main/business/first)
     * @param customerIDin - customer ID
     */
    public Ticket(int flightIDIn, int quantityTicketsIn, double totalPriceIn, int confirmationNumIn, int numSeatsIn, String typeSeatIn, int customerIDin){
        this.flightID = flightIDIn;
        this.quantityTickets = quantityTicketsIn;
        this.totalPrice = totalPriceIn;
        this.confirmationNumber = confirmationNumIn;
        this.numberSeatsPurchased = numSeatsIn;
        this.typeSeatPurchased = typeSeatIn;
        this.customerID = customerIDin;
        //status of the ticket will always be paid unless it was cancelled by the employee
        this.status = "PAID";
    }

    //setters
    /**
     * Sets the flight ID for which the ticket belongs.
     * @param flightIDIn
     */
    public void setFlightID(int flightIDIn){
        this.flightID = flightIDIn;
    }
    /**
     * Sets the number of seats bought for the ticket belongs.
     * @param quantityTicketsIn
     */
    public void setQuantityTickets(int quantityTicketsIn){
        this.quantityTickets = quantityTicketsIn;
    }
    /**
     * Sets the total transaction paid for the ticket.
     * @param totalPriceIn
     */
    public void setTotalPrice(double totalPriceIn){
        this.totalPrice = totalPriceIn;
    }
    /**
     * Sets the confirmation number of the ticket.
     * @param confirmationNumIn
     */
    public void setConfirmationNum(int confirmationNumIn){
        this.confirmationNumber = confirmationNumIn;
    }
    /**
     * Sets the number of seats purchased for that ticket.
     * @param numberSeatsPurchasedIn
     */
    public void setNumSeatsPurchased(int numberSeatsPurchasedIn){
        this.numberSeatsPurchased = numberSeatsPurchasedIn;
    }
    /**
     * Sets the type of seats belonging to the ticket transaction (main,business,first)
     * @param typeSeatIn
     */
    public void setTypeSeat(String typeSeatIn){
        this.typeSeatPurchased = typeSeatIn;
    }
    /**
     * Sets the customer id belonging to the ticket, therefore determines who the ticket belongs to.
     * @param customerIDin
     */
    public void setCustomerID(int customerIDin){
        this.customerID = customerIDin;
    }
    /**
     * Sets the number of the ticket.
     * @param numIn
     */
    public void setTicketNumber(int numIn){
        this.ticketNumber = numIn;
    }
    /**
     * Sets the status of the ticket (Cancelled or not)
     * @param statusIn
     */
    public void setStatus(String statusIn){
        this.status = statusIn;
    }
    //getters
    /**
     * Gets the flight ID.
     * @return - int
     */
    public int getFlightID(){
        return this.flightID;
    }
    /**
     * Gets the quantity of seats.
     * @return - int
     */
    public int getQuantityTickets(){
        return this.quantityTickets;
    }
    /**
     * Gets the total price of the transaction for the ticket.
     * @return - double
     */
    public double getTotalPrice(){
        return this.totalPrice;
    }
    /**
     * Gets the confirmation number of the ticket.
     * @return - int
     */
    public int getConfirmationNum(){
        return this.confirmationNumber;
    }
    /**
     * Gets the number of seats purchased for the ticket.
     * @return - int 
     */
    public int getNumSeatsPurchased(){
        return this.numberSeatsPurchased;
    }
    /**
     * Gets the type of seat for the ticket (main,business,first)
     * @return - string
     */
    public String getTypeSeat(){
        return this.typeSeatPurchased;
    }
    /**
     * Gets the customer ID.
     * @return - int
     */
    public int getCustomerID(){
        return this.customerID;
    }
    /**
     * Gets the ticket number.
     * @return - int
     */
    public int getTicketNum(){
        return this.ticketNumber;
    }
    /**
     * Gets the status of the ticket (cancelled or not)
     * @return - string
     */
    public String getSatus(){
        return this.status;
    }
    /**
     * Method to print ticket info in nicely format
     * @return - string/"image" of ticket
     */
    public String printTicket(){
        //can we assume confirmatin number
        return "/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\\n\t  TICKET\nSTATUS: "+this.status+"\nQUANTITY: "+this.quantityTickets+
        "\nTOTAL PRICE: $"+this.totalPrice+"\nCONFIRMATION NUMBER: "+this.confirmationNumber+"\nNUMBER OF SEATS: "
        +this.numberSeatsPurchased+"\n/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\";
    }
    /**
     * Method to print flight information of ticket object
     * @param ticketID - ticket wanted to print information of
     * @param flightList - hashMap of flight attributes
     * @return - string of flight information or null if flight doesn't exists
     */
    public String printFlight(int ticketID, HashMap<Integer,Flight> flightList){
        if(flightList.containsKey(ticketID)){
            return flightList.get(flightID).printFlightLine();
        }
        return null;
    }
}
