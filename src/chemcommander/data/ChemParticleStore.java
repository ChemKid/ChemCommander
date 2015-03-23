/**************************************************************************************************
  chemcommander/data/ChemParticleStore.java
  @author: Jan Chara
  date:
  description: 
**************************************************************************************************/

package chemcommander.data;

import chemcommander.chem.Atom;
import chemcommander.chem.ChemFormula;
import chemcommander.chem.ChemParticle;
import chemcommander.chem.MolecularParticle;
import chemcommander.io.TerminalScanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Iterator;


/**
 *
 * @author honza
 */
public class ChemParticleStore
{
/**************************************************************************************************
 * variables declaration
**************************************************************************************************/
    
    private String name = "particles.cpt";
    private String dir = System.getProperty("user.dir");
    private File file;
    private FileInputStream fis;
    private FileOutputStream fos;
    
    private static HashMap<String, ChemParticle> byName = new HashMap();
    private static HashMap<String, ChemParticle> bySymbol = new HashMap();
    
    static {
        bySymbol.put("H", Atom.H);
        bySymbol.put("O", Atom.O);
        bySymbol.put("C", Atom.C);
        bySymbol.put("N", Atom.N);
    }
    
/**************************************************************************************************
 * constructors
**************************************************************************************************/
    
    public ChemParticleStore() {
        this.file = new File(this.name);
    }
    
/**************************************************************************************************
 * main method:
**************************************************************************************************/

    public static void main(String[] args) {
        ChemParticleStore cps = new ChemParticleStore();
        try {
            //System.out.println("compiled ok!");
            cps.create();
            cps.addToMemory("U", "Uranium", new Atom(98, 356.0f));
            cps.addToMemory("H", "Hydrogen", Atom.H);
            cps.addToMemory("O", "Oxygen", Atom.O);
            cps.addToMemory("N", "Nitrogen", Atom.N);
            cps.addToMemory("C", "Carbon", Atom.C);
            System.out.println("Particles added succesfully!");
            cps.save();
            System.out.println("Saved successfully!");
            cps.load();
            System.out.println("Loaded successfully!");
        } catch(Throwable t) {
            System.out.println("Particle could not be inserted!");
            System.out.println(t.getMessage());
        }
        String s = "";
        int loopCount = 0;
        while (!"exit".equals(s)) {
            System.out.println("zadej prvek:");
            s = TerminalScanner.readLine();
            loopCount++;
            if (10 < loopCount) {
                System.out.println("Max loop count reached!");
                break;
            }
            else {
                //System.out.println("elem: " + cps.getBySymbol(s));
                try {
                    ChemFormula f = ChemFormula.parseFormula(s);
                    System.out.println(f);
                    System.out.println(cps.getParticle(f));
                } catch(Throwable t) {
                    System.out.println("Chyba!");
                    System.out.println(t.toString());
                }
            }
        }
    }
    
/**************************************************************************************************
 * overriding or implementing of parent or overloaded methods
**************************************************************************************************/
    
/**************************************************************************************************
 * own methods:
**************************************************************************************************/
    
    public MolecularParticle getParticle(final ChemFormula form) {
        Object o = null;
        ChemFormula.Component c = null;
        int i = 0;
        for (Iterator it = form.getComponent().iterator(); it.hasNext();) {
            o = it.next();
            System.out.println("run " + ++i);
            if (o instanceof ChemFormula.Component) {
                c = (ChemFormula.Component)o;
                System.out.println("parsing component: ");
                System.out.println("symbol: " + this.getBySymbol(c.getSymbol()) + " x " + c.getkoef());
            }
            else if (o instanceof ChemFormula) {
                
                System.out.println("parsing formula: ");
                System.out.println(this.getParticle((ChemFormula)o));
            }
            else {
                System.out.println("wtf: " + o.getClass());
            }
        }
        System.out.println("parsing done!");
        return null;
    }
    
    private void open() throws Throwable {
        this.fis = new FileInputStream(this.file);
        this.fos = new FileOutputStream(this.file);
    }
    
    private void close() throws Throwable {
        this.fis.close();
        this.fos.close();
    }
    
    public void create() throws Throwable {
        if (this.file.exists()) {
            System.out.println("File already exists!");
            return;
        }
        this.file.createNewFile();
        System.out.println("File created!");
    }
    
    public void save() throws Throwable {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(this.file));
        oos.writeObject(ChemParticleStore.bySymbol);
        oos.writeObject(ChemParticleStore.byName);
        oos.close();
    }
    
    public void load() throws Throwable {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(this.file));
        ChemParticleStore.bySymbol = (HashMap)ois.readObject();
        ChemParticleStore.byName = (HashMap)ois.readObject();
        ois.close();
        /*FileInputStream fis = new FileInputStream(this.file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        //this.open();
        int c;
        while (true) {
            try {
                this.addToMemory((String)ois.readObject(), (String)ois.readObject(), (ChemParticle)ois.readObject());
            } catch (EOFException e) {
                System.out.println("EOF");
                break;
            } catch (Throwable t) {
                System.out.println("chyba: " + t.toString() + "!");
                break;
            }
        }
        ois.close();
        //this.close();*/
    }
    
    private void addToMemory(String symbol, String name, ChemParticle p) {
        System.out.println("pridavam + " + symbol);
        ChemParticleStore.bySymbol.put(symbol, p);
        ChemParticleStore.byName.put(name, p);
    }
    
    public void add(String symbol, String name, ChemParticle p) throws Throwable {
        //this.open();
        FileOutputStream fos = new FileOutputStream(this.file, true);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(symbol);
        oos.writeObject(name);
        oos.writeObject(p);
        oos.close();
        //this.close();
    }
    
    public ChemParticle getByName(String name) {
        return ChemParticleStore.byName.get(name);
    }
    
    public ChemParticle getBySymbol(String symbol) {
        return ChemParticleStore.bySymbol.get(symbol);
    }
    
/**************************************************************************************************
 * parent class implemented methods:
**************************************************************************************************/
    
/**************************************************************************************************
 * interface implemented methods:
**************************************************************************************************/
}