package edu.cmu.lti.f14.hw3.hw3_ningnaw.casconsumers;

import java.awt.List;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.collection.CasConsumer_ImplBase;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSList;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceProcessException;
import org.apache.uima.util.ProcessTrace;

import edu.cmu.lti.f14.hw3.hw3_ningnaw.typesystems.Answer;
import edu.cmu.lti.f14.hw3.hw3_ningnaw.typesystems.Document;
import edu.cmu.lti.f14.hw3.hw3_ningnaw.typesystems.Token;
import edu.cmu.lti.f14.hw3.hw3_ningnaw.utils.Utils;


public class RetrievalEvaluator extends CasConsumer_ImplBase {

	/** query id number **/
	public ArrayList<Integer> qIdList;

	/** query and text relevant values **/
	public ArrayList<Integer> relList;

	/** query id and term with frequency **/
  public HashMap<Integer, HashMap<String, Integer>> queryMap;
  
  /** golden answer id + No. + term + frequency **/
  public HashMap<Integer, HashMap<String, Integer>> goldenAnsMap;

  /** other answers id + No. + term + frequency **/
  public HashMap<Integer, ArrayList<HashMap<String,Integer>>> ansMap;
  
  /** query id + cos similarity of each answer **/
  public HashMap<Integer, ArrayList<Double>> cosSimilarity;
  
  /** rank of golden answer of each quary (compare cos similarity with other answers) **/
  public ArrayList<Integer> rankList;
  
  /** text of golden answer used for output **/
  public ArrayList<String> goldText;
  
