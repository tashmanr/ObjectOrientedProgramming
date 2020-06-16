/**
 * 336423124
 * Rebecca Tashman
 */
package sprites.backgrounds;

import biuoop.DrawSurface;
import interfaces.Sprite;
import java.awt.Color;

/**
 * Background level four - to be called on from within levelFour class.
 */
public class BackgroundLevelFour implements Sprite {

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(new Color(29, 78, 255));
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(new Color(173, 173, 173));
        for (int i = 100; i < 200; i += 10) {
            d.drawLine(i, 390, i - 30, 600);
        }
        for (int i = 600; i < 700; i += 10) {
            d.drawLine(i, 490, i - 30, 700);
        }
        d.fillCircle(605, 490, 20);
        d.fillCircle(105, 390, 20);
        d.setColor(new Color(174, 174, 174));
        d.fillCircle(120, 415, 22);
        d.fillCircle(620, 515, 22);
        d.setColor(new Color(155, 155, 155));
        d.fillCircle(140, 385, 27);
        d.fillCircle(640, 485, 27);
        d.setColor(new Color(131, 131, 131));
        d.fillCircle(170, 395, 30);
        d.fillCircle(150, 415, 20);
        d.fillCircle(670, 495, 30);
        d.fillCircle(650, 515, 20);
    }

    @Override
    public void timePassed() {
    }
}
