package sprites.backgrounds;

import biuoop.DrawSurface;
import interfaces.Sprite;

import java.awt.*;

public class BackgroundLevelOne implements Sprite {

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.fillRectangle(0,0, 800, 600);
        d.setColor(Color.blue);
        d.drawCircle(400, 190, 50);
        d.drawCircle(400, 190, 100);
        d.drawCircle(400, 190, 150);
        d.drawLine(240, 190, 560, 190);
        d.drawLine(400, 30, 400, 350);
    }

    @Override
    public void timePassed() {
    }
}
