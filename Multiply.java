package bit;

public class Multiply {


    public static Longword multiply(Longword a, Longword b) throws TestException {
        if(a.getSigned() == 0 || b.getSigned() == 0){
            return new Longword();
        }

        Bit sign = new Bit(0);
        Longword second = new Longword();
        Longword first = new Longword();
        first.copy(a);
        second.copy(b);
        Longword result;
        Longword sum = new Longword();
        if ((a.sequence[0].xor(b.sequence[0])).getValue() == 1) {
            sign.toggle();
        }
        second.sequence[0].clear();
        first.sequence[0].clear();
        for (int i = 0; i < 32; i++) {
            if (second.sequence[31].getValue() == 1) {
                sum = RippleAdder.add(first, sum);
                second = second.rightShift(1);
                second.sequence[0] = sum.sequence[31];
                sum = sum.rightShift(1);
            } else {
                second = second.rightShift(1);
                second.sequence[0] = sum.sequence[31];
                sum = sum.rightShift(1);
            }
        }
        result = RippleAdder.add(sum, second);
        result.sequence[0] = sign;
        return result;
    }

}
