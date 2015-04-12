<%-- 
    Document   : muncul-koreksi
    Created on : Nov 30, 2014, 8:40:59 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<form action="rubahDataTraining.jsp" method="POST">
    <%
        out.println("<select id=\"select" + request.getParameter("id") + "\" name=\"select"+ request.getParameter("id") + "\">");
    %>
    <option>Pendidikan</option>
    <option>Kriminal</option>
    <option>Hukum dan Kriminal</option>
    <option>Olahraga</option>
    <option>Teknologi dan Sains</option>
    <option>Politik</option>
    <option>Sosial Budaya</option>
    <option>Pertanian</option>
    <option>Hiburan</option>
    <option>Bisnis dan Ekonomi</option>
    <option>Ekonomi</option>
    <option>Kesehatan</option>
    <option>Internasional</option>
    <option>Bencana dan Kecelakaan</option>
    <option>Investigasi dan Persidangan</option>
    <option></option>
</select>   
    <input type="hidden" value="<%= request.getParameter("id")%>" name="id" id="id"/>
    <input type="submit" value="OK" />
<%
    String petik = "\"";
    String fungsi = petik + "restoreKoreksi('" + request.getParameter("id") + "');"
            + petik;
    out.println("<input  type = 'button' value = 'Cancel' "
            + "onclick=" + fungsi + " />");
%>
</form>

