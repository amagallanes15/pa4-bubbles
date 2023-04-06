

import java.util.ArrayList;
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
 * Flight class with all the attributes needed for a flight object
 */
public abstract class Flight {
    //Attributes
    private int ID;
    private int flightNumber;
    //airport info
    private Airport originAirport;
    private Airport destinationAirport;
    private String departureDate;
    private String departureTime;
    private int duration;
    private int distance;
    private int timeZoneDiff;
    private String arrivalDate;
    private String arrivalTime;
    private int totalSeats;
    private int firstClassSeats;
    private int businessClassSeats;
    private int mainCabinSeats;
    private int firstClassPrice;
    private int businessClassPrice;
    private int mainCabinPrice;
    private String flightType;
    private int surcharge;
    private boolean foodServed;
    private int routeCost;
    private int minerPoints;
    private HashMap<Integer, Ticket> ticketsPurchased;
    //getting every customer in flight (name, seats, price)
    private HashMap<Integer, ArrayList<String>> customersInFlight;
    //used for customerInFlight keys
    private String status;
    private int firstClassSeatsSold;
    private int businessClassSeatsSold;
    private int mainClassSeatsSold;
    private int mainClassRevenue;
    private int businessClassRevenue;
    private int firstClassRevenue;
    private double securityFee;
    private double minerAirlinesFee;
    private double memberDiscount;
    private double totalFlightDiscounts;
    private double tax;

    //Constructors
    //default
    public Flight(){
    }
    /**
     * Constructor for the flight objects
     * @param ID
     * @param flightNumber
     * @param originAirport - Airport object (name)
     * @param originCode - Airport object (code)
     * @param destinationAirport - Airport object (name)
     * @param destinationCode - Airport object (code)
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
     * @param oCityIn - Airport object (city)
     * @param oStateIn - Airport object (state)
     * @param oCountryIn - Airport object (country)
     * @param oFeeIn - Airport object (fee)
     * @param oLoungeIn - Airport object (lounge)
     * @param dCityIn - Airport object (city)
     * @param dStateIn - Airport object (state)
     * @param dCountryIn - Airport object (country)
     * @param dFeeIn - Airport object (fee)
     * @param loungeIn - Airport object (lounge)
     */
    public Flight(int ID, int flightNumber, String originAirport, String originCode, String destinationAirport, String destinationCode,
    String departureDate, String departureTime, int duration, int distance, int timeZoneDiff, String arrivalDate, String arrivalTime, 
    String flightTypeIn, int surchargeIn, boolean foodServedIn, int routeCostIn, int minerPointsIn,int totalSeats, int firstClassSeats, int businessClassSeats, 
    int mainCabinSeats, int firstClassPrice, int businessClassPrice, int mainCabinPrice, String oCityIn, String oStateIn, String oCountryIn, double oFeeIn, 
    boolean oLoungeIn, String dCityIn, String dStateIn, String dCountryIn, double dFeeIn, boolean loungeIn){ //35 attributes
        this.ID = ID;
        this.flightNumber = flightNumber;
        this.originAirport = new Airport(originAirport, originCode, oCityIn, oStateIn, oCountryIn, oFeeIn, oLoungeIn);
        this.destinationAirport = new Airport(destinationAirport, destinationCode, dCityIn, dStateIn, dCountryIn, dFeeIn, loungeIn);
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.duration = duration;
        this.distance = distance;
        this.timeZoneDiff = timeZoneDiff;
        this.arrivalDate = arrivalDate;
        this.arrivalTime = arrivalTime;
        this.totalSeats = totalSeats;
        this.firstClassSeats = firstClassSeats;
        this.businessClassSeats = businessClassSeats;
        this.mainCabinSeats = mainCabinSeats;
        this.firstClassPrice = firstClassPrice;
        this.businessClassPrice = businessClassPrice;
        this.mainCabinPrice = mainCabinPrice;
        this.flightType = flightTypeIn;
        this.surcharge = surchargeIn;
        this.foodServed = foodServedIn;
        this.minerPoints = minerPointsIn;
        this.routeCost = routeCostIn;
        //will store the total tickets purchased for that flight
        this.ticketsPurchased = new HashMap<Integer, Ticket> ();
        //will store the information of the customers on the flight
        this.customersInFlight = new HashMap<Integer, ArrayList<String>>();
        //all flights will be OPEN unless cancelled by employee
        this.status = "OPEN";
        //saving original seats to use when computing their total sold 
        this.firstClassSeatsSold = firstClassSeats;
        this.businessClassSeatsSold = businessClassSeats;
        this.mainClassSeatsSold = mainCabinSeats;
        //there is no money made at the instance of the program
        this.firstClassRevenue = 0;
        this.businessClassRevenue = 0;
        this.mainClassRevenue = 0;
        //new flight fees/dicounts
        this.minerAirlinesFee=9.15;
        this.securityFee=5.60;
        this.memberDiscount=1.05;
        this.tax=1.0825;

    }
   
