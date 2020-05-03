/**
 * 336423124
 * Rebecca Tashman
 */

/**
 * This class generates a new block, and has functions for the collidable interface.
 */
public class Block extends Rectangle implements Collidable {
    private Rectangle rectangle;
    private static double epsilon = 0.00000000000000001;

    public Block(Point upperLeft, double width, double height) {
        this.rectangle = super(upperLeft, width, height);
    }

    public Rectangle getCollsionRectangle(){
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