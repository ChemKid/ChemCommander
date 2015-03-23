/**************************************************************************************************
  chemcommander/math/Fraction.java
  @author: Jan Chara
  date:
  description: 
**************************************************************************************************/

package chemcommander.math;

import java.io.Serializable;

/**
 *
 * @author honza
 */
public class Fraction extends java.lang.Number implements Serializable
{
/**************************************************************************************************
 * variables declaration
**************************************************************************************************/
    
    int nominator;
    int denominator;
    
    public static final Fraction ZERO = new Fraction();
    
/**************************************************************************************************
 * constructors
**************************************************************************************************/

    public Fraction(int nominator, int denominator) {
        if (0 == denominator) {
            throw new IllegalArgumentException("zero denominator");
        }
        this.nominator = nominator;
        this.denominator = denominator;
        this.shorten();
    }
    
    public Fraction(int nominator) {
        this(nominator, 1);
    }
    
    public Fraction() {
        this(0, 1);
    }
    
    public Fraction(String s) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
/**************************************************************************************************
 * main method:
**************************************************************************************************/

    public static void main(String[] args) {
    }
    
/**************************************************************************************************
 * own methods:
**************************************************************************************************/
    
    private void shorten() {
        int i = MyMath.gcd(this.nominator, this.nominator);
        if (1 == i) return;
        this.nominator /= i;
        this.denominator /= i;
    }
    
    public int compareTo(Fraction f) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
/**************************************************************************************************
 * parent class implemented methods:
**************************************************************************************************/
    
    @Override
    public boolean equals(Object o) {
        if (this == o) 
            return true;
        if (false == o instanceof Fraction)
            return false;
        Fraction f = (Fraction)o;
        if (this.nominator == f.nominator && this.denominator == f.denominator)
            return true;
        return false;
    }
    
    @Override
    public String toString() {
        return this.nominator + "/" + this.denominator;
    }
    
    @Override
    public int intValue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long longValue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public float floatValue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double doubleValue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
/**************************************************************************************************
 * interface implemented methods:
**************************************************************************************************/
}