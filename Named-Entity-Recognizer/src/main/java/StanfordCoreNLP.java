import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.uima.analysis_component.CasAnnotator_ImplBase;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;

/**
 * 
 * This annotator is aimed at discovering gene name in each sentence.
 * The gene are detected using StanfordCoreNLP. 
 * 
 * @author ningna
 *
 */
public class StanfordCoreNLP extends JCasAnnotator_ImplBase {

  /**
   * This annotator searches for Gene Mention using StanfordCoreNLP.
   * 
   * @param ajCAS
   *          ajCAS containing sentences text and previously discovered annotations, and to which new
   *          annotations are to be written.
   * 
   * @see JCasAnnotator_ImplBase#process(JCas)
   */
  @Override
  public void process(JCas ajCAS) throws AnalysisEngineProcessException {
    // TODO Auto-generated method stub
    
    Iterator<org.apache.uima.jcas.tcas.Annotation> it = ajCAS.getAnnotationIndex(Type.sentence.type).iterator();
    while(it.hasNext()){
      //get sentence annotation from CAS
      Type.sentence annotation = (Type.sentence) it.next();
      String SentenceID = annotation.getID();
      String SentenceText = annotation.getText();
//    System.out.println(SentenceText);
      
      //Stanford NLP processor
      PosTagNamedEntityRecognizer p;
      try {
       p = new PosTagNamedEntityRecognizer();
       Map<Integer,Integer> map = new HashMap<Integer,Integer>();
       map = p.getGeneSpans(SentenceText);
       Iterator<java.util.Map.Entry<Integer, Integer>> index=map.entrySet().iterator();   
       while(index.hasNext()){
         Map.Entry entry = (Map.Entry) index.next();
         Object start = entry.getKey();
         Object end = entry.getValue();
         
         //create gene annotation CAS
         Type.gene gene = new Type.gene(ajCAS);
         gene.setID(SentenceID);
         gene.setGeneStart((Integer)start);
         gene.setGeneEnd((Integer)end);
         gene.setText(SentenceText.substring((Integer)start,(Integer)end));
         gene.addToIndexes();
       }
      }catch (ResourceInitializationException e) {
       // TODO Auto-generated catch block
       e.printStackTrace();
       }
      }
  }

}
