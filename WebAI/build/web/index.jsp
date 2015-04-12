<%-- 
    Document   : index
    Created on : Nov 27, 2014, 11:34:39 AM
    Author     : user
--%>

<%@page import="weka.filter.ContainerData"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="style.css">
        <title>News Analyzer</title>
    </head>

    <script type="text/javascript">
        function changeInput() {
            var input = document.getElementById("drop").value;
            var xRequest1;

            if (window.XMLHttpRequest) {
                xRequest1 = new XMLHttpRequest();
            } else {
                xRequest1 = new ActiveXObject("Microsoft.XMLHTTP");
            }

            xRequest1.open("get", "input.jsp?drop=" + input, "true");
            xRequest1.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            xRequest1.onreadystatechange = function() {
                if ((xRequest1.readyState == 4) && (xRequest1.status == 200)) {
                    document.getElementById("dynamic").innerHTML
                            = xRequest1.responseText;
                }
            }
            xRequest1.send();
        }
        function tes(input) {
            var s = document.getElementById("drop").value;
            if (s == "text" || s == "url" || s == "crawl") {
                alert(document.getElementById(input).value);

            } else if (s == "csv") {
                var elm = document.getElementById(input);
                if (elm.value.split('.').pop() == "csv") {
                    alert("Format file benar");
                } else {
                    alert("Format file harus .csv");
                    elm.focus();
                }
            }

            return;
        }
    </script>
    
    <%
        ContainerData.setBerita(null);
        ContainerData.setListBerita(null);
        ContainerData.setState(null);
    %>
    
    <body>
        <div id="bg">
            <div id="outer">
                <%@include file="header.jsp" %>
                <div id="main">
                    <h3>Input Berita</h3>
                    <form action="InputText.jsp" method="POST">
                        <!--<form action="style.css" method="POST">-->
                        <span>
                            <select id="drop" name="drop" onchange="changeInput();">
                                <option value="text" selected>Text Input</option>
                                <option value="url">Url Input</option>
                                <option value="crawl">Url Input Crawling</option>
                                <option value="csv">Open CSV file</option>
                            </select>
                        </span>
                        <span>
                            <div id="dynamic">
                                <textarea id="input" name="textInput" rows="25" cols="157"></textarea>
                                <input type="submit" value="Analyze" name = "textButton" />
                            </div>

                        </span>
                    </form>
                    <form action="BuildDataTraining.jsp">
                        <input type="submit" value="Build Data Training" name = "textButton" />
                    </form>
                </div>
            </div>
        </div>






    </body>
</html>
