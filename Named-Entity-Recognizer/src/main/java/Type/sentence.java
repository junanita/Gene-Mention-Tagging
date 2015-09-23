

/* First created by JCasGen Mon Sep 22 08:38:38 EDT 2014 */
package Type;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Wed Sep 24 09:03:01 EDT 2014
 * XML source: /home/ningna/git/hw1-ningnaw/hw1-ningnaw/src/main/resources/aeDescriptor.xml
 * @generated */
public class sentence extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(sentence.class);
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
  protected sentence() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public sentence(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public sentence(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public sentence(JCas jcas, int begin, int end) {
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
  //* Feature: ID

  /** getter for ID - gets 
   * @generated
   * @return value of the feature 
   */
  public String getID() {
    if (sentence_Type.featOkTst && ((sentence_Type)jcasType).casFeat_ID == null)
      jcasType.jcas.throwFeatMissing("ID", "Type.sentence");
    return jcasType.ll_cas.ll_getStringValue(addr, ((sentence_Type)jcasType).casFeatCode_ID);}
    
  /** setter for ID - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setID(String v) {
    if (sentence_Type.featOkTst && ((sentence_Type)jcasType).casFeat_ID == null)
      jcasType.jcas.throwFeatMissing("ID", "Type.sentence");
    jcasType.ll_cas.ll_setStringValue(addr, ((sentence_Type)jcasType).casFeatCode_ID, v);}    
   
    
  //*--------------*
  //* Feature: Text

  /** getter for Text - gets 
   * @generated
   * @return value of the feature 
   */
  public String getText() {
    if (sentence_Type.featOkTst && ((sentence_Type)jcasType).casFeat_Text == null)
      jcasType.jcas.throwFeatMissing("Text", "Type.sentence");
    return jcasType.ll_cas.ll_getStringValue(addr, ((sentence_Type)jcasType).casFeatCode_Text);}
    
  /** setter for Text - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setText(String v) {
    if (sentence_Type.featOkTst && ((sentence_Type)jcasType).casFeat_Text == null)
      jcasType.jcas.throwFeatMissing("Text", "Type.sentence");
    jcasType.ll_cas.ll_setStringValue(addr, ((sentence_Type)jcasType).casFeatCode_Text, v);}    
  }

    