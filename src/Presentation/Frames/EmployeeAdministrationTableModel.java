/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentation.Frames;

import BE.Employee;
import BLL.Commands.CommandStack;
import BLL.Commands.EmployeeUpdateCommand;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Niels
 */
public class EmployeeAdministrationTableModel extends AbstractTableModel{

    CommandStack commandStack;
    EmployeeAdministrationPanel parent;
    ArrayList<Employee> employees;
    String[] colNames = {"Login id","Fornavn", "Efternavn", "Chauffør", "Holdleder"};
    Class[] classes = {Integer.class, String.class, String.class, Boolean.class, Boolean.class};
    
    public EmployeeAdministrationTableModel(){
        employees = new ArrayList<>();
        fireTableDataChanged();
        
    }

    EmployeeAdministrationTableModel(EmployeeAdministrationPanel parent, CommandStack commandStack) {
        this();
        this.parent = parent;
        this.commandStack = commandStack;
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
                return e.getEmplyeeId();
            case 1:
                return e.getFirstName();
            case 2:
                return e.getLastName();
            case 3: 
                return e.isDriver();
            case 4:
                return e.isTeamLeader();
        }
        return null;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        Employee e = employees.get(rowIndex);
        Employee old = e.getCopyOfEmployee();
        switch (columnIndex){
            case 1:
                 e.setFirstName((String) value);
                break;
            case 2:
                e.setLastName((String) value);
                break;
            case 3: 
                e.setDriver((boolean) value);
                break;
            case 4:
                e.setTeamLeader((boolean) value);
                break;
        }
        try {
            commandStack.addCommandToStack(new EmployeeUpdateCommand(e, old));
            fireTableDataChanged();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        parent.setButtonsEnabled();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if(columnIndex == 0) return false;
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
    
    public void setEmployeeAtRow(Employee e, int row){
        employees.set(row, e);
        fireTableDataChanged();
    }
}
