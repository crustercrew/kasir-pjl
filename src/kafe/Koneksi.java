/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kafe;


/**
 *
 * @author Furqan
 */
public interface Koneksi {
    String driver = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/kasir_pjl?zeroDateTimeBehavior=convertToNull";
    String user = "root";
    String pass = "";
}
