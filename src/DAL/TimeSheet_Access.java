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
import java.sql.Timestamp;
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
                int employeeID = rs.getInt("empoyeeId");
                String firstName = rs.getString("firstname");
                String lastName = rs.getString("lastName");
                Timestamp startTime = rs.getTimestamp("startTime");
                Timestamp endTime = rs.getTimestamp("endTime");
                int tmlHours = rs.getInt("tmlHours");
                int salHours = rs.getInt("salHours");
                int hl = rs.getInt("firemanID");
                boolean approved = rs.getBoolean("approved");
                String position = rs.getString("name");
                
                if(salHours == 0) salHours = tmlHours;
                
                TimeSheet timeSheet = new TimeSheet(employeeID, firstName, lastName, startTime, endTime, tmlHours, hl, salHours, approved, position);
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