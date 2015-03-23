/**************************************************************************************************
  chemcommander/ui/Item.java
  @author: Jan Chara
  date:
  description: 
**************************************************************************************************/

package chemcommander.ui;

import java.io.InputStream;
import java.io.OutputStream;


/**
 *
 * @author honza
 */
public abstract class Item implements Runnable
{
/**************************************************************************************************
 * variables declaration
**************************************************************************************************/
    
    protected String label;
    protected InputStream is;
    protected OutputStream os;
    
    public static final InputStream DEFAULT_INPUT_STREAM = System.in;
    public static final OutputStream DEFAULT_OUTPUT_STREAM = System.out;
    
/**************************************************************************************************
 * constructors
**************************************************************************************************/
    
    /**
     * Common constructor for Item classes
     * @param label string label for this item
     */
    public Item(String label) {
        this.label = label;
        this.is = Item.DEFAULT_INPUT_STREAM;
        this.os = Item.DEFAULT_OUTPUT_STREAM;
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
    
    public void setOutputStream(OutputStream os) {
        if (null == os) {
            this.os = Item.DEFAULT_OUTPUT_STREAM;
        }
        else {
            this.os = os;
        }
    }
    
    public void setInputStream(InputStream is) {
        if (null == is) {
            this.is = Item.DEFAULT_INPUT_STREAM;
        }
        else {
            this.is = is;
        }
    }
    
/**************************************************************************************************
 * parent class implemented methods:
**************************************************************************************************/
    
/**************************************************************************************************
 * interface implemented methods:
**************************************************************************************************/
}