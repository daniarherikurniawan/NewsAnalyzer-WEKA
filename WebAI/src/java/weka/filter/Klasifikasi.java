/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package weka.filter;

import weka.core.*;
import weka.core.FastVector;
import weka.classifiers.meta.FilteredClassifier;
import java.util.List;
import java.util.ArrayList;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ichwan Haryo Sembodo
 */
public class Klasifikasi {
    private String text;
    private Instances instances;
    private FilteredClassifier classifier;
    
    public void load(String DATA_SOURCE) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(DATA_SOURCE));
            String line;
            text = "";
            
            while ((line = reader.readLine()) != null) {
            text = text + " " + line;
            }
            /*
            text = text.replaceAll(" dan ", " ");
            text = text.replaceAll(" atau ", " ");
            text = text.replaceAll(" yang ", " ");
            text = text.replaceAll(" di ", " ");
            text = text.replaceAll(" dari ", " ");
            text = text.replaceAll(" ke ", " ");
            text = text.replaceAll(" untuk ", " ");
            text = text.replaceAll(" seperti ", " ");
            text = text.replaceAll(" apakah ", " ");
            text = text.replaceAll(" apa ", " ");
            text = text.replaceAll(" bagaimana ", " ");
            text = text.replaceAll(" jika ", " ");
            text = text.replaceAll(" karena ", " ");
            text = text.replaceAll(" kapan ", " ");
            text = text.replaceAll(" dimana ", " ");
            text = text.replaceAll(" berapa ", " ");
            text = text.replaceAll(" mengapa ", " ");
            text = text.replaceAll(" ataupun ", " ");
            text = text.replaceAll(" pun ", " ");
            text = text.replaceAll(" akan ", " ");
            text = text.replaceAll(" meskipun ", " ");
            text = text.replaceAll(" pada ", " ");
            text = text.replaceAll(" dalam ", " ");
            text = text.replaceAll(" luar ", " ");
            text = text.replaceAll(" jarang ", " ");
            */
            
            System.out.println("===== Loaded text data: " + DATA_SOURCE + " =====");
            reader.close();
         //   System.out.println(text);
        }
        catch (IOException e) {
            System.out.println("Problem found when reading: " + DATA_SOURCE);
        }
    }
    public void inputString(String text){
        this.text = text;
    }
    public void loadARFF(String DATA_SOURCE) {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(DATA_SOURCE));
            Object tmp = in.readObject();
            instances = (Instances) tmp;
            instances.setClassIndex(0);
            in.close();
            System.out.println("===== Loaded model: " + DATA_SOURCE + " =====");
        }
        catch (Exception e) {           
            System.out.println("Problem found when reading: " + DATA_SOURCE);
        }
    }
    
    public String loadModel(String DATA_SOURCE) {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(DATA_SOURCE));
            Object tmp = in.readObject();
            classifier = (FilteredClassifier) tmp;
            in.close();
            System.out.println("===== Loaded model: " + DATA_SOURCE + " =====");
            return "yes sucses";
        }
        catch (Exception e) {           
            System.out.println("Problem found when reading: " + DATA_SOURCE);
            return "Failure "+e.getMessage();
        }
    }
    
    public void makeInstance() {
        // Create the attributes, class and text
        FastVector fvNominalVal = new FastVector(2);
        fvNominalVal.addElement("Pendidikan");
        fvNominalVal.addElement("Politik");
        fvNominalVal.addElement("Hukum dan Kriminal");
        fvNominalVal.addElement("Sosial Budaya");
        fvNominalVal.addElement("Olahraga");
        fvNominalVal.addElement("Teknologi dan Sains");
        fvNominalVal.addElement("Hiburan");
        fvNominalVal.addElement("Bisnis dan Ekonomi");
        fvNominalVal.addElement("Kesehatan");
        fvNominalVal.addElement("Bencana dan Kecelakaan");
        
        Attribute attribute1 = new Attribute("class", fvNominalVal);
        Attribute attribute2 = new Attribute("berita",(FastVector) null);
        // Create list of instances with one element
        FastVector fvWekaAttributes = new FastVector(2);
        fvWekaAttributes.addElement(attribute1);
        fvWekaAttributes.addElement(attribute2);
        instances = new Instances("Artikel", fvWekaAttributes, 1);
        // Set class index
        instances.setClassIndex(0);
        // Create and add the instance
        DenseInstance instance = new DenseInstance(2);
        instance.setValue(attribute2, text);
        // Another way to do it:
        // instance.setValue((Attribute)fvWekaAttributes.elementAt(1), text);
        instances.add(instance);
        System.out.println("===== Instance created with reference dataset =====");
        System.out.println(instances);
    }
    
    public void classify() {
        System.out.println("Problem found when classifying the text");
        try {
            double pred = classifier.classifyInstance(instances.instance(0));
            System.out.println("===== Classified instance =====");
            System.out.println("Class predicted: " + instances.classAttribute().value((int) pred));
        }
        catch (Exception e) {
            System.out.println("Problem found when classifying the text");
        }
    }
    public String getKategori(){
        double pred;
        try {
            pred = classifier.classifyInstance(instances.instance(0));
            System.out.println("Get kategori berhasil!");
            return instances.classAttribute().value((int) pred);
        } catch (Exception ex) {
            Logger.getLogger(Klasifikasi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Kategori Error"; 
    }
    
    public ArrayList<String> getDaftarTextFromCSV(String path){
        ArrayList<String> listText = new ArrayList<String>();
        StringManipulation str = new StringManipulation();
        try{
            str.processingDataUseLib(path);
            System.out.println("Berhasil membaca file CSV");
        } catch (Exception e){
            System.out.println("Error getting list text from csv");
        }
        
        for(int i = 0; i<str.getDaftarArtikel().size(); i++){
            System.out.println("iterasi untuk get daftar text yang ke-" + i);
            System.out.println(str.getDaftarJudul().get(i) + " " + str.getDaftarArtikel().get(i));
            listText.add(str.getDaftarJudul().get(i) + " " + str.getDaftarArtikel().get(i));
        }
        
        return listText;
    }
    
    public void setNull(){
        instances = null;
        text = null;
    }
}
