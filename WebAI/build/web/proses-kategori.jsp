<%-- 
    Document   : proses-kategori
    Created on : Nov 30, 2014, 7:14:25 PM
    Author     : user
--%>

<%@page import="java.io.FileWriter"%>
<%@page import="com.opencsv.CSVWriter"%>
<%@page import="weka.filter.StringManipulation"%>
<%@page import="weka.filter.ContainerData"%>
<%@page import="java.util.ArrayList"%>
<%@page import="weka.filter.GenerateHasil"%>
<%@page import="parser.CobaJSoup"%>
<%@page import="weka.filter.Klasifikasi"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String berita;
    String jspPath = session.getServletContext().getRealPath("/");
    try {
        /* TODO output your page here. You may use following sample code. */
        String tipe = request.getParameter("drop");
        if (tipe.equals("text")) {
            ContainerData.setState("text");
            berita = request.getParameter("textInput");
            // buat inputan yang berupa string          
            Klasifikasi cls = new Klasifikasi();
            cls.inputString(berita);
            cls.makeInstance();
            cls.loadModel(jspPath + "data/training_data.model");
            out.println("<p>" + cls.getKategori() + "</p>");
            ContainerData.setBerita(berita);
            out.println("<div id='berita1' />");
            String petik = "\"";
            String fungsi = petik + "koreksi('berita1');" + petik;
            out.print("<input type='button' value='Koreksi' onclick="
                    + fungsi + " />");
            out.print("</div>");
        } else if (tipe.equals("url")) {
            String url = request.getParameter("textInput");
            berita = CobaJSoup.getTextFromURL(url);

            Klasifikasi cls = new Klasifikasi();
            cls.inputString(berita);
            cls.makeInstance();
            cls.loadModel(jspPath + "data/training_data.model");
            out.println("<p>" + berita + "</p>");
            out.println("<p>" + cls.getKategori() + "</p>");
        } else if (tipe.equals("csv")) {
            ContainerData.setState("csv");
            Klasifikasi cls = new Klasifikasi();
            cls.loadModel(jspPath + "data\\training_data.model");
            ArrayList<String> listText = cls.getDaftarTextFromCSV(jspPath + "data\\artikel.csv");
            ArrayList<String> s = new ArrayList<String>();
            ContainerData.setListBerita(s);
            for (int i = 0; i < listText.size(); i++) {
                cls.setNull();
                cls.inputString(listText.get(i));
                cls.makeInstance();
                s.add(cls.getKategori());
            }
            out.println("<table>");
            out.println("<tbody>");
            CSVWriter writer = new CSVWriter(new FileWriter("D:\\hasilFix.csv"), ',');
            for (int i = 0; i < s.size(); i++) {
                out.println("<td>" + (i + 1) + " </td>");
                out.println("<td>" + s.get(i) + "<td>");
                out.println("<div id='berita" + i + "' />");
                String petik = "\"";
                String fungsi = petik + "koreksi('berita" + i + "');" + petik;
                out.print("<input type='button' value='Koreksi' onclick="
                        + fungsi + " />");
                out.print("</div>");
                out.print("</td><tr>");
                
                String temp = i + "#" + listText.get(i) + "#" + s.get(i);
                String[] oneRow = temp.split("#");
                writer.writeNext(oneRow);
                
%>

<%    }
            writer.close();
            StringManipulation asd = new StringManipulation();
            
            out.println("</tbody>");
            out.println("</table>");
        }
    } finally {
//                out.close();
    }
%>
