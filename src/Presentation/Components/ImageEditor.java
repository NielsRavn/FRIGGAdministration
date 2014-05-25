/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentation.Components;

/* 
 * ColorEditor.java (compiles with releases 1.3 and 1.4) is used by 
 * TableDialogEditDemo.java.
 */

import BE.MyImage;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

public class ImageEditor extends AbstractCellEditor
                         implements TableCellEditor,
			            ActionListener {
    MyImage currentImage;
    JButton button;
    ImageEditorDialog dialog;
    protected static final String EDIT = "edit";

    public ImageEditor(JFrame parent) {
        //Set up the editor (from the table's point of view),
        //which is a button.
        //This button brings up the color chooser dialog,
        //which is the editor from the user's point of view.
        button = new JButton();
        button.setActionCommand(EDIT);
        button.addActionListener(this);
        button.setBorderPainted(false);

        //Set up the dialog that the button brings up.
        dialog = new ImageEditorDialog(parent, true);
    }

    /**
     * Handles events from the editor button and from
     * the dialog's OK button.
     */
    public void actionPerformed(ActionEvent e) {
        if (EDIT.equals(e.getActionCommand())) {
            //The user has clicked the cell, so
            //bring up the dialog.
            button.setText("Choose Image");
            dialog.setImage(currentImage);
            dialog.setVisible(true);

            MyImage image = dialog.getImage(); 
            if(image != null){
                currentImage = image;
            }
            
            //Make the renderer reappear.
            fireEditingStopped();

        }
    }

    //Implement the one CellEditor method that AbstractCellEditor doesn't.
    public Object getCellEditorValue() {
        return currentImage;
    }

    //Implement the one method defined by TableCellEditor.
    public Component getTableCellEditorComponent(JTable table,
                                                 Object value,
                                                 boolean isSelected,
                                                 int row,
                                                 int column) {
        currentImage = (MyImage) value;
        return button;
    }
}
