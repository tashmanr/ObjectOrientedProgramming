/**
 * 336423124
 * Rebecca Tashman
 */

/**
 * This class generates a new line, and has functions to access the fields.
 */
public class Line {
    // Fields
    private Point start;
    private Point end;

    // constructors
    /**
     * Constructor 1 - receives two points and makes the start value = the point with the smallest x.
     * Other value will be end point.
     *
     * @param start point on line
     * @param end point on line
     */
    public Line(Point start, Point end) {
        if (start.getX() < end.getX()) {
            this.start = start;
            this.end = end;
        } else {
            this.end = start;
            this.start = end;
        }
    }
    /**
     * Constructor 2 - receives two points and makes the start value = the point with the smallest x.
     * Other value will be end point.
     *
     * @param x1 x value of first point
     * @param y1 y value of first point
     * @param x2 x value of second point
     * @param y2 y value of second point
     */
    public Line(double x1, double y1, double x2, double y2) {
        if (x1 < x2) {
            this.start = new Point(x1, y1);
            this.end = new Point(x2, y2);
        } else {
            this.start = new Point(x2, y2);
            this.end = new Point(x1, y1);
        }
    }

    /**
     * Function that returns the length of the line using distance function from point class.
     *
     * @return length
     */
    public double length() {
        return this.start.distance(end);
    }

    /**
     * Function that creates a point in the middle of the line.
     *
     * @return middle point
     */
    public Point middle() {
        double x = (start.getX() + end.getX()) / 2;
        double y = (start.getY() + end.getY()) / 2;
        Point middle = new Point(x, y);
        return middle;
    }

    //accessors
    /**
     * Function that returns the start point in the line.
     *
     * @return start point
     */
    public Point start() {
        return this.start;
    }
    /**
     * Function that returns the end point in the line.
     *
     * @return end point
     */
    public Point end() {
        return this.end;
    }

    /**
     * Function that checks if two lines intersect.
     *
     * @param other line to compare this line to
     * @return true if intersect, false if not
     */
    public boolean isIntersecting(Line other) {
        return !(this.intersectionWith(other) == null);
    }

    /**
     * Function that checks the intersection point of two lines.
     *
     * @param other line to compare this line to
     * @return intersection point if the lines intersect, and null otherwise.
     */
    public Point intersectionWith(Line other) {
        /**
         * Calculating the slope for both lines, if they have the same slope they do not intersect.
         */
        double otherSlope = (other.end().getY() - other.start().getY()) / (other.end().getX() - other.start().getX());
        double thisSlope = (this.end.getY() - this.start.getY()) / (this.end.getX() - this.start.getX());
        if (otherSlope == thisSlope) {
            return null;
        }
        /**
         * Calculating the axis intercept point for both lines, and then solving for the interseciton point.
         */
        double otherIntercept = other.start().getY() - (otherSlope * other.start().getX());
        double thisIntercept = this.start.getY() - (thisSlope * this.start.getX());
        double x = (thisIntercept - otherIntercept) / (otherSlope - thisSlope);
        double y = (thisSlope * x) + thisIntercept;
        Point intersectionPoint = new Point(x, y);
        /**
         * Checking that intersection point touches both lines (since the length is not infinite).
         */
        if ((this.start.getX() < x)
                && (this.end.getX() > x)
                && (other.start().getX() < x)
                && (other.end().getX() > x)) {
            return intersectionPoint;
        } else {
            return null;
        }
    }

    /**
     * Function that checks if two lines are equal.
     *
     * @param other line to compare this line to
     * @return true is the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        return (this.start == other.start && this.end == other.end);
    }
}