/**
 * 336423124
 * Rebecca Tashman
 */

/**
 * This class generates velocity, and has functions to access the x and y fields.
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * Function calculates new velocity from angle and speed according to the y axis.
     *
     * @param angle of direction
     * @param speed of movement
     * @return new velocity in terms of dx and dy
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.sin(Math.toRadians(angle));
        double dy = speed * Math.cos(Math.toRadians(angle));
        return new Velocity(dx, dy);
    }

    /**
     * Constructor.
     *
     * @param dx direction for movement on x axis
     * @param dy direction for movement on y axis
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    // accessors

    /**
     * Function to access dx.
     *
     * @return dx
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Function to access dy.
     *
     * @return dy
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Function to access the speed.
     *
     * @return double speed
     */
    public double getSpeed() {
        return Math.sqrt((dx * dx) + (dy * dy));
    }

    /**
     * Function to take a point with position (x,y) and return a new point with position (x+dx, y+dy).
     *
     * @param p point
     * @return new point
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }
}