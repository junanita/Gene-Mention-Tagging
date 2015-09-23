import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.collection.CasConsumer_ImplBase;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceProcessException;

/**
 * This CAS Consumer receives ID and text information about gene and writes it to a file.
 * 
 * @author ningna
 */
public class casConsumer extends CasConsumer_ImplBase {

 // public static final String PARAM_OUTPUTDIR = "OutputDocument";
  
  public void initialize() throws ResourceInitializationException {
 
/*  File f = new File(PARAM_OUTPUTDIR);
    FileWriter fw;
    try {
      fw = new FileWriter(f);
      fw.write("");
      fw.close();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
 */   
    
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
    // TODO Auto-generated method stub}
    JCas jcas;
    try {
      jcas = aCAS.getJCas();
    } catch (CASException e) {
      throw new ResourceProcessException(e);
    }   
    
    // retrieve the filename of the input file from the CAS    
    FSIterator<org.apache.uima.jcas.tcas.Annotation> it = jcas.getAnnotationIndex(Type.gene.type).iterator();
    String geneID = "";
    String geneText = "";
    int start=0;
    int end=0;
    FileWriter fw =null;
    BufferedWriter bw =null;
    try {
      
      File file = new File("./src/main/resources/data/hw1-ningnaw.out.txt");
      
      // if file doesn't exists, then create it
      if (!file.exists()) {
        file.createNewFile();
      }
           
      fw = new FileWriter(file.getAbsoluteFile());
      bw = new BufferedWriter(fw);
      while(it.hasNext()){
        
        Type.gene annotation = (Type.gene) it.next();
        geneID = annotation.getID();
        geneText = annotation.getText();
        if(geneText.length() == 1 || geneText.length() == 2){
          continue;
        }
        start = annotation.getGeneStart();
        end = annotation.getGeneEnd();
        bw.write(geneID + "|" + start + " " + end + "|" + geneText);
        bw.newLine();
                       
      }
        bw.close();
        fw.close();
     
    } catch (IOException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace(); 
      };
    }
}