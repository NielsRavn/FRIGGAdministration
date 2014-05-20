/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BE.TimeSheet;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Susanne
 */
public class TimeSheet_Access extends DatabaseConnection {

    public TimeSheet_Access() throws IOException {

    }

    public ArrayList<TimeSheet> getTimeSheetByCarNrAndAlarmID(int alarmID, int carNr) throws SQLException {
        ArrayList<TimeSheet> result = new ArrayList<>();
        Connection con = null;
        ResultSet rs = null;

        con = getConnection();
        Statement stmnt = con.createStatement();
        try {
            if (carNr != 0) {
               
                rs = stmnt.executeQuery("select empoyeeId, firstname, lastName, startTime, endTime,tml.hours as tmlHours, sal.hours as salHours, tml.firemanId, sal.approved, name\n"
                        + "from TimeSheet\n"
                        + "inner join Position\n"
                        + "on TimeSheet.positionId = Position.id\n"
                        + "inner join Fireman\n"
                        + "on TimeSheet.empoyeeId = Fireman.employeeId\n"
                        + "left join ApprovalSheet as tml\n"
                        + "on TimeSheet.acceptedByTeamleader = tml.id\n"
                        + "left join ApprovalSheet as sal\n"
                        + "on TimeSheet.acceptedForSalary = sal.id\n"
                        + "where alarmId = " + alarmID + " and carNr = " + carNr
                );
               

            } else {
                rs = stmnt.executeQuery("select empoyeeId, firstname, lastName, startTime, endTime, tml.hours as tmlHours, sal.hours as salHours, tml.firemanId, sal.approved, name\n"
                        + "from TimeSheet\n"
                        + "inner join Position\n"
                        + "on TimeSheet.positionId = Position.id\n"
                        + "inner join Fireman\n"
                        + "on TimeSheet.empoyeeId = Fireman.employeeId\n"
                        + "left join ApprovalSheet as tml\n"
                        + "on TimeSheet.acceptedByTeamleader = tml.id\n"
                        + "left join ApprovalSheet as sal\n"
                        + "on TimeSheet.acceptedForSalary = sal.id\n"
                        + "where alarmId = " + alarmID + " and carNr is NULL"
                );
            }
            while (rs.next()) {
                TimeSheet timeSheet = new TimeSheet(rs.getInt("empoyeeId"), rs.getString("firstname"), rs.getString("lastName"), rs.getTimestamp("startTime"), rs.getTimestamp("endTime"), rs.getInt("tmlHours"), rs.getInt("salHours"), rs.getInt("firemanID"), rs.getBoolean("approved"), rs.getString("name"));
                result.add(timeSheet);
            }
        } finally {
            if (con != null) {
                con.close();
            }
            return result;
        }
    }
}
