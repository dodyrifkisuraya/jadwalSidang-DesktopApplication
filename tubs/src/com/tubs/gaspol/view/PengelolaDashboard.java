/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tubs.gaspol.view;

import com.tubs.gaspol.db.CRUD;
import com.tubs.gaspol.item.Dosen;
import com.tubs.gaspol.item.JadwalMengajarDosen;
import com.tubs.gaspol.item.JadwalSidang;
import com.tubs.gaspol.item.Keahlian;
import com.tubs.gaspol.item.Mahasiswa;
import com.tubs.gaspol.item.Periode;
import com.tubs.gaspol.item.ProyekAkhir;
import com.tubs.gaspol.item.Ruangan;
import com.tubs.gaspol.list.ListData;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DORIZU
 */
public class PengelolaDashboard extends javax.swing.JFrame {

    /**
     * Creates new form PengelolaDashboard
     */
    private DefaultTableModel modelTblDosen = new DefaultTableModel();
    private DefaultTableModel modelTblJdwlDosen = new DefaultTableModel();
    private DefaultTableModel modelTblKeahlian = new DefaultTableModel();
    private DefaultTableModel modelTblMahasiswa = new DefaultTableModel();
    private DefaultTableModel modelTblPA = new DefaultTableModel();
    private DefaultTableModel modelTblRuang = new DefaultTableModel();
    private DefaultTableModel modelTblJdwlSidang = new DefaultTableModel();
    private DefaultTableModel modelTblPeriode = new DefaultTableModel();
    
    public PengelolaDashboard() {
        initComponents();
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_HORIZ);
        setVisible(true);
        setResizable(false);
        JOptionPane.showMessageDialog(this, "*Catatan : Untuk Merefresh Isi Kolom tabel data, Klik Kembali Tabel Yang di Pilih");
         //content Dosen
        loadKolomDosen();
        tblDosen.setModel(modelTblDosen);
        //content Jadwal Dosen
        loadKolomJdwl();
        tbJdwlDosen.setModel(modelTblJdwlDosen);
        //content keahlian
        laodKolomKeahlian();
        tblKeahlian.setModel(modelTblKeahlian);
        //contentMahasiswa
        loadKolomMahasiswa();
        tblMhs.setModel(modelTblMahasiswa);
        //contentPA
        loadKolomPA();
        tblPA.setModel(modelTblPA);
        //contentRuang
        loadKolomRuang();
        tblRuang.setModel(modelTblRuang);
        //contentJadwalSidang
        loadKolomJadwalSidang();
        tblSidang.setModel(modelTblJdwlSidang);
        //contentPeriode
        loadKolomPeriode();
        tblPeriode.setModel(modelTblPeriode);
        
