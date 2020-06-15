/**
 * 336423124
 * Rebecca Tashman
 */
package sprites.backgrounds;

import biuoop.DrawSurface;
import interfaces.Sprite;

import java.awt.*;

public class BackgroundLevelThree implements Sprite {

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(new Color(0, 102, 0));
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.lightGray);
        Point corner = new Point(100, 450);
        int width = 110;
        int height = 150;
        d.fillRectangle((int) corner.getX(), (int) corner.getY(), width, height);
        d.setColor(Color.black);
        d.drawRectangle((int) corner.getX(), (int) corner.getY(), width, height);
        int startX = (int) corner.getX() + width / 5;
        int startY = (int) corner.getY() + height / 5;
        for (int i = 0; i < 5; i++) {
            d.drawLine((int) corner.getX(), startY, (int) corner.getX() + width, startY); // horizontal lines
            d.drawLine(startX, (int) corner.getY(), startX, (int) corner.getY() + height); // vertical lines
            startX += width / 5;
            startY += height / 5;
        }
        d.setColor(new Color(32, 26, 22));
        d.fillRectangle(145, 390, 20, 60);
        d.setColor(new Color(32, 29, 25));
        d.fillRectangle(150, 210, 10, 180);
        d.setColor(new Color(230, 155, 16));
        d.fillCircle(155, 210, 10);
        d.setColor(new Color(204, 51, 39));
        d.fillCircle(155, 210, 7);
        d.setColor(new Color(255, 224, 39));
        d.fillCircle(155, 210, 4);
    }

    @Override
    public void timePassed() {
    }
}
