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
public class EmployeeAdministrationTableModel extends AbstractTableModel {

    CommandStack commandStack;
    EmployeeAdministrationPanel parent;
    ArrayList<Employee> employees;
    //the column names to be displayed above the column
    String[] colNames = {"Login id", "Fornavn", "Efternavn", "Chauffør", "Holdleder"};
    // the type of input that is in the column so that the editor and renderer know how to handle it.
    Class[] classes = {Integer.class, String.class, String.class, Boolean.class, Boolean.class};

    public EmployeeAdministrationTableModel() {
        employees = new ArrayList<>();
        fireTableDataChanged();

    }

    /**
     * creates a new model to be used with a table
     *
     * @param parent the adminstration panel, so that the buttons can be updated
     * when new commands are made.
     * @param commandStack the command stack that the commands to set values
     * should be put on.
     */
    EmployeeAdministrationTableModel(EmployeeAdministrationPanel parent, CommandStack commandStack) {
        this();
        this.parent = parent;
        this.commandStack = commandStack;
    }

    /**
     * gets the number of rows to be created
     *
     * @return the number of rows in the table
     */
    @Override
    public int getRowCount() {
        return employees.size();
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
        Employee e = employees.get(rowIndex);
        switch (columnIndex) {
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

    /**
     * sets the value of a specific cell, also makes and excutes a comand to
     * update the database
     *
     * @param value the value to set in
     * @param rowIndex the row of the cell
     * @param columnIndex the column that the cell is in.
     */
    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        Employee e = employees.get(rowIndex);
        Employee old = e.getCopyOfEmployee();
        switch (columnIndex) {
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

    /**
     * tells wether or not a cell should be able to be eddited.
     *
     * @param rowIndex the row of the cell
     * @param columnIndex the column of the cell
     * @return true if the cell can be edited and false otherwise. here it is
     * only the first cell that cannot be edited.
     */
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex != 0;
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
     * sets the array of employees to be shown and updates the table.
     *
     * @param employees an array to be shown in the table.
     */
    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
        fireTableDataChanged();
    }

    /**
     * @return the arrayList of the currently shown data of the table.
     */
    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    /**
     * clears the table putting in an empty arraylist.
     */
    public void clearList() {
        employees = new ArrayList<>();
        fireTableDataChanged();
    }

    /**
     * sets a specific rows employee to a new one.
     *
     * @param e the new employee
     * @param row the row of the one to be shifted.
     */
    public void setEmployeeAtRow(Employee e, int row) {
        employees.set(row, e);
        fireTableDataChanged();
    }
}
