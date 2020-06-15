package levels;

import ballinfo.Velocity;
import geometryprimatives.Point;
import geometryprimatives.Rectangle;
import interfaces.LevelInformation;
import interfaces.Sprite;
import sprites.Block;
import sprites.backgrounds.BackgroundLevelOne;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LevelThree implements LevelInformation {
    @Override
    public int getBorderDepth() {
        return 20;
    }

    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> v = new ArrayList<>();
        Random random = new Random();
        double speed = 6;
        double angle = random.nextInt(360);
        v.add(Velocity.fromAngleAndSpeed(angle, speed));
        return v;
    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth() {
        return 70;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        return new BackgroundLevelOne();
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        int blockWidth = 30;
        int blockHeight = 30;

     /*   Rectangle firstRectangle = new Rectangle(new Point(right.getUpperLeft().getX(),
                right.getUpperLeft().getY() + 150), blockWidth, blockHeight);
        Block firstBlock = new Block(firstRectangle);
        for (int i = 0; i < 6; i++) { // outer loop is for the number of rows
            if (i != 0) {
                firstBlock = new Block(new Rectangle(new Point(right.getUpperLeft().getX(),
                        firstBlock.getCollisionRectangle().getUpperLeft().getY() + blockHeight),
                        blockWidth, blockHeight));
            }
            for (int j = 1; j < max; j++) { // inner loop is for the number of blocks per row
                Rectangle rect = new Rectangle(new Point(firstBlock.getCollisionRectangle().getUpperLeft().getX()
                        - blockWidth, firstBlock.getCollisionRectangle().getUpperLeft().getY()),
                        blockWidth, blockHeight);
                Block block = new Block(rect, firstBlock.getColor());
                block.addToGame(this);
                blocks.increase(1);
                block.addHitListener(blockRemover);
                block.addHitListener(scoreTrackingListener);
                firstBlock = block;
            }
            max--;
        }
*/
        blocks.add(new Block(new Rectangle(new Point(385, 175), blockWidth, blockHeight), Color.red));
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}
