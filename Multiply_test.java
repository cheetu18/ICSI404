package bit;

public class Multiply_test {

    public static void MultiplyrunTests() throws TestException {
        testMultiply(11, 13);
        testMultiply(512, 1333);
        testMultiply(-11, 313);
        testMultiply(11, -45454);
        testMultiply(-11, -13);
        testMultiply(-240, -300);
        testMultiply(-650, -230);
        testMultiply(-650, 0);
        testMultiply(-650, 0);
    }

    public static void testMultiply(int val, int val2) throws TestException {
        System.out.println("Testing multiply: " + val + " * " + val2);
        Longword a = new Longword();
        Longword b = new Longword();
        Longword c;
        Longword product = new Longword();
        product.set((val * val2));
        a.set(val);
        b.set(val2);
        c = Multiply.multiply(a, b);
        System.out.println("Expected Output:\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tValue");
        System.out.println(product + "\t" + (val * val2));
        System.out.println("Actual Output:");
        System.out.println(c + "\t" + c.getSigned());
        if ((val * val2) == c.getSigned())
            System.out.println("TEST: PASS");
        else
            throw new TestException("TEST: FAILED");

        System.out.println("\n");
    }
}
