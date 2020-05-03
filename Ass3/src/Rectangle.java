/**
 * 336423124
 * Rebecca Tashman
 */

import java.util.*;

/**
 * This class generates a new rectangle, and has functions to access the fields.
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;

    // Create a new rectangle with location and width/height.
    public Rectangle(Point upperLeft, double width, double height){
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    // Return a (possibly empty) List of intersection points
    // with the specified line.
    public java.util.List<Point> intersectionPoints(Line line){
        ArrayList<Point> points = new ArrayList<Point>();
        Point tmp;
        Line top = new Line(upperLeft.getX(), upperLeft.getY(), upperLeft.getX()+width, upperLeft.getY());
        if (top.isIntersecting(line)) {
            points.add(top.intersectionWith(line));
        }
        Line bottom = new Line(upperLeft.getX(), upperLeft.getY()-height, upperLeft.getX()+width, upperLeft.getY()-height);
        if (bottom.isIntersecting(line)) {
            points.add(bottom.intersectionWith(line));
        }
        Line left = new Line(upperLeft.getX(), upperLeft.getY(), upperLeft.getX(), upperLeft.getY()-height);
        if (left.isIntersecting(line)) {
            tmp = left.intersectionWith(line);
            if (!points.contains(tmp)){
                points.add(tmp);
            }
        }
        Line right = new Line(upperLeft.getX()+width, upperLeft.getY(), upperLeft.getX()+width, upperLeft.getY()-height);
        if (right.isIntersecting(line)) {
            tmp = right.intersectionWith(line);
            if (!points.contains(tmp)){
                points.add(tmp);
            }
        }
        return points;
    }

    // Return the width and height of the rectangle
    public double getWidth(){
        return this.width;
    }
    public double getHeight(){
        return this.height;
    }

    // Returns the upper-left point of the rectangle.
    public Point getUpperLeft(){
        return this.upperLeft;
    }
}