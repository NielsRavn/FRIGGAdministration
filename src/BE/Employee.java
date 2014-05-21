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

    public Employee(int emplyeeId, String firstName, String lastName, boolean teamLeader, boolean driver) {
        this.emplyeeId = emplyeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.teamLeader = teamLeader;
        this.driver = driver;
    }

    public Employee() {
        emplyeeId = -1;
        firstName = "";
        lastName = "";
        teamLeader = false;
        driver = false;
    }

    public int getEmplyeeId() {
        return emplyeeId;
    }

    public void setEmplyeeId(int emplyeeId) {
        this.emplyeeId = emplyeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isTeamLeader() {
        return teamLeader;
    }

    public void setTeamLeader(boolean teamLeader) {
        this.teamLeader = teamLeader;
    }

    public boolean isDriver() {
        return driver;
    }

    public void setDriver(boolean driver) {
        this.driver = driver;
    }
    
    
    
}
