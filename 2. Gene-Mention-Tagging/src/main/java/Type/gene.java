

/* First created by JCasGen Mon Oct 06 05:18:00 EDT 2014 */
package Type;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import edu.cmu.deiis.types.Annotation;


/** 
 * Updated by JCasGen Mon Oct 06 05:18:00 EDT 2014
 * XML source: /home/ningna/git/hw2-ningnaw/hw2-ningnaw/src/main/resources/descriptors/deiis_types.xml
 * @generated */
public class gene extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(gene.class);
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
  protected gene() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public gene(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public gene(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public gene(JCas jcas, int begin, int end) {
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
    if (gene_Type.featOkTst && ((gene_Type)jcasType).casFeat_ID == null)
      jcasType.jcas.throwFeatMissing("ID", "Type.gene");
    return jcasType.ll_cas.ll_getStringValue(addr, ((gene_Type)jcasType).casFeatCode_ID);}
    
  /** setter for ID - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setID(String v) {
    if (gene_Type.featOkTst && ((gene_Type)jcasType).casFeat_ID == null)
      jcasType.jcas.throwFeatMissing("ID", "Type.gene");
    jcasType.ll_cas.ll_setStringValue(addr, ((gene_Type)jcasType).casFeatCode_ID, v);}    
   
    
  //*--------------*
  //* Feature: Text

  /** getter for Text - gets 
   * @generated
   * @return value of the feature 
   */
  public String getText() {
    if (gene_Type.featOkTst && ((gene_Type)jcasType).casFeat_Text == null)
      jcasType.jcas.throwFeatMissing("Text", "Type.gene");
    return jcasType.ll_cas.ll_getStringValue(addr, ((gene_Type)jcasType).casFeatCode_Text);}
    
  /** setter for Text - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setText(String v) {
    if (gene_Type.featOkTst && ((gene_Type)jcasType).casFeat_Text == null)
      jcasType.jcas.throwFeatMissing("Text", "Type.gene");
    jcasType.ll_cas.ll_setStringValue(addr, ((gene_Type)jcasType).casFeatCode_Text, v);}    
   
    
  //*--------------*
  //* Feature: GeneStart

  /** getter for GeneStart - gets 
   * @generated
   * @return value of the feature 
   */
  public int getGeneStart() {
    if (gene_Type.featOkTst && ((gene_Type)jcasType).casFeat_GeneStart == null)
      jcasType.jcas.throwFeatMissing("GeneStart", "Type.gene");
    return jcasType.ll_cas.ll_getIntValue(addr, ((gene_Type)jcasType).casFeatCode_GeneStart);}
    
  /** setter for GeneStart - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setGeneStart(int v) {
    if (gene_Type.featOkTst && ((gene_Type)jcasType).casFeat_GeneStart == null)
      jcasType.jcas.throwFeatMissing("GeneStart", "Type.gene");
    jcasType.ll_cas.ll_setIntValue(addr, ((gene_Type)jcasType).casFeatCode_GeneStart, v);}    
   
    
  //*--------------*
  //* Feature: GeneEnd

  /** getter for GeneEnd - gets 
   * @generated
   * @return value of the feature 
   */
  public int getGeneEnd() {
    if (gene_Type.featOkTst && ((gene_Type)jcasType).casFeat_GeneEnd == null)
      jcasType.jcas.throwFeatMissing("GeneEnd", "Type.gene");
    return jcasType.ll_cas.ll_getIntValue(addr, ((gene_Type)jcasType).casFeatCode_GeneEnd);}
    
  /** setter for GeneEnd - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setGeneEnd(int v) {
    if (gene_Type.featOkTst && ((gene_Type)jcasType).casFeat_GeneEnd == null)
      jcasType.jcas.throwFeatMissing("GeneEnd", "Type.gene");
    jcasType.ll_cas.ll_setIntValue(addr, ((gene_Type)jcasType).casFeatCode_GeneEnd, v);}    
  }

    