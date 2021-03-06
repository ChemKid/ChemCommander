/**************************************************************************************************
  chemcommander/ui/StringItem.java
  @author: Jan Chara
  date:
  description: 
**************************************************************************************************/

package chemcommander.ui;


/**
 *
 * @author honza
 */
public class StringItem extends Item
{
/**************************************************************************************************
 * variables declaration
**************************************************************************************************/

    protected String info;
    
/**************************************************************************************************
 * constructors
**************************************************************************************************/
    
    public StringItem(String label, String info) {
        super(label);
        this.info = info;
    }
    
    public StringItem(String info) {
        this("", info);
    }
    
/**************************************************************************************************
 * main method:
**************************************************************************************************/

    public static void main(String[] args) {
    }
    
/**************************************************************************************************
 * overriding or implementing of parent or overloaded methods
**************************************************************************************************/
    
    public String toString() {
        if (null == label || "".equals(this.label)) {
            return this.info;
        }
        return this.label + ": " + this.info;
    }
    
/**************************************************************************************************
 * own methods:
**************************************************************************************************/
    
/**************************************************************************************************
 * parent class implemented methods:
**************************************************************************************************/
    
/**************************************************************************************************
 * interface implemented methods:
**************************************************************************************************/
    
    @Override
    public void run() {
        System.out.println(this); /// zmenit na this.os.wrtite();
    }
}