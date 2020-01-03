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
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Enkripsi {
    MessageDigest hash;

    public String hash(String plain){
        try {
            this.hash = MessageDigest.getInstance("SHA1");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Enkripsi.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.hash.update(plain.getBytes(),0,plain.length());
        String hashed = new BigInteger(1,hash.digest()).toString(16);
        return hashed;
    }
    
    
    
    
}
