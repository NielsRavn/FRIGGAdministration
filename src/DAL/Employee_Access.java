/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAL;

import BE.Employee;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Niels Kristian Ravn
 */
public class Employee_Access extends DatabaseConnection{
    
    public Employee_Access() throws IOException{
        super();
    }

    public ArrayList<Employee> getEmployeesBySearchQuery(String query) throws SQLException {
        ArrayList<Employee> employees = new ArrayList<>();
        Connection con = null;
        
        try{
            con = getConnection();
            Statement stmnt = con.createStatement();
            
            ResultSet rs = stmnt.executeQuery("SELECT * FROM Fireman "
                    + "WHERE firstName LIKE '%" + query + "%' OR lastName LIKE '%" + query + "%'");
            
            while (rs.next()) {
                int id = rs.getInt("employeeId");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                boolean teamLeader = rs.getBoolean("teamleader");
                boolean driver = rs.getBoolean("driver");
                
                Employee e = new Employee(id, firstName, lastName, teamLeader, driver);
                employees.add(e);
                
            }
            
        }finally{
            if (con != null) {
                con.close();
            }
        }
        return employees;
    }

    public void updateEmployee(Employee e) throws SQLException {
        Connection con = null;
        
        try{
            con = getConnection();
            Statement stmnt = con.createStatement();

            int affectedRows = stmnt.executeUpdate("UPDATE Fireman SET "
                    + "firstName = '" + e.getFirstName() + "', "
                    + "lastName = '" + e.getLastName()+ "', "
                    + "teamleader = '" + e.isTeamLeader()+ "', "
                    + "driver = '" + e.isDriver()+ "' "
                    + "WHERE employeeId = " + e.getEmplyeeId() + "; ");
            
            if(affectedRows == 0){
                throw new SQLException("Update failed no affected rows");
            }
            
        }finally {
            if (con != null) {
                con.close();
            }
        }
    }

    public void createNewEmployee(Employee e) throws SQLException {
        Connection con = null;
        
        try{
            con = getConnection();
            Statement stmnt = con.createStatement();
            
            int affectedRows = stmnt.executeUpdate("INSERT INTO Fireman VALUES ("
                    + e.getEmplyeeId() + ", '"
                    + e.getFirstName()+ "', '"
                    + e.getLastName()+ "', '"
                    + e.isTeamLeader()+ "', '"
                    + e.isDriver()+ "');");
            
            if (affectedRows <=0) {
                throw new SQLException("Insert failed no affected rows");
            }
            
        }finally {
            if (con != null) {
                con.close();
            }
        }
    }
}
