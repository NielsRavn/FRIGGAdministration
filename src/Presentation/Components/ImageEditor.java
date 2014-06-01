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

    /**
     * creates a new imageEditor with a button to be put in to the table cell
     *
     * @param parent the jframe, for giving the dialog.
     */
    public ImageEditor(JFrame parent) {

        button = new JButton();
        button.setActionCommand(EDIT);
        button.addActionListener(this);
        button.setBorderPainted(false);
        dialog = new ImageEditorDialog(parent, true);
    }

    /**
     * handles event on the cell to edit.
     */
    public void actionPerformed(ActionEvent e) {
        if (EDIT.equals(e.getActionCommand())) {
            //The user has clicked the cell, so
            //bring up the dialog.
            button.setText("Choose Image");
            dialog.setImage(currentImage);
            dialog.setVisible(true);

            MyImage image = dialog.getImage();
            if (image != null) {
                currentImage = image;
            }

            //Make the renderer reappear.
            fireEditingStopped();

        }
    }

    /**
     * called by the table when the editing stops.
     *
     * @return the value saves as the current image.
     */
    @Override
    public Object getCellEditorValue() {
        return currentImage;
    }

    /**
     * method from cell editor, returns a button to be shown in the table when
     * it is to be edited.
     */
    @Override
    public Component getTableCellEditorComponent(JTable table,
            Object value,
            boolean isSelected,
            int row,
            int column) {
        currentImage = (MyImage) value;
        return button;
    }
}
