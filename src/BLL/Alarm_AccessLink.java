/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BLL;

import BE.Alarm;
import DAL.Alarm_Access;
import datechooser.model.multiple.Period;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Susanne
 */
public class Alarm_AccessLink {
    Alarm_Access aa;
    
    public Alarm_AccessLink() throws IOException {
        aa = new Alarm_Access();
    }
    
    public Alarm getAlarmByDate (int date) {
        return aa.getAlarmByDate(date);
    }

    public ArrayList<Alarm> getAlarmsByPeriodAndAccepted(Period p, Boolean approved) throws SQLException {
        return aa.getAlarmsByPeriodAndAccepted(p,approved);
    }
    
    public ArrayList<Integer> getCarNrByAlarmID (int ID) throws SQLException {
        return aa.getCarNrByAlarmID (ID);
    }

    public void updateAlarm(Alarm a) throws SQLException {
        aa.updateAlarm(a);    }
}
