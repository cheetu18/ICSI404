package bit;


public class Assembler {


    /**
     * PROJECT 8
     *
     * @author Kyle Reddy
     * Title: Assingment 8: Assembler
     */

    public static String[] assemble(String[] code) throws TestException {
        int len = code.length;
        String[] assembled = new String[len];

        for (int i = 0; i < assembled.length; i++) {
            assembled[i] = evaluate(code[i]);
        }


        return assembled;
    }

    //evaluates an entire command
    public static String evaluate(String cmd) throws TestException {
        int i = 0;
        String[] token = cmd.split(" ");
        String keyword = token[0].toLowerCase();
        token[0] = cmd_first(token[i++]);  //evalute first token and convert

        //halt or return
        if (token.length == 1) {
            if (keyword.equals("return")) {
                return "0110 1100 0000 0000";
            }

            return "0000 0000 0000 0000";
        }
        else if (token.length == 2) {
            switch (keyword) {
                case "interrupt" -> {
                    if (Integer.parseInt(token[i]) == 0)
                        return "0010 0000 0000 0000";
                    else
                        return "0010 0000 0000 0001";

                }

                //JUMP! JUMP!
                case "jump" -> {
                    String[] newtoken = new String[token.length + 2];
                    String[] bits;
                    newtoken[0] = token[0];
                    newtoken[1] = "0000";
                    bits = calc_val(Integer.parseInt(token[i]));
                    newtoken[2] = bits[0];
                    newtoken[3] = bits[1];
                    return String.join(" ", newtoken);
                }

                case "push" -> {
                    String[] newtoken = new String[token.length + 2];
                    newtoken[0] = token[0];
                    newtoken[1] = "0000";
                    newtoken[2] = "0000";
                    newtoken[3] = which_reg(token[1]);
                    return String.join(" ", newtoken);

                }
                case "pop" -> {
                    String[] newtoken = new String[token.length + 2];
                    newtoken[0] = token[0];
                    newtoken[1] = "0100";
                    newtoken[2] = "0000";
                    newtoken[3] = which_reg(token[1]);
                    return String.join(" ", newtoken);

                }

                case "call" -> {
                    String fpart = "0110 10";
                    int val = Integer.parseInt(token[1]);
                    int ninth_power = (int) Longword.binary_pow(9);
                    int eight_power = (int) Longword.binary_pow(8);
                    if (val >= ninth_power) {
                        val -= ninth_power;
                        fpart = fpart + '1';
                    } else {
                        fpart = fpart + '0';
                    }

                    if (val >= eight_power) {
                        val -= eight_power;
                        fpart = fpart + '1';
                    } else {
                        fpart = fpart + '0';
                    }

                    String[] newtoken = new String[token.length + 2];
                    String[] first_part = fpart.split(" ");
                    String[] bits;
                    newtoken[0] = first_part[0];
                    newtoken[1] = first_part[1];
                    bits = calc_val(val);
                    newtoken[2] = bits[0];
                    newtoken[3] = bits[1];
                    return String.join(" ", newtoken);
                }

                //ALL 4 BRANCH STATEMENTS
                case "brancheq", "branchneq", "branchg", "branchgeq" -> {
                    String fpart;
                    fpart = switch (keyword) {
                        case "branchneq" -> "0101 00";
                        case "brancheq" -> "0101 01";
                        case "branchg" -> "0101 10";
                        case "branchgeq" -> "0101 11";
                        default -> throw new IllegalStateException("Unexpected value: " + keyword);
                    };
                    int val = Integer.parseInt(token[1]);
                    if (val < 0) {
                        fpart = fpart + '1';
                        val *= -1;
                    } else
                        fpart = fpart + '0';

                    int a = (int) Longword.binary_pow(8);

                    if (val >= a) {
                        val -= a;
                        fpart = fpart + '1' + ' ';
                    } else {
                        fpart = fpart + '0' + ' ';
                    }
                    String[] newtoken = new String[token.length + 2];
                    String[] first_part = fpart.split(" ");
                    String[] bits;
                    newtoken[0] = first_part[0];
                    newtoken[1] = first_part[1];
                    bits = calc_val(val);
                    newtoken[2] = bits[0];
                    newtoken[3] = bits[1];
                    return String.join(" ", newtoken);
                }
            }
            ;
        } else if (token.length == 3) {
            String[] newtoken = new String[token.length + 1];

            //move
            if (keyword.equals("move")) {
                newtoken[0] = token[0];
                newtoken[1] = which_reg(token[1]);
                int val = Integer.parseInt(token[2]);
                String[] bits;
                bits = calc_val(val);
                newtoken[2] = bits[0];
                newtoken[3] = bits[1];
                return String.join(" ", newtoken);
            }
            //not || compare
            else {
                newtoken[0] = token[0];
                newtoken[1] = which_reg(token[1]);
                newtoken[2] = "0000";
                newtoken[3] = which_reg(token[2]);
                return String.join(" ", newtoken);
            }

        }
            //for all other code i.e ALU operations
            token[1] = which_reg(token[1]);
            token[2] = which_reg(token[2]);
            token[3] = which_reg(token[3]);
            return String.join(" ", token);

    }

