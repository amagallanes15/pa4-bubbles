
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
 * @version 2- date of birth attribute was added
 * This class is abstract so no instances are directly made
 */
public abstract class Person {
    //Attributes
    private int ID;
    private String role;
    private String firstName;
    private String lastName;
    private String DOB;

    //Constructors
    /**
     * Default Constructor of Person
     */
    public Person(){
    }
    /**
     * Main Constructor for person objects
     * @param firstNameIn
     * @param lastNameIn
     * @param DOBIn
     * @param IDIn
     * @param roleIn
     */
    public Person(String firstNameIn, String lastNameIn, String DOBIn, int IDIn, String roleIn){
        this.firstName = firstNameIn;
        this.lastName = lastNameIn;
        this.DOB = DOBIn;
        this.ID=IDIn;
        this.role=roleIn;
    }

    //Setters
    /**
     * Sets the first name of the person.
     * @param firstNameIn
     */
    public void setFirstName(String firstNameIn){
        this.firstName = firstNameIn;
    }
    /**
     * Sets the last name of the person.
     * @param lastNameIn
     */
    public void setLastName(String lastNameIn){
        this.lastName = lastNameIn;
    }
    /**
     * Sets the date of birth of the person.
     * @param DOBIn
     */
    public void setDOB(String DOBIn){
        this.DOB = DOBIn;
    }
    /**
     * Sets the id of the person
     * @param IDin
     */
    public void setID(int IDin){
        this.ID = IDin;
    }
    /**
     * Sets the role of the person
     * @param roleIn
     */
    public void setRole(String roleIn){
        this.role=roleIn;
    }

    //Getters
    /**
     * Gets the first name of the person.
     * @return - string
     */
    public String getFirstName(){
        return this.firstName;
    }
    /**
     * Gets the last name of the person.
     * @return - string
     */
    public String getLastName(){
        return this.lastName;
    }
    /**
     * Gets the date of birth of the person.
     * @return - string
     */
    public String getDOB(){
        return this.DOB;
    }
    /**
     * Gets the id of the person
     * @return
     */
    public int getID(){
        return this.ID;
    }
    /**
     * Gets the role of the person
     * @return
     */
    public String getRole(){
        return this.role;
    }

}

