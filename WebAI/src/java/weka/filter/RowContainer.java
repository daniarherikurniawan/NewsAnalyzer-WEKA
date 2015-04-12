/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weka.filter;

/**
 *
 * @author akhfa
 */
public class RowContainer {
    public String No;
    public String Berita;
    public String PrediksiKategori;
    public RowContainer(int _No, String _Berita, String _PrediksiKategori)
    {
        No = String.valueOf(_No);
        Berita = _Berita;
        PrediksiKategori = _PrediksiKategori;
    }
    public String getNo()
    {
        return No;
    }
    
    public String getBerita()
    {
        return Berita;
    }
    public String getPrediksiKategori()
    {
        Klasifikasi cls = new Klasifikasi();
        cls.inputString(Berita);
        cls.makeInstance();
        //jika test yang dibaca *ARFF
        //cls.loadARFF("src/data/test.arff");
        cls.loadModel("src/data/training_data.model");
       // cls.classify(); 
        PrediksiKategori = cls.getKategori();
        return PrediksiKategori;
    }
}
