/**************************************************************************************************
  chemcommander/ui/ChoiceGroup.java
  @author: Jan Chara
  date:
  description: 
**************************************************************************************************/

package chemcommander.ui;

import chemcommander.command.CommandManager;
import chemcommander.io.TerminalScanner;
import java.util.ArrayList;


/**
 *
 * @author honza
 */
public class ChoiceGroup extends Item
{
/**************************************************************************************************
 * variables declaration
**************************************************************************************************/
    
    private ArrayList<CommandManager> commandManager;
    
/**************************************************************************************************
 * constructors
**************************************************************************************************/
    
    public ChoiceGroup(String label, CommandManager[] commandManager) {
        super(label);
        if (null == commandManager) {
            this.commandManager = null;
            return;
        }
        this.commandManager = new ArrayList();
        int length = commandManager.length;
        for (int i = 0; i < length; i++) {
            this.add(commandManager[i]);
        }
    }
    
    public ChoiceGroup(String label) {
        this(label, null);
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
    
    public void add(CommandManager cm) {
        if (null == this.commandManager) {
            this.commandManager = new ArrayList();
        }
        this.commandManager.add(cm);
    }
    
    private void getCommandAction(String s) {
        int size = this.commandManager.size();
        CommandManager cm;
        for (int i = 0; i < size; i++) {
            cm = this.commandManager.get(i);
            if (s.equals(cm.getCommand().toString())) {
                //cm.getAction(this);
                return;
            }
        }
    }
    
/**************************************************************************************************
 * parent class implemented methods:
**************************************************************************************************/
    
    @Override
    public void run() {
        System.out.println(this.label + ":");
        if (null == this.commandManager) {
            return;
        }
        int size = this.commandManager.size();
        for (int i = 0; i < size; i++) {
            CommandManager cm = this.commandManager.get(i);
            System.out.println(cm.toString());
        }
        String s = TerminalScanner.readLine();
    }
    
/**************************************************************************************************
 * interface implemented methods:
**************************************************************************************************/
}