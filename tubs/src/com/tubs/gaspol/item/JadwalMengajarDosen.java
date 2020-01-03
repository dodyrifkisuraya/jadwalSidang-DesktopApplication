/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tubs.gaspol.item;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author DORIZU
 */
public class JadwalMengajarDosen extends Jadwal {
    private int hariKe;
    private String idDosen;
    DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");

    public JadwalMengajarDosen(int id,String idDosen, int hariKe, Time jamMulai, Time jamSelesai) {
        super(id,jamMulai,jamSelesai);
        this.hariKe = hariKe;
        this.idDosen = idDosen;
    }

    public int getHariKe() {
        return hariKe;
    }

    public String getIdDosen() {
        return idDosen;
    }
    
    
}
