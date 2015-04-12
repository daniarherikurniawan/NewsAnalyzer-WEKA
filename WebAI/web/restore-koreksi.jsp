<%-- 
    Document   : muncul-koreksi
    Created on : Nov 30, 2014, 8:40:59 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
    <%
        String petik = "\"";
        String fungsi = petik + "koreksi('" + request.getParameter("id") + "');" + petik;
        out.println("<input type='button' value='Koreksi' onclick="
                + fungsi + " />");
    %>
