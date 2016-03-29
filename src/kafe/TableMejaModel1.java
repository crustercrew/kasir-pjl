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
public class TableMejaModel1 extends AbstractTableModel{
    private int colnum = 2;
    private int rownum;
    private String[] colNames = {"ID", "Nama Meja"};
    private ArrayList ResultSets;
    
    public TableMejaModel1(ResultSet rs)
    {
        ResultSets = new ArrayList();

        try{
            while(rs.next()){
                String[] row = {
                rs.getString("id_meja"),
                rs.getString("nama_meja")};
                ResultSets.add(row);
            }
        }
        catch(Exception e){
            System.out.println("Exception di TableMejaModel");
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
