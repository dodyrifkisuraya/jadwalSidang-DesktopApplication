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
public class PeriodeSidang {
    private int id;
    private int tahun;
    private String tanggalAwal;
    private String tanggalAkhir;
    private int periodeKe;

    public PeriodeSidang(int id, int tahun, String tanggalAwal, String tanggalAkhir, int periodeKe) {
        this.id = id;
        this.tahun = tahun;
        this.tanggalAwal = tanggalAwal;
        this.tanggalAkhir = tanggalAkhir;
        this.periodeKe = periodeKe;
    }

    public int getId() {
        return id;
    }

    public int getTahun() {
        return tahun;
    }

    public String getTanggalAwal() {
        return tanggalAwal;
    }

    public String getTanggalAkhir() {
        return tanggalAkhir;
    }

    public int getPeriodeKe() {
        return periodeKe;
    }
    
    
}
