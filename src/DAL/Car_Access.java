/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAL;

import BE.Car;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author Niels Kristian Ravn
 */
public class Car_Access extends DatabaseConnection{
    
    
    public Car_Access() throws IOException{
        super();
    }

    public ArrayList<Car> getCarsBySearchQuery(String query) throws SQLException {
        Connection con = null;
        ArrayList<Car> cars = new ArrayList<>();
        
        try{
            con = getConnection();
            Statement stmnt = con.createStatement();
            
            ResultSet rs = stmnt.executeQuery("SELECT * FROM Car "
                    + "WHERE carNr LIKE '%" + query + "%' OR name LIKE '%" + query +"%';" );
            
            while(rs.next()){
                int carNr = rs.getInt("carNr");
                String iconPath = rs.getString("iconPath");
                String name = rs.getString("name");
                int seats = rs.getInt("seats");
                
                Car c = new Car(carNr, iconPath, name, seats);
                cars.add(c);
            }
            
        }finally {
            if (con != null) {
                con.close();
            }
        }
        return cars;
    }

    public boolean checkNewCarNr(int carNr) throws SQLException {
        Connection con = null;
        
        try{
            con = getConnection();
            Statement stmnt = con.createStatement();
            
            ResultSet rs = stmnt.executeQuery("SELECT carNr FROM Car WHERE carNr = " + carNr);
            
            return !rs.next();
            
            
        }finally {
            if (con != null) {
                con.close();
            }
        }
    }

    public void updateCar(Car c) throws SQLException {
        Connection con = null;
        
        try{
            con = getConnection();
            Statement stmnt = con.createStatement();
            
            int affectedRows = stmnt.executeUpdate("UPDATE Car SET "
                    + "iconPath = '" + c.getImage().getPath()+ "', "
                    + "name = '" + c.getName() + "', "
                    + "seats = " + c.getSeats() +" "
                    + "WHERE carNr = " + c.getCarNr() + ";");
            
            if (affectedRows == 0) {
                throw new SQLException("Updating car entry failed, no rows affected");
            }
            
        }finally {
            if (con != null) {
                con.close();
            }
        }
    }

    public void deleteCar(Car car) throws SQLException {
        Connection con = null;
        
        try{
            con = getConnection();
            Statement stmnt = con.createStatement();
            
            int affectedRows = stmnt.executeUpdate("DELETE FROM Car WHERE carNr = " + car.getCarNr());
            
            if (affectedRows == 0) {
                throw new SQLException("Deleting car entry failed, no rows affected");
            }
            
        }finally {
            if (con != null) {
                con.close();
            }
        }
    }

    public void createNewCar(Car car) throws SQLException {
        Connection con = null;
        
        try{
            con = getConnection();
            Statement stmnt = con.createStatement();
            
            int affectedRows = stmnt.executeUpdate("INSERT INTO Car VALUES ("
                    + car.getCarNr() + ", '"
                    + car.getImage().getPath()+ "', '"
                    + car.getName() + "', "
                    + car.getSeats() +");");
            
            if (affectedRows == 0) {
                throw new SQLException("Creating car entry failed, no rows affected");
            }
            
        }finally {
            if (con != null) {
                con.close();
            }
        }
    }
}
