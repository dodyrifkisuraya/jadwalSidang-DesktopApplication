/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tubs.gaspol.item;

import com.tubs.gaspol.db.AlgoritmaPenjadwalan;
import com.tubs.gaspol.db.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;;
import java.sql.Time;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DORIZU
 */
public class JadwalSidang extends Jadwal{
    
    private Connection conn = Koneksi.bukaKoneksi();
    private int idRuangan;
    private int idPA;
    private Date tanggal;
    private String KDPenguji1;
    private String KDPenguji2;
    private int idPeriode;
    private int id;
    private int status;

    public JadwalSidang(int idRuangan, int idPA, Date tanggal, String KDPenguji1, String KDPenguji2, Time jamMulai, Time jamSelesai, int idPeriode) {
        super(jamMulai, jamSelesai);
        this.idRuangan = idRuangan;
        this.idPA = idPA;
        this.tanggal = tanggal;
        this.KDPenguji1 = KDPenguji1;
        this.KDPenguji2 = KDPenguji2;
        this.idPeriode = idPeriode;
    }
    
    public JadwalSidang(int idJadwal,int idRuangan, int idPA, java.sql.Date tanggal, String KDPenguji1, String KDPenguji2,  Time jamMulai, Time jamSelesai, int periode) {
        super(idJadwal, jamMulai, jamSelesai);
        this.idRuangan = idRuangan;
        this.idPA = idPA;
        this.tanggal = tanggal;
        this.KDPenguji1 = KDPenguji1;
        this.KDPenguji2 = KDPenguji2;
        this.idPeriode = periode;
    }
    
    
    public int getId() {
        return id;
    }

    public int getStatus() {
        return status;
    }
    
    
    

    public int getIdRuangan() {
        return idRuangan;
    }

    public void setIdRuangan(int idRuangan) {
        this.idRuangan = idRuangan;
    }

    public int getIdPA() {
        return idPA;
    }

    public void setIdPA(int idPA) {
        this.idPA = idPA;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public String getKDPenguji1() {
        return KDPenguji1;
    }

    public void setKDPenguji1(String KDPenguji1) {
        this.KDPenguji1 = KDPenguji1;
    }

    public String getKDPenguji2() {
        return KDPenguji2;
    }

    public void setKDPenguji2(String KDPenguji2) {
        this.KDPenguji2 = KDPenguji2;
    }
    
    public void registrasi(String nama,String nim,int idKeahlian,String email,
            String dosenPembimbing1,String dosenPembimbing2,String tahunSidangg,String periodSidang){
            //new ProyekAkhir()
    }

    public int getIdPeriode() {
        return idPeriode;
    }

    public void setIdPeriode(int idPeriode) {
        this.idPeriode = idPeriode;
    }
    
     public void tambahJadwalSidang(){
        String query = "INSERT INTO jadwal_sidang (jam_mulai,jam_selesai,tanggal,kode_penguji1, kode_penguji2,"
                + "id_pa, id_periode, id_ruangan) VALUES "
                + " (TIME('"+this.getJamMulai()+"'),"
                + " DATE_ADD(TIME('"+this.getJamSelesai()+"'), interval 2 hour),"
                + " DATE('"+(new java.sql.Date(this.getTanggal().getTime()))+"'),"
                + "'"+this.getKDPenguji1()+"',"
                + "'"+this.getKDPenguji2()+"',"
                + " "+this.getIdPA()+","
                + " "+this.getIdPeriode()+","
                + " "+this.getIdRuangan()+")";
        
        try {
            Statement ps = this.conn.createStatement();
            /*ps.setTime(1, jadwal.getJamMulai());
            ps.setTime(2, jadwal.getJamSelesai());
            ps.setDate(3, new java.sql.Date(jadwal.getTanggal().getTime()));
            ps.setString(4, jadwal.getKDPenguji1());
            ps.setString(5, jadwal.getKDPenguji2());
            ps.setInt(6, jadwal.getIdPA());
            ps.setInt(7, jadwal.getIdPeriode());
            ps.setInt(8, jadwal.getIdRuangan());*/
            ps.executeUpdate(query);
            ps.close();
            this.tambahJatah(this.KDPenguji1,this.KDPenguji2);
        } catch (SQLException ex) {
            Logger.getLogger(AlgoritmaPenjadwalan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
     private void tambahJatah(String d1, String d2){
        String query = "UPDATE dosen SET jatah_sidang=jatah_sidang+1 WHERE kode_dosen='"+d1+"' OR kode_dosen='"+d2+"'";
        try {
            Statement ps = this.conn.createStatement();
            ps.executeUpdate(query);
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(AlgoritmaPenjadwalan.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
}
