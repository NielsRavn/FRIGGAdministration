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
public class Employee_Access extends DatabaseConnection {

    public Employee_Access() throws IOException {
        super();
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
        ArrayList<Employee> employees = new ArrayList<>();
        Connection con = null;

        try {
            con = getConnection();
            Statement stmnt = con.createStatement();

            ResultSet rs = stmnt.executeQuery("SELECT * FROM Fireman "
                    + "WHERE firstName LIKE '%" + query + "%' OR lastName LIKE '%" + query + "%' OR employeeId LIKE '%" + query + "%'");

            while (rs.next()) {
                int id = rs.getInt("employeeId");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                boolean teamLeader = rs.getBoolean("teamleader");
                boolean driver = rs.getBoolean("driver");

                Employee e = new Employee(id, firstName, lastName, teamLeader, driver);
                employees.add(e);

            }

        } finally {
            if (con != null) {
                con.close();
            }
        }
        return employees;
    }

    /**
     * updates all data of an employee
     *
     * @param e the employee to update, the data on this should be updated
     * before sending it here.
     * @throws SQLException
     */
    public void updateEmployee(Employee e) throws SQLException {
        Connection con = null;

        try {
            con = getConnection();
            Statement stmnt = con.createStatement();

            int affectedRows = stmnt.executeUpdate("UPDATE Fireman SET "
                    + "firstName = '" + e.getFirstName() + "', "
                    + "lastName = '" + e.getLastName() + "', "
                    + "teamleader = '" + e.isTeamLeader() + "', "
                    + "driver = '" + e.isDriver() + "' "
                    + "WHERE employeeId = " + e.getEmplyeeId() + "; ");

            if (affectedRows == 0) {
                throw new SQLException("Update failed no affected rows");
            }

        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    /**
     * creates a new employee with all data given.
     *
     * @param e the employee to create in the dtabase.
     * @throws SQLException
     */
    public void createNewEmployee(Employee e) throws SQLException {
        Connection con = null;

        try {
            con = getConnection();
            Statement stmnt = con.createStatement();

            int affectedRows = stmnt.executeUpdate("INSERT INTO Fireman VALUES ("
                    + e.getEmplyeeId() + ", '"
                    + e.getFirstName() + "', '"
                    + e.getLastName() + "', '"
                    + e.isTeamLeader() + "', '"
                    + e.isDriver() + "');");

            if (affectedRows <= 0) {
                throw new SQLException("Insert failed no affected rows");
            }

        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    /**
     * deletes an employee from the database
     *
     * @param e the employee to delete, the id of this is used.
     * @throws SQLException
     */
    public void deleteEmployee(Employee e) throws SQLException {
        Connection con = null;

        try {
            con = getConnection();
            Statement stmnt = con.createStatement();

            int affectedRows = stmnt.executeUpdate("DELETE FROM Fireman WHERE employeeId = " + e.getEmplyeeId() + ";");

            if (affectedRows == 0) {
                throw new SQLException("employee was not deleted or was not in the database, no changes are made");
            }

        } finally {
            if (con != null) {
                con.close();
            }
        }
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
        Connection con = null;

        try {
            con = getConnection();
            Statement stmnt = con.createStatement();

            ResultSet rs = stmnt.executeQuery("SELECT employeeId FROM Fireman WHERE employeeId = " + id + ";");

            return !rs.next();

        } finally {
            if (con != null) {
                con.close();
            }
        }
    }
}
