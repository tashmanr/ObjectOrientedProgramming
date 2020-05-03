/**
 * 336423124
 * Rebecca Tashman
 */

import biuoop.DrawSurface;

import java.awt.Color;
import java.util.Random;

/**
 * This class generates a new ball, and has functions to access data within as well as draw it on a surface.
 */
public class Ball {
    private Point center;
    private int r; //radius
    private java.awt.Color color;
    private Velocity v;
    private GameEnvironment g;

    // constructors

    /**
     * Constructor 1.
     *
     * @param center of ball
     * @param r      - radius
     * @param color  of ball
     * @param g      gameEnvironment
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment g) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.g = g;
        this.v = new Velocity(0, 0); // all balls are initialized with velocity = 0
    }

    /**
     * Constructor 2.
     *
     * @param x     value of center of ball
     * @param y     value of center of ball
     * @param r     - radius
     * @param color of ball
     * @param g     gameEnvironment
     */
    public Ball(double x, double y, int r, java.awt.Color color, GameEnvironment g) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
        this.g = g;
        this.v = new Velocity(0, 0); // all balls are initialized with velocity = 0
    }

    /**
     * Constructor 3 - doesn't receive color of ball in arguments, calls to randomColorGenerator.
     *
     * @param center of ball
     * @param r      - radius
     * @param g      gameEnvironment
     */
    public Ball(Point center, int r, GameEnvironment g) {
        this.center = center;
        this.r = r;
        this.color = randomColorGenerator();
        this.g = g;
        this.v = new Velocity(0, 0); // all balls are initialized with velocity = 0
    }

    /**
     * Constructor 4 - doesn't receive color of ball in arguments, calls to randomColorGenerator.
     *
     * @param x value of center of ball
     * @param y value of center of ball
     * @param r - radius
     * @param g gameEnvironment
     */
    public Ball(double x, double y, int r, GameEnvironment g) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = randomColorGenerator();
        this.g = g;
        this.v = new Velocity(0, 0); // all balls are initialized with velocity = 0
    }

    // accessors

    /**
     * Function to access x value of center of ball.
     *
     * @return x value
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Function to access y value of center of ball.
     *
     * @return y value
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Function to access radius of ball.
     *
     * @return r value
     */
    public int getSize() {
        return this.r;
    }

    /**
     * Function to access velocity of ball.
     *
     * @return v value
     */
    public Velocity getV() {
        return this.v;
    }

    /**
     * Function to access color of ball.
     *
     * @return color
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Function to access game environment.
     *
     * @return gameEnvironment
     */
    public GameEnvironment getGameEnvironment() {
        return this.g;
    }

    /**
     * Function to draw the ball on the given DrawSurface.
     *
     * @param surface to be drawn on
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.r);
    }

    /**
     * Function to generate a random color for the ball.
     *
     * @return color
     */
    public static java.awt.Color randomColorGenerator() {
        Random rand = new Random();
        float color1 = rand.nextFloat();
        float color2 = rand.nextFloat();
        float color3 = rand.nextFloat();
        Color color = new Color(color1, color2, color3);
        return color;
    }

    /**
     * Function to set the ball's velocity.
     *
     * @param v1 velocity to set
     */
    public void setVelocity(Velocity v1) {
        this.v = v1;
    }

    /**
     * Function to set the ball's velocity.
     *
     * @param dx difference for x coordinate
     * @param dy difference for y coordinate
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }

    /**
     * Function to move the ball once using applyToPoint function.
     */
    public void moveOneStep() {
        Line trajectory = new Line(center, v.applyToPoint(center));
        CollisionInfo tmp = g.getClosestCollision(trajectory);
        if (tmp == null) {
            center = v.applyToPoint(center);
        } else {
            double tmpX = tmp.collisionPoint().getX();
            double tmpY = tmp.collisionPoint().getY();
            if (v.getDx() > 0) {
                tmpX = tmp.collisionPoint().getX() - 1;
            } else if (v.getDx() < 0) {
                tmpX = tmp.collisionPoint().getX() + 1;
            }
            if (v.getDy() > 0) {
                tmpY = tmp.collisionPoint().getY() - 1;
            } else if (v.getDy() < 0) {
                tmpY = tmp.collisionPoint().getY() + 1;
            }
            center = new Point(tmpX, tmpY);
            v = tmp.collisionObject().hit(center, v);
        }
    }

    /**
     * Function to move the ball once using applyToPoint function.
     * The ball is limited by a certain width and height, so if it gets to the limit
     * it will need to "bounce off" in the other direction.
     *
     * @param width  limit
     * @param height limit
     */
    public void moveOneStep(double width, double height) {
        Point destination = new Point(0, 0);
        destination = this.v.applyToPoint(this.center);
        if ((this.center.getX() - this.r >= 0 && destination.getX() - this.r < 0)
                || (this.center.getX() + this.r <= width && destination.getX() + this.r > width)) {
            this.v = new Velocity(-this.v.getDx(), this.v.getDy());
        }
        if ((this.center.getY() - this.r >= 0 && destination.getY() - this.r < 0)
                || (this.center.getY() + this.r <= height && destination.getY() + this.r > height)) {
            this.v = new Velocity(this.v.getDx(), -this.v.getDy());
        }
        this.center = this.v.applyToPoint(this.center);
    }

    /**
     * Function to move the ball once using applyToPoint function.
     * The ball is limited by certain coordinates, so if it gets to the limit
     * it will need to "bounce off" in the other direction.
     *
     * @param x1 minimum x coordinate
     * @param y1 minimum y coordinate
     * @param x2 maximum x coordinate
     * @param y2 maximum y coordinate
     */
    public void moveOneStep(double x1, double y1, double x2, double y2) {
        Point destination = this.v.applyToPoint(this.center);
        if ((this.center.getX() - this.r >= x1 && destination.getX() - this.r < x1)
                || (this.center.getX() + this.r <= x2 && destination.getX() + this.r > x2)) {
            this.v = new Velocity(-this.v.getDx(), this.v.getDy());
        }
        if ((this.center.getY() - this.r >= y1 && destination.getY() - this.r < y1)
                || (this.center.getY() + this.r <= y2 && destination.getY() + this.r > y2)) {
            this.v = new Velocity(this.v.getDx(), -this.v.getDy());
        }
        this.center = this.v.applyToPoint(this.center);
    }
}