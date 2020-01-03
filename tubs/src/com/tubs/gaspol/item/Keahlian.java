/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tubs.gaspol.item;

/**
 *
 * @author DORIZU
 */
public class Keahlian {
    int id;
    String namaKeahlian;

    public Keahlian(int id, String namaKeahlian) {
        this.id = id;
        this.namaKeahlian = namaKeahlian;
    }

    public int getId() {
        return id;
    }

    public String getNamaKeahlian() {
        return namaKeahlian;
    }
    
    
}