    //Setters
    /**
     * Sets flight ID.
     * @param ID
     */
    public void setID(int ID){
        this.ID= ID;
    }
    /**
     * Sets the flight number.
     * @param FN
     */
    public void setFlightNumber(int FN){
        this.flightNumber = FN;
    }
    /**
     * This method is a setter but there is some logic behind it.
     * The arrival date is dependent to the departure, therefore it is set based on the departure.
     * @param DD - departure date
     */
    public void setDepartureDate(String DD){
        this.departureDate = DD;
        this.departureDate = DD;
        //split date by / and store individual numbers as ints
        String[] onlyNumsDate = this.departureDate.split("/");
        int prevMonth = Integer.parseInt(onlyNumsDate[0]);
        int prevDay = Integer.parseInt(onlyNumsDate[1]);
        int prevYear = Integer.parseInt(onlyNumsDate[2]);
        //the time also needs to be taken into consideration to know if the day will cahnge or not
        String[] prevDepartureTime = this.departureTime.split("[:, ]");
        setDepartureTime(this.departureTime);
        String[] currentArrivalTime = this.arrivalTime.split("[:, ]");
        //array with days that each month has
        int[] daysInMonth = new int[] {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        //new day since it changed from PM to AM based on the flights time
        if((prevDepartureTime[2]).equals("PM") && (currentArrivalTime[2]).equals("AM")){
            prevDay+=1;
            //assuming flight won't take years!
            if(prevDay >= daysInMonth[prevMonth-1]){
                prevMonth+=1;
                prevDay = prevDay - daysInMonth[prevMonth-2];
            }
            //new year
            if(prevMonth > 12){
                prevYear +=1;
                prevMonth-=12;
            }
        }
        //combining individual elements into whole
        this.arrivalDate = prevMonth + "/" + prevDay + "/" + prevYear;
        
    }
    /**
     * This method is a setter but there is some logic behind it.
     * The arrival time is dependent to the departure, therefore it is set based on the departure time.
     * @param DT - departure time
     */
    public void setDepartureTime(String DT){
        this.departureTime = DT;
        this.departureTime = DT;
        //splitting time to hours and mins
        String[] onlyNumsTime = this.departureTime.split("[:, ]");
        //holding amount of days each month has (this year!) needs to be taken into consideration
        int[] daysInMonth = new int[] {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int prevHours = Integer.parseInt(onlyNumsTime[0]);
        int prevMinutes = Integer.parseInt(onlyNumsTime[1]);
        //getting the hours added to get arrival 
        int hoursDuration = this.duration / 60;
        int minutesDuration = this.duration - (hoursDuration * 60);
        int totalHours = prevHours + hoursDuration + this.timeZoneDiff;
        int totalMinutes = prevMinutes + minutesDuration;
        //Splitting month/day
        String[] onlyNumsDate = this.departureDate.split("/");
        int prevMonth = Integer.parseInt(onlyNumsDate[0]);
        int prevDay = Integer.parseInt(onlyNumsDate[1]);
        int prevYear = Integer.parseInt(onlyNumsDate[2]);
        //making minutes over 60 into hours
        while(totalMinutes > 60){
            totalMinutes-=60;
            totalHours+=1;
        }
        //handeling AM/PM statements and hours over 12
        if(totalHours > 11){
            //changes AM->PM
            if((prevHours<12) && (totalHours>=12) ){
                if(onlyNumsTime[2].equals("AM")){
                    onlyNumsTime[2] = "PM";
                }else{
                    //new day
                    onlyNumsTime[2] = "AM";
                    prevDay+=1;
                    //assuming flight won't take years
                    //daysInMonth[prevMonth] makes sure that the amount of days corresponds to the month that it is
                    if(prevDay >= daysInMonth[prevMonth-1]){
                        prevMonth+=1;
                        prevDay-=daysInMonth[prevMonth-1];
                    }
                    //new year
                    if(prevMonth > 12){
                        prevYear +=1;
                        prevMonth-=12;
                    }
                    //setting arrival date based on updating departure time
                    this.arrivalDate = prevMonth +"/" +prevDay +"/" + prevYear;
                }
            }
            //changes 12 to 1 
            if(totalHours > 12){
                totalHours -= 12;
            }
        }
    }
    /**
     * Sets duration of the flight.
     * @param durationIn
     */
    public void setDuration(int durationIn){
        this.duration = durationIn;
    }
    /**
     * Sets distance of the flight.
     * @param distanceIn
     */
    public void setDistance(int distanceIn){
        this.distance = distanceIn;
    }
    /**
     * Sets time zone difference of flight compared to destination.
     * @param timeZoneIn
     */
    public void setTimeZoneDifference(int timeZoneIn){
        this.timeZoneDiff = timeZoneIn;
    }
    /**
     * Sets the first class price.
     * @param FCP - first class price
     */
    public void setFirstClassPrice(int FCP){
        this.firstClassPrice = FCP;
    }
    /**
     * Sets the business class price.
     * @param BCP - business class price
     */
    public void setBusinessClassPrice(int BCP){
        this.businessClassPrice = BCP;
    }
    /**
     * Sets the main cabin price.
     * @param MCP - main cabin price
     */
    public void setMainCabinPrice(int MCP){
        this.mainCabinPrice = MCP;
    }
    /**
     * Sets the first class seats.
     * @param FCS - first class seats
     */
    public void setFirstClassSeats(int FCS){
        this.firstClassSeats = FCS;
    }
    /**
     * Sets the business class seats.
     * @param BCS - business class seats
     */
    public void setBusinessClassSeats(int BCS){
        this.businessClassSeats = BCS;
    }
    /**
     * Sets the main cabin seats.
     * @param MCS - main cabin seats
     */
    public void setMainCabinSeats(int MCS){
        this.mainCabinSeats = MCS;
    }
    /**
     * Sets total amount of seats in flight.
     * @param TS - total seats
     */
    public void setTotalSeats(int TS){
        this.totalSeats = TS;
    }
    /**
     * Sets type of flight (domestic/international)
     * @param fligthTypeIn
     */
    public void setFlightType(String fligthTypeIn){
        this.flightType = fligthTypeIn;
    }
    /**
     * Sets total surcharge if any, or zero if none.
     * @param surchargeIn
     */
    public void setSurcharge(int surchargeIn){
        this.surcharge = surchargeIn;
    }
    /**
     * Sets the state for food served in flight.
     * @param foodservedIn
     */
    public void setFoodServed(boolean foodservedIn){
        this.foodServed = foodservedIn;
    }
    /**
     * Sets the total cost of the route.
     * @param routeCostIn
     */
    public void setRouteCost(int routeCostIn){
        this.routeCost = routeCostIn;
    }
    /**
     * Sets the number of miner points.
     * @param minerPointsIn
     */
    public void setMinerPoints(int minerPointsIn){
        this.minerPoints = minerPointsIn;
    }
    /**
     * Sets the hashmap for the tickets purchsed of the flight.
     * @param ticketsPurchasedIn
     */
    public void setTicketsPurchased(HashMap<Integer,Ticket> ticketsPurchasedIn){
        this.ticketsPurchased = ticketsPurchasedIn;
    }
    /**
     * Method that adds ticket object to the tickets purchased for the flight hashMap
     * @param newTicket - ticket object
     * @param randomNum - used for the key of the ticket
     */
    public void addTicket(Ticket newTicket, int randomNum){
        this.ticketsPurchased.put(randomNum,newTicket);
        
    }
    /**
     * Sets the hashmap of customer information for the flight.
     * @param customersInFlightIn
     */
    public void setCustomersInFlight(HashMap<Integer, ArrayList<String>> customersInFlightIn){
        this.customersInFlight = customersInFlightIn;
    }
    /**
     * Adds customer information to the customersInFlight list.
     * @param customerInfo - customer information being added
     * @param randomNum - used for key
     */
    public void addCustomer(ArrayList<String> customerInfo, int randomNum){
        this.customersInFlight.put(randomNum,customerInfo);
    }
    /**
     * Sets the status of the flight, used for cancelling functionality.
     * @param statusIn
     */
    public void setFlightSatus(String statusIn){
        this.status= statusIn;
    }
    /**
     * Sets the amount of first class seats sold from flight.
     * @param numSeats
     */
    public void setFirstClassSeatsSold(int numSeats){
        this.firstClassSeatsSold = numSeats;
    }
    /**
     * Sets the amount of business class seats sold from flight.
     * @param numSeats
     */
    public void setBusinessClassSeatsSold(int numSeats){
        this.businessClassSeatsSold=numSeats;
    }
    /**
     * Sets the amount of main class seats sold from flight.
     * @param numSeats
     */
    public void setMainClassSeatsSold(int numSeats){
        this.mainClassSeatsSold = numSeats;
    }
    /**
     * Sets the revenue for first class.
     * @param revenue
     */
    public void setFirstClassRevenue(int revenue){
        this.firstClassRevenue = revenue;
    }
    /**
     * Sets the revenue for business class.
     * @param revenue
     */
    public void setBusinessClassRevenue(int revenue){
        this.businessClassRevenue=revenue;
    }
    /**
     * Sets the revenue for main class.
     * @param revenue
     */
    public void setMainClassRevenue(int revenue){
        this.mainClassRevenue=revenue;
    }
    /**
     * Sets the name of origin airport.
     * @param name
     */
    public void setOriginAirportName(String name){
        this.originAirport.setAirportName(name);
    }
    /**
     * Sets the code of origin airport.
     * @param code
     */
    public void setOriginCode(String code){
        this.originAirport.setAirportCode(code);
    }
    /**
     * Sets the name of destination airport.
     * @param name
     */
    public void setDestinationName(String name){
        this.destinationAirport.setAirportName(name);
    }
    /**
     * Sets the code of destination airport.
     * @param code
     */
    public void setDestinationCode(String code){
        this.destinationAirport.setAirportCode(code);
    }
    /**
     * Sets miner airlines fee amount.
     * @param fee
     */
    public void setMinerAirlinesFee(double fee){
        this.minerAirlinesFee=fee;
    }
    /**
     * Sets security fee amount.
     * @param fee
     */
    public void setSecurityFee(double fee){
        this.securityFee=fee;
    }
    /**
     * Sets the member discount amount.
     * @param discount
     */
    public void setMemeberDiscount(double discount){
        this.memberDiscount=discount;
    }
    /**
     * Sets the total flight discounted amount.
     * @param totalDiscount
     */
    public void setTotalFlightDiscount(double totalDiscount){
        this.totalFlightDiscounts=totalDiscount;
    }
    /**
     * Sets the tax amount.
     * @param tax
     */
    public void setTax(double tax){
        this.tax=tax;
    }

    //Getters
    /**
     * Gets the ID.
     * @return - int
     */
    public int getID(){
        return ID;
    }
    /**
     * Gets the flight number.
     * @return - int
     */
    public int getFlightNumber(){
        return flightNumber;
    }
    /**
     * Gets the total number of seats.
     * @return - int
     */
    public int getTotalSeats(){
        return totalSeats;
    }
    /**
     * Gets the departure date of flight.
     * @return - string
     */
    public String getDepartureDate(){
        return departureDate;
    }
    /**
     * Gets departure time of flight.
     * @return - string
     */
    public String getDepartureTime(){
        return departureTime;
    }
    /**
     * Gets arrival date of flight.
     * @return - string
     */
    public String getArrivalDate(){
        return arrivalDate;
    }
    /**
     * Gets arrival time of flight.
     * @return - string
     */
    public String getArrivalTime(){
        return arrivalTime;
    }
    /**
     * Gets the duration of the flight.
     * @return - int
     */
    public int getDuration(){
        return duration;
    }
    /**
     * Gets the ditance of the flight.
     * @return
     */
    public int getDistance(){
        return distance;
    }
    /**
     * Gets the time zone difference of the flight origin compared to destination
     * @return - int
     */
    public int getTimeZoneDiff(){
        return timeZoneDiff;
    }
    /**
     * Gets the price of the first class.
     * @return
     */
    public int getFirstClassPrice(){
        return firstClassPrice;
    }
    /**
     * Gets the price of the business class.
     * @return - int 
     */
    public int getBusinessClassPrice(){
        return businessClassPrice;
    }
    /**
     * Gets the price of the mian class.
     * @return - int
     */
    public int getMainCabinPrice(){
        return mainCabinPrice;
    }
    /**
     * Gets the seats of the first class.
     * @return - int
     */
    public int getFirstClassSeats(){
        return firstClassSeats;
    }
    /**
     * Gets the seats of the business class.
     * @return - int
     */
    public int getBusinessClassSeats(){
        return businessClassSeats;
    }
    /**
     * Gets the seats of the main class.
     * @return - int
     */
    public int getMainCabinSeats(){
        return mainCabinSeats;
    }
    /**
     * Gets the type of the flight (Domestic/International).
     * @return - string
     */
    public String getFlightType(){
        return this.flightType;
    }
    /**
     * Gets the surcharge of the flight.
     * @return - int
     */
    public int getSurcharge(){
        return this.surcharge;
    }
    /**
     * Gets the status of food served.
     * @return - boolean
     */
    public boolean getFoodServed(){
        return this.foodServed;
    }
    /**
     * Gets the total cost of the route for the flight.
     * @return - int
     */
    public int getRouteCost(){
        return this.routeCost;
    }
    /**
     * Gets the total miner points corresponding to the flight.
     * @return - int
     */
    public int getMinerPoints(){
        return this.minerPoints;
    }
    /**
     * Gets the hashmap containing the tickets purchased of the flight.
     * @return - hashmap of key integer and value Ticket
     */
    public HashMap<Integer,Ticket> getTicketsPurchased(){
        return this.ticketsPurchased;
    }
    /**
     * Gets the hashmap of customer in flight information.
     * @return - hashmap of key integer and value arrayList of information
     */
    public HashMap<Integer, ArrayList<String>> getCustomersInFlight(){
        return this.customersInFlight;
    }
    /**
     * Gets the fee of origin airport.
     * @return - double
     */
    public double getOriginAirportFee(){
        return this.originAirport.getFee();
    }
    /**
     * Gets the fee of destination airport.
     * @return - double
     */
    public double getDestinationAirportFee(){
        return this.destinationAirport.getFee();
    }
    /**
     * Method that prints customers in flight hashMap elements nicely
     * @return - string with the customers information listed
     */
    public String printCustomersInFlight(){
        String tempString = "";
        for(ArrayList<String> value: getCustomersInFlight().values()){
            for(int i=0; i<value.size(); i++){
                tempString += String.valueOf(value.get(i))+"\n";
            }
            tempString += "------------------------------\n";
        }
        return tempString;
    }
    /**
     * Gets the status of flight, used for cancelling function.
     * @return - int
     */
    public String getStatus(){
        return this.status;
    }
    /**
     * Gets the revenue of first class.
     * @return - int 
     */
    public int getFirstClassRevenue(){
        return (this.firstClassPrice+this.surcharge) * getFirstClassSeatsSold();
    }
    /**
     * Gets the revenue of the business class.
     * @return - int
     */
    public int getBusinessClassRevenue(){
        return (this.businessClassPrice+this.surcharge) * getBusinessClassSeatsSold();
    }
    /**
     * Gets the revenue of the main class.
     * @return - int
     */
    public int getMainClassRevenue(){
        return (this.mainCabinPrice+this.surcharge) * getMainCabinSeatsSold();
    }
    /**
     * Gets the amount of first class seats sold.
     * @return - int
     */
    public int getFirstClassSeatsSold(){
        return this.firstClassSeatsSold-this.firstClassSeats;
    }
    /**
     * Gets the amount of business class seats sold.
     * @return - int 
     */
    public int getBusinessClassSeatsSold(){
        return this.businessClassSeatsSold-this.businessClassSeats;
    }
    /**
     * Gets the main cabin seats sold.
     * @return - int
     */
    public int getMainCabinSeatsSold(){
        return this.mainClassSeatsSold-this.mainCabinSeats;
    }
    /**
     * Gets the origin airport name.
     * @return - string
     */
    public String getOriginAirportName(){
        return this.originAirport.getAirportName();
    }
    /**
     * Gets the destination airport name.
     * @return - string
     */
    public String getDestinationAirportName(){
        return this.destinationAirport.getAirportName();
    }
    /**
     * Gets the origin airport code.
     * @return - String
     */
    public String getOriginAirportCode(){
        return this.originAirport.getAirportCode();
    }
    /**
     * Gets the destination airport code.
     * @return - string
     */
    public String getDestinationAirportCode(){
        return this.destinationAirport.getAirportCode();
    }
    /**
     * Gets the miner airlines fee.
     * @return - double
     */
    public double getMinerAirlinesFee(){
        return this.minerAirlinesFee;
    }
    /**
     * Gets the security fee.
     * @return - double
     */
    public double getSecurityFee(){
        return this.securityFee;
    }
    /**
     * Gets the member discount amount.
     * @return - double
     */
    public double getMemberDiscount(){
        return this.memberDiscount;
    }
    /**
     * Gets the total amount discounted.
     * @return - double
     */
    public double getTotalFlightDiscount(){
        return this.totalFlightDiscounts;
    }
    /**
     * Gets the tax applied to the transaction.
     * @return - double
     */
    public double getTax(){
        return this.tax;
    }
    public Airport getOriginAirport(){
        return this.originAirport;
    }
    public Airport getDestinationAirport(){
        return this.destinationAirport;
    }

    
    /**
     * Method to print flight information (attributes) in a nicely manner 
     * @return - string with sections of attributes corresponding to the flight
     */
    public String printFlight(){
        return "\n*************** Flight Information ****************\nFlight Number: "+this.flightNumber + "\nOrigin Airport: " + this.originAirport.getAirportName() + "\nOrigin Code: " + this.originAirport.getAirportCode() + "\nDestination Airport: "
        + this.destinationAirport.getAirportName() + "\nDestination Code: " +  this.destinationAirport.getAirportCode()+ "\nDeparture Date: " + this.departureDate +
        "\nDeparture Time: " + this.departureTime + "\nArrival Date: " +this.arrivalDate + "\nArrival Time: " + this.arrivalTime
        + "\nDuration: " + this.duration + "\nDistance: " + this.distance +"\nTime Zone Difference: " + this.timeZoneDiff +
        "\nFlight Type: "+this.flightType+ "\nSurcharge: "+this.surcharge+"\nFood Served: "+this.foodServed+"\nRoute Cost: "+this.routeCost+"\nMiner Points: "+
        this.minerPoints+
        "\nFirst Class Price: " + this.firstClassPrice + "\nBusiness Class Price: " + this.businessClassPrice + "\nMain Cabin Price: "
        + this.mainCabinPrice + "\nFirst Class Seats: " + this.firstClassSeats + "\nBusiness Class Seats: " +
        this.businessClassSeats + "\nMain Cabin Seats: " + this. mainCabinSeats + "\nTotal Seats: " + this.totalSeats+
        "\nOrigin Airport City: "+this.originAirport.getCity()+"\nOrigin Airport State: "+this.originAirport.getState()+"\nOrigin Airport Country: "+
        this.originAirport.getCountry()+ "\nOrigin Airport Fee: "+this.originAirport.getFee()+"\nOrigin Airport Lounge: "+this.originAirport.getLounge()
        +"\nDestination Airport City: "+this.destinationAirport.getCity()+"\nDestination Airport State: "+this.destinationAirport.getState()+"\nDestination Airport Country: "+
        this.destinationAirport.getCountry()+ "\nDestination Airport Fee: "+this.destinationAirport.getFee()+"\nDestination Airport Lounge: "+this.destinationAirport.getLounge();
    }
    /**
     * Method that prints flight information in just one line in order to condense the space on the terminal when showcasing all flights to customer.
     * @return - string in one line with all flight attributes
     */
    public String printFlightLine(){
        return "Flight ID: "+this.ID+"\nFlight Number: "+this.flightNumber+", Origin Airport: " + this.originAirport.getAirportName() + ", Origin Code: " + this.originAirport.getAirportCode() + 
        ", Origin Airport City: "+this.originAirport.getCity()+", Origin Airport State: "+this.originAirport.getState()+", Origin Airport Country: "+this.originAirport.getCountry()
        +", Origin Airport Fee: "+this.originAirport.getFee()+", Origin Airport Lounge: "+this.originAirport.getLounge()+
        ", Destination Airport: "+ this.destinationAirport.getAirportName() + ", Destination Airport Code: "+this.destinationAirport.getAirportCode()+
        ", Destination Airport City: "+this.destinationAirport.getCity()+", Destination Airport State: "+this.destinationAirport.getState()+", Origin Airport Country: "+this.originAirport.getCountry()
        +", Destination Airport Fee: "+this.destinationAirport.getFee()+", Destination Airport Lounge: "+this.destinationAirport.getLounge()+
        ", Departure Date: " + this.departureDate +
        ", Departure Time: " + this.departureTime + ", Arrival Date: " +this.arrivalDate + ", Arrival Time: " + this.arrivalTime
        + ", Duration: " + this.duration + ", Distance: " + this.distance +", Time Zone Difference: " + this.timeZoneDiff +
        ", Flight Type: "+this.flightType+ ", Surcharge: "+this.surcharge+", Food Served: "+this.foodServed+", Route Cost: "+this.routeCost+", Miner Points: "+
        this.minerPoints+
        ", First Class Price: " + this.firstClassPrice + ", Business Class Price: " + this.businessClassPrice + ", Main Cabin Price: "
        + this.mainCabinPrice + ", First Class Seats: " + this.firstClassSeats + ", Business Class Seats: " +
        this.businessClassSeats + ", Main Cabin Seats: " + this. mainCabinSeats + ", Total Seats: " + this.totalSeats+"\n";
    }
    /**
     * Method is used for the updated flight sheet at the termination of the program
     * It orders attrbutes into original header order before returning the string
     * @param header - array of strings corresponding to the first line of the flight schedule csv file
     * @return - string line of attributes with commas between each
     */
    public String updatedSheetInfo(String[] header){
        String temp = "";
        for(int i=0; i<header.length; i++){
            if(header[i].equals("ID")){
                temp+=this.ID+",";
            }else if(header[i].equals("Flight Number")){
                temp+=this.flightNumber+",";
            }else if(header[i].equals("Origin Airport")){
                temp+=this.originAirport.getAirportName()+",";
            }else if(header[i].equals("Origin Code")){
                temp+=this.originAirport.getAirportCode()+",";
            }else if(header[i].equals("Destination Airport")){
                temp+=this.destinationAirport.getAirportName()+",";
            }else if(header[i].equals("Destination Code")){
                temp+=this.destinationAirport.getAirportCode()+",";
            }else if(header[i].equals("Departing Date")){
                temp+=this.departureDate+",";
            }else if(header[i].equals("Departing Time")){
                temp+=this.departureTime+",";
            }else if(header[i].equals("Duration")){
                temp+=this.duration+",";
            }else if(header[i].equals("Distance")){
                temp+=this.distance+",";
            }else if(header[i].equals("Time Zone Difference")){
                temp+=this.timeZoneDiff+",";
            }else if(header[i].equals("Arrival Date")){
                temp+=this.arrivalDate+",";
            }else if(header[i].equals("Arrival Time")){
                temp+=this.arrivalTime+",";
            }else if(header[i].equals("Type")){
                temp+=this.flightType+",";
            }else if(header[i].equals("Surcharge")){
                temp+=this.surcharge+",";
            }else if(header[i].equals("Food Served")){
                temp+=this.foodServed+",";
            }else if(header[i].equals("Route Cost")){
                temp+=this.routeCost+",";
            }else if(header[i].equals("Miner Points")){
                temp+=this.minerPoints+",";
            }else if(header[i].equals("Total Seats")){
                temp+=this.totalSeats+",";
            }else if(header[i].equals("First Class Seats")){
                temp+=this.firstClassSeats+",";
            }else if(header[i].equals("Business Class Seats")){
                temp+=this.businessClassSeats+",";
            }else if(header[i].equals("Main Cabin Seats")){
                temp+=this.mainCabinSeats+",";
            }else if(header[i].equals("First Class Price")){
                temp+=this.firstClassPrice+",";
            }else if(header[i].equals("Business Class Price")){
                temp+=this.businessClassPrice+",";
            }else if(header[i].equals("Main Cabin Price")){
                temp+=this.mainCabinPrice+",";
            }else if(header[i].equals("Origin Airport City")){
                temp+=this.originAirport.getCity()+",";
            }else if(header[i].equals("Origin Airport State")){
                temp+=this.originAirport.getState()+",";
            }else if(header[i].equals("Origin Airport Country")){
                temp+=this.originAirport.getCountry()+",";
            }else if(header[i].equals("Origin Airport Fee")){
                temp+=this.originAirport.getFee()+",";
            }else if(header[i].equals("Origin Airport Lounge")){
                temp+=this.originAirport.getLounge()+",";
            }else if(header[i].equals("Destination Airport City")){
                temp+=this.destinationAirport.getCity()+",";
            }else if(header[i].equals("Destination Airport State")){
                temp+=this.destinationAirport.getState()+",";
            }else if(header[i].equals("Destination Airport Country")){
                temp+=this.destinationAirport.getCountry()+",";
            }else if(header[i].equals("Destination Airport Fee")){
                temp+=this.destinationAirport.getFee()+",";
            }else if(header[i].equals("Destination Airport Lounge")){
                temp+=this.destinationAirport.getLounge()+",";
            }
        }
        //extra columns:
        //First Class Seats Sold, Business Class Seats Sold, Main Class Seats Sold, Total First Class Revenue, Total Business Main Class Revenue, Total Main Class Revenue
        temp+=getFirstClassSeatsSold()+","+getBusinessClassSeatsSold()+","+getMainCabinSeatsSold()+","+getFirstClassRevenue()+","+getBusinessClassRevenue()+","+getMainClassRevenue();
        return temp;
    }
}


