/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tubs.gaspol.item;

import com.tubs.gaspol.db.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DORIZU
 */
public class Mahasiswa extends Personal {
   
    private Connection conn = Koneksi.bukaKoneksi();
    private String nim;
    
    public Mahasiswa(String nim, String name, String email) {
        super(name, email);
        this.nim = nim;
    }
    
    public void insertMahasiswa(){
        String query = "INSERT INTO mahasiswa (nama,email,nim,id_keahlian) VALUES ('?','?','?',?)";
        PreparedStatement ps;
        try {
            ps = this.conn.prepareStatement(query);
            ps.setString(1, super.getName());
            ps.setString(2, super.getEmail());
            ps.setString(3, this.nim);
            ps.executeQuery();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Mahasiswa.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }
    
}
