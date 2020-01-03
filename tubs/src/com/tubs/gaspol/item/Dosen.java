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
public class Dosen extends Personal{
    private String kodeDosen;
    private int idKeahlian1;
    private int idKeahlian2;
    private String nip;

    public Dosen(String kodeDosen, String nip, String name, String email, int idKeahlian1, int idKeahlian2) {
        super(name, email);
        this.kodeDosen = kodeDosen;
        this.idKeahlian1 = idKeahlian1;
        this.idKeahlian2 = idKeahlian2;
        this.nip = nip;
    }

    public int getIdKeahlian1() {
        return idKeahlian1;
    }

    public void setIdKeahlian1(int idKeahlian1) {
        this.idKeahlian1 = idKeahlian1;
    }

    public int getIdKeahlian2() {
        return idKeahlian2;
    }

    public void setIdKeahlian2(int idKeahlian2) {
        this.idKeahlian2 = idKeahlian2;
    }

    
    public String getKodeDosen() {
        return kodeDosen;
    }

    public void setKodeDosen(String kodeDosen) {
        this.kodeDosen = kodeDosen;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    
    
    
    
    
}
