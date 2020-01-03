/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tubs.gaspol.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author seifer
 */
public class CheckData {
    
    private Connection conn;

    public CheckData() {
        this.conn = new Koneksi().bukaKoneksi();
    }
    
    public boolean cekNim(String nim){
        String query = "SELECT * FROM mahasiswa WHERE nim='"+nim+"'";
        boolean ada = false;
        try {
            PreparedStatement ps = this.conn.prepareStatement(query);
            ResultSet res = ps.executeQuery();
            while(res.next()){
                ada = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CheckData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ada;
    }
    
    
    
}
