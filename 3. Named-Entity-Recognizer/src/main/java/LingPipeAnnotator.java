import org.apache.uima.analysis_component.CasAnnotator_ImplBase;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.aliasi.chunk.Chunk;
import com.aliasi.chunk.Chunker;
import com.aliasi.chunk.Chunking;
import com.aliasi.chunk.NBestChunker;
import com.aliasi.util.AbstractExternalizable;

import Type.sentence;
import Type.gene;

/**
 * This annotator is aimed at discovering gene name in each sentence.
 * The gene are detected using LingPipe.
 * @author ningna
 *
 */
public class LingPipeAnnotator extends JCasAnnotator_ImplBase {

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
  
    File modelFile = new File("./src/main/resources/LingPipeLibrary/ne-en-bio-genetag.HmmChunker");
    Chunker chunker = null;
    Chunking chunking = null;
    try {
      chunker = (Chunker) AbstractExternalizable.readObject(modelFile);
      } catch (IOException e1) {
      e1.printStackTrace();
      } catch (ClassNotFoundException e1) {
      e1.printStackTrace();
      }
    
    Iterator<org.apache.uima.jcas.tcas.Annotation> it = jcas.getAnnotationIndex(sentence.type).iterator();    
    while(it.hasNext()){
      //get sentence annotation from CAS
      sentence annotation = (sentence) it.next();
      String SentenceID = annotation.getID();
      String SentenceText = annotation.getText();
      
      //using LingPipe to process text in each sentence
      chunking = chunker.chunk(SentenceText);
     
      //delete extra spaces in GeneStart and GeneEnd
      Iterator<Chunk> index = chunking.chunkSet().iterator();
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
        gene.addToIndexes();
      }
     }
  }
}
