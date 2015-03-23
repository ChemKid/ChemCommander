/**************************************************************************************************
  chemcommander/ChemCommander.java
  @author: Jan Chara
  date:
  description: 
**************************************************************************************************/

package chemcommander;

import chemcommander.data.ChemParticleStore;
import chemcommander.ui.Installer;
import chemcommander.ui.Preloader;

/**
 *
 * @author honza
 */
public class ChemCommander extends Commander
{
/**************************************************************************************************
 * variables declaration
**************************************************************************************************/
    
/**************************************************************************************************
 * constructors
**************************************************************************************************/

    public ChemCommander() {
    }
    
/**************************************************************************************************
 * main method:
**************************************************************************************************/

    public static void main(String[] args) {
        ChemCommander chemCommander = new ChemCommander();
        chemCommander.initialize();
        chemCommander.display(new Preloader(chemCommander));
        try {
            //Process p = Runtime.getRuntime().exec("cmd /c start cmd.exe");
            ChemParticleStore cps = new ChemParticleStore();
            cps.create();
        } catch(Throwable t) {
            System.out.println("Process cannot run!");
        }
        Installer i = new Installer(chemCommander);
        i.getDisplayable().execute();
        String s;
        try {
            /*while(false == "exit".equals(s = TerminalScanner.in.nextLine())) {
                System.out.println(TerminalScanner.in.nextLine());
            }*/
            //String moleculeString = args[0];
            //System.out.println("command: " + moleculeString);
            //MolecularParticle.fromString(moleculeString);
            //ChemCommander.readString();
        } catch(Throwable t) {
            System.out.println("invalid command!");
            System.out.println(t);
        }
        System.exit(0);
    }
    
/**************************************************************************************************
 * overriding or implementing of parent or overloaded methods
**************************************************************************************************/
    
/**************************************************************************************************
 * own methods:
**************************************************************************************************/
    
    protected void initialize() {
    }
    
/**************************************************************************************************
 * parent class implemented methods:
**************************************************************************************************/
    
/**************************************************************************************************
 * interface implemented methods:
**************************************************************************************************/
}