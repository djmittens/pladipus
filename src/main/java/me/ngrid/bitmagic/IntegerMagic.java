package me.ngrid.bitmagic;

/**
 * IntegerMagic bitmagic is what you shall find in this class.
 * Magical binary formulas for doing basic bit manipulation.
 * This class isnt very usefull because you are likely to inline all of these operations,
 * but it is very useful for testing and benchmarking.
 * Besides they all use integers, not longs and not chars and not shorts.
 * However if you are interested in only ints go for it, these will make your code more readable.
 */
public final class IntegerMagic {
    /**
     * Turns the right most 1 bit to off.  Useful for when you want to test if something is a power of 2.
     * eg. where {@code i = 0b0101000} the output will be {@code 0b0100000}
     * Please note that a number is a power of 2 iff there is only a single 1 in the field.
     * @param i input word.
     * @return {int | 0} returns 0 iff the input is 0 or the logic is a power of 2.
     */
    public static int rightMostOff(int i) {
        return i & (i - 1);
    }

    /**
     * Turns the right most 0 bit on. And producing all 1's if none.
     * {@code 0b10011 => 0b10111}
     * @param i input word.
     * @return {int | -1} -1 iff the input is 0, otherwise the output of the operation.
     */
    public static int rightMostOn(int i) {
        return i | (i + 1);
    }

    /**
     * Turns trailing 1's into 0's.
     * This is useful for when you wan to know if the unsigned integer is of the form 2<exp>n</exp> - 1
     *
     * {@code 0b1011 => 0b1000}
     *
     * @param i input word.
     * @return output of the operation
     */
    public static int trailingOff (int i) {
        return i & (i + 1);
    }

    /**
     * Turns trailing 0's into 1's.
     * {@code 0b100 => 0b111}
     *
     * @param i input word.
     * @return {result | i} iff i has no trailing 0's then returns i
     */
    public static int trailingOn (int i) {
        return i | (i - 1);
    }

    /**
     * Zeroes out the string and puts a 1 where the right most zero was.
     * {@code 0b1001 => 0b0010}
     *
     * @param i input word
     * @return 0 iff there are no zeroes.
     */
    public static int rightMostZero(int i) {
        return ~i & (i + 1);
    }

    /**
     * Zeroes out the string and puts a 1 where the right most one was.
     * {@code 0b1001 => 0b0001}
     *
     * @param i input word
     * @return -1(or all 1's) iff there are no zeroes.
     */
    public static int rightMostOne(int i) {
        return ~i | (i - 1);
    }


    /**
     * Test whether the given number is power of 2.
     * This method uses simple bit magic to tell if the number is a power of 2.
     * @param i input word
     * @return true if this number is a power of 2.
     */
    public static boolean isPowerOfTwo (int i) {
        return (i & (i - 1)) == 0;
    }

}
