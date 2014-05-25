/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentation.Components;

import BE.MyImage;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 *
 * @author Niels Kristian Ravn
 */
public class ImageEditorDialog extends javax.swing.JDialog {

    MyImage image;
    JFileChooser fileChooser;
    JFrame parent;
    
    /**
     * Creates new form ImageEditorDialog
     */
    public ImageEditorDialog(JFrame parent, boolean modal) {
        super(parent, modal);
        this.parent = parent;
        initComponents();
        fileChooser = new JFileChooser();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblImage = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        tfPath = new javax.swing.JTextField();
        btnBrowse = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        btnApprove = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().add(lblImage, java.awt.BorderLayout.EAST);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        tfPath.setPreferredSize(new java.awt.Dimension(200, 20));
        tfPath.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfPathKeyReleased(evt);
            }
        });
        jPanel1.add(tfPath);

        btnBrowse.setText("gennemse");
        btnBrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBrowseActionPerformed(evt);
            }
        });
        jPanel1.add(btnBrowse);

        jPanel2.add(jPanel1, new java.awt.GridBagConstraints());

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        btnApprove.setText("Godkend");
        btnApprove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApproveActionPerformed(evt);
            }
        });
        jPanel3.add(btnApprove);

        btnCancel.setText("Annuller");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        jPanel3.add(btnCancel);

        getContentPane().add(jPanel3, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBrowseActionPerformed
        chooseFile();
    }//GEN-LAST:event_btnBrowseActionPerformed

    private void btnApproveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApproveActionPerformed
        dispose();
    }//GEN-LAST:event_btnApproveActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        image = null;
        dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void tfPathKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfPathKeyReleased
        setImage();
    }//GEN-LAST:event_tfPathKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnApprove;
    private javax.swing.JButton btnBrowse;
    private javax.swing.JButton btnCancel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblImage;
    private javax.swing.JTextField tfPath;
    // End of variables declaration//GEN-END:variables

    public void setImage(MyImage currentImage) {
        image = currentImage;
        tfPath.setText(image.getPath());
        lblImage.setIcon(image.getImage());
        validate();
        repaint();
    }

    public MyImage getImage() {
        return image;
    }

    private void setImage() {
        String path = tfPath.getText();
        setImage(new MyImage(path));
    }

    private void chooseFile() {
        fileChooser.setCurrentDirectory(new File(image.getPath()));
        int result = fileChooser.showOpenDialog(parent);
        
        if(result == JFileChooser.APPROVE_OPTION){
            File f = fileChooser.getSelectedFile();
            setImage(new MyImage(f.getPath()));
        }
    }
}
