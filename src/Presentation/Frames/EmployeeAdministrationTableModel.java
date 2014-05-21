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
    Class[] classes = {String.class, String.class, Boolean.class, Boolean.class};
    
    public EmployeeAdministrationTableModel(){
        employees = new ArrayList<>();
        fireTableDataChanged();
        
    }
    
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
        Employee e = employees.get(rowIndex);
        switch (columnIndex){
            case 0:
                return e.getFirstName();
            case 1:
                return e.getLastName();
            case 2: 
                return e.isDriver();
            case 3:
                return e.isTeamLeader();
        }
        return null;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        Employee e = employees.get(rowIndex);
        System.out.println("setting value");
        switch (columnIndex){
            case 0:
                 e.setFirstName((String) value);
                break;
            case 1:
                e.setLastName((String) value);
                break;
            case 2: 
                e.setDriver((boolean) value);
                break;
            case 3:
                e.setTeamLeader((boolean) value);
                break;
        }
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
    
    public void setEmployees(ArrayList<Employee> employees){
        this.employees = employees;
        fireTableDataChanged();
    }
    
    public ArrayList<Employee> getEmployees(){
        return employees;
    }
    
    public void clearList(){
        employees = new ArrayList<>();
        fireTableDataChanged();
    }
    
}
