/**************************************************************************************************
  chemcommander/chem/ChemParticle.java
  @author: Jan Chara
  date:
  description: 
**************************************************************************************************/

package chemcommander.chem;

import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author honza
 */
public abstract class ChemParticle implements Serializable, Cloneable
{
/**************************************************************************************************
 * variables declaration
**************************************************************************************************/
    
    protected float molarWeight;
    protected static final HashMap byName = new HashMap();
    protected static final HashMap bySymbol = new HashMap();
    
/**************************************************************************************************
 * constructors
**************************************************************************************************/
    
    public ChemParticle(final float molarWeight) {
        this.molarWeight = molarWeight;
    }
    
    public ChemParticle(final ChemParticle p) {
        this.molarWeight = p.molarWeight;
    }
    
    protected ChemParticle() {
        this(0f);
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
    
    public float getMolarWeight() {
        return this.molarWeight;
    }
    
    public static void add(String symbol, String name, ChemParticle p) {
        ChemParticle.byName.put(name, p);
        ChemParticle.bySymbol.put(symbol, p);
    }
    
    public ChemParticle getBySymbol(String symbol) {
        return (ChemParticle)ChemParticle.bySymbol.get(symbol);
    }
    
    public ChemParticle getByName(String name) {
        return (ChemParticle)ChemParticle.byName.get(name);
    }
    
/**************************************************************************************************
 * parent class implemented methods:
**************************************************************************************************/
    
    @Override
    public Object clone() {
        ChemParticle p = null;
        try {
            p = (ChemParticle)super.clone();
            p.molarWeight = this.molarWeight;
        } catch(CloneNotSupportedException e) {
            System.out.println(e.toString());
        }
        return p;
    }
    
/**************************************************************************************************
 * interface implemented methods:
**************************************************************************************************/
}