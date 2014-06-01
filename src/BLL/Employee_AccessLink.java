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

    public Employee_AccessLink() throws IOException {
        ea = new Employee_Access();
    }

    /**
     * gets an employee from a search query, firname lastname and employee id
     * will be compared to see if any of them contains some of the string.
     *
     * @param query the query to search for
     * @return an array of employees matching the search query
     * @throws SQLException
     */
    public ArrayList<Employee> getEmployeesBySearchQuery(String query) throws SQLException {
        return ea.getEmployeesBySearchQuery(query);
    }

    /**
     * creates a new employee with all data given.
     *
     * @param e the employee to create in the dtabase.
     * @throws SQLException
     */
    public void createNewEmployee(Employee e) throws SQLException {
        ea.createNewEmployee(e);
    }

    /**
     * deletes an employee from the database
     *
     * @param e the employee to delete, the id of this is used.
     * @throws SQLException
     */
    public void deleteEmployee(Employee e) throws SQLException {
        ea.deleteEmployee(e);
    }

    /**
     * checks if the employee id exists in the database
     *
     * @param id the id to check for
     * @return true if the employee id is NOT in the database, false if it IS in
     * the database.
     * @throws SQLException
     */
    public boolean checkNewEmployeeId(int id) throws SQLException {
        return ea.checkNewEmployeeId(id);
    }

}
