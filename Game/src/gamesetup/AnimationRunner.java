/**
 * 336423124
 * Rebecca Tashman
 */
package gamesetup;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import interfaces.Animation;

/**
 * Class for animation runner.
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    Sleeper sleeper;

    public AnimationRunner(GUI gui) {
        this.gui = gui;
        framesPerSecond = 60;
        sleeper = new biuoop.Sleeper();
    }

    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d);
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}