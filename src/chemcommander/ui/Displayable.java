/**************************************************************************************************
  chemcommander/ui/Displayable.java
  @author: Jan Chara
  date:
  description: 
**************************************************************************************************/

package chemcommander.ui;

import chemcommander.command.CommandManager;
import java.util.ArrayList;


/**
 *
 * @author honza
 */
public class Displayable
{
/**************************************************************************************************
 * variables declaration
**************************************************************************************************/
    
    private String title;
    private ArrayList<Item> items;
    
/**************************************************************************************************
 * constructors
**************************************************************************************************/
    
    public Displayable(String title) {
        this.title = title;
        this.items = new ArrayList();
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
    
    public void addCommand(CommandManager c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public void append(Item i) {
        this.items.add(i);
    }
    
    public void append(Object o) {
        this.append(new StringItem(o.toString()));
    }
    
    public void execute() {
        System.out.println("executing: " + this.title);
        int l = this.items.size();
        Object o;
        for (int i = 0; i < l; i++) {
            o = this.items.get(i);
            if (null == o) {
                continue;
            }
            else if (o instanceof CommandManager) {
                System.out.println((CommandManager)o);
            }
            else {
                ((Item)o).run();
            }
        }
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public boolean isShown() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
/**************************************************************************************************
 * parent class implemented methods:
**************************************************************************************************/
    
/**************************************************************************************************
 * interface implemented methods:
**************************************************************************************************/
}