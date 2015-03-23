/**************************************************************************************************
  chemcommander/ui/DisplayableManager.java
  @author: Jan Chara
  date:
  description: 
**************************************************************************************************/

package chemcommander.ui;

import chemcommander.Commander;
import chemcommander.command.Command;
import chemcommander.command.CommandManager;


/**
 *
 * @author honza
 */
public class Installer extends DisplayableManager
{
/**************************************************************************************************
 * variables declaration
**************************************************************************************************/
    
    public static final CommandManager INSTALL = new CommandManager(new Command("Y")) {
        public void getAction(DisplayableManager dm) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    };
    
/**************************************************************************************************
 * constructors
**************************************************************************************************/
    
    public Installer(Commander c) {
        super(c);
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
    
    public Displayable getDisplayable() {
        if (null == this.d) {
            this.d = new Displayable("Installer");
            this.d.append("Not installed yet!");
            ChoiceGroup cg = new ChoiceGroup("Choose");
            cg.add(new CommandManager(Command.YES) {
                public void getAction(DisplayableManager dm) {
                    System.out.println("Installing...");
                }
            });
            cg.add(new CommandManager(Command.NO) {
                public void getAction(DisplayableManager dm) {
                    System.out.println("Installation aborted!");
                }
            });
            this.d.append(cg);
            //CommandManager[] cm = {Commander.EXIT, Commander.BACK};
            //this.d.append(new ChoiceGroup("Choose", cm));
            this.d.append(Commander.ANY_KEY);
            return this.d;
        }
        return this.d;
    }
    
/**************************************************************************************************
 * parent class implemented methods:
**************************************************************************************************/
    
/**************************************************************************************************
 * interface implemented methods:
**************************************************************************************************/
}