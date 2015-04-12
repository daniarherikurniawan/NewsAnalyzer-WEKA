/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package weka.filter;

import java.util.ArrayList;

/**
 *
 * @author Ichwan Haryo Sembodo
 */
public class ContainerData {
    private static ArrayList<String> listBerita= new ArrayList<String>();
    private static String berita;
    private static String state;

    /**
     * @return the listBerita
     */
    public static ArrayList<String> getListBerita() {
        return listBerita;
    }

    /**
     * @param aListBerita the listBerita to set
     */
    public static void setListBerita(ArrayList<String> aListBerita) {
        listBerita = aListBerita;
    }

    /**
     * @return the berita
     */
    public static String getBerita() {
        return berita;
    }

    /**
     * @param aBerita the berita to set
     */
    public static void setBerita(String aBerita) {
        berita = aBerita;
    }

    /**
     * @return the state
     */
    public static String getState() {
        return state;
    }

    /**
     * @param aState the state to set
     */
    public static void setState(String aState) {
        state = aState;
    }
    
}
