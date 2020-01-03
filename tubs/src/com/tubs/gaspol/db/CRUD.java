/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tubs.gaspol.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DORIZU
 */
public class CRUD {
    
    private Connection conn;
    private DefaultTableModel model = new DefaultTableModel();
    
    public CRUD(){
        conn = Koneksi.bukaKoneksi();
    
    }
    
    public int getIdRuangan(String r){
        int id = 0;
        String query = "SELECT id FROM ruangan WHERE nama='"+r+"'";
        PreparedStatement ps;
        try {
            ps = this.conn.prepareStatement(query);
            ResultSet res = ps.executeQuery();
            while(res.next()){
                id = res.getInt("id");
            }
            res.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    public String getEmailDosen(String kode){
        String email = "";
        String query = "SELECT email FROM dosen WHERE kode_dosen='"+kode+"'";
        try {
            PreparedStatement ps = this.conn.prepareStatement(query);
            ResultSet res = ps.executeQuery();
            while(res.next()){
                email = res.getString("email");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return email;
    }
    
    public String getEmailMahasiswa(String nim){
        String email = "";
        String query = "SELECT email FROM mahasiswa WHERE nim='"+nim+"'";
        try {
            PreparedStatement ps = this.conn.prepareStatement(query);
            ResultSet res = ps.executeQuery();
            while(res.next()){
                email = res.getString("email");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return email;
    }
    
    public int sudahRegister(String nim){
        int jumlah = 0;
        String query = "SELECT nim FROM mahasiswa WHERE nim='"+nim+"'";
        try {
            PreparedStatement ps = this.conn.prepareStatement(query);
            ResultSet res = ps.executeQuery();
            while(res.next()){
                jumlah++;
            }
            res.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jumlah;
    }
    
    public int tambahDosen(String nama, String email, int idKeahlian, int idKeah2, String kodeDosen,String nip){
        int hasil = 0;
        if(conn != null){
            try{
                String query = "INSERT INTO dosen(nama, email, id_keahlian1, id_keahlian2, kode_dosen, nip) VALUES (?,?,?,?,?,?)";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, nama);
                ps.setString(2, email);
                ps.setInt(3, idKeahlian);
                ps.setInt(4, idKeah2);
                ps.setString(5, kodeDosen);
                ps.setString(6, nip);
                hasil = ps.executeUpdate();
                
            }catch(SQLException e){
                
                Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return hasil;
    }
    
    public int tambahMahasiswa(String nim, String nama, String email){
        int hasil = 0;
        if(conn != null){
            try{
                String query = "INSERT INTO mahasiswa(nama, email, nim) VALUES (?,?,?)";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, nama);
                ps.setString(2, email);
                ps.setString(3, nim);
                hasil = ps.executeUpdate();
                
            }catch(SQLException e){
                Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return hasil;
    }
    
    public int tambahJadwalDosen(String kodos,String jamMulai, String jamSelesai, int hariKe){
        int hasil = 0;
        if(conn != null){
            try{
                String query = "INSERT INTO jadwal_mengajar_dosen(kode_dosen, jam_mulai, jam_selesai, hari_ke) VALUES (?,?,?,?)";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, kodos);
                ps.setString(2, jamMulai);
                ps.setString(3, jamSelesai);                
                ps.setInt(4, hariKe);
                 hasil= ps.executeUpdate();
                
            }catch(SQLException e){
                Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return hasil;
    }
    
    public int tambahJadwalSidang(String jamMulai, String jamSelesai, String tanggal, String idPenguji1, String idPenguji2, int idRuang, int idPa, int idPeriode){
        int hasil =0;
        if(conn != null){
            try{
                String query = "INSERT INTO jadwal_sidang(jam_mulai, jam_selesai, tanggal, kode_penguji1, kode_penguji2, id_ruangan, id_pa, id_periode) VALUES (?,?,?,?,?,?,?,?)";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, jamMulai);
                ps.setString(2, jamSelesai);
                ps.setString(3, tanggal);
                ps.setString(4, idPenguji1);
                ps.setString(5, idPenguji2);
                ps.setInt(6, idRuang);
                ps.setInt(7, idPa);
                ps.setInt(8, idPeriode);

                 hasil= ps.executeUpdate();
                 ps.close();
                 
                
            }catch(SQLException e){
                Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, e);
            }
            
            
            try {
                String queryUpdate = "UPDATE proyek_akhir SET status = 1 WHERE id="+idPa;
                 PreparedStatement ps2 = this.conn.prepareStatement(queryUpdate);
                 ps2.executeUpdate();
                 ps2.close();
                
            } catch (SQLException ex) {
                Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, ex);
            }
                 
        }
        
        return hasil;
    }
    
    public int updateJadwalSidang(String idPaAwal, String jamMulai, String jamSelesai, String tanggal, String idPenguji1, String idPenguji2, int idRuang, int idPa, int idPeriode){
        int hasil =0;
        if(conn != null){
            try{
                String query = "UPDATE jadwal_sidang SET jam_mulai=?, jam_selesai=?, tanggal=?, kode_penguji1=?, kode_penguji2=?, id_ruangan=?, id_pa=?, id_periode=? WHERE id_pa="+idPaAwal;
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, jamMulai);
                ps.setString(2, jamSelesai);
                ps.setString(3, tanggal);
                ps.setString(4, idPenguji1);
                ps.setString(5, idPenguji2);
                ps.setInt(6, idRuang);
                ps.setInt(7, idPa);
                ps.setInt(8, idPeriode);

                 hasil= ps.executeUpdate();
                
            }catch(SQLException e){
                Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        
        return hasil;
    }
    
    public int tambahKeahlian(String namaKeahlian){
        int hasil = 0;
        if(conn != null){
            try{
                String query = "INSERT INTO keahlian(nama_keahlian) VALUES (?)";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, namaKeahlian);
               
                hasil = ps.executeUpdate();
                
            }catch(SQLException e){
                Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return hasil;
    }
    
    public void tambahPengelola(String username, String password, String nama, String nip){
        if(conn != null){
            try{
                String query = "INSERT INTO pengelola(username, password, nama, nip) VALUES (?,?,?,?)";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, username);
                ps.setString(2, password);
                ps.setString(3, nama);
                ps.setString(4, nip);          

                int hasil = ps.executeUpdate();
                
            }catch(SQLException e){
                Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }
    
    
    public String getNamaPa(int id){
        String nama = "";
        String query = "SELECT judul FROM proyek_akhir WHERE id="+id+" LIMIT 1";
        PreparedStatement ps;
        try {
            ps = this.conn.prepareStatement(query);
            ResultSet res = ps.executeQuery();
            while(res.next()){
                nama = res.getString("judul");
            }
            
            
            res.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nama;
    }
    
     public String getNamaRuangan(int id){
        String nama = "";
        String query = "SELECT nama FROM ruangan WHERE id="+id+" LIMIT 1";
        PreparedStatement ps;
        try {
            ps = this.conn.prepareStatement(query);
            ResultSet res = ps.executeQuery();
            while(res.next()){
                nama = res.getString("nama");
            }
            res.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nama;
    }
    
    public int tambahPa(String judul, String idDosbing1, String idDosbing2, int keahlian, String nim1){
        int hasil = 0;
        if(conn != null){
            try{
                String query = "INSERT INTO proyek_akhir(judul, kode_dosbing1, kode_dosbing2, id_keahlian, nim, status) VALUES (?,?,?,?,?,?)";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, judul);
                ps.setString(2, idDosbing1);
                ps.setString(3, idDosbing2);
                ps.setInt(4, keahlian);   
                ps.setString(5, nim1);
                ps.setString(6, "0");
                hasil = ps.executeUpdate();
                
            }catch(SQLException e){
                Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return hasil;
    }
    
    public int tambahRuang(String nama){
        int hasil = 0;
        if(conn != null){
            try{
                String query = "INSERT INTO ruangan(nama) VALUES (?)";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, nama);
                

                hasil = ps.executeUpdate();
                
            }catch(SQLException e){
                Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return hasil;
    }
    
    public int updateDosen(String kodeAwal, String nama, String email, int idKeahlian,int idKeahlian2, String kodeDosen,String nip){
        int hasil = 0;
        if(conn != null){
            try{
                String query = "UPDATE dosen SET nama=?, email=?, id_keahlian1=?,id_keahlian2=?, kode_dosen=?, nip=? WHERE kode_dosen=?";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, nama);
                ps.setString(2, email);
                ps.setInt(3, idKeahlian);
                ps.setInt(4, idKeahlian2);
                ps.setString(5, kodeDosen);
                ps.setString(6, nip);
                ps.setString(7, kodeAwal);
                hasil = ps.executeUpdate();
                
            }catch(SQLException e){
                Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return hasil;
    }
    
    public int updateJadwalDosen(int id,String kodos, String jamMulai, String jamSelesai, int hariKe){
        int hasil = 0;
        if(conn != null){
            try{
                String query = "UPDATE jadwal_mengajar_dosen SET jam_mulai=?, jam_selesai=?, hari_ke=?, kode_dosen = ? WHERE id=?";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, jamMulai);
                ps.setString(2, jamSelesai);
                ps.setInt(3, hariKe);
                ps.setString(4,kodos);
                ps.setInt(5, id);
                 hasil= ps.executeUpdate();
                
            }catch(SQLException e){
                Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return hasil;
    }
    
    public int updateMahasiswa(String nimAwal,String namaBaru, String email, String nim){
        int hasil = 0;
        if(conn != null){
            try{
                String query = "UPDATE mahasiswa SET nama=?, email=?,nim=? WHERE nim=?";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, namaBaru);
                ps.setString(2, email);
                ps.setString(3, nim);
                ps.setString(4, nimAwal);
                hasil = ps.executeUpdate();
                
            }catch(SQLException e){
                Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return hasil;
    }
    
    public int updateKeahlian(String namaAwal, String namaAkhir){
        int hasil = 0;
        if(conn != null){
            try{
                String query = "UPDATE keahlian SET nama_keahlian=? WHERE nama_keahlian=?";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, namaAkhir);
                ps.setString(2, namaAwal);
                
                hasil = ps.executeUpdate();
                
            }catch(SQLException e){
                Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        
        return hasil;
    }
    
    public int updateRuang(String kode, String nama){
        int hasil =0;
        if(conn != null){
            try{
                String query = "UPDATE ruangan SET nama=? WHERE id=?";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, nama);
                ps.setString(2, kode);
                
                hasil = ps.executeUpdate();
                
            }catch(SQLException e){
                Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return hasil;
    }
    
    public int tambahPeriodeSidang(String tanggalAwal, String tanggalAkhir, String periode){
        int hasil = 0;
        if(conn != null){
            try{
                String query = "INSERT INTO periode_sidang(tanggal_awal, tanggal_akhir, periode_ke,tahun) VALUES (?,?,?,?)";
                PreparedStatement ps = conn.prepareStatement(query);
                
                String[] date = tanggalAwal.split("-");
                String tahun = date[0];
                ps.setString(1, tanggalAwal);
                ps.setString(2, tanggalAkhir);
                ps.setString(3, periode);          
                ps.setString(4, tahun);
                

                hasil = ps.executeUpdate();
                
            }catch(SQLException e){
                Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return hasil;
    }
    
    
    
    public int updatePeriode(String periodeAwal,String awal, String akir, String periode){
        int hasil = 0;
        if(conn != null){
            try{
                String query = "UPDATE periode_sidang SET tanggal_awal=?, tanggal_akhir=?, periode_ke=? WHERE periode_ke=?";
                PreparedStatement ps = conn.prepareStatement(query);
         
                ps.setString(1, awal);
                ps.setString(2, akir);
                ps.setString(3, periode);
                ps.setString(4, periodeAwal);
                
                hasil = ps.executeUpdate();
                
            }catch(SQLException e){
                Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return hasil;
    }
    
    public int updatePA(String nim1Awal, String judul, String idDosbing1,String idDosbing2,int keahlian,String nim1,int status){
        int hasil = 0;
        if(conn != null){
            try{
                String query = "UPDATE proyek_akhir SET judul=? , kode_dosbing1=? , kode_dosbing2=? , id_keahlian=? , nim=?, status=? WHERE nim=? ";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, judul);
                ps.setString(2, idDosbing1);
                ps.setString(3, idDosbing2);
                ps.setInt(4, keahlian);   
                ps.setString(5, nim1);
              
                ps.setInt(6, status);
                ps.setString(7, nim1Awal);
                
                hasil = ps.executeUpdate();
                
            }catch(SQLException e){
                Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return hasil;
    }
    
    public int hitungMuncul(String tfNimMhs, char c) {
         //To change body of generated methods, choose Tools | Templates.
         int count = 0;
         for(int i=0;i<tfNimMhs.length();i++){
             if(tfNimMhs.charAt(i) == c){
                 count++;
             }
         }
         return count+1;
    }
    
    public boolean sudahTerdaftarSidang(String nim){
        boolean sudah = false;
        String query = "SELECT nim FROM proyek_akhir WHERE nim LIKE '%"+nim+"%'";
        PreparedStatement ps;
        try {
            ps = this.conn.prepareStatement(query);
            ResultSet res = ps.executeQuery();
            while(res.next()){
                sudah = true;
                break;
            }
            res.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return sudah;
    }

    public String getNamaPeriode(int id){
        String nama = null;
        String query = "SELECT periode_ke,tahun FROM periode_sidang WHERE id_periode="+id;
        PreparedStatement ps;
        try {
            ps = this.conn.prepareStatement(query);
            ResultSet res = ps.executeQuery();
            while(res.next()){
                nama = res.getString("tahun")+"-"+res.getString("periode_ke");
            }
            res.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nama;
    }
    
    
    
}
