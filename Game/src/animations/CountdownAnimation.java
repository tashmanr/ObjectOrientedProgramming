/**
 * 336423124
 * Rebecca Tashman
 */
package animations;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import gamesetup.SpriteCollection;
import interfaces.Animation;

import java.awt.Color;

/**
 * Countdown animation class - implements animation.
 */
public class CountdownAnimation implements Animation {
    private double seconds;
    private int max;
    private SpriteCollection game;
    private boolean stop;
    private int countdown;
    private String countdownString = "";

    /**
     * Constructor.
     *
     * @param numOfSeconds total time to be viewed
     * @param countFrom    countdown starter
     * @param game         game sprites to be shown behind the message
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection game) {
        seconds = numOfSeconds;
        max = countFrom;
        this.game = game;
        this.stop = false;
        countdown = countFrom;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        Sleeper sleeper = new biuoop.Sleeper();
        if (countdown == 0) {
            stop = true;
        }
        game.drawAllOn(d);
        d.setColor(new Color(170, 119, 221));
        d.drawText(d.getWidth() / 2, d.getHeight() / 2, Integer.toString(countdown), 100);
        countdown--;
        sleeper.sleepFor((long) (1000 * seconds / max));
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}