/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tubs.gaspol.item;

import java.sql.Time;

/**
 * @author seifer
 */
public class Jadwal {
    private int idJadwal;
    private Time jamMulai;
    private Time jamSelesai;

    public Jadwal(Time jamMulai, Time jamSelesai) {
        this.jamMulai = jamMulai;
        this.jamSelesai = jamSelesai;
    }

    public Jadwal(int id, Time jamMulai, Time jamSelesai) {
        this.jamMulai = jamMulai;
        this.jamSelesai = jamSelesai;
        this.idJadwal = id;
    }
    
    public Time getJamMulai() {
        return jamMulai;
    }

    public void setJamMulai(Time jamMulai) {
        this.jamMulai = jamMulai;
    }

    public Time getJamSelesai() {
        return jamSelesai;
    }

    public void setJamSelesai(Time jamSelesai) {
        this.jamSelesai = jamSelesai;
    }

    public int getIdJadwal() {
        return idJadwal;
    }
    
    
    
}
