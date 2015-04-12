<%-- 
    Document   : index
    Created on : Nov 27, 2014, 11:34:39 AM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>News Analyzer</title>
    </head>

    <script type="text/javascript">
        function changeInput() {
            var input = document.getElementsByName("drop").value;
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
                    document.getElementById("form").innerHTML
                            = xRequest1.responseText;
                }
            }
            xRequest1.send();
        }
        function tes(input) {
            alert(input);
            return;
        }
    </script>

    <body>
        <h1> NEWS ANALYZER </h1>
        <h2>Input Berita</h2>

        <% java.util.Date date = new java.util.Date();%>
        <h3>Sekarang <%= date%> </h3>
        <form>
            <table border="0">
                <tbody>
                    <tr>
                        <td><select name="drop" onchange="changeInput(this.value);">
                                <option value="text" selected>Text Input</option>
                                <option value="url">Url Input</option>
                                <option value="crawl">Url Input Crawling</option>
                            </select></td>
                            <td id="input-type"><textarea name="textInput" rows="25" cols="20"></textarea></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input type="submit" value="Analyze" onsubmit="changeInput();"  /></td>
                    </tr>
                </tbody>
            </table>
            <form id="form">
                <h2>Ini form tes </h2>
            </form>
        </form>


    </body>
</html>
