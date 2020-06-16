/**
 * 336423124
 * Rebecca Tashman
 */
package animations;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import gamesetup.GameFlow;
import gamesetup.GameLevel;
import gamesetup.SpriteCollection;
import interfaces.Animation;

import java.awt.*;

public class CountdownAnimation implements Animation {
    private double seconds;
    private int max;
    private SpriteCollection game;
    private boolean stop;
    private int countdown;
    private String countdownString = "";

    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection game) {
        seconds = numOfSeconds;
        max = countFrom;
        this.game = game;
        this.stop = false;
        countdown = countFrom;
    }

    public void doOneFrame(DrawSurface d) {
        Sleeper sleeper = new biuoop.Sleeper();
        if (countdown == -1) {
            stop = true;
        }
        if (countdown == 0) {
            countdownString += "Go!";
        } else {
            countdownString += Integer.toString(countdown) + ", ";
        }
        game.drawAllOn(d);
        d.setColor(new Color(170, 119, 221));
        d.drawText(300, d.getHeight() / 2, countdownString, 50);
        countdown--;
        sleeper.sleepFor((long) (1000 * seconds / max));
    }

    public boolean shouldStop() {
        return this.stop;
    }
}