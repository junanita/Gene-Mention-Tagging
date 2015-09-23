package edu.cmu.hw2;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;

import Type.gene;
import Type.sentence;

import com.aliasi.chunk.Chunk;
import com.aliasi.chunk.Chunker;
import com.aliasi.chunk.Chunking;
import com.aliasi.chunk.ConfidenceChunker;
import com.aliasi.util.AbstractExternalizable;


/**
 * This annotator aims at discovering gene name in each sentence using LingPipe.
 * @author ningna
 *
 */
public class LingPipeAnnotator extends JCasAnnotator_ImplBase {

  public static final String GenesFilename = "Library";
  ConfidenceChunker chunker = null;

  /**
   * This method aims at reading model for LingPipe
   */
  public void initialize(UimaContext context){
    String filePath = (String) context.getConfigParameterValue(GenesFilename);
    try {
      chunker = (ConfidenceChunker) AbstractExternalizable.readResourceObject(filePath);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }
  /**
   * This annotator searches for Gene Mention in file using LingPipe.
   * 
   * @param jcas
   *          jcas containing sentences text and previously discovered annotations, 
   *          and to which new annotations are to be written.
   * 
   * @see JCasAnnotator_ImplBase#process(JCas)
   */
  public void process(JCas jcas) throws AnalysisEngineProcessException {
    // TODO Auto-generated method stub
          
    Iterator<org.apache.uima.jcas.tcas.Annotation> it = jcas.getAnnotationIndex(sentence.type).iterator();    
    while(it.hasNext()){
      //get sentence annotation from CAS
      sentence annotation = (sentence) it.next();
      String SentenceID = annotation.getID();
      String SentenceText = annotation.getText();
       
      //delete extra spaces in GeneStart and GeneEnd
      Iterator<Chunk> index = chunker.nBestChunks(SentenceText.toCharArray(),0,SentenceText.length(),5);
      while(index.hasNext()){
        Chunk c = index.next();
        int space = 0;
        int InnerSpace = 0;

        for(int i=0; i<c.start(); i++)
        {
          if(SentenceText.charAt(i) == ' ') space++;
        }
        for(int i=c.start(); i<c.end(); i++)
        {
          if(SentenceText.charAt(i) == ' ') InnerSpace++;
        }
        
        //create gene annotation CAS
        gene gene = new gene(jcas);
        gene.setID(SentenceID);
        gene.setGeneStart((Integer)c.start() - space);
        gene.setGeneEnd((Integer)c.end() - space - InnerSpace -1);
        gene.setText(SentenceText.substring((Integer)c.start(),(Integer)c.end()));
        gene.setConfidence(Math.pow(2.0, c.score()));
        gene.addToIndexes();
        }
    }
  }
}

