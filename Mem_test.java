package bit;

public class Mem_test {

    public static void memruntest() throws TestException {

        testmem(1000, 1000);
        testmem(932, -3334);
        testmem(32, -333);
        testmem(0, 1232433333);

    }


    public static void testmem(int address, int value) throws TestException {
        Memory mem = new Memory();
        Longword addr = new Longword();
        Longword val = new Longword();
        addr.set(address);
        val.set(value);
        System.out.println("Testing read and write with value: " + val.getSigned() + " at memory index: " + addr.getSigned());
        mem.write(val, addr);
        Longword actual = mem.read(addr);
        System.out.println("Expected output:\n" + val);
        System.out.println("Actual Output\n" + actual);
        if (actual.getSigned() == val.getSigned())
            System.out.println("TEST: PASS");
        else
            System.out.println("TEST: FAIL");


    }

}
