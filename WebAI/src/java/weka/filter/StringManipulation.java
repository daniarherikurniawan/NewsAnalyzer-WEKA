/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weka.filter;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.meta.FilteredClassifier;

/**
 *
 * @author akhfa
 */
public class StringManipulation {

    /**
     * @param args the command line arguments
     */
    public ArrayList<RowContainer> data;
    private ArrayList<String> daftarJudul;
    private ArrayList<String> daftarArtikel;
    private String judul;
    private String text;
    String ganti(String text, String yangDiganti, String Pengganti)
    {
        text = text.replaceAll(yangDiganti, Pengganti);
        return text;
    }
    
    /**
     * Memproses string dalam file input csv dan mendapatkan list of judul dan list of artikel
     * @param namaFile
     * @throws FileNotFoundException 
     */
    public void processingData(String namaFile) throws FileNotFoundException
    {
        daftarJudul = new ArrayList<String>();
        daftarArtikel= new ArrayList<String>();
        BufferedReader artikel = new BufferedReader(new FileReader(namaFile));
        StringBuilder stringBuilder = new StringBuilder();
        
        String line;     //Untuk membaca 1 line
        String oneArtikel; //Untuk memanipulasi tanda baca dalam 1 artikel
        String pattern = "'(.*)','(.*)'";
        try {
            line = artikel.readLine();
            while(line != null)
            {
                line = ganti(line, "\"\\d*\",NULL,", "");//Hapus 2 kolom awal
                stringBuilder.append(line);
//                System.out.println(line);
                if(line.contains("NULL")) //ketika ada NULL di dalam 1 line
                {
//                    System.out.println("Masuk if");
                    oneArtikel = stringBuilder.toString();
//                    System.out.println(oneArtikel);

                    oneArtikel = ganti(oneArtikel, ",\"\\d*-\\d*-\\d* \\d*:\\d*:\\d*\",\"\\d*-\\d*-\\d* \\d*:\\d*:\\d*\"", "");  //Hapus format tanggal
//                    System.out.println("Hapus format tanggal:"+ oneArtikel);
                    oneArtikel = ganti(oneArtikel, ",\"http:.*\"", "");                                        //Ganti alamat web jadi ""
//                    System.out.println("Hapus alamat web: " + oneArtikel);
                    oneArtikel = ganti(oneArtikel, ",NULL", "");
//                    System.out.println("Ganti NULL dengan \"\":"+ oneArtikel);
//                    oneArtikel = ganti(oneArtikel, ",", "");
//                    System.out.println("Ganti , dengan \"\":"+ oneArtikel);
//                    oneArtikel = ganti(oneArtikel, "\\.", "");
//                    System.out.println("Ganti . dengan \"\":"+ oneArtikel);
                    oneArtikel = ganti(oneArtikel, "\"", "'");
//                    System.out.println(oneArtikel);
                    
                    //Ambil text dan judul artikel
                    Pattern r = Pattern.compile(pattern);
                    Matcher m = r.matcher(oneArtikel);
                    if(m.find())
                    {
                        text = m.group(1);
                        judul = m.group(2);
                        
//                        System.out.println("text = "+text);
//                        System.out.println("judul = "+judul);
                    }
                    
                    daftarJudul.add(judul);
                    daftarArtikel.add(oneArtikel);
                    
//                    daftarArtikel.add(stringBuilder.toString());
                    stringBuilder = new StringBuilder();
                }
                line = artikel.readLine();
            }
        } catch (IOException ex) {
            Logger.getLogger(StringManipulation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<String> getDaftarArtikel()
    {
        return daftarArtikel;
    }
    
    public void processingDataUseLib(String namaFile) throws FileNotFoundException, IOException
    {
        daftarJudul = new ArrayList<String>();
        daftarArtikel= new ArrayList<String>();
        CSVReader reader = new CSVReader(new FileReader(namaFile));
        List Entries = reader.readAll();
        
        for(String [] entry :(List<String[]>) Entries)
        {
            daftarJudul.add( entry[5]);
            String artikel = entry[2];
            
            daftarArtikel.add(artikel);
        }
        
    }
    
    public ArrayList<String> getDaftarJudul()
    {
        return daftarJudul;
    }
    
    public void saveCSV(String No, String textBerita, String prediksiKategory) throws IOException
    {
        CSVWriter writer = new CSVWriter(new FileWriter("yourfile.csv"), '\t');
        String temp = No + "#"+textBerita+"#"+prediksiKategory;
        String [] oneRow = temp.split("#");
        writer.writeNext(oneRow);
        writer.close();
    }
    
    public void saveCSV(ArrayList<RowContainer> rows) throws IOException
    {
        CSVWriter writer = new CSVWriter(new FileWriter("src/data/hasil.csv"), ',');
        for(RowContainer row : rows)
        {
            String temp = row.getNo() + "#" + row.getBerita() + "#" + row.getPrediksiKategori();
            String []oneRow = temp.split("#");
            writer.writeNext(oneRow);
        }
        writer.close();
    }
    
    /**
     * Menambahkan 1 baris data ke arff
     * @param namaFile
     * @param kategori
     * @param berita 
     * @throws java.io.IOException 
     */
    public void appendOneData(String namaFile, String kategori, String berita) throws IOException
    {
        FileWriter fw = new FileWriter(namaFile, true);
        fw.write("'" + kategori + "','" + berita + "'\n");
        fw.close();
    }
    
    public void prosesFileCSV(String path){
        // TODO code application logic here
        weka.filter.StringManipulation Artikel = new StringManipulation();
        ArrayList<String> daftarArtikel;
        ArrayList<String> daftarJudul;
        try {
            Artikel.processingDataUseLib(path);
            daftarArtikel = Artikel.getDaftarArtikel();
            daftarJudul = Artikel.getDaftarJudul();
            //System.out.println(daftarJudul.get(daftarJudul.size()-1));
            //System.out.println(daftarArtikel.get(daftarArtikel.size()-1));
            data = new ArrayList<RowContainer>();
            RowContainer temp;
            for(int i = 1; i <= daftarJudul.size(); i++)
            {
                temp = new RowContainer(i, daftarArtikel.get(i-1), "?");
                data.add(temp);
            }
            Artikel.saveCSV(data);
        } catch (IOException ex) {
            Logger.getLogger(StringManipulation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
