package bit;

public class Computer {
    Bit[] opcode;
    boolean halt;
    Longword[] registers;
    Memory resource;
    private Longword currentInstruction = new Longword();
    private Longword SP = new Longword();
    private Longword result = new Longword();
    private final Longword op1 = new Longword();
    private final Longword op2 = new Longword();
    private Longword program_counter = new Longword(); //program counter
    private final Longword instruction_increment = new Longword();
    private final Longword SP_increment = new Longword();
    private int register_num;
    private int interrupt_val;
    private final Bit[] comparison;
    private int branch_operation_val, stack_operation_val, opcode_val;

    Computer() {
        //init opcode
        opcode = new Bit[4];
        for (int i = 0; i < 4; i++) {
            opcode[i] = new Bit(0);
        }

        //registers created
        registers = new Longword[16];
        for (int i = 0; i < 16; i++) {
            registers[i] = new Longword();
        }
        resource = new Memory();
        instruction_increment.set(2);
        SP_increment.set(4);
        halt = false;
        register_num = 0;
        interrupt_val = 0;
        SP.set(1020);
        comparison = new Bit[]{new Bit(0), new Bit(0)};
    }


    public void run() throws TestException {
        while (!halt) {
            fetch();
            decode();
            execute();
            store();
        }
    }

    public void halt() {
        halt = true;
    }

    public void fetch() throws TestException {
        currentInstruction = resource.read(program_counter);
        program_counter = RippleAdder.add(program_counter, instruction_increment);
    }

    public void decode() {
        register_num = 0;

        //find operation code
        for (int i = 0; i < 4; i++) {
            opcode[i] = new Bit(currentInstruction.sequence[i].getValue());
        }
        opcode_val = ALU.eval_op_code(opcode);

        switch (opcode_val) {
            //interrupt and halt
            case 0, 2 -> {
                interrupt_val = currentInstruction.sequence[15].getValue();
                halt();
            }

            //move
            case 1 -> {
                currentInstruction = currentInstruction.leftShift(4); //shift away opcode bytes
                register_num = currentInstruction.eval_byte(0);
                currentInstruction = currentInstruction.leftShift(4); //shift away register bytes
                op2.set(currentInstruction.eval_word(0, 8));
            }

            //jump
            //jump 1
            case 3 -> {
                currentInstruction = currentInstruction.leftShift(8);
                int new_add = currentInstruction.unsigned_eval_word(0, 8);
                program_counter.set(new_add);
            }

            //compare
            case 4 -> {
                currentInstruction = currentInstruction.leftShift(4); //shift away opcode
                op1.set(registers[currentInstruction.eval_word(0, 4)].getSigned());
                currentInstruction = currentInstruction.leftShift(8);
                op2.set(registers[currentInstruction.eval_word(0, 4)].getSigned());
                //op2 = registers[currentInstruction.eval_byte(0)];
                currentInstruction = currentInstruction.leftShift(4); //shift away second register address
            }

            //branch  operations
            case 5 -> {
                currentInstruction = currentInstruction.leftShift(4); //shift away opcode bytes
                branch_operation_val = ((int) Longword.binary_pow(1) * currentInstruction.sequence[0].getValue());
                branch_operation_val += (Longword.binary_pow(0)) * (currentInstruction.sequence[1]).getValue();
                currentInstruction.leftShift(2);
            }

            //stack operations
            case 6 -> {
                currentInstruction = currentInstruction.leftShift(4);
                stack_operation_val = currentInstruction.eval_byte(0);
                //push & pop
                if (stack_operation_val <= 4) {
                    currentInstruction = currentInstruction.leftShift(8);
                    register_num = currentInstruction.eval_byte(0);
                }
                //return will just pop new address from stack and move it to pc.
                else if (stack_operation_val == 12) {
                    currentInstruction = currentInstruction.leftShift(16);
                }
                //call
                else {
                    currentInstruction = currentInstruction.leftShift(2);
                }


            }
            //math
            default -> {
                currentInstruction = currentInstruction.leftShift(4); //shift away opcode bytes
                op1.set(registers[currentInstruction.eval_byte(0)].getSigned());
                currentInstruction = currentInstruction.leftShift(4); //shift away first register address
                op2.set(registers[currentInstruction.eval_byte(0)].getSigned());
                currentInstruction = currentInstruction.leftShift(4); //shift away second register address
                register_num = currentInstruction.eval_byte(0);
            }
        }
    }


    public void execute() throws TestException {
        //if computer is halted, check for an interrupt code
        if (halt) interrupt();

        else {
            result = ALU.ALUtest(opcode, op1, op2);
        }
    }

    public void store() throws TestException {
        if (!halt) {
            switch (opcode_val) {
                case 3:
                    break;
                case 4:
                    compare(result);
                    break;
                case 5:
                    int counter_val = 2 * currentInstruction.eval_word(0, 10);

                    if (counter_val < 0) {
                        counter_val -= 2;
                    }

                    counter_val += program_counter.getSigned();

                    switch (branch_operation_val) {
                        //branchNEQ
                        case 0:
                            if (comparison[1].getValue() == 0) {
                                program_counter.set(counter_val);
                            }
                            break;

                        //branchEQ
                        case 1:
                            if (comparison[1].getValue() == 1) {
                                program_counter.set(counter_val);
                            }
                            break;

                        //branchG
                        case 2:
                            if (comparison[0].getValue() == 1) {
                                program_counter.set(counter_val);
                            }
                            break;

                        //branchGEQ
                        case 3:
                            if (comparison[0].getValue() == 1 || comparison[1].getValue() == 1) {
                                program_counter.set(counter_val);
                            }
                            break;

                        default:
                            throw new TestException("Branch Error...");
                    }
                    break;
                case 6:
                    switch (stack_operation_val) {
                        //push
                        case 0 -> {
                            //decrement then push to updated stack pointer
                            SP = RippleAdder.subtract(SP, SP_increment);
                            resource.write(registers[register_num], SP);
                        }

                        //pop
                        case 4 -> {
                            registers[register_num].copy(resource.read(SP));
                            SP = RippleAdder.add(SP, SP_increment);
                        }

                        //return
                        case 12 -> {
                            //set program_counter to last on stack
                            program_counter = resource.read(SP);
                            //pop stack
                            SP = RippleAdder.add(SP, SP_increment);
                        }

                        //call
                        default -> {
                            int address = 2 * currentInstruction.unsigned_eval_word(0, 10);
                            //write current PC address to stack memory
                            SP = RippleAdder.subtract(SP, SP_increment);
                            resource.write(program_counter, SP);
                            //jump to new address
                            program_counter.set(address);
                        }
                    }


                    break;
                default:
                    registers[register_num].copy(result);
                    break;
            }
        } else {
            System.out.println("System halted!\n");
        }
    }


    public void compare(Longword a) {
        if (a.getSigned() == 0) {
            comparison[1].set();
        } else if (a.getSigned() < 0) {
            comparison[0].clear();
            // comparison = new Bit[]{new Bit(0), new Bit(1)};
        }
        if (a.getSigned() > 0) {
            comparison[0].set();
            //comparison = new Bit[]{new Bit(1), new Bit(1)};
        }
    }


    public void interrupt() {
        int i = 0;
        if (ALU.eval_op_code(opcode) == 2) {
            //print out registers
            if (interrupt_val == 0) {
                System.out.println("Printing the registers:");
                for (Longword the_register : registers) {
                    System.out.println("Register" + (i++) + ": " + the_register);
                }
            }
            //print out memory array
            if (interrupt_val == 1) {
                System.out.println("Printing memory:");
                for (int j = 0; j < resource.memory.length; j++) {
                    if (j != 0 && j % 256 == 0)
                        System.out.println("" + resource.memory[j]);
                    else
                        System.out.print(resource.memory[j]);

                }
            }
        }

    }

    // if valid integers aren't set in long word bit throw exception
    public void preload(String[] str) throws TestException {
        Longword address = new Longword();
        Longword increment = new Longword();
        Longword instruction = new Longword();
        increment.set(2);
        for (String l_word : str) {
            for (int i = 0; i < 16; i++) {
                String this_word = l_word.replaceAll("\\s", "");
                int set_bit = Character.getNumericValue(this_word.charAt(i));
                instruction.sequence[i].set(set_bit);
            }
            resource.write(instruction, address);
            address = RippleAdder.add(address, increment);

        }
    }


}