  /** write result into report.txt **/
  private File file;

  
	public void initialize() throws ResourceInitializationException {

		qIdList = new ArrayList<Integer>();

		relList = new ArrayList<Integer>();

	  queryMap = new HashMap<Integer, HashMap<String, Integer>>();
	  
	  goldenAnsMap = new HashMap<Integer, HashMap<String, Integer>>();

	  ansMap = new HashMap<Integer, ArrayList<HashMap<String,Integer>>>();
	  
	  cosSimilarity = new HashMap<Integer, ArrayList<Double>>();
	 
	  rankList = new ArrayList<Integer>();
	  
	  goldText = new ArrayList<String>();

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
	 * TODO :: 1. construct the global word dictionary 2. keep the word
	 * frequency for each sentence
	 */
	@Override
	public void processCas(CAS aCas) throws ResourceProcessException {

		JCas jcas;
		try {
			jcas =aCas.getJCas();
		} catch (CASException e) {
			throw new ResourceProcessException(e);
		}

    HashMap<String, Integer> queryVector = null;
    HashMap<String, Integer> goldenAnsVector = null;
    HashMap<String, Integer> ansVector = null;
    ArrayList<HashMap<String, Integer>> answerList;
    
		FSIterator it = jcas.getAnnotationIndex(Document.type).iterator();
	
		if (it.hasNext()) {
			Document doc = (Document) it.next();
			int qID = doc.getQueryID();
			//Make sure that your previous annotators have populated this in CAS
			FSList fsTokenList = doc.getTokenList();
			if (!qIdList.contains(qID)) {
			  qIdList.add(doc.getQueryID());
			}
			relList.add(doc.getRelevanceValue());
			
			//Do something useful here
	    ArrayList<Token> tokenList = Utils.fromFSListToCollection(fsTokenList, Token.class);
      
	    //add query(ID+term+frequency) to queryMap
      if (doc.getRelevanceValue() == 99) {
        queryVector = new HashMap<String, Integer>();
        for (Token token : tokenList) {
          queryVector.put(token.getText(), token.getFrequency());
        }
        queryMap.put(qID, queryVector);
      }
      else {
        //add golden answers(ID+term+frequency) to goldenAnsMap
        if (doc.getRelevanceValue() == 1) {
          goldenAnsVector = new HashMap<String, Integer>();
          for (Token token : tokenList) {
            goldenAnsVector.put(token.getText(), token.getFrequency());
          }
          goldenAnsMap.put(qID, goldenAnsVector);
          goldText.add(doc.getText());
        }
        else {
        //add answers(ID+No+term+frequency) to answerMap
          ansVector = new HashMap<String, Integer>();
          for (Token token : tokenList) {
            ansVector.put(token.getText(), token.getFrequency());
          }
          if (!ansMap.containsKey(qID)) {
            answerList = new ArrayList<HashMap<String, Integer>>();
            ansMap.put(qID, answerList);
            }
          else {
            answerList = ansMap.get(qID);
          }
          answerList.add(ansVector);
          ansMap.put(qID, answerList);    
          }
      }
		}

	}

	/**
	 * TODO 1. Compute Cosine Similarity and rank the retrieved sentences 2.
	 * Compute the MRR metric
	 */
	@Override
	public void collectionProcessComplete(ProcessTrace arg0)
			throws ResourceProcessException, IOException {

		super.collectionProcessComplete(arg0);

		// TODO :: compute the cosine similarity measure
		
		int qID;
		Map<String, Integer> queryVector;
    Map<String, Integer> goldenAnsVector;
    HashMap<String, Integer> ansVector;
    
    Iterator qIdIt = qIdList.iterator();
    while (qIdIt.hasNext()) {
      ArrayList<Double> simList;
      qID = (Integer) qIdIt.next();
      queryVector = queryMap.get(qID);
     
      //add similarity between query and golden answer
      goldenAnsVector = goldenAnsMap.get(qID);      
      
      double goldSim = computeCosineSimilarity(queryVector, goldenAnsVector);
//      double goldSim = compute_Jaccard(queryVector, goldenAnsVector);
//    double goldSim = compute_Dice(queryVector, goldenAnsVector);

      if (!cosSimilarity.containsKey(qID)) {
        simList = new ArrayList<Double>(); 
        cosSimilarity.put(qID, simList);//before sorting, the firse element in simList should be cos similairity of golden answer
      }
      else {
        simList = cosSimilarity.get(qID); 
      }
      simList.add(goldSim);
      
      //add similarity between query and other answers
      Iterator ansListIt = ansMap.get(qID).iterator();
      while (ansListIt.hasNext()) { 
        ansVector = (HashMap<String, Integer>) ansListIt.next();

        double otherSim = computeCosineSimilarity(queryVector, ansVector);
//      double otherSim = compute_Jaccard(queryVector, ansVector);
//      double otherSim = compute_Dice(queryVector, ansVector);

        simList = cosSimilarity.get(qID); 
        simList.add(otherSim);
      }
    }

		
		
		// TODO :: compute the rank of retrieved sentences
    FileWriter fw = new FileWriter(file);
    BufferedWriter bw = new BufferedWriter(fw);
    for (int id : qIdList) { // not use: keySet since traverse map may not have order
      ArrayList<Double> simList = cosSimilarity.get(id);
      Double goldCos = simList.get(0);//save cos similarity of gold answer(answer with 'rel==1')
      Collections.sort(simList, Collections.reverseOrder());// sort in descending order
      for (int j = 0; j < simList.size(); j++) {
        if (simList.get(j) == goldCos) {
          rankList.add(j+1);//add the rank of gold answer(simList start from 0)  
          DecimalFormat df = new DecimalFormat("0.0000");// save four significant digits
          
          bw.write("consine=" + df.format(goldCos) + "\t rank=" + (int)(j+1) + "\t qid=" + id + 
                  "\t rel=" + "1" + "\t" + goldText.get(id-1));
//          bw.write("Jaccard=" + df.format(goldCos) + "\t rank=" + (int)(j+1) + "\t qid=" + id + 
//                  "\t rel=" + "1" + "\t" + goldText.get(id-1));         
//          bw.write("Dice=" + df.format(goldCos) + "\t rank=" + (int)(j+1) + "\t qid=" + id + 
//                  "\t rel=" + "1" + "\t" + goldText.get(id-1));
          
          bw.newLine();         
          break;
        }
      }     
    }
    
		// TODO :: compute the metric:: mean reciprocal rank
		double metric_mrr = compute_mrr();
		bw.write(" (MRR) Mean Reciprocal Rank ::" + (double)(Math.round(metric_mrr*10000)/10000.0000));    
		System.out.println(" (MRR) Mean Reciprocal Rank ::" + (double)(Math.round(metric_mrr*10000)/10000.0000));// save four significant digit
		
		bw.close();
    fw.close();
	}

	/**
	 * 
	 * @return cosine_similarity
	 */
	private double computeCosineSimilarity(Map<String, Integer> queryVector,
			Map<String, Integer> docVector) {
		
	  double cosine_similarity=0.0;

		// TODO :: compute cosine similarity between two sentences
		double numerator = 0.0;//sum(w_a_i * w_b_i)
		double demominator1 = 0.0;//sum((w_a_i)^2)
    double demominator2 = 0.0;//sum((w_b_i)^2)
    double demominator = 0.0; //Math.sqrt(sum((w_a_i)^2) * sum((w_b_i)^2))
    
    Set<String> querVectorIt = queryVector.keySet(); // traverse map
    Set<String> docVectorIt = docVector.keySet();
		for (String s1 : querVectorIt) {
		  demominator1 += queryVector.get(s1) * queryVector.get(s1);
		  if (docVector.containsKey(s1)) {
       numerator += queryVector.get(s1) * docVector.get(s1); 
		  }
    }
		for (String s2 : docVectorIt) {
      demominator2 += docVector.get(s2) * docVector.get(s2);
    }
		
		demominator = Math.sqrt(demominator1 * demominator2);
		cosine_similarity = numerator / demominator;
		
		return cosine_similarity;
	}

	/**
	 * 
	 * @return mrr
	 */
	private double compute_mrr() {
		
	  double metric_mrr=0.0;
		int count = 0;
		
		// TODO :: compute Mean Reciprocal Rank (MRR) of the text collection
		for (int rank : rankList) {
		  metric_mrr += 1.0 / (double) rank;
		  count++;
		}
		metric_mrr = metric_mrr / count;
		return metric_mrr;
	}

/**
 * return Jaccord similarity between two vectors
 * @param queryVector
 * @param docVector
 * @return
 */
private double compute_Jaccard(Map<String, Integer> queryVector,
        Map<String, Integer> docVector) {
  
  double Jaccord_similarity=0.0;
  
//TODO :: compute Jaccord similarity between two sentences
  double numerator = 0.0;//sum(w_a_i * w_b_i)
  double demominator1 = 0.0;//sum((w_a_i)^2)
  double demominator2 = 0.0;//sum((w_b_i)^2)
  double demominator = 0.0; //sum((w_a_i)^2) + sum((w_b_i)^2) - sum(w_a_i * w_b_i)
  
  Set<String> querVectorIt = queryVector.keySet(); // traverse map
  Set<String> docVectorIt = docVector.keySet();
  for (String s1 : querVectorIt) {
    demominator1 += queryVector.get(s1) * queryVector.get(s1);
    if (docVector.containsKey(s1)) {
     numerator += queryVector.get(s1) * docVector.get(s1); 
    }
  }
  for (String s2 : docVectorIt) {
    demominator2 += docVector.get(s2) * docVector.get(s2);
  }
  
  demominator = demominator1 + demominator2 - numerator;
  Jaccord_similarity = numerator / demominator;
  
  return Jaccord_similarity;
 }


private double compute_Dice(Map<String, Integer> queryVector,
        Map<String, Integer> docVector) {
  
  double Dice_similarity=0.0;
  
//TODO :: compute Dice similarity between two sentences
  double numerator = 0.0;//sum(w_a_i * w_b_i)
  double demominator1 = 0.0;//sum((w_a_i)^2)
  double demominator2 = 0.0;//sum((w_b_i)^2)
  double demominator = 0.0; //sum((w_a_i)^2) + sum((w_b_i)^2)
  
  Set<String> querVectorIt = queryVector.keySet(); // traverse map
  Set<String> docVectorIt = docVector.keySet();
  for (String s1 : querVectorIt) {
    demominator1 += queryVector.get(s1) * queryVector.get(s1);
    if (docVector.containsKey(s1)) {
     numerator += queryVector.get(s1) * docVector.get(s1); 
    }
  }
  for (String s2 : docVectorIt) {
    demominator2 += docVector.get(s2) * docVector.get(s2);
  }
  
  demominator = demominator1 + demominator2;
  Dice_similarity = 2 * numerator / demominator;
  System.out.println(Dice_similarity);
  
  return Dice_similarity;
 }
}

