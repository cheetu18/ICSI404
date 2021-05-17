package bit;

public class cpu_test1 {

    public static void cpuruntest() throws TestException {
        //testing halt, interrupt (0 & 1), move, preload, and proper evaluation of arithmitic with registers
        String[] str = {
                "0001 0000 1111 1111",  //r0  --> -127
                "0001 0001 1011 1000",  //r1  --> -56
                "0001 0010 0111 1111",  //r2  -->  127
                "0001 1111 0000 1010",  //r15  --> 10
                "0001 1110 0000 1001",  //r14  --> 9
                "0010 0000 0000 0000",  //print out registers
        };
        String[] str1 = {
                "0001 0000 1111 1111",  //r0  --> -127
                "0001 0001 1011 1000",  //r1  --> -56
                "0001 0010 0111 1111",  //r2  -->  127
                "0001 1111 0000 1010",  //r15  --> 10
                "0001 1110 0000 1001",  //r14  --> 9
                "0010 0000 0000 0001",  //print out memory
        };
        String[] str_add = {
                "0001 1111 0000 1010",  //r15  --> 10
                "0001 1110 0000 1001",  //r14  --> 9
                "1110 1111 1110 0001",  // r15 + r14  --> r1
                "0010 0000 0000 0000",  //print out registers
        };
        String[] str_halt = {
                "0001 1111 1000 1010",  //r15  --> 10
                "0001 1010 0000 0001",  //r14  --> -1
                "1110 1111 1110 0001",  // r15 + r14  --> r1
                "0000 0000 0000 0000",  //halt
        };

        System.out.println("\n\nTest 1:");
        System.out.println("mov -127 to r0");
        System.out.println("mov -56 to r1");
        System.out.println("mov 127 to r2");
        System.out.println("mov 10 to r15");
        System.out.println("mov 9 to r14");
        System.out.println("interrupt 0 (print registers)");
        testregister(str);

        System.out.println("\n\nTest 2:");
        System.out.println("mov -127 to r0");
        System.out.println("mov -56 to r1");
        System.out.println("mov 127 to r2");
        System.out.println("mov 10 to r15");
        System.out.println("mov 9 to r14");
        System.out.println("interrupt 1 (print memory)");
        testregister(str1);

        System.out.println("\n\nTest 3:");
        System.out.println("mov 10 to r15");
        System.out.println("mov 9 to r14");
        System.out.println("add  r14 and r15 and store in r1");
        System.out.println("interrupt 0 (print registers)");
        testregister(str_add);

        System.out.println("\n\nTest 4: Halt!!!");
        testregister(str_halt);
    }

    public static void testregister(String[] preload) throws TestException {
        Computer test1 = new Computer();
        test1.preload(preload);
        test1.run();
        }
        
}
        

