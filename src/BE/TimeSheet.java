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
    private int timeSheetID;
    private boolean change = false;

    public TimeSheet(int timeSheetID, int firemanID, String firstName, String lastName, Timestamp startTime, Timestamp endTime, int hours, int approvedByTeamleader, int hoursApproved, boolean approvedByCoomander, String position) {
        this.timeSheetID = timeSheetID;
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
        return ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

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
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setApprovedByTeamleader(int approvedByTeamleader) {
        this.approvedByTeamleader = approvedByTeamleader;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setApprovedByCommander(boolean approvedByCommander) {
        this.approvedByCommander = approvedByCommander;
    }

    public void setHoursApproved(int hoursApproved) {
        this.hoursApproved = hoursApproved;
    }

    public boolean isChange() {
        return change;
    }

    public void setChange(boolean change) {
        this.change = change;
    }

    public int getTimeSheetID() {
        return timeSheetID;
    }

}
