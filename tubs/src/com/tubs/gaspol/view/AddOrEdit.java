/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tubs.gaspol.view;

import com.tubs.gaspol.db.CRUD;
import com.tubs.gaspol.item.Dosen;
import com.tubs.gaspol.item.JadwalSidang;
import com.tubs.gaspol.item.Keahlian;
import com.tubs.gaspol.item.Mahasiswa;
import com.tubs.gaspol.item.Periode;
import com.tubs.gaspol.item.ProyekAkhir;
import com.tubs.gaspol.item.Ruangan;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;

import com.tubs.gaspol.list.ListData;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DORIZU
 */
public class AddOrEdit extends javax.swing.JFrame {
    String temp,temp2 = null;
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    DateFormat tf = new SimpleDateFormat("HH:mm:ss");
    
    /**
     * Creates new form AddOrEdit
     */
    public AddOrEdit(String key, String operation, DefaultTableModel model, int baris) {
        initComponents();
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_HORIZ);
        setVisible(true);
        setResizable(false);
        //load combo keahlian
        for(Keahlian k :new ListData().getKeahlian()){
            cbKeahlian1.addItem(k.getNamaKeahlian());
            cbKeahlian2.addItem(k.getNamaKeahlian());
            cbKeahlian.addItem(k.getNamaKeahlian());
        }
        //loadcbPenguji
        for(Dosen d : new ListData().getAllDosen()){
            cbUji1.addItem(d.getKodeDosen());
            cbUji2.addItem(d.getKodeDosen());
            cbPemb1.addItem(d.getKodeDosen());
            cbPemb2.addItem(d.getKodeDosen());
            cdKodes.addItem(d.getKodeDosen());
        }
        //loadcbruang
        for(Ruangan r : new ListData().getRuang()){
            cbRuang.addItem(r.getNamaRuang());
        }
        //loadPeriode
        for(Periode p : new ListData().getPeriode()){
            cbPeriode.addItem(p.getTahun()+"-"+p.getPeriodeKe());
        }
        //loadPA
        for(ProyekAkhir p : new ListData().getPA()){
            cbPA.addItem(p.getIdPA()+"-"+p.getJudulPa());
        }
        //loadMhs
        for(Mahasiswa m : new ListData().getAllMahasiswa()){
            cbNim1.addItem(m.getNim());
            cbNim2.addItem(m.getNim());
        }
        
