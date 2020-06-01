/**
 * 336423124
 * Rebecca Tashman
 */

package gamesetup;

/**
 * Counter class.
 */
public class Counter {
    private int value;

    /**
     * Constructor - initializes at zero.
     */
    public Counter() {
        value = 0;
    }

    /**
     * Function to add number to current count.
     * @param number to add
     */
    public void increase(int number) {
        value += number;
    }

    /**
     * Function to subtract number from current count.
     * @param number to subtract
     */
    public void decrease(int number) {
        value -= number;
    }

    /**
     * Getter function for the current count.
     * @return value of counter
     */
    public int getValue() {
        return value;
    }
}