/**
 * 336423124.
 * Rebecca Tashman
 */
package interfaces;

import geometryprimatives.Point;
import geometryprimatives.Rectangle;
import ballinfo.Velocity;
import sprites.Ball;

public interface Collidable {
    /**
     * Return the "collision shape" of the object.
     * @return rectangle
     */
    Rectangle getCollisionRectangle();

    /**
     *  Notify the object that we collided with it at collisionPoint with a given velocity.
     *  The return is the new velocity expected after the hit (based on the force the object inflicted on us).
     * @param collisionPoint location of collision
     * @param currentVelocity velocity of the object hitting the collidable
     * @param hitter ball that's hitting the collidable
     * @return new velocity for the object that hit
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}