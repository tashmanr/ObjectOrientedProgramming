/**
 * 336423124
 * Rebecca Tashman
 */

import java.util.*;

/**
 * This class generates a new game environment.
 */
public class GameEnvironment {
    private List<Collidable> collidables = new ArrayList<Collidable>();

    // add the given collidable to the environment.
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }

    // Assume an object moving from line.start() to line.end().
    // If this object will not collide with any of the collidables
    // in this collection, return null. Else, return the information
    // about the closest collision that is going to occur.
    public CollisionInfo getClosestCollision(Line trajectory) {
        Point closest = null;
        double distance = 0;
        Collidable c2 = null;
        for (Collidable c : collidables) {
            Rectangle r = c.getCollisionRectangle();
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
        if (c2 == null) {
            return null;
        } else {
            return new CollisionInfo(closest, c2);
        }
    }
}