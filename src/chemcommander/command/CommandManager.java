/**************************************************************************************************
  chemcommander/command/Command.java
  @author: Jan Chara
  date: 
  description: 
**************************************************************************************************/

package chemcommander.command;

import chemcommander.ui.DisplayableManager;


/**
 * 
 * @author honza
 */
public abstract class CommandManager
{
/**************************************************************************************************
 * variables declaration
**************************************************************************************************/
    
    Command command;
    DisplayableManager dm;
    
/**************************************************************************************************
 * constructors
**************************************************************************************************/
    
    /**
     * creates a new command manager object for a command
     * @param command - command to be managed
     */
    public CommandManager(Command command) {
        this.command = command;
        command.addCommandManager(this);
    }
    
/**************************************************************************************************
 * main method:
**************************************************************************************************/

    public static void main(String[] args) {
        System.out.println("MIDlet says hello!");
    }

/**************************************************************************************************
 * overriding or implementing of parent or overloaded methods
**************************************************************************************************/
    
/**************************************************************************************************
 * own methods:
**************************************************************************************************/
    
    /**
     * defines an action associated to this instance
     */
    public abstract void getAction(DisplayableManager dm); //// arg -> CommandListener
    
    /**
     * 
     * @return - MyCommand associated to this instance of CommandManager
     */
    public Command getCommand() {
        return this.command;
    }
    
/**************************************************************************************************
 * parent class implemented methods:
**************************************************************************************************/
    
/**************************************************************************************************
 * interface implemented methods:
**************************************************************************************************/
}