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
    
    
    public ArrayList<TimeSheet> getTimeSheetByCarNrAndAlarmID(int alarmID, int carNr) throws SQLException {
        return tsa.getTimeSheetByCarNrAndAlarmID(alarmID, carNr);
    }

    public void updateTimesheet(TimeSheet t) throws SQLException {
        tsa.updateTimesheet(t);    }


}
