/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import BE.Message;
import DAL.Message_Access;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Niels Kristian Ravn
 */
public class Message_AccessLink {

    Message_Access ma;

    public Message_AccessLink() throws IOException {
        ma = new Message_Access();
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
        return ma.getMessagesByArchivedStatus(archived);
    }

    /**
     * creates a new message to then be loaded from the database.
     *
     * @throws SQLException
     */
    public void createNewMessage() throws SQLException {
        ma.createNewMessage();
    }

    /**
     * updates all fields of a message
     *
     * @param m the message to update.
     * @throws SQLException
     */
    public void updateMessage(Message m) throws SQLException {
        ma.updateMessage(m);
    }
}
