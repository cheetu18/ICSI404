package bit;

public interface IBit {
    void set(int value) throws TestException; // sets the value of the bit

    void toggle(); // changes the value from 0 to 1 or 1 to 0

    void set(); // sets the bit to 1

    void clear(); // sets the bit to 0

    int getValue(); // returns the current value

    Bit and(Bit other); // performs and on two bits and returns a new bit set to the result

    Bit or(Bit other); // performs or on two bits and returns a new bit set to the result

    Bit xor(Bit other); // performs xor on two bits and returns a new bit set to the result

    Bit not(); // performs not on the existing bit, returning the result as a new bit

    String toString(); // returns 0 or 1
}