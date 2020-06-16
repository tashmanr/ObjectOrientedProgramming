/**
 * 336423124
 * Rebecca Tashman
 */
package animations;

import biuoop.DrawSurface;
import gamesetup.Counter;
import interfaces.Animation;

import java.awt.*;

public class EndScreen implements Animation {
    private Counter score;
    private boolean dead;

    public EndScreen(Counter score, boolean dead) {
        this.score = score;
        this.dead = dead;
    }

    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.lightGray);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(new Color(170, 119, 221));
        int startPoint;
        if (!dead) {
            d.drawText(20, d.getHeight() / 2, "You win! ", 45);
            startPoint = 250;
        } else {
            d.drawText(20, d.getHeight() / 2, "Game Over! ", 45);
            startPoint = 300;
        }
        d.drawText(startPoint, d.getHeight() / 2, "Your score is " + Integer.toString(score.getValue()) + ".", 45);
    }

    public boolean shouldStop() {
        return false;
    }
}
