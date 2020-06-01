/**
 * 336423124
 * Rebecca Tashman
 */
package sprites;

import ballinfo.Velocity;
import biuoop.DrawSurface;
import gamesetup.Game;
import interfaces.Collidable;
import geometryprimatives.Point;
import geometryprimatives.Rectangle;
import interfaces.HitListener;
import interfaces.HitNotifier;
import interfaces.Sprite;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class generates a new block, and has functions for the collidable interface.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rectangle;
    private java.awt.Color color;
    private static double epsilon = Math.pow(10, -15);
    List<HitListener> hitListeners;

    /**
     * Constructor for block.
     * @param rectangle1 receives rectangle for block
     * @param color receives color of block
     */
    public Block(Rectangle rectangle1, java.awt.Color color) {
        this.rectangle = rectangle1;
        this.color = color;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * Constructor for block. Doesn't receive color, so calls to randomColorGenerator to make one.
     * @param rectangle1 rectangle for block
     */
    public Block(Rectangle rectangle1) {
        this.rectangle = rectangle1;
        this.color = randomColorGenerator();
        this.hitListeners = new ArrayList<>();
    }

    /**
     * Function to generate a random color for the brick.
     *
     * @return color
     */
    public static java.awt.Color randomColorGenerator() {
        Random rand = new Random();
        float color1 = rand.nextFloat();
        float color2 = rand.nextFloat();
        float color3 = rand.nextFloat();
        Color color = new Color(color1, color2, color3);
        return color;
    }

    /**
     * Function to return the collision rectangle.
     * @return rectangle
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * Accessor to block's color.
     * @return color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Function to notify the Hitlisteners that the block was hit.
     * @param hitter the ball that hits the block
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Function to calculate the collision's impact on the ball's velocity.
     * @param collisionPoint point of collision between the block and the ball
     * @param currentVelocity ball's current velocity
     * @return new velocity after hit
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Velocity v = currentVelocity;
        /**
         * the next ifs check which side of the block is hit during the collision in order to change the dx & dy.
         */
        if (Math.abs(collisionPoint.getX() - this.rectangle.getUpperLeft().getX()) < epsilon
                || Math.abs(collisionPoint.getX()
                - (this.rectangle.getUpperLeft().getX() + this.rectangle.getWidth())) < epsilon) {
            v = new Velocity(-v.getDx(), v.getDy());
        }
        if (Math.abs(collisionPoint.getY() - this.rectangle.getUpperLeft().getY()) < epsilon
                || Math.abs(collisionPoint.getY()
                - (this.rectangle.getUpperLeft().getY() + this.rectangle.getHeight())) < epsilon) {
            v = new Velocity(v.getDx(), -v.getDy());
        }
        this.notifyHit(hitter);
        return v;
    }

    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
        surface.setColor(Color.black); // need to draw the frame of the block as well
        surface.drawRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
    }

    @Override
    public void timePassed() {
        return;
    }

    /**
     * Function from sprite, adds the block to the game received.
     * @param g game
     */
    public void addToGame(Game g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * Function that removes the block from the game received.
     * @param game to remove it from
     */
    public void removeFromGame(Game game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
}