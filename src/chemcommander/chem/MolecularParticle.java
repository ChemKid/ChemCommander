/**************************************************************************************************
  chemcommander/chem/MolecularParticle.java
  @author: Jan Chara
  date:
  description: 
**************************************************************************************************/

package chemcommander.chem;

import chemcommander.data.ChemParticleStore;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author honza
 */
public class MolecularParticle extends ChemParticle
{
/**************************************************************************************************
 * variables declaration
**************************************************************************************************/
    
    public static final MolecularParticle H2O;
    public static final MolecularParticle O2;
    public static final MolecularParticle NH3;
    
    private HashMap<ChemParticle, Float> component;
    
    public class Component {
        ChemParticle chemParticle;
        float koef;
    }
    
    static {
        HashMap<ChemParticle, Float> m;
        /* H2O */
        m = new HashMap(); m.put(Atom.H, new Float(2)); m.put(Atom.O, new Float(1));
        H2O = new MolecularParticle(); H2O.component = m; H2O.weight();
        /* O2 */
        m = new HashMap(); m.put(Atom.O, new Float(2));
        O2 = new MolecularParticle(); O2.component = m; O2.weight();
        /* NH3 */ 
        m = new HashMap(); m.put(Atom.N, new Float(1)); m.put(Atom.H, new Float(3));
        NH3 = new MolecularParticle(); NH3.component = m; NH3.weight();
    }
    
/**************************************************************************************************
 * constructors
**************************************************************************************************/

    private MolecularParticle() {
        this.component = new HashMap();
    }
    
    public MolecularParticle(HashMap<ChemParticle, Float> component) {
        if (null == component) {
            return;
        }
        this.component = new HashMap(component.size());
        ChemParticle p;
        Float k;
        for (Iterator it = component.entrySet().iterator(); it.hasNext();) {
            Map.Entry e = (Map.Entry)it.next();
            if (null == (p = (ChemParticle)e.getKey()) || null == (k = (Float)e.getValue())) {
                this.component = null;
                return;
            }
            //this.component.put(new ChemParticle(p), new Float(k));
        }
    }
    
    public MolecularParticle(final MolecularParticle p) {
        this(p.component);
    }
    
/**************************************************************************************************
 * main method:
**************************************************************************************************/

    public static void main(String[] args) {
        System.out.println("testing: " + MolecularParticle.class);
        System.out.println("Mr(H2O) = " + MolecularParticle.H2O.getMolarWeight());
        System.out.println("H2O toString(): " + MolecularParticle.H2O);
        System.out.println("O2 equals O2: " + MolecularParticle.O2.equals(MolecularParticle.O2));
        try {
            MolecularParticle p;
            ChemFormula f = ChemFormula.parseFormula(args[0]);
            System.out.println("parsing formula " + f);
            System.out.println(p = MolecularParticle.parse(f, new ChemParticleStore()));
            p.weight();
            System.out.println("mw = " + p.getMolarWeight());
        } catch(Throwable t) {
            System.out.println(t);
        }
    }
    
/**************************************************************************************************
 * own methods:
**************************************************************************************************/
    
    public static final MolecularParticle parse(final ChemFormula form, ChemParticleStore store) throws Throwable {
        Object o;
        ChemFormula.Component c;
        MolecularParticle part = new MolecularParticle();
        ChemParticle p;
        int i = 0;
        for (Iterator it = form.getComponent().iterator(); it.hasNext();) {
            o = it.next();
            System.out.println("run " + ++i);
            if (o instanceof ChemFormula.Component) {
                c = (ChemFormula.Component)o;
                System.out.println("parsing component: ");
                System.out.println("symbol: " + c.symbol + " x " + c.koef);
                p = store.getBySymbol(c.symbol);
                if (null == p) {
                    throw new Throwable("Particle " + c.symbol + " not found in the store!");
                }
                part.addParticle(store.getBySymbol(c.symbol), c.koef.floatValue());
            }
            else if (o instanceof ChemFormula) {
                System.out.println("parsing formula: ");
                System.out.println(MolecularParticle.parse((ChemFormula)o, store));
            }
            else {
                System.out.println("wtf: " + o.getClass());
            }
        }
        System.out.println("parsing done!");
        return part;
    }
    
    private void addParticle(ChemParticle part, Float koef) {
        Float f = this.component.get(part);
        if (null == f) {
            this.component.put(part, koef);
        }
        else {
            this.component.put(part, f.floatValue() + koef.floatValue());
        }
    }
    
    private void addParticle(MolecularParticle part, Float koef) {
        Object o;
        Float f;
        for (Iterator it = part.component.entrySet().iterator(); it.hasNext();) {
            o = it.next();
        } //// dodelat!
    }
    
    private void weight() {
        this.molarWeight = 0;
        for (Iterator it = this.component.entrySet().iterator(); it.hasNext();) {
            Map.Entry e = (Map.Entry)it.next();
            this.molarWeight += ((ChemParticle)e.getKey()).getMolarWeight() * ((Float)e.getValue());
        }
    }
    
    public Component[] components() {
        Component[] c = new Component[this.component.size()];
        int i = 0;
        for (Iterator it = this.component.entrySet().iterator(); it.hasNext(); i++) {
            Map.Entry e = (Map.Entry)it.next();
            c[i].chemParticle = (ChemParticle)e.getKey();
            c[i].koef = ((Float)e.getValue());
        }
        return c;
    }
    
/**************************************************************************************************
 * parent class implemented methods:
**************************************************************************************************/
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (false == (o instanceof MolecularParticle)) return false;
        MolecularParticle p = (MolecularParticle)o;
        return this.component.equals(p.component);
    }
    
    @Override
    public int hashCode() {
        int vysledek = 17;
        vysledek = 37 * vysledek + this.component.hashCode();
        vysledek = 37 * vysledek + Float.floatToIntBits(this.molarWeight);
        return vysledek;
    }
    
    @Override
    public Object clone() {
        return null;
    }
    
    @Override
    public String toString() {
        String s = "";
        s += this.component;
        return s;
    }
    
/**************************************************************************************************
 * interface implemented methods:
**************************************************************************************************/
}