/**
 * 336423124
 * Rebecca Tashman
 */
package sprites.backgrounds;

import biuoop.DrawSurface;
import interfaces.Sprite;

import java.awt.*;

public class BackgroundLevelTwo implements Sprite {

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.white);
        d.fillRectangle(0,0, 800, 600);
        d.setColor(new Color(237, 237, 129)); // outer circle light yellow
        d.fillCircle(150, 150, 60);
        for (int i = 0; i < 700; i+= 5) {
            d.drawLine(150, 150, i, 250);
        }
        d.setColor(new Color(221, 219, 81)); // mid circle dark yellow
        d.fillCircle(150, 150, 50);
        d.setColor(Color.yellow); // inner circle yellow
        d.fillCircle(150, 150, 40);
    }

    @Override
    public void timePassed() {
    }
}
