import java.util.List;

/**
 * 336423124
 * Rebecca Tashman
 */

public class CollisionInfo {
    private Point point;
    private Collidable collidable;

    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.point = collisionPoint;
        this.collidable = collisionObject;
    }

    public CollisionInfo(Line trajectory, List<Collidable> collidables) {
        Point closest = null;
        double distance = 0;
        Collidable c2 = null;
        for (Collidable c : collidables) {
            Rectangle r = c.getCollisionRectangle();
            if (r.intersectionPoints(trajectory).size() == 0) {
                continue;
            }
            Point tmp = trajectory.closestIntersectionToStartOfLine(r);
            if (closest == null) {
                closest = tmp;
                distance = trajectory.start().distance(closest);
                c2 = c;
            } else if (trajectory.start().distance(tmp) < distance) {
                closest = tmp;
                distance = trajectory.start().distance(closest);
                c2 = c;
            }
        }
        this.point = closest;
        this.collidable = c2;
    }

    // the point at which the collision occurs.
    public Point collisionPoint() {
        return this.point;
    }

    // the collidable object involved in the collision.
    public Collidable collisionObject() {
        return this.collidable;
    }
}