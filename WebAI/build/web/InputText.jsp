<%-- 
    Document   : InputText
    Created on : Nov 30, 2014, 2:46:32 PM
    Author     : daniar heri
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="weka.filter.GenerateHasil"%>
<%@page import="parser.CobaJSoup"%>
<%@page import="weka.filter.Klasifikasi"%>
<%@page import="java.io.PrintWriter"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="style.css">
        <title>Hasil</title>
    </head>
    <body>
        <script type="text/javascript">
            function koreksi(idBerita) {
                var beritaKoreksi = document.getElementById(idBerita);
                var xRequest1;

                if (window.XMLHttpRequest) {
                    xRequest1 = new XMLHttpRequest();
                } else {
                    xRequest1 = new ActiveXObject("Microsoft.XMLHTTP");
                }

                xRequest1.open("get", "muncul-koreksi.jsp?id=" + idBerita, "true");
                xRequest1.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                xRequest1.onreadystatechange = function() {
                    if ((xRequest1.readyState == 4) && (xRequest1.status == 200)) {
                        beritaKoreksi.innerHTML
                                = xRequest1.responseText;
                    }
                }
                xRequest1.send();
            }

            function restoreKoreksi(idBerita) {
                var beritaKoreksi = document.getElementById(idBerita);
                var xRequest1;

                if (window.XMLHttpRequest) {
                    xRequest1 = new XMLHttpRequest();
                } else {
                    xRequest1 = new ActiveXObject("Microsoft.XMLHTTP");
                }

                xRequest1.open("get", "restore-koreksi.jsp?id=" + idBerita, "true");
                xRequest1.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                xRequest1.onreadystatechange = function() {
                    if ((xRequest1.readyState == 4) && (xRequest1.status == 200)) {
                        beritaKoreksi.innerHTML
                                = xRequest1.responseText;
                    }
                }
                xRequest1.send();
            }
        </script>
        <div id="bg">
            <div id="outer">
                <%@include file="header.jsp" %>
                <div id="main">
                    <h1>Hasil Analisis</h1>
                    <%@include file="proses-kategori.jsp" %>
                    <div>
                        <form action="index.jsp">
                            <input type="submit" value="Back" />
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
