public class AutoPurchases {
    //attributes
    private String firstName;
    private String lastName;
    private String action;
    private int flightID;
    private String originCode;
    private String destCode;
    private String ticketType;
    private int quantity;

    //constructor
    /**
     * Default constructor for this class
     */
    public AutoPurchases(){}
    /**
     * Main Constructor
     * @param firstNameIn - first name of the customer
     * @param lastNameIn - last name of the customer
     * @param actionIn - what the customer wants to do
     * @param IDin - id of the customer
     * @param originCodeIn - the origin code wanted
     * @param destCodeIn 
     * @param typeIn
     * @param quantityIn - number of seats wanted
     */
    public AutoPurchases(String firstNameIn, String lastNameIn, String actionIn, int IDin, String originCodeIn, String destCodeIn, String typeIn, int quantityIn){
        this.firstName=firstNameIn;
        this.lastName=lastNameIn;
        this.action=actionIn;
        this.flightID=IDin;
        this.originCode=originCodeIn;
        this.destCode=destCodeIn;
        this.ticketType=typeIn;
        this.quantity=quantityIn;
    }
    /**
     * Sets the first name of the customer
     * @param firstIn
     */
    public void setFirstName(String firstIn){
        this.firstName=firstIn;
    }
    /**
     * Sets the last name of the customer
     * @param lastIn
     */
    public void setLastName(String lastIn){
        this.lastName=lastIn;
    }
    /**
     * Sets the action that the customer wants to do
     * @param action
     */
    public void setAction(String action){
        this.action=action;
    }
    /**
     * Sets the ID of the flight that the customer wants to purchase
     * @param ID
     */
    public void setID(int ID){
        this.flightID=ID;
    }
    /**
     * Sets the origin code of the flight wanted
     * @param originIn
     */
    public void setOrigin(String originIn){
        this.originCode=originIn;
    }
    /**
     * Set the destination of the flight wanted
     * @param destIn
     */
    public void setDest(String destIn){
        this.destCode=destIn;
    }
    /**
     * Sets the type of the seat wanted
     * @param type
     */
    public void setType(String type){
        this.ticketType=type;
    }
    /**
     * Sets the amount of seats wanted for the flight
     * @param quantity
     */
    public void setQuantity(int quantity){
        this.quantity=quantity;
    }
    /**
     * Gets the first name of the customer
     * @return
     */
    public String getFirstName(){
        return this.firstName;
    }
    /**
     * Gets the last name of the customer
     * @return
     */
    public String getLastName(){
        return this.lastName;
    }
    /**
     * Gets the action that the customer wants to do
     * @return
     */
    public String getAction(){
        return this.action;
    }
    /**
     * Gets the ID of the customer
     * @return
     */
    public int getID(){
        return this.flightID;
    }
    /**
     * Gets the origin code of the flight
     * @return
     */
    public String getOriginCode(){
        return this.originCode;
    }
    /**
     * Gets the destination code of the flight
     * @return
     */
    public String getDestCode(){
        return this.destCode;
    }
    /**
     * Gets the type of the seat wanted
     * @return
     */
    public String getType(){
        return this.ticketType;
    }
    /**
     * Gets the number of seats/tickets wanted
     * @return
     */
    public int getQuantity(){
        return this.quantity;
    }
}
