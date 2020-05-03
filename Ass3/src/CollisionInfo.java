/**
 * 336423124
 * Rebecca Tashman
 */

public class CollisionInfo {
    private Point point;
    private Collidable collidable;

    public CollisionInfo(Point collisionPoint, Collidable collisionObject){
        this.point = collisionPoint;
        this.collidable = collisionObject;
    }

    // the point at which the collision occurs.
    public Point collisionPoint(){
        return this.point;
    }

    // the collidable object involved in the collision.
    public Collidable collisionObject(){
        return this.collidable;
    }
}