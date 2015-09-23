package edu.cmu.hw2;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;

import com.aliasi.chunk.Chunk;

import Type.gene;
import Type.sentence;
import abner.*;

import java.io.*;
import java.lang.*;
import java.util.*;
import java.util.regex.Pattern;

/**
 * This annotator aims at discovering gene name in each sentence using Abner.
 * @author ningna
 */
public class AbnerAnnotator extends JCasAnnotator_ImplBase {

  @Override
  public void process(JCas aJCas) throws AnalysisEngineProcessException {
    // TODO Auto-generated method stub
    
    Tagger t = new Tagger();   
    
    Iterator<org.apache.uima.jcas.tcas.Annotation> it = aJCas.getAnnotationIndex(sentence.type).iterator();    
    while(it.hasNext()){
      //get sentence annotation from CAS
      sentence annotation = (sentence) it.next();
      String SentenceID = annotation.getID();
      String SentenceText = annotation.getText();
      
      //using Abner to process text in each sentence     
        String[][] ents = t.getEntities(SentenceText);
        for (int i=0; i<ents[0].length; i++) {
          
          //set confidence for AbnerAnnotator
          float confidence = 0.82f;
          if(Pattern.matches("[\\w-\\s]+", ents[0][i])== false){
            confidence -= 0.21f;
          }
          
          int start = SentenceText.indexOf(ents[0][i]);
          if(start != -1){
            int end = start + ents[0][i].length();

            //delete extra spaces in GeneStart and GeneEnd
            int space = 0;
            int InnerSpace = 0;

            for(int j=0; j< start; j++)
            {
              if(SentenceText.charAt(j) == ' ') space++;
            }
            for(int i1 = start; i1 < end; i1++)
            {
              if(SentenceText.charAt(i1) == ' ') InnerSpace++;
            }
           
            //create gene annotation CAS
            gene gene = new gene(aJCas);
            gene.setID(SentenceID);
            gene.setGeneStart(start-space);
            gene.setGeneEnd(end - space - InnerSpace -1);
            gene.setText(ents[0][i]);
            gene.setConfidence(confidence);
            gene.addToIndexes();
            
            }
          }
        }
     }
  }

