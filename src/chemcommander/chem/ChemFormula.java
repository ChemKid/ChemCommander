/**************************************************************************************************
 * chemcommander/chem/ChemFormula.java
 * @author: Jan Chara 
 * date: description:
**************************************************************************************************/

package chemcommander.chem;

import chemcommander.math.MyNumber;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author honza
 */
public class ChemFormula implements Cloneable {

/**************************************************************************************************
 * variables declaration
**************************************************************************************************/
    
    public static final char QUOTATION_MARK = '"';
    public static final char SEPARATOR = '*';
    public static final char OPEN_ROUND_BRACKET = '(';
    public static final char CLOSE_ROUND_BRACKET = ')';
    public static final char OPEN_SQUARE_BRACKET = '[';
    public static final char CLOSE_SQUARE_BRACKET = ']';

    MyNumber cm; // common multiplier
    MyNumber charge;
    MyNumber koef;
    ArrayList component;
    ChemFormula previous;

    public class Component {
        String symbol;
        MyNumber iso;
        MyNumber charge;
        MyNumber koef;
        
        @Override
        public String toString() {
            return symbol + "\t" + koef;
        }
        
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (false == (o instanceof Component)) return false;
            Component c = (Component)o;
            if (false == (this.symbol.equals(c.symbol) && this.iso.equals(c.iso))) return false;
            if (false == (this.charge.equals(c.charge) && this.koef.equals(c.koef))) return false;
            return true;
        }
        
        public String getSymbol() {
            return this.symbol;
        }
        
        public MyNumber getkoef() {
            return this.koef;
        }
    }

/**************************************************************************************************
 * constructors
**************************************************************************************************/
    
    private ChemFormula() {
        this.component = new ArrayList(5);
        try {
            this.cm = new MyNumber(1);
            this.charge = new MyNumber(0);
            this.koef = new MyNumber(1);
        } catch (Throwable t) {
            System.out.println("wtf");
        }
        this.previous = null;
    }
    
    public ChemFormula(final ChemFormula formula) {
        if (null == formula) return;
        this.charge = formula.charge;
        this.cm = formula.cm;
        this.koef = formula.koef;
        //this.previous 
    }

/**************************************************************************************************
 * main method:
**************************************************************************************************/
    
    public static void main(String[] args) {
        System.out.println("Testing ChemFormula class: ");
        String s;
        if (null == args || 0 == args.length) {
            s = null;
        }
        else {
            s = args[0];
        }
        //ChemFormula f = new ChemFormula(s);
        try {
            ChemFormula f1 = ChemFormula.parseFormula("H2O");
            System.out.println("\nformula H2O:\n" + f1);
            ChemFormula f = ChemFormula.parseFormula(s);
            System.out.println("\nformula " + s + ":\n" + f);
            System.out.println("f.equals(f1) = " + f.equals(f1));
        } catch(Throwable t) {
            System.err.println("invalid formula!\n" + t.toString());
        }
    }
    
/**************************************************************************************************
 * own methods:
**************************************************************************************************/
    
    public static ChemFormula parseFormula(String formula) throws Throwable {
        if (null == formula || 0 == formula.length()) {
            throw new Throwable("Null formula exception!");
        }
        ChemFormula form = new ChemFormula();
        ChemFormula prev = form;
        Component comp;
        StringCharacterIterator it = new StringCharacterIterator(formula);
        int i = 0;
        int loopCounter = 0;
        MyNumber n;
        boolean start = true;
        int lvl = 0;
        for (char c = it.first(); (c = it.current()) != CharacterIterator.DONE; /*c = it.next(),*/) {
            System.out.println("run " + ++i + " [" + (it.getIndex() + 1) + "]");
            if (start) {
                System.out.println("[" + (it.getIndex() + 1) + "] parsing cm:");
                form.cm = form.getNumber(it);
                start = false;
                System.out.println("cm = " + form.cm);
            }
            else if (ChemFormula.SEPARATOR == c) {
                if (0 == form.component.size()) throw new Throwable("Error when parsing formula " + formula + " at char no. " + (it.getIndex() + 1));
                System.out.println("[" + (it.getIndex() + 1) + "] separator detected");
                if (null == form.previous) {
                    prev = form.previous = new ChemFormula();
                    form.previous.add(form);
                }
                form = form.previous.add(new ChemFormula());
                start = true;
                it.next(); // presun o jednu pozici dale
            } else if (ChemFormula.OPEN_ROUND_BRACKET == c) {
                System.out.println("[" + (it.getIndex() + 1) + "] open bracket detected");
                lvl++;
                form = form.add(new ChemFormula());
                it.next();
            } else if (ChemFormula.CLOSE_ROUND_BRACKET == c) {
                System.out.println("[" + (it.getIndex() + 1) + "] close bracket detected");
                if (0 > --lvl) {
                    throw new Throwable("Error when parsing formula " + formula + " at char no. " + (it.getIndex() + 1));
                }
                it.next();
                System.out.println("[" + (it.getIndex() + 1) + "] parsing koef:");
                n = form.getNumber(it);
                form.koef = n;
                System.out.println("koef = " + form.koef);
                System.out.println(n);
                form = form.previous;
            }
            else {
                System.out.println("[" + (it.getIndex() + 1) + "] parsing component:");
                comp = form.getComponent(it); // lze bez promenne comp?
                form.component.add(comp);
                System.out.println("component = " + comp);
            }
            if (++loopCounter > 20) {
                System.out.println("loop terminated by loop counter!");
                break;
            }
            //System.out.println("Position = " + it.getIndex());
        }
        if (lvl > 0) throw new Throwable("Error when parsing formula " + formula + " at char no. " + (it.getIndex() + 1));
        if (0 == form.component.size()) throw new Throwable("Error when parsing formula " + formula + " at char no. " + (it.getIndex() + 1));
        System.out.println("parsing ok");
        return prev;
    }
    
    private Component getComponent(StringCharacterIterator it) throws Throwable {
        Component comp = new Component();
        System.out.print("[" + (it.getIndex() + 1) + "] parsing symbol: ");
        if ("".equals(comp.symbol = this.getSymbol(it))) {
            System.out.println();
            throw new Throwable("Error when parsing formula " + it.toString() + " at char no. " + (it.getIndex() + 1));
        }
        System.out.println(comp.symbol);
        System.out.print("[" + (it.getIndex() + 1) + "] parsing koef: ");
        comp.koef = this.getNumber(it);
        comp.iso = new MyNumber(-1);
        comp.charge = new MyNumber(0);
        System.out.println(comp.koef);
        return comp;
    }

    private MyNumber getNumber(StringCharacterIterator it) throws Throwable {
        String s = "";
        for (char c = it.current(); c != StringCharacterIterator.DONE; c = it.next()) {
            if (Character.isDigit(c) || MyNumber.MINUS == c || MyNumber.EXPONENT == c || MyNumber.SLASH == c || MyNumber.POINT == c) {
                s += c;
            } else {
                break;
            }
        }
        if ("".equals(s)) {
            return new MyNumber(1); /// vytvorit konstruktor MyNumber(int) !!!
        }
        return new MyNumber(s);
    }

    private String getSymbol(StringCharacterIterator it) throws Throwable {
        String s = "";
        boolean start = true;
        boolean anyCharAllowed = false;
        for (char c = it.current(); c != StringCharacterIterator.DONE; c = it.next(), start = false) {
            if (ChemFormula.QUOTATION_MARK == c) {
                if (start) {
                    anyCharAllowed = true;
                } else {
                    break;
                }
            } else if (anyCharAllowed) {
                s += c;
            } else if (Character.isUpperCase(c)) {
                if (start) {
                    s += c;
                } else {
                    break;
                }
            } else if (Character.isLowerCase(c)) {
                s += c;
            } else {
                break;
            }
        }
        return s;
    }

    private String getIso(StringCharacterIterator it) {
        String s = "";
        return s;
    }

    private String getCharge(StringCharacterIterator it) {
        String s = "";
        return s;
    }
    
    private ChemFormula add(ChemFormula f) {
        this.component.add(f);
        f.previous = this;
        return f;
    }
    
    public ArrayList getComponent() {
        return this.component;
    }

/**************************************************************************************************
 * parent class implemented methods:
**************************************************************************************************/
    
    @Override
    public String toString() {
        String s = "cm = " + this.cm + ", charge = " + this.charge + ", koef = " + this.koef + "\n";
        int size = this.component.size();
        Object o;
        for (int i = 0; i < size; i++) {
            s += this.component.get(i) + "\n";
        }
        return s;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (false == (o instanceof ChemFormula)) return false;
        ChemFormula f = (ChemFormula)o;
        System.out.println("comparing numbers: ");
        if (false == (this.cm.equals(f.cm) && this.koef.equals(f.koef) && this.charge.equals(f.charge))) return false;
        int size = f.component.size();
        if (false == (size == this.component.size())) return false;
        //if (this.component.equals(f.component)) return false; ///// pouzit toto?
        for (Iterator it = f.component.iterator(); it.hasNext();) {
            if (!this.component.contains(it.next())) return false;
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        int vysledek = 17;
        vysledek = 37 * vysledek + this.previous.hashCode();
        vysledek = 37 * vysledek + this.component.hashCode(); /// vytvorit hashCode pro this.component
        vysledek = 37 * vysledek + this.cm.hashCode();
        vysledek = 37 * vysledek + this.charge.hashCode();
        vysledek = 37 * vysledek + this.koef.hashCode();
        return vysledek;
    }
    
    @Override
    public Object clone() { // dodelat
        return null;
    }

/**************************************************************************************************
 * interface implemented methods:
**************************************************************************************************/
}
