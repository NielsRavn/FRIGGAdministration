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
    
    public Car_AccessLink() throws IOException{
        ca = new Car_Access();
    }

    public ArrayList<Car> getCarsBySearchQuery(String query) throws SQLException {
        return ca.getCarsBySearchQuery(query);
    }

    public boolean checkNewCarNr(int carNr) throws SQLException {
        return ca.checkNewCarNr(carNr);
    }
    
}
