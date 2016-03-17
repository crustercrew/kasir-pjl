/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kafe;

import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Furqan
 */
public class TableMakananModel1 extends AbstractTableModel{
    private int colnum = 3;
    private int rownum;
    private String[] colNames = {"ID", "Nama Makanan", "Harga Makanan"};
    private ArrayList ResultSets;
    
    public TableMakananModel1(ResultSet rs)
    {
        ResultSets = new ArrayList();

        try{
            while(rs.next()){
                String[] row = {
                rs.getString("id_makanan"),
                rs.getString("nama_makanan"),
                rs.getString("harga_makanan")};
                ResultSets.add(row);
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
