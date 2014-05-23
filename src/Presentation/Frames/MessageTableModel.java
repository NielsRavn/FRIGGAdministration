/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentation.Frames;

import BE.Message;
import BLL.Message_AccessLink;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Niels Kristian Ravn
 */
public class MessageTableModel extends AbstractTableModel{

    ArrayList<Message> messages;
    String[] colNames = {"Besked", "Arkiver"};
    Class[] classes = {String.class, Boolean.class};
    Message_AccessLink mal;
    MessagePanel parent;

    public MessageTableModel(ArrayList<Message> messages) {
        try {
            mal = new Message_AccessLink();
        } catch (IOException ex) {
        }
        this.messages = messages;
        fireTableDataChanged();
    }

    public MessageTableModel(MessagePanel parent) {
        this(new ArrayList<Message>());
        this.parent = parent;
    }
    
    @Override
    public int getRowCount() {
        return messages.size();
    }

    @Override
    public int getColumnCount() {
        return colNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Message m = messages.get(rowIndex);
        switch (columnIndex){
            case 0:
                return m.getMessage();
            case 1:
                return !m.isShown();
        }
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Message m = messages.get(rowIndex);
        switch (columnIndex){
            case 0:
                m.setMessage((String)aValue);
                break;
            case 1:
                m.setShown(!(boolean) aValue);
                break;
        }
        try {
            mal.updateMessage(m);
        } catch (SQLException ex) {
            Logger.getLogger(MessageTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        parent.search();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return classes[columnIndex];
    }

    @Override
    public String getColumnName(int column) {
        return colNames[column];
    }

    void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
        fireTableDataChanged();
    }
    
    
    
}
