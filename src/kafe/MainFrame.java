/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kafe;

import java.awt.Color;
import java.awt.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Furqan
 */
public class MainFrame extends javax.swing.JFrame implements Koneksi {

    /**
     * Creates new form MainFrame
     */
    int id_meja;
    int id_pesanan;

    public MainFrame() {
        initComponents();

        jTable1 = new javax.swing.JTable() {
            public Component prepareRenderer(TableCellRenderer renderer, int Index_row, int Index_col) {
                // get the current row
                Component comp = super.prepareRenderer(renderer, Index_row, Index_col);
                // even index, not selected
                if (!isCellSelected(Index_row, Index_col)) {
                    comp.setBackground(Color.white);
                } else {
                    comp.setBackground(Color.blue);
                }

                if (jTable1.getValueAt(Index_row, 2).equals("Ditempati")) {
                    comp.setBackground(Color.red);
                }
                return comp;
            }
        };

        jTable1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jScrollPane1.setViewportView(jTable1);
        
        ambilMeja();
    }

    public void ambilMeja() {
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, user, pass);
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM meja";
            ResultSet rs = statement.executeQuery(sql);

            jTable1.getSelectionModel().addListSelectionListener(
                    new ListSelectionListener() {
                public void valueChanged(ListSelectionEvent event) {
                    int viewRow = jTable1.getSelectedRow();
                    if (viewRow < 0) {
                        //Selection got filtered away

                    } else {
                        id_meja = Integer.parseInt("" + jTable1.getValueAt(viewRow, 0));

                        jButton2.setEnabled(true);
                        jButton4.setEnabled(true);
                        tambahPesanan();
                    }
                }
            }
            );

            jTable1.setModel(new TableModel(rs));

            pack();

            rs.close();
            statement.close();
            connection.close();
        } catch (Exception DBException) {
            System.err.println("Error : " + DBException);
        }
    }

    public void tambahPesanan() {
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, user, pass);
            Statement statement = connection.createStatement();

            String sql1 = "SELECT id_pesanan, meja, meja.status_meja as status_meja FROM pesanan INNER JOIN meja ON meja.id_meja=pesanan.meja where meja='" + id_meja + "' ORDER BY id_pesanan DESC LIMIT 1;";
            ResultSet rs = statement.executeQuery(sql1);

            if (!rs.next()) {
                DateFormat date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date tgl = new Date();
                String sql = "INSERT INTO pesanan (meja, waktu) values ('" + id_meja + "', '" + date.format(tgl) + "');";
                statement.executeUpdate(sql);
                rs = statement.executeQuery("select last_insert_id() as id_pesanan from pesanan");
                id_pesanan = Integer.parseInt(rs.getString("id_pesanan"));
            } 
            else{
                id_pesanan = Integer.parseInt(rs.getString("id_pesanan"));
                ambilPesanan();
            }

            statement.close();
            connection.close();

        } catch (Exception DBException) {
            System.out.println("Error : " + DBException);
        }
    }

    private void ambilPesanan() {
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, user, pass);
            Statement statement = connection.createStatement();
            String sql = "SELECT isi_pesanan.id_makanan, makanan.nama_makanan, makanan.harga_makanan*isi_pesanan.jumlah_makanan as harga_makanan, jumlah_makanan FROM isi_pesanan INNER JOIN makanan ON makanan.id_makanan=isi_pesanan.id_makanan INNER JOIN pesanan ON pesanan.id_pesanan=isi_pesanan.id_pesanan INNER JOIN meja ON meja.id_meja=pesanan.meja where isi_pesanan.id_pesanan='" + id_pesanan + "' and meja.id_meja='" + id_meja + "';";
            ResultSet rs = statement.executeQuery(sql);

//            jTable1.getSelectionModel().addListSelectionListener(
//                    new ListSelectionListener() {
//                public void valueChanged(ListSelectionEvent event) {
//                    int viewRow = jTable1.getSelectedRow();
//                    if (viewRow < 0) {
//                        //Selection got filtered away.
//                    } else {
//                        
//                    }
//                }
//            }
//            );
            jTable2.setModel(new TablePesananModel(rs));

            pack();

            rs.close();
            statement.close();
            connection.close();
        } catch (Exception DBException) {

            System.out.println("Error : " + DBException);
        }
    }

    public void updateStatus() {
        try {

            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, user, pass);
            Statement statement = connection.createStatement();

            String sql = "UPDATE meja SET status_meja='Ditempati' where id_meja='" + id_meja + "'";
            statement.executeUpdate(sql);

            statement.close();
            connection.close();

        } catch (Exception DBException) {
            System.out.println("Error : " + DBException);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Kasir Kafe");

        jTable1.setBackground(new java.awt.Color(255, 255, 153));
        jTable1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Meja", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton1.setBackground(new java.awt.Color(255, 204, 102));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kafe/refresh.png"))); // NOI18N
        jButton1.setText("Refresh Meja");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Nama", "Jumlah", "Harga"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jButton2.setBackground(new java.awt.Color(255, 204, 102));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kafe/add.png"))); // NOI18N
        jButton2.setText("Tambah Pesanan");
        jButton2.setEnabled(false);
        jButton2.setPreferredSize(new java.awt.Dimension(135, 26));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(255, 204, 102));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kafe/dolar.png"))); // NOI18N
        jButton4.setText("Bayar Semua");
        jButton4.setEnabled(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jMenuBar1.setBackground(new java.awt.Color(255, 255, 255));

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
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 740, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        ambilMeja();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        new MakananFrame().setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenu2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu2ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        new MejaFrame().setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jMenuItem3ActionPerformed


    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        new BayarSemuaFrame(id_pesanan, id_meja).setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        new TambahPesananFrame(id_pesanan).setVisible(true);
        updateStatus();
        ambilMeja();
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
}
