package bit;

/**
 * @author Kyle Reddy
 * STUDENTID#: 001456693
 * ICSI 404 PROJECT 3: RIPPLE ADDER
 **/
public class RippleAdder {
    /**
     * @param a First Addend
     * @param b Second Addend
     * @return sum
     * @throws TestException if invalid value
     */
    public static Longword add(Longword a, Longword b) throws TestException {

        //a and b have same sign
        if ((a.sequence[0].xor(b.sequence[0])).getValue() == 0) {
            Longword sum = ripple(a, b);
            sum.sequence[0] = a.sequence[0];
            return sum;
        }
        Longword first = complement_check(a);
        Longword second = complement_check(b);
        Longword ones = new Longword();
        ones.set(1);
        Longword sum = ripple(first, second);

        //undo 2's complement back to signed bit
        if (greater_abs_negative(a, b) == 1) {
            return sum.not();
        }

        return ripple(sum, ones);

    }

    /**
     *
     * @param x Longword 1
     * @param y Longword 2
     * @return sum or difference using ripple logic
     */
    public static Longword ripple(Longword x, Longword y) {
        Longword sum = new Longword();
        Bit carry = new Bit(0);
        for (int i = 31; i > 0; i--) {
            sum.sequence[i] = carry.xor(x.sequence[i].xor(y.sequence[i]));
            carry = (x.sequence[i].and(y.sequence[i])).or(((x.sequence[i].xor(y.sequence[i])).and(carry)));
        }
        return sum;
    }

    /**
     * @param a Minuend longword
     * @param b Subtrahend
     * @return Difference Longword
     * @throws TestException for invalid values
     */
    public static Longword subtract(Longword a, Longword b) throws TestException {

        Longword negate = new Longword();
        negate.copy(b);
        negate.sequence[0].toggle();
        return add(a, negate);
    }

    /**
     * @param a longword
     * @param b longword * @return 1 if  absolute value of first longword > absolute value of second longword
     */
    public static int greater_abs_negative(Longword a, Longword b) {
        Longword first = new Longword();
        Longword second = new Longword();
        if (a.sequence[0].getValue() == 1) {
            first.copy(a);
            second.copy(b);
        } else {
            first.copy(b);
            second.copy(a);
        }
        for (int i = 1; i < 32; i++) {
            if (first.sequence[i].getValue() > second.sequence[i].getValue()) {
                return 1;
            }
            if (first.sequence[i].getValue() < second.sequence[i].getValue()) {
                break;
            }
        }
        return 0;
    }

    /**
     * @param a longword
     * @return longword that returns a or its complement if negative
     */
    public static Longword complement_check(Longword a) {
        Longword helper = new Longword();
        Longword ones = new Longword();
        ones.set(1);
        if (a.sequence[0].getValue() == 1) {
            helper.copy(a);
            helper.not();
            ripple(helper, ones);
        } else {
            helper.copy(a);
        }
        return helper;
    }


}