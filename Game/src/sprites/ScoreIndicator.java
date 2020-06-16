/**
 * 336423124
 * Rebecca Tashman
 */
package sprites;

import biuoop.DrawSurface;
import gamesetup.Counter;
import interfaces.Sprite;
import java.awt.Color;

/**
 * Class for score indicator, to show the score in the gui.
 */
public class ScoreIndicator implements Sprite {
    private Counter score;
    private int scorePanelHeight;
    private String levelName;

    /**
     * Constructor.
     * @param height of the score indicator block to be drawn
     * @param c score
     * @param levelName string to display in the top bar
     */
    public ScoreIndicator(int height, Counter c, String levelName) {
        score = c;
        scorePanelHeight = height;
        this.levelName = levelName;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.lightGray);
        d.fillRectangle(0, 0, 800, scorePanelHeight);
        d.setColor(Color.black);
        d.drawText(350, scorePanelHeight - 5, "Score: " + Integer.toString(score.getValue()), 15);
        d.drawText(550, scorePanelHeight - 5, "Level Name: " + levelName, 15);
    }

    @Override
    public void timePassed() {
    }
}
