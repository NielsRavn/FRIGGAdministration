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
public class MessageTableModel extends AbstractTableModel {

    ArrayList<Message> messages;
    //the column names to be displayed above the column
    String[] colNames = {"Besked", "Arkiver"};
    // the type of input that is in the column so that the editor and renderer know how to handle it.
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

    /**
     * creates a new model for a table, with the parent, to make it search again
     * when somthing is changed.
     *
     * @param parent the parent message panel.
     */
    public MessageTableModel(MessagePanel parent) {
        this(new ArrayList<Message>());
        this.parent = parent;
    }

    /**
     * gets the number of rows to be created
     *
     * @return the number of rows in the table
     */
    @Override
    public int getRowCount() {
        return messages.size();
    }

    /**
     *
     * @return the number of coulms to be shown
     */
    @Override
    public int getColumnCount() {
        return colNames.length;
    }

    /**
     * returns the value in a specific cell
     *
     * @param rowIndex the row of the cell
     * @param columnIndex the column of the cell
     * @return the value in the cell
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Message m = messages.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return m.getMessage();
            case 1:
                return !m.isShown();
        }
        return null;
    }

    /**
     * sets the value of a specific cell, also updates the database
     *
     * @param aValue the value to set in
     * @param rowIndex the row of the cell
     * @param columnIndex the column that the cell is in.
     */
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Message m = messages.get(rowIndex);
        switch (columnIndex) {
            case 0:
                m.setMessage((String) aValue);
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

    /**
     * tells wether or not a cell should be able to be eddited.
     *
     * @param rowIndex the row of the cell
     * @param columnIndex the column of the cell
     * @return true if the cell can be edited and false otherwise.
     */
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    /**
     * gets the class of the objects to be shown in a column
     *
     * @param columnIndex the column to look in
     * @return the class that the values to be shown in the column goes under.
     */
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return classes[columnIndex];
    }

    /**
     * gets the column name to be shown over the columns
     *
     * @param column the column to get the name for
     * @return the name of the column
     */
    @Override
    public String getColumnName(int column) {
        return colNames[column];
    }

    /**
     * sets the array of messages to be shown and updates the table.
     *
     * @param messages an array to be shown in the table.
     */
    void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
        fireTableDataChanged();
    }

}
