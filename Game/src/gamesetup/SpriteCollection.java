/**
 * 336423124
 * Rebecca Tashman
 */
package gamesetup;

import java.util.ArrayList;
import java.util.List;
import biuoop.DrawSurface;
import interfaces.Sprite;

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
     * Function that removes the sprite from the list.
     * @param s sprite to be removed
     */
    public void removeSprite(Sprite s) {
        sprites.remove(s);
    }
    /**
     * Function that calls timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> tmpSprites = new ArrayList<Sprite>(this.sprites);
        for (Sprite s : tmpSprites) {
            s.timePassed();
        }
    }

    /**
     * Function that calls drawOn() on all sprites.
     * @param d drawSurface
     */
    public void drawAllOn(DrawSurface d) {
        List<Sprite> tmpSprites = new ArrayList<Sprite>(this.sprites);
        for (Sprite s : tmpSprites) {
            s.drawOn(d);
        }
    }
}