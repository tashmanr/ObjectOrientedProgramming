/**
 * 336423124
 * Rebecca Tashman
 */

import sprites.Ball;
import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;
import java.util.Random;

/**
 * This class generates an animation of multiple balls bouncing in different frames.
 */
public class MultipleFramesBouncingBallsAnimation {
    /**
     * Function to sort the balls by size so that when drawn the smaller balls will be in front (drawn last).
     * Use the halfway index to ensure that the balls are sorted within their respective frames.
     *
     * @param balls array
     * @param halfway index
     */
    public static void sortBalls(Ball[] balls, int halfway) {
        int n = balls.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (balls[j].getSize() < balls[j + 1].getSize()
                        && ((j < halfway - 1 || j >= halfway))) {
                    // swap temp and balls[i]
                    Ball temp = balls[j];
                    balls[j] = balls[j + 1];
                    balls[j + 1] = temp;
                }
            }
        }
    }

    /**
     * Main function, receives number of balls from command line and their corresponding sizes. Creates new GUI
     * surface and sets each balls velocity in proportion to its size. Splits the balls to two groups, the first half
     * in the first frame (gray, 450x450) and the second half in the second frame (yellow, 150x150).
     * Draws balls on surface and shows their movement while keeping them in their prospective frames.
     *
     * @param args - string of integers to be set as ball sizes
     */
    public static void main(String[] args) {
        Random rand = new Random();
        Ball[] balls = new Ball[args.length];
        GUI gui = new GUI("Multiple Bouncing Balls Animation", 700, 700);
        DrawSurface d = gui.getDrawSurface();
        int halfOfBalls;
        /**
         * If the number of balls is odd, then the bigger half will be in the first group.
         */
        if (args.length % 2 == 0) {
            halfOfBalls = args.length / 2;
        } else {
            halfOfBalls = (args.length + 1) / 2;
        }
        /**
         * Generate the balls, we receive size from command line but need to generate the starting point and velocity.
         */
        for (int i = 0; i < args.length; i++) {
            int r = Integer.parseInt(args[i]);
            int x;
            int y;
            if (i < halfOfBalls) {
                x = Math.max(rand.nextInt(500 - r), r + 50); // get integer in range of gray frame
                y = Math.max(rand.nextInt(500 - r), r + 50); // get integer in range of gray frame
            } else {
                x = Math.max(rand.nextInt(600 - r), r + 450); // get integer in range of yellow frame
                y = Math.max(rand.nextInt(600 - r), r + 450); // get integer in range of yellow frame
            }
            /**
             * Velocity is proportionate to the radius of the ball - the bigger the ball, the slower the movement.
             */
            double dx = 250 / r;
            double dy = 200 / r;
            balls[i] = new Ball(x, y, r);
            balls[i].setVelocity(dx, dy);
        }
        sortBalls(balls, halfOfBalls); // so that bigger balls in each frame will be behind the smaller balls
        Sleeper sleeper = new Sleeper();
        while (true) {
            d = gui.getDrawSurface();
            /**
             * Draw gray rectangle before drawing balls in that frame.
             */
            d.setColor(java.awt.Color.GRAY);
            d.fillRectangle(50, 50, 450, 450);
            for (int i = 0; i < halfOfBalls; i++) {
                balls[i].moveOneStep(50, 50, 500, 500);
                if (balls[i].getColor() == java.awt.Color.GRAY) {
                    balls[i] = new Ball(balls[i].getX(), balls[i].getY(), balls[i].getSize(), java.awt.Color.YELLOW);
                }
                balls[i].drawOn(d);
            }
            /**
             * Draw yellow rectangle before drawing balls in that frame.
             */
            d.setColor(java.awt.Color.YELLOW);
            d.fillRectangle(450, 450, 150, 150);
            for (int i = halfOfBalls; i  < args.length; i++) {
                balls[i].moveOneStep(450, 450, 600, 600);
                if (balls[i].getColor() == java.awt.Color.YELLOW) {
                    balls[i] = new Ball(balls[i].getX(), balls[i].getY(), balls[i].getSize(), java.awt.Color.GRAY);
                }
                balls[i].drawOn(d);
            }
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }

}