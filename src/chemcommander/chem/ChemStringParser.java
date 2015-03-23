/**************************************************************************************************
  chemcommander/chem/ChemStringParser.java
  @author: Jan Chara
  date:
  description: 
**************************************************************************************************/

package chemcommander.chem;

import chemcommander.phys.PhysProperty;
import java.text.StringCharacterIterator;

/**
 *
 * @author honza
 */
public abstract class ChemStringParser
{
/**************************************************************************************************
 * variables declaration
**************************************************************************************************/
    
    public static final char PRIORITY_START = '(';
    public static final char PRIORITY_END = ')';
    
    private String chemString;
    
/**************************************************************************************************
 * constructors
**************************************************************************************************/
    
    public ChemStringParser(String chemString) {
        this.chemString = chemString;
    }
    
/**************************************************************************************************
 * main method:
**************************************************************************************************/

    public static void main(String[] args) {
    }
    
/**************************************************************************************************
 * own methods:
**************************************************************************************************/
    
    /**
     * Parses properties from StringCharacterIterator
     * @param it
     * @return 
     */
    public abstract PhysProperty[] getProperties(StringCharacterIterator it);
    
    /**
     * By using regular expression divides parser
     * @param regex
     * @param parser
     */
    public static final void fragmentize(String regex, ChemStringParser parser) {
    }
    
/**************************************************************************************************
 * parent class implemented methods:
**************************************************************************************************/
    
/**************************************************************************************************
 * interface implemented methods:
**************************************************************************************************/
}