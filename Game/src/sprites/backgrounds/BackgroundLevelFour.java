/**
 * 336423124
 * Rebecca Tashman
 */
package sprites.backgrounds;

import biuoop.DrawSurface;
import interfaces.Sprite;
import java.awt.*;

public class BackgroundLevelFour implements Sprite {

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(new Color(29, 78, 255));
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(new Color(173, 173, 173));
        d.drawLine(110, 390, 80, 642);
        d.drawLine(120, 390, 80, 726);
        d.drawLine(130, 390, 80, 810);
        d.drawLine(140, 390, 80, 894);
        d.drawLine(150, 390, 80, 978);
        d.drawLine(160, 390, 80, 1062);
        d.drawLine(170, 390, 80, 1146);
        d.drawLine(180, 390, 80, 1230);
        d.drawLine(610, 490, 580, 742);
        d.drawLine(620, 490, 580, 826);
        d.drawLine(630, 490, 580, 910);
        d.drawLine(640, 490, 580, 994);
        d.drawLine(650, 490, 580, 1078);
        d.drawLine(660, 490, 580, 1162);
        d.drawLine(670, 490, 580, 1246);
        d.drawLine(680, 490, 580, 1330);
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
