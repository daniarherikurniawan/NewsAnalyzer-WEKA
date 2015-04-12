/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package weka.filter;

import java.util.ArrayList;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.meta.FilteredClassifier;

/**
 *
 * @author Ichwan Haryo Sembodo
 */
public class WekaFilter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        /*FilteredClassifier tes = new FilteredClassifier();
        try{
            tes.setClassifier(new NaiveBayes());
            System.out.println("berhasil set classifier");
        } catch (Exception e) {
            System.out.println("set classifier gagal");
        }
        
        FilterDataset filter = new FilterDataset();
        filter.loadDataset("src/data/training_data.arff");
        filter.setClassifier(tes);
        //evaluate() harus dijalankan sebelum build classifier
        filter.evaluate();
        filter.buildClassifier();
        filter.saveModel("src/data/training_data.model");
        */
        //buat klasifikasi suatu string berita/berita dalam bentuk *arff
        
        Klasifikasi cls = new Klasifikasi();
        //jika test yang dibaca teks berita biasa
        //cls.load("src/data/test.txt");
        //cls.makeInstance();
        //jika test yang dibaca *ARFF
        //cls.loadARFF("src/data/test.arff");
        cls.loadModel("C:\\Users\\Ichwan Haryo Sembodo\\Documents\\NetBeansProjects\\WebAI\\web\\data\\training_data.model");
        ArrayList<String> asd = cls.getDaftarTextFromCSV("C:\\Users\\Ichwan Haryo Sembodo\\Documents\\NetBeansProjects\\WebAI\\web\\data\\artikel.csv");
        for(int i = 0; i<asd.size(); i++){
            cls.setNull();
            cls.inputString(asd.get(i));
            cls.makeInstance();
            System.out.println(cls.getKategori()); 
        }
        
        /*
        // buat inputan yang berupa string
        String StringBerita = "";
        Klasifikasi cls = new Klasifikasi();
        cls.inputString(StringBerita);
        cls.makeInstance();
        cls.loadModel("src/data/training_data.model");
        System.out.println("Kategorinya : "+ cls.getKategori());
        
        // buat inputan yang berupa file csv :
        StringManipulation prosesCSV = new StringManipulation();
        prosesCSV.prosesFileCSV("src/data/artikel.csv");
        
        // buat build ulang model
        StringManipulation appendArff = new StringManipulation();
        String kategori = "", berita = "";
        prosesCSV.appendOneData("src/data/training_data.arff",kategori,berita);*/
    }
}
