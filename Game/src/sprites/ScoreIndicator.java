package sprites;

import biuoop.DrawSurface;
import gamesetup.Counter;
import gamesetup.Game;
import hitlisteners.ScoreTrackingListener;
import interfaces.Sprite;

import java.awt.*;

public class ScoreIndicator implements Sprite {
    private Counter score;
    private int scorePanelHeight;

    public ScoreIndicator(int height, Counter c) {
        score = c;
        scorePanelHeight = height;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.lightGray);
        d.fillRectangle(0,0, 800, scorePanelHeight);
        d.setColor(Color.black);
        d.drawText(390, scorePanelHeight - 5, "Score: " + Integer.toString(score.getValue()), 15);
    }

    @Override
    public void timePassed() {
    }
}
