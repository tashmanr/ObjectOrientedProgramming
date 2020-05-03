/**
 * 336423124
 * Rebecca Tashman
 */

import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;

/**
 * This class generates an animation of one ball bouncing.
 */
public class BouncingBallAnimation {

    /**
     * Function to draw an animation of a ball.
     *
     * @param start - center of the ball
     * @param dx    - direction for x movement
     * @param dy    - direction for y movement
     */
    private static void drawAnimation(Point start, double dx, double dy) {
        GUI gui = new GUI("title", 200, 200);
        Sleeper sleeper = new Sleeper();
        Ball ball = new Ball(start.getX(), start.getY(), 30, java.awt.Color.BLACK);
        ball.setVelocity(dx, dy);
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            ball.moveOneStep(d.getWidth(), d.getHeight());
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }

    /**
     * Main function, calls on drawAnimation.
     *
     * @param args - string of integers to be set as x, y, dx, dy
     */
    public static void main(String[] args) {
        int x = Integer.parseInt(args[0]);
        int y = Integer.parseInt(args[1]);
        int dx = Integer.parseInt(args[2]);
        int dy = Integer.parseInt(args[3]);
        drawAnimation(new Point(x, y), dx, dy);
    }
}

