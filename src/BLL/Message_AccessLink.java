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
    
    
    public Message_AccessLink() throws IOException{
        ma = new Message_Access();
    }

    public ArrayList<Message> getMessagesByArchivedStatus(boolean archived) throws SQLException {
        return ma.getMessagesByArchivedStatus(archived);
    }

    public void createNewMessage() throws SQLException {
        ma.createNewMessage();
    }

    public void updateMessage(Message m) throws SQLException {
        ma.updateMessage(m);
    }
}
