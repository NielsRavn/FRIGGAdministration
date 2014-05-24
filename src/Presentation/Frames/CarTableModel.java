/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentation.Frames;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Niels Kristian Ravn
 */
public class CarTableModel extends AbstractTableModel{

    @Override
    public int getRowCount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getColumnCount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        super.setValueAt(aValue, rowIndex, columnIndex); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return super.isCellEditable(rowIndex, columnIndex); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return super.getColumnClass(columnIndex); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getColumnName(int column) {
        return super.getColumnName(column); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
