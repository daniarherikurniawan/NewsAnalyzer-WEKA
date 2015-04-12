<%-- 
    Document   : input
    Created on : Nov 28, 2014, 1:59:47 AM
    Author     : user
--%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="javax.servlet.*" %>
<%@page import="javax.servlet.http.*" %>
<%@page import="java.util.*,java.sql.*,java.io.*" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <body>
        <% String input = request.getParameter("drop");
            if (input.equals("url") ) { %>
            <input id="input" type="text" name="textInput" value="" size="80"/>    
            <input type="submit" value=" Analyze " name="urlButton" onclick="tes('input');" />
        <%
        } else if (input.equals("text")) { %>
            <textarea id="input" name="textInput" rows="25" cols="157" ></textarea>
            <input type="submit" value=" Analyze " name="textButton" onclick=" location.href = '/TextServlet';" />
        <%
         } else if (input.equals("csv")) { %>
            <input id="input" type="file" accept=".csv" name="fileChooser" value="" />
            <input type="submit" value=" Analyze " name="csvButton" onclick="tes('input');" />
         <% 
         } else if (input.equals("crawl")) { %>
            <input id="input" type="text" name="textInput" value="" size="80"/>
            <input type="submit" value=" Analyze " name="crawlButton" onclick="tes('input');" />
            <%
         }
            %>
    </body>
</html>