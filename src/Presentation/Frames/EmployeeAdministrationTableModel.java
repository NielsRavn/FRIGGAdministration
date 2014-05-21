/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentation.Frames;

import BE.Employee;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Niels
 */
public class EmployeeAdministrationTableModel extends AbstractTableModel{

    ArrayList<Employee> employees;
    String[] colNames = {"First name", "Last name", "Chauffør", "TeamLeader"};
    Class[] classes = {String.class, String.class, boolean.class, boolean.class};
    
    @Override
    public int getRowCount() {
        return employees.size();
    }

    @Override
    public int getColumnCount() {
        return colNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        
        
        return null;
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
        return classes[columnIndex];
    }

    @Override
    public String getColumnName(int column) {
        return colNames[column];
    }
    
    
    
}
