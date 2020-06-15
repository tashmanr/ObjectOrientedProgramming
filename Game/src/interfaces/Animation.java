/**
 * 336423124
 * Rebecca Tashman
 */

package interfaces;

import biuoop.DrawSurface;

/**
 * Interface for animation.
 */
public interface Animation {
    /**
     * Function to run one frame of the animation.
     * @param d drawsurface to show it on
     */
    void doOneFrame(DrawSurface d);

    /**
     * Function to let the animation know when to stop running.
     * @return false unless it's time to stop
     */
    boolean shouldStop();
}
