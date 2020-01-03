/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tubs.gaspol.item;

/**
 *
 * @author seifer
 */
public class Periode {
    private int id;
    private String tanggalAwal, tanggalAkhir, periodeKe, tahun;

    public Periode(int id, String tanggalAwal, String tanggalAkhir, String periodeKe, String tahun) {
        this.id = id;
        this.tanggalAwal = tanggalAwal;
        this.tanggalAkhir = tanggalAkhir;
        this.periodeKe = periodeKe;
        this.tahun = tahun;
    }

    public int getId() {
        return id;
    }

    public String getTahun() {
        return tahun;
    }
    
    

    public String getTanggalAwal() {
        return tanggalAwal;
    }

    public String getTanggalAkhir() {
        return tanggalAkhir;
    }

    public String getPeriodeKe() {
        return periodeKe;
    }
    
    
    
}
