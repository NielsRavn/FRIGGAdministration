/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Frames;

import BE.Car;
import BE.MyImage;
import BLL.Car_AccessLink;
import BLL.Commands.CarCreateCommand;
import BLL.Commands.CommandStack;
import Presentation.Components.ImageEditor;
import Presentation.MyConstants;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Niels
 */
public class CarAdministrationPanel extends javax.swing.JPanel {

    CarTableModel ctm;
    CommandStack commandStack;
    Car_AccessLink cal;
    RowSorter<CarTableModel> rowSorter;
    JFrame parent;

    /**
     * Creates new form VehicleAdministrationPanel
     */
    public CarAdministrationPanel(JFrame parent) {
        this.parent = parent;
        commandStack = new CommandStack();
        initComponents();
        try {
            cal = new Car_AccessLink();
        } catch (IOException ex) {
        }
        tblCar.getTableHeader().setReorderingAllowed(false);
        ctm = new CarTableModel(this, commandStack);
        rowSorter = new TableRowSorter<>(ctm);
        tblCar.setModel(ctm);
        tblCar.setRowSorter(rowSorter);
        tblCar.setDefaultEditor(ImageIcon.class, new ImageEditor(parent));
        search();
        addKeyListeners();
        addCellRenderes();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tfSearch = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        btnBack = new javax.swing.JButton();
        btnForward = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        btnNewCar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCar = new javax.swing.JTable();

        setLayout(new java.awt.BorderLayout());

        jLabel1.setText("Søge tekst:");
        jPanel1.add(jLabel1);

        tfSearch.setPreferredSize(new java.awt.Dimension(150, 20));
        tfSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfSearchKeyReleased(evt);
            }
        });
        jPanel1.add(tfSearch);

        add(jPanel1, java.awt.BorderLayout.NORTH);

        jPanel2.setLayout(new java.awt.BorderLayout());

        btnBack.setText("<--");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        jPanel3.add(btnBack);

        btnForward.setText("-->");
        btnForward.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnForwardActionPerformed(evt);
            }
        });
        jPanel3.add(btnForward);

        jPanel2.add(jPanel3, java.awt.BorderLayout.WEST);

        btnNewCar.setText("Ny bil");
        btnNewCar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewCarActionPerformed(evt);
            }
        });
        jPanel4.add(btnNewCar);

        jPanel2.add(jPanel4, java.awt.BorderLayout.EAST);

        add(jPanel2, java.awt.BorderLayout.SOUTH);

        tblCar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblCar);

        add(jScrollPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void tfSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfSearchKeyReleased
        search();
    }//GEN-LAST:event_tfSearchKeyReleased

    private void btnNewCarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewCarActionPerformed
        newCar();
    }//GEN-LAST:event_btnNewCarActionPerformed

    private void btnForwardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnForwardActionPerformed
        forward();
    }//GEN-LAST:event_btnForwardActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        backwards();
    }//GEN-LAST:event_btnBackActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnForward;
    private javax.swing.JButton btnNewCar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblCar;
    private javax.swing.JTextField tfSearch;
    // End of variables declaration//GEN-END:variables

    /**
     * uses the search query text to search for data in the database,
     * and sets the data in the table, plus updates the back and fourth buttons.
     */
    public void search() {
        String query = tfSearch.getText();
        ArrayList<Car> employees = new ArrayList<>();
        try {
            employees = cal.getCarsBySearchQuery(query);
        } catch (SQLException ex) {
        }
        ctm.setCars(employees);
        setButtonsEnabled();
    }

    /**
     * creates a new dialog to ask for the nr of a new car to be made.
     * if the return of the dialog is not null, a command to create teh new car
     * is created and executed.
     */
    private void newCar() {
        NewCarDialog ncd = new NewCarDialog(parent, true);
        Car c = ncd.getCar();
        if (c != null) {
            try {
                commandStack.addCommandToStack(new CarCreateCommand(c.getCopyOfCar()));
            } catch (SQLException | IOException ex) {
            }
            ctm.getCars().add(c);
            ctm.fireTableDataChanged();
        }
        setButtonsEnabled();
    }

    /**
     * goes backwards in the commandstack, reversing the actions done.
     */
    private void backwards() {
        if (commandStack.canGoBackwards()) {
            try {
                commandStack.goBack();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            search();
        }
    }

    /**
     * sets the enabled status of the back and forward buttons so that they are only active
     * when they can be used.
     */
    public void setButtonsEnabled() {
        btnForward.setEnabled(commandStack.canGoForward());
        btnBack.setEnabled(commandStack.canGoBackwards());
    }

    /**
     * goes forward in the command stack, redoing the action that has previosly been undone. 
     */
    private void forward() {
        if (commandStack.canGoForward()) {
            try {
                commandStack.goForward();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            search();
        }
    }

    /**
     * adds keylistener to just about every focus able object,
     * so that ctrl-z and ctrl-y always can be used to go back and forward.
     */
    private void addKeyListeners() {
        MyUndoAndRedoKeyListener urk = new MyUndoAndRedoKeyListener();
        addKeyListener(urk);
        tblCar.addKeyListener(urk);
        btnBack.addKeyListener(urk);
        btnForward.addKeyListener(urk);
        btnNewCar.addKeyListener(urk);
        tfSearch.addKeyListener(urk);
    }

    /**
     * my keylistener to listen for ctrl-z and ctrl-y keyboard actions.
     */
    private class MyUndoAndRedoKeyListener extends KeyAdapter {

        boolean controlHold = false;

        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
                controlHold = false;
            }
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
                controlHold = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_Z && controlHold) {
                backwards();
            } else if (e.getKeyCode() == KeyEvent.VK_Y && controlHold) {
                forward();
            }
        }
    }

    /**
     * adds a cell renderer to the image column to render it corectly
     */
    private void addCellRenderes() {
        TableColumn imageCol = tblCar.getColumnModel().getColumn(1);
        imageCol.setCellRenderer(new MyImageCellRenderer());
    }

    /**
     * my cell render to resize and render images on screen.
     */
    private class MyImageCellRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            MyImage image = (MyImage) value;
            Image img = image.getImage().getImage();
            BufferedImage bi = new BufferedImage(150, 100, BufferedImage.TYPE_INT_ARGB);
            Graphics g = bi.createGraphics();
            g.drawImage(img, 0, 0, 150, 100, null);
            ImageIcon newIcon = new ImageIcon(bi);
            table.setRowHeight(row, 100);
            JLabel lbl = new JLabel();
            if (isSelected) {
                lbl.setBackground(MyConstants.COLOR_LIGHT_BLUE);
            }
            lbl.setIcon(newIcon);
            return lbl;
        }
    }
}
