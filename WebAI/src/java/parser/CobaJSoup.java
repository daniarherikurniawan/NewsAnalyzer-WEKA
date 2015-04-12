/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package parser;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.String;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author user
 */
public class CobaJSoup {
    
    public static void tes(String website) throws IOException {          
        ArrayList absHrefs=new ArrayList<String>();
        String absHref;
        Document doc=null;        
        Connection con = Jsoup.connect(website).timeout(60000);
        con.ignoreHttpErrors(true).followRedirects(true);
        Response resp=con.execute();
        if (resp.statusCode()==200){
            boolean add = absHrefs.add(website);
            doc=con.get();
            Elements elements_link=doc.getElementsByTag("a");         
            for (Element element : elements_link) {
                if (!(element.attr("abs:href").isEmpty())){                
                    absHref=element.attr("abs:href"); 
                    int cek_sama=0;
                    int iteration=0;
                    while (iteration<absHrefs.size()){
                        if (absHref.equalsIgnoreCase(absHrefs.get(iteration).toString())){
                            cek_sama+=1;                            
                        }
                        iteration++;
                    }                   
                    if (cek_sama==0){
                        absHrefs.add(absHref);                                      
                    }
                } 
            }       
            elements_link.clear();             
            int j=1;
            int index=0;
            System.out.println("Size sebelum : " + absHrefs.size());
            if ((absHrefs.size()<7) && (absHrefs.size()>1)){                
                do{
                    con=Jsoup.connect(absHrefs.get(j).toString()).timeout(60000);
                    con.ignoreHttpErrors(true).followRedirects(true);
                    resp=con.execute();
                    if (resp.statusCode()==200){
                        doc=con.get();
                        elements_link=doc.getElementsByTag("a");
                        for (Element element : elements_link) {
                            if (!(element.attr("abs:href").isEmpty())){                        
                                absHref=element.attr("abs:href");  
                                int cek_sama=0;
                                int iteration=0;
                                while (iteration<absHrefs.size()){
                                    if (absHref.equalsIgnoreCase(absHrefs.get(iteration).toString())){
                                        cek_sama+=1;
                                    }
                                    iteration++;
                                }                   
                                if (cek_sama==0){
                                    absHrefs.add(absHref);                                      
                                }                                                   
                            }
                        }
                    } 
                    j++;
                }while((j<=absHrefs.size()-1) && (absHrefs.size()<7));            
            }        
            System.out.println("Size sesudah " + absHrefs.size());
            Elements elements_text = null;
            PrintWriter writer=new PrintWriter("C:/Users/Me/Desktop/tes1.txt","UTF-8");
            if (absHrefs.size()>10){
                while (index<11){
                    //writer.println(index);            
                    //writer.println(absHrefs.get(index).toString());   
                    try{
                        con=Jsoup.connect(absHrefs.get(index).toString()).timeout(20000);
                        con.ignoreHttpErrors(true).followRedirects(true);
                        resp=con.execute();                       
                        if (resp.statusCode()==200){
                            doc=con.get();           
                            //writer.println("Sedang menulis isi dari link index : "+ index);                                                  
                            writer.println(doc.text());                          
                        }                                                
                    }
                    catch(IOException e){
                        writer.println(e);
                    }
                    index++; 
                } 
            }
            else{
                for (index=0;index<=absHrefs.size()-1;index++){
                    //writer.println(index);            
                    //writer.println(absHrefs.get(index).toString()); 
                    try{
                        con=Jsoup.connect(absHrefs.get(index).toString()).timeout(20000);
                        con.ignoreHttpErrors(true).followRedirects(true);
                        resp=con.execute();                          
                        if (resp.statusCode()==200){
                            doc=con.get();
                            //writer.println("Sedang menulis isi dari link index : "+ index);
                            writer.println(doc.text());               
                        }
                    }
                    catch(IOException e){
                        writer.println(e);
                    }
 
                }                    
            }
            //System.out.println("Selesai nge crawl ^^");
            writer.close();
        }
    }
    
    public static String getTextFromURL(String url) throws IOException{
        String berita = "";
        Document doc = null;
        
        Connection con = Jsoup.connect(url).timeout(60000);
        con.ignoreHttpErrors(true).followRedirects(true);
        Response resp=con.execute();
        if (resp.statusCode()==200){
            doc=con.get();   
            berita=doc.text();
        }       
        return berita;
    }
//    public static void main(String[] args) {
//        try {
//            tes();
//        } catch (IOException ex) {
//            Logger.getLogger(CobaJSoup.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    
}
