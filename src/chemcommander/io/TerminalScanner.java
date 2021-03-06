/**************************************************************************************************
  chemcommander/io/TerminalReader.java
  @author: Jan Chara
  date:
  description: 
**************************************************************************************************/

package chemcommander.io;

import java.util.Scanner;


/**
 *
 * @author honza
 */
public class TerminalScanner
{
/**************************************************************************************************
 * variables declaration
**************************************************************************************************/
    
    private Scanner scanner;
    
    public static final Scanner in =  new Scanner(System.in);
    
/**************************************************************************************************
 * constructors
**************************************************************************************************/
    
    private TerminalScanner() {
        this.scanner = new Scanner(System.in);
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
    
    public TerminalScanner getInstance() {
        return new TerminalScanner();
    }
    
    public static String readLine() {
        return TerminalScanner.in.nextLine();
    }
    
/**************************************************************************************************
 * parent class implemented methods:
**************************************************************************************************/
    
/**************************************************************************************************
 * interface implemented methods:
**************************************************************************************************/
}