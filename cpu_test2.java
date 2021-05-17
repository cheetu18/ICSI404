package bit;

public class cpu_test2 {

    public static void run() throws TestException {

        String[] jmp = {
                "jump 6",
                "interrupt 1",
                "move r1 12",
                "interrupt 0",
        };

        String[] branchneq = {
                "move r1 100",
                "move r2 10",
                "move r3 10",
                "add r2 r3 r2",
                "compare r1 r2",
                "branchneq -3",
                "interrupt 0",
        };

        String[] brancheq = {
                "move r1 100",
                "move r2 100",
                "compare r1 r2",
                "brancheq 1",
                "interrupt 1",
                "interrupt 0",
        };
        String[] strbrancheq2 = {
                "move r1 100",
                "move r2 100",
                "jump 8",
                "interrupt 0",
                "compare r1 r2",
                "brancheq -2",
                "interrupt 1",
        };

        String[] branchg = {
                "move r1 111",
                "move r2 100",
                "compare r1 r2",
                "branchg 1",
                "interrupt 1",
                "interrupt 0",
        };
        String[] branchgeq = {
                "move r1 100",
                "move r2 100",
                "compare r1 r2",
                "branchgeq 4",
                "interrupt 1",
                "interrupt 1",
                "interrupt 1",
                "move r3 -120",
                "interrupt 0",

        };


        System.out.println("Testing jump:");
        runCode(jmp);


        System.out.println("Testing branchNEQ");
        runCode(brancheq);
        System.out.println("PASS since branch/skip interrupt 1\n");


        System.out.println("Testing branchEQ:");
        runCode(brancheq);
        System.out.println("PASS\n");

        System.out.println("Testing branchEQ 2 :");
        runCode(strbrancheq2);
        System.out.println("PASS since branch/skip interrupt 1\n");


        System.out.println("Testing branchG:");
        runCode(branchg);
        System.out.println("PASS since branch/skip interrupt 1\n");

        System.out.println("Testing branchGEQ:");
        runCode(branchgeq);
        System.out.println("PASS since branch/skip interrupt 1\n");


    }


    public static void runCode(String[] str) throws TestException {
        String[] bin = Assembler.assemble(str);
        System.out.println("Binary instructions:");
        for (int i = 0; i < bin.length; i++) {
            System.out.print(bin[i]);
            System.out.print("              " + str[i] + "\n");
        }
        Computer test = new Computer();
        test.preload(bin);
        test.run();
    }
}
