/**************************************************************************************************
  chemcommander/math/Number.java
  @author: Jan Chara
  date:
  description: 
**************************************************************************************************/

package chemcommander.math;

import static chemcommander.math.MyMath.gcd;
import java.text.StringCharacterIterator;

/**
 *
 * @author honza
 */
public class MyNumber extends java.lang.Number
{
/**************************************************************************************************
 * variables declaration
**************************************************************************************************/
    
    public static final char MINUS = '-';
    public static final char POINT = '.';
    public static final char EXPONENT = 'e';
    public static final char SLASH = '/';
    public static final MyNumber ZERO = new MyNumber(0);
    public static final MyNumber ONE = new MyNumber(1);
    
    private int wholePart;
    private int nominator;
    private int denominator;
    private int exponent;
    private int sign;
    private int exponentSign;
    
/**************************************************************************************************
 * constructors
**************************************************************************************************/

    private MyNumber() {
    } // MyNumber()
    
    public MyNumber(int num) {
        this.sign = (num >= 0) ? 1 : (-1);
        this.wholePart = MyMath.abs(num);
        this.nominator = 0;
        this.denominator = 1;
        this.exponent = 0;
        this.exponentSign = 1;
    } // MyNumber(int)
    
    public MyNumber(MyNumber num) {
        this.sign = num.sign;
        this.wholePart = num.wholePart;
        this.nominator = num.nominator;
        this.denominator = num.denominator;
        this.exponent = num.exponent;
        this.exponentSign = num.exponentSign;
    } // MyNumber(MyNumber)
    
    public MyNumber(String number) throws Throwable { // ošetřit velikost exponentu!!!
        char prev = '\u0000';
        int x = 0;
        int sign = 1, wholePart = 0, nominator = 0, denominator = 0, exponent = 0, exponentSign = 1;
        boolean start = true, nonNumeral = true, wholePartOK = true, exponentFixed = false, pointFixed = false, slashed = false, doubleSlashed = false, nominatorOK = false, denominatorOK = false;
        StringCharacterIterator it = new StringCharacterIterator(number);
        for (char c = it.first(); c != StringCharacterIterator.DONE; c = it.next()) {
            if (Character.isDigit(c)) {
                if (nonNumeral) nonNumeral = false;
                if (exponentFixed) exponent = MyMath.add(Character.digit(c, 10), MyMath.multiply(exponent, 10));
                else if (pointFixed) {
                    nominator = MyMath.add(Character.digit(c, 10), MyMath.multiply(nominator, 10));
                    denominator = MyMath.multiply(denominator, 10);
                }
                else if (doubleSlashed) denominator = MyMath.add(Character.digit(c, 10), MyMath.multiply(denominator, 10));
                else if (slashed) denominator = MyMath.add(Character.digit(c, 10), MyMath.multiply(denominator, 10));
                else if (nominatorOK) nominator = MyMath.add(Character.digit(c, 10), MyMath.multiply(nominator, 10));
                else if (denominatorOK) denominator = MyMath.add(Character.digit(c, 10), MyMath.multiply(denominator, 10));
                else if (wholePartOK) wholePart = MyMath.add(Character.digit(c, 10), MyMath.multiply(wholePart, 10));
            }
            else if (MyNumber.MINUS ==c) {
                if (start) sign = -1;
                else if ('e' == prev) exponentSign = -1;
                else throw new InvalidNumberFormatException(/*command, startPosition, command.getPosition()*/);
                nonNumeral = true;
            }
            else if (MyNumber.POINT ==c) {
                if (pointFixed) throw new InvalidNumberFormatException(/*command, startPosition, command.getPosition()*/);
                if (nonNumeral) throw new InvalidNumberFormatException(/*command, startPosition, command.getPosition()*/);
                if (slashed || doubleSlashed) throw new InvalidNumberFormatException(/*command, startPosition, command.getPosition()*/);
                if (exponentFixed) throw new InvalidNumberFormatException(/*command, startPosition, command.getPosition()*/);
                pointFixed = true;
                nonNumeral = true;
                denominator = 1;
            }
            else if (MyNumber.EXPONENT == c) {
                if (exponentFixed) throw new InvalidNumberFormatException(/*command, startPosition, command.getPosition()*/);
                if (nonNumeral) throw new InvalidNumberFormatException(/*command, startPosition, command.getPosition()*/);
                exponentFixed = true;
                nonNumeral = true;
                nominatorOK = false;
            }
            else if (MyNumber.SLASH ==c) {
                if (doubleSlashed) throw new InvalidNumberFormatException(/*command, startPosition, command.getPosition()*/);
                if (nonNumeral) throw new InvalidNumberFormatException(/*command, startPosition, command.getPosition()*/);
                if (slashed) {
                    doubleSlashed = true;
                    nominator = denominator;
                    denominator = 0;
                }
                else {
                    slashed = true;
                }
            }
            else {
                System.out.println("zde!");
                //it.previous();
                break;
                //throw new Throwable("Invalid number format!");
            }
            if (start) start = false;
            else prev = c;
            x++;
        }
        if (slashed && !doubleSlashed) {
            nominator = wholePart;
            wholePart = 0;
        }
        if (nonNumeral) throw new InvalidNumberFormatException(/*command, startPosition, command.getPosition()*/);
        this.wholePart = wholePart;
        this.nominator = nominator;
        this.denominator  = (0 == nominator) ? 1: denominator;
        this.exponent = exponent;
        this.sign = sign;
        this.exponentSign = exponentSign;
    } // MyNumber(ChemCommand)
    
/**************************************************************************************************
 * main method:
**************************************************************************************************/

    public static void main(String[] args) throws Throwable {
        long time;
        MyNumber n;
        String s = args[0].trim();
        
        try {
            MyNumber n1 = new MyNumber(args[0].trim());
            MyNumber n2 = new MyNumber(args[1].trim());
            System.out.println("start!");
            time = System.currentTimeMillis();
            n = MyNumber.gcd(n1, n2);
            //n = new MyNumber(s);
            //System.out.println("time elapsed = " + (System.currentTimeMillis() - time) + " ms\n");
            time = System.currentTimeMillis() - time;
            System.out.println("n1 = " + n1 + "\tn2 = " + n2);
            System.out.println("gcd(n1, n2) = " + n);
            System.out.println("time elapsed: " + time + " ms");
            //System.out.println(n.toString());
            //System.out.println(n.toFraction().toString());
            //System.out.println(n.doubleValue());
            //System.out.println(n.intValue());
	} catch (Throwable t) {
            System.out.println(t.getMessage());
        }
    }
    
/**************************************************************************************************
 * own methods:
**************************************************************************************************/
    
    /**
     * greatest common divisor of two MyNumber type numbers
     * @param num1
     * @param num2
     * @return 
     * @throws ArithmeticException if both num1.equals(MyNumber.ZERO) and num2.equals(MyNumber.ZERO)
     */
    public static final MyNumber gcd(final MyNumber num1, final MyNumber num2) {
        if (MyNumber.ZERO.equals(num1)) {
            if (MyNumber.ZERO.equals(num2)) {
                throw new ArithmeticException("Cannot compute MyNumber.gcd(" + num1 + ", " + num2 + ")!");
            }
            else {
                return new MyNumber(num2);
            }
        }
        else if (MyNumber.ZERO.equals(num2)) {
            return new MyNumber(num1);
        }
        MyNumber num = new MyNumber();
        num.nominator = MyMath.gcd(num1.nominator, num2.nominator);
        num.denominator = MyMath.lcm(num1.nominator, num2.nominator);
        //num.exponent = MyMath.lcm(num1.exponent , num2.exponent);
        return num;
    } // gcd(MyNumber, MyNumber)
    
    /*public Fraction toIntFraction() throws Throwable {
        int i = MyMath.convertToInt(Math.pow(10, this.exponent));
        if (1 == this.exponentSign) {
            return new Fraction(this.wholePart, MyMath.multiply(this.nominator, i), this.denominator, this.sign);
	}
	return new Fraction(this.wholePart, this.nominator, MyMath.multiply(this.denominator, i), this.sign);
    } // toIntFraction()*/
    
/**************************************************************************************************
 * parent class implemented methods:
**************************************************************************************************/
    
    @Override
    public final String toString() {
        String s = "";
        s += ((this.sign == 1) ? "" : "-") + this.wholePart + "/" + this.nominator + "/" + this.denominator + "e" + ((this.exponentSign == 1) ? "" : "-") + this.exponent;
        return s;
    } // toString()
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (false == (o instanceof MyNumber)) return false;
        MyNumber n = (MyNumber)o;
        if (false == (this.wholePart == n.wholePart && this.sign == n.sign)) return false;
        if (false == (this.nominator == n.nominator && this.denominator == n.denominator)) return false;
        if (false == (this.exponent == n.exponent && this.exponentSign == n.exponentSign)) return false;
        return true;
    }
    
    @Override
    public int hashCode() {
        long l = Double.doubleToLongBits(this.doubleValue());
        return 17 * (int)(l ^ (l >>> 32));
    }

    @Override
    public int intValue() {
        if (0 != nominator) {
            throw new ArithmeticException("cannot convert to integer!");
        }
        int i = MyMath.convertToInt(Math.pow(10, this.exponent));
        if (1 == this.exponentSign) {
            return this.sign * MyMath.multiply(this.wholePart, i);
        }
        return this.sign * MyMath.convertToInt((double) this.wholePart / i);
    } // intValue()

    @Override
    public long longValue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    } // longValue()

    @Override
    public float floatValue() {
        return this.sign * (((float)this.wholePart + ((float)this.nominator / (float)this.denominator)) * (float)Math.pow(10, this.exponentSign*this.exponent));
    } // floatValue()

    @Override
    public double doubleValue() {
        return this.sign * (((double)this.wholePart + ((double)this.nominator / (double)this.denominator)) * Math.pow(10, this.exponentSign*this.exponent));
    } // doubleValue()
    
/**************************************************************************************************
 * interface implemented methods:
**************************************************************************************************/
}
	
	
	
	
