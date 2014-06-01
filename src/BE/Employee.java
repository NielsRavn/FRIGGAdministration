/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BE;

/**
 *
 * @author Niels
 */
public class Employee {

    int emplyeeId;
    String firstName, lastName;
    boolean teamLeader, driver;

    /**
     * ceates a new employee with all info, for loading data from the database.
     *
     * @param emplyeeId the id of the employee
     * @param firstName the first name of the employee
     * @param lastName the last name of the employee
     * @param teamLeader whether or not the employee can be a teamleader
     * @param driver whether or not the employee can be a driver of a car.
     */
    public Employee(int emplyeeId, String firstName, String lastName, boolean teamLeader, boolean driver) {
        this.emplyeeId = emplyeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.teamLeader = teamLeader;
        this.driver = driver;
    }

    /**
     * creates a new employee with the given employee id, everything else is set
     * to an empty string and false. used for creating new employees in the
     * database.
     *
     * @param emplyeeId the employee id of the employee
     */
    public Employee(int emplyeeId) {
        this.emplyeeId = emplyeeId;
        firstName = "";
        lastName = "";
        teamLeader = false;
        driver = false;
    }

    /**
     * @return the employee id
     */
    public int getEmplyeeId() {
        return emplyeeId;
    }

    /**
     * @param emplyeeId the employee id to set
     */
    public void setEmplyeeId(int emplyeeId) {
        this.emplyeeId = emplyeeId;
    }

    /**
     * @return the first name of the employee
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the new firstname to set.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the last name of the employee
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the new lastname to set.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return whether or not the employee can be a teamleader
     */
    public boolean isTeamLeader() {
        return teamLeader;
    }

    /**
     * @param teamLeader the new teamleader status to set, if true this employee
     * can be a teamleader.
     */
    public void setTeamLeader(boolean teamLeader) {
        this.teamLeader = teamLeader;
    }

    /**
     * @return whether or not this employee can be a driver
     */
    public boolean isDriver() {
        return driver;
    }

    /**
     * @param driver the new driver value to set, if true this employee can be a
     * driver.
     */
    public void setDriver(boolean driver) {
        this.driver = driver;
    }

    /**
     * @return a new employee with the same data as this one
     */
    public Employee getCopyOfEmployee() {
        Employee e = new Employee(emplyeeId, firstName, lastName, teamLeader, driver);
        return e;
    }

}
