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
        if (collisionPoint.getX() == this.rectangle.getUpperLeft().getX()
                || collisionPoint.getX() == this.rectangle.getUpperLeft().getX() + this.rectangle.getWidth()) {
            v = new Velocity(-v.getDx(), v.getDy());
        }
        if (v != currentVelocity) {
            System.out.println(v + " " + currentVelocity);
        }
        if (collisionPoint.getY() == this.rectangle.getUpperLeft().getY()
                || collisionPoint.getY() == this.rectangle.getUpperLeft().getY() + this.rectangle.getHeight()) {
            v = new Velocity(v.getDx(), -v.getDy());
        }
        if (v != currentVelocity) {
            System.out.println(v + " " + currentVelocity);
        }
        return v;

        /*Point destination = this.v.applyToPoint(this.center);
        if ((this.center.getX() - this.r >= x1 && destination.getX() - this.r < x1)
                || (this.center.getX() + this.r <= x2 && destination.getX() + this.r > x2)) {
            this.v = new Velocity(-this.v.getDx(), this.v.getDy());
        }
        if ((this.center.getY() - this.r >= y1 && destination.getY() - this.r < y1)
                || (this.center.getY() + this.r <= y2 && destination.getY() + this.r > y2)) {
            this.v = new Velocity(this.v.getDx(), -this.v.getDy());
        }
        this.center = this.v.applyToPoint(this.center);*/
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