/**
 * 336423124
 * Rebecca Tashman
 */
package interfaces;
import sprites.Ball;
import sprites.Block;

public interface HitNotifier {
    // Add hl as a listener to hit events.
    void addHitListener(HitListener hl);
    // Remove hl from the list of listeners to hit events.
    void removeHitListener(HitListener hl);
}