package bit;

import java.util.Arrays;

/**
 * @author Kyle Reddy
 * STUDENTID#:001456693
 * PROJECT 2: LONGWORD
 **/
public class Longword implements ILongword {
    Bit[] sequence = new Bit[32];

    Longword() {
        for (int i = 0; i < 32; i++)
            this.sequence[i] = new Bit(0);
    }

    public Bit getBit(int i) {
        //return new Bit(this.sequence[i].getValue());
        return this.sequence[i];
    }

    public void setBit(int i, Bit value) {
        this.sequence[i] = value;
    }

    public Longword and(Longword other) {
        Longword longAND = new Longword();
        for (int i = 0; i < 32; i++) {
            longAND.sequence[i] = this.sequence[i].and(other.sequence[i]);
        }
        return longAND;
    }

    public Longword or(Longword other) {
        Longword longOR = new Longword();
        for (int i = 0; i < 32; i++) {
            longOR.sequence[i] = this.sequence[i].or(other.sequence[i]);
        }
        return longOR;

    }

    public Longword xor(Longword other) {
        Longword longXOR = new Longword();
        for (int i = 0; i < 32; i++) {
            longXOR.sequence[i] = this.sequence[i].xor(other.sequence[i]);
        }
        return longXOR;

    }

    public Longword not() {
        Longword longNOT = new Longword();
        for (int i = 0; i < 32; i++) {
            longNOT.sequence[i] = this.sequence[i].not();
        }
        return longNOT;
    }

    /**
     * @param amount number of shifts computed recursively
     *               each shift copies "this" to newword shifting accordingly
     */
    public Longword rightShift(int amount) {
        Longword newword = new Longword();
        newword.copy(this);
        if (amount > 0) {
            System.arraycopy(sequence, 0, newword.sequence, 1, 31);
            this.copy(newword);
            newword = rightShift(--amount);
        }

        newword.sequence[0].clear();
        return newword;

    }

    /**
     * @param amount number of shifts computed recursively
     *               each shift copies "this" to newword shifting accordingly
     **/
    public Longword leftShift(int amount) {
        Longword newword = new Longword();
        newword.copy(this);
        if (amount > 0) {
            System.arraycopy(sequence, 1, newword.sequence, 0, 31);
            this.copy(newword);
            newword = leftShift(--amount);
        }
        newword.sequence[31].clear();
        return newword;
    }

    /**
     * all 32 bits interpreted as long value
     **/
    public long getUnsigned() {
        long value = 0;
        for (int i = 0; i < 32; i++) {
            value += (long) sequence[i].getValue() * binary_pow(31 - i);
        }
        return value;
    }

    /*
     31 bits for value and first bit determines sign 
     first bit = 1 is negative  
     */
    public int getSigned() {
        int value = 0;
        for (int i = 1; i < 32; i++) {
            value += sequence[i].getValue() * binary_pow(31 - i);
        }
        if (sequence[0].getValue() == 1)
            value *= -1;

        return value;
    }

    public void copy(Longword other) {
        for (int i = 0; i < 32; i++)
            sequence[i] = new Bit(other.sequence[i].getValue());
    }

    //helper function for longword's set.
    public void clear_from_int(int idx) {
        for (int i = idx; i < 32; i++) {
            sequence[i].clear();
        }
    }

    /*

    helper function compute powers of 2
     */
    public static long binary_pow(long i) {
        long num = 1;
        while (i > 0) {
            num *= 2;
            i--;
        }
        return num;
    }

    /**
     * @param value if value >0 all bits accoutn for value
     *              if value <0 first bit "sequence[0] is set to 1 as negative
     **/
    public void set(int value) {
        if (value >= 0) {
            for (int i = 0; i < 32; i++) {
                long bit_val = binary_pow(31 - i);
                // System.out.println(bit_val);
                if (value == bit_val) {
                    sequence[i].set();
                    this.clear_from_int(++i);
                    break;
                } else if (value < bit_val) {
                    sequence[i].clear();
                } else {
                    value -= bit_val;
                    sequence[i].set();
                }
            }
        } else {
            sequence[0].set();
            value *= -1;
            for (int i = 1; i < 32; i++) {
                long bit_val = binary_pow(31 - i);
                if (value == bit_val) {
                    sequence[i].set();
                    this.clear_from_int(++i);
                    break;
                } else if (value < bit_val) {
                    sequence[i].clear();
                } else {
                    value -= bit_val;
                    sequence[i].set();
                }
            }
        }
    }

    public String toString() {
        return Arrays.toString(sequence);
    }

    public int eval_byte(int idx) {
        int val = 0;
        for (int i = idx; i < (idx + 4); i++) {
            val += sequence[i].getValue() * binary_pow(3 - i);
        }

        return val;
    }

    public int eval_word(int idx, int len) {
        int val = 0;
        for (int i = idx + 1; i < (idx + len); i++) {
            val += sequence[i].getValue() * binary_pow((len - 1) - i);
        }
        if (sequence[0].getValue() == 1) {
            val *= -1;
        }

        return val;
    }

    public int unsigned_eval_word(int idx, int len) {
        int val = 0;
        for (int i = idx; i < (idx + len); i++) {
            val += sequence[i].getValue() * binary_pow((len - 1) - i);
        }
        return val;
    }

}

