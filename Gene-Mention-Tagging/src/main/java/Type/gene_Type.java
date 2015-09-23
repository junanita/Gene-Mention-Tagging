
/* First created by JCasGen Mon Oct 06 05:18:00 EDT 2014 */
package Type;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import edu.cmu.deiis.types.Annotation_Type;

/** 
 * Updated by JCasGen Mon Oct 06 05:18:00 EDT 2014
 * @generated */
public class gene_Type extends Annotation_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (gene_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = gene_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new gene(addr, gene_Type.this);
  			   gene_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new gene(addr, gene_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = gene.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("Type.gene");
 
  /** @generated */
  final Feature casFeat_ID;
  /** @generated */
  final int     casFeatCode_ID;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getID(int addr) {
        if (featOkTst && casFeat_ID == null)
      jcas.throwFeatMissing("ID", "Type.gene");
    return ll_cas.ll_getStringValue(addr, casFeatCode_ID);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setID(int addr, String v) {
        if (featOkTst && casFeat_ID == null)
      jcas.throwFeatMissing("ID", "Type.gene");
    ll_cas.ll_setStringValue(addr, casFeatCode_ID, v);}
    
  
 
  /** @generated */
  final Feature casFeat_Text;
  /** @generated */
  final int     casFeatCode_Text;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getText(int addr) {
        if (featOkTst && casFeat_Text == null)
      jcas.throwFeatMissing("Text", "Type.gene");
    return ll_cas.ll_getStringValue(addr, casFeatCode_Text);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setText(int addr, String v) {
        if (featOkTst && casFeat_Text == null)
      jcas.throwFeatMissing("Text", "Type.gene");
    ll_cas.ll_setStringValue(addr, casFeatCode_Text, v);}
    
  
 
  /** @generated */
  final Feature casFeat_GeneStart;
  /** @generated */
  final int     casFeatCode_GeneStart;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getGeneStart(int addr) {
        if (featOkTst && casFeat_GeneStart == null)
      jcas.throwFeatMissing("GeneStart", "Type.gene");
    return ll_cas.ll_getIntValue(addr, casFeatCode_GeneStart);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setGeneStart(int addr, int v) {
        if (featOkTst && casFeat_GeneStart == null)
      jcas.throwFeatMissing("GeneStart", "Type.gene");
    ll_cas.ll_setIntValue(addr, casFeatCode_GeneStart, v);}
    
  
 
  /** @generated */
  final Feature casFeat_GeneEnd;
  /** @generated */
  final int     casFeatCode_GeneEnd;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getGeneEnd(int addr) {
        if (featOkTst && casFeat_GeneEnd == null)
      jcas.throwFeatMissing("GeneEnd", "Type.gene");
    return ll_cas.ll_getIntValue(addr, casFeatCode_GeneEnd);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setGeneEnd(int addr, int v) {
        if (featOkTst && casFeat_GeneEnd == null)
      jcas.throwFeatMissing("GeneEnd", "Type.gene");
    ll_cas.ll_setIntValue(addr, casFeatCode_GeneEnd, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public gene_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_ID = jcas.getRequiredFeatureDE(casType, "ID", "uima.cas.String", featOkTst);
    casFeatCode_ID  = (null == casFeat_ID) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_ID).getCode();

 
    casFeat_Text = jcas.getRequiredFeatureDE(casType, "Text", "uima.cas.String", featOkTst);
    casFeatCode_Text  = (null == casFeat_Text) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Text).getCode();

 
    casFeat_GeneStart = jcas.getRequiredFeatureDE(casType, "GeneStart", "uima.cas.Integer", featOkTst);
    casFeatCode_GeneStart  = (null == casFeat_GeneStart) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_GeneStart).getCode();

 
    casFeat_GeneEnd = jcas.getRequiredFeatureDE(casType, "GeneEnd", "uima.cas.Integer", featOkTst);
    casFeatCode_GeneEnd  = (null == casFeat_GeneEnd) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_GeneEnd).getCode();

  }
}



    