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
public class Ball implements Sprite {
    private Point center;
    private int r; //radius
    private java.awt.Color color;
    private Velocity v;
    private GameEnvironment gameEnvironment;
    private static double epsilon = Math.pow(10, -15);

    // constructors

    /**
     * Constructor 1.
     *
     * @param center of ball
     * @param r      - radius
     * @param color  of ball
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.gameEnvironment = null;
        this.v = new Velocity(0, 0); // all balls are initialized with velocity = 0
    }

    /**
     * Constructor 2.
     *
     * @param x     value of center of ball
     * @param y     value of center of ball
     * @param r     - radius
     * @param color of ball
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
        this.gameEnvironment = null;
        this.v = new Velocity(0, 0); // all balls are initialized with velocity = 0
    }

    /**
     * Constructor 3 - doesn't receive color of ball in arguments, calls to randomColorGenerator.
     *
     * @param center of ball
     * @param r      - radius
     */
    public Ball(Point center, int r) {
        this.center = center;
        this.r = r;
        this.color = randomColorGenerator();
        this.gameEnvironment = null;
        this.v = new Velocity(0, 0); // all balls are initialized with velocity = 0
    }

    /**
     * Constructor 4 - doesn't receive color of ball in arguments, calls to randomColorGenerator.
     *
     * @param x value of center of ball
     * @param y value of center of ball
     * @param r - radius
     */
    public Ball(double x, double y, int r) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = randomColorGenerator();
        this.gameEnvironment = null;
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
     * Function to access game environment.
     *
     * @return gameEnvironment value
     */
    public GameEnvironment getGameEnvironment() {
        return gameEnvironment;
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
     * Function to draw the ball on the given DrawSurface.
     *
     * @param surface to be drawn on
     */
    @Override
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
     * Function to set the ball's game environment.
     *
     * @param g1 game environment to set
     */
    public void setGameEnvironment(GameEnvironment g1) {
        this.gameEnvironment = g1;
    }

    /**
     * Function to move the ball once using the gameEnvironment.
     */
    public void moveOneStep() {
        Line trajectory = new Line(center, v.applyToPoint(center));
        CollisionInfo tmpCollisionInfo = gameEnvironment.getClosestCollision(trajectory);
        double tmpX;
        double tmpY;
        if (tmpCollisionInfo != null) {
            Rectangle tmpRectangle = tmpCollisionInfo.collisionObject().getCollisionRectangle();
            tmpX = tmpCollisionInfo.collisionPoint().getX();
            tmpY = tmpCollisionInfo.collisionPoint().getY();
            if (tmpY - (tmpRectangle.getUpperLeft().getY() + tmpRectangle.getHeight()) < epsilon
                    && v.getDy() < 0) { //hits the block's bottom from below
                tmpY++;
            } else if (tmpRectangle.getUpperLeft().getY() - tmpY < epsilon
                    && v.getDy() > 0) { //hits the block's top from above
                tmpY--;
            }
            if (tmpX - (tmpRectangle.getUpperLeft().getX() + tmpRectangle.getWidth()) < epsilon
                    && v.getDx() < 0) { //hits the block's right side from the right
                tmpX++;
            } else if (tmpRectangle.getUpperLeft().getX() - tmpX < epsilon
                    && v.getDx() > 0) { //hits the block's left side from the left
                tmpX--;
            }
            this.setVelocity(tmpCollisionInfo.collisionObject().hit(tmpCollisionInfo.collisionPoint(), v));
            center = new Point(tmpX, tmpY);
        }
        center = v.applyToPoint(center);
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

    /**
     * Function for animation - calls on moveOneStep() function.
     */
    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * Function to add the ball to an existing game.
     * @param g game to add it to
     */
    public void addToGame(Game g) {
        g.addSprite(this);
    }
}