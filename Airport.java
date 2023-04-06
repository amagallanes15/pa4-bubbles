
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
 * @version 2- New Attributes were added
 * This class holds airport qualities
 */
public class Airport {
    //attributes
    private String name;
    private String code;
    private String city;
    private String state;
    private String country;
    private double fee;
    private boolean lounge;
    private double moneyEarned;

    /**
     * Main Constructor for Airport Class
     * @param nameIn - the name of the airport
     * @param codeIn - the code corresponding to the airport
     * @param cityIn - the city in which the airport is located
     * @param stateIn - the state in which the airport is located
     * @param countryIn - the country in which the airport is located
     * @param feeIn - corresponding fee for the airport
     * @param loungeIn - boolean for determining if the airport has lounge
     */
    //constructor
    public Airport(String nameIn, String codeIn, String cityIn, String stateIn, String countryIn, double feeIn, boolean loungeIn){
        this.name=nameIn;
        this.code=codeIn;
        this.city=cityIn;
        this.state=stateIn;
        this.country=countryIn;
        this.fee=feeIn;
        this.lounge=loungeIn;
        this.moneyEarned=0.0;
    }
    
    //setter
    /**
     * Sets the name of the origin airport.
     * @param nameIn
     */
    public void setAirportName(String nameIn){
        this.name = nameIn;
    }
    /**
     * Sets the code of the origin airport.
     * @param codeIn
     */
    public void setAirportCode(String codeIn){
        this.code=codeIn;
    }
    /**
     * Sets the city where the origin airport is at.
     * @param cityIn
     */
    public void setCity(String cityIn){
        this.city=cityIn;
    }
    /**
     * Sets the state where the origin airport is at.
     * @param stateIn
     */
    public void setState(String stateIn){
        this.state=stateIn;
    }
    /**
     * Sets the country where the origin airport is at.
     * @param countryIn
     */
    public void setCountry(String countryIn){
        this.country=countryIn;
    }
    /**
     * Sets the fee of the origin airport.
     * @param feeIn
     */
    public void setFee(double feeIn){
        this.fee=feeIn;
    }
    /**
     * Sets the lounge status of the origin airport
     * @param loungeIn
     */
    public void setLounge(boolean loungeIn){
        this.lounge=loungeIn;
    }
    /**
     * Sets the money the airport earned
     * @param money
     */
    public void setMoneyEarned(double money){
        this.moneyEarned=money;
    }

    //getter
    /**
     * Gets the name of the origin airport.
     * @return
     */
    public String getAirportName(){
        return this.name;
    }
    /**
     * Gets the code of the origin airport.
     * @return
     */
    public String getAirportCode(){
        return this.code;
    }
    /**
     * Gets the city where the origin airport is loccated.
     * @return
     */
    public String getCity(){
        return this.city;
    }
    /**
     * Gets the state where the origin airport is loccated.
     * @return
     */
    public String getState(){
        return this.state;
    }
    /**
     * Gets the country where the origin airport is loccated.
     * @return
     */
    public String getCountry(){
        return this.country;
    }
    /**
     * Gets the fee of the origin airport.
     * @return
     */
    public double getFee(){
        return this.fee;
    }
    /**
     * Gets the status of the lounge for the origin code.
     * @return
     */
    public boolean getLounge(){
        return this.lounge;
    }
    /**
     * Gets the moey the airport earned
     * @return
     */
    public double getMoneyEarned(){
        return this.moneyEarned;
    }

}