    public static String[] calc_val(int val) throws TestException {
        String[] bits = new String[2];
        char[] first = {'0', '0', '0', '0'};
        char[] second = {'0', '0', '0', '0'};

        if (val == 0) {
            bits[0] = new String(first);
            bits[1] = new String(second);
            return bits;
        }

        //if negative convert val to positive and sign negative bit : first[0]
        if (val < 0) {
            first[0] = '1';
            val = val * -1;
        }

        //if value is  < -127 or  >   +127  it is out of bounds
        if (val > 127) {
            throw new TestException("Value Out of range for move function.");
        }

        for (int i = 1; i <= 7; i++) {
            if (i <= 3) {
                if (val == Longword.binary_pow(7 - i)) {
                    first[i] = '1';
                    break;
                }
                if (val > Longword.binary_pow(7 - i)) {
                    val -= Longword.binary_pow(7 - i);
                    first[i] = '1';

                }
            }
            if (i >= 4) {
                if (val == Longword.binary_pow(7 - i)) {
                    second[i - 4] = '1';
                    break;
                }

                if (val > Longword.binary_pow(7 - i)) {
                    val -= Longword.binary_pow(7 - i);
                    second[i - 4] = '1';
                }
            }
        }
        bits[0] = new String(first);
        bits[1] = new String(second);

        return bits;
    }

    //statement for the assembly
    public static String cmd_first(String cmd) throws TestException {
        cmd = cmd.toLowerCase();
        cmd = cmd.trim();
        return switch (cmd) {
            //alu operations 
            case "and" -> "1000";
            case "or" -> "1001";
            case "not" -> "1011";
            case "leftshift" -> "1100";
            case "rightshift" -> "1101";
            case "add" -> "1110";
            case "subtract" -> "1111";
            case "multiply" -> "0111";

            //system operations 
            case "halt" -> "0000";
            case "move" -> "0001";
            case "interrupt" -> "0010";
            case "jump" -> "0011";
            case "compare" -> "0100";
            case "brancheq", "branchneq", "branchg", "branchgeq" -> "0101";
            case "push", "pop", "return", "call" -> "0110";
            default -> throw new TestException("Invalid Syntax: Command doesn't exist");
        };
    }

    public static String which_reg(String token) throws TestException {
        token = token.toLowerCase();
        if (token.charAt(0) != 'r') {
            throw new TestException("Invalid Syntax");
        }
        int i = Integer.parseInt(token.substring(1));
        //means not a register, is a value
        return switch (i) {
            case 0 -> "0000";
            case 1 -> "0001";
            case 2 -> "0010";
            case 3 -> "0011";
            case 4 -> "0100";
            case 5 -> "0101";
            case 6 -> "0110";
            case 7 -> "0111";
            case 8 -> "1000";
            case 9 -> "1001";
            case 10 -> "1010";
            case 11 -> "1011";
            case 12 -> "1100";
            case 13 -> "1101";
            case 14 -> "1110";
            case 15 -> "1111";
            default -> throw new TestException("Invalid Register");
        };
    }


}

