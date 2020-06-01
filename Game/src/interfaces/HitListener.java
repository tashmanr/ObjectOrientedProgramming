/**
 * 336423124
 * Rebecca Tashman
 */
package interfaces;

import sprites.Ball;
import sprites.Block;

/**
 * Hit listener interface, used by classes in hitlisteners package.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     * @param beingHit block
     * @param hitter ball
     */
    void hitEvent(Block beingHit, Ball hitter);
}