        refreshData();
    }
    
    private void refreshData(){
        
        tampilDosen();
        
        tampilJdDosen();
        
        tampilKeahlian();
        
        tampilMahasiswa();
        
        tampilPA();
        
        tampilRuang();
        
        tampilJadwalSidang();
        
        tampilPeriode();
    }
    
    private void loadKolomDosen(){
        modelTblDosen.addColumn("Nama");
        modelTblDosen.addColumn("Kode Dosen");
        modelTblDosen.addColumn("NIP");
        modelTblDosen.addColumn("Keahlian 1");
        modelTblDosen.addColumn("Keahlian 2");
        modelTblDosen.addColumn("Email");
        
    }
    
    private void tampilDosen(){
        modelTblDosen.setRowCount(0);
        
        for(Dosen d: new ListData().getAllDosen()){
           String keahlian1 = new ListData().namaKeahlian(d.getIdKeahlian1());
           String keahlian2 = new ListData().namaKeahlian(d.getIdKeahlian2());
            modelTblDosen.addRow(new Object[]{d.getName(), d.getKodeDosen(), d.getNip(), keahlian1, keahlian2, d.getEmail()});
        }
    }
    
    private void loadKolomJdwl(){
        modelTblJdwlDosen.addColumn("ID Jadwal");
        modelTblJdwlDosen.addColumn("Kode Dosen");
        modelTblJdwlDosen.addColumn("Hari Ke");
        modelTblJdwlDosen.addColumn("Jam Mulai");
        modelTblJdwlDosen.addColumn("Jam Selesai");
    }
    
    
    
    private void tampilJdDosen(){
        modelTblJdwlDosen.setRowCount(0);
        for(JadwalMengajarDosen d: new ListData().getAllJdDosen()){
            modelTblJdwlDosen.addRow(new Object[]{d.getIdJadwal(),d.getIdDosen(),new ListData().getNamaHari(d.getHariKe()), d.getJamMulai(), d.getJamSelesai()});
        }
    }
    
    private void laodKolomKeahlian(){
        modelTblKeahlian.addColumn("Id");
        modelTblKeahlian.addColumn("Nama Keahlian");
    }
    
    private void tampilKeahlian(){
        modelTblKeahlian.setRowCount(0);
        for(Keahlian k : new ListData().getKeahlian()){
            modelTblKeahlian.addRow(new Object[]{k.getId(), k.getNamaKeahlian()});
        }
    }
    
    private void loadKolomMahasiswa(){
        modelTblMahasiswa.addColumn("NIM");
        modelTblMahasiswa.addColumn("Nama");
        modelTblMahasiswa.addColumn("Email");
    }
    
    private void tampilMahasiswa(){
        modelTblMahasiswa.setRowCount(0);
        for(Mahasiswa m : new ListData().getAllMahasiswa()){
            modelTblMahasiswa.addRow(new Object[]{m.getNim(), m.getName(), m.getEmail()});
        }
    }
    
    private void loadKolomPA(){
        modelTblPA.addColumn("Judul");
        modelTblPA.addColumn("ID Dosen Pembimbing 1");
        modelTblPA.addColumn("ID Dosen Pembimbing 2");
        modelTblPA.addColumn("ID Keahlian");
        modelTblPA.addColumn("Status");        
        modelTblPA.addColumn("NIM Sidang");
    }
    
    private void tampilPA(){
        modelTblPA.setRowCount(0);
        for(ProyekAkhir p : new ListData().getPA()){
            String status = null;
            if(p.getStatus() == 0){
                status = "Belum Terjadwal";
            }else{
                status = "Sudah Terjadwal";
            }
            String keahlian = new ListData().namaKeahlian(p.getIdKeahlian());
            String nim = p.getNim();
            
            modelTblPA.addRow(new Object[]{p.getJudulPa(), p.getKDDosbing1(), p.getKDDosbing2(),keahlian ,status, nim});
        }
    }
    
    private void loadKolomRuang(){
        modelTblRuang.addColumn("Kode");
        modelTblRuang.addColumn("Nama Ruang");
    }
    
    private void tampilRuang(){
        modelTblRuang.setRowCount(0);
        for(Ruangan r : new ListData().getRuang()){
            modelTblRuang.addRow(new Object[]{r.getId(), r.getNamaRuang()});
        }
    }
    
    private void loadKolomJadwalSidang(){
        modelTblJdwlSidang.addColumn("Tanggal");
        modelTblJdwlSidang.addColumn("Jam Mulai");
        modelTblJdwlSidang.addColumn("Jam Selesai");
        modelTblJdwlSidang.addColumn("ID PA");
        modelTblJdwlSidang.addColumn("ID Periode");
        modelTblJdwlSidang.addColumn("ID Ruang");
        modelTblJdwlSidang.addColumn("ID Penguji 1");
        modelTblJdwlSidang.addColumn("ID Penguji 2");
    }
    
    private void tampilJadwalSidang(){
        modelTblJdwlSidang.setRowCount(0);
        for(JadwalSidang j : new ListData().getJadwalSidang()){
            modelTblJdwlSidang.addRow(new Object[]{j.getTanggal(), j.getJamMulai(), j.getJamSelesai(), j.getIdPA()+"-"+new CRUD().getNamaPa(j.getIdPA()) , new CRUD().getNamaPeriode(j.getIdPeriode()), new CRUD().getNamaRuangan(j.getIdRuangan()), j.getKDPenguji1(), j.getKDPenguji2()});
        }
    }
    
    private void loadKolomPeriode(){
        modelTblPeriode.addColumn("Periode ke-");
        modelTblPeriode.addColumn("Tanggal Awal");
        modelTblPeriode.addColumn("Tanggal Akhir");
        modelTblPeriode.addColumn("Tahun");
        
    }
    
    private void tampilPeriode(){
        modelTblPeriode.setRowCount(0);
        for(Periode p : new ListData().getPeriode()){
            modelTblPeriode.addRow(new Object[]{p.getPeriodeKe(), p.getTanggalAwal(), p.getTanggalAkhir(), p.getTahun()});
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        background = new javax.swing.JPanel();
        header = new javax.swing.JPanel();
        logo = new javax.swing.JLabel();
        HomeUser = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        navigasiMenu = new javax.swing.JPanel();
//        navDashboard = new javax.swing.JButton();
        navDosen = new javax.swing.JButton();
        navJadwalDosen = new javax.swing.JButton();
        navKeahlian = new javax.swing.JButton();
        navMahasiswa = new javax.swing.JButton();
        navPA = new javax.swing.JButton();
        navRuang = new javax.swing.JButton();
        navJadwalSidang = new javax.swing.JButton();
        navPeriode = new javax.swing.JButton();
        mainContent = new javax.swing.JPanel();
        contentDashboard = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        contentDosen = new javax.swing.JPanel();
        addDosen = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        editDosen = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDosen = new javax.swing.JTable();
        contentJadwalDosen = new javax.swing.JPanel();
        addJadwalDosen = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        editJadwalDosen = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbJdwlDosen = new javax.swing.JTable();
        contentKeahlian = new javax.swing.JPanel();
        addKeahlian = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        editKeahlian = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblKeahlian = new javax.swing.JTable();
        contentMahasiswa = new javax.swing.JPanel();
        addMhs = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        editMhs = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblMhs = new javax.swing.JTable();
        contentPA = new javax.swing.JPanel();
        addPA = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        editPA = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblPA = new javax.swing.JTable();
        contentRuang = new javax.swing.JPanel();
        addRuang = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        editRuang = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblRuang = new javax.swing.JTable();
        contentSidang = new javax.swing.JPanel();
        addSidang = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        editSidang = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblSidang = new javax.swing.JTable();
        contentPeriodeSidang = new javax.swing.JPanel();
        addPeriode = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        editPeriode = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblPeriode = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                formMouseEntered(evt);
            }
        });

        background.setBackground(new java.awt.Color(255, 255, 255));

        header.setBackground(new java.awt.Color(255, 102, 102));

        logo.setFont(new java.awt.Font("Segoe UI Black", 0, 58)); // NOI18N
        logo.setForeground(new java.awt.Color(255, 255, 255));
        logo.setText("SidangAja.");

        HomeUser.setBackground(new java.awt.Color(255, 102, 102));
        HomeUser.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        HomeUser.setPreferredSize(new java.awt.Dimension(90, 90));
        HomeUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                HomeUserMouseClicked(evt);
            }
        });
        HomeUser.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                HomeUserPropertyChange(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/tubs/gaspol/image/home-icon-silhouette.png"))); // NOI18N

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Home");

        javax.swing.GroupLayout HomeUserLayout = new javax.swing.GroupLayout(HomeUser);
        HomeUser.setLayout(HomeUserLayout);
        HomeUserLayout.setHorizontalGroup(
            HomeUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HomeUserLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(HomeUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        HomeUserLayout.setVerticalGroup(
            HomeUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HomeUserLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addContainerGap())
        );

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(HomeUser, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(logo)
                .addGap(34, 34, 34))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerLayout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(HomeUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(logo))
                .addGap(23, 23, 23))
        );

