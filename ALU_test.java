package bit;


public class ALU_test {

    /**
     * Loop that goes through all operation codes and tests them each with longword a = 650
     * longword b = 230
     * <p>
     * <p>
     * For shifting (code 12 & 13), longword b has been replaced with 5 as testing value for cycles.
     *
     * @throws TestException if invalid values are assigned to bit ex. <0 || >1
     */
    public static void ALUruntests() throws TestException {

        Bit[][] op = {{new Bit(0), new Bit(1), new Bit(1), new Bit(1)}
                , {new Bit(1), new Bit(0), new Bit(0), new Bit(0)}
                , {new Bit(1), new Bit(0), new Bit(0), new Bit(1)}
                , {new Bit(1), new Bit(0), new Bit(1), new Bit(0)}
                , {new Bit(1), new Bit(0), new Bit(1), new Bit(1)}
                , {new Bit(1), new Bit(1), new Bit(0), new Bit(0)}
                , {new Bit(1), new Bit(1), new Bit(0), new Bit(1)}
                , {new Bit(1), new Bit(1), new Bit(1), new Bit(0)}
                , {new Bit(1), new Bit(1), new Bit(1), new Bit(1)}
        };

        for (Bit[] bits : op) {
            testALU(bits, 650, 230);
        }
    }


    public static void testALU(Bit[] op, int a, int b) throws TestException {
        Longword first = new Longword();
        Longword second = new Longword();
        Longword five = new Longword();
        Longword c = new Longword();
        five.set(5);
        first.set(a);
        second.set(b);
        switch (ALU.eval_op_code(op)) {
            case 7 -> {
                System.out.println("\nTesting ALU: MULTIPLY " + a + " * " + b);
                c.set(a * b);
                System.out.println("Expected Output:\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tValue");
                System.out.println(c + "\t" + (a * b));
                System.out.println("Actual Output:");
                Longword product = ALU.ALUtest(op, first, second);
                System.out.println(product + "\t" + product.getSigned());
                if ((product.getSigned()) == c.getSigned())
                    System.out.println("TEST: PASS");
                else
                    throw new TestException("TEST: FAILED");
            }
            case 8 -> {
                System.out.println("\nTesting ALU:" + a + " AND " + b);
                System.out.println(first);
                System.out.println(second);
                System.out.println(ALU.ALUtest(op, first, second));
            }
            case 9 -> {
                System.out.println("\nTesting ALU: " + a + " OR " + b);
                System.out.println(first);
                System.out.println(second);
                System.out.println(ALU.ALUtest(op, first, second));
            }
            case 10 -> {
                System.out.println("\nTesting ALU: " + a + " XOR " + b);
                System.out.println(first);
                System.out.println(second);
                System.out.println(ALU.ALUtest(op, first, second));
            }
            case 11 -> {
                System.out.println("\nTesting ALU: " + "NOT " + a);
                Longword aone = new Longword();
                aone.set(a);
                c = first.not();
                System.out.println(c);
                Longword notex = (ALU.ALUtest(op, aone, second));
                System.out.println(notex);
            }
            case 12 -> {
                System.out.println("\nTesting ALU:" + a + " LEFT SHIFT 5 TIMES");
                System.out.println("Before shift:");
                System.out.println(first);
                c = first.leftShift(5);
                Longword a_one = new Longword();
                System.out.println("Expected Output after Shift:");
                a_one.set(a);
                System.out.println(c);
                System.out.println(five);
                System.out.println("Actual Output after Shift:");
                System.out.println(ALU.ALUtest(op, a_one, five));
            }
            case 13 -> {
                System.out.println("\nTesting ALU:" + a + " RIGHT SHIFT 5 TIMES");
                Longword aonne = new Longword();
                aonne.set(a);
                System.out.println("Before shift:");
                System.out.println(first);
                c = first.rightShift(5);
                System.out.println("Expected Output:");
                System.out.println(c);
                System.out.println("Actual Output:");
                System.out.println(ALU.ALUtest(op, aonne, five));
            }
            case 14 -> {
                System.out.println("\nTesting ALU: ADD " + a + " + " + b);
                c.set(a + b);
                Longword add = ALU.ALUtest(op, first, second);
                System.out.println("Expected Output:\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tValue");
                System.out.println(c + "\t" + (a + b));
                System.out.println("Actual Output:");
                System.out.println(add + "\t" + add.getSigned());
                if ((add.getSigned()) == c.getSigned())
                    System.out.println("TEST: PASS");
                else
                    throw new TestException("TEST: FAILED");
            }
            case 15 -> {
                System.out.println("\nTesting ALU: SUBTRACT " + a + "  -  " + b);
                c.set(a - b);
                System.out.println("Expected Output:\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tValue");
                System.out.println(c + "\t" + (a - b));
                System.out.println("Actual Output:");
                Longword subtract = ALU.ALUtest(op, first, second);
                System.out.println(subtract + "\t" + subtract.getSigned());
                if ((subtract.getSigned()) == c.getSigned())
                    System.out.println("TEST: PASS");
                else
                    throw new TestException("TEST: FAILED");
            }
            default -> System.out.println("Errror invalid codes");
        }
    }

}