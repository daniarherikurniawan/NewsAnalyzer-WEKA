<%-- 
    Document   : BuildDataTraining
    Created on : Dec 1, 2014, 3:42:09 PM
    Author     : Ichwan Haryo Sembodo
--%>

<%@page import="weka.filters.unsupervised.attribute.StringToWordVector"%>
<%@page import="weka.classifiers.lazy.IBk"%>
<%@page import="weka.classifiers.bayes.NaiveBayes"%>
<%@page import="weka.classifiers.bayes.NaiveBayesMultinomialUpdateable"%>
<%@page import="weka.classifiers.meta.FilteredClassifier"%>
<%@page import="weka.filter.FilterDataset"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Build Data Training</title>
    </head>
    <body>
        <%
            FilteredClassifier tes = new FilteredClassifier();
            FilteredClassifier FC = new FilteredClassifier();
            try{
                tes.setClassifier(new NaiveBayesMultinomialUpdateable());
                System.out.println("berhasil set classifier");
            } catch (Exception e) {
                System.out.println("set classifier gagal");
            }
            String jspPath = session.getServletContext().getRealPath("/");
            FilterDataset filter = new FilterDataset();
            filter.loadDataset(jspPath +"data\\training_data.arff");
            filter.setClassifier(tes);
            //evaluate() harus dijalankan sebelum build classifier
            filter.evaluate();
            FC.setFilter(new StringToWordVector());
            FC.setClassifier(new NaiveBayesMultinomialUpdateable());
            
            filter.buildClassifier();
            filter.saveModel(jspPath +"data\\training_data.model");
            
            
            String site = new String("index.jsp");
            response.setStatus(response.SC_MOVED_TEMPORARILY);
            response.setHeader("Location", site);
        %>
    </body>
</html>
