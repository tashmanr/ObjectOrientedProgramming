/**
 * 336423124
 * Rebecca Tashman
 */
package interfaces;

import biuoop.DrawSurface;

/**
 * Interface for interfaces.Sprite.
 */
public interface Sprite {
    /**
     * Function that draws the sprite on the drawSurface.
     * @param d drawSurface
     */
    void drawOn(DrawSurface d);

    /**
     * Function that notifies the sprite that time has passed.
     */
    void timePassed();
}