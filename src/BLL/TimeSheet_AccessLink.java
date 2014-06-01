/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import BE.TimeSheet;
import DAL.TimeSheet_Access;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Susanne
 */
public class TimeSheet_AccessLink {

    TimeSheet_Access tsa;

    public TimeSheet_AccessLink() throws IOException {
        tsa = new TimeSheet_Access();
    }

    /**
     * gets all timesheets with the given alarm id and car nr.
     *
     * @param alarmID the alarm id to search for
     * @param carNr the car number to search fro.
     * @return an arraylist of timesheets which meets the given requirements.
     * @throws SQLException
     */
    public ArrayList<TimeSheet> getTimeSheetByCarNrAndAlarmID(int alarmID, int carNr) throws SQLException {
        return tsa.getTimeSheetByCarNrAndAlarmID(alarmID, carNr);
    }

    /**
     * creates a new approval sheet for the timesheets with data from the
     * timesheet and adds it as the approval sheet for the timesheet
     *
     * @param t the timesheets which should have a new approval sheet made, with
     * approved hours, or approved by comander updated.
     * @throws SQLException
     */
    public void updateTimesheet(TimeSheet t) throws SQLException {
        tsa.updateApprovalSheet(t);
    }

}
