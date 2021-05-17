package bit;

/**
 * @author Kyle Reddy
 * STUDENTID#: 001456693
 * ICSI 404 PROJECT 1: The Bit
 */
public class Bit_test {

    public static void bitrunTests() throws TestException {
        testAnd();
        testOR();
        testXOR();
        testNOT();
        System.out.println("\nTesting mutators, accessors and toString:");
        testgetVAL();
        testClear();
        testToggle();
        testtoStr();

        //testSet();
    }

    //test and function
    public static void testAnd() throws TestException {
        System.out.println("Testing AND function:");
        if (new Bit(0).and(new Bit(0)).getValue() != 0) throw new TestException("1 AND 1 FAILED");
        else System.out.println("1 AND 1 PASSED");
        if (new Bit(0).and(new Bit(1)).getValue() != 0) throw new TestException("0 AND 1 FAILED");
        else System.out.println("0 AND 1 PASSED");
        if (new Bit(1).and(new Bit(0)).getValue() != 0) throw new TestException("1 AND 0 FAILED");
        else System.out.println("1 AND 0 PASSED");
        if (new Bit(1).and(new Bit(1)).getValue() != 1) throw new TestException("1 AND 1 FAILED");
        else System.out.println("1 AND 1 PASSED");

    }

    //test OR function
    public static void testOR() throws TestException {
        System.out.println("Testing OR function:");
        if (new Bit(0).or(new Bit(0)).getValue() != 0) throw new TestException("1 OR 1 FAILED");
        else System.out.println("1 OR 1 PASSED");
        if (new Bit(0).or(new Bit(1)).getValue() != 1) throw new TestException("0 OR 1 FAILED");
        else System.out.println("0 OR 1 PASSED");
        if (new Bit(1).or(new Bit(0)).getValue() != 1) throw new TestException("1 OR 0 FAILED");
        else System.out.println("1 OR 0 PASSED");
        if (new Bit(1).or(new Bit(1)).getValue() != 1) throw new TestException("1 OR 1 FAILED");
        else System.out.println("1 OR 1 PASSED");

    }

    //test XOR function
    public static void testXOR() throws TestException {
        System.out.println("Testing XOR function:");
        if (new Bit(0).xor(new Bit(0)).getValue() != 0) throw new TestException("1 XOR 1 FAILED");
        else System.out.println("1 XOR 1 PASSED");
        if (new Bit(0).xor(new Bit(1)).getValue() != 1) throw new TestException("0 XOR 1 FAILED");
        else System.out.println("0 XOR 1 PASSED");
        if (new Bit(1).xor(new Bit(0)).getValue() != 1) throw new TestException("1 XOR 0 FAILED");
        else System.out.println("1 XOR 0 PASSED");
        if (new Bit(1).xor(new Bit(1)).getValue() != 0) throw new TestException("1 XOR 1 FAILED");
        else System.out.println("1 XOR 1 PASSED");

    }

    //test NOT funtion
    public static void testNOT() throws TestException {
        System.out.println("Testing NOT function:");
        if (new Bit(0).not().getValue() != 1) throw new TestException("NOT on 0 Failed");
        else System.out.println("NOT ON 0 PASSED");
        if (new Bit(1).not().getValue() != 0) throw new TestException("NOT on 1 Failed");
        else System.out.println("NOT ON 1 PASSED");
    }

    //test getval function
    public static void testgetVAL() throws TestException {
        System.out.println("Testing getValue:");

        if (new Bit(0).getValue() != 0) throw new TestException("getValue: FAIL");
        else System.out.println("getValue: PASS (1/2)");

        if (new Bit(1).getValue() != 1) throw new TestException("getValue: FAIL");
        else System.out.println("getValue: PASS (2/2)");

    }

    //tests set(void) and set(int) function
    public static void testSet() throws TestException {
        System.out.println("Testing set(void) and set(int) functions:");
        Bit test1 = new Bit(0);
        test1.set();
        if (test1.getValue() != 1) throw new TestException("SET TO 1: FAIL");
        else System.out.println("SET TO 1: PASS");
        test1.set(0);
        if (test1.getValue() != 0) throw new TestException("SET TO 0: FAIL");
        else System.out.println("SET TO 0: PASS");
        System.out.println("\\nTesting set(int) to invalid value. WILL THROW EXCEPTION!!");
        test1.set(3);
    }

    //test clear function
    public static void testClear() throws TestException {
        System.out.println("Testing clear function:");
        Bit test1 = new Bit(1);
        test1.clear();
        if (test1.getValue() != 0) throw new TestException("CLEAR:FAIL");
        else System.out.println("CLEAR:PASS");
    }

    public static void testToggle() throws TestException {
        System.out.println("Testing toggle function:");
        Bit test0 = new Bit(0);
        Bit test1 = new Bit(1);
        //call toggle function
        test0.toggle();
        test1.toggle();
        if (test1.getValue() != 0) throw new TestException("TOGGLE: FAIL");
        else System.out.println("TOGGLE: PASS (1/2)");
        if (test0.getValue() != 1) throw new TestException("TOGGLE: FAIL");
        else System.out.println("TOGGLE: PASS (2/2)");
    }

    public static void testtoStr() throws TestException {
        System.out.println("Testing toString function:");
        if (Integer.parseInt(new Bit(1).toString()) != 1) throw new TestException("toString: FAIL");
        else System.out.println("toString: PASS");
    }

}

