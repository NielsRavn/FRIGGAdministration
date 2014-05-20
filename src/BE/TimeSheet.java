/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BE;

import java.sql.Timestamp;

/**
 *
 * @author Susanne
 */
public class TimeSheet {
    private String position;
    private int approvedByTeamleader;
    private int hours;
    private Timestamp endTime;
    private Timestamp startTime;
    private String lastName;
    private String firstName;
    private int ID;
    private boolean approvedByCommander;
    private int hoursApproved;

    public TimeSheet(int firemanID, String firstName, String lastName, Timestamp startTime, Timestamp endTime, int hours, int approvedByTeamleader,int hoursApproved, boolean approvedByCoomander, String position) {
        this.ID = firemanID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.hours = hours;
        this.approvedByTeamleader = approvedByTeamleader;
        this.hoursApproved = hoursApproved;
        this.approvedByCommander = approvedByCoomander;
        this.position = position;
    }

    public int getEmployeeID() {
        return ID;    }

    public String getFirstName() {
        return firstName;    }

    public String getLastName() {
        return lastName;    }

    public Timestamp getStartTime() {
        return startTime;    }

    public Timestamp getEndTime() {
        return endTime;    }
    
    public int getApprovedByTeamleader() {
        return approvedByTeamleader;
    }
    
    public boolean getApprovedByCommander() {
        return approvedByCommander;
    }
    
    public int getHours() {
        return hours;
    }
    
    public int getHoursApproved() {
        return hoursApproved;
    }

    public String getPosition() {
        return position;    }


    
}
