/**
 * Rebecca Tashman
 * 336423124
 */

import biuoop.*;

import java.awt.*;

public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle paddle;
    private static double epsilon = 0.00000000000000001;
    private static int gameBorderWidth = 25;
    private static double guiHeight;
    private static double guiWidth;
    private double startX;
    private static double y;
    private static int paddleHeight = 20;
    private static int paddleWidth = 70;


    public Paddle(GUI gui) {
        guiHeight = gui.getDrawSurface().getHeight();
        guiWidth = gui.getDrawSurface().getWidth();
        y = guiHeight - gameBorderWidth - paddleHeight;
        startX = gameBorderWidth;
        this.paddle = new Rectangle(new Point(startX, y), paddleWidth, paddleHeight);
        this.keyboard = gui.getKeyboardSensor();
    }

    public void moveLeft() {
        if (this.paddle.getUpperLeft().getX() <= 10 + startX) {
            this.paddle = new Rectangle(new Point(startX, y), paddleWidth, paddleHeight);
        } else {
            this.paddle = new Rectangle(new Point(this.paddle.getUpperLeft().getX() - 10, y), paddleWidth, paddleHeight);
        }
    }

    public void moveRight() {
        if (this.paddle.getUpperLeft().getX() >= (guiWidth - gameBorderWidth) - paddleWidth) {
            this.paddle = new Rectangle(new Point((guiWidth - gameBorderWidth) - paddleWidth, y), paddleWidth, paddleHeight);
        } else {
            this.paddle = new Rectangle(new Point(this.paddle.getUpperLeft().getX() + 10, y), paddleWidth, paddleHeight);
        }
    }

    // Sprite
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    public void drawOn(DrawSurface d) {
        d.setColor(Color.yellow);
        d.fillRectangle((int) this.paddle.getUpperLeft().getX(), (int) this.paddle.getUpperLeft().getY(),
                (int) this.paddle.getWidth(), (int) this.paddle.getHeight());
        d.setColor(Color.black);
        d.drawRectangle((int) this.paddle.getUpperLeft().getX(), (int) this.paddle.getUpperLeft().getY(),
                (int) this.paddle.getWidth(), (int) this.paddle.getHeight());
    }

    // Collidable
    public Rectangle getCollisionRectangle() {
        return this.paddle;
    }

    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        Velocity v = currentVelocity;
        double locationOnPaddle = collisionPoint.getX() - paddle.getUpperLeft().getX();
        double fifthOfPaddle = paddle.getWidth() / 5;
        if (locationOnPaddle >= 3 * fifthOfPaddle && locationOnPaddle < 4 * fifthOfPaddle) {
            v = new Velocity(v.getDx(), -v.getDy()); // middle section
        } else if (Math.abs(collisionPoint.getY() - this.paddle.getUpperLeft().getY()) < epsilon
                || Math.abs(collisionPoint.getY() - (this.paddle.getUpperLeft().getY() + this.paddleHeight)) < epsilon) {
            v = new Velocity(v.getDx(), -v.getDy());
        } else {
            double angle;
            if (locationOnPaddle < 2 * fifthOfPaddle) {
                angle = 300; // first section
            } else if (locationOnPaddle >= 2 * fifthOfPaddle && locationOnPaddle < 3 * fifthOfPaddle) {
                angle = 330; // second section
            } else if (locationOnPaddle >= 4 * fifthOfPaddle && locationOnPaddle < 5 * fifthOfPaddle) {
                angle = 30; // fourth section
            } else {
                angle = 60; // fifth section
            }
            v = Velocity.fromAngleAndSpeed(angle, currentVelocity.getDx() / Math.sin(Math.toRadians(angle)));
        }
        return v;
    }

    // Add this paddle to the game.
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}