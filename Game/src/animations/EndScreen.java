/**
 * 336423124
 * Rebecca Tashman
 */
package animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import gamesetup.Counter;
import interfaces.Animation;

public class EndScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private Counter score;
    private boolean dead;

    public EndScreen(KeyboardSensor k, Counter score, boolean dead) {
        this.keyboard = k;
        this.stop = false;
        this.score = score;
        this.dead = dead;
    }

    public void doOneFrame(DrawSurface d) {
        if (!dead) {
            d.drawText(10, d.getHeight() / 2, "You win! ", 32);
        } else {
            d.drawText(10, d.getHeight() / 2, "Game Over. ", 32);
        }
        d.drawText(200, d.getHeight() / 2, "Your score is " + Integer.toString(score.getValue()), 32);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }

    public boolean shouldStop() {
        return this.stop;
    }
}
