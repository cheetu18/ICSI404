package bit;

public class ALU {
    /**
     * @param operation 4 Bit array
     * @param a         first longword * @param b         second longword
     * @return Longword computed  with operation from 4 bit op code with a and b
     * @throws TestException if invalid op code provided ex. != (7 - 15)
     * @author Kyle Reddy
     * STUDENTID#:001456693
     */

    public static Longword ALUtest(Bit[] operation, Longword a, Longword b) throws TestException {
        Longword result;
        int i = eval_op_code(operation);
        int cycles = b.eval_byte(28);
        result = switch (i) {
            case 1 -> b;
            case 3, 5, 6 -> a.leftShift(0); //jump code doesn't actually calc
            case 7 -> Multiply.multiply(a, b);
            case 8 -> a.and(b);
            case 9 -> a.or(b);
            case 10 -> a.xor(b);
            case 11 -> a.not();
            case 12 -> a.leftShift(cycles);
            case 13 -> a.rightShift(cycles);
            case 14 -> RippleAdder.add(a, b);
            case 4, 15 -> RippleAdder.subtract(a, b);
            default -> throw new TestException("INVALID OPERATION CODE");
        };
        return result;
    }

    /**
     * @param operation array of 4 BITS to be calculated into binary
     * @return value of operation bits
     */
    public static int eval_op_code(Bit[] operation) {
        int val = 0;
        for (int i = 0; i < operation.length; i++) {
            val += operation[i].getValue() * Longword.binary_pow((3 - i));
        }
        return val;
    }


}


