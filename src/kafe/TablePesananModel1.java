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
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import static kafe.Koneksi.driver;
import static kafe.Koneksi.pass;
import static kafe.Koneksi.url;
import static kafe.Koneksi.user;

/**
 *
 * @author Furqan
 */
public class TablePesananModel1 extends AbstractTableModel{
    private int colnum = 4;
    private int rownum;
    private String[] colNames = {"ID", "Nama Makanan", "HARGA", "Jumlah"};
    private ArrayList ResultSets;
    
    public TablePesananModel1(ResultSet rs)
    {
        ResultSets = new ArrayList();
        
        try{
            while(rs.next()){
            String pesanan = rs.getString("pemesanan");
            String [] pesanan2 = pesanan.split(";");
            for(String baris:pesanan2){
                String [] pesanan3 = baris.split(":");
             String[] row = { 
                 "tes", "tes1", pesanan3[0],pesanan3[1]
             };
             System.out.println(pesanan3[0]);
             System.out.println(pesanan3[1]);
                     ResultSets.add(row);
            }
            
//            Class.forName(driver);
//            Connection connection = DriverManager.getConnection(url, user, pass);
//            Statement statement = connection.createStatement();
//            String sql = "SELECT * FROM makanan WHERE nama_makanan = '"++"'";
//            ResultSet set = statement.executeQuery(sql);
//            set.next();
//                      
//                String[] row = {
//                rs.getString("id_makanan"),
//                rs.getString("nama_makanan"),
//                rs.getString("harga_makanan")};
//                ResultSets.add(row);
//                set.close();
//                statement.close();
//                connection.close();

            }
        }
        catch(Exception e){
            System.out.println("Exception di TableMakananModel");
        }
    }

    public Object getValueAt(int rowIndex, int columnIndex){
        String[] row = (String[]) ResultSets.get(rowIndex);
        return row[columnIndex];
    }

    public int getRowCount(){
        return ResultSets.size();
    }

    public int getColumnCount(){
        return colnum;
    }

    public String getColumnName(int param){
        return colNames[param];
    }
}