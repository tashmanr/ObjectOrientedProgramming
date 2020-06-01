package gamesetup; /**
 * 336423124
 * Rebecca Tashman
 */

import interfaces.Collidable;
import collision.CollisionInfo;
import geometryprimatives.Line;
import geometryprimatives.Point;
import geometryprimatives.Rectangle;

import java.util.ArrayList;
import java.util.List;

/**
 * This class generates a new game environment.
 */
public class GameEnvironment {
    private List<Collidable> collidables = new ArrayList<Collidable>();

    /**
     * Constructor to create a new game environment (create new array list for collidables).
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }

    /**
     * Function to add the given collidable to the environment.
     * @param c collidable to be added
     */
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }

    /**
     * Function to remove the given collidable from the environment.
     * @param c collidable to be removed
     */
    public void removeCollidable(Collidable c) {
        collidables.remove(c);
    }

    /**
     * Function - Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables in this collection, return null.
     * Else, return the information about the closest collision that is going to occur.
     *
     * @param trajectory of the ball
     * @return collisionInfo of the first object it will collide with
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Point closest = null;
        double distance = 0;
        Collidable c2 = null;
        List<Collidable> tempCollidables = new ArrayList<Collidable>(this.collidables);
        for (Collidable c : tempCollidables) { // loop to check all of the collidables
            Rectangle r = c.getCollisionRectangle();
            if (r.intersectionPoints(trajectory).size() == 0) { // if the object won't collide check the next one
                continue;
            }
            Point tmp = trajectory.closestIntersectionToStartOfLine(r);
            if (closest == null) { // finding the closest collision point
                closest = tmp;
                distance = trajectory.start().distance(closest);
                c2 = c;
            } else if (trajectory.start().distance(tmp) < distance) {
                closest = tmp;
                distance = trajectory.start().distance(closest);
                c2 = c;
            }
        }
        if (closest == null) {
            return null;
        } else {
            return new CollisionInfo(closest, c2);
        }
    }
}