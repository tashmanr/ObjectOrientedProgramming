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

    public Paddle(GUI gui) {
        this.paddle = new Rectangle(new Point(0, 590), 100, 10);
        this.keyboard = gui.getKeyboardSensor();
    }

    public void moveLeft() {
        this.paddle = new Rectangle(new Point(this.paddle.getUpperLeft().getX() - 10, 590), 100, 10);
    }

    public void moveRight() {
        this.paddle = new Rectangle(new Point(this.paddle.getUpperLeft().getX() + 10, 590), 100, 10);
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
        d.setColor(Color.gray);
        d.fillRectangle((int) this.paddle.getUpperLeft().getX(), (int) this.paddle.getUpperLeft().getY(),
                (int) this.paddle.getWidth(), (int) this.paddle.getHeight());
    }

    // Collidable
    public Rectangle getCollisionRectangle() {
        return this.paddle;
    }

    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        Velocity v = currentVelocity;
        if (Math.abs(collisionPoint.getX() - this.paddle.getUpperLeft().getX()) < epsilon
                || Math.abs(collisionPoint.getX() - (this.paddle.getUpperLeft().getX() + this.paddle.getWidth())) < epsilon) {
            v = new Velocity(-v.getDx(), v.getDy());
        }
        if (Math.abs(collisionPoint.getY() - this.paddle.getUpperLeft().getY()) < epsilon
                || Math.abs(collisionPoint.getY() - (this.paddle.getUpperLeft().getY() + this.paddle.getHeight())) < epsilon) {
            v = new Velocity(v.getDx(), -v.getDy());
        }
        return v;
    }

    // Add this paddle to the game.
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}