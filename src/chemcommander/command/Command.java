/**************************************************************************************************
  chemcommander/command/Command.java
  @author: Jan Chara
  date: 
  description: 
**************************************************************************************************/

package chemcommander.command;


/**
 *
 * @author honza
 */
public class Command
{
/**************************************************************************************************
 * variables declaration
**************************************************************************************************/
    
    private String label;
    private CommandManager commandManager;
    
    public static final Command YES = new Command("Y");
    public static final Command NO = new Command("N");
    
/**************************************************************************************************
 * constructors
**************************************************************************************************/
    
    public Command(String label) {
        this.label = label;
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
    
    public CommandManager getCommandManager() {
        return this.commandManager;
    }
    
    public void addCommandManager(CommandManager commandManager) {
        this.commandManager = commandManager;
    }
    
/**************************************************************************************************
 * parent class implemented methods:
**************************************************************************************************/
    
/**************************************************************************************************
 * interface implemented methods:
**************************************************************************************************/
}