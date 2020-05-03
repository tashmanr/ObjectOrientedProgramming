/**
 * 336423124
 * Rebecca Tashman
 */

import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;
import java.util.Random;

/**
 * This class generates an animation of multiple balls bouncing.
 */
public class MultipleBouncingBallsAnimation {
    /**
     * Main function, receives number of balls from command line and their corresponding sizes. Creates new GUI
     * surface and sets each balls velocity in proportion to its size. Draws balls on surface and shows their movement
     *
     * @param args - string of integers to be set as ball sizes
     */
    public static void main(String[] args) {
        Random rand = new Random();
        Ball[] balls = new Ball[args.length];
        GUI gui = new GUI("Multiple Bouncing Balls Animation", 600, 600); // create new GUI 600x600 pixels
        DrawSurface d = gui.getDrawSurface();
        for (int i = 0; i < args.length; i++) {
            int r = Integer.parseInt(args[i]);
            int x = Math.max(rand.nextInt(d.getWidth() - r) + 1, r + 1); // get integer in range 1-frame limit
            int y = Math.max(rand.nextInt(d.getHeight() - r) + 1, r + 1); // get integer in range 1-frame limit
            /**
             * Velocity is proportionate to the radius of the ball - the bigger the ball, the slower the movement.
             */
            double dx = 300 / r;
            double dy = 200 / r;
            balls[i] = new Ball(x, y, r);
            balls[i].setVelocity(dx, dy);
        }
        Sleeper sleeper = new Sleeper();
        while (true) {
            d = gui.getDrawSurface();
            for (int i = 0; i < args.length; i++) {
                balls[i].moveOneStep(d.getWidth(), d.getHeight());
                balls[i].drawOn(d);
            }
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }
}