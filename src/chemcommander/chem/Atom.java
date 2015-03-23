/**************************************************************************************************
  chemcommander/chem/Atom.java
  @author: Jan Chara
  date:
  description: 
**************************************************************************************************/

package chemcommander.chem;

/**
 *
 * @author honza
 */
public class Atom extends ChemParticle
{
/**************************************************************************************************
 * variables declaration
**************************************************************************************************/
    
    protected int p; // proton number
    protected float electronegativity;
    
    public static final Atom H = new Atom(1, 1.0079f);
    public static final Atom C = new Atom(6, 12.011f);
    public static final Atom O = new Atom(8, 15.9994f);
    public static final Atom N = new Atom(7, 14.0067f);
    public static final Atom He = new Atom(2, 4.0026f);
    public static final Atom Ne = new Atom(10, 20.179f);
    public static final Atom Ar = new Atom(18, 39.948f);
    public static final Atom Kr = new Atom(36, 83.80f);
    public static final Atom Xe = new Atom(54, 131.30f);
    public static final Atom Rn = new Atom(86, 222f);
    
/**************************************************************************************************
 * constructors
**************************************************************************************************/

    public Atom(int p, float molarWeight, float electronegativity) {
        super(molarWeight);
        this.p = p;
        this.electronegativity = electronegativity;
    }
    
    public Atom(int p, float molarWeight) {
        this(p, molarWeight, Float.NaN);
    }
    
    public Atom(final Atom a) {
        // this.set();
        this(a.p, a.molarWeight, a.electronegativity);
    }
    
    protected Atom() {
        super();
    }
    
/**************************************************************************************************
 * main method:
**************************************************************************************************/

    public static void main(String[] args) {
    }
    
/**************************************************************************************************
 * own methods:
**************************************************************************************************/
    
    public int getProtNumber() {
        return this.p;
    }
    
    public float getElectronegativity() {
        return this.electronegativity;
    }
    
/**************************************************************************************************
 * parent class implemented methods:
**************************************************************************************************/
    
    @Override
    public int hashCode() {
        return this.p;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) 
            return true;
        if (false == o instanceof Atom) 
            return false;
        Atom a = (Atom)o;
        if (this.p == a.p && this.molarWeight == a.p) 
            return true;
        return false;
    }
    
    @Override
    public String toString() {
        return "Atom [" + this.p + "]" + this.molarWeight;
    }
    
    @Override
    public Object clone() {
        Atom a = null;
        a = (Atom)super.clone();
        a.p = this.p;
        a.molarWeight = this.molarWeight;
        a.electronegativity = this.electronegativity;
        return a;
    }
    
/**************************************************************************************************
 * interface implemented methods:
**************************************************************************************************/
}