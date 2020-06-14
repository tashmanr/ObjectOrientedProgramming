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
    void doOneFrame(DrawSurface d);

    boolean shouldStop();
}