//        navDashboard.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
//        navDashboard.setForeground(new java.awt.Color(102, 102, 102));
//        navDashboard.setText("Dashboard");
//        navDashboard.setBorder(null);
//        navDashboard.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                navDashboardActionPerformed(evt);
//            }
//        });

        navDosen.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        navDosen.setForeground(new java.awt.Color(102, 102, 102));
        navDosen.setText("Dosen");
        navDosen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                navDosenActionPerformed(evt);
            }
        });

        navJadwalDosen.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        navJadwalDosen.setForeground(new java.awt.Color(102, 102, 102));
        navJadwalDosen.setText("Jadwal Mengajar Dosen");
        navJadwalDosen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                navJadwalDosenActionPerformed(evt);
            }
        });

        navKeahlian.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        navKeahlian.setForeground(new java.awt.Color(102, 102, 102));
        navKeahlian.setText("Keahlian");
        navKeahlian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                navKeahlianActionPerformed(evt);
            }
        });

        navMahasiswa.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        navMahasiswa.setForeground(new java.awt.Color(102, 102, 102));
        navMahasiswa.setText("Mahasiswa");
        navMahasiswa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                navMahasiswaActionPerformed(evt);
            }
        });

        navPA.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        navPA.setForeground(new java.awt.Color(102, 102, 102));
        navPA.setText("Proyek Akhir");
        navPA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                navPAActionPerformed(evt);
            }
        });

        navRuang.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        navRuang.setForeground(new java.awt.Color(102, 102, 102));
        navRuang.setText("Ruang Sidang");
        navRuang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                navRuangMouseClicked(evt);
            }
        });
        navRuang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                navRuangActionPerformed(evt);
            }
        });

        navJadwalSidang.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        navJadwalSidang.setForeground(new java.awt.Color(102, 102, 102));
        navJadwalSidang.setText("Jadwal Sidang");
        navJadwalSidang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                navJadwalSidangActionPerformed(evt);
            }
        });

        navPeriode.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        navPeriode.setForeground(new java.awt.Color(102, 102, 102));
        navPeriode.setText("Periode Sidang");
        navPeriode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                navPeriodeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout navigasiMenuLayout = new javax.swing.GroupLayout(navigasiMenu);
        navigasiMenu.setLayout(navigasiMenuLayout);
        navigasiMenuLayout.setHorizontalGroup(
            navigasiMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addComponent(navDashboard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(navDosen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(navJadwalDosen, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
            .addComponent(navKeahlian, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(navMahasiswa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(navPA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(navRuang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(navJadwalSidang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(navPeriode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        navigasiMenuLayout.setVerticalGroup(
            navigasiMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(navigasiMenuLayout.createSequentialGroup()
                .addContainerGap()
//                .addComponent(navDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(navDosen, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(navJadwalDosen, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(navKeahlian, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(navMahasiswa, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(navPA, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(navRuang, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(navJadwalSidang, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(navPeriode, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mainContent.setBackground(new java.awt.Color(255, 255, 255));
        mainContent.setLayout(new java.awt.CardLayout());

//        contentDashboard.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 153, 153));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1217, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 225, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout contentDashboardLayout = new javax.swing.GroupLayout(contentDosen);
        contentDosen.setLayout(contentDashboardLayout);
        contentDashboardLayout.setHorizontalGroup(
            contentDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentDashboardLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        contentDashboardLayout.setVerticalGroup(
            contentDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentDashboardLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(398, Short.MAX_VALUE))
        );
//
        mainContent.add(contentDosen, "card2");

        contentDosen.setBackground(new java.awt.Color(255, 255, 255));
        contentDosen.setPreferredSize(new java.awt.Dimension(737, 400));

        addDosen.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 102, 102), new java.awt.Color(255, 102, 102), new java.awt.Color(255, 102, 102), new java.awt.Color(255, 102, 102)));
        addDosen.setPreferredSize(new java.awt.Dimension(106, 80));
        addDosen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addDosenMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                addDosenMouseEntered(evt);
            }
        });

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Tambah Dosen");

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/tubs/gaspol/image/plus.png"))); // NOI18N

        javax.swing.GroupLayout addDosenLayout = new javax.swing.GroupLayout(addDosen);
        addDosen.setLayout(addDosenLayout);
        addDosenLayout.setHorizontalGroup(
            addDosenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addDosenLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(addDosenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE))
                .addContainerGap())
        );
        addDosenLayout.setVerticalGroup(
            addDosenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addDosenLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addContainerGap())
        );

        editDosen.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 102, 102), new java.awt.Color(255, 102, 102), new java.awt.Color(255, 102, 102), new java.awt.Color(255, 102, 102)));
        editDosen.setPreferredSize(new java.awt.Dimension(106, 80));
        editDosen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editDosenMouseClicked(evt);
            }
        });

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Edit Dosen");

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/tubs/gaspol/image/edit-button.png"))); // NOI18N

        javax.swing.GroupLayout editDosenLayout = new javax.swing.GroupLayout(editDosen);
        editDosen.setLayout(editDosenLayout);
        editDosenLayout.setHorizontalGroup(
            editDosenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editDosenLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editDosenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        editDosenLayout.setVerticalGroup(
            editDosenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editDosenLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addContainerGap())
        );

        tblDosen.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblDosen.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setViewportView(tblDosen);

        javax.swing.GroupLayout contentDosenLayout = new javax.swing.GroupLayout(contentDosen);
        contentDosen.setLayout(contentDosenLayout);
        contentDosenLayout.setHorizontalGroup(
            contentDosenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contentDosenLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1217, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(contentDosenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(contentDosenLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(addDosen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(editDosen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(1005, Short.MAX_VALUE)))
        );
        contentDosenLayout.setVerticalGroup(
            contentDosenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentDosenLayout.createSequentialGroup()
                .addGap(116, 116, 116)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(contentDosenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(contentDosenLayout.createSequentialGroup()
                    .addGap(6, 6, 6)
                    .addGroup(contentDosenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(addDosen, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                        .addComponent(editDosen, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE))
                    .addContainerGap(530, Short.MAX_VALUE)))
        );

        mainContent.add(contentDosen, "card3");

        contentJadwalDosen.setBackground(new java.awt.Color(255, 255, 255));
        contentJadwalDosen.setPreferredSize(new java.awt.Dimension(737, 400));

        addJadwalDosen.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 102, 102), new java.awt.Color(255, 102, 102), new java.awt.Color(255, 102, 102), new java.awt.Color(255, 102, 102)));
        addJadwalDosen.setPreferredSize(new java.awt.Dimension(106, 80));
        addJadwalDosen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addJadwalDosenMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                addJadwalDosenMouseEntered(evt);
            }
        });

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Tambah Jadwal");

        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/tubs/gaspol/image/plus.png"))); // NOI18N

        javax.swing.GroupLayout addJadwalDosenLayout = new javax.swing.GroupLayout(addJadwalDosen);
        addJadwalDosen.setLayout(addJadwalDosenLayout);
        addJadwalDosenLayout.setHorizontalGroup(
            addJadwalDosenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addJadwalDosenLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(addJadwalDosenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE))
                .addContainerGap())
        );
        addJadwalDosenLayout.setVerticalGroup(
            addJadwalDosenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addJadwalDosenLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addContainerGap())
        );

        editJadwalDosen.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 102, 102), new java.awt.Color(255, 102, 102), new java.awt.Color(255, 102, 102), new java.awt.Color(255, 102, 102)));
        editJadwalDosen.setPreferredSize(new java.awt.Dimension(106, 80));
        editJadwalDosen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editJadwalDosenMouseClicked(evt);
            }
        });

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Edit Jadwal");

        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/tubs/gaspol/image/edit-button.png"))); // NOI18N

        javax.swing.GroupLayout editJadwalDosenLayout = new javax.swing.GroupLayout(editJadwalDosen);
        editJadwalDosen.setLayout(editJadwalDosenLayout);
        editJadwalDosenLayout.setHorizontalGroup(
            editJadwalDosenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editJadwalDosenLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editJadwalDosenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        editJadwalDosenLayout.setVerticalGroup(
            editJadwalDosenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editJadwalDosenLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17)
                .addContainerGap())
        );

        tbJdwlDosen.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbJdwlDosen.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane3.setViewportView(tbJdwlDosen);

        javax.swing.GroupLayout contentJadwalDosenLayout = new javax.swing.GroupLayout(contentJadwalDosen);
        contentJadwalDosen.setLayout(contentJadwalDosenLayout);
        contentJadwalDosenLayout.setHorizontalGroup(
            contentJadwalDosenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contentJadwalDosenLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1217, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(contentJadwalDosenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(contentJadwalDosenLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(addJadwalDosen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(editJadwalDosen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(1005, Short.MAX_VALUE)))
        );
        contentJadwalDosenLayout.setVerticalGroup(
            contentJadwalDosenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentJadwalDosenLayout.createSequentialGroup()
                .addGap(116, 116, 116)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(contentJadwalDosenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(contentJadwalDosenLayout.createSequentialGroup()
                    .addGap(6, 6, 6)
                    .addGroup(contentJadwalDosenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(addJadwalDosen, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                        .addComponent(editJadwalDosen, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE))
                    .addContainerGap(359, Short.MAX_VALUE)))
        );

        mainContent.add(contentJadwalDosen, "card3");

        contentKeahlian.setBackground(new java.awt.Color(255, 255, 255));
        contentKeahlian.setPreferredSize(new java.awt.Dimension(737, 400));

        addKeahlian.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 102, 102), new java.awt.Color(255, 102, 102), new java.awt.Color(255, 102, 102), new java.awt.Color(255, 102, 102)));
        addKeahlian.setPreferredSize(new java.awt.Dimension(106, 80));
        addKeahlian.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addKeahlianMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                addKeahlianMouseEntered(evt);
            }
        });

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Tambah Keahlian");

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/tubs/gaspol/image/plus.png"))); // NOI18N

        javax.swing.GroupLayout addKeahlianLayout = new javax.swing.GroupLayout(addKeahlian);
        addKeahlian.setLayout(addKeahlianLayout);
        addKeahlianLayout.setHorizontalGroup(
            addKeahlianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addKeahlianLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        addKeahlianLayout.setVerticalGroup(
            addKeahlianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addKeahlianLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addContainerGap())
        );

        editKeahlian.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 102, 102), new java.awt.Color(255, 102, 102), new java.awt.Color(255, 102, 102), new java.awt.Color(255, 102, 102)));
        editKeahlian.setPreferredSize(new java.awt.Dimension(106, 80));
        editKeahlian.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editKeahlianMouseClicked(evt);
            }
        });

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Edit Keahlian");

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/tubs/gaspol/image/edit-button.png"))); // NOI18N

        javax.swing.GroupLayout editKeahlianLayout = new javax.swing.GroupLayout(editKeahlian);
        editKeahlian.setLayout(editKeahlianLayout);
        editKeahlianLayout.setHorizontalGroup(
            editKeahlianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editKeahlianLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editKeahlianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        editKeahlianLayout.setVerticalGroup(
            editKeahlianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editKeahlianLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addContainerGap())
        );

        tblKeahlian.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblKeahlian.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane2.setViewportView(tblKeahlian);

        javax.swing.GroupLayout contentKeahlianLayout = new javax.swing.GroupLayout(contentKeahlian);
        contentKeahlian.setLayout(contentKeahlianLayout);
        contentKeahlianLayout.setHorizontalGroup(
            contentKeahlianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contentKeahlianLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1217, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(contentKeahlianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(contentKeahlianLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(addKeahlian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(editKeahlian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(1005, Short.MAX_VALUE)))
        );
        contentKeahlianLayout.setVerticalGroup(
            contentKeahlianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentKeahlianLayout.createSequentialGroup()
                .addGap(116, 116, 116)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(contentKeahlianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(contentKeahlianLayout.createSequentialGroup()
                    .addGap(6, 6, 6)
                    .addGroup(contentKeahlianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(addKeahlian, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                        .addComponent(editKeahlian, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE))
                    .addContainerGap(530, Short.MAX_VALUE)))
        );

        mainContent.add(contentKeahlian, "card3");

        contentMahasiswa.setBackground(new java.awt.Color(255, 255, 255));
        contentMahasiswa.setPreferredSize(new java.awt.Dimension(737, 400));

        addMhs.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 102, 102), new java.awt.Color(255, 102, 102), new java.awt.Color(255, 102, 102), new java.awt.Color(255, 102, 102)));
        addMhs.setPreferredSize(new java.awt.Dimension(106, 80));
        addMhs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addMhsMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                addMhsMouseEntered(evt);
            }
        });

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Tambah Mahasiswa");

        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/tubs/gaspol/image/plus.png"))); // NOI18N

        javax.swing.GroupLayout addMhsLayout = new javax.swing.GroupLayout(addMhs);
        addMhs.setLayout(addMhsLayout);
        addMhsLayout.setHorizontalGroup(
            addMhsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addMhsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        addMhsLayout.setVerticalGroup(
            addMhsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addMhsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        editMhs.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 102, 102), new java.awt.Color(255, 102, 102), new java.awt.Color(255, 102, 102), new java.awt.Color(255, 102, 102)));
        editMhs.setPreferredSize(new java.awt.Dimension(106, 80));
        editMhs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editMhsMouseClicked(evt);
            }
        });

        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Edit Mahasiswa");

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/tubs/gaspol/image/edit-button.png"))); // NOI18N

        javax.swing.GroupLayout editMhsLayout = new javax.swing.GroupLayout(editMhs);
        editMhs.setLayout(editMhsLayout);
        editMhsLayout.setHorizontalGroup(
            editMhsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editMhsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editMhsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        editMhsLayout.setVerticalGroup(
            editMhsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editMhsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel20)
                .addContainerGap())
        );

        tblMhs.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblMhs.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane4.setViewportView(tblMhs);

        javax.swing.GroupLayout contentMahasiswaLayout = new javax.swing.GroupLayout(contentMahasiswa);
        contentMahasiswa.setLayout(contentMahasiswaLayout);
        contentMahasiswaLayout.setHorizontalGroup(
            contentMahasiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contentMahasiswaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1217, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(contentMahasiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(contentMahasiswaLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(addMhs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(editMhs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(1005, Short.MAX_VALUE)))
        );
        contentMahasiswaLayout.setVerticalGroup(
            contentMahasiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentMahasiswaLayout.createSequentialGroup()
                .addGap(116, 116, 116)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(contentMahasiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(contentMahasiswaLayout.createSequentialGroup()
                    .addGap(6, 6, 6)
                    .addGroup(contentMahasiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(addMhs, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                        .addComponent(editMhs, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE))
                    .addContainerGap(530, Short.MAX_VALUE)))
        );

        mainContent.add(contentMahasiswa, "card3");

        contentPA.setBackground(new java.awt.Color(255, 255, 255));
        contentPA.setPreferredSize(new java.awt.Dimension(737, 400));

        addPA.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 102, 102), new java.awt.Color(255, 102, 102), new java.awt.Color(255, 102, 102), new java.awt.Color(255, 102, 102)));
        addPA.setPreferredSize(new java.awt.Dimension(106, 80));
        addPA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addPAMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                addPAMouseEntered(evt);
            }
        });

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Tambah PA");

        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/tubs/gaspol/image/plus.png"))); // NOI18N

        javax.swing.GroupLayout addPALayout = new javax.swing.GroupLayout(addPA);
        addPA.setLayout(addPALayout);
        addPALayout.setHorizontalGroup(
            addPALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addPALayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        addPALayout.setVerticalGroup(
            addPALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addPALayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        editPA.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 102, 102), new java.awt.Color(255, 102, 102), new java.awt.Color(255, 102, 102), new java.awt.Color(255, 102, 102)));
        editPA.setPreferredSize(new java.awt.Dimension(106, 80));
        editPA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editPAMouseClicked(evt);
            }
        });

        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("Edit PA");

        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/tubs/gaspol/image/edit-button.png"))); // NOI18N

        javax.swing.GroupLayout editPALayout = new javax.swing.GroupLayout(editPA);
        editPA.setLayout(editPALayout);
        editPALayout.setHorizontalGroup(
            editPALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editPALayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editPALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        editPALayout.setVerticalGroup(
            editPALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editPALayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel23)
                .addContainerGap())
        );

        tblPA.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblPA.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane5.setViewportView(tblPA);

        javax.swing.GroupLayout contentPALayout = new javax.swing.GroupLayout(contentPA);
        contentPA.setLayout(contentPALayout);
        contentPALayout.setHorizontalGroup(
            contentPALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contentPALayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 1217, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(contentPALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(contentPALayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(addPA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(editPA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(1005, Short.MAX_VALUE)))
        );
        contentPALayout.setVerticalGroup(
            contentPALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPALayout.createSequentialGroup()
                .addGap(116, 116, 116)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(contentPALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(contentPALayout.createSequentialGroup()
                    .addGap(6, 6, 6)
                    .addGroup(contentPALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(addPA, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                        .addComponent(editPA, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE))
                    .addContainerGap(530, Short.MAX_VALUE)))
        );

        mainContent.add(contentPA, "card3");

        contentRuang.setBackground(new java.awt.Color(255, 255, 255));
        contentRuang.setPreferredSize(new java.awt.Dimension(737, 400));

        addRuang.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 102, 102), new java.awt.Color(255, 102, 102), new java.awt.Color(255, 102, 102), new java.awt.Color(255, 102, 102)));
        addRuang.setPreferredSize(new java.awt.Dimension(106, 80));
        addRuang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addRuangMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                addRuangMouseEntered(evt);
            }
        });

        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("Tambah Ruang");

        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/tubs/gaspol/image/plus.png"))); // NOI18N

        javax.swing.GroupLayout addRuangLayout = new javax.swing.GroupLayout(addRuang);
        addRuang.setLayout(addRuangLayout);
        addRuangLayout.setHorizontalGroup(
            addRuangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addRuangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        addRuangLayout.setVerticalGroup(
            addRuangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addRuangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel25)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        editRuang.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 102, 102), new java.awt.Color(255, 102, 102), new java.awt.Color(255, 102, 102), new java.awt.Color(255, 102, 102)));
        editRuang.setPreferredSize(new java.awt.Dimension(106, 80));
        editRuang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editRuangMouseClicked(evt);
            }
        });

        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("Edit Ruang");

        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/tubs/gaspol/image/edit-button.png"))); // NOI18N

        javax.swing.GroupLayout editRuangLayout = new javax.swing.GroupLayout(editRuang);
        editRuang.setLayout(editRuangLayout);
        editRuangLayout.setHorizontalGroup(
            editRuangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editRuangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editRuangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        editRuangLayout.setVerticalGroup(
            editRuangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editRuangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel27)
                .addContainerGap())
        );

        tblRuang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblRuang.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane6.setViewportView(tblRuang);

        javax.swing.GroupLayout contentRuangLayout = new javax.swing.GroupLayout(contentRuang);
        contentRuang.setLayout(contentRuangLayout);
        contentRuangLayout.setHorizontalGroup(
            contentRuangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contentRuangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 1217, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(contentRuangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(contentRuangLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(addRuang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(editRuang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(1005, Short.MAX_VALUE)))
        );
        contentRuangLayout.setVerticalGroup(
            contentRuangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentRuangLayout.createSequentialGroup()
                .addGap(116, 116, 116)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(contentRuangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(contentRuangLayout.createSequentialGroup()
                    .addGap(6, 6, 6)
                    .addGroup(contentRuangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(addRuang, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                        .addComponent(editRuang, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE))
                    .addContainerGap(530, Short.MAX_VALUE)))
        );

        mainContent.add(contentRuang, "card3");

        contentSidang.setBackground(new java.awt.Color(255, 255, 255));
        contentSidang.setPreferredSize(new java.awt.Dimension(737, 400));

        addSidang.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 102, 102), new java.awt.Color(255, 102, 102), new java.awt.Color(255, 102, 102), new java.awt.Color(255, 102, 102)));
        addSidang.setPreferredSize(new java.awt.Dimension(106, 80));
        addSidang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addSidangMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                addSidangMouseEntered(evt);
            }
        });

        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("Tambah Sidang");

        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/tubs/gaspol/image/plus.png"))); // NOI18N

        javax.swing.GroupLayout addSidangLayout = new javax.swing.GroupLayout(addSidang);
        addSidang.setLayout(addSidangLayout);
        addSidangLayout.setHorizontalGroup(
            addSidangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addSidangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        addSidangLayout.setVerticalGroup(
            addSidangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addSidangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel29)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        editSidang.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 102, 102), new java.awt.Color(255, 102, 102), new java.awt.Color(255, 102, 102), new java.awt.Color(255, 102, 102)));
        editSidang.setPreferredSize(new java.awt.Dimension(106, 80));
        editSidang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editSidangMouseClicked(evt);
            }
        });

        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setText("Edit Sidang");

        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/tubs/gaspol/image/edit-button.png"))); // NOI18N

        javax.swing.GroupLayout editSidangLayout = new javax.swing.GroupLayout(editSidang);
        editSidang.setLayout(editSidangLayout);
        editSidangLayout.setHorizontalGroup(
            editSidangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editSidangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editSidangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                    .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        editSidangLayout.setVerticalGroup(
            editSidangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editSidangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel31)
                .addContainerGap())
        );

        tblSidang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblSidang.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane7.setViewportView(tblSidang);

        javax.swing.GroupLayout contentSidangLayout = new javax.swing.GroupLayout(contentSidang);
        contentSidang.setLayout(contentSidangLayout);
        contentSidangLayout.setHorizontalGroup(
            contentSidangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contentSidangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 1217, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(contentSidangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(contentSidangLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(addSidang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(editSidang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(1005, Short.MAX_VALUE)))
        );
        contentSidangLayout.setVerticalGroup(
            contentSidangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentSidangLayout.createSequentialGroup()
                .addGap(116, 116, 116)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(contentSidangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(contentSidangLayout.createSequentialGroup()
                    .addGap(6, 6, 6)
                    .addGroup(contentSidangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(addSidang, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                        .addComponent(editSidang, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE))
                    .addContainerGap(530, Short.MAX_VALUE)))
        );

        mainContent.add(contentSidang, "card3");

        contentPeriodeSidang.setBackground(new java.awt.Color(255, 255, 255));
        contentPeriodeSidang.setPreferredSize(new java.awt.Dimension(737, 400));

        addPeriode.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 102, 102), new java.awt.Color(255, 102, 102), new java.awt.Color(255, 102, 102), new java.awt.Color(255, 102, 102)));
        addPeriode.setPreferredSize(new java.awt.Dimension(106, 80));
        addPeriode.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addPeriodeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                addPeriodeMouseEntered(evt);
            }
        });

        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setText("Tambah Periode");

        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/tubs/gaspol/image/plus.png"))); // NOI18N

        javax.swing.GroupLayout addPeriodeLayout = new javax.swing.GroupLayout(addPeriode);
        addPeriode.setLayout(addPeriodeLayout);
        addPeriodeLayout.setHorizontalGroup(
            addPeriodeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addPeriodeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        addPeriodeLayout.setVerticalGroup(
            addPeriodeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addPeriodeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel33)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        editPeriode.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 102, 102), new java.awt.Color(255, 102, 102), new java.awt.Color(255, 102, 102), new java.awt.Color(255, 102, 102)));
        editPeriode.setPreferredSize(new java.awt.Dimension(106, 80));
        editPeriode.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editPeriodeMouseClicked(evt);
            }
        });

        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setText("Edit Periode");

        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/tubs/gaspol/image/edit-button.png"))); // NOI18N

        javax.swing.GroupLayout editPeriodeLayout = new javax.swing.GroupLayout(editPeriode);
        editPeriode.setLayout(editPeriodeLayout);
        editPeriodeLayout.setHorizontalGroup(
            editPeriodeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editPeriodeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editPeriodeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                    .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        editPeriodeLayout.setVerticalGroup(
            editPeriodeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editPeriodeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel35)
                .addContainerGap())
        );

        tblPeriode.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblPeriode.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane8.setViewportView(tblPeriode);

        javax.swing.GroupLayout contentPeriodeSidangLayout = new javax.swing.GroupLayout(contentPeriodeSidang);
        contentPeriodeSidang.setLayout(contentPeriodeSidangLayout);
        contentPeriodeSidangLayout.setHorizontalGroup(
            contentPeriodeSidangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contentPeriodeSidangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 1217, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(contentPeriodeSidangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(contentPeriodeSidangLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(addPeriode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(editPeriode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(1005, Short.MAX_VALUE)))
        );
        contentPeriodeSidangLayout.setVerticalGroup(
            contentPeriodeSidangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPeriodeSidangLayout.createSequentialGroup()
                .addGap(116, 116, 116)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(179, Short.MAX_VALUE))
            .addGroup(contentPeriodeSidangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(contentPeriodeSidangLayout.createSequentialGroup()
                    .addGap(6, 6, 6)
                    .addGroup(contentPeriodeSidangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(addPeriode, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                        .addComponent(editPeriode, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE))
                    .addContainerGap(530, Short.MAX_VALUE)))
        );

        mainContent.add(contentPeriodeSidang, "card3");

        javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
        background.setLayout(backgroundLayout);
        backgroundLayout.setHorizontalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(navigasiMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(mainContent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        backgroundLayout.setVerticalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(navigasiMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(mainContent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>                        

    private void navJadwalSidangActionPerformed(java.awt.event.ActionEvent evt) {                                                
        // TODO add your handling code here:
        mainContent.removeAll();
        mainContent.repaint();
        mainContent.revalidate();
        
        //add panel
        mainContent.add(contentSidang);
        mainContent.repaint();
        mainContent.revalidate();
        
        refreshData();
    }                                               

    private void navPeriodeActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
        mainContent.removeAll();
        mainContent.repaint();
        mainContent.revalidate();
        
        //add panel
        mainContent.add(contentPeriodeSidang);
        mainContent.repaint();
        mainContent.revalidate();
        
        refreshData();
    }                                          

    private void HomeUserMouseClicked(java.awt.event.MouseEvent evt) {                                      
        // TODO add your handling code here:
        new Main().setVisible(true);
        this.dispose();
    }                                     

    private void HomeUserPropertyChange(java.beans.PropertyChangeEvent evt) {                                        
        // TODO add your handling code here:
    }                                       

    private void addDosenMouseClicked(java.awt.event.MouseEvent evt) {                                      
        // TODO add your handling code here:
//        reset();
//        btnSimpan.setText("Tambah Dosen");
        new AddOrEdit("dosen", "add", null, -1).setVisible(true);
    }                                     

    private void addDosenMouseEntered(java.awt.event.MouseEvent evt) {                                      
        // TODO add your handling code here:
    }                                     

    private void editDosenMouseClicked(java.awt.event.MouseEvent evt) {                                       
        // TODO add your handling code here:

        int baris = tblDosen.getSelectedRow();
        if(baris >= 0){
            new AddOrEdit("dosen", "edit", modelTblDosen, baris).setVisible(true);
        }else{
            JOptionPane.showMessageDialog(this, "Pilih Baris yang ingin di edit!");
        }
        
    }                                      

    private void navDashboardActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // TODO add your handling code here:
        
       //remove panel sebelum
//        mainContent.removeAll();
//        mainContent.repaint();
//        mainContent.revalidate();
//        
//        //add panel
//        mainContent.add(contentDashboard);
//        mainContent.repaint();
//        mainContent.revalidate();
    }                                            

    private void navDosenActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        
        //remove panel sebelum
        mainContent.removeAll();
        mainContent.repaint();
        mainContent.revalidate();
        
        //add panel
        mainContent.add(contentDosen);
        mainContent.repaint();
        mainContent.revalidate();
        
        refreshData();
        
       
    }                                        

    private void addJadwalDosenMouseClicked(java.awt.event.MouseEvent evt) {                                            
        // TODO add your handling code here:
        new AddOrEdit("jddosen", "add", null, -1).setVisible(true);
    }                                           

    private void addJadwalDosenMouseEntered(java.awt.event.MouseEvent evt) {                                            
        // TODO add your handling code here:
    }                                           

    private void editJadwalDosenMouseClicked(java.awt.event.MouseEvent evt) {                                             
        // TODO add your handling code here:
        int baris = tbJdwlDosen.getSelectedRow();
        if(baris >= 0){
            new AddOrEdit("jddosen", "edit", modelTblJdwlDosen, baris).setVisible(true);
        }else{
            JOptionPane.showMessageDialog(this, "Pilih Baris yang ingin di edit!");
        }
    }                                            

    private void navJadwalDosenActionPerformed(java.awt.event.ActionEvent evt) {                                               
        // TODO add your handling code here:
        //remove panel sebelum
        mainContent.removeAll();
        mainContent.repaint();
        mainContent.revalidate();
        
        //add panel
        mainContent.add(contentJadwalDosen);
        mainContent.repaint();
        mainContent.revalidate();
        
        refreshData();
    }                                              

    private void addKeahlianMouseClicked(java.awt.event.MouseEvent evt) {                                         
        // TODO add your handling code here:
        new AddOrEdit("keahlian", "add", null, -1).setVisible(true);
    }                                        

    private void addKeahlianMouseEntered(java.awt.event.MouseEvent evt) {                                         
        // TODO add your handling code here:
    }                                        

    private void editKeahlianMouseClicked(java.awt.event.MouseEvent evt) {                                          
        // TODO add your handling code here:
        int baris = tblKeahlian.getSelectedRow();
        if(baris >= 0){
            new AddOrEdit("keahlian", "edit", modelTblKeahlian, baris).setVisible(true);
        }else{
            JOptionPane.showMessageDialog(this, "Pilih Baris yang ingin di edit!");
        }
    }                                         

    private void addMhsMouseClicked(java.awt.event.MouseEvent evt) {                                    
        // TODO add your handling code here:
        new AddOrEdit("mahasiswa", "add", null, -1).setVisible(true);
    }                                   

    private void addMhsMouseEntered(java.awt.event.MouseEvent evt) {                                    
        // TODO add your handling code here:
    }                                   

    private void editMhsMouseClicked(java.awt.event.MouseEvent evt) {                                     
        // TODO add your handling code here:
        int baris = tblMhs.getSelectedRow();
        if(baris >= 0){
            new AddOrEdit("mahasiswa", "edit", modelTblMahasiswa, baris).setVisible(true);
        }else{
            JOptionPane.showMessageDialog(this, "Pilih Baris yang ingin di edit!");
        }
    }                                    

    private void addPAMouseClicked(java.awt.event.MouseEvent evt) {                                   
        // TODO add your handling code here:
        new AddOrEdit("pa", "add", null, -1).setVisible(true);
        
    }                                  

    private void addPAMouseEntered(java.awt.event.MouseEvent evt) {                                   
        // TODO add your handling code here:
    }                                  

    private void editPAMouseClicked(java.awt.event.MouseEvent evt) {                                    
        // TODO add your handling code here:
        int baris = tblPA.getSelectedRow();
        if(baris >= 0){
            new AddOrEdit("pa", "edit", modelTblPA, baris).setVisible(true);
        }else{
            JOptionPane.showMessageDialog(this, "Pilih Baris yang ingin di edit!");
        }
    }                                   

    private void addRuangMouseClicked(java.awt.event.MouseEvent evt) {                                      
        // TODO add your handling code here:
        
        new AddOrEdit("ruangan", "add", null, -1).setVisible(true);
    }                                     

    private void addRuangMouseEntered(java.awt.event.MouseEvent evt) {                                      
        // TODO add your handling code here:
        
    }                                     

    private void editRuangMouseClicked(java.awt.event.MouseEvent evt) {                                       
        // TODO add your handling code here:
        int baris = tblRuang.getSelectedRow();
        if(baris >= 0){
            new AddOrEdit("ruangan", "edit", modelTblRuang, baris).setVisible(true);
        }else{
            JOptionPane.showMessageDialog(this, "Pilih Baris yang ingin di edit!");
        }
    }                                      

    private void addSidangMouseClicked(java.awt.event.MouseEvent evt) {                                       
        // TODO add your handling code here:
        new AddOrEdit("jadwalsidang", "add", null, -1).setVisible(true);
    }                                      

    private void addSidangMouseEntered(java.awt.event.MouseEvent evt) {                                       
        // TODO add your handling code here:
    }                                      

    private void editSidangMouseClicked(java.awt.event.MouseEvent evt) {                                        
        // TODO add your handling code here:
        int baris = tblSidang.getSelectedRow();
        if(baris >= 0){
            new AddOrEdit("jadwalsidang", "edit", modelTblJdwlSidang, baris).setVisible(true);
        }else{
            JOptionPane.showMessageDialog(this, "Pilih Baris yang ingin di edit!");
        }
    }                                       

    private void editPeriodeMouseClicked(java.awt.event.MouseEvent evt) {                                         
        // TODO add your handling code here:
        int baris = tblPeriode.getSelectedRow();
        if(baris >= 0){
            new AddOrEdit("periode", "edit", modelTblPeriode, baris).setVisible(true);
        }else{
            JOptionPane.showMessageDialog(this, "Pilih Baris yang ingin di edit!");
        }
    }                                        

    private void addPeriodeMouseEntered(java.awt.event.MouseEvent evt) {                                        
        // TODO add your handling code here:
    }                                       

    private void addPeriodeMouseClicked(java.awt.event.MouseEvent evt) {                                        
        // TODO add your handling code here:
        new AddOrEdit("periode", "add", null, -1).setVisible(true);
    }                                       

    private void navKeahlianActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
        
        mainContent.removeAll();
        mainContent.repaint();
        mainContent.revalidate();
        
        //add panel
        mainContent.add(contentKeahlian);
        mainContent.repaint();
        mainContent.revalidate();
        
        refreshData();
    }                                           

    private void navMahasiswaActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // TODO add your handling code here:
        
        mainContent.removeAll();
        mainContent.repaint();
        mainContent.revalidate();
        
        //add panel
        mainContent.add(contentMahasiswa);
        mainContent.repaint();
        mainContent.revalidate();
        
        refreshData();
    }                                            

    private void navPAActionPerformed(java.awt.event.ActionEvent evt) {                                      
        // TODO add your handling code here:
        mainContent.removeAll();
        mainContent.repaint();
        mainContent.revalidate();
        
        //add panel
        mainContent.add(contentPA);
        mainContent.repaint();
        mainContent.revalidate();
        
        refreshData();
    }                                     

    private void navRuangActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        mainContent.removeAll();
        mainContent.repaint();
        mainContent.revalidate();
        
        //add panel
        mainContent.add(contentRuang);
        mainContent.repaint();
        mainContent.revalidate();
        
        refreshData();
    }                                        

    private void formMouseEntered(java.awt.event.MouseEvent evt) {                                  
        // TODO add your handling code here:
        
    }                                 

    private void navRuangMouseClicked(java.awt.event.MouseEvent evt) {                                      
        // TODO add your handling code here:
        refreshData();
    }                                     

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PengelolaDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PengelolaDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PengelolaDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PengelolaDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PengelolaDashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JPanel HomeUser;
    private javax.swing.JPanel addDosen;
    private javax.swing.JPanel addJadwalDosen;
    private javax.swing.JPanel addKeahlian;
    private javax.swing.JPanel addMhs;
    private javax.swing.JPanel addPA;
    private javax.swing.JPanel addPeriode;
    private javax.swing.JPanel addRuang;
    private javax.swing.JPanel addSidang;
    private javax.swing.JPanel background;
    private javax.swing.JPanel contentDashboard;
    private javax.swing.JPanel contentDosen;
    private javax.swing.JPanel contentJadwalDosen;
    private javax.swing.JPanel contentKeahlian;
    private javax.swing.JPanel contentMahasiswa;
    private javax.swing.JPanel contentPA;
    private javax.swing.JPanel contentPeriodeSidang;
    private javax.swing.JPanel contentRuang;
    private javax.swing.JPanel contentSidang;
    private javax.swing.JPanel editDosen;
    private javax.swing.JPanel editJadwalDosen;
    private javax.swing.JPanel editKeahlian;
    private javax.swing.JPanel editMhs;
    private javax.swing.JPanel editPA;
    private javax.swing.JPanel editPeriode;
    private javax.swing.JPanel editRuang;
    private javax.swing.JPanel editSidang;
    private javax.swing.JPanel header;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JLabel logo;
    private javax.swing.JPanel mainContent;
    private javax.swing.JButton navDashboard;
    private javax.swing.JButton navDosen;
    private javax.swing.JButton navJadwalDosen;
    private javax.swing.JButton navJadwalSidang;
    private javax.swing.JButton navKeahlian;
    private javax.swing.JButton navMahasiswa;
    private javax.swing.JButton navPA;
    private javax.swing.JButton navPeriode;
    private javax.swing.JButton navRuang;
    private javax.swing.JPanel navigasiMenu;
    private javax.swing.JTable tbJdwlDosen;
    private javax.swing.JTable tblDosen;
    private javax.swing.JTable tblKeahlian;
    private javax.swing.JTable tblMhs;
    private javax.swing.JTable tblPA;
    private javax.swing.JTable tblPeriode;
    private javax.swing.JTable tblRuang;
    private javax.swing.JTable tblSidang;
    // End of variables declaration                   
}
