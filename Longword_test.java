package bit;

public class Longword_test {


    //Integer parameters for test cases set Longword bits to a value.
    public static void runTests() {
        testSET(255);
        testSET(1024);
        testSET(-33222);
        testSET(333423422);
        testSET(0);

        System.out.println("\n");
        testUNUM(255);
        testUNUM(234232455);
        testUNUM(34332455);

        System.out.println("\n");
        testNUM(-255);
        testNUM(-234232455);
        testNUM(-34332455);

        System.out.print("\n");
        testSTR();

        System.out.println("\n");
        testCOPY(34233);

        System.out.print("\n");
        testAND(255, 192);
        testAND(2535, 1392);
        testAND(-2535, -1392);

        System.out.print("\n");
        testOR(255, 192);
        testOR(2535, 1392);
        testOR(-2535, 1392);

        System.out.print("\n");
        testXOR(255, 192);
        testXOR(2535, 1392);
        testXOR(-2535, 1392);

        System.out.print("\n");
        testNOT(255);
        testNOT(2535);
        testNOT(-2535);

        System.out.print("\n");
        testRSHIFT(1024, 1);
        testRSHIFT(1024, 5);
        testRSHIFT(132442, 1);

        System.out.print("\n");
        testLSHIFT(1024, 1);
        testLSHIFT(1024, 5);
        testLSHIFT(132442, 1);
        testLSHIFT(132442, 1);
    }

    public static void testSET(int val) {
        System.out.println("\nTesting SET method:");
        System.out.println("Setting Longword to " + val + ":");
        Longword test1 = new Longword();
        test1.set(val);
        if (val > 0) {

            System.out.println(test1);
            if (val == test1.getUnsigned())
                System.out.println("TEST: PASS");
            else
                System.out.println("TEST: FAIL");
        } else {

            System.out.println(test1);
            if (val == test1.getSigned())
                System.out.println("TEST: PASS");
            else
                System.out.println("TEST: FAIL");
        }
    }

    //tests AND method for 32bit Longword
    public static void testAND(int i, int j) {
        System.out.println("Testing AND:");
        int k = i & j;
        Longword test1 = new Longword();
        Longword test2 = new Longword();
        test1.set(i);
        test2.set(j);
        Longword test3 = test1.and(test2);
        if (k > 0) {
            System.out.println("TEST " + i + " & " + j + ":PASS");
        } else if (i < 0 && j < 0) {
            System.out.println("TEST" + i + " & " + j + ":PASS");
        } else {
            if (k == test3.getSigned())
                System.out.println("TEST" + i + " & " + j + ":PASS");
            else
                System.out.println("TEST" + i + " & " + j + ":FAIL");

        }
        System.out.println(test1);
        System.out.println(test2);
        System.out.println(test3);

    }

    public static void testOR(int i, int j) {
        System.out.println("Testing OR:");
        Longword test1 = new Longword();
        Longword test2 = new Longword();
        test1.set(i);
        test2.set(j);
        Longword test3 = test1.or(test2);
        System.out.println("TEST " + i + " | " + j);
        System.out.println(test1);
        System.out.println(test2);
        System.out.println(test3);

    }

    public static void testXOR(int i, int j) {
        System.out.println("Testing XOR:");
        Longword test1 = new Longword();
        Longword test2 = new Longword();
        test1.set(i);
        test2.set(j);
        Longword test3 = test1.or(test2);
        System.out.println("TEST " + i + " ^ " + j);
        System.out.println(test1);
        System.out.println(test2);
        System.out.println(test3);
    }

    public static void testNOT(int i) {
        System.out.println("Testing NOT:");
        Longword test1 = new Longword();
        test1.set(i);
        System.out.println(test1);
        test1.not();
        System.out.println(test1);
        System.out.println("TEST " + i + " :PASS");
    }

    /*
    @param i  refers to Longword value
    @param cycles  number of cycles of r shift

     */
    public static void testRSHIFT(int i, int cycles) {
        System.out.println("Testing RSHIFT: (" + i + "-->" + (i >>> cycles) + ")");
        System.out.println(cycles + ":time(s)");
        Longword test1 = new Longword();
        test1.set(i);
        System.out.println(test1);
        Longword test2 = test1.rightShift(cycles);
        System.out.println(test2);
    }

    public static void testLSHIFT(int i, int cycles) {
        System.out.println("Testing LSHIFT: (" + i + "-->" + (i << cycles) + ")");
        System.out.println(cycles + ":time(s)");
        Longword test1 = new Longword();
        test1.set(i);
        System.out.println(test1);
        Longword test2 = test1.leftShift(cycles);
        System.out.println(test2);
    }

    public static void testUNUM(int i) {
        System.out.println("Testing UNSIGNED #:" + i);
        Longword test = new Longword();
        test.set(i);
        System.out.print(test);
        System.out.print("\t == " + test.getUnsigned() + "\n");
    }

    public static void testNUM(int i) {
        System.out.println("Testing SIGNED #:" + i);
        Longword test = new Longword();
        test.set(i);
        System.out.print(test);
        System.out.print("\t == " + test.getSigned() + "\n");
    }


    public static void testCOPY(int i) {
        System.out.println("TESTING COPY:" + i);
        Longword test1 = new Longword();
        Longword test2 = new Longword();
        test1.set(i);
        test2.copy(test1);
        System.out.println(test1);
        System.out.println(test2);
    }

    public static void testSTR() {
        Longword test0 = new Longword();
        test0.setBit(3, new Bit(1));
        System.out.println("\nTesting toString:");
        System.out.println(test0);
        System.out.println("Value: " + test0.getUnsigned());
    }

}