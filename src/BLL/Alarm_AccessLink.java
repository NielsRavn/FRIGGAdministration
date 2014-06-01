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

    /**
     * returns alarm picked by date(s) and if its approved
     *
     * @param p the period object of the date picker
     * @param approved whether or not the alarms may be approved
     * @return an array of alarms.
     * @throws SQLException
     */
    public ArrayList<Alarm> getAlarmsByPeriodAndAccepted(Period p, Boolean approved) throws SQLException {
        return aa.getAlarmsByPeriodAndAccepted(p, approved);
    }

    /**
     * aget an array of car numbers that has been on an alarm.
     *
     * @param ID the id to search for
     * @return an array of integers representing the car numbers.
     * @throws SQLException
     */
    public ArrayList<Integer> getCarNrByAlarmID(int ID) throws SQLException {
        return aa.getCarNrByAlarmID(ID);
    }

    /**
     * updates the accepted status of an alarm
     *
     * @param a the alarm, to get the id and accepted status from.
     * @throws SQLException
     */
    public void updateAlarm(Alarm a) throws SQLException {
        aa.updateAlarm(a);
    }
}
