package bit;

public class Ripple_test {

    public static void ripplerunTests() throws TestException {
        System.out.println("Testing addition method:");
        System.out.println("Testing add between two positive numbers:");
        testAdd(255, 33);
        System.out.println("\nTesting add between two negative numbers:");
        testAdd(-255, -33);
        System.out.println("\nTesting add between one positive and one negative:(|a| > |-b|");
        testAdd(255, -33);
        System.out.println("\nTesting add between one positive and  one negative:(|-a| < |b|");
        testAdd(-332, 443444);
        System.out.println("\nTesting add between one positive one negative:(|-a| > |b|");
        testAdd(-2000500, 33330);
        System.out.println("\nTesting add between one positive one negative:(|a| < |-b|");
        testAdd(2500, -33330);
        System.out.println("\nTesting add between number and 0");
        testAdd(255, 0);
        System.out.println("\nTesting add between 0 and 0");
        testAdd(0, 0);
        System.out.println("\nTesting add between 1 and -1");
        testAdd(1, -1);
        System.out.println("\n\nTesting subtraction method:");
        System.out.println("Testing subtract between two positive numbers:");
        testSubtract(255, 33);
        System.out.println("\nTesting subtract between two negative numbers:");
        testSubtract(-332, -443444);
        System.out.println("\nTesting subtract with negative minuend and positive Subtrahend:( a < 0  && b > 0)");
        testSubtract(-33332, 443344);
        System.out.println("\nTesting subtract with positive minuend and posi Subtrahend:( a < 0  && b > 0)");
        testSubtract(332, -88889);

    }

    public static void testAdd(int val, int val2) throws TestException {
        Longword a = new Longword();
        Longword b = new Longword();
        a.set(val);
        b.set(val2);
        Longword c = RippleAdder.add(a, b);
        Longword d = new Longword();
        d.set(val + val2);
        System.out.println("Expected Output:\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tValue");
        System.out.println(d + "\t" + (val + val2));
        System.out.println("Actual Output:");
        System.out.println(c + "\t" + c.getSigned());
        if ((val + val2) == c.getSigned())
            System.out.println("TEST: PASS");
        else
            throw new TestException("TEST: FAILED");
    }


    public static void testSubtract(int val, int val2) throws TestException {
        Longword a = new Longword();
        Longword b = new Longword();
        a.set(val);
        b.set(val2);
        Longword c = RippleAdder.subtract(a, b);
        Longword d = new Longword();
        d.set(val - val2);
        System.out.println("Expected Output:\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tValue");
        System.out.println(d + "\t" + (val - val2));
        System.out.println("Actual Output:");
        System.out.println(c + "\t" + c.getSigned());
        if ((val - val2) == c.getSigned())
            System.out.println("TEST: PASS");
        else
            throw new TestException("TEST: FAILED");
    }

}
