/**
 * 336423124
 * Rebecca Tashman
 */

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

    public Rectangle getCollisionRectangle(){
        return this.rectangle;
    }

    public Velocity hit(Point collisionPoint, Velocity currentVelocity){
        if (collisionPoint.getX() == this.rectangle.getUpperLeft().getX()
                || collisionPoint.getX() == this.rectangle.getUpperLeft().getX()+this.rectangle.getWidth()) {
            currentVelocity = new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }
        if (collisionPoint.getY() == this.rectangle.getUpperLeft().getY()
                || collisionPoint.getY() == this.rectangle.getUpperLeft().getY()-this.rectangle.getHeight()) {
            currentVelocity = new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        }
        return currentVelocity;
    }
}