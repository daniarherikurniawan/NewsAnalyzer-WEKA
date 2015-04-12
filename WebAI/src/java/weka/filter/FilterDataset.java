/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package weka.filter;

import java.io.*;
import java.util.Random;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.bayes.NaiveBayesMultinomialUpdateable;
import weka.classifiers.meta.FilteredClassifier;
import weka.core.Instances;
import weka.core.converters.ArffLoader.ArffReader;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.StringToWordVector;

/**
 *
 * @author Ichwan Haryo Sembodo
 */
public class FilterDataset {
    private Instances dataTrain;
    private FilteredClassifier classifier;
    
    public void loadDataset(String DATA_SOURCE) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(DATA_SOURCE));
            ArffReader arff = new ArffReader(reader);
            dataTrain = arff.getData();
            System.out.println("===== Loaded dataset: " + DATA_SOURCE + " =====");
            reader.close();
        }
        catch (IOException e) {
            System.out.println("Problem found when reading: " + DATA_SOURCE);
        }
    }
    
    public void evaluate() {
        try {
            dataTrain.setClassIndex(0);
            StringToWordVector filter;
            filter = new StringToWordVector();
            filter.setAttributeIndices("last");
            //setClassifier(new FilteredClassifier());
            classifier.setClassifier(new NaiveBayesMultinomialUpdateable());
            classifier.setFilter(filter);
            Evaluation eval = new Evaluation(dataTrain);
            eval.crossValidateModel(classifier, dataTrain, 10, new Random(1));
            System.out.println(eval.toSummaryString());
            System.out.println(eval.toClassDetailsString());
            System.out.println("===== Evaluating on filtered (training) dataset done =====");
        }
        catch (Exception e) {
            System.out.println("Problem found when evaluating");
            //e.printStackTrace();
        }
    }
    
    public void buildClassifier() {
        try {
            dataTrain.setClassIndex(0);
            StringToWordVector filter;
            filter = new StringToWordVector();
            filter.setAttributeIndices("last");
            //filter.setWordsToKeep(4000);
            //setClassifier(new FilteredClassifier());
            dataTrain.setClass(dataTrain.attribute("class"));
            classifier.setClassifier(new NaiveBayesMultinomialUpdateable());
            classifier.setFilter(filter);
            classifier.buildClassifier(dataTrain);
            // Uncomment to see the classifier
            // System.out.println(classifier);
            System.out.println("===== classifier builded =====");
        }
        catch (Exception e) {
            System.out.println("Problem found when building");
        }
    }
    
    public void saveModel(String DATA_SOURCE) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(DATA_SOURCE));
            out.writeObject(classifier);
            out.close();
            System.out.println("===== Saved model: " + DATA_SOURCE + " =====");
        }
        catch (IOException e) {
            System.out.println("Problem found when writing: " + DATA_SOURCE);
        }
    }

    /**
     * @param classifier the classifier to set
     */
    public void setClassifier(FilteredClassifier classifier) {
        this.classifier = classifier;
    }
    
    
}
