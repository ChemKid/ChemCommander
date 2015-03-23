/**************************************************************************************************
  chemcommander/ui/Display.java
  @author: Jan Chara
  date:
  description: 
**************************************************************************************************/

package chemcommander.ui;

import chemcommander.Commander;
import java.util.ArrayList;


/**
 *
 * @author honza
 */
public class Display
{
/**************************************************************************************************
 * variables declaration
**************************************************************************************************/
    
    private Commander commander;
    private Displayable currentDisplayable;
    
    private static ArrayList<Display> display = new ArrayList();
    
/**************************************************************************************************
 * constructors
**************************************************************************************************/
    
    private Display(Commander commander) {
        this.commander = commander;
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
    
    public static final Display getDisplay(Commander commander) {
        Display d;
        if (null == Display.display || Display.display.isEmpty()) {
            Display.add(d = new Display(commander));
            return d;
        }
        int size = Display.display.size();
        for (int i = 0; i < size; i++) {
            d = Display.display.get(i);
            if (d.commander == commander) {
                return d;
            }
        }
        return null;
    }
    
    public void setCurrent(Displayable d) {
        this.currentDisplayable = d;
    }
    
    public Displayable getCurrent() {
        return this.currentDisplayable;
    }
    
    private static void add(Display display) {
        Display.display.add(display);
    }
    
/**************************************************************************************************
 * parent class implemented methods:
**************************************************************************************************/
    
/**************************************************************************************************
 * interface implemented methods:
**************************************************************************************************/
}