/**
 * Rebecca Tashman
 * 336423124
 */

import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;

/**
 * Class for paddle, which implements Sprite and Collidable interfaces.
 */
public class Paddle implements Sprite, Collidable {
    private KeyboardSensor keyboard;
    private Rectangle paddle;
    private static double epsilon = Math.pow(10, -15);
    private static int gameBorderWidth = 25;
    private static double guiWidth;
    private double startX;
    private static double y;
    private static int paddleHeight = 20;
    private static int paddleWidth = 70;
    private static int paddleMovement = 10;

    /**
     * Constructor to create a new paddle.
     * @param gui from the game
     */
    public Paddle(GUI gui) {
        double guiHeight = gui.getDrawSurface().getHeight();
        guiWidth = gui.getDrawSurface().getWidth();
        y = guiHeight - gameBorderWidth - paddleHeight;
        startX = gameBorderWidth;
        this.paddle = new Rectangle(new Point(startX, y), paddleWidth, paddleHeight);
        this.keyboard = gui.getKeyboardSensor();
    }

    /**
     * Function to move the paddle left for paddleMovement pixels.
     * In the function, we check if the movement would go into the border, if it does then place paddle as far left as
     * possible before the border.
     */
    public void moveLeft() {
        if (this.paddle.getUpperLeft().getX() <= paddleMovement + startX) {
            this.paddle = new Rectangle(new Point(startX, y), paddleWidth, paddleHeight);
        } else {
            this.paddle = new Rectangle(new Point(this.paddle.getUpperLeft().getX() - paddleMovement, y),
                    paddleWidth, paddleHeight);
        }
    }

    /**
     * Function to move the paddle right for paddleMovement pixels.
     * In the function, we check if the movement would go into the border, if it does then place paddle as far right as
     * possible before the border.
     */
    public void moveRight() {
        if (this.paddle.getUpperLeft().getX() >= (guiWidth - gameBorderWidth) - paddleWidth) {
            this.paddle = new Rectangle(new Point((guiWidth - gameBorderWidth) - paddleWidth, y),
                    paddleWidth, paddleHeight);
        } else {
            this.paddle = new Rectangle(new Point(this.paddle.getUpperLeft().getX() + paddleMovement, y),
                    paddleWidth, paddleHeight);
        }
    }

    /**
     * Function from Sprite interface, to animate and "move" the paddle based on key presses.
     */
    @Override
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * Function to draw the paddle onto the DrawSurface.
     * @param d DrawSurface
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.yellow);
        d.fillRectangle((int) this.paddle.getUpperLeft().getX(), (int) this.paddle.getUpperLeft().getY(),
                (int) this.paddle.getWidth(), (int) this.paddle.getHeight());
        d.setColor(Color.black);
        d.drawRectangle((int) this.paddle.getUpperLeft().getX(), (int) this.paddle.getUpperLeft().getY(),
                (int) this.paddle.getWidth(), (int) this.paddle.getHeight());
    }

    /**
     * Function from collidable interface, to return the collision rectangle.
     * @return rectangle
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.paddle;
    }

    /**
     * Function from Collidable interface to return the new velocity after collision.
     * @param collisionPoint location of collision
     * @param currentVelocity velocity of the object hitting the collidable
     * @return velocity
     */
    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        Velocity v = currentVelocity;
        double locationOnPaddle = collisionPoint.getX() - paddle.getUpperLeft().getX();
        double fifthOfPaddle = paddle.getWidth() / 5; //divide the paddle to five sections
        if (Math.abs(collisionPoint.getY() - y) > epsilon) { //if the ball hits the side of the paddle, treat like block
            if (Math.abs(collisionPoint.getX() - paddle.getUpperLeft().getX()) < epsilon
                    || Math.abs(collisionPoint.getX() - (paddle.getUpperLeft().getX() + paddleWidth)) < epsilon) {
                v = new Velocity(-v.getDx(), v.getDy());
            }
        } else if (locationOnPaddle >= (2 * fifthOfPaddle) && locationOnPaddle < (3 * fifthOfPaddle)) {
            v = new Velocity(v.getDx(), -v.getDy()); // middle section
        } else {
            double angle;
            if (locationOnPaddle < fifthOfPaddle) {
                angle = 300; // first section
            } else if (locationOnPaddle >= fifthOfPaddle && locationOnPaddle < (2 * fifthOfPaddle)) {
                angle = 330; // second section
            } else if (locationOnPaddle >= (3 * fifthOfPaddle) && locationOnPaddle < (4 * fifthOfPaddle)) {
                angle = 30; // fourth section
            } else {
                angle = 60; // fifth section
            }
            v = Velocity.fromAngleAndSpeed(angle, currentVelocity.getSpeed());
        }
        return v;
    }

    /**
     * Function to add the paddle to the game.
     * @param g game
     */
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}