package bit;

/**
 * @author Kyle Reddy
 * STUDENTID#: 001456693
 * ICSI 404 PROJECT 1: The Bit
 */

public class Bit implements IBit {
    private int thebit;

    Bit(int thebit) {
        if (thebit == 1)
            this.thebit = 1;
        else
            this.thebit = 0;
    }

    //
    public void set(int value) throws TestException {
        if (value == 1)
            this.thebit = 1;
        else
            this.thebit = 0;
    }

    public void set() {
        this.thebit = 1;
    }

    public void clear() {
        this.thebit = 0;
    }

    public void toggle() {
        if (this.thebit == 1)
            this.thebit = 0;
        else
            this.thebit = 1;
    }

    public int getValue() {
        return this.thebit;
    }

    //if sum of both bits is 2, AND = 1
    public Bit and(Bit other) {
        int sum = this.thebit + other.thebit;

        if (sum == 2)
            return new Bit(1);
        else
            return new Bit(0);
    }

    //ONLY CASE or will = 0 is if the sum is 0
    public Bit or(Bit other) {
        int sum = this.thebit + other.thebit;

        if (sum == 0)
            return new Bit(0);
        else
            return new Bit(1);

    }

    //ONLY CASE XOR = 1 is if SUM = 1
    public Bit xor(Bit other) {
        int sum = this.thebit + other.thebit;

        if (sum == 1)
            return new Bit(1);
        else
            return new Bit(0);
    }

    public Bit not() {
        this.toggle();
        int i = this.thebit;
        return new Bit(i);
    }

    public String toString() {
        return String.valueOf(this.getValue());
    }
}
