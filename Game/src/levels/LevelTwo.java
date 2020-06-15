/**
 * 336423124
 * Rebecca Tashman
 */
package levels;

import ballinfo.Velocity;
import geometryprimatives.Point;
import geometryprimatives.Rectangle;
import interfaces.LevelInformation;
import interfaces.Sprite;
import sprites.Block;
import sprites.backgrounds.BackgroundLevelTwo;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LevelTwo implements LevelInformation {
    @Override
    public int getBorderDepth() {
        return 20;
    }

    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> v = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < this.numberOfBalls(); i++) {
            double speed = 6;
            double angle = random.nextInt(360);
            v.add(Velocity.fromAngleAndSpeed(angle, speed));
        }
        return v;
    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth() {
        return 500;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        return new BackgroundLevelTwo();
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        double blockWidth = (double)(800-(2*getBorderDepth()))/15;
        int blockHeight = 30;
        Rectangle rectangle = new Rectangle(new Point(getBorderDepth(), 250), blockWidth, blockHeight);
        for (int i = 0; i < 15; i++) {
            Color color;
            if (i < 2) {
                color = Color.red;
            } else if (i < 4) {
                color = Color.orange;
            } else if (i < 6) {
                color = Color.yellow;
            } else if (i < 9) {
                color = Color.green;
            } else if (i < 11) {
                color = Color.blue;
            } else if (i < 13) {
                color = Color.pink;
            } else {
                color = Color.cyan;
            }
            blocks.add(new Block(rectangle, color));
            rectangle = new Rectangle(new Point(rectangle.getUpperLeft().getX() + blockWidth,
                    rectangle.getUpperLeft().getY()), blockWidth, blockHeight);
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}
