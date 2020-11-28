public class Bits {


    public static void main(String args[]) {
        System.out.println("---------------Start-----------------");
        Bits obj = new Bits();

        /* Test Desired fucntion */
        int x = obj.findComplement(5);
        System.out.print(x);
    }


     /*
     * Given a positive integer num, output its complement number. The complement
     * strategy is to flip the bits of its binary representation.
     */
    public int findComplement(int num) {
        int com = 0;
        int shift = 0;
        while (num != 0) {
            int lsb = (num & 1) ^ 1;
            com |= (lsb << shift);
            shift++;
            num >>= 1;
        }
        return com;
    }
    public int findComplement2(int num) {
        int c = 0, t = num;
        // First, you need to find how many bits are present in the given number.
        while (t > 0) {
            t = t >> 1;
            c++;
        }
        // Now after that, create a mask of 1's about the size of num.
        // eg: if num = 5(101) then mask = 7(111)
        int mask = (1 << (c)) - 1;
        // now this mask can be used to flip all the bits in num using XOR.
        return num ^ mask;
    }





}