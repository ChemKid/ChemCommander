/**************************************************************************************************
  chemcommander/chem/Chemical.java
  @author: Jan Chara
  date:
  description: 
**************************************************************************************************/

package chemcommander.chem;

/**
 *
 * @author honza
 */
public abstract class Chemical
{
/**************************************************************************************************
 * variables declaration
**************************************************************************************************/
    
    protected String name;
    
/**************************************************************************************************
 * constructors
**************************************************************************************************/

    public Chemical(String name) {
        this.name = name;
    }
    
/**************************************************************************************************
 * main method:
**************************************************************************************************/

    public static void main(String[] args) {
    }
    
/**************************************************************************************************
 * overriding or implementing of parent or overloaded methods
**************************************************************************************************/
    
/**************************************************************************************************
 * own methods:
**************************************************************************************************/
    
    public String getName() {
        return this.name;
    }
    
/**************************************************************************************************
 * parent class implemented methods:
**************************************************************************************************/
    
/**************************************************************************************************
 * interface implemented methods:
**************************************************************************************************/
}