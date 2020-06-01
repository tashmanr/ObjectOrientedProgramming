/**
 * 336423124
 * Rebecca Tashman
 */
package interfaces;

/**
 * Interface for hit notifier, used by block.
 */
public interface HitNotifier {

    /**
     * Function to add hl to the list of listeners to hit events.
     * @param hl hit listener to add
     */
    void addHitListener(HitListener hl);

    /**
     * Function to remove hl from the list of listeners to hit events.
     * @param hl hit listener to remove
     */
    void removeHitListener(HitListener hl);
}