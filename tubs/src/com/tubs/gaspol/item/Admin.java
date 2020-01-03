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
public class Admin {
    
    String username, password, nama, nip;

    public Admin(String username, String password, String nama, String nip) {
        this.username = username;
        this.password = password;
        this.nama = nama;
        this.nip = nip;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getNama() {
        return nama;
    }

    public String getNip() {
        return nip;
    }
    
    
    
}
