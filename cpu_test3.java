package bit;

public class cpu_test3 {
    /**
     * @author Kyle Reddy
     */
    public static void run() throws TestException {


        String[] pushpop_test = {
                "move r1 100",
                "move r2 89",
                "move r3 30",
                "push r1",
                "push r2",
                "push r3",
                "pop r4",
                "pop r5",
                "pop r6",
                "interrupt 0",

        };


        String[] test = {
                "move r1 1",
                "move r2 4",
                "push r1",
                "push r2",
                "pop r3",
                "pop r4",
                "call 11",
                "interrupt 0",
                "interrupt 1",
                "interrupt 1",
                "add r1 r2 r3",
                "return",

        };



        System.out.println("\nTesting push and pop method");
       System.out.println("Test pass if:" );
        System.out.print(" r3 == r4 &&");
        System.out.print(" r2 == r5 && ");
        System.out.println(" r1 == r6\n");
        cpu_test2.runCode(pushpop_test);




        System.out.println("\nTesting call and return:");
        System.out.println("Call 11 should execute and return should return pc to interrupt 0");
        System.out.println("If interrupt 0 is called, the test passes. Call and Return work correctly");
        cpu_test2.runCode(test);
    }


}
