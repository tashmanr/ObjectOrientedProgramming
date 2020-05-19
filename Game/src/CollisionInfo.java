/**
 * 336423124
 * Rebecca Tashman
 */

/**
 * This class holds info about the collision.
 */
public class CollisionInfo {
    private Point point;
    private Collidable collidable;

    /**
     * Constructor.
     * @param collisionPoint coordinates of collision
     * @param collisionObject collidable object involved in collision
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.point = collisionPoint;
        this.collidable = collisionObject;
    }

    /**
     * Accessor to receive the point at which the collision occurs.
     * @return point of collision
     */
    public Point collisionPoint() {
        return this.point;
    }

    /**
     * Accessor for the collidable object involved in the collision.
     * @return collidable
     */
    public Collidable collisionObject() {
        return this.collidable;
    }
}