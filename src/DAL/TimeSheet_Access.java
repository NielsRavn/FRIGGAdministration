/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BE.TimeSheet;
import java.io.IOException;
import java.util.ArrayList;
import java.sql.Connection;

/**
 *
 * @author Susanne
 */
public class TimeSheet_Access extends DatabaseConnection {

    public TimeSheet_Access () throws IOException{
        
    }
    public ArrayList<TimeSheet> getTimeSheetByCarNrAndAlarmID(int alarmID, int carNr) {
        ArrayList<TimeSheet> result = new ArrayList<>();
        Connection con = null;
        
        
        return result;
    }
}
