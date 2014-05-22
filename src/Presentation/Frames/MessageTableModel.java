/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentation.Frames;

import BE.Message;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Niels Kristian Ravn
 */
public class MessageTableModel extends AbstractTableModel{

    ArrayList<Message> messages;
    String[] colNames = {"Besked", "Arkiveret"};
    Class[] classes = {String.class, Boolean.class};

    public MessageTableModel(ArrayList<Message> messages) {
        this.messages = messages;
        fireTableDataChanged();
    }

    public MessageTableModel() {
        this(new ArrayList<Message>());
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
                return m.isShown();
        }
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        super.setValueAt(aValue, rowIndex, columnIndex); //To change body of generated methods, choose Tools | Templates.
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
    
    
    
}
