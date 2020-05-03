/**
 * 336423124
 * Rebecca Tashman
 */

/**
 * This class generates point, and has functions to access the x and y fields.
 */
public class Point {
    // Fields
    private double x;
    private double y;

    /**
     * Constructor.
     *
     * @param x coordinate
     * @param y coordinate
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Function that calculates the distance of this point to the other point.
     *
     * @param other point to compare to
     * @return distance
     */
    public double distance(Point other) {
        return Math.sqrt(((this.x - other.getX()) * (this.x - other.getX()))
                + ((this.y - other.getY()) * (this.y - other.getY())));
    }

    /**
     * Function that checks if two points are equal.
     *
     * @param other point to compare to
     * @return true is the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        boolean result = false;
        if ((this.x == other.getX()) && (this.y == other.getY())) {
            result = true;
        }
        return result;
    }

    // accessors
    /**
     * Access x value of this point.
     *
     * @return x
     */
    public double getX() {
        return this.x;
    }
    /**
     * Access y value of this point.
     *
     * @return y
     */
    public double getY() {
        return this.y;
    }
}