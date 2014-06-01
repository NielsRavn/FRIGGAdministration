/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BE.Message;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Niels Kristian Ravn
 */
public class Message_Access extends DatabaseConnection {

    public Message_Access() throws IOException {
        super();
    }

    /**
     * gets ethier archived or unarchived messages, the archived status is the
     * oposit of the shown value that is saved in the database.
     *
     * @param archived whether to get archived or un archived messages, false to
     * get messages that are shown in the check out program.
     * @return an arraylist of messages.
     * @throws SQLException
     */
    public ArrayList<Message> getMessagesByArchivedStatus(boolean archived) throws SQLException {
        Connection con = null;
        ArrayList<Message> messages = new ArrayList<Message>();

        try {
            con = getConnection();
            Statement stmnt = con.createStatement();

            ResultSet rs = stmnt.executeQuery("SELECT * FROM Message WHERE shown = '" + !archived + "';");

            while (rs.next()) {
                int id = rs.getInt("id");
                String message = rs.getString("message");
                boolean shown = rs.getBoolean("shown");

                Message m = new Message(id, message, shown);
                messages.add(m);
            }

        } finally {
            if (con != null) {
                con.close();
            }
        }

        return messages;
    }

    /**
     * creates a new message to then be loaded from the database.
     *
     * @throws SQLException
     */
    public void createNewMessage() throws SQLException {
        Connection con = null;

        try {
            con = getConnection();
            Statement stmnt = con.createStatement();

            int affectedRows = stmnt.executeUpdate("INSERT INTO Message VALUES('', 1)");

            if (affectedRows == 0) {
                throw new SQLException("No message created, no affected rows");
            }

        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    /**
     * updates all fields of a message
     *
     * @param m the message to update.
     * @throws SQLException
     */
    public void updateMessage(Message m) throws SQLException {
        Connection con = null;

        try {
            con = getConnection();
            Statement stmnt = con.createStatement();

            int affectedRows = stmnt.executeUpdate("UPDATE Message SET "
                    + "message = '" + m.getMessage() + "', "
                    + "shown = '" + m.isShown() + "' "
                    + "WHERE id = " + m.getId() + ";");

            if (affectedRows == 0) {
                throw new SQLException("Updating failed, no rows affected");
            }

        } finally {
            if (con != null) {
                con.close();
            }
        }
    }
}
