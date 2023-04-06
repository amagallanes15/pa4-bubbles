
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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * @version 2-this class was modified to read files no matter the order of the attributes
 * This class handles reading the csv files and making objects which are added to hashMap structures
 */
public class ReadFiles {
    //attributes
    //Data Structure to store customer objects
    private HashMap<Integer, Customer> customerList;
    //Data Structure to store customer objects
    private HashMap<Integer, Employee> employeeList;
    //flight map with domestic and international instances
    private HashMap<Integer, Flight> dataList;
    //Storing the automated purchases
    private LinkedHashMap<Integer, AutoPurchases> automatedList;
    //Storing both customers and employees
    private HashMap<Integer, Person> people;
    //singleton design pattern
    private static ReadFiles obj;
    //headers of two files
    private String[] customerFileHeader;
    private String[] flightsFileHeader;
    //factory design pattern
    private FlightFactory ff;
    
    /**
     * Constructor- makes instances of hashMaps needed to store objects made
     */
    public ReadFiles(){
        this.customerList = new HashMap<Integer, Customer>();
        this.employeeList = new HashMap<Integer, Employee>();
        this.dataList = new HashMap<Integer, Flight>();
        this.automatedList = new LinkedHashMap<Integer, AutoPurchases>();
        this.people= new HashMap<Integer, Person>();
        this.ff = new FlightFactory();
    }
    /**
     * Method to make the class a Singleton design
     * @return - ReadFiles object
     */
    public static ReadFiles getInstance(){
        if(obj==null){
            obj = new ReadFiles();
        }
        return obj;
    }

    //setters
    /**
     * Sets the customer list hashmap.
     * @param customerList
     */
    public void setCustomerList(HashMap<Integer,Customer> customerList){
        this.customerList = customerList;
    }
    /**
     * Sets the employee list hashmap.
     * @param employeeList
     */
    public void setEmployeeList(HashMap<Integer, Employee> employeeList){
        this.employeeList = employeeList;
    }
    /**
     * Sets the list of flights hashmap.
     * @param dataListIn
     */
    public void setDataList(HashMap<Integer,Flight> dataListIn){
        this.dataList = dataListIn;
    }
    /**
     * Sets the hashMap for the autopurchases objects
     * @param automatedListIn
     */
    public void setAutomatedList(LinkedHashMap<Integer, AutoPurchases> automatedListIn){
        this.automatedList=automatedListIn;
    }
    /**
     * Sets the hashMap of all the poeple in the customer list
     * @param peopleListIn
     */
    public void setPeopleList(HashMap<Integer, Person> peopleListIn){
        this.people=peopleListIn;
    }
    
    //getters
    /**
     * Gets the hashmap of the customers.
     * @return - hashmap
     */
    public HashMap<Integer, Customer> getCustomerList(){
        return this.customerList;
    }
    /**
     * Gets the hashmap of the employees.
     * @return - hahsmap
     */
    public HashMap<Integer, Employee> getEmployeeList(){
        return this.employeeList;
    }
    /**
     * Gets the haahmap of the flights.
     * @return - hashmap
     */
    public HashMap<Integer,Flight> getDataList(){
        return this.dataList;
    }
    /**
     * Gets the hashMap containing the autopurchases objects
     * @return
     */
    public LinkedHashMap<Integer, AutoPurchases> getAutomatedList(){
        return this.automatedList;
    }
    /**
     * Gets the hashMap containing all the people objects form the customer list
     * @return
     */
    public HashMap<Integer, Person> getPeopleList(){
        return this.people;
    }
    /**
     * Method to read customer csv file and make customer objects and add them to corresponding hashMap
     * @param fileName - string of file being read
     */
    public void fillCustomerList(String fileName){
        try{    
            Scanner sc = new Scanner(new File(fileName));
            //reading customer file
            String lineFile = sc.nextLine();
            String[] header = lineFile.split(",");
            this.customerFileHeader=header;
            int firstNameIndex=0;
            int lastNameIndex=0;
            int DOBindex=0;
            int customerIDindex=0;
            int moneyAvailableIndex=0;
            int flightsPurchasedIndex=0;
            int hasMembershipIndex=0;
            int usernameIndex=0;
            int passwordIndex=0;
            int roleIndex=0;
            for(int i=0; i<header.length; i++){
                if(header[i].equals("DOB")){
                    DOBindex=i;
                }else if(header[i].equals("Username")){
                    usernameIndex=i;
                }else if(header[i].equals("Money Available")){
                    moneyAvailableIndex=i;
                }else if(header[i].equals("Last Name")){
                    lastNameIndex=i;
                }else if(header[i].equals("Password")){
                    passwordIndex=i;
                }else if(header[i].equals("Role")){
                    roleIndex=i;
                }else if(header[i].equals("ID")){
                    customerIDindex=i;
                }else if(header[i].equals("First Name")){
                    firstNameIndex=i;
                }else if(header[i].equals("Flights Purchased")){
                    flightsPurchasedIndex=i;
                }else if(header[i].equals("MinerAirlines Membership")){
                    hasMembershipIndex=i;
                }
            }
            while(sc.hasNext()){
                lineFile = sc.nextLine();
                String[] tempCustomerData = lineFile.split(",");
                if(tempCustomerData[roleIndex].equals("Customer")){
                    Customer tempCustomer = new Customer(Integer.parseInt(tempCustomerData[customerIDindex]), tempCustomerData[firstNameIndex], 
                    tempCustomerData[lastNameIndex], tempCustomerData[DOBindex], tempCustomerData[roleIndex], Double.parseDouble(tempCustomerData[moneyAvailableIndex]), 
                    Integer.parseInt(tempCustomerData[flightsPurchasedIndex]), Boolean.parseBoolean(tempCustomerData[hasMembershipIndex]), 
                    tempCustomerData[usernameIndex], tempCustomerData[passwordIndex]);
                    this.customerList.put(Integer.parseInt(tempCustomerData[customerIDindex]), tempCustomer);
                    this.people.put(Integer.parseInt(tempCustomerData[customerIDindex]), tempCustomer);
                }
            }
        }catch(FileNotFoundException e){
            System.out.println("The file is not found!");
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
     * Method to read customer csv file and make employee objects and add them to corresponding hashMap
     * @param fileName - string of file being read
     */
    public void fillEmployeeList(String fileName){
        try{    
            Scanner sc = new Scanner(new File(fileName)); 
            String lineFile = sc.nextLine();
            String[] header = lineFile.split(",");
            int firstNameIndex=0;
            int lastNameIndex=0;
            int DOBindex=0;
            int employeeIDindex=0;
            int moneyAvailableIndex=0;
            int flightsPurchasedIndex=0;
            int hasMembershipIndex=0;
            int usernameIndex=0;
            int passwordIndex=0;
            int roleIndex=0;
            for(int i=0; i<header.length; i++){
                if(header[i].equals("DOB")){
                    DOBindex=i;
                }else if(header[i].equals("Username")){
                    usernameIndex=i;
                }else if(header[i].equals("Money Available")){
                    moneyAvailableIndex=i;
                }else if(header[i].equals("Last Name")){
                    lastNameIndex=i;
                }else if(header[i].equals("Password")){
                    passwordIndex=i;
                }else if(header[i].equals("Role")){
                    roleIndex=i;
                }else if(header[i].equals("ID")){
                    employeeIDindex=i;
                }else if(header[i].equals("First Name")){
                    firstNameIndex=i;
                }else if(header[i].equals("Flights Purchased")){
                    flightsPurchasedIndex=i;
                }else if(header[i].equals("MinerAirlines Membership")){
                    hasMembershipIndex=i;
                }
            }
            while(sc.hasNext()){
                lineFile = sc.nextLine();
                String[] tempCustomerData = lineFile.split(",");
                if(tempCustomerData[roleIndex].equals("Employee")){
                    /*int employeeIDIn, String firstNameIn, String lastNameIn, String DOBIn, String roleIn, double moneyAvailableIn, 
                    int flightsPurchasedIn, boolean hasMembershipIn, String usernameIn, String passwordIn */
                    Employee tempEmployee = new Employee(Integer.parseInt(tempCustomerData[employeeIDindex]), tempCustomerData[firstNameIndex], 
                    tempCustomerData[lastNameIndex], tempCustomerData[DOBindex], tempCustomerData[roleIndex], 
                    Double.parseDouble(tempCustomerData[moneyAvailableIndex]), Integer.parseInt(tempCustomerData[flightsPurchasedIndex]), 
                    Boolean.parseBoolean(tempCustomerData[hasMembershipIndex]), tempCustomerData[usernameIndex], tempCustomerData[passwordIndex]);
                    this.employeeList.put(Integer.parseInt(tempCustomerData[employeeIDindex]), tempEmployee);
                    this.people.put(Integer.parseInt(tempCustomerData[employeeIDindex]), tempEmployee);
                }
            }
        }catch(FileNotFoundException e){
            System.out.println("The file is not found!");
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
     * Method to read flight schedule csv file and make flight objects (domestic or international) and add them to corresponding hashMap
     * @param fileName - string of file being read
     */
    public void fillFlightList(String fileName){
        try{    
            Scanner sc = new Scanner(new File(fileName)); 
            String lineFile = sc.nextLine();
            String[] header = lineFile.split(",");
            this.flightsFileHeader=header;
            List<String> list = Arrays.asList(header);
            ArrayList<String> headerList = new ArrayList<String>(list);
            while(sc.hasNext()){
                lineFile = sc.nextLine();
                String[] tempFlightData = lineFile.split(",");
                if(tempFlightData[headerList.indexOf("Type")].equals("International")){
                    Flight flightData = ff.createFlight("International", tempFlightData, headerList);
                    this.dataList.put(Integer.parseInt(tempFlightData[headerList.indexOf("ID")]), flightData);

                }else if(tempFlightData[headerList.indexOf("Type")].equals("Domestic")){
                    Flight flightDataDomestic = ff.createFlight("Domestic", tempFlightData, headerList);
                    this.dataList.put(Integer.parseInt(tempFlightData[headerList.indexOf("ID")]),flightDataDomestic);
                }
            }

            
        }catch(FileNotFoundException e){
            System.out.println("The file is not found!");
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
     * Method to fill a hashmap with autoPurchases objects
     * @param fileName
     */
    public void fillAutomatedList(String fileName){
        try{    
            Scanner sc = new Scanner(new File(fileName)); 
            String lineFile = sc.nextLine();
            String[] header = lineFile.split(",");
            //this.purchasesFileHeader=header;
            List<String> list = Arrays.asList(header);
            ArrayList<String> headerList = new ArrayList<String>(list);
            //used for key in hashmap
            int counter = 1;
            while(sc.hasNext()){
                lineFile = sc.nextLine();
                String[] tempPurchaseData = lineFile.split(",");
                AutoPurchases purchase = new AutoPurchases(tempPurchaseData[headerList.indexOf("First Name")], tempPurchaseData[headerList.indexOf("Last Name")], 
                tempPurchaseData[headerList.indexOf("Action")], Integer.parseInt(tempPurchaseData[headerList.indexOf("Flight ID")]), tempPurchaseData[headerList.indexOf("Origin Airport")],
                tempPurchaseData[headerList.indexOf("Destination Airport")], tempPurchaseData[headerList.indexOf("Ticket Type")],Integer.parseInt(tempPurchaseData[headerList.indexOf("Ticket Quantity")]));
                this.automatedList.put(counter, purchase);
                counter++;
            }

            
        }catch(FileNotFoundException e){
            System.out.println("The file is not found!");
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
     * Method that allows the new csv file containing the flight data to be created upon termination of the system
     * @param newInfo - file where new contents will be placed into
     * @param dataList - hashMap of flight objects
     */
    public void makeFlightCSV(File newInfo, HashMap<Integer, Flight> dataList){
        try {
            //newInfo is the name of the new updated file
            FileWriter fileWriter = new FileWriter(newInfo);
            fileWriter.write((String.join(",",flightsFileHeader)+",First Class Seats Sold,Business Class Seats Sold,Main Class Seats Sold,Total First Class Revenue,Total Business Class Revenue,Total Main Class Revenue\n"));
            for(Flight value: dataList.values()){
                fileWriter.write(value.updatedSheetInfo(flightsFileHeader)+"\n");
            }
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Method that allows the new csv file containing the customer and employee data to be created upon termination of the system
     * @param newInfo - file where new contents will be placed into
     * @param customerList - hashMap of customer objects
     * @param employeeList - hashMap of employee objects
     */
    public void makeCustomerCSV(File newInfo, HashMap<Integer, Customer> customerList, HashMap<Integer,Employee> employeeList){
        try {
            //newInfo is the name of the new updated file
            FileWriter fileWriter = new FileWriter(newInfo);
            fileWriter.write(String.join(",",customerFileHeader)+"\n");
            for(Customer value: customerList.values()){
                fileWriter.write(value.printCustomerLine(customerFileHeader)+"\n");
            }
            for(Employee value: employeeList.values()){
                fileWriter.write(value.printEmployeeLine(customerFileHeader)+"\n");
            }
            fileWriter.flush();
            fileWriter.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
