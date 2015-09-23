package edu.cmu.hw2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.collection.CasConsumer_ImplBase;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceProcessException;

/**
 * This CAS Consumer receives ID and text information of gene and writes it to a new file.
 * 
 * @author ningna
 */

public class CasConsumer extends CasConsumer_ImplBase {

  private File file;
 
/**
 * This method create a new file to store output
 */
  public void initialize() throws ResourceInitializationException {
    
    file = new File(((String) getConfigParameterValue("OutputDocument")).trim());

    // if file doesn't exists, then create it
    if (!file.exists()) {
      try {
        file.createNewFile();
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    
 } 
  
  /**
   * Processes the CAS Container which was populated by the Analysis Engine. <br>
   * In this case, the gene information is extracted and written into the output file .
   * 
   * @param aCAS
   * @see org.apache.uima.collection.base_cpm.CasObjectProcessor#processCas(org.apache.uima.cas.CAS)
   */
  
  @Override
  public void processCas(CAS aCAS) throws ResourceProcessException {
    // TODO Auto-generated method stub
    
    JCas jcas;
    try {
      jcas = aCAS.getJCas();
    } catch (CASException e) {
      throw new ResourceProcessException(e);
    }   
     
     
    // retrieve the filename of the input file from the CAS    
    String geneID = "";
    String geneText = "";
    int start=0;
    int end=0;
    FileWriter fw =null;
    BufferedWriter bw =null;
    try {          
      
/* initalize Evaluator 
 * Evaluator evaluator = new Evaluator();
 * String pathname = "./src/main/resources/data/test.out";   // set path for correct answer in Evaluator 
 * String pathname = "./src/main/resources/data/sample.out"; // set file's path of answers
 * evaluator.setAnswerText(pathname);
 * int total_ans = 0;
*/     
      //store geneText and detect repeated geneText
      HashMap<String, Integer> map = new HashMap<String, Integer>();
     
      //write gene metion into output file
      FSIterator<org.apache.uima.jcas.tcas.Annotation> it = jcas.getAnnotationIndex(Type.gene.type).iterator(); 
      fw = new FileWriter(file);
      bw = new BufferedWriter(fw);
      
      while(it.hasNext()){ 
        Type.gene annotation = (Type.gene) it.next();
        geneID = annotation.getID();
        geneText = annotation.getText();
        
        if(geneText.length() == 1 || geneText.length() == 2){
          continue;
        }
        
        //ignore repeated gene between LingPipe and Abner
        if(map.containsKey(geneID + geneText) 
                || annotation.getConfidence() <= 0.8){
          continue;          
        }else{
          map.put(geneID+geneText, 1);         
          start = annotation.getGeneStart();
          end = annotation.getGeneEnd();
          bw.write(geneID + "|" + start + " " + end + "|" + geneText);
          bw.newLine(); 
/*        
 * evaluator.judgeAnswer(geneID + geneText);
 * total_ans++;
*/
        }
                       
      }
      
/* recall Evaluator 
 *      evaluator.setAnswernum(total_ans);
 *      String report = evaluator.getReport();
 *      System.out.println(report);      
 *      evaluator.setAnswernum(total_ans);
*/      
      bw.close();
      fw.close();
     
    } catch (IOException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace(); 
      };
    }

}
