/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Frames;

import BE.Car;
import BE.MyImage;
import BLL.Commands.CarUpdateCommand;
import BLL.Commands.CommandStack;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Niels Kristian Ravn
 */
public class CarTableModel extends AbstractTableModel {

    ArrayList<Car> cars;
    //the column names to be displayed above the column
    String[] colNames = {"Bil nr", "Billede", "Navn", "Pladser"};
    // the type of input that is in the column so that the editor and renderer know how to handle it.
    Class[] classes = {Integer.class, ImageIcon.class, String.class, Integer.class};
    CarAdministrationPanel parent;
    CommandStack commandStack;

    /**
     * creates a new model to be used with a table
     *
     * @param parent the adminstration panel, so that the buttons can be updated
     * when new commands are made.
     * @param commandStack the command stack that the commands to set values
     * should be put on.
     */
    public CarTableModel(CarAdministrationPanel parent, CommandStack commandStack) {
        cars = new ArrayList<>();
        this.parent = parent;
        this.commandStack = commandStack;
        fireTableDataChanged();
    }

    /**
     * gets the number of rows to be created
     *
     * @return the number of rows in the table
     */
    @Override
    public int getRowCount() {
        return cars.size();
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
        Car c = cars.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return c.getCarNr();
            case 1:
                return c.getImage();
            case 2:
                return c.getName();
            case 3:
                return c.getSeats();
        }
        return null;
    }

    /**
     * sets the value of a specific cell, also makes and excutes a comand to
     * update the database
     *
     * @param aValue the value to set in
     * @param rowIndex the row of the cell
     * @param columnIndex the column that the cell is in.
     */
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Car c = cars.get(rowIndex);
        Car old = c.getCopyOfCar();
        switch (columnIndex) {
            case 0:
                c.setCarNr((int) aValue);
                break;
            case 1:
                c.setImage((MyImage) aValue);
                break;
            case 2:
                c.setName((String) aValue);
                break;
            case 3:
                c.setSeats((int) aValue);
                break;
        }
        try {
            commandStack.addCommandToStack(new CarUpdateCommand(c, old));
            fireTableDataChanged();
            parent.setButtonsEnabled();
        } catch (IOException | SQLException ex) {
        }
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
     * sets the array of cars to be shown and updates the table.
     *
     * @param cars an array to be shown in the table.
     */
    public void setCars(ArrayList<Car> cars) {
        this.cars = cars;
        fireTableDataChanged();
    }

    /**
     *
     * @return the arrayList of the currently shown data of the table.
     */
    public ArrayList<Car> getCars() {
        return cars;
    }

}
