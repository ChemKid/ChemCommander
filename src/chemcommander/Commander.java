/**************************************************************************************************
  chemcommander/Commander.java
  @author: Jan Chara
  date:
  description: 
**************************************************************************************************/

package chemcommander;

import chemcommander.command.Command;
import chemcommander.command.CommandManager;
import chemcommander.ui.DisplayableManager;
import chemcommander.ui.Item;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author honza
 */
public abstract class Commander
{
/**************************************************************************************************
 * variables declaration
**************************************************************************************************/

    private ArrayList<DisplayableManager> dm;
    private DisplayableManager actualDM;
    
    public static final CommandManager EXIT = new CommandManager(new Command("Exit")) {
        @Override
        public void getAction(DisplayableManager dm) {
            dm.getCommander().exit();
        }
    };
    
    public static final CommandManager BACK = new CommandManager(new Command("Back")) {
        @Override
        public void getAction(DisplayableManager dm) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    };
    
    public static final Item ANY_KEY = new Item("") {
        @Override
        public void run() {
            System.out.println("Press any key");
            try {
                System.in.read();
            } catch(IOException e) {
                //System.out.println(e.getMessage());
            }
        }
    };
    
    public static final Item NEW_LINE = new Item("") {
        @Override
        public void run() {
            System.out.println();
        }
    };
    
/**************************************************************************************************
 * constructors
**************************************************************************************************/

    public Commander() {
        this.dm = new ArrayList();
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
    
    protected abstract void initialize();
    
    public void exit() {
    }
    
    private void add(DisplayableManager dm) {
        this.actualDM = dm;
        this.dm.add(dm);
    }
    
    public void display(DisplayableManager dm) {
        this.add(dm);
        dm.getDisplayable().execute();
    }
    
/**************************************************************************************************
 * parent class implemented methods:
**************************************************************************************************/
    
/**************************************************************************************************
 * interface implemented methods:
**************************************************************************************************/
}