/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tubs.gaspol.list;

import com.tubs.gaspol.db.CRUD;
import com.tubs.gaspol.db.Koneksi;
import com.tubs.gaspol.item.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author seifer
 */
public class ListData {
    private ArrayList<Dosen> listDosen;
    private ArrayList<Keahlian> listKeahlian;

    private ArrayList<Ruangan> listRuangan;
    private ArrayList<JadwalMengajarDosen> listJadwalDosen;
    private ArrayList<Mahasiswa> listMahasiswa;
    private ArrayList<ProyekAkhir> listPA;
    private ArrayList<Ruangan> listRuang;
    private ArrayList<JadwalSidang> listJadwalSidang;
    private ArrayList<Periode> listPeriode;
    private Connection conn;
    
    public ListData(){
        this.conn = new Koneksi().bukaKoneksi();
        loadDosen();
        loadKeahlian();
        loadRuangan();
        loadJadwalDosen();
        loadMahasiswa();
        loadPA();
        loadRuang();
        loadJdwlSidang();
        loadPeriode();
    }
    
    public void loadDosen(){
        if(conn != null){
            listDosen = new ArrayList<>();
            String query = "SELECT * FROM dosen";
            try{
                PreparedStatement ps = conn.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    int id = rs.getInt("id");
                    String nama = rs.getString("nama");
                    String kode = rs.getString("kode_dosen");
                    String nip = rs.getString("nip");
                    int idKeahlian1 = rs.getInt("id_keahlian1");
                    int idKeahlian2 = rs.getInt("id_keahlian2");
                    String email = rs.getString("email");
                    
                    Dosen dosen = new Dosen(kode,nip,nama,email,idKeahlian1,idKeahlian2);
                    listDosen.add(dosen);
                }
                rs.close();
                ps.close();
            }catch(SQLException e){
                Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }
    
    public String getNimByIdPa(int id){
        String nim = "";
        String query = "SELECT nim FROM proyek_akhir WHERE id="+id;
        try{
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                rs.getString("nim");
            }
        }catch(SQLException e){
                Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, e);
            }
        return nim;
    }
    
    public void loadJadwalDosen(){
        if(conn != null){
            listJadwalDosen = new ArrayList<>();
            String query = "SELECT * FROM jadwal_mengajar_dosen";
            try{
                PreparedStatement ps = conn.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    int id = rs.getInt("id");
                    String idDos = rs.getString("kode_dosen");
                    String jamAwal = rs.getString("jam_mulai");
                    String jamAkhir = rs.getString("jam_selesai");
                    int hariKe = rs.getInt("hari_ke");
                    
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                    JadwalMengajarDosen jdDosen;
                    try {
                        jdDosen = new JadwalMengajarDosen(id,idDos,hariKe,new Time(sdf.parse(jamAwal).getTime()),
                                new Time(sdf.parse(jamAkhir).getTime()));
                        listJadwalDosen.add(jdDosen);
                    } catch (ParseException ex) {
                        Logger.getLogger(ListData.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
                rs.close();
                ps.close();
            }catch(SQLException e){
                Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        
    }
    
    public void loadPeriode(){
        if(conn != null){
            listPeriode = new ArrayList<>();
            String query = "SELECT * FROM periode_sidang ORDER BY periode_ke ASC";
            try{
                PreparedStatement ps =conn.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    int id = rs.getInt("id_periode");
                    String periodeKe = rs.getString("periode_ke");
                    String tanggalAwal = rs.getString("tanggal_awal");
                    String tanggalAkhir = rs.getString("tanggal_akhir");
                    String tahun = rs.getString("tahun");
            
                    listPeriode.add(new Periode(id, tanggalAwal, tanggalAkhir, periodeKe, tahun));
                }
                rs.close();
                ps.close();
            }catch(SQLException e){
                Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }
    
    public void loadMahasiswa(){
        if(conn != null){
            listMahasiswa = new ArrayList<>();
            String query = "SELECT * FROM mahasiswa";
            try{
                PreparedStatement ps =conn.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    String nama = rs.getString("nama");
                    String email = rs.getString("email");
                    String nim = rs.getString("nim");

                    listMahasiswa.add(new Mahasiswa(nim, nama, email));
                }
                rs.close();
                ps.close();
            }catch(SQLException e){
                Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }
    
    public int idKeahlian(String nama){
        int id = 0;
        if(conn != null){
             String query = "SELECT id FROM keahlian WHERE nama_keahlian='"+nama+"'";
            try{
                PreparedStatement ps =conn.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    id = rs.getInt("id");
                }
                
                rs.close();
                ps.close();
            }catch(SQLException e){
                Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return id;
    }
    
    public void loadPA(){
        if(conn != null){
            listPA = new ArrayList<>();
            String query = "SELECT * FROM proyek_akhir";
            try{
                PreparedStatement ps =conn.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    int id = rs.getInt("id");
                    String judul = rs.getString("judul");
                    String nim = rs.getString("nim");
                    String dosbing1 = rs.getString("kode_dosbing1");
                    String dosbing2 = rs.getString("kode_dosbing2");
                    int keahlian = rs.getInt("id_keahlian");
                    int status = rs.getInt("status");
                    
                    listPA.add(new ProyekAkhir(id, judul, nim, dosbing1, dosbing2, keahlian,status));
                }
                rs.close();
                ps.close();
            }catch(SQLException e){
                Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }
    
    public void loadRuang(){
        if(conn != null){
            listRuang = new ArrayList<>();
            String query = "SELECT * FROM ruangan";
            try{
                PreparedStatement ps =conn.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    int id = rs.getInt("id");
                    String nama = rs.getString("nama");
            
                    listRuang.add(new Ruangan(id, nama));
                }
                rs.close();
                ps.close();
            }catch(SQLException e){
                Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }
    
    public void loadJdwlSidang(){
        if(conn != null){
            listJadwalSidang = new ArrayList<>();
            String query = "SELECT * FROM jadwal_sidang";
            try{
                PreparedStatement ps =conn.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    int id = rs.getInt("id");
                    Time jamMulai = rs.getTime("jam_mulai");
                    Time jamSelesai = rs.getTime("jam_selesai");
                    Date tanggal = rs.getDate("tanggal");
                    String idP1 = rs.getString("kode_penguji1");
                    String idP2 = rs.getString("kode_penguji2");
                    int idruang = rs.getInt("id_ruangan");
                    int idPA = rs.getInt("id_pa");
                    int idPeriode = rs.getInt("id_periode");

                    listJadwalSidang.add(new JadwalSidang(id, idruang, idPA, tanggal, idP1, idP2, jamMulai, jamSelesai, idPeriode ));
                }
                rs.close();
                ps.close();
            }catch(SQLException e){
                Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }
    
    public ArrayList<Periode> getPeriode(){
        return this.listPeriode;
    }
    
    public ArrayList<JadwalSidang> getJadwalSidang(){
        return this.listJadwalSidang;
    }
    
    public ArrayList<Ruangan> getRuang(){
        return this.listRuang;
    }
    
    public ArrayList<ProyekAkhir> getPA(){
        return this.listPA;
    }
    
    public ArrayList<Mahasiswa> getAllMahasiswa(){
        return this.listMahasiswa;
    }
    
    public ArrayList<Dosen> getAllDosen(){
        return this.listDosen;
    }
    
    public ArrayList<JadwalMengajarDosen> getAllJdDosen(){
        return this.listJadwalDosen;
    }
    
    public ArrayList<Keahlian> getKeahlian(){
        return this.listKeahlian;
    }
    
    public Object[] getKodeAllDosen(){
        
        ArrayList<String> kodeDosen = new ArrayList<>();
        
        for(Dosen kd: listDosen){
                kodeDosen.add(kd.getKodeDosen());
            
            
        }
        Object[] listKodeDosen = kodeDosen.toArray();
        return listKodeDosen;
    }
    
    public Object[] getKodeAllDosenByKeahlian(int idKeahlian){
        
        ArrayList<String> kodeDosen = new ArrayList<>();
        
        for(Dosen kd: listDosen){
            if(kd.getIdKeahlian1() == (idKeahlian) || kd.getIdKeahlian2() == (idKeahlian)){
                kodeDosen.add(kd.getKodeDosen());
            }
            
        }
        Object[] listKodeDosen = kodeDosen.toArray();
        return listKodeDosen;
    }
    
    private void loadKeahlian(){
        System.out.println(conn);
        if(conn != null){
            String query = "SELECT * FROM keahlian";
            try {
                this.listKeahlian = new ArrayList<>();
                PreparedStatement ps = this.conn.prepareStatement(query);
                ResultSet res = ps.executeQuery();
                while(res.next()){
                    int id = res.getInt("id");
                    String namaKeahlian = res.getString("nama_keahlian");
                    this.listKeahlian.add(new Keahlian(id,namaKeahlian));
                }
                res.close();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(ListData.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
    }
    
     private void loadRuangan(){
        System.out.println(conn);
        if(conn != null){
            String query = "SELECT * FROM ruangan";
            try {
                this.listRuangan = new ArrayList<>();
                PreparedStatement ps = this.conn.prepareStatement(query);
                ResultSet res = ps.executeQuery();
                while(res.next()){
                    int id = res.getInt("id");
                    String namaRuangan = res.getString("nama");
                    this.listRuangan.add(new Ruangan(id,namaRuangan));
                }
                res.close();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(ListData.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
    }
    
    public String[][] getAllKeahlian(){
        String[][] keahlians = new String[2][listKeahlian.size()];
        ArrayList<String> idList = new ArrayList<>();
        ArrayList<String> keahlianList = new ArrayList<>();
        for(Keahlian k:this.listKeahlian){
            idList.add(Integer.toString(k.getId()));
            keahlianList.add(k.getNamaKeahlian());
        }
        
       idList.toArray(keahlians[0]);
       keahlianList.toArray(keahlians[1]);
       return keahlians;
    }
    
    public Object[] getAllRuangan(){
        ArrayList<Integer> idList = new ArrayList<>();
        for(Ruangan k:this.listRuangan){
            idList.add(k.getId());
        }
        
       Object ruangan[] = idList.toArray();
       return ruangan;
    }
    
  /*  public ArrayList<JadwalMengajarDosen> getAllJadwalDosen(){
        ArrayList<JadwalMengajarDosen> listJadwal = new ArrayList<>();
        String query = "SELECT * FROM jadwal_mengajar_dosen";
    }*/

    public ArrayList<Dosen> getDosenByKeahlian(int id_keahlian, String dosbing1, String dosbing2) {
        String query = "SELECT * FROM dosen WHERE (id_keahlian1="+id_keahlian+" OR id_keahlian2="+id_keahlian+") AND kode_dosen != '"+dosbing1+"' AND kode_dosen != '"+dosbing2+"' ORDER BY jatah_sidang ASC"; //To change body of generated methods, choose Tools | Templates.
        ArrayList<Dosen> ld = new ArrayList<>();
        try {
            PreparedStatement ps = this.conn.prepareStatement(query);
            ResultSet res = ps.executeQuery();
            while(res.next()){
                String kode = res.getString("kode_dosen");
                String nama = res.getString("nama");
                String email = res.getString("email");
                String nip = res.getString("nip");
                int idKeahlian1 = res.getInt("id_keahlian1");
                int idKeahlian2 = res.getInt("id_keahlian2");
                ld.add(new Dosen(kode,nip,nama,email,idKeahlian1,idKeahlian2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ListData.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return ld;
    }
    
    public String namaKeahlian(int id){
        String nama = null;
        if(conn != null){
             String query = "SELECT nama_keahlian FROM keahlian WHERE id="+id;
            try{
                PreparedStatement ps =conn.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    nama = rs.getString("nama_keahlian");
                }
                
                rs.close();
                ps.close();
            }catch(SQLException e){
                Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return nama;
    }
    
    public int getIdHari(String cb){
        int hariKe = 0;
        if(cb.equals("Senin")){
            hariKe = 1;
        }else if(cb.equals("Selasa")){
            hariKe = 2;
        }else if(cb.equals("Rabu")){
            hariKe = 3;
        }else if(cb.equals("Kamis")){
            hariKe = 4;
        }else if(cb.equals("Jumat")){
            hariKe = 5;
        }else if(cb.equals("Sabtu")){
            hariKe = 6;
        }
        return hariKe;
    }
    
    public String getNamaHari(int id){
        String nama = null;
        if(id == 1){
            nama = "Senin";
        }else if (id == 2){
            nama = "Selasa";
        }else if (id == 3){
            nama = "Rabu";
        }else if (id == 4){
            nama = "Kamis";
        }else if (id == 5){
            nama = "Jumat";
        }else if (id == 6){
            nama = "Sabtu";
        }
        return nama;
    }
}
