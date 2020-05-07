/**
 * 336423124
 * Rebecca Tashman
 */

import java.util.ArrayList;

/**
 * This class generates a new rectangle, and has functions to access the fields.
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;

    /**
     * Constructor.
     * @param upperLeft location of upper left corner
     * @param width width of rectangle
     * @param height height of rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Function to return a (possibly empty) List of intersection points with the specified line.
     * @param line to compare with
     * @return list of points of intersection
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        ArrayList<Point> points = new ArrayList<Point>();
        Point tmp;
        Line top = new Line(upperLeft.getX(), upperLeft.getY(), upperLeft.getX() + width, upperLeft.getY());
        if (top.isIntersecting(line)) {
            points.add(top.intersectionWith(line));
        }
        Line bottom = new Line(upperLeft.getX(), upperLeft.getY() + height,
                upperLeft.getX() + width, upperLeft.getY() + height);
        if (bottom.isIntersecting(line)) {
            points.add(bottom.intersectionWith(line));
        }
        Line left = new Line(upperLeft.getX(), upperLeft.getY(), upperLeft.getX(), upperLeft.getY() + height);
        if (left.isIntersecting(line)) {
            tmp = left.intersectionWith(line);
            if (!points.contains(tmp)) { // if point already exists (corner point) in list don't add it again
                points.add(tmp);
            }
        }
        Line right = new Line(upperLeft.getX() + width, upperLeft.getY(),
                upperLeft.getX() + width, upperLeft.getY() + height);
        if (right.isIntersecting(line)) {
            tmp = right.intersectionWith(line);
            if (!points.contains(tmp)) { // if point already exists (corner point) in list don't add it again
                points.add(tmp);
            }
        }
        return points;
    }

    /**
     * Function to access the width of the rectangle.
     * @return double width
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Function to access the height of the rectangle.
     * @return double height
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Function to access the upper left corner of the rectangle.
     * @return point
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }
}