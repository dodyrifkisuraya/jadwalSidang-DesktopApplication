/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tubs.gaspol.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DORIZU
 */
public class Koneksi {
    private static Connection conn;
    private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_NAME = "gaspol_tubes";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/"+DB_NAME;
    private static final String DB_UNAME = "tubes";
    private static final String DB_PASS = "tubes2019!";
    
    public static Connection bukaKoneksi(){
        if(conn == null){
            try{
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection(DB_URL, DB_UNAME, DB_PASS);
            }catch(ClassNotFoundException e){
                System.err.format("Class Not Found");
            }catch(SQLException e){
                System.err.format("SQL Static : %s\n%s", e.getSQLState(), e.getMessage());
            }catch(Exception e){
                e.printStackTrace();
            }
            
        }
        return conn;
    
    }
    
    public static int countRow(String table) throws SQLException{
        Connection connect = bukaKoneksi();
        String query = "SELECT COUNT(*) AS jumlah FROM ?";
        PreparedStatement ps;
        int total = 0;
        try {
            ps = connect.prepareStatement(query);
            ps.setString(0, table);
            ResultSet jumlah = ps.executeQuery();
            while(jumlah.next()){
                total += jumlah.getInt("jumlah");
            }
            jumlah.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Koneksi.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return total;
    
    }
    
    
    
//    private static Connection MySQLConfig;
//    
//    public static Connection configDB()throws SQLException{
//        try{
//            String url = "jdbc:mysql://localhost:3306/db_desktop";
//            String user = "root";
//            String pass = "";
//            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
//            MySQLConfig = DriverManager.getConnection(url, user, pass);
//        }catch(SQLException e){
//            System.out.println("Koneksi DB Gagal : "+e.getMessage());
//        }
//        return MySQLConfig;
//    }
}
