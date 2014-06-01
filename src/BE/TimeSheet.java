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

    /**
     * creates a new timsheet with all data
     *
     * @param timeSheetID the id of the timesheet
     * @param firemanID the id of the employee in the timesheet
     * @param firstName the firstname of the employee
     * @param lastName the last name of the employee
     * @param startTime the start time and date of this time registration
     * @param endTime the end time and date of this time registration.
     * @param hours how many hours the teamlader has approved.
     * @param approvedByTeamleader the id for the teamleader who approved this
     * time sheet
     * @param hoursApproved how many hours inputted by the comander
     * @param approvedByCoomander whether or not the commander approved the
     * hours
     * @param position the position that the employee was filling.
     */
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

    /**
     * @return the employee id
     */
    public int getEmployeeID() {
        return ID;
    }

    /**
     * @return the firstname of the employee
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @return the last name of the employee
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @return the start time and date of this time sheet
     */
    public Timestamp getStartTime() {
        return startTime;
    }

    /**
     * @return the end time and date of this timesheet
     */
    public Timestamp getEndTime() {
        return endTime;
    }

    /**
     * @return which teamleader it was approved by, 0 if not approved.
     */
    public int getApprovedByTeamleader() {
        return approvedByTeamleader;
    }

    /**
     * @return whether or not it is approed by the comander.
     */
    public boolean getApprovedByCommander() {
        return approvedByCommander;
    }

    /**
     * @return how many hours the teamleader approved.
     */
    public int getHours() {
        return hours;
    }

    /**
     * @return how many hour the comander has written in.
     */
    public int getHoursApproved() {
        return hoursApproved;
    }

    /**
     * @return the position that was filled.
     */
    public String getPosition() {
        return position;
    }

    /**
     * @param position the new position to set.
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * @param approvedByTeamleader the new teamleder id to be set for who
     * approved the teamleader hours.
     */
    public void setApprovedByTeamleader(int approvedByTeamleader) {
        this.approvedByTeamleader = approvedByTeamleader;
    }

    /**
     * @param hours the new amount of hours approved be the teamleader.
     */
    public void setHours(int hours) {
        this.hours = hours;
    }

    /**
     * @param endTime the new end time and date for this timesheet.
     */
    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    /**
     * @param startTime the new start time and date to be set for this
     * timesheet.
     */
    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    /**
     * @param lastName the new last name of the employee to be set.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @param firstName the new first name of the employee to be set.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @param ID the new employee id to be set.
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * @param approvedByCommander whether or nor it is approved by the
     * commander, if true it will be sent on to salary.
     */
    public void setApprovedByCommander(boolean approvedByCommander) {
        this.approvedByCommander = approvedByCommander;
    }

    /**
     * @param hoursApproved set how many hours the comander has written in.
     */
    public void setHoursApproved(int hoursApproved) {
        this.hoursApproved = hoursApproved;
    }

    /**
     * @return whether or not a change has happened in this timesheet used to
     * determine if it should get updated in the database
     */
    public boolean isChange() {
        return change;
    }

    /**
     * @param change set whether or not a change has happend in this timesheet
     */
    public void setChange(boolean change) {
        this.change = change;
    }

    /**
     * @return the id of this timesheet.
     */
    public int getTimeSheetID() {
        return timeSheetID;
    }

}
