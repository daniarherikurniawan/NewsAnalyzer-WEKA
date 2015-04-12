/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import weka.filter.Klasifikasi;

/**
 *
 * @author daniar heri
 */
@WebServlet("/TextServlet")
public class TextServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String berita;
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            berita = request.getParameter("textInput");
            // buat inputan yang berupa string          
            Klasifikasi cls = new Klasifikasi();
            cls.inputString(berita);
            cls.makeInstance();
            String result = cls.loadModel("training_data.model");
//            out.println("sestuu");
           // cls.classify();
            //System.out.println("kura");
            //System.out.println("Kategorinya : "+ cls.getKategori());
            
            out.println("<script type=\"text/javascript\">");  
            out.println("alert('"+result+"');");//'"+berita+"')");//
            out.println("</script>");
            response.sendRedirect("training_data.model");
        } finally {
            out.close();
        }
    }
}
