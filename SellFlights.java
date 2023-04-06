import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
/**
 * @version - 1) this is the first version of the SellFlights class
 */
public class SellFlights {
    public SellFlights(){}
    /**
     * Method to handle customer flight purchases
     * @param Log - file writter to log actions 
     * @param flightList - hashMap of flight objects
     * @param input - scanner to continue user interface
     * @param customerIndexInList - ID of customer
     * @param customerList - hashMap of customer attributes
     * @param moreTransactions - flag for continuesly system
     * @throws IOException - handled in method signature since other exceptions overwrite it
     */
    public void sellFlightTickets(FileWriter Log, HashMap<Integer, Flight> flightList, Scanner input, int customerIndexInList, HashMap<Integer, Customer> customerList, boolean moreTransactions, boolean knowsIDs) throws IOException{
        try{
            //showcases the domestic flights
            if(knowsIDs==false){
                System.out.println("These are some of the flights available:");
                for(Flight value: flightList.values()){
                    System.out.println("ID: "+(value.getID())+", "+(value.printFlightLine()));
                    System.out.println("----------------------------------------------------------------------------");
                }
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
                    //ustomer discount
                    double discount=0;
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
                    double subtotalBeforeTax= totalPrice+flightList.get(flightID).getMinerAirlinesFee()+securityFee+airportFees-discount;
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
                            //setting money earned
                            flightList.get(flightID).getOriginAirport().setMoneyEarned(flightList.get(flightID).getOriginAirport().getMoneyEarned()+flightList.get(flightID).getOriginAirport().getFee());
                            flightList.get(flightID).getDestinationAirport().setMoneyEarned(flightList.get(flightID).getDestinationAirport().getMoneyEarned()+flightList.get(flightID).getDestinationAirportFee());
                            //setting discount to customer savings/if they are not memebers discount is zero
                            customerList.get(customerIndexInList).setSavings(customerList.get(customerIndexInList).getSavings()+discount);
                            //adding any discounts to the flight total discounted amount
                            flightList.get(flightID).setTotalFlightDiscount(flightList.get(flightID).getTotalFlightDiscount()+discount);
                            //setting the seats bought
                            customerList.get(customerIndexInList).setFlightsPurchased(customerList.get(customerIndexInList).getFlightsPurchased()+1);
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
                            Ticket customerTicket = new Ticket(flightID, Integer.parseInt(numTickets), total, randomNum, Integer.parseInt(numTickets), typeSeat, customerList.get(customerIndexInList).getCustomerID());
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
     * Method that handles autoPurchases only for customer roles
     * @param firstNameIn - customer first name
     * @param lastNameIn - customer last name
     * @param actionIn - the action wanted by the customer
     * @param IDin - flight ID wanted
     * @param originCodeIn
     * @param destCodeIn
     * @param typeIn - type of seat wanted
     * @param quantityIn - the quantity of ticket seats wanted
     * @param customerList - hashMap of customers only
     * @param flightList - hashMap of flight objects
     * @param Log - file writer
     * @param employeeList - hashmap of employee objects only
     */
    public void autoPurchase(String firstNameIn, String lastNameIn, String actionIn, int IDin, String originCodeIn, String destCodeIn, String typeIn, int quantityIn, HashMap<Integer, Customer> customerList, HashMap<Integer, Flight> flightList, FileWriter Log, HashMap<Integer, Employee> employeeList){
        if(actionIn.equals("Buy")){
            //looking for customer ID
            //Customer client = null;
            Customer client = null;
            for(Customer value: customerList.values()){
                if(value.getFirstName().equals(firstNameIn)&value.getLastName().equals(lastNameIn)){
                    client =value;
                }
            }
            boolean stop = false;
            if(client==null){
                stop = true;
                autoPurchaseEmployee(firstNameIn, lastNameIn, actionIn, IDin, originCodeIn, destCodeIn, typeIn, quantityIn, employeeList, flightList, Log);
            }

            if(stop==false){
                //checking if there are seats available
                //checking if seats are sold out
                boolean seatsAvailable = true;
                //for transaction
                //ustomer discount
                double discount=0.0;
                double employeeDiscount=0.0;
                //seeting the type of seat wanted to use in ticket
                String typeSeat = null;
                //for later use in transaction
                double totalPrice = 0.0;

                if(typeIn.equals("Main Cabin")){
                    if(flightList.get(IDin).getMainCabinSeats()<quantityIn){
                        seatsAvailable=false;
                    }
                    typeSeat = "Main";
                    //handeling customer discount
                    if(client.getHasMembership()==true){
                        discount = ((flightList.get(IDin).getMemberDiscount()*flightList.get(IDin).getMainCabinPrice())-flightList.get(IDin).getMainCabinPrice())*quantityIn;
                        discount = Math.round(discount * 100.0)/100.0;
                    }
                    if(client.getRole().equals("Employee")){
                        employeeDiscount=((flightList.get(IDin).getMainCabinPrice()/4.0)*3.0)*quantityIn;
                    }
                    totalPrice = (quantityIn * flightList.get(IDin).getMainCabinPrice()) + (flightList.get(IDin).getSurcharge() *quantityIn);
                }
                if(typeIn.equals("Business Class")){
                    if(flightList.get(IDin).getBusinessClassSeats()<quantityIn){
                        seatsAvailable = false;
                    }
                    typeSeat = "Business";
                    //handeling customer discount
                    if(client.getHasMembership()==true){
                        discount = ((flightList.get(IDin).getMemberDiscount()*flightList.get(IDin).getBusinessClassPrice())-flightList.get(IDin).getBusinessClassPrice())*quantityIn;
                        discount = Math.round(discount * 100.0)/100.0;
                    }
                    if(client.getRole().equals("Employee")){
                        employeeDiscount=((flightList.get(IDin).getBusinessClassPrice()/4)*3)*quantityIn;
                    }
                    totalPrice = (quantityIn * flightList.get(IDin).getBusinessClassPrice()) +(flightList.get(IDin).getSurcharge()*quantityIn);
                }
                if(typeIn.equals("First Class")){
                    if(flightList.get(IDin).getFirstClassSeats()<quantityIn){
                        seatsAvailable = false;
                    }
                    //handeling customer discount
                    typeSeat = "First";
                    //handeling customer discount
                    if(client.getHasMembership()==true){
                        discount = ((flightList.get(IDin).getMemberDiscount()*flightList.get(IDin).getFirstClassPrice())-flightList.get(IDin).getFirstClassPrice())*quantityIn;
                        discount = Math.round(discount * 100.0)/100.0;
                    }
                    if(client.getRole().equals("Employee")){
                        //employee class discount 50% off
                        employeeDiscount=(flightList.get(IDin).getMainCabinPrice()/2)*quantityIn;
                            
                    }
                    totalPrice = (quantityIn * flightList.get(IDin).getFirstClassPrice()) +(flightList.get(IDin).getSurcharge()*quantityIn);
                }
                //getting the total
                //FEES/DISCOUNTS/TAXES
                //security
                double securityFee=flightList.get(IDin).getSecurityFee()*quantityIn;
                securityFee = Math.round(securityFee * 100.0)/100.0;
                //airport fees
                //double airportFees=(flightList.get(flightID).getOriginAirportFee()*Integer.parseInt(numTickets)) + (flightList.get(flightID).getDestinationAirportFee()*Integer.parseInt(numTickets));
                double airportFees=flightList.get(IDin).getOriginAirportFee() + flightList.get(IDin).getDestinationAirportFee();
                airportFees = Math.round(airportFees * 100.0)/100.0;
                //subtotal (prices,fees,discounts)
                double subtotalBeforeTax= totalPrice+flightList.get(IDin).getMinerAirlinesFee()+securityFee+airportFees-(discount+employeeDiscount);
                subtotalBeforeTax = Math.round(subtotalBeforeTax * 100.0)/100.0;
                //tax
                double tax= (subtotalBeforeTax*flightList.get(IDin).getTax())-subtotalBeforeTax;
                tax = Math.round(tax * 100.0)/100.0;
                //total (subtotal with taxes)
                double total = (subtotalBeforeTax+ tax);
                total = Math.round(total * 100.0)/100.0;

                //only make transaction if there are seats and there is money
                if((client.getMoneyAvailable()-total>=0)&seatsAvailable==true){
                    //setting things... should be done after the transaction
                    flightList.get(IDin).getOriginAirport().setMoneyEarned(flightList.get(IDin).getOriginAirport().getMoneyEarned()+flightList.get(IDin).getOriginAirport().getFee());
                    flightList.get(IDin).getDestinationAirport().setMoneyEarned(flightList.get(IDin).getDestinationAirport().getMoneyEarned()+flightList.get(IDin).getDestinationAirportFee());
                    //setting discount to customer savings/if they are not memebers discount is zero
                    client.setSavings(client.getSavings()+discount);
                    //adding any discounts to the flight total discounted amount
                    flightList.get(IDin).setTotalFlightDiscount(flightList.get(IDin).getTotalFlightDiscount()+discount);
                    client.setFlightsPurchased(client.getFlightsPurchased()+1);
                    //setting the seats bought for the customer
                    if(typeSeat.equals("Main")){
                        client.setMainSeatsBought(client.getMainSeatsBought()+quantityIn);
                    }
                    if(typeSeat.equals("Business")){
                        client.setBusinessSeatsBought(client.getBusinessSeatsBought()+quantityIn);
                    }
                    if(typeSeat.equals("First")){
                        client.setFirstSeatsBought(client.getFirstSeatsBought()+quantityIn);
                    }
                    client.setMoneyWasted(client.getMoneyWasted()+total);
        
                    //for confirmation number
                    Random rand = new Random();
                    int randomNum = rand.nextInt(10000);
                    //creating instance of ticket
                    Ticket customerTicket = new Ticket(IDin, quantityIn, total, randomNum, quantityIn, typeSeat, client.getCustomerID());
                    //adding ticket to the customer list of tickets
                    client.addTicket(customerTicket,randomNum);
                    client.printTickets();
                    //adding to the flight total tickets purchased
                    flightList.get(IDin).addTicket(customerTicket, randomNum);
                    //adding customer to list of customers per flight
                    if(flightList.get(IDin).getCustomersInFlight().containsKey(client.getCustomerID())){
                        if(typeSeat.equals("Main")){
                            flightList.get(IDin).getCustomersInFlight().get(client.getCustomerID()).set(1,"Main Class: "+client.getMainSeatsBought());
                        }else if (typeSeat.equals("Business")){
                            flightList.get(IDin).getCustomersInFlight().get(client.getCustomerID()).set(2,"Business Class: "+client.getBusinessSeatsBought());
                        }else if(typeSeat.equals("First")){
                            flightList.get(IDin).getCustomersInFlight().get(client.getCustomerID()).set(3,"First Class: "+client.getFirstSeatsBought());
                        }
                        flightList.get(IDin).getCustomersInFlight().get(client.getCustomerID()).set(4,"Total Price: $"+client.getMoneyWasted());
                    }else{
                        //creating the customer information space if the customer was not already on the list of customers for the flight
                        ArrayList<String> customerInfo = new ArrayList<String>();
                        customerInfo.add(client.getFirstName()+" "+client.getLastName());
                        customerInfo.add("Main Class: "+client.getMainSeatsBought());
                        customerInfo.add("Business Class: "+client.getBusinessSeatsBought());
                        customerInfo.add("First Class: "+client.getFirstSeatsBought());
                        customerInfo.add("Total Price: $"+client.getMoneyWasted());
                        flightList.get(IDin).addCustomer(customerInfo, client.getCustomerID());
                    }
                    //writing actions in log
                    try {
                        Log.write(client.getFirstName()+" " +client.getLastName()+" recieved this ticket for the transaction made:\n"+customerTicket.printTicket()+"\n");
                        //updating customers money
                        client.setMoneyAvailable((Math.round(client.getMoneyAvailable()* 100.0)/100.0)-total);
                        Log.write(client.getFirstName()+" " +client.getLastName()+" money available is now: "+(Math.round(client.getMoneyAvailable()* 100.0)/100.0)+"\n");
                        //modifing seats bought
                        if(typeIn.equals("Main Cabin")){
                            flightList.get(IDin).setMainCabinSeats((flightList.get(IDin).getMainCabinSeats())-quantityIn);
                            Log.write("Main Cabin seats for flight ID: "+flightList.get(IDin).getID()+ " is now " +flightList.get(IDin).getMainCabinSeats()+"\n");
                        }else if(typeIn.equals("Business Class")){
                            flightList.get(IDin).setBusinessClassSeats((flightList.get(IDin).getBusinessClassSeats())-quantityIn);
                            Log.write("Business Cabin seats for flight ID: "+flightList.get(IDin).getID()+ " is now " +flightList.get(IDin).getBusinessClassSeats()+"\n");
                        }else if(typeIn.equals("First Class")){
                            flightList.get(IDin).setFirstClassSeats((flightList.get(IDin).getFirstClassSeats())-quantityIn);
                            Log.write("First Class seats for flight ID: "+flightList.get(IDin).getID()+ " is now " +flightList.get(IDin).getFirstClassSeats()+"\n");
                        }
                        //modifing total seats
                        flightList.get(IDin).setTotalSeats(flightList.get(IDin).getTotalSeats()-quantityIn);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }catch(IllegalArgumentException e){
                        System.out.println("There was an issue with your input!");
                        System.out.println(e.getMessage());
                    }catch(IndexOutOfBoundsException e){
                        System.out.println("That ID doesn't exists!");
                        System.out.println(e.getMessage());
                    }catch(IllegalStateException e){
                        System.out.println("The user interaction has terminated!");
                        System.out.println(e.getMessage());
                    }catch(NullPointerException e){
                        System.out.println("The customer was not found!");
                        System.out.println(e.getMessage());
                    }
                }

            }

        }
    }
    /**
     * Mathod that handles autopurchases only for employees 
     * @param firstNameIn - first name of employee
     * @param lastNameIn - last name of employee
     * @param actionIn - the action wanted by employee
     * @param IDin - id of flight wanted
     * @param originCodeIn
     * @param destCodeIn
     * @param typeIn - type of seat wanted
     * @param quantityIn - number of seat tickets wanted
     * @param customerList - hashMap of employee objects
     * @param flightList - hashMap of flight objects
     * @param Log - file writer
     */
    public void autoPurchaseEmployee(String firstNameIn, String lastNameIn, String actionIn, int IDin, String originCodeIn, String destCodeIn, String typeIn, int quantityIn, HashMap<Integer, Employee> customerList, HashMap<Integer, Flight> flightList, FileWriter Log){
        if(actionIn.equals("Buy")){
            //looking for customer ID
            //Customer client = null;
            Employee client = null;
            for(Employee value: customerList.values()){
                if(value.getFirstName().equals(firstNameIn)&value.getLastName().equals(lastNameIn)){
                    client =value;
                }
            }
            //checking if there are seats available
            //checking if seats are sold out
            boolean seatsAvailable = true;
            //for transaction
            //ustomer discount
            double discount=0.0;
            double employeeDiscount=0.0;
            //seeting the type of seat wanted to use in ticket
            String typeSeat = null;
            //for later use in transaction
            double totalPrice = 0.0;

            if(typeIn.equals("Main Cabin")){
                if(flightList.get(IDin).getMainCabinSeats()<quantityIn){
                    seatsAvailable=false;
                }
                typeSeat = "Main";
                //handeling customer discount
                if(client.getHasMembership()==true){
                    discount = ((flightList.get(IDin).getMemberDiscount()*flightList.get(IDin).getMainCabinPrice())-flightList.get(IDin).getMainCabinPrice())*quantityIn;
                    discount = Math.round(discount * 100.0)/100.0;
                }
                if(client.getRole().equals("Employee")){
                    employeeDiscount=((flightList.get(IDin).getMainCabinPrice()/4.0)*3.0)*quantityIn;
                }
                totalPrice = (quantityIn * flightList.get(IDin).getMainCabinPrice()) + (flightList.get(IDin).getSurcharge() *quantityIn);
            }
            if(typeIn.equals("Business Class")){
                if(flightList.get(IDin).getBusinessClassSeats()<quantityIn){
                    seatsAvailable = false;
                }
                typeSeat = "Business";
                //handeling customer discount
                if(client.getHasMembership()==true){
                    discount = ((flightList.get(IDin).getMemberDiscount()*flightList.get(IDin).getBusinessClassPrice())-flightList.get(IDin).getBusinessClassPrice())*quantityIn;
                    discount = Math.round(discount * 100.0)/100.0;
                }
                if(client.getRole().equals("Employee")){
                    employeeDiscount=((flightList.get(IDin).getBusinessClassPrice()/4)*3)*quantityIn;
                }
                totalPrice = (quantityIn * flightList.get(IDin).getBusinessClassPrice()) +(flightList.get(IDin).getSurcharge()*quantityIn);
            }
            if(typeIn.equals("First Class")){
                if(flightList.get(IDin).getFirstClassSeats()<quantityIn){
                    seatsAvailable = false;
                }
                //handeling customer discount
                typeSeat = "First";
                //handeling customer discount
                if(client.getHasMembership()==true){
                    discount = ((flightList.get(IDin).getMemberDiscount()*flightList.get(IDin).getFirstClassPrice())-flightList.get(IDin).getFirstClassPrice())*quantityIn;
                    discount = Math.round(discount * 100.0)/100.0;
                }
                if(client.getRole().equals("Employee")){
                    //employee class discount 50% off
                    employeeDiscount=(flightList.get(IDin).getMainCabinPrice()/2)*quantityIn;
                        
                }
                totalPrice = (quantityIn * flightList.get(IDin).getFirstClassPrice()) +(flightList.get(IDin).getSurcharge()*quantityIn);
            }
            //getting the total
            //FEES/DISCOUNTS/TAXES
            //security
            double securityFee=flightList.get(IDin).getSecurityFee()*quantityIn;
            securityFee = Math.round(securityFee * 100.0)/100.0;
            //airport fees
            //double airportFees=(flightList.get(flightID).getOriginAirportFee()*Integer.parseInt(numTickets)) + (flightList.get(flightID).getDestinationAirportFee()*Integer.parseInt(numTickets));
            double airportFees=flightList.get(IDin).getOriginAirportFee() + flightList.get(IDin).getDestinationAirportFee();
            airportFees = Math.round(airportFees * 100.0)/100.0;
            //subtotal (prices,fees,discounts)
            double subtotalBeforeTax= totalPrice+flightList.get(IDin).getMinerAirlinesFee()+securityFee+airportFees-(discount+employeeDiscount);
            subtotalBeforeTax = Math.round(subtotalBeforeTax * 100.0)/100.0;
            //tax
            double tax= (subtotalBeforeTax*flightList.get(IDin).getTax())-subtotalBeforeTax;
            tax = Math.round(tax * 100.0)/100.0;
            //total (subtotal with taxes)
            double total = (subtotalBeforeTax+ tax);
            total = Math.round(total * 100.0)/100.0;

            //only make transaction if there are seats and there is money
            if((client.getMoneyAvailable()-total>=0)&seatsAvailable==true){
                //setting things... should be done after the transaction
                flightList.get(IDin).getOriginAirport().setMoneyEarned(flightList.get(IDin).getOriginAirport().getMoneyEarned()+flightList.get(IDin).getOriginAirport().getFee());
                flightList.get(IDin).getDestinationAirport().setMoneyEarned(flightList.get(IDin).getDestinationAirport().getMoneyEarned()+flightList.get(IDin).getDestinationAirportFee());
                //setting discount to customer savings/if they are not memebers discount is zero
                client.setSavings(client.getSavings()+discount);
                //adding any discounts to the flight total discounted amount
                flightList.get(IDin).setTotalFlightDiscount(flightList.get(IDin).getTotalFlightDiscount()+discount);
                client.setFlightsPurchased(client.getFlightsPurchasedNum()+1);
                //setting the seats bought for the customer
                if(typeSeat.equals("Main")){
                    client.setMainSeatsBought(client.getMainSeatsBought()+quantityIn);
                }
                if(typeSeat.equals("Business")){
                    client.setBusinessSeatsBought(client.getBusinessSeatsBought()+quantityIn);
                }
                if(typeSeat.equals("First")){
                    client.setFirstSeatsBought(client.getFirstSeatsBought()+quantityIn);
                }
                client.setMoneyWasted(client.getMoneyWasted()+total);
    
                //for confirmation number
                Random rand = new Random();
                int randomNum = rand.nextInt(10000);
                //creating instance of ticket
                Ticket customerTicket = new Ticket(IDin, quantityIn, total, randomNum, quantityIn, typeSeat, client.getEmployeeID());
                //adding ticket to the customer list of tickets
                client.addTicket(customerTicket,randomNum);
                client.printTickets();
                //adding to the flight total tickets purchased
                flightList.get(IDin).addTicket(customerTicket, randomNum);
                //adding customer to list of customers per flight
                if(flightList.get(IDin).getCustomersInFlight().containsKey(client.getEmployeeID())){
                    if(typeSeat.equals("Main")){
                        flightList.get(IDin).getCustomersInFlight().get(client.getEmployeeID()).set(1,"Main Class: "+client.getMainSeatsBought());
                    }else if (typeSeat.equals("Business")){
                        flightList.get(IDin).getCustomersInFlight().get(client.getEmployeeID()).set(2,"Business Class: "+client.getBusinessSeatsBought());
                    }else if(typeSeat.equals("First")){
                        flightList.get(IDin).getCustomersInFlight().get(client.getEmployeeID()).set(3,"First Class: "+client.getFirstSeatsBought());
                    }
                    flightList.get(IDin).getCustomersInFlight().get(client.getEmployeeID()).set(4,"Total Price: $"+client.getMoneyWasted());
                }else{
                    //creating the customer information space if the customer was not already on the list of customers for the flight
                    ArrayList<String> customerInfo = new ArrayList<String>();
                    customerInfo.add(client.getFirstName()+" "+client.getLastName());
                    customerInfo.add("Main Class: "+client.getMainSeatsBought());
                    customerInfo.add("Business Class: "+client.getBusinessSeatsBought());
                    customerInfo.add("First Class: "+client.getFirstSeatsBought());
                    customerInfo.add("Total Price: $"+client.getMoneyWasted());
                    flightList.get(IDin).addCustomer(customerInfo, client.getEmployeeID());
                }
                //writing actions in log
                try {
                    Log.write(client.getFirstName()+" " +client.getLastName()+" recieved this ticket for the transaction made:\n"+customerTicket.printTicket()+"\n");
                    //updating customers money
                    client.setMoneyAvailable((Math.round(client.getMoneyAvailable()* 100.0)/100.0)-total);
                    Log.write(client.getFirstName()+" " +client.getLastName()+" money available is now: "+(Math.round(client.getMoneyAvailable()* 100.0)/100.0)+"\n");
                    //modifing seats bought
                    if(typeIn.equals("Main Cabin")){
                        flightList.get(IDin).setMainCabinSeats((flightList.get(IDin).getMainCabinSeats())-quantityIn);
                        Log.write("Main Cabin seats for flight ID: "+flightList.get(IDin).getID()+ " is now " +flightList.get(IDin).getMainCabinSeats()+"\n");
                    }else if(typeIn.equals("Business Class")){
                        flightList.get(IDin).setBusinessClassSeats((flightList.get(IDin).getBusinessClassSeats())-quantityIn);
                        Log.write("Business Cabin seats for flight ID: "+flightList.get(IDin).getID()+ " is now " +flightList.get(IDin).getBusinessClassSeats()+"\n");
                    }else if(typeIn.equals("First Class")){
                        flightList.get(IDin).setFirstClassSeats((flightList.get(IDin).getFirstClassSeats())-quantityIn);
                        Log.write("First Class seats for flight ID: "+flightList.get(IDin).getID()+ " is now " +flightList.get(IDin).getFirstClassSeats()+"\n");
                    }
                    //modifing total seats
                    flightList.get(IDin).setTotalSeats(flightList.get(IDin).getTotalSeats()-quantityIn);
                } catch (IOException e) {
                    e.printStackTrace();
                }catch(IllegalArgumentException e){
                    System.out.println("There was an issue with your input!");
                    System.out.println(e.getMessage());
                }catch(IndexOutOfBoundsException e){
                    System.out.println("That ID doesn't exists!");
                    System.out.println(e.getMessage());
                }catch(IllegalStateException e){
                    System.out.println("The user interaction has terminated!");
                    System.out.println(e.getMessage());
                }catch(NullPointerException e){
                    System.out.println("The customer was not found!");
                    System.out.println(e.getMessage());
                }

            }

        }
    }
}
