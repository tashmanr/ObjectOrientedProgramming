/**
 * 336423124
 * Rebecca Tashman
 */
package animations;

import biuoop.DrawSurface;
import interfaces.Animation;

import java.awt.*;

public class PauseScreen implements Animation {

    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.lightGray);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(new Color(170, 119, 221));
        d.drawText(20, d.getHeight() / 2, "paused -- press space to continue", 50);
    }

    public boolean shouldStop() {
        return false;
    }
}
