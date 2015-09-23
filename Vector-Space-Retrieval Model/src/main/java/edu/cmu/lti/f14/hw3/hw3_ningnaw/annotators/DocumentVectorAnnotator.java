package edu.cmu.lti.f14.hw3.hw3_ningnaw.annotators;

import java.util.*;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSList;
import org.apache.uima.jcas.cas.IntegerArray;
import org.apache.uima.jcas.cas.StringArray;
import org.apache.uima.jcas.tcas.Annotation;

import edu.cmu.lti.f14.hw3.hw3_ningnaw.typesystems.Document;
import edu.cmu.lti.f14.hw3.hw3_ningnaw.typesystems.Token;
import edu.cmu.lti.f14.hw3.hw3_ningnaw.utils.StanfordLemmatizer;
import edu.cmu.lti.f14.hw3.hw3_ningnaw.utils.Utils;

public class DocumentVectorAnnotator extends JCasAnnotator_ImplBase {

	@Override
	public void process(JCas jcas) throws AnalysisEngineProcessException {

		FSIterator<Annotation> iter = jcas.getAnnotationIndex().iterator();
		if (iter.isValid()) {
			iter.moveToNext();
			Document doc = (Document) iter.get();
			createTermFreqVector(jcas, doc);
		}

	}

	/**
   * A basic white-space tokenizer, it deliberately does not split on punctuation!
   *
	 * @param doc input text
	 * @return    a list of tokens.
	 */

	List<String> tokenize0(String doc) {
	  List<String> res = new ArrayList<String>();
	  
	  for (String s: doc.split("\\s+"))
	    res.add(s);
	  return res;
	  
	   
	}
	
	/**
	 * A new tokenizer that take both white-space and punctuation into consideration
	 * @param doc
	 * @return
	 */
	 List<String> tokenize1(String doc) {
	    List<String> res = new ArrayList<String>();
	    
	    for (String s: doc.split("[\\p{Punct} || \\s]+"))
	      res.add(s);
	    return res;
	}
	    
	  
	

	/**
	 * This function constructs a vector of tokens and updates the tokenList in CAS
	 * @param jcas
	 * @param doc
	 */
	private void createTermFreqVector(JCas jcas, Document doc) {

		String docText = doc.getText();
		
		//TO DO: construct a vector of tokens and update the tokenList in CAS
    //TO DO: use tokenize0 from above 
		
		List<String> tokens = new ArrayList<String>();
    Map<String, Integer> tokenFre = new HashMap<String, Integer>();
       
//		tokens = tokenize1(StanfordLemmatizer.stemText(docText));
    tokens = tokenize0(docText);
		
		//calculate term frequency
		for (int i = 0; i < tokens.size(); i++) {
		  if(tokenFre.containsKey(tokens.get(i))) {
		    int value = tokenFre.get(tokens.get(i));
		    value++;
		    tokenFre.put(tokens.get(i), value);
		  }
		  else {
		    tokenFre.put(tokens.get(i), 1);
		  }
		}
		
				
		//add term in tokens to tokenList
		List<Token> tokenList = new ArrayList<Token>();
		Set<String> tokenFreIt = tokenFre.keySet(); // traverse map
		for (String s : tokenFreIt) {
		  Token t = new Token(jcas);
		  t.setText(s);
      t.setFrequency(tokenFre.get(s));
      tokenList.add(t);
		}
		
		//add tokenList to cas
		FSList fslist = Utils.fromCollectionToFSList(jcas, tokenList);
		doc.setTokenList(fslist);
	}

}
