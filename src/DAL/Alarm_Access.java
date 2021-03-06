/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BE.Alarm;
import datechooser.model.multiple.Period;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author Susanne
 */
public class Alarm_Access extends DatabaseConnection {

    public Alarm_Access() throws IOException {

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
        ArrayList<Alarm> result = new ArrayList<>();
        String start = "" + p.getStartDate().get(Calendar.YEAR) + "-" + (p.getStartDate().get(Calendar.MONTH) + 1) + "-" + p.getStartDate().get(Calendar.DAY_OF_MONTH);
        String end = "" + p.getEndDate().get(Calendar.YEAR) + "-" + (p.getEndDate().get(Calendar.MONTH) + 1) + "-" + p.getEndDate().get(Calendar.DAY_OF_MONTH);
        Boolean app = approved;
        Connection con = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            Statement stmnt = con.createStatement();
            if (app) {
                rs = stmnt.executeQuery("select *\n"
                        + "from Alarm\n"
                        + "where time > '" + start + " 00:00:00.001' and time < '" + end + " 23:59:59:999'");
            } else {
                rs = stmnt.executeQuery("select *\n"
                        + "from Alarm\n"
                        + "where time > '" + start + " 00:00:00.001' and time < '" + end + " 23:59:59:999' and accepted = 'false'");
            }
            while (rs.next()) {
                Alarm alarm = new Alarm(rs.getInt("id"), rs.getInt("odinNr"), rs.getString("destination"), rs.getString("type"), rs.getTimestamp("time"), rs.getBoolean("accepted"), rs.getBoolean("exercise"));
                result.add(alarm);
            }
        } finally {
            if (con != null) {
                con.close();
            }
        }
        return result;
    }

    /**
     * aget an array of car numbers that has been on an alarm.
     *
     * @param ID the id to search for
     * @return an array of integers representing the car numbers.
     * @throws SQLException
     */
    public ArrayList<Integer> getCarNrByAlarmID(int ID) throws SQLException {
        ArrayList<Integer> result = new ArrayList<>();
        Connection con = null;
        ResultSet rs = null;
        try {
            con = getConnection();
            Statement stmnt = con.createStatement();
            rs = stmnt.executeQuery("select distinct carNr\n"
                    + "from TimeSheet\n"
                    + "where alarmId = " + ID);
            while (rs.next()) {
                Integer car = rs.getInt("carNr");
                result.add(car);
            }

        } finally {
            if (con != null) {
                con.close();
            }
        }
        return result;
    }

    /**
     * updates the accepted status of an alarm
     *
     * @param a the alarm, to get the id and accepted status from.
     * @throws SQLException
     */
    public void updateAlarm(Alarm a) throws SQLException {
        Connection con = null;
        ResultSet rs = null;
        try {
            con = getConnection();
            Statement stmnt = con.createStatement();

            stmnt.executeUpdate("update Alarm\n"
                    + "set accepted = '" + a.isAccepted() + "'\n"
                    + "where id = " + a.getID());
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }
}
