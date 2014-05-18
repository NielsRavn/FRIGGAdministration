/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentation.Frames;

import BE.TimeSheet;
import java.util.ArrayList;
import java.sql.Timestamp;
import javax.swing.table.AbstractTableModel;


/**
 *
 * @author Susanne
 */
public class TableModelFiremanByCar extends AbstractTableModel{
    ArrayList<TimeSheet> carList;
    String[] colNames = {"Medarb.Nr", "Fornavn", "Efternavn", "Starttid", "Sluttid", "Position"};
    Class[] classes = {Integer.class, String.class, String.class, Timestamp.class, Timestamp.class, String.class};

    @Override
    public int getRowCount() {
        return carList.size();
    }

    @Override
    public int getColumnCount() {
        return colNames.length;    }

  @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return carList.get(rowIndex).getEmployeeID();
            case 1:
                return carList.get(rowIndex).getFirstName();
            case 2:
                return carList.get(rowIndex).getLastName();
            case 3:
                return carList.get(rowIndex).getStartTime();
            case 4:
                return carList.get(rowIndex).getEndTime();
            case 5:
                return carList.get(rowIndex).getPosition();
        }
        return null;
  }
    
}
