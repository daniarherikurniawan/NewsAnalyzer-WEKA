/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package weka.filter;

import java.util.ArrayList;

/**
 *
 * @author daniar heri
 */
public class GenerateHasil {
    public ArrayList<String> arrayKategori;
    
    public GenerateHasil() {
        arrayKategori = new ArrayList<String>();
        arrayKategori.add("Daniar");
        arrayKategori.add("Dede");
        arrayKategori.add("Kevin");
        arrayKategori.add("Ichwan");
    }
    
    public GenerateHasil(ArrayList<String> PrediksiKategori){
        arrayKategori =  PrediksiKategori;
    }
    
    public ArrayList<String> getArrayKategori() {
        return arrayKategori;
    }
    
    public void iniMethod(int idx) {
        arrayKategori.get(idx);
    }
    
}