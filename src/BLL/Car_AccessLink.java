/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BLL;

import BE.Car;
import DAL.Car_Access;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Niels Kristian Ravn
 */
public class Car_AccessLink {
    
    Car_Access ca;
    
    /**
     * creates a new link to the car acces class
     * @throws IOException if the config file is not found.
     */
    public Car_AccessLink() throws IOException{
        ca = new Car_Access();
    }

    /**
     * gets alle the cars where carnr or car name contains the search query
     * @param query the query to search for
     * @return a list of all cars which meets the search criteria.
     * @throws SQLException 
     */
    public ArrayList<Car> getCarsBySearchQuery(String query) throws SQLException {
        return ca.getCarsBySearchQuery(query);
    }

    /**
     * checks if the carnr is in the database 
     * @param carNr the carnr to check for.
     * @return false if the car is in the database, truw otherwise
     * @throws SQLException 
     */
    public boolean checkNewCarNr(int carNr) throws SQLException {
        return ca.checkNewCarNr(carNr);
    }
    
}
