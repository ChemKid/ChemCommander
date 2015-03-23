/**************************************************************************************************
  chemcommander/install/Installer.java
  @author: Jan Chara
  date:
  description: 
**************************************************************************************************/

package chemcommander.install;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 *
 * @author honza
 */
public class InstallManager
{
/**************************************************************************************************
 * variables declaration
**************************************************************************************************/
    
    public final static String VERSION = "version";
    private int installedVersion;
    
/**************************************************************************************************
 * constructors
**************************************************************************************************/
    
    private InstallManager() {
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
    
    public static InstallManager getInstaller() {
        return new InstallManager();
    }
    
    public int install() throws InstallationException {
        int v = this.getInstalledVersion();
        return 0;
    }
    
    public int getInstalledVersion() {
        File f = new File(InstallManager.VERSION);
        if (false == f.exists()) {
            System.out.println("Not installed yet!");
            return -1;
        }
        int v;
        try {
            FileInputStream fr = new FileInputStream(f);
            DataInputStream dis = new DataInputStream(fr);
            return dis.readInt();
        } catch (IOException e) {
            System.out.println("Corrupted installation detected. Reinstall?");
        }
        return -1;
    }
    
/**************************************************************************************************
 * parent class implemented methods:
**************************************************************************************************/
    
/**************************************************************************************************
 * interface implemented methods:
**************************************************************************************************/
}