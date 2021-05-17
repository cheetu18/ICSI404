package bit;


/*

add r1 r2 r3
or  r1 r2 r3
not r1 r2
leftshift r2 r3 r3
rightshift r1 f4
move r2 23



 */
public class Assembler_test {


    public static void run() throws TestException {
        String[] str = {
                "add r1 r2 r3",
                "or r1 r3 r5",
                "not r1 r2",
                "halt",
                "or r1 r12 r3",
                "leftShift r1 r3 r3",
                "rightShift r11 r2 r3",
                "subtract r0 r15 r2",
                "multiply r3 r3 r3",
                "interrupt 1",
                "interrupt 0",
                "move r1 -32",
                "move r1 30",
                "move r1 -100",
        };


        String[] answers = {

                "1101 0001 0010 0011",
                "1001 0001 0011 0101",
                "1011 0001 0000 0010",
                "0000 0000 0000 0000",
                "1001 0001 1100 0011",
                "1100 0001 0011 0011",
                "1101 1011 0010 0011",
                "1111 0000 1111 0010",
                "0111 0011 0011 0011",
                "0010 0000 0000 0001",
                "0010 0000 0000 0000",
                "0001 0001 1010 0000",
                "0001 0001 0001 1110",
                "0001 0001 1110 0100",
        };


        testit(str, answers);
    }


    public static void testit(String[] test, String[] answers) throws TestException {

        String[] str = Assembler.assemble(test);

        for (int i = 0; i < str.length; i++) {
            System.out.println("Command: " + test[i]);
            System.out.println("Expected Output: " + answers[i]);
            System.out.println("Actual Output:   " + str[i]);

            if (str[i].equals(answers[i]))
                System.out.println("TEST: PASS\n\n");
            else
                System.out.println("TEST: FAIL\n\n");
        }
    }

}
