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
public class CarTableModel extends AbstractTableModel{

    ArrayList<Car> cars;
    String[] colNames = {"Bil nr", "Billede", "Navn", "Pladser"};
    Class[] classes = {Integer.class, ImageIcon.class, String.class, Integer.class};
    CarAdministrationPanel parent;
    CommandStack commandStack;
    
    public CarTableModel(CarAdministrationPanel parent, CommandStack commandStack){
        cars = new ArrayList<>();
        this.parent = parent;
        this.commandStack = commandStack;
        fireTableDataChanged();
    }
    
    @Override
    public int getRowCount() {
        return cars.size();
    }

    @Override
    public int getColumnCount() {
        return colNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Car c = cars.get(rowIndex);
        switch (columnIndex){
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

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Car c = cars.get(rowIndex);
        Car old = c.getCopyOfCar();
        switch (columnIndex){
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
            parent.search();
        } catch (IOException | SQLException ex) {
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if(columnIndex == 0) return false;
//        else if(columnIndex == 1) {
//            System.out.println("this could maybe work");
//            //return false;
//        }
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

    public void setCars(ArrayList<Car> cars) {
        this.cars = cars;
        fireTableDataChanged();
    }

    public ArrayList<Car> getCars() {
        return cars;
    }
    
    
    
}
