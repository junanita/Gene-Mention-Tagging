

/* First created by JCasGen Sat Oct 18 17:31:50 EDT 2014 */
package edu.cmu.lti.f14.hw3.hw3_ningnaw.typesystems;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.FSList;
import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Sat Oct 18 18:00:35 EDT 2014
 * XML source: /home/ningna/git/hw3-ningnaw/hw3-ningnaw/src/main/resources/descriptors/typesystems/VectorSpaceTypes.xml
 * @generated */
public class Answer extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Answer.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated
   * @return index of the type  
   */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected Answer() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public Answer(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public Answer(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public Answer(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** 
   * <!-- begin-user-doc -->
   * Write your own initialization here
   * <!-- end-user-doc -->
   *
   * @generated modifiable 
   */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: cosSimilarity

  /** getter for cosSimilarity - gets 
   * @generated
   * @return value of the feature 
   */
  public double getCosSimilarity() {
    if (Answer_Type.featOkTst && ((Answer_Type)jcasType).casFeat_cosSimilarity == null)
      jcasType.jcas.throwFeatMissing("cosSimilarity", "edu.cmu.lti.f14.hw3.hw3_ningnaw.typesystems.Answer");
    return jcasType.ll_cas.ll_getDoubleValue(addr, ((Answer_Type)jcasType).casFeatCode_cosSimilarity);}
    
  /** setter for cosSimilarity - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setCosSimilarity(double v) {
    if (Answer_Type.featOkTst && ((Answer_Type)jcasType).casFeat_cosSimilarity == null)
      jcasType.jcas.throwFeatMissing("cosSimilarity", "edu.cmu.lti.f14.hw3.hw3_ningnaw.typesystems.Answer");
    jcasType.ll_cas.ll_setDoubleValue(addr, ((Answer_Type)jcasType).casFeatCode_cosSimilarity, v);}    
   
    
  //*--------------*
  //* Feature: rank

  /** getter for rank - gets 
   * @generated
   * @return value of the feature 
   */
  public int getRank() {
    if (Answer_Type.featOkTst && ((Answer_Type)jcasType).casFeat_rank == null)
      jcasType.jcas.throwFeatMissing("rank", "edu.cmu.lti.f14.hw3.hw3_ningnaw.typesystems.Answer");
    return jcasType.ll_cas.ll_getIntValue(addr, ((Answer_Type)jcasType).casFeatCode_rank);}
    
  /** setter for rank - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setRank(int v) {
    if (Answer_Type.featOkTst && ((Answer_Type)jcasType).casFeat_rank == null)
      jcasType.jcas.throwFeatMissing("rank", "edu.cmu.lti.f14.hw3.hw3_ningnaw.typesystems.Answer");
    jcasType.ll_cas.ll_setIntValue(addr, ((Answer_Type)jcasType).casFeatCode_rank, v);}    
   
    
  //*--------------*
  //* Feature: queryID

  /** getter for queryID - gets 
   * @generated
   * @return value of the feature 
   */
  public int getQueryID() {
    if (Answer_Type.featOkTst && ((Answer_Type)jcasType).casFeat_queryID == null)
      jcasType.jcas.throwFeatMissing("queryID", "edu.cmu.lti.f14.hw3.hw3_ningnaw.typesystems.Answer");
    return jcasType.ll_cas.ll_getIntValue(addr, ((Answer_Type)jcasType).casFeatCode_queryID);}
    
  /** setter for queryID - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setQueryID(int v) {
    if (Answer_Type.featOkTst && ((Answer_Type)jcasType).casFeat_queryID == null)
      jcasType.jcas.throwFeatMissing("queryID", "edu.cmu.lti.f14.hw3.hw3_ningnaw.typesystems.Answer");
    jcasType.ll_cas.ll_setIntValue(addr, ((Answer_Type)jcasType).casFeatCode_queryID, v);}    
   
    
  //*--------------*
  //* Feature: tokenList

  /** getter for tokenList - gets 
   * @generated
   * @return value of the feature 
   */
  public FSList getTokenList() {
    if (Answer_Type.featOkTst && ((Answer_Type)jcasType).casFeat_tokenList == null)
      jcasType.jcas.throwFeatMissing("tokenList", "edu.cmu.lti.f14.hw3.hw3_ningnaw.typesystems.Answer");
    return (FSList)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Answer_Type)jcasType).casFeatCode_tokenList)));}
    
  /** setter for tokenList - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setTokenList(FSList v) {
    if (Answer_Type.featOkTst && ((Answer_Type)jcasType).casFeat_tokenList == null)
      jcasType.jcas.throwFeatMissing("tokenList", "edu.cmu.lti.f14.hw3.hw3_ningnaw.typesystems.Answer");
    jcasType.ll_cas.ll_setRefValue(addr, ((Answer_Type)jcasType).casFeatCode_tokenList, jcasType.ll_cas.ll_getFSRef(v));}    
  }

    