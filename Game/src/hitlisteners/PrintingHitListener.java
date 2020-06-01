/**
 * 336423124
 * Rebecca Tashman
 */
package hitlisteners;

import interfaces.HitListener;
import sprites.Ball;
import sprites.Block;

/**
 * Printing hit listener class, for testing the hit event.
 */
public class PrintingHitListener implements HitListener {

    /**
     * Constructor - empty.
     */
    public PrintingHitListener() {
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A Block was hit.");
    }
}