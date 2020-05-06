/**
 * 336423124
 * Rebecca Tashman
 */

import java.util.ArrayList;
import java.util.List;
import biuoop.DrawSurface;

/**
 * Class for spriteCollection - holds list of sprites.
 */
public class SpriteCollection {
    private List<Sprite> sprites = new ArrayList<Sprite>();

    /**
     * Constructor - creates new list of sprites.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<>();
    }

    /**
     * Function that adds the sprite to the list.
     * @param s sprite to be added
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }

    /**
     * Function that calls timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        for (Sprite s : sprites) {
            s.timePassed();
        }
    }

    /**
     * Function that calls drawOn() on all sprites.
     * @param d drawSurface
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : sprites) {
            s.drawOn(d);
        }
    }
}