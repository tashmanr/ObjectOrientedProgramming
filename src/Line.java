/**
 * 336423124
 * Rebecca Tashman
 */

import java.util.List;

/**
 * This class generates a new line, and has functions to access the fields.
 */
public class Line {
    // Fields
    private Point start;
    private Point end;

    // constructors

    /**
     * Constructor 1 - receives two points and makes a line.
     *
     * @param start point on line
     * @param end   point on line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Constructor 2 - receives two points and makes a line.
     *
     * @param x1 x value of first point
     * @param y1 y value of first point
     * @param x2 x value of second point
     * @param y2 y value of second point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
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
        return new Point(x, y);
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
        if (this.equals(other)) { //if the lines are the same
            return null;
        }
        if (this.start.equals(this.end)) { //if this line is actually one point
            if (this.start.pointOnLine(other)) {
                return this.start;
            } else {
                return null;
            }
        }
        if (other.start().equals(other.end())) { //if other line is actually one point
            if (other.start().pointOnLine(this)) {
                return other.start();
            } else {
                return null;
            }
        }
        double otherSlope = (other.end().getY() - other.start().getY()) / (other.end().getX() - other.start().getX());
        double thisSlope = (this.end.getY() - this.start.getY()) / (this.end.getX() - this.start.getX());
        if (otherSlope == thisSlope) // if they have the same slope and no overlapping start/end they do not intersect
        {
            if (this.start.equals(other.end())) {
                if ((this.end.getY() > this.start.getY() && other.end().getY() > other.start().getY())
                        || (this.start.getY() > this.end.getY() && other.start().getY() > other.end().getY())) {
                    return this.start;
                } else {
                    return null;
                }
            } else if (this.start.equals(other.start())) {
                if ((this.end.getY() > this.start.getY() && other.end().getY() < other.start().getY())
                        || (this.start.getY() > this.end.getY() && other.start().getY() < other.end().getY())) {
                    return this.start;
                } else {
                    return null;
                }
            } else if (this.end.equals(other.start())) {
                if ((this.end.getY() > this.start.getY() && other.end().getY() > other.start().getY())
                        || (this.start.getY() > this.end.getY() && other.start().getY() > other.end().getY())) {
                    return this.start;
                } else {
                    return null;
                }
            } else if (this.end.equals(other.end())) {
                if ((this.end.getY() > this.start.getY() && other.end().getY() < other.start().getY())
                        || (this.start.getY() > this.end.getY() && other.start().getY() < other.end().getY())) {
                    return this.start;
                } else {
                    return null;
                }
            }
        }
        if (other.end().getX() == other.start().getX()) {
            double thisIntercept = this.start.getY() - (thisSlope * this.start.getX());
            double x = other.end().getX();
            double y = (thisSlope * x) + thisIntercept;
            Point intersectionPoint = new Point(x, y);
            if (intersectionPoint.pointOnLine(this) && intersectionPoint.pointOnLine(other)) {//is point on both lines
                return intersectionPoint;
            } else { // checking if the lines have a shared end point
                if (this.start.equals(other.end()) || this.start.equals(other.start())) {
                    return this.start;
                } else if (this.end.equals(other.start()) || this.end.equals(other.end())) {
                    return this.end;
                } else {
                    return null;
                }
            }
        }
        if (this.end.getX() == this.start.getX()) {
            double otherIntercept = other.start().getY() - (otherSlope * other.start().getX());
            double x = this.end.getX();
            double y = (otherSlope * x) + otherIntercept;
            Point intersectionPoint = new Point(x, y);
            if (intersectionPoint.pointOnLine(this) && intersectionPoint.pointOnLine(other)) {//is point on both lines
                return intersectionPoint;
            } else {
                return null;
            }
        }
        /**
         * checking if the two lines share an end point but are not overlapping
         */
        if (this.start.equals(other.start()) || this.start.equals(other.end())) {
            return this.start;
        } else if (this.end.equals(other.start()) || this.end.equals(other.end())) {
            return this.end;
        }
        if (other.end().getY() == other.start().getY()) {
            double thisIntercept = this.start.getY() - (thisSlope * this.start.getX());
            double y = other.end().getY();
            double x = (y - thisIntercept) / thisSlope;
            Point intersectionPoint = new Point(x, y);
            if (intersectionPoint.pointOnLine(this) && intersectionPoint.pointOnLine(other)) {//is point on both lines
                return intersectionPoint;
            } else {
                return null;
            }
        }
        if (this.end.getY() == this.start.getY()) {
            double otherIntercept = other.start().getY() - (otherSlope * other.start().getX());
            double y = this.end.getY();
            double x = (y - otherIntercept) / otherSlope;
            Point intersectionPoint = new Point(x, y);
            if (intersectionPoint.pointOnLine(this) && intersectionPoint.pointOnLine(other)) {//is point on both lines
                return intersectionPoint;
            } else {
                return null;
            }
        }
        /**
         * Calculating the axis intercept point for both lines, and then solving for the intersection point.
         */
        double otherIntercept = other.start().getY() - (otherSlope * other.start().getX());
        double thisIntercept = this.start.getY() - (thisSlope * this.start.getX());
        double x = (thisIntercept - otherIntercept) / (otherSlope - thisSlope);
        double y = (thisSlope * x) + thisIntercept;
        Point intersectionPoint = new Point(x, y);
        if (intersectionPoint.pointOnLine(this) && intersectionPoint.pointOnLine(other)) { // is point on both lines
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

    /**
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the
     * start of the line.
     *
     * @param rect to compare this line to
     * @return see above description
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> intersectionPoints = rect.intersectionPoints(this);
        Point tmpPoint = null;
        double tmpDistance = 0;
        for (Point p : intersectionPoints) {
            if (tmpPoint == null) {
                tmpPoint = p;
                tmpDistance = this.start.distance(tmpPoint);
            } else {
                if (this.start.distance(p) < tmpDistance) {
                    tmpPoint = p;
                    tmpDistance = this.start.distance(tmpPoint);
                }
            }
        }
        return tmpPoint;
    }
}