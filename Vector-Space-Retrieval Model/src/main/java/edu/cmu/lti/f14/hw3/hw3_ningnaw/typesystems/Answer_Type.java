
/* First created by JCasGen Sat Oct 18 17:31:50 EDT 2014 */
package edu.cmu.lti.f14.hw3.hw3_ningnaw.typesystems;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** 
 * Updated by JCasGen Sat Oct 18 18:00:35 EDT 2014
 * @generated */
public class Answer_Type extends Annotation_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Answer_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Answer_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Answer(addr, Answer_Type.this);
  			   Answer_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Answer(addr, Answer_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = Answer.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("edu.cmu.lti.f14.hw3.hw3_ningnaw.typesystems.Answer");
 
  /** @generated */
  final Feature casFeat_cosSimilarity;
  /** @generated */
  final int     casFeatCode_cosSimilarity;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public double getCosSimilarity(int addr) {
        if (featOkTst && casFeat_cosSimilarity == null)
      jcas.throwFeatMissing("cosSimilarity", "edu.cmu.lti.f14.hw3.hw3_ningnaw.typesystems.Answer");
    return ll_cas.ll_getDoubleValue(addr, casFeatCode_cosSimilarity);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setCosSimilarity(int addr, double v) {
        if (featOkTst && casFeat_cosSimilarity == null)
      jcas.throwFeatMissing("cosSimilarity", "edu.cmu.lti.f14.hw3.hw3_ningnaw.typesystems.Answer");
    ll_cas.ll_setDoubleValue(addr, casFeatCode_cosSimilarity, v);}
    
  
 
  /** @generated */
  final Feature casFeat_rank;
  /** @generated */
  final int     casFeatCode_rank;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getRank(int addr) {
        if (featOkTst && casFeat_rank == null)
      jcas.throwFeatMissing("rank", "edu.cmu.lti.f14.hw3.hw3_ningnaw.typesystems.Answer");
    return ll_cas.ll_getIntValue(addr, casFeatCode_rank);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setRank(int addr, int v) {
        if (featOkTst && casFeat_rank == null)
      jcas.throwFeatMissing("rank", "edu.cmu.lti.f14.hw3.hw3_ningnaw.typesystems.Answer");
    ll_cas.ll_setIntValue(addr, casFeatCode_rank, v);}
    
  
 
  /** @generated */
  final Feature casFeat_queryID;
  /** @generated */
  final int     casFeatCode_queryID;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getQueryID(int addr) {
        if (featOkTst && casFeat_queryID == null)
      jcas.throwFeatMissing("queryID", "edu.cmu.lti.f14.hw3.hw3_ningnaw.typesystems.Answer");
    return ll_cas.ll_getIntValue(addr, casFeatCode_queryID);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setQueryID(int addr, int v) {
        if (featOkTst && casFeat_queryID == null)
      jcas.throwFeatMissing("queryID", "edu.cmu.lti.f14.hw3.hw3_ningnaw.typesystems.Answer");
    ll_cas.ll_setIntValue(addr, casFeatCode_queryID, v);}
    
  
 
  /** @generated */
  final Feature casFeat_tokenList;
  /** @generated */
  final int     casFeatCode_tokenList;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getTokenList(int addr) {
        if (featOkTst && casFeat_tokenList == null)
      jcas.throwFeatMissing("tokenList", "edu.cmu.lti.f14.hw3.hw3_ningnaw.typesystems.Answer");
    return ll_cas.ll_getRefValue(addr, casFeatCode_tokenList);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setTokenList(int addr, int v) {
        if (featOkTst && casFeat_tokenList == null)
      jcas.throwFeatMissing("tokenList", "edu.cmu.lti.f14.hw3.hw3_ningnaw.typesystems.Answer");
    ll_cas.ll_setRefValue(addr, casFeatCode_tokenList, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public Answer_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_cosSimilarity = jcas.getRequiredFeatureDE(casType, "cosSimilarity", "uima.cas.Double", featOkTst);
    casFeatCode_cosSimilarity  = (null == casFeat_cosSimilarity) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_cosSimilarity).getCode();

 
    casFeat_rank = jcas.getRequiredFeatureDE(casType, "rank", "uima.cas.Integer", featOkTst);
    casFeatCode_rank  = (null == casFeat_rank) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_rank).getCode();

 
    casFeat_queryID = jcas.getRequiredFeatureDE(casType, "queryID", "uima.cas.Integer", featOkTst);
    casFeatCode_queryID  = (null == casFeat_queryID) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_queryID).getCode();

 
    casFeat_tokenList = jcas.getRequiredFeatureDE(casType, "tokenList", "uima.cas.FSList", featOkTst);
    casFeatCode_tokenList  = (null == casFeat_tokenList) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_tokenList).getCode();

  }
}



    