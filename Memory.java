package bit;

/**
 * @author Kyle Reddy
 * Project 5
 * ID#:373851
 */
public class Memory {
    Bit[] memory;
    static final int MEM_LEN = 8192;


    public Memory() {
        memory = new Bit[MEM_LEN];
        //set each bit to 0
        for (int i = 0; i < memory.length; i++) memory[i] = new Bit(0);
    }

    public void write(Longword val, Longword addr) throws TestException {
        //if value > 1020, memory will be full before address fully written
        if (addr.getUnsigned() > 1020) {
            throw new TestException("Memory write overflow.");
        }
        int mem_idx = addr.getSigned() * 8;

        //write to memory
        for (int i = 0; i < 32; i++) {
            this.memory[mem_idx++].set(val.sequence[i].getValue());
        }
    }

    public Longword read(Longword addr) throws TestException {
        if (addr.getSigned() > 1020)
            throw new TestException("Invalid address range");

        Longword val = new Longword();
        int mem_idx = addr.getSigned() * 8;

        for (int i = 0; i < 32; i++) {
            val.sequence[i].set(memory[mem_idx++].getValue());
        }
        return val;
    }


}
