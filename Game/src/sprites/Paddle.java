/**
 * Rebecca Tashman
 * 336423124
 */
package sprites;

import ballinfo.Velocity;
import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import gamesetup.GameLevel;
import interfaces.Collidable;
import geometryprimatives.Point;
import geometryprimatives.Rectangle;
import interfaces.Sprite;
import java.awt.Color;

/**
 * Class for paddle, which implements interfaces.Sprite and collisiondetection.Collidable interfaces.
 */
public class Paddle implements Sprite, Collidable {
    private KeyboardSensor keyboard;
    private Rectangle paddleVisual;
    private Rectangle paddleForHit;
    private static double epsilon = Math.pow(10, -15);
    private static int gameBorderWidth = 25;
    private static double guiWidth;
    private double startX;
    private static double y;
    private static int paddleHeight = 10;
    private int paddleWidth;
    private int paddleSpeed;

    /**
     * Constructor to create a new paddle.
     * We have two rectangles, one for the drawsurface (is thicker) and one that has height of 0, for hits.
     *
     * @param gui from the game
     */
    public Paddle(GUI gui, int paddleWidth, int paddleSpeed) {
        double guiHeight = gui.getDrawSurface().getHeight();
        guiWidth = gui.getDrawSurface().getWidth();
        y = guiHeight - gameBorderWidth - paddleHeight;
        startX = (double) gui.getDrawSurface().getWidth()/2 - (double) paddleWidth/2;
        this.paddleSpeed = paddleSpeed;
        this.paddleWidth = paddleWidth;
        this.paddleVisual = new Rectangle(new Point(startX, y), paddleWidth, paddleHeight);
        this.paddleForHit = new Rectangle(new Point(startX, y), paddleWidth, 0);
        this.keyboard = gui.getKeyboardSensor();
    }

    /**
     * Function to move the paddle left for paddleSpeed pixels.
     * In the function, we check if the movement would go into the border, if it does then place paddle as far left as
     * possible before the border.
     */
    public void moveLeft() {
        if (this.paddleForHit.getUpperLeft().getX() <= paddleSpeed + gameBorderWidth) {
            this.paddleForHit = new Rectangle(new Point(gameBorderWidth, y), paddleWidth, 0);
            this.paddleVisual = new Rectangle(new Point(gameBorderWidth, y), paddleWidth, paddleHeight);

        } else {
            this.paddleForHit = new Rectangle(new Point(this.paddleForHit.getUpperLeft().getX() - paddleSpeed, y),
                    paddleWidth, 0);
            this.paddleVisual = new Rectangle(new Point(this.paddleVisual.getUpperLeft().getX() - paddleSpeed, y),
                    paddleWidth, paddleHeight);
        }
    }

    /**
     * Function to move the paddle right for paddleSpeed pixels.
     * In the function, we check if the movement would go into the border, if it does then place paddle as far right as
     * possible before the border.
     */
    public void moveRight() {
        if (this.paddleForHit.getUpperLeft().getX() >= (guiWidth - gameBorderWidth) - paddleWidth) {
            this.paddleForHit = new Rectangle(new Point((guiWidth - gameBorderWidth) - paddleWidth, y),
                    paddleWidth, 0);
            this.paddleVisual = new Rectangle(new Point((guiWidth - gameBorderWidth) - paddleWidth, y),
                    paddleWidth, paddleHeight);
        } else {
            this.paddleForHit = new Rectangle(new Point(this.paddleForHit.getUpperLeft().getX() + paddleSpeed, y),
                    paddleWidth, 0);
            this.paddleVisual = new Rectangle(new Point(this.paddleVisual.getUpperLeft().getX() + paddleSpeed, y),
                    paddleWidth, paddleHeight);
        }
    }

    @Override
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.yellow);
        d.fillRectangle((int) this.paddleVisual.getUpperLeft().getX(), (int) this.paddleVisual.getUpperLeft().getY(),
                (int) this.paddleVisual.getWidth(), (int) this.paddleVisual.getHeight());
        d.setColor(Color.black);
        d.drawRectangle((int) this.paddleVisual.getUpperLeft().getX(), (int) this.paddleVisual.getUpperLeft().getY(),
                (int) this.paddleVisual.getWidth(), (int) this.paddleVisual.getHeight());
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.paddleForHit;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double locationOnPaddle = collisionPoint.getX() - paddleForHit.getUpperLeft().getX();
        double fifthOfPaddle = paddleForHit.getWidth() / 5; //divide the paddle to five sections
        if (currentVelocity.getDy() <= 0) {
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        } else if (locationOnPaddle >= (2 * fifthOfPaddle) && locationOnPaddle < (3 * fifthOfPaddle)) {
            currentVelocity = new Velocity(currentVelocity.getDx(), -currentVelocity.getDy()); // middle section
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
            currentVelocity = Velocity.fromAngleAndSpeed(angle, currentVelocity.getSpeed());
        }
        return currentVelocity;
    }

    /**
     * Function to add the paddle to the game.
     * @param g game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}