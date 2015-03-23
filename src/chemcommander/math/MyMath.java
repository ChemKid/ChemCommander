/**************************************************************************************************
  chemcommander/math/Math.java
  @author: Jan Chara
  date:
  description: 
**************************************************************************************************/

package chemcommander.math;


public final class MyMath
{
/**************************************************************************************************
 * variables declaration
**************************************************************************************************/

/**************************************************************************************************
 * constructors
**************************************************************************************************/

/**************************************************************************************************
 * main method:
**************************************************************************************************/

    public static void main(String[] args) {
        long time;
        int l;
        int[] num;
        try {
            l = args.length;
            num = new int[l];
            for (int j=0; j<l; j++) {
                num[j] = Integer.parseInt(args[j]);
            }
            System.out.println("gcd(" + num + ") = " + gcd(num[0], num[1]));
            System.out.println("lcm(" + num + ") = " + lcm(num[0], num[1]));
            int i1 = Integer.MAX_VALUE;
            //int i1 = Integer.MIN_VALUE;
            //int i2 = Integer.MAX_VALUE;
            int i2 = Integer.MIN_VALUE;
            //System.out.println("lcm(" + i1 + ", " + i2 + ") = " + lcm(i1, i2+1));
        } catch (Throwable t) {
            System.out.println("error in method main:");
            System.out.println(t);
        }
    }


/**************************************************************************************************
 * overriding or implementing of parent or overloaded methods
**************************************************************************************************/

/**************************************************************************************************
 * own methods:
**************************************************************************************************/

    /**
     * the greatest common divisor of given integers
     * @param num array of integers
     * @return integer denominator
     * @throws IllegalArgumentException if num is null or empty
     */
    public static int gcd(int[] num) throws IllegalArgumentException {
        int l;
        if (null == num || 0 == (l = num.length)) {
            throw new IllegalArgumentException("integer array parametr must not be null or empty");
        }
        int gcd,z,r;
        gcd = z = r = num[0];
        for (int i = 1; i < l; i++) {
            if (1 == gcd || (-1) == gcd) return gcd;
            if (num[i] < gcd) {
                z = gcd;
                gcd = num[i];
            }
            else {
                z = num[i];
            }
            while (0 != (r = z % gcd)) {
                z = gcd;
                gcd = r;
            }
        }
        return gcd;
    }

    /**
     * greatest common divisor of two integers
     * @param num1
     * @param num2
     * @return integer denominator
     * @throws ArithmeticException when num1 and num2 are both 0
     */
    public static int gcd(int num1, int num2) {
        if (0 == num1) {
            if (0 == num2) {
                throw new ArithmeticException("cannot compute gcd(" + num1 + ", " + num2 + ")!");
            }
            else return num2;
        }
        else if (0 == num2) {
            return num1;
        }
        int tmp;
        while (num2 != 0) {
            tmp = num1;
            num1 = num2;
            num2 = tmp % num2;
        }
        return num1;
    } // gcd(int, int)

    /**
     * greatest common divisor of two long type numbers
     * @param num1
     * @param num2
     * @return long type denominator
     */
    public static long gcd(long num1, long num2) {
        if (0 == num1) {
            if (0 == num2) {
                throw new ArithmeticException("cannot compute gcd(" + num1 + ", " + num2 + ")!");
            }
            else return num2;
        }
        else if (0 == num2) {
            return num1;
        }
        long tmp;
        while (num2 != 0) {
            tmp = num1;
            num1 = num2;
            num2 = tmp % num2;
        }
        return num1;
    } // gcd(long, long)
    
    /**
     * lowest common multiplier of two integers
     * @param num1
     * @param num2
     * @return zero one of the num1 or num2 are zero
     * @throws OverflowException 
     * @throws ArithmeticException when both num1 and num2 are zero
     */
    public static final int lcm(int num1, int num2) throws OverflowException {
        int gcd = gcd(num1, num2);
        return multiply(num1 / gcd, num2);
    } // lcm(int, int)

    /**
     * lowest common multiplier of two long type numbers
     * @param num1
     * @param num2
     * @return
     * @throws OverflowException 
     */
    public static final long lcm(long num1, long num2) throws OverflowException {
        long gcd = gcd(num1, num2);
        return multiply(num1 / gcd, num2);
    } // lcm(long, long)
    
    /**
     * lowest common multiplier of two MyNumber type numbers
     * @param num1
     * @param num2
     * @return 
     */
    public static final MyNumber lcm(final MyNumber num1, final MyNumber num2) {
        return null;
    } // lcm(MyNumber, MyNumber)
    
    /* vrátí součet 2 čísel typu int; pokud dojde k přetečení hodí chybu */
    public static int add(int i1, int i2) throws OverflowException {
    	if ((0 < i1 && (Integer.MAX_VALUE - i1) < i2) || (0 > i1 && (Integer.MIN_VALUE - i1) > i2 )) {
            throw new OverflowException("integer overflow occured!");
    	}
    	return i1 + i2; //// test udelat podobne jako v metode multiply
    } // add(int, int)
    
    
    /* vrátí součet 2 čísel typu long; pokud dojde k přetečení hodí chybu */
    public static long add(long i1, long i2) throws OverflowException {
    	if ((0 < i1 && (Long.MAX_VALUE - i1) < i2) || (0 > i1 && (Long.MIN_VALUE - i1) > i2 )) {
            throw new OverflowException("integer overflow occured!");
    	}
    	return i1 + i2;
    } // add(long, long)
    
    
    /* vrátí součin 2 čísel typu int; pokud dojde k přetečení hodí chybu */
    public static int multiply(int i1, int i2) throws OverflowException {
    	if (0 == i1 || 0 == i2) return 0;
    	if (1 == i1) return i2;
    	if (1 == i2) return i1;
    	int i = i1 * i2;
    	if (i / i1 == i2) return i;
        throw new OverflowException("integer overflow occured!");
    } // multiply(int, int)
    
    
    /* vrátí součin 2 čísel typu long; pokud dojde k přetečení hodí chybu */
    public static long multiply(long l1, long l2) throws OverflowException {
    	if (0 == l1 || 0 == l2) return 0;
    	if (1 == l1) return l2;
    	if (1 == l2) return l1;
    	long l = l1 * l2;
    	if (l / l1 != l2) throw new OverflowException("long overflow occured!");
    	return l;
    } // multiply(int, int)
    
    
    public static int abs(int i) throws OverflowException {
    	i = Math.abs(i);
    	if (0 > i) {
            throw new OverflowException("integer overflow occured!");
    	}
    	return i;
    } // abs(int)
    
    
    public static long abs(long l) throws OverflowException {
    	l = Math.abs(l);
    	if (0 > l) {
            throw new OverflowException("long overflow occured!");
    	}
    	return l;
    } // abs(long)
    
    
    public static int convertToInt(long n) throws Throwable {
    	int i = (int)n;
    	if (i != n) throw new Throwable("integer overflow occured!");
    	return i;
    } // convertToInt(long)
    
    
    public static int convertToInt(double n) throws OverflowException {
    	int i = (int)n;
    	if (i != n) throw new OverflowException("Overflow when converting " + n + " (double) to int!");
    	return i;
    } // convertToInt(double)

/**************************************************************************************************
 * parent class implemented methods:
**************************************************************************************************/
    
/**************************************************************************************************
 * interface implemented methods:
**************************************************************************************************/
}
