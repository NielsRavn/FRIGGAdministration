/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BLL;

import BE.Employee;
import DAL.Employee_Access;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Niels Kristian Ravn
 */
public class Employee_AccessLink {
    
    Employee_Access ea;
    
    public Employee_AccessLink() throws IOException{
        ea = new Employee_Access();
    }

    public ArrayList<Employee> getEmployeesBySearchQuery(String query) throws SQLException {
        return ea.getEmployeesBySearchQuery(query); 
    }

    public void createNewEmployee(Employee e) throws SQLException {
        ea.createNewEmployee(e);
    }
    
}