        switch(key){
                case "dosen":
                    main.removeAll();
                    main.repaint();
                    main.revalidate();
                    
                    main.add(dosen);
                    main.repaint();
                    main.revalidate();
                    
                    if(operation.equals("add")){
                        titleDosen.setText("Tambah Dosen");
                    }else{
                        titleDosen.setText("Edit Dosen");
                        submit.setText("Simpan");
                        
                        tfNama.setText(model.getValueAt(baris, 0).toString());
                        tfEmail.setText(model.getValueAt(baris, 5).toString());
                        tfKodeDosen.setText(model.getValueAt(baris, 1).toString());
                        tfNip.setText(model.getValueAt(baris, 2).toString());
                        cbKeahlian1.setSelectedItem(model.getValueAt(baris, 3));
                        cbKeahlian2.setSelectedItem(model.getValueAt(baris, 4));
                        //ambilKodeAwal, kep.apabila buat update
                        temp = model.getValueAt(baris, 1).toString();
                    }
                    
                break;
                case "jadwalsidang":
                    main.removeAll();
                    main.repaint();
                    main.revalidate();
                    
                    main.add(jadwalSidang);
                    main.repaint();
                    main.revalidate();
                    tglSidang.setDateFormatString("yyyy-MM-dd");
                    
                    if(operation.equals("add")){
                        titleSidang.setText("Tambah Sidang");
                        submitSidang.setText("Tambah");
                    }else{
                        titleSidang.setText("Edit Sidang");
                        submitSidang.setText("Simpan");
                        
                        jmMulaiSidang.setValue(model.getValueAt(baris, 1));
                        jmSelesaiSidang.setValue(model.getValueAt(baris, 2));
                        cbPA.setSelectedItem(model.getValueAt(baris, 3));
                        try {
                            tglSidang.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(model.getValueAt(baris, 0).toString()));
                        } catch (ParseException ex) {
                            Logger.getLogger(AddOrEdit.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        cbPeriode.setSelectedItem(model.getValueAt(baris, 4));
                        cbRuang.setSelectedItem(model.getValueAt(baris, 5));
                        cbUji1.setSelectedItem(model.getValueAt(baris, 6));
                        cbUji2.setSelectedItem(model.getValueAt(baris, 7));
                        
                        temp = model.getValueAt(baris, 3).toString().split("-")[0];
                    }
                break;
                case "jddosen":
                    main.removeAll();
                    main.repaint();
                    main.revalidate();
                    
                    main.add(jadwalDosen);
                    main.repaint();
                    main.revalidate();
                    
                    if(operation.equals("add")){
                        titleJdDosen.setText("Tambah Jadwal Dosen");
                        submitJd.setText("Tambah");
                    }else{
                        titleJdDosen.setText("Edit Jadwal Dosen");
                        submitJd.setText("Simpan");
                        
                        cdKodes.setSelectedItem(model.getValueAt(baris, 1));
                        jmMulai.setValue(model.getValueAt(baris, 3));
                        jmSelesai.setValue(model.getValueAt(baris, 4));
                        cbHari.setSelectedItem(model.getValueAt(baris, 2));
                        
                        temp = model.getValueAt(baris, 0).toString();
                    }
                break;
                case "keahlian":
                    main.removeAll();
                    main.repaint();
                    main.revalidate();
                    
                    main.add(keahlian);
                    main.repaint();
                    main.revalidate();
                    
                    if(operation.equals("add")){
                        titleKeahlian.setText("Tambah Keahlian");
                        submitKeahlian.setText("Tambah");
                    }else{
                        titleKeahlian.setText("Edit Keahlian");
                        submitKeahlian.setText("Simpan");
                        
                        tfNamaKeahlian.setText(model.getValueAt(baris, 1).toString());
                        
                        //ambilNamaAwal, kep.apabila buat update
                        temp = model.getValueAt(baris, 1).toString();
                    }
                    break;
                case "mahasiswa":
                    main.removeAll();
                    main.repaint();
                    main.revalidate();
                    
                    main.add(mahasiswa);
                    main.repaint();
                    main.revalidate();
                    
                    if(operation.equals("add")){
                        titleMhs.setText("Tambah Mahasiswa");
                        submitMhs.setText("Tambah");
                    }else{
                        titleMhs.setText("Edit Mahasiswa");
                        submitMhs.setText("Simpan");
                        
                        tfNim.setText(model.getValueAt(baris, 0).toString());
                        tfNamaMhs.setText(model.getValueAt(baris, 1).toString());
                        tfEmailMhs.setText(model.getValueAt(baris, 2).toString());
                        
                        
                        //ambilNimAwal, kep.apabila buat update
                        temp = model.getValueAt(baris, 0).toString();
                    }
                break;
                case "ruangan":
                    main.removeAll();
                    main.repaint();
                    main.revalidate();
                    
                    main.add(ruangan);
                    main.repaint();
                    main.revalidate();
                    
                    if(operation.equals("add")){
                        titleRuang.setText("Tambah Ruangan Sidang");
                        submitRuang.setText("Tambah");
                    }else{
                        titleRuang.setText("Edit Ruangan Sidang");
                        submitRuang.setText("Simpan");
                        
                        tfNamaRuang.setText(model.getValueAt(baris, 1).toString());
                        
                                            
                        //ambilKodeAwal, kep.apabila buat update
                        temp = model.getValueAt(baris, 0).toString();
                    }
                    break;
                case "periode":
                    main.removeAll();
                    main.repaint();
                    main.revalidate();
                    
                    main.add(periode);
                    main.repaint();
                    main.revalidate();
                    
                    dtAwal.setDateFormatString("yyyy-MM-dd");
                    dtAkhir.setDateFormatString("yyyy-MM-dd");
                    
                    if(operation.equals("add")){
                        titlePeriode.setText("Tambah Periode");
                        submitPeriode.setText("Tambah");
                    }else{
                        titlePeriode.setText("Edit Periode");
                        submitPeriode.setText("Simpan");
                        
                        tfPeriode.setText(model.getValueAt(baris, 0).toString());
                        String dateAwal = model.getValueAt(baris, 1).toString();
                        String dateAkhir = model.getValueAt(baris, 2).toString();
                        
                        
                        
                        Date d1 = null;
                        Date d2 = null;
                        try {
                            d1 = df.parse(dateAwal);
                            d2 = df.parse(dateAkhir);
                        } catch (ParseException ex) {
                            Logger.getLogger(AddOrEdit.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                        dtAwal.setDate(d1);
                        dtAkhir.setDate(d2);
                                                                    
                        //ambilKodeAwal, kep.apabila buat update
                        temp = model.getValueAt(baris, 0).toString();
                    }
                    break;
                case "pa":
                    main.removeAll();
                    main.repaint();
                    main.revalidate();
                    
                    main.add(pa);
                    main.repaint();
                    main.revalidate();
                    
                    if(operation.equals("add")){
                        titlePA.setText("Tambah Proyek Akhir");
                        submitPA.setText("Tambah");
                        cbStatus.setSelectedIndex(1);
                        cbStatus.removeAllItems();
                        cbStatus.addItem("Belum Terjadwal");
                        
                    }else{
                        titlePA.setText("Edit Proyek Akhir");
                        submitPA.setText("Simpan");
                        
                        tfJudulPA.setText(model.getValueAt(baris, 0).toString());
                        cbPemb1.setSelectedItem(model.getValueAt(baris, 1));
                        cbPemb2.setSelectedItem(model.getValueAt(baris, 2));
                        cbKeahlian.setSelectedItem(model.getValueAt(baris, 3));
                        cbStatus.setSelectedItem(model.getValueAt(baris, 4));
                        cbNim1.setSelectedItem(model.getValueAt(baris, 5));
                        cbNim2.setSelectedItem(model.getValueAt(baris, 6));
                                            
                        //ambilNim1Awal, kep.apabila buat update
                        temp = model.getValueAt(baris, 5).toString();
                        temp2 = model.getValueAt(baris, 6).toString();
                    }
                    break;
        }
        
        
        
        
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        main = new javax.swing.JPanel();
        dosen = new javax.swing.JPanel();
        header = new javax.swing.JPanel();
        titleDosen = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tfNama = new javax.swing.JTextField();
        tfEmail = new javax.swing.JTextField();
        tfKodeDosen = new javax.swing.JTextField();
        tfNip = new javax.swing.JTextField();
        cbKeahlian1 = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        cbKeahlian2 = new javax.swing.JComboBox();
        submit = new javax.swing.JButton();
        jadwalSidang = new javax.swing.JPanel();
        header1 = new javax.swing.JPanel();
        titleSidang = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        cbUji1 = new javax.swing.JComboBox();
        jLabel16 = new javax.swing.JLabel();
        cbUji2 = new javax.swing.JComboBox();
        submitSidang = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        cbRuang = new javax.swing.JComboBox();
        cbPeriode = new javax.swing.JComboBox();
        cbPA = new javax.swing.JComboBox();
        tglSidang = new com.toedter.calendar.JDateChooser();
        Date date3 = new Date();
        SpinnerDateModel sm3 = new SpinnerDateModel(date3, null, null, Calendar.HOUR_OF_DAY);
        jmMulaiSidang = new javax.swing.JSpinner(sm3);
        Date date4 = new Date();
        SpinnerDateModel sm4 = new SpinnerDateModel(date4, null, null, Calendar.HOUR_OF_DAY);
        jmSelesaiSidang = new javax.swing.JSpinner(sm4);
        jadwalDosen = new javax.swing.JPanel();
        header2 = new javax.swing.JPanel();
        titleJdDosen = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        submitJd = new javax.swing.JButton();
        cbHari = new javax.swing.JComboBox();
        cdKodes = new javax.swing.JComboBox();
        Date date = new Date();
        SpinnerDateModel sm = new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
        jmMulai = new javax.swing.JSpinner(sm);
        Date date1 = new Date();
        SpinnerDateModel sm1 = new SpinnerDateModel(date1, null, null, Calendar.HOUR_OF_DAY);
        jmSelesai = new javax.swing.JSpinner(sm1);
        keahlian = new javax.swing.JPanel();
        header3 = new javax.swing.JPanel();
        titleKeahlian = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        tfNamaKeahlian = new javax.swing.JTextField();
        submitKeahlian = new javax.swing.JButton();
        mahasiswa = new javax.swing.JPanel();
        header4 = new javax.swing.JPanel();
        titleMhs = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        tfNamaMhs = new javax.swing.JTextField();
        tfEmailMhs = new javax.swing.JTextField();
        tfNim = new javax.swing.JTextField();
        submitMhs = new javax.swing.JButton();
        ruangan = new javax.swing.JPanel();
        header5 = new javax.swing.JPanel();
        titleRuang = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        tfNamaRuang = new javax.swing.JTextField();
        submitRuang = new javax.swing.JButton();
        periode = new javax.swing.JPanel();
        header6 = new javax.swing.JPanel();
        titlePeriode = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        tfPeriode = new javax.swing.JTextField();
        submitPeriode = new javax.swing.JButton();
        dtAwal = new com.toedter.calendar.JDateChooser();
        dtAkhir = new com.toedter.calendar.JDateChooser();
        pa = new javax.swing.JPanel();
        header7 = new javax.swing.JPanel();
        titlePA = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        tfJudulPA = new javax.swing.JTextField();
        submitPA = new javax.swing.JButton();
        cbKeahlian = new javax.swing.JComboBox();
        cbPemb2 = new javax.swing.JComboBox();
        cbPemb1 = new javax.swing.JComboBox();
        jLabel34 = new javax.swing.JLabel();
        cbStatus = new javax.swing.JComboBox();
        jLabel33 = new javax.swing.JLabel();
        cbNim2 = new javax.swing.JComboBox();
        cbNim1 = new javax.swing.JComboBox();

        setMaximumSize(new java.awt.Dimension(508, 547));

        main.setMaximumSize(new java.awt.Dimension(508, 547));
        main.setLayout(new java.awt.CardLayout());

        dosen.setMaximumSize(new java.awt.Dimension(508, 547));

        header.setBackground(new java.awt.Color(255, 102, 102));

        titleDosen.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        titleDosen.setForeground(new java.awt.Color(255, 255, 255));
        titleDosen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleDosen.setText("Tambah Dosen");

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addComponent(titleDosen, javax.swing.GroupLayout.PREFERRED_SIZE, 513, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(titleDosen)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Email");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Nama");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Kode Dosen");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("NIP");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Keahlian 1");

        tfNama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNamaActionPerformed(evt);
            }
        });

        tfEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfEmailActionPerformed(evt);
            }
        });

        tfKodeDosen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfKodeDosenActionPerformed(evt);
            }
        });

        tfNip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNipActionPerformed(evt);
            }
        });

        cbKeahlian1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- Pilih Keahlian -" }));
        cbKeahlian1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbKeahlian1ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Keahlian 2");

        cbKeahlian2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- Pilih Keahlian -" }));
        cbKeahlian2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbKeahlian2ActionPerformed(evt);
            }
        });

        submit.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        submit.setText("Tambah");
        submit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                submitMouseClicked(evt);
            }
        });
        submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout dosenLayout = new javax.swing.GroupLayout(dosen);
        dosen.setLayout(dosenLayout);
        dosenLayout.setHorizontalGroup(
            dosenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dosenLayout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addGroup(dosenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addGroup(dosenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dosenLayout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tfKodeDosen, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dosenLayout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dosenLayout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addGap(107, 107, 107)
                            .addComponent(tfNama, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dosenLayout.createSequentialGroup()
                            .addGroup(dosenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5)
                                .addComponent(jLabel6))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(dosenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(submit)
                                .addComponent(tfNip, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cbKeahlian1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cbKeahlian2, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(dosenLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        dosenLayout.setVerticalGroup(
            dosenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dosenLayout.createSequentialGroup()
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(dosenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tfNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(dosenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(dosenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tfKodeDosen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(dosenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tfNip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(dosenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cbKeahlian1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(dosenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cbKeahlian2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(108, 108, 108)
                .addComponent(submit)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        main.add(dosen, "card2");

        jadwalSidang.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jadwalSidang.setMaximumSize(new java.awt.Dimension(508, 547));

        header1.setBackground(new java.awt.Color(255, 102, 102));

        titleSidang.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        titleSidang.setForeground(new java.awt.Color(255, 255, 255));
        titleSidang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleSidang.setText("Tambah Sidang");

        javax.swing.GroupLayout header1Layout = new javax.swing.GroupLayout(header1);
        header1.setLayout(header1Layout);
        header1Layout.setHorizontalGroup(
            header1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titleSidang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        header1Layout.setVerticalGroup(
            header1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(header1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(titleSidang)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Jam Selesai");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("Jam Mulai");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("Tanggal");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText("Proyek Akhir");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setText("Kode Penguji 1");

        cbUji1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- Pilih Dosen -" }));

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel16.setText("Kode Penguji 2");

        cbUji2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- Pilih Dosen -" }));

        submitSidang.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        submitSidang.setText("Simpan");
        submitSidang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                submitSidangMouseClicked(evt);
            }
        });
        submitSidang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitSidangActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel17.setText("Ruang");

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel18.setText("Periode ke");

        cbRuang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- Pilih Ruang -" }));

        cbPeriode.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- Pilih Periode -" }));

        cbPA.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- Pilih PA -" }));
        cbPA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbPAActionPerformed(evt);
            }
        });

        JSpinner.DateEditor de3 = new JSpinner.DateEditor(jmMulaiSidang, "HH:mm:ss");
        jmMulaiSidang.setEditor(de3);

        JSpinner.DateEditor de4 = new JSpinner.DateEditor(jmSelesaiSidang, "HH:mm:ss");
        jmSelesaiSidang.setEditor(de4);

        javax.swing.GroupLayout jadwalSidangLayout = new javax.swing.GroupLayout(jadwalSidang);
        jadwalSidang.setLayout(jadwalSidangLayout);
        jadwalSidangLayout.setHorizontalGroup(
            jadwalSidangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jadwalSidangLayout.createSequentialGroup()
                .addGroup(jadwalSidangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jadwalSidangLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(header1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jadwalSidangLayout.createSequentialGroup()
                        .addGroup(jadwalSidangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jadwalSidangLayout.createSequentialGroup()
                                .addGap(209, 209, 209)
                                .addComponent(submitSidang))
                            .addGroup(jadwalSidangLayout.createSequentialGroup()
                                .addGap(73, 73, 73)
                                .addGroup(jadwalSidangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jadwalSidangLayout.createSequentialGroup()
                                        .addComponent(jLabel18)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cbPeriode, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jadwalSidangLayout.createSequentialGroup()
                                        .addComponent(jLabel13)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(tglSidang, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jadwalSidangLayout.createSequentialGroup()
                                        .addComponent(jLabel16)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cbUji2, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jadwalSidangLayout.createSequentialGroup()
                                        .addComponent(jLabel17)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cbRuang, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jadwalSidangLayout.createSequentialGroup()
                                        .addComponent(jLabel12)
                                        .addGap(107, 107, 107)
                                        .addComponent(jmMulaiSidang, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jadwalSidangLayout.createSequentialGroup()
                                        .addComponent(jLabel14)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cbPA, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jadwalSidangLayout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jmSelesaiSidang, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jadwalSidangLayout.createSequentialGroup()
                                        .addComponent(jLabel15)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cbUji1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 65, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jadwalSidangLayout.setVerticalGroup(
            jadwalSidangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jadwalSidangLayout.createSequentialGroup()
                .addComponent(header1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(jadwalSidangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jmMulaiSidang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jadwalSidangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11)
                    .addComponent(jmSelesaiSidang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(jadwalSidangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel18)
                    .addComponent(cbPeriode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jadwalSidangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel13)
                    .addComponent(tglSidang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jadwalSidangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel14)
                    .addComponent(cbPA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jadwalSidangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbUji1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addGap(18, 18, 18)
                .addGroup(jadwalSidangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel16)
                    .addComponent(cbUji2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jadwalSidangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(cbRuang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54)
                .addComponent(submitSidang)
                .addGap(27, 27, 27))
        );

        main.add(jadwalSidang, "card2");

        jadwalDosen.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jadwalDosen.setMaximumSize(new java.awt.Dimension(508, 547));

        header2.setBackground(new java.awt.Color(255, 102, 102));

        titleJdDosen.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        titleJdDosen.setForeground(new java.awt.Color(255, 255, 255));
        titleJdDosen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleJdDosen.setText("Tambah Jadwal Dosen");

        javax.swing.GroupLayout header2Layout = new javax.swing.GroupLayout(header2);
        header2.setLayout(header2Layout);
        header2Layout.setHorizontalGroup(
            header2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titleJdDosen, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 513, Short.MAX_VALUE)
        );
        header2Layout.setVerticalGroup(
            header2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(header2Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(titleJdDosen)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel19.setText("Jam Mulai");

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel20.setText("Kode Dosen");

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel21.setText("Jam Selesai");

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel22.setText("Hari Ke-");

        submitJd.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        submitJd.setText("Simpan");
        submitJd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                submitJdMouseClicked(evt);
            }
        });
        submitJd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitJdActionPerformed(evt);
            }
        });

        cbHari.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- Pilih Hari -", "Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu", " " }));

        cdKodes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- Pilih Kode Dosen -" }));

        JSpinner.DateEditor de = new JSpinner.DateEditor(jmMulai, "HH:mm:ss");
        jmMulai.setEditor(de);

        JSpinner.DateEditor d = new JSpinner.DateEditor(jmSelesai, "HH:mm:ss");
        jmSelesai.setEditor(d);

        javax.swing.GroupLayout jadwalDosenLayout = new javax.swing.GroupLayout(jadwalDosen);
        jadwalDosen.setLayout(jadwalDosenLayout);
        jadwalDosenLayout.setHorizontalGroup(
            jadwalDosenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jadwalDosenLayout.createSequentialGroup()
                .addGroup(jadwalDosenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jadwalDosenLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(header2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jadwalDosenLayout.createSequentialGroup()
                        .addGroup(jadwalDosenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jadwalDosenLayout.createSequentialGroup()
                                .addGap(73, 73, 73)
                                .addGroup(jadwalDosenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jadwalDosenLayout.createSequentialGroup()
                                        .addComponent(jLabel21)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jmSelesai, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jadwalDosenLayout.createSequentialGroup()
                                        .addGroup(jadwalDosenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jadwalDosenLayout.createSequentialGroup()
                                                .addComponent(jLabel19)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jadwalDosenLayout.createSequentialGroup()
                                                .addComponent(jLabel20)
                                                .addGap(107, 107, 107)))
                                        .addGroup(jadwalDosenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(cdKodes, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jmMulai, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jadwalDosenLayout.createSequentialGroup()
                                        .addComponent(jLabel22)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cbHari, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jadwalDosenLayout.createSequentialGroup()
                                .addGap(209, 209, 209)
                                .addComponent(submitJd)))
                        .addGap(0, 49, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jadwalDosenLayout.setVerticalGroup(
            jadwalDosenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jadwalDosenLayout.createSequentialGroup()
                .addComponent(header2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(jadwalDosenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(cdKodes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jadwalDosenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jmMulai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jadwalDosenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jmSelesai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jadwalDosenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(cbHari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(189, 189, 189)
                .addComponent(submitJd)
                .addGap(27, 27, 27))
        );

        main.add(jadwalDosen, "card2");

        keahlian.setMaximumSize(new java.awt.Dimension(508, 547));

        header3.setBackground(new java.awt.Color(255, 102, 102));

        titleKeahlian.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        titleKeahlian.setForeground(new java.awt.Color(255, 255, 255));
        titleKeahlian.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleKeahlian.setText("Tambah Keahlian");

        javax.swing.GroupLayout header3Layout = new javax.swing.GroupLayout(header3);
        header3.setLayout(header3Layout);
        header3Layout.setHorizontalGroup(
            header3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(header3Layout.createSequentialGroup()
                .addComponent(titleKeahlian, javax.swing.GroupLayout.PREFERRED_SIZE, 513, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        header3Layout.setVerticalGroup(
            header3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(header3Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(titleKeahlian)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Nama Keahlian");

        tfNamaKeahlian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNamaKeahlianActionPerformed(evt);
            }
        });

        submitKeahlian.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        submitKeahlian.setText("Tambah");
        submitKeahlian.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                submitKeahlianMouseClicked(evt);
            }
        });
        submitKeahlian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitKeahlianActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout keahlianLayout = new javax.swing.GroupLayout(keahlian);
        keahlian.setLayout(keahlianLayout);
        keahlianLayout.setHorizontalGroup(
            keahlianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(keahlianLayout.createSequentialGroup()
                .addGroup(keahlianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(keahlianLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(header3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(keahlianLayout.createSequentialGroup()
                        .addGroup(keahlianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(keahlianLayout.createSequentialGroup()
                                .addGap(73, 73, 73)
                                .addComponent(jLabel9)
                                .addGap(70, 70, 70)
                                .addComponent(tfNamaKeahlian, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(keahlianLayout.createSequentialGroup()
                                .addGap(214, 214, 214)
                                .addComponent(submitKeahlian)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        keahlianLayout.setVerticalGroup(
            keahlianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, keahlianLayout.createSequentialGroup()
                .addComponent(header3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(keahlianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(tfNamaKeahlian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 285, Short.MAX_VALUE)
                .addComponent(submitKeahlian)
                .addGap(42, 42, 42))
        );

        main.add(keahlian, "card2");

        mahasiswa.setMaximumSize(new java.awt.Dimension(508, 547));

        header4.setBackground(new java.awt.Color(255, 102, 102));

        titleMhs.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        titleMhs.setForeground(new java.awt.Color(255, 255, 255));
        titleMhs.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleMhs.setText("Tambah Mahasiswa");

        javax.swing.GroupLayout header4Layout = new javax.swing.GroupLayout(header4);
        header4.setLayout(header4Layout);
        header4Layout.setHorizontalGroup(
            header4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(header4Layout.createSequentialGroup()
                .addComponent(titleMhs, javax.swing.GroupLayout.PREFERRED_SIZE, 513, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        header4Layout.setVerticalGroup(
            header4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(header4Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(titleMhs)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Email");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Nama");

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel23.setText("NIM");

        tfNamaMhs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNamaMhsActionPerformed(evt);
            }
        });

        tfEmailMhs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfEmailMhsActionPerformed(evt);
            }
        });

        tfNim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNimActionPerformed(evt);
            }
        });

        submitMhs.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        submitMhs.setText("Tambah");
        submitMhs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                submitMhsMouseClicked(evt);
            }
        });
        submitMhs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitMhsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mahasiswaLayout = new javax.swing.GroupLayout(mahasiswa);
        mahasiswa.setLayout(mahasiswaLayout);
        mahasiswaLayout.setHorizontalGroup(
            mahasiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mahasiswaLayout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addGroup(mahasiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mahasiswaLayout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tfNim, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mahasiswaLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tfEmailMhs, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mahasiswaLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(107, 107, 107)
                        .addComponent(tfNamaMhs, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mahasiswaLayout.createSequentialGroup()
                        .addComponent(submitMhs)
                        .addGap(123, 123, 123)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(mahasiswaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(header4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        mahasiswaLayout.setVerticalGroup(
            mahasiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mahasiswaLayout.createSequentialGroup()
                .addComponent(header4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(mahasiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(tfNamaMhs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(mahasiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(tfEmailMhs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(mahasiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(tfNim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(222, 222, 222)
                .addComponent(submitMhs)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        main.add(mahasiswa, "card2");

        ruangan.setMaximumSize(new java.awt.Dimension(508, 547));

        header5.setBackground(new java.awt.Color(255, 102, 102));

        titleRuang.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        titleRuang.setForeground(new java.awt.Color(255, 255, 255));
        titleRuang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleRuang.setText("Tambah Ruang");

        javax.swing.GroupLayout header5Layout = new javax.swing.GroupLayout(header5);
        header5.setLayout(header5Layout);
        header5Layout.setHorizontalGroup(
            header5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(header5Layout.createSequentialGroup()
                .addComponent(titleRuang, javax.swing.GroupLayout.PREFERRED_SIZE, 513, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        header5Layout.setVerticalGroup(
            header5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(header5Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(titleRuang)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel25.setText("Nama");

        tfNamaRuang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNamaRuangActionPerformed(evt);
            }
        });

        submitRuang.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        submitRuang.setText("Tambah");
        submitRuang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                submitRuangMouseClicked(evt);
            }
        });
        submitRuang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitRuangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ruanganLayout = new javax.swing.GroupLayout(ruangan);
        ruangan.setLayout(ruanganLayout);
        ruanganLayout.setHorizontalGroup(
            ruanganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ruanganLayout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addGroup(ruanganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ruanganLayout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addGap(107, 107, 107)
                        .addComponent(tfNamaRuang, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ruanganLayout.createSequentialGroup()
                        .addComponent(submitRuang)
                        .addGap(123, 123, 123)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(ruanganLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(header5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        ruanganLayout.setVerticalGroup(
            ruanganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ruanganLayout.createSequentialGroup()
                .addComponent(header5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(ruanganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(tfNamaRuang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(298, 298, 298)
                .addComponent(submitRuang)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        main.add(ruangan, "card2");

        periode.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        periode.setMaximumSize(new java.awt.Dimension(508, 547));

        header6.setBackground(new java.awt.Color(255, 102, 102));

        titlePeriode.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        titlePeriode.setForeground(new java.awt.Color(255, 255, 255));
        titlePeriode.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titlePeriode.setText("Tambah Periode");

        javax.swing.GroupLayout header6Layout = new javax.swing.GroupLayout(header6);
        header6.setLayout(header6Layout);
        header6Layout.setHorizontalGroup(
            header6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titlePeriode, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 513, Short.MAX_VALUE)
        );
        header6Layout.setVerticalGroup(
            header6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(header6Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(titlePeriode)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel24.setText("Tanggal Akhir");

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel26.setText("Tanggal Awal");

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel27.setText("Periode Ke-");

        tfPeriode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfPeriodeActionPerformed(evt);
            }
        });

        submitPeriode.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        submitPeriode.setText("Simpan");
        submitPeriode.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                submitPeriodeMouseClicked(evt);
            }
        });
        submitPeriode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitPeriodeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout periodeLayout = new javax.swing.GroupLayout(periode);
        periode.setLayout(periodeLayout);
        periodeLayout.setHorizontalGroup(
            periodeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(periodeLayout.createSequentialGroup()
                .addGroup(periodeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(periodeLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(header6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(periodeLayout.createSequentialGroup()
                        .addGroup(periodeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(periodeLayout.createSequentialGroup()
                                .addGap(73, 73, 73)
                                .addGroup(periodeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, periodeLayout.createSequentialGroup()
                                        .addComponent(jLabel27)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(tfPeriode, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, periodeLayout.createSequentialGroup()
                                        .addGroup(periodeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(periodeLayout.createSequentialGroup()
                                                .addComponent(jLabel24)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, periodeLayout.createSequentialGroup()
                                                .addComponent(jLabel26)
                                                .addGap(107, 107, 107)))
                                        .addGroup(periodeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(dtAwal, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(dtAkhir, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(periodeLayout.createSequentialGroup()
                                .addGap(209, 209, 209)
                                .addComponent(submitPeriode)))
                        .addGap(0, 44, Short.MAX_VALUE)))
                .addContainerGap())
        );
        periodeLayout.setVerticalGroup(
            periodeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, periodeLayout.createSequentialGroup()
                .addComponent(header6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(periodeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26)
                    .addComponent(dtAwal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(periodeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dtAkhir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24))
                .addGap(18, 18, 18)
                .addGroup(periodeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(tfPeriode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(227, 227, 227)
                .addComponent(submitPeriode)
                .addGap(27, 27, 27))
        );

        main.add(periode, "card2");

        pa.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pa.setMaximumSize(new java.awt.Dimension(508, 547));

        header7.setBackground(new java.awt.Color(255, 102, 102));

        titlePA.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        titlePA.setForeground(new java.awt.Color(255, 255, 255));
        titlePA.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titlePA.setText("Tambah Proyek Akhir");

        javax.swing.GroupLayout header7Layout = new javax.swing.GroupLayout(header7);
        header7.setLayout(header7Layout);
        header7Layout.setHorizontalGroup(
            header7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titlePA, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        header7Layout.setVerticalGroup(
            header7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(header7Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(titlePA)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel28.setText("Kode Pembimbing 1");

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel29.setText("Judul");

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel30.setText("Kode Pembimbing 2");

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel31.setText("Keahlian");

        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel32.setText("NIM 1");

        tfJudulPA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfJudulPAActionPerformed(evt);
            }
        });

        submitPA.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        submitPA.setText("Simpan");
        submitPA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                submitPAMouseClicked(evt);
            }
        });
        submitPA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitPAActionPerformed(evt);
            }
        });

        cbKeahlian.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- Pilih Keahlian -" }));

        cbPemb2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- Pilih Kode Dosen -" }));

        cbPemb1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- Pilih Kode Dosen -" }));

        jLabel34.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel34.setText("Status");

        cbStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- Pilih Status -", "Belum Terjadwal", "Sudah Terjadwal" }));

        jLabel33.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel33.setText("NIM 2");

        cbNim2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- Pilih NIM -" }));

        cbNim1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- Pilih NIM -" }));

        javax.swing.GroupLayout paLayout = new javax.swing.GroupLayout(pa);
        pa.setLayout(paLayout);
        paLayout.setHorizontalGroup(
            paLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paLayout.createSequentialGroup()
                .addGroup(paLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(paLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(header7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(paLayout.createSequentialGroup()
                        .addGroup(paLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(paLayout.createSequentialGroup()
                                .addGap(73, 73, 73)
                                .addGroup(paLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(paLayout.createSequentialGroup()
                                        .addComponent(jLabel30)
                                        .addGap(18, 18, 18)
                                        .addComponent(cbPemb2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(paLayout.createSequentialGroup()
                                        .addComponent(jLabel28)
                                        .addGap(18, 18, 18)
                                        .addComponent(cbPemb1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paLayout.createSequentialGroup()
                                        .addComponent(jLabel29)
                                        .addGap(107, 107, 107)
                                        .addComponent(tfJudulPA, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paLayout.createSequentialGroup()
                                        .addGroup(paLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel31)
                                            .addComponent(jLabel32)
                                            .addComponent(jLabel34)
                                            .addComponent(jLabel33))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(paLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cbKeahlian, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cbNim2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cbStatus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cbNim1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(paLayout.createSequentialGroup()
                                .addGap(209, 209, 209)
                                .addComponent(submitPA)))
                        .addGap(0, 91, Short.MAX_VALUE)))
                .addContainerGap())
        );
        paLayout.setVerticalGroup(
            paLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paLayout.createSequentialGroup()
                .addComponent(header7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(paLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(tfJudulPA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(paLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(cbPemb1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(paLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(cbPemb2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(paLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(cbKeahlian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(paLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(cbNim1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(paLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(cbNim2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(paLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34))
                .addGap(75, 75, 75)
                .addComponent(submitPA)
                .addGap(27, 27, 27))
        );

        main.add(pa, "card2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(main, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(main, javax.swing.GroupLayout.PREFERRED_SIZE, 536, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfNamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNamaActionPerformed

    private void tfEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfEmailActionPerformed

    private void tfKodeDosenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfKodeDosenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfKodeDosenActionPerformed

    private void tfNipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNipActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNipActionPerformed

    private void submitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_submitActionPerformed

    private void submitSidangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitSidangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_submitSidangActionPerformed

    private void cbKeahlian1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbKeahlian1ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_cbKeahlian1ActionPerformed

    private void cbKeahlian2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbKeahlian2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbKeahlian2ActionPerformed

    private void submitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_submitMouseClicked
        // TODO add your handling code here:
        
        String nama = tfNama.getText();
        String email = tfEmail.getText();
        int keah1 = new ListData().idKeahlian(cbKeahlian1.getSelectedItem().toString());
        int keah2 = new ListData().idKeahlian(cbKeahlian2.getSelectedItem().toString());
        String kodes = tfKodeDosen.getText();
        String nip = tfNip.getText();
        
        if(submit.getText().equals("Tambah")){
            int rslt= new CRUD().tambahDosen(nama, email, keah1, keah2, kodes, nip);
            
            if(rslt > 0){
                this.dispose();
               
                JOptionPane.showMessageDialog(this, "Input Berhasil");
            }else{
                JOptionPane.showMessageDialog(this, "Input Gagal, Kode Dosen Sudah Dipakai !");
            }
        }else{
            int rslt = new CRUD().updateDosen(temp, nama, email, keah1, keah2, kodes, nip);
            if(rslt > 0){
                this.dispose();
                JOptionPane.showMessageDialog(this, "Update Berhasil");
            }else{
                JOptionPane.showMessageDialog(this, "Update Gagal, Kode Dosen Sudah Dipakai !");
            }
        }
    }//GEN-LAST:event_submitMouseClicked

    private void submitJdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitJdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_submitJdActionPerformed

    private void tfNamaKeahlianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNamaKeahlianActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNamaKeahlianActionPerformed

    private void submitKeahlianMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_submitKeahlianMouseClicked
        // TODO add your handling code here:
        String nama = tfNamaKeahlian.getText();
        
        
        if(submitKeahlian.getText().equals("Tambah")){
            int rslt= new CRUD().tambahKeahlian(nama);
            
            if(rslt > 0){
                this.dispose();
                JOptionPane.showMessageDialog(this, "Input Berhasil");
            }else{
                JOptionPane.showMessageDialog(this, "Input Gagal, Nama Keahlian Udah Ada");
            }
        }else{
            int rslt = new CRUD().updateKeahlian(temp, nama);
            if(rslt > 0){
                this.dispose();
                
                JOptionPane.showMessageDialog(this, "Update Berhasil");
            }else{
                JOptionPane.showMessageDialog(this, "Update Gagal, Nama Keahlian Udah Ada");
            }
        }
    }//GEN-LAST:event_submitKeahlianMouseClicked

    private void submitKeahlianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitKeahlianActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_submitKeahlianActionPerformed

    private void tfNamaMhsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNamaMhsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNamaMhsActionPerformed

    private void tfEmailMhsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfEmailMhsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfEmailMhsActionPerformed

    private void tfNimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNimActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNimActionPerformed

    private void submitMhsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_submitMhsMouseClicked
        // TODO add your handling code here:
        String nama = tfNamaMhs.getText();
        String email = tfEmailMhs.getText();
        String nim = tfNim.getText();
        
        if(submitMhs.getText().equals("Tambah")){
            int rslt= new CRUD().tambahMahasiswa(nim, nama, email);
            
            if(rslt > 0){
                this.dispose();
                
                JOptionPane.showMessageDialog(this, "Input Berhasil");
            }else{
                JOptionPane.showMessageDialog(this, "Input Gagal, NIM udah ada !");
            }
        }else{
            int rslt = new CRUD().updateMahasiswa(temp,nama, email, nim);
            if(rslt > 0){
                this.dispose();
                
                JOptionPane.showMessageDialog(this, "Update Berhasil");
            }else{
                JOptionPane.showMessageDialog(this, "Update Gagal, NIM udah ada !");
            }
        }
    }//GEN-LAST:event_submitMhsMouseClicked

    private void submitMhsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitMhsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_submitMhsActionPerformed

    private void tfNamaRuangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNamaRuangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNamaRuangActionPerformed

    private void submitRuangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_submitRuangMouseClicked
        // TODO add your handling code here:
        String nama = tfNamaRuang.getText();
        
        
        if(submitRuang.getText().equals("Tambah")){
            int rslt= new CRUD().tambahRuang(nama);
            
            if(rslt > 0){
                this.dispose();
                JOptionPane.showMessageDialog(this, "Input Berhasil");
            }else{
                JOptionPane.showMessageDialog(this, "Input Gagal, Nama Ruangan udah Ada !");
            }
        }else{
            int rslt = new CRUD().updateRuang(temp, nama);
            if(rslt > 0){
                this.dispose();
                
                JOptionPane.showMessageDialog(this, "Update Berhasil");
            }else{
                JOptionPane.showMessageDialog(this, "Update Gagal, Nama Ruangan udah Ada !");
            }
        }
    }//GEN-LAST:event_submitRuangMouseClicked

    private void submitRuangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitRuangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_submitRuangActionPerformed

    private void tfPeriodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfPeriodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfPeriodeActionPerformed

    private void submitPeriodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitPeriodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_submitPeriodeActionPerformed

    private void submitPeriodeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_submitPeriodeMouseClicked
        // TODO add your handling code here:
        String periodeKe = tfPeriode.getText();
        String dateAwal = df.format(dtAwal.getDate());
        String dateakhir = df.format(dtAkhir.getDate());
        
        
        if(submitPeriode.getText().equals("Tambah")){
            int rslt= new CRUD().tambahPeriodeSidang(dateAwal, dateakhir, periodeKe);
            
            if(rslt > 0){
                this.dispose();
                JOptionPane.showMessageDialog(this, "Input Berhasil");
            }else{
                JOptionPane.showMessageDialog(this, "Input Gagal");
            }
        }else{
            int rslt = new CRUD().updatePeriode( temp, dateAwal, dateakhir,periodeKe);
            if(rslt > 0){
                this.dispose();
                
                JOptionPane.showMessageDialog(this, "Update Berhasil");
            }else{
                JOptionPane.showMessageDialog(this, "Update Gagal");
            }
        }
    }//GEN-LAST:event_submitPeriodeMouseClicked

    private void tfJudulPAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfJudulPAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfJudulPAActionPerformed

    private void submitPAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitPAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_submitPAActionPerformed

    private void submitPAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_submitPAMouseClicked
        // TODO add your handling code here:
        String judul = tfJudulPA.getText();
        String pemb1 = cbPemb1.getSelectedItem().toString();
        String pemb2 = cbPemb2.getSelectedItem().toString();
        int keahlian = new ListData().idKeahlian(cbKeahlian.getSelectedItem().toString());
        String nim1 = cbNim1.getSelectedItem().toString();
        String nim2 = cbNim2.getSelectedItem().toString();
        String nim12 = nim1 +","+nim2;
        
        if(!nim1.equals(nim2)){
            int status = 1;
            if(cbStatus.getSelectedItem().toString().equals("Belum Terjadwal")){
                status = 0;
            }


            if(submitPA.getText().equals("Tambah")){
                int rslt= new CRUD().tambahPa(judul, pemb1, pemb2, keahlian, nim12);

                if(rslt > 0){
                    this.dispose();
                    JOptionPane.showMessageDialog(this, "Input Berhasil");
                }else{
                    JOptionPane.showMessageDialog(this, "Input Gagal, Nim Udah Terdaftar Di PA Lain");
                }
            }else{
                int rslt = new CRUD().updatePA(temp, judul, pemb1, pemb2, keahlian,nim12, status);
                if(rslt > 0){
                    this.dispose();

                    JOptionPane.showMessageDialog(this, "Update Berhasil");
                }else{
                    JOptionPane.showMessageDialog(this, "Update Gagal, Nim Udah Terdaftar Di PA Lain");
                }
            }
        }else{
            JOptionPane.showMessageDialog(this, "Nim 1 dan Nim2 Harus Beda!");
        }
        
        
    }//GEN-LAST:event_submitPAMouseClicked

    private void submitJdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_submitJdMouseClicked
        // TODO add your handling code here:
        String kodos = cdKodes.getSelectedItem().toString();
        String mulai = tf.format(jmMulai.getValue());
        String akir = tf.format(jmSelesai.getValue());
        
        String cb = cbHari.getSelectedItem().toString();
        int hariKe = new ListData().getIdHari(cb);
        
        
        if(submitJd.getText().equals("Tambah")){
            int rslt= new CRUD().tambahJadwalDosen(kodos, mulai, akir, hariKe);
            
            if(rslt > 0){
                this.dispose();
                JOptionPane.showMessageDialog(this, "Input Berhasil");
            }else{
                JOptionPane.showMessageDialog(this, "Input Gagal");
            }
        }else{
            int rslt = new CRUD().updateJadwalDosen(Integer.parseInt(temp), kodos,mulai, akir, hariKe);
            if(rslt > 0){
                this.dispose();
                
                JOptionPane.showMessageDialog(this, "Update Berhasil");
            }else{
                JOptionPane.showMessageDialog(this, "Update Gagal");
            }
        }
    }//GEN-LAST:event_submitJdMouseClicked

    private void submitSidangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_submitSidangMouseClicked
      
            // TODO add your handling code here:
            
            String jamAwal = tf.format(jmMulaiSidang.getValue());
            String jamAkhir = tf.format(jmSelesaiSidang.getValue());            
            int periodeKe = Integer.parseInt(cbPeriode.getSelectedItem().toString().split("-",2)[1]);
            String tanggal = df.format(tglSidang.getDate());            
            int idPA = Integer.parseInt(cbPA.getSelectedItem().toString().split("-", 2)[0]);
            String penguji1 = cbUji1.getSelectedItem().toString();
            String penguji2 = cbUji2.getSelectedItem().toString();
            int idRuangan = new CRUD().getIdRuangan(cbRuang.getSelectedItem().toString());
            
            
            if(submitSidang.getText().equals("Tambah")){
                int rslt= new CRUD().tambahJadwalSidang(jamAwal, jamAkhir, tanggal, penguji1, penguji2, idRuangan, idPA, periodeKe);

                if(rslt > 0){
                    this.dispose();
                    JOptionPane.showMessageDialog(this, "Input Berhasil");
                }else{
                    JOptionPane.showMessageDialog(this, "Input Gagal");
                }
            }else{
                int rslt = new CRUD().updateJadwalSidang(temp,jamAwal, jamAkhir, tanggal, penguji1, penguji2, idRuangan, idPA, periodeKe);
                if(rslt > 0){
                    this.dispose();

                    JOptionPane.showMessageDialog(this, "Update Berhasil");
                }else{
                    JOptionPane.showMessageDialog(this, "Update Gagal");
                }
            }        
            
            
        
    }//GEN-LAST:event_submitSidangMouseClicked

    private void cbPAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbPAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbPAActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(AddOrEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(AddOrEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(AddOrEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(AddOrEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new AddOrEdit("","").setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cbHari;
    private javax.swing.JComboBox cbKeahlian;
    private javax.swing.JComboBox cbKeahlian1;
    private javax.swing.JComboBox cbKeahlian2;
    private javax.swing.JComboBox cbNim1;
    private javax.swing.JComboBox cbNim2;
    private javax.swing.JComboBox cbPA;
    private javax.swing.JComboBox cbPemb1;
    private javax.swing.JComboBox cbPemb2;
    private javax.swing.JComboBox cbPeriode;
    private javax.swing.JComboBox cbRuang;
    private javax.swing.JComboBox cbStatus;
    private javax.swing.JComboBox cbUji1;
    private javax.swing.JComboBox cbUji2;
    private javax.swing.JComboBox cdKodes;
    private javax.swing.JPanel dosen;
    private com.toedter.calendar.JDateChooser dtAkhir;
    private com.toedter.calendar.JDateChooser dtAwal;
    private javax.swing.JPanel header;
    private javax.swing.JPanel header1;
    private javax.swing.JPanel header2;
    private javax.swing.JPanel header3;
    private javax.swing.JPanel header4;
    private javax.swing.JPanel header5;
    private javax.swing.JPanel header6;
    private javax.swing.JPanel header7;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jadwalDosen;
    private javax.swing.JPanel jadwalSidang;
    private javax.swing.JSpinner jmMulai;
    private javax.swing.JSpinner jmMulaiSidang;
    private javax.swing.JSpinner jmSelesai;
    private javax.swing.JSpinner jmSelesaiSidang;
    private javax.swing.JPanel keahlian;
    private javax.swing.JPanel mahasiswa;
    private javax.swing.JPanel main;
    private javax.swing.JPanel pa;
    private javax.swing.JPanel periode;
    private javax.swing.JPanel ruangan;
    private javax.swing.JButton submit;
    private javax.swing.JButton submitJd;
    private javax.swing.JButton submitKeahlian;
    private javax.swing.JButton submitMhs;
    private javax.swing.JButton submitPA;
    private javax.swing.JButton submitPeriode;
    private javax.swing.JButton submitRuang;
    private javax.swing.JButton submitSidang;
    private javax.swing.JTextField tfEmail;
    private javax.swing.JTextField tfEmailMhs;
    private javax.swing.JTextField tfJudulPA;
    private javax.swing.JTextField tfKodeDosen;
    private javax.swing.JTextField tfNama;
    private javax.swing.JTextField tfNamaKeahlian;
    private javax.swing.JTextField tfNamaMhs;
    private javax.swing.JTextField tfNamaRuang;
    private javax.swing.JTextField tfNim;
    private javax.swing.JTextField tfNip;
    private javax.swing.JTextField tfPeriode;
    private com.toedter.calendar.JDateChooser tglSidang;
    private javax.swing.JLabel titleDosen;
    private javax.swing.JLabel titleJdDosen;
    private javax.swing.JLabel titleKeahlian;
    private javax.swing.JLabel titleMhs;
    private javax.swing.JLabel titlePA;
    private javax.swing.JLabel titlePeriode;
    private javax.swing.JLabel titleRuang;
    private javax.swing.JLabel titleSidang;
    // End of variables declaration//GEN-END:variables
}
