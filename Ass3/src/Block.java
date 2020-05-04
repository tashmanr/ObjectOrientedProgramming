/**
 * 336423124
 * Rebecca Tashman
 */

import biuoop.DrawSurface;

/**
 * This class generates a new block, and has functions for the collidable interface.
 */
public class Block implements Collidable {
    private Rectangle rectangle;
    private java.awt.Color color;
    private static double epsilon = 0.00000000000000001;

    public Block(Rectangle rectangle1, java.awt.Color color) {
        this.rectangle = rectangle1;
        this.color = color;
    }

    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        Velocity v = currentVelocity;
        if (Math.abs(collisionPoint.getX() - this.rectangle.getUpperLeft().getX()) < epsilon
                || Math.abs(collisionPoint.getX() - (this.rectangle.getUpperLeft().getX() + this.rectangle.getWidth())) < epsilon) {
            v = new Velocity(-v.getDx(), v.getDy());
        }
        if (Math.abs(collisionPoint.getY() - this.rectangle.getUpperLeft().getY()) < epsilon
                || Math.abs(collisionPoint.getY() - (this.rectangle.getUpperLeft().getY() + this.rectangle.getHeight())) < epsilon) {
            v = new Velocity(v.getDx(), -v.getDy());
        }
        return v;
    }

    /**
     * Function to draw the block on the given DrawSurface.
     *
     * @param surface to be drawn on
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
    }
}