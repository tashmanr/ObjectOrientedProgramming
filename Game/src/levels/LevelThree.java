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
import sprites.backgrounds.BackgroundLevelThree;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class for level three of game levels.
 */
public class LevelThree implements LevelInformation {
    @Override
    public int getBorderDepth() {
        return 20;
    }

    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> v = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < this.numberOfBalls(); i++) {
            double speed = 6;
            double angle = random.nextInt(180) - 90;
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
        return 100;
    }

    @Override
    public String levelName() {
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        return new BackgroundLevelThree();
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        double blockWidth = (double) (800 - (2 * getBorderDepth())) / 15;
        int blockHeight = 20;
        int max = 10;
        Rectangle rect = new Rectangle(new Point(800 - getBorderDepth() - blockWidth, 200), blockWidth,
                blockHeight);
        for (int i = 0; i < 5; i++) { // 5 rows of blocks
            Color color;
            if (i < 1) {
                color = Color.darkGray;
            } else if (i < 2) {
                color = Color.red;
            } else if (i < 3) {
                color = Color.yellow;
            } else if (i < 4) {
                color = Color.blue;
            } else {
                color = Color.lightGray;
            }
            for (int j = 0; j < max; j++) { // inner loop is for the number of blocks per row
                blocks.add(new Block(rect, color));
                rect = new Rectangle(new Point(rect.getUpperLeft().getX() - blockWidth,
                        rect.getUpperLeft().getY()), blockWidth, blockHeight);
            }
            rect = new Rectangle(new Point(800 - getBorderDepth() - blockWidth,
                    rect.getUpperLeft().getY() + blockHeight), blockWidth, blockHeight);
            max--;
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}
