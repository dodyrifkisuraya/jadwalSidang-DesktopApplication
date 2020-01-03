/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tubs.gaspol.list;

import com.tubs.gaspol.db.Koneksi;
import com.tubs.gaspol.item.Dosen;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author seifer
 */
public class ListPeriode {
   private Connection conn;
   
   public ListPeriode(){
       this.conn = new Koneksi().bukaKoneksi();
   }
   
   public Object[] getAllTahunSidang(){
       ArrayList<Integer> tahun = new ArrayList<>();
       String query = "SELECT tahun FROM periode_sidang GROUP BY tahun";
       try {
           PreparedStatement ps = this.conn.prepareStatement(query);
           ResultSet setTahun = ps.executeQuery();
           while(setTahun.next()){
               tahun.add(setTahun.getInt("tahun"));
           }
           setTahun.close();
           ps.close();
       } catch (SQLException ex) {
           Logger.getLogger(ListPeriode.class.getName()).log(Level.SEVERE, null, ex);
       }
       
       Object allTahun[] = tahun.toArray();
       return allTahun;
   }
   
   public Object[] getPeriode(int tahun){
       ArrayList<Integer> periode = new ArrayList<>();
       String query = "SELECT periode_ke FROM periode_sidang WHERE tahun = ? ORDER BY periode_ke ASC";
       try {
           PreparedStatement ps = this.conn.prepareStatement(query);
           ps.setInt(1, tahun);
           ResultSet res = ps.executeQuery();
           while(res.next()){
               periode.add(res.getInt("periode_ke"));
           }
           res.close();
           ps.close();
       } catch (SQLException ex) {
           Logger.getLogger(ListPeriode.class.getName()).log(Level.SEVERE, null, ex);
       }
       System.out.println(periode.toString());
       Object[] periodsOfYear = periode.toArray();
       return periodsOfYear;
   }
}
