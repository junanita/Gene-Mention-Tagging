import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.naming.spi.DirStateFactory.Result;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.collection.CollectionException;
import org.apache.uima.collection.CollectionReader_ImplBase;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.FileUtils;
import org.apache.uima.util.Progress;
import org.apache.xerces.impl.xs.identity.Selector.Matcher;

/**
 * This Collection Reader is responsible for obtaining sentences from file
 * and returning each sentence as a CAS.
 * 
 * It can be configured with the following parameters:
 * <ul>
 * <li><code>InputDocument</code> - path to file containing sentences</li>
 * <li><code>cfile</code> - ArrayList that save all sentences</li>
 * <li><code>isfinish</code> - Decide this file has been opened or not</li> 
 * </ul>
 * 
 * @author ningna
 * 
 * 
 */
public class CollectionReader extends CollectionReader_ImplBase {
 
  /**
   * Name of configuration parameter that 
   * must be set to the path of a directory containing input files.
   */
  public static final String PARAM_INPUTDIR = "InputDocument";
  
  private ArrayList<String> cfile;
  int isfinish;

  
  /**
   * @see org.apache.uima.collection.CollectionReader_ImplBase#initialize()
   */
  public void initialize() throws ResourceInitializationException {
    // this file has not been opened
    isfinish = 1;
    File directory = new File(((String) getConfigParameterValue(PARAM_INPUTDIR)).trim());
    try {
      cfile=ReadFile(directory);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

   }
  
  /**
   * This method saves all sentences in one ArrayList and return this ArrayList
   * 
   * @param FileName 
   * @return result 
   * @throws IOException
   */
  private ArrayList<String> ReadFile(File FileName) throws IOException {
  
    //Using BufferReader
    BufferedReader reader = null;
    ArrayList<String> result;     
    result= new ArrayList<String>();
      
    reader = new BufferedReader(new FileReader(FileName));
    String sCurrentline = reader.readLine();
      
    while(sCurrentline != null){
      result.add(sCurrentline);
      sCurrentline = reader.readLine();
    }
      
    reader.close();
    return result;
       
} 
  
  @Override
  /**
   * This method split sentences into 2 parts and return each sentence as a CAS
   * @see org.apache.uima.collection.CollectionReader#getNext(org.apache.uima.cas.CAS)
   */
  public void getNext(CAS aCas) throws IOException, CollectionException {
    // TODO Auto-generated method stub 
    JCas jcas;
    try {
      jcas = aCas.getJCas();
    } catch (CASException e) {
      throw new CollectionException(e);
    }
    
/*  
 * Solution 1: check "\n" to separate sentences
 *   
    open input stream to file
    String file = cfile.get(CurrentIndex++);//sentences is a ArrayList<String>
    put document in CAS
    jcas.setDocumentText(file);
    
     int begin = 0;
     int end = 0;
     Pattern eol = Pattern.compile("\n");
     java.util.regex.Matcher matcher = eol.matcher(file);
     while(matcher.find()){
         end = matcher.start();
         String line = file.substring(begin, end);
         String[] fields = line.split(" ", 2); 
      }
      begin = matcher.end();       
 */
   
/*
 * Solution 2: directly calling ArrayList and process sentences one by one
 */
    for(int j=0; j<cfile.size(); j++){
      String line = cfile.get(j);
      //Dividing each sentence into two parts: 1.ID   2.text
      String[] fields = line.split(" ", 2);  
      
      //Create sentence annotation CAS
      Type.sentence sentence = new Type.sentence(jcas);
      sentence.setID(fields[0]);
      sentence.setText(fields[1]);
      sentence.addToIndexes();
    }
    //This file has been opened
    isfinish = 0;

  }
  
 
  @Override
  public void close() throws IOException {
    // TODO Auto-generated method stub

  }

  @Override
  public Progress[] getProgress() {
    // TODO Auto-generated method stub
    return null;
  }

  
  /**
   * The terminal condition of stopping reading file
   * @see org.apache.uima.collection.CollectionReader#hasNext()
   */
  @Override
  public boolean hasNext() throws IOException, CollectionException {
    // TODO Auto-generated method stub

    if(isfinish == 0) return false;
    else return true;
  }

}
