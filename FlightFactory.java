import java.util.*;
public class FlightFactory {
    public FlightFactory(){}

    /**
     * This method will allow flight object creation to follow a factory design pattern.
     * @param type - what object is wanted
     * @param tempFlightData - array with information
     * @param headerList - the order of the attributes
     * @return - Flight object
     */
    public Flight createFlight(String type, String[] tempFlightData, ArrayList<String> headerList){
        if(type.equals("Domestic")){
            return new Domestic(Integer.parseInt(tempFlightData[headerList.indexOf("ID")]), Integer.parseInt(tempFlightData[headerList.indexOf("Flight Number")]),
            tempFlightData[headerList.indexOf("Origin Airport")], tempFlightData[headerList.indexOf("Origin Code")], tempFlightData[headerList.indexOf("Destination Airport")], 
            tempFlightData[headerList.indexOf("Destination Code")], tempFlightData[headerList.indexOf("Departing Date")], tempFlightData[headerList.indexOf("Departing Time")], 
            Integer.parseInt(tempFlightData[headerList.indexOf("Duration")]), Integer.parseInt(tempFlightData[headerList.indexOf("Distance")]), Integer.parseInt(tempFlightData[headerList.indexOf("Time Zone Difference")]),
            tempFlightData[headerList.indexOf("Arrival Date")], tempFlightData[headerList.indexOf("Arrival Time")], tempFlightData[headerList.indexOf("Type")], 
            Integer.parseInt(tempFlightData[headerList.indexOf("Surcharge")]), Boolean.parseBoolean(tempFlightData[headerList.indexOf("Food Served")]), 
            Integer.parseInt(tempFlightData[headerList.indexOf("Route Cost")]), Integer.parseInt(tempFlightData[headerList.indexOf("Miner Points")]), 
            Integer.parseInt(tempFlightData[headerList.indexOf("Total Seats")]),Integer.parseInt(tempFlightData[headerList.indexOf("First Class Seats")]),
            Integer.parseInt(tempFlightData[headerList.indexOf("Business Class Seats")]), Integer.parseInt(tempFlightData[headerList.indexOf("Main Cabin Seats")]), 
            Integer.parseInt(tempFlightData[headerList.indexOf("First Class Price")]), Integer.parseInt(tempFlightData[headerList.indexOf("Business Class Price")]), 
            Integer.parseInt(tempFlightData[headerList.indexOf("Main Cabin Price")]),tempFlightData[headerList.indexOf("Origin Airport City")],
            tempFlightData[headerList.indexOf("Origin Airport State")], tempFlightData[headerList.indexOf("Origin Airport Country")],
            Double.parseDouble(tempFlightData[headerList.indexOf("Origin Airport Fee")]), Boolean.parseBoolean(tempFlightData[headerList.indexOf("Origin Airport Lounge")]),
            tempFlightData[headerList.indexOf("Destination Airport City")], tempFlightData[headerList.indexOf("Destination Airport State")],
            tempFlightData[headerList.indexOf("Destination Airport Country")], Double.parseDouble(tempFlightData[headerList.indexOf("Destination Airport Fee")]),
            Boolean.parseBoolean(tempFlightData[headerList.indexOf("Destination Airport Lounge")]));
        }else{
            return new International(Integer.parseInt(tempFlightData[headerList.indexOf("ID")]), Integer.parseInt(tempFlightData[headerList.indexOf("Flight Number")]),
            tempFlightData[headerList.indexOf("Origin Airport")], tempFlightData[headerList.indexOf("Origin Code")], tempFlightData[headerList.indexOf("Destination Airport")], 
            tempFlightData[headerList.indexOf("Destination Code")], tempFlightData[headerList.indexOf("Departing Date")], tempFlightData[headerList.indexOf("Departing Time")], 
            Integer.parseInt(tempFlightData[headerList.indexOf("Duration")]), Integer.parseInt(tempFlightData[headerList.indexOf("Distance")]), Integer.parseInt(tempFlightData[headerList.indexOf("Time Zone Difference")]),
            tempFlightData[headerList.indexOf("Arrival Date")], tempFlightData[headerList.indexOf("Arrival Time")], tempFlightData[headerList.indexOf("Type")], 
            Integer.parseInt(tempFlightData[headerList.indexOf("Surcharge")]), Boolean.parseBoolean(tempFlightData[headerList.indexOf("Food Served")]), 
            Integer.parseInt(tempFlightData[headerList.indexOf("Route Cost")]), Integer.parseInt(tempFlightData[headerList.indexOf("Miner Points")]), 
            Integer.parseInt(tempFlightData[headerList.indexOf("Total Seats")]),Integer.parseInt(tempFlightData[headerList.indexOf("First Class Seats")]),
            Integer.parseInt(tempFlightData[headerList.indexOf("Business Class Seats")]), Integer.parseInt(tempFlightData[headerList.indexOf("Main Cabin Seats")]), 
            Integer.parseInt(tempFlightData[headerList.indexOf("First Class Price")]), Integer.parseInt(tempFlightData[headerList.indexOf("Business Class Price")]), 
            Integer.parseInt(tempFlightData[headerList.indexOf("Main Cabin Price")]),tempFlightData[headerList.indexOf("Origin Airport City")],
            tempFlightData[headerList.indexOf("Origin Airport State")], tempFlightData[headerList.indexOf("Origin Airport Country")],
            Double.parseDouble(tempFlightData[headerList.indexOf("Origin Airport Fee")]), Boolean.parseBoolean(tempFlightData[headerList.indexOf("Origin Airport Lounge")]),
            tempFlightData[headerList.indexOf("Destination Airport City")], tempFlightData[headerList.indexOf("Destination Airport State")],
            tempFlightData[headerList.indexOf("Destination Airport Country")], Double.parseDouble(tempFlightData[headerList.indexOf("Destination Airport Fee")]),
            Boolean.parseBoolean(tempFlightData[headerList.indexOf("Destination Airport Lounge")]));
        }
    }
}
