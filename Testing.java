package bit;

public class Testing {
    public static void main(String[] args) throws Exception {

        System.out.println("***********************************");
        System.out.println("PROJECT 1: BIT UNIT TESTS");
        System.out.println("***********************************\n");
        Bit_test.bitrunTests();
        System.out.println("\n************************************");
        System.out.println("PROJECT 2: LONGWORD UNIT TESTS");
        System.out.println("*************************************");
        Longword_test.runTests();
        System.out.println("\n************************************");
        System.out.println("PROJECT 3: RIPPLEADDER UNIT TESTS");
        System.out.println("*************************************");
        Ripple_test.ripplerunTests();
        System.out.println("\n************************************");
        System.out.println("PROJECT 4: MULTIPLY UNIT TESTS");
        System.out.println("*************************************");
        Multiply_test.MultiplyrunTests();
        System.out.println("\n************************************");
        System.out.println("PROJECT 4:ALU UNIT TESTS");
        System.out.println("*************************************");
        ALU_test.ALUruntests();
        System.out.println("PROJECT 5: MEMORY UNIT TESTS");
        System.out.println("*************************************");
        Mem_test.memruntest();
        System.out.println("\n*************************************");
        System.out.println("PROJECT 7: CPU TEST 1");
        System.out.println("*************************************");
        cpu_test1.cpuruntest();
        System.out.println("\n*************************************");
        System.out.println("PROJECT 8: ASSEMBLER TEST ");
        System.out.println("*************************************");
        Assembler_test.run();
        System.out.println("\n*************************************");
        System.out.println("PROJECT 9: CPU TEST 2: JUMP, COMPARE, CONDITIONALS");
        System.out.println("*************************************");
        cpu_test2.run();
        System.out.println("\n*************************************");
        System.out.println("FINAL PROJECT 10: CPU TEST 3: STACK OPERATIONS");
        System.out.println("*************************************");
        cpu_test3.run();
    }
}
