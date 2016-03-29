/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kafe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.*;
import static kafe.Koneksi.driver;
import static kafe.Koneksi.pass;
import static kafe.Koneksi.url;
import static kafe.Koneksi.user;

/**
 *
 * @author Furqan
 */
public class MakananFrame extends javax.swing.JFrame {

    /**
     * Creates new form TambahMakananFrame
     */
    public MakananFrame() {
        initComponents();
        ambilMakanan();
    }

    private void ambilMakanan() {
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, user, pass);
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM makanan";
            ResultSet rs = statement.executeQuery(sql);

            jTable1.getSelectionModel().addListSelectionListener(
                    new ListSelectionListener() {
                public void valueChanged(ListSelectionEvent event) {
                    int viewRow = jTable1.getSelectedRow();
                    if (viewRow < 0) {
                        //Selection got filtered away.
                    } else {
                        txt_id.setText("" + jTable1.getValueAt(viewRow, 0));
                        txt_nama.setText("" + jTable1.getValueAt(viewRow, 1));
                        txt_harga.setText("" + jTable1.getValueAt(viewRow, 2));
                        
                        jButton1.setText("Update Makanan");
                        jButton3.setEnabled(true);
                    }
                }
            }
            );

            jTable1.setModel(new TableMakananModel1(rs));

            pack();

            rs.close();
            statement.close();
            connection.close();
        } catch (Exception DBException) {
            jLabel5.setText("Error : " + DBException);
        }
    }

    public void tambahMakanan() {
        try {
            String nama_makanan = txt_nama.getText();
            String harga_makanan = txt_harga.getText();

            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, user, pass);
            Statement statement = connection.createStatement();

            String sql = "INSERT INTO makanan (nama_makanan, harga_makanan) values ('" + nama_makanan + "','" + harga_makanan + "');";
            statement.executeUpdate(sql);

            statement.close();
            connection.close();
            
            jLabel5.setText("Success : Data telah disimpan");
            ambilMakanan();
        } catch (Exception DBException) {
            jLabel5.setText("Error : " + DBException);
        }
    }
    
    public void editMakanan() {
        try {
            String id_makanan = txt_id.getText();
            String nama_makanan = txt_nama.getText();
            String harga_makanan = txt_harga.getText();

            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, user, pass);
            Statement statement = connection.createStatement();

            String sql = "UPDATE makanan SET nama_makanan='" + nama_makanan + "',harga_makanan='" + harga_makanan + "' where id_makanan='" + id_makanan + "'";
            statement.executeUpdate(sql);

            statement.close();
            connection.close();
            
            jButton1.setText("Simpan Makanan");
            jLabel5.setText("Success : Data telah diupdate");
            ambilMakanan();
        } catch (Exception DBException) {
            jLabel5.setText("Error : " + DBException);
        }
    }
    
    public void hapusData() {
        try {
            String id_makanan = txt_id.getText();

            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, user, pass);
            Statement statement = connection.createStatement();

            String sql = "DELETE FROM makanan where id_makanan='" + id_makanan + "'";
            statement.executeUpdate(sql);

            statement.close();
            connection.close();
            
            jLabel5.setText("Success : Makanan telah dihapus");
            jButton1.setText("Simpan Makanan");
            ambilMakanan();
        } catch (Exception DBException) {
            jLabel5.setText("Error : " + DBException);
        }
    }

    
/*#####################################  FUNGSI CARI MAKANAN DI DATABASE ######################################*/
    private void cariMakanan() {
        try {
            String nama_makanan = cari_makanan.getText();

            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, user, pass);
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM makanan WHERE nama_makanan like '%"+nama_makanan+"%'";
            ResultSet rs = statement.executeQuery(sql);

            jTable1.getSelectionModel().addListSelectionListener(
                    new ListSelectionListener() {
                public void valueChanged(ListSelectionEvent event) {
                    int viewRow = jTable1.getSelectedRow();
                    if (viewRow < 0) {
                        //Selection got filtered away.
                    } else {
                        txt_id.setText("" + jTable1.getValueAt(viewRow, 0));
                        txt_nama.setText("" + jTable1.getValueAt(viewRow, 1));
                        txt_harga.setText("" + jTable1.getValueAt(viewRow, 2));
                        
                        jButton1.setText("Update Makanan");
                        jButton3.setEnabled(true);
                    }
                }
            }
            );

            jTable1.setModel(new TableMakananModel1(rs));

            pack();

            rs.close();
            statement.close();
            connection.close();
        } catch (Exception DBException) {
            jLabel5.setText("Error : " + DBException);
        }
    }
        
/*##################################### FUNGSI CARI MAKANAN  ######################################*/
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jOptionPane1 = new javax.swing.JOptionPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_nama = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_harga = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txt_id = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        cari_makanan = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setTitle("Tambah Makanan");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("ID :");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Nama Makanan :");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Harga Makanan :");

        jLabel4.setText("Rp.");

        jButton1.setText("Simpan Makanan");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Reset");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        txt_id.setEditable(false);

        jButton3.setText("Hapus Makanan");
        jButton3.setEnabled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        cari_makanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cari_makananActionPerformed(evt);
            }
        });
        cari_makanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cari_makananKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cari_makananKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cari_makananKeyTyped(evt);
            }
        });

        jMenu1.setText("File");

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setText("Quit");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenu2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu2ActionPerformed(evt);
            }
        });

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Makanan");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText("Meja");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cari_makanan, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 579, Short.MAX_VALUE)))
                .addGap(12, 12, 12))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_harga))
                            .addComponent(txt_nama)
                            .addComponent(txt_id))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel1, jLabel2, jLabel3});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(cari_makanan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_nama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_harga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(2, 2, 2)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getAccessibleContext().setAccessibleName("Makanan");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if(txt_harga.getText().equals("") || txt_nama.getText().equals("")){
            jLabel5.setText("Error : Harap isi semua kolom.");
        }else{
            try{
                Integer.parseInt(txt_harga.getText());
                
                if(jButton1.getText().equals("Simpan Makanan")){
                    tambahMakanan();
                    txt_nama.setText("");
                    txt_harga.setText("");
                    txt_id.setText("");
                }else{
                    int n = jOptionPane1.showConfirmDialog(
                            this, "Apakah anda yakin ingin mengubah makanan dengan id " + txt_id.getText() + " ?",
                            "Ubah Makanan", JOptionPane.YES_NO_OPTION);
                    if (n == JOptionPane.YES_OPTION) {
                        editMakanan();
                        txt_nama.setText("");
                        txt_harga.setText("");
                        txt_id.setText("");
                    }
                }
            }catch(NumberFormatException e){    
                jLabel5.setText("Error : Harga harus angka.");
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        txt_harga.setText("");
        txt_nama.setText("");
        txt_id.setText("");
        jButton1.setText("Simpan Makanan");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        int n = jOptionPane1.showConfirmDialog(
                this, "Apakah anda yakin ingin menghapus makanan dengan id " + txt_id.getText() + " ?",
                "Hapus Makanan", JOptionPane.YES_NO_OPTION);
        if (n == JOptionPane.YES_OPTION) {
            hapusData();
            txt_id.setText("");
            txt_nama.setText("");
            txt_harga.setText("");
            jButton3.setEnabled(false);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        new MejaFrame().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenu2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    
/*#####################################  BUAT SEARCH  ######################################*/
    //(ini merupakan pemanggil fungsi cari makanan ketika tombol diketik)
    private void cari_makananKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cari_makananKeyTyped
        // TODO add your handling code here:
        cariMakanan();
    }//GEN-LAST:event_cari_makananKeyTyped
    
    //(ini merupakan pemanggil fungsi cari makanan ketika tombol diketik kemudian kita tekan enter)
    private void cari_makananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cari_makananActionPerformed
        // TODO add your handling code here:
        cariMakanan();
    }//GEN-LAST:event_cari_makananActionPerformed

    //(ini merupakan pemanggil fungsi cari makanan ketika tombol selesai ditekan)
    private void cari_makananKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cari_makananKeyReleased
        // TODO add your handling code here:
        cariMakanan();
    }//GEN-LAST:event_cari_makananKeyReleased

    //(ini merupakan pemanggil fungsi cari makanan ketika tombol hendak ditekan)
    private void cari_makananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cari_makananKeyPressed
        // TODO add your handling code here:
        cariMakanan();
    }//GEN-LAST:event_cari_makananKeyPressed
/*#####################################  BUAT SEARCH  ######################################*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField cari_makanan;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JOptionPane jOptionPane1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txt_harga;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_nama;
    // End of variables declaration//GEN-END:variables
}
