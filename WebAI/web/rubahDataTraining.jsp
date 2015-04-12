<%-- 
    Document   : rubahDataTraining
    Created on : Dec 1, 2014, 2:32:41 PM
    Author     : Ichwan Haryo Sembodo
--%>

<%@page import="weka.filter.StringManipulation"%>
<%@page import="weka.filter.ContainerData"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Redirect</title>
    </head>
    <body>
        <%
            if(ContainerData.getState().equals("csv")){
                String berita_id = request.getParameter("id");
                String[] split = berita_id.split("berita");
                String kategori = request.getParameter("select" + request.getParameter("id"));
                berita_id = split[1];
                int id = Integer.parseInt(berita_id);
                String jspPath = session.getServletContext().getRealPath("/");
                StringManipulation str = new StringManipulation();
                str.appendOneData(jspPath + "data\\training_data.arff", kategori, ContainerData.getListBerita().get(id));
            }
            else if(ContainerData.getState().equals("text")) {
                String jspPath = session.getServletContext().getRealPath("/");
                String kategori = request.getParameter("select" + request.getParameter("id"));
                StringManipulation str = new StringManipulation();
                str.appendOneData(jspPath + "data\\training_data.arff", kategori, ContainerData.getBerita());
            }
            // New location to be redirected
            String site = new String("index.jsp");
            response.setStatus(response.SC_MOVED_TEMPORARILY);
            response.setHeader("Location", site);
        %>
    </body>
</html>
