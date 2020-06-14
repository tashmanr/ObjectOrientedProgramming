/**
 * 336423124
 * Rebecca Tashman
 */
package animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
import gamesetup.SpriteCollection;
import interfaces.Animation;

public class CountdownAnimation implements Animation {
    private double seconds;
    private int max;
    private SpriteCollection gamescreen;
    private boolean stop;

    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        seconds = numOfSeconds;
        max = countFrom;
        this.gamescreen = gameScreen;
        this.stop = false;
    }

    public void doOneFrame(DrawSurface d) {
        Sleeper sleeper = new biuoop.Sleeper();
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000/framesPerSecond;
        for (int i = max; i > 0; i--){
            long startTime = System.currentTimeMillis(); // timing
            this.gamescreen.drawAllOn(d);
            d.drawText(10, d.getHeight() / 2, Integer.toString(i), 32);
            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
        stop = true;
    }

    public boolean shouldStop() {
        return this.stop;
    }
}