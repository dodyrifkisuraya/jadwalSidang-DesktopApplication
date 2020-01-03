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
public class ProyekAkhir {
    private String judulPa;
    private String nim;
    private String KDDosbing1;
    private String KDDosbing2;
    private int idKeahlian;
    private int idPA;
    private int status;
    
    public ProyekAkhir(int idPA, String judulPa, String nim, String KDDosbing1, String KDDosbing2, int idKeahlian, int status) {
        this.idPA = idPA;
        this.judulPa = judulPa;
        this.nim = nim;
        this.KDDosbing1 = KDDosbing1;
        this.KDDosbing2 = KDDosbing2;
        this.idKeahlian = idKeahlian;
        this.status = status;
    }


    public ProyekAkhir(String judulPa, String nim, String KDDosbing1, String KDDosbing2, int idKeahlian) {
        this.judulPa = judulPa;
        this.nim = nim;
        this.KDDosbing1 = KDDosbing1;
        this.KDDosbing2 = KDDosbing2;
        this.idKeahlian = idKeahlian;
    }

    public int getIdPA() {
        return idPA;
    }

    public int getStatus() {
        return status;
    }

        
    
    public String getJudulPa() {
        return judulPa;
    }

    public void setJudulPa(String judulPa) {
        this.judulPa = judulPa;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getKDDosbing1() {
        return KDDosbing1;
    }

    public void setKDDosbing1(String KDDosbing1) {
        this.KDDosbing1 = KDDosbing1;
    }

    public String getKDDosbing2() {
        return KDDosbing2;
    }

    public void setKDDosbing2(String KDDosbing2) {
        this.KDDosbing2 = KDDosbing2;
    }

    public int getIdKeahlian() {
        return idKeahlian;
    }

    public void setIdKeahlian(int idKeahlian) {
        this.idKeahlian = idKeahlian;
    }
    
    
}
