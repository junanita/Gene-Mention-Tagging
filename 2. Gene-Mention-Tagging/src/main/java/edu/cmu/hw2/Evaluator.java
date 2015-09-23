package edu.cmu.hw2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * This java file aims at evaluate correctness of aggregated analysis engine.
 * @author ningna
 */
public class Evaluator {
  private int correct_num = 0; // total number of correct answer in hw2-ningna.out

  private int answer_num = 0; // total number of answer (correct + false) in hw2-ningna.out

  private int expected_num = 0; // expected number of correct answer 
  
  private HashMap<String, Integer> map = new HashMap<String,Integer>(); //store all gold standard in sample.out.


  
  public void setAnswerText(String path) throws IOException {
    File file = new File(path);
    InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
    BufferedReader br = new BufferedReader(reader);
    String line = ""; //read gold standard line by line
    String[] parts; 
    String ID = "";
    String Text = "";
    line = br.readLine();
    while ((line = br.readLine()) != null) { // one line each time
      parts = line.split("\\|");
      ID = parts[0];
      Text = parts[2];
      map.put(ID+Text, 1);//store ID+Text informaton of each gold standard
      expected_num++;
    }
    br.close();
    reader.close();

  }

  public double getPrecision() {
    return (double) correct_num / answer_num;
  }

  public double getRecall() {
    return (double) correct_num / expected_num;
  }


 /**
  * Judge if the answer is the right answer
  * @param ans
  */
  public void judgeAnswer(String ans) {
    if (ans == null)
      return;
    if(map.containsKey(ans)){
      correct_num++;
    }
  }

  public void setAnswernum(int num) {
    answer_num = num;
  }

 /**
  * Calculate F-Score: 
  * F-Score = 2 * precision * recall / (precision + recall)
  * @return
  */
  public double getfScore() {
    double precision = getPrecision();
    double recall = getRecall();
    return 2.0 * precision * recall / (precision + recall);
  }

  /**
   * return the report
   * @return
   */
  public String getReport() {
    return "\nPerformance Report:\nReturned Correct Answer:" + correct_num + "\nTotal Returned Answer:" + answer_num + "\nExpected Answer Num:" + expected_num + "\nPrecision:"
            + getPrecision() + "\nRecall:" + getRecall() + "\nF-socre:" + getfScore();
  }
}