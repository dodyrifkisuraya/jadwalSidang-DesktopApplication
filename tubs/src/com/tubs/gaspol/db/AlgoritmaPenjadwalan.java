/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tubs.gaspol.db;

import com.tubs.gaspol.item.*;
import com.tubs.gaspol.list.ListData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author seifer
 */
public class AlgoritmaPenjadwalan {
    private String jam[] = {"08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00"};
    private List<Date> tanggals;
    private PeriodeSidang periodeSidang;
    private ProyekAkhir pa;
    private Connection conn = Koneksi.bukaKoneksi();
    private JadwalSidang jadwalFix;
    private ArrayList<Dosen> dosenKeahlian;
    private Object[] Ruangan = new ListData().getAllRuangan();
    
    public AlgoritmaPenjadwalan(int id_keahlian, int tahun, int periode, String dosbing1, String dosbing2, String judul,String nim, int mode){
        dosenKeahlian = new ListData().getDosenByKeahlian(id_keahlian, dosbing1, dosbing2);
        getDataPeriodeSidang(tahun,periode);
        Date tanggalAwal;
        System.out.println("Jadwal : "+this.jadwalFix);
        pa = new ProyekAkhir(judul,nim,dosbing1,dosbing2,id_keahlian);
        System.out.println("DEBUG Constructor");
        try {
            tanggalAwal = new SimpleDateFormat("yyyy-MM-dd").parse(periodeSidang.getTanggalAwal());
            Date tanggalAkhir = new SimpleDateFormat("yyyy-MM-dd").parse(periodeSidang.getTanggalAkhir());;
            tanggals = JarakTanggal(tanggalAwal,tanggalAkhir);
            if(mode == 1){
                getJadwalFix(periode,dosbing1,dosbing2);
            }
            else if(mode == 2){
                this.getJadwalFixTanpaDosen(periode,dosbing1,dosbing2);
            }
            
            //tambahJadwalSidang(this.jadwalFix);
            //tambahJatah(this.jadwalFix.getKDPenguji1(), this.jadwalFix.getKDPenguji2());
            System.out.println("Sukses");
            //ArrayList<Dosen> dosenDapat = new getDosenFix();
        } catch (ParseException ex) {
            Logger.getLogger(AlgoritmaPenjadwalan.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //tambahJatah(dosen1,dosen2);
        
    }
    
    public JadwalSidang getJadwalFixed() {
        return jadwalFix;
    }
    
    public List<Date> JarakTanggal(Date startDate, Date endDate) {
        List<Date> datesInRange = new ArrayList<>();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(startDate);

        Calendar endCalendar = new GregorianCalendar();
        endCalendar.setTime(endDate);

        while (calendar.before(endCalendar)) {
            Date result = calendar.getTime();
            datesInRange.add(result);
            calendar.add(Calendar.DATE, 1);
        }
        return datesInRange;
    }
    
    private void getDataPeriodeSidang(int tahun,int periode){
        String query = "SELECT * FROM periode_sidang WHERE tahun="+tahun+" AND periode_ke="+periode;
        try {
            PreparedStatement ps = this.conn.prepareStatement(query);
            ResultSet res = ps.executeQuery();
            while(res.next()){
                int id_periode = res.getInt("id_periode");
                int tahuns = res.getInt("tahun");
                String tanggal_awal = res.getString("tanggal_awal");
                String tanggal_akhir = res.getString("tanggal_akhir");
                int periodeKe = res.getInt("periode_ke");
                this.periodeSidang = new PeriodeSidang(id_periode,tahuns,tanggal_awal,tanggal_akhir,periodeKe);
                break;
            }
            res.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(AlgoritmaPenjadwalan.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    
    }
    
    
   private void getJadwalFix(int periode, String dosbing1, String dosbing2){
       int debug = 0;
       //System.out.println("terpanggil per jadwal");
        for(Dosen d:this.dosenKeahlian){
            System.out.println(d.getKodeDosen());
            for(Dosen d2:this.dosenKeahlian){
                if(d.getKodeDosen().equals(d2.getKodeDosen())){
                    continue;
                }
                else {
       //             System.out.println(d2.getKodeDosen());
                    boolean ada = false;
                    boolean dapat = false;
               //     System.out.println(dapat);
                    for(Date date: tanggals){
                        if(dapat == true){
                            break;
                        }
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(date);
                        int hari = cal.get(Calendar.DAY_OF_WEEK);
                        if(hari == 6 || hari == 1){
                            continue;
                        }
                        hari -= 1;
           //             System.out.println(date);
             //           System.out.println(hari);
                        for(String w: this.jam){
                            if(dapat == true){
                                break;
                            }
                            //System.out.println(w.toString());
                            for(Object r: this.Ruangan){
                                if(dapat == true){
                                    break;
                                }
                                //System.out.println("Ruangan : "+r);
                                String query = "SELECT * FROM jadwal_mengajar_dosen WHERE (TIME(\""+w+"\") BETWEEN jam_mulai AND jam_selesai) "
                                        + "OR (DATE_ADD(TIME(\""+w+"\"), interval 2 hour) BETWEEN jam_mulai AND jam_selesai) AND hari_ke="+hari+" "
                                        + "AND (kode_dosen='"+d.getKodeDosen()+"' OR kode_dosen='"+d2.getKodeDosen()+"' OR kode_dosen='"+dosbing1+"'"
                                        + " OR kode_dosen='"+dosbing2+"')";
                                
         //                       System.out.println(query);
                                try {
                                    Statement ps = this.conn.createStatement();
                                    ResultSet res = ps.executeQuery(query);
                                    res.last();
                                    int jumlah = res.getRow();
                                    res.beforeFirst();
                 //                   System.out.println("Get Row jaddos: "+jumlah);
                                    if(jumlah > 0){
                                        ada = true;
                   //                     System.out.println(ada+" Query 1 :"+res.getRow());
                                    }
                                    res.close();
                                    ps.close();
                                    String query2 = "SELECT * FROM jadwal_sidang JOIN proyek_akhir ON jadwal_sidang.id_pa = proyek_akhir.id WHERE ((TIME(\""+w+"\") BETWEEN jadwal_sidang.jam_mulai AND jadwal_sidang.jam_selesai) "
                                            + "OR (DATE_ADD(TIME(\""+w+"\"), interval 2 hour) BETWEEN jadwal_sidang.jam_mulai AND jadwal_sidang.jam_selesai)) AND jadwal_sidang.tanggal=DATE('"+new SimpleDateFormat("yyyy-MM-dd").format(date)+"') "
                                            + "AND (jadwal_sidang.kode_penguji1='"+d.getKodeDosen()+"' OR jadwal_sidang.kode_penguji1='"+d2.getKodeDosen()+"' OR jadwal_sidang.kode_penguji1='"+dosbing1+"' OR jadwal_sidang.kode_penguji1='"+dosbing2+"') "
                                            + "AND (jadwal_sidang.kode_penguji2='"+d.getKodeDosen()+"' OR jadwal_sidang.kode_penguji2='"+d2.getKodeDosen()+"' OR jadwal_sidang.kode_penguji2='"+dosbing1+"' OR jadwal_sidang.kode_penguji2='"+dosbing2+"') "
                                            + "AND (proyek_akhir.kode_dosbing1='"+d.getKodeDosen()+"' OR proyek_akhir.kode_dosbing1='"+d2.getKodeDosen()+"' OR proyek_akhir.kode_dosbing1='"+dosbing1+"' OR proyek_akhir.kode_dosbing1='"+dosbing2+"') "
                                            + "AND (proyek_akhir.kode_dosbing2='"+d.getKodeDosen()+"' OR proyek_akhir.kode_dosbing2='"+d2.getKodeDosen()+"' OR proyek_akhir.kode_dosbing2='"+dosbing1+"' OR proyek_akhir.kode_dosbing2='"+dosbing2+"') ";
                                            System.out.println(query2);
                                    try {
                                        Statement ps2 = this.conn.createStatement();
                                        ResultSet res2 = ps2.executeQuery(query2);
                                        res2.last();
                                        int jumlah2 = res2.getRow();
                                        res2.beforeFirst();
                       //                 System.out.println("Get Row : "+jumlah);
                                        if(jumlah2 > 0){
                                            ada = true;
                         //                   System.out.println(ada+" Query 2 : "+res2.getRow());
                                        }
                                        res2.close();
                                        ps2.close();
                                        
                                        String query3 = "SELECT * FROM jadwal_sidang WHERE ((TIME(\""+w+"\") BETWEEN jam_mulai AND jam_selesai) "
                                                + "OR (DATE_ADD(TIME(\""+w+"\"), interval 2 hour) BETWEEN jam_mulai AND jam_selesai)) AND tanggal=DATE('"+new SimpleDateFormat("yyyy-MM-dd").format(date)+"') "
                                                + "AND id_ruangan="+r;
                           //                     System.out.println(query2);
                                        
                                        try {
                                            Statement ps3 = this.conn.createStatement();
                                            ResultSet res3 = ps3.executeQuery(query3);
                                            res3.last();
                                            int jumlah3 = res3.getRow();
                                            res3.beforeFirst();
                             //               System.out.println("Get Row : "+jumlah3);
                                            if(jumlah3 > 0){
                                                ada = true;
                                            }
                                            res3.close();
                                            ps3.close();
                                            if(ada == false){
                                                DateFormat formatt = new SimpleDateFormat("HH:mm");
                                                dapat = true;
                                                java.sql.Time waktu;
                                                try {
                                                    waktu = new java.sql.Time(formatt.parse(w).getTime());
                                                    System.out.println("Dapat Jadwal, Pada Tanggal : "+new SimpleDateFormat("yyyy-MM-dd").format(date)+", Dimulai Jam : "+w+", Dengan Dosen Penguji1 : "+d.getKodeDosen()+" Dan Dosen Penguji 2 "+d2.getKodeDosen());
                                                    int id_pa = this.tambahPA(pa.getJudulPa(), pa.getKDDosbing1(), pa.getKDDosbing2(), pa.getIdKeahlian(), pa.getNim());
                                                    this.jadwalFix = new JadwalSidang(Integer.parseInt(r.toString()), id_pa, date, d.getKodeDosen(), d2.getKodeDosen(), waktu, waktu, periode);
                                                     //System.out.println("Tereksekusi : "+debug++);
                                                    ada = true;
                                                    return;
                                                } catch (ParseException ex) {
                                                    Logger.getLogger(AlgoritmaPenjadwalan.class.getName()).log(Level.SEVERE, null, ex);
                                                }
                                                                                                //tambahJadwalSidang(jadwalFix);
                                                //tambahJatah(d.getKodeDosen(),d2.getKodeDosen());
                                                break;
                                            }
                                        }
                                        catch (SQLException ex) {
                                            Logger.getLogger(AlgoritmaPenjadwalan.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }
                                    catch (SQLException ex) {
                                        Logger.getLogger(AlgoritmaPenjadwalan.class.getName()).log(Level.SEVERE, null, ex);
                                    }

                                    
                                } catch (SQLException ex) {
                                    Logger.getLogger(AlgoritmaPenjadwalan.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                //System.out.println("Ada : "+ada);
                                ada = false;
                            }
                        }
                    }
                }
            }
        }
       
    }
   
   private void getJadwalFixTanpaDosen(int periode,String dosbing1, String dosbing2) throws ParseException{
       System.out.println("Terpanggil");
       for(Dosen d:this.dosenKeahlian){
            System.out.println(d.getKodeDosen());
            for(Dosen d2:this.dosenKeahlian){
                if(d.getKodeDosen().equals(d2.getKodeDosen())){
                    continue;
                }
                else {
                    System.out.println(d2.getKodeDosen());
                    boolean ada = false;
                    boolean dapat = false;
                    System.out.println(dapat);
                    for(Date date: tanggals){
                        if(dapat == true){
                            break;
                        }
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(date);
                        int hari = cal.get(Calendar.DAY_OF_WEEK);
                        if(hari == 6 || hari == 1){
                            continue;
                        }
                        hari -= 1;
                        System.out.println(date);
                        System.out.println(hari);
                        for(String w: this.jam){
                            if(dapat == true){
                                break;
                            }
                            //System.out.println(w.toString());
                            for(Object r: this.Ruangan){
                                if(dapat == true){
                                    break;
                                }
                                //System.out.println("Ruangan : "+r);
                                
                                
                                String query2 = "SELECT * FROM jadwal_sidang JOIN proyek_akhir ON jadwal_sidang.id_pa = proyek_akhir.id WHERE ((TIME(\""+w+"\") BETWEEN jadwal_sidang.jam_mulai AND jadwal_sidang.jam_selesai) "
                                            + "OR (DATE_ADD(TIME(\""+w+"\"), interval 2 hour) BETWEEN jadwal_sidang.jam_mulai AND jadwal_sidang.jam_selesai)) AND jadwal_sidang.tanggal=DATE('"+new SimpleDateFormat("yyyy-MM-dd").format(date)+"') "
                                            + "AND (jadwal_sidang.kode_penguji1='"+d.getKodeDosen()+"' OR jadwal_sidang.kode_penguji1='"+d2.getKodeDosen()+"' OR jadwal_sidang.kode_penguji1='"+dosbing1+"' OR jadwal_sidang.kode_penguji1='"+dosbing2+"') "
                                            + "AND (jadwal_sidang.kode_penguji2='"+d.getKodeDosen()+"' OR jadwal_sidang.kode_penguji2='"+d2.getKodeDosen()+"' OR jadwal_sidang.kode_penguji2='"+dosbing1+"' OR jadwal_sidang.kode_penguji2='"+dosbing2+"') "
                                            + "AND (proyek_akhir.kode_dosbing1='"+d.getKodeDosen()+"' OR proyek_akhir.kode_dosbing1='"+d2.getKodeDosen()+"' OR proyek_akhir.kode_dosbing1='"+dosbing1+"' OR proyek_akhir.kode_dosbing1='"+dosbing2+"') "
                                            + "AND (proyek_akhir.kode_dosbing2='"+d.getKodeDosen()+"' OR proyek_akhir.kode_dosbing2='"+d2.getKodeDosen()+"' OR proyek_akhir.kode_dosbing2='"+dosbing1+"' OR proyek_akhir.kode_dosbing2='"+dosbing2+"') ";

                                System.out.println(query2);
                                    try {
                                        Statement ps2 = this.conn.createStatement();
                                        ResultSet res2 = ps2.executeQuery(query2);
                                        res2.last();
                                        int jumlah2 = res2.getRow();
                                        res2.beforeFirst();
                                        if(jumlah2 > 0){
                                            ada = true;
                                            System.out.println(ada+" Query 2 : "+res2.getRow());
                                        }
                                        res2.close();
                                        ps2.close();
                                        
                                        String query3 = "SELECT * FROM jadwal_sidang WHERE ((TIME(\""+w+"\") BETWEEN jam_mulai AND jam_selesai) "
                                                + "OR (DATE_ADD(TIME(\""+w+"\"), interval 2 hour) BETWEEN jam_mulai AND jam_selesai)) AND tanggal=DATE('"+new SimpleDateFormat("yyyy-MM-dd").format(date)+"') "
                                                + "AND id_ruangan="+r;
                                                System.out.println(query2);
                                        try {
                                            Statement ps3 = this.conn.createStatement();
                                            ResultSet res3 = ps3.executeQuery(query3);
                                            res3.last();
                                            int jumlah3 = res3.getRow();
                                            res3.beforeFirst();
                                            System.out.println("Get Row : "+jumlah3);
                                            if(jumlah3 > 0){
                                                ada = true;
                                            }
                                            res3.close();
                                            ps3.close();
                                            if(ada == false){
                                                DateFormat formatt = new SimpleDateFormat("HH:mm");
                                                dapat = true;
                                                java.sql.Time waktu = new java.sql.Time(formatt.parse(w).getTime());
                                                System.out.println("Dapat Jadwal, Pada Tanggal : "+new SimpleDateFormat("yyyy-MM-dd").format(date)+", Dimulai Jam : "+w+", Dengan Dosen Penguji1 : "+d.getKodeDosen()+" Dan Dosen Penguji 2 "+d2.getKodeDosen());
                                                int id_pa = this.tambahPA(pa.getJudulPa(), pa.getKDDosbing1(), pa.getKDDosbing2(), pa.getIdKeahlian(), pa.getNim());
                                                this.jadwalFix = new JadwalSidang(Integer.parseInt(r.toString()), id_pa, date, d.getKodeDosen(), d2.getKodeDosen(), waktu, waktu, periode);
                                                //tambahJadwalSidang(jadwalFix);
                                                //tambahJatah(d.getKodeDosen(),d2.getKodeDosen());
                                                
                                                        
                                                return;
                                            }
                                        }
                                        catch (SQLException ex) {
                                            Logger.getLogger(AlgoritmaPenjadwalan.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }
                                    catch (SQLException ex) {
                                        Logger.getLogger(AlgoritmaPenjadwalan.class.getName()).log(Level.SEVERE, null, ex);
                                    }

                                    
                                } 
                                //System.out.println("Ada : "+ada);
                                ada = false;
                            }
                        }
                    }
                }
            }
        }
    
    private int tambahPA(String judul, String p1, String p2, int id_keahlian, String nim){
        this.pa = new ProyekAkhir(judul,nim,p1,p2,id_keahlian);
        String query = "INSERT INTO proyek_akhir (judul,kode_dosbing1,kode_dosbing2,id_keahlian,nim,status) "
                + "VALUES ('"+judul+"', '"+p1+"', '"+p2+"', "+id_keahlian+", '"+nim+"', 1)";
        PreparedStatement ps;
        int id_pa = 0;
        System.out.println("PA Ditambahkan");
        try {
            ps = this.conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.executeUpdate();
            ResultSet res = ps.getGeneratedKeys();
            while(res.next()){
                id_pa = res.getInt(1);
            }
            res.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(AlgoritmaPenjadwalan.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id_pa;
        
    }
    
    
    
    
   
}
