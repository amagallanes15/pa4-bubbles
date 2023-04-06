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
 * @version 3- This class has been modified three times
 * This class is used to classify flight objects into flights that don't go outside of the United States
 * This class inherits all attributes and methods form Flight
 */
public class Domestic extends Flight{
    //Attributes
    private boolean inState;

    //Constructors
    /**
     * Default Constructor
     */
    public Domestic(){
    }
    /**
     * This is the main constructor for the class Domestic
     * These are all the attributes needed for a flight, most of them are inherited from parent Flight
     * @param ID
     * @param flightNumber
     * @param originAirport
     * @param originCode
     * @param destinationAirport
     * @param destinationCode
     * @param departureDate
     * @param departureTime
     * @param duration
     * @param distance
     * @param timeZoneDiff
     * @param arrivalDate
     * @param arrivalTime
     * @param flightTypeIn
     * @param surchargeIn
     * @param foodServedIn
     * @param routeCostIn
     * @param minerPointsIn
     * @param totalSeats
     * @param firstClassSeats
     * @param businessClassSeats
     * @param mainCabinSeats
     * @param firstClassPrice
     * @param businessClassPrice
     * @param mainCabinPrice
     * @param oCityIn
     * @param oStateIn
     * @param oCountryIn
     * @param oFeeIn
     * @param oLoungeIn
     * @param dCityIn
     * @param dStateIn
     * @param dCountryIn
     * @param dFeeIn
     * @param loungeIn
     */
    public Domestic(int ID, int flightNumber, String originAirport, String originCode, String destinationAirport, String destinationCode,
    String departureDate, String departureTime, int duration, int distance, int timeZoneDiff, String arrivalDate, String arrivalTime, 
    String flightTypeIn, int surchargeIn, boolean foodServedIn, int routeCostIn, int minerPointsIn,int totalSeats, int firstClassSeats, int businessClassSeats, int mainCabinSeats, 
    int firstClassPrice, int businessClassPrice, int mainCabinPrice, String oCityIn, String oStateIn, String oCountryIn, double oFeeIn, 
    boolean oLoungeIn, String dCityIn, String dStateIn, String dCountryIn, double dFeeIn, boolean loungeIn){
        //inherits attributes form Flight class
        super(ID, flightNumber, originAirport, originCode, destinationAirport, destinationCode, departureDate, departureTime, duration, distance, timeZoneDiff, arrivalDate, arrivalTime, 
         flightTypeIn, surchargeIn, foodServedIn, routeCostIn, minerPointsIn,totalSeats, firstClassSeats, businessClassSeats, mainCabinSeats, firstClassPrice, businessClassPrice, mainCabinPrice
         ,oCityIn, oStateIn, oCountryIn, oFeeIn, oLoungeIn, dCityIn, dStateIn, dCountryIn, dFeeIn, loungeIn);
        this.inState = true;
    }

    //Setters
    /**
     * Sets if the flight is in state.
     * @param stateIn
     */
    public void setState(boolean stateIn){
        this.inState = stateIn;
    }

    //Getters
    /**
     * Gets state status.
     * @return
     */
    public boolean getState(){
        return this.inState;
    }

    
}

