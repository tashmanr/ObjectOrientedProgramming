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
import sprites.backgrounds.BackgroundLevelOne;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LevelOne implements LevelInformation {

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
        double speed = 6;
        double angle = 0; // ball should start going straight up
        v.add(Velocity.fromAngleAndSpeed(angle, speed));
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
        blocks.add(new Block(new Rectangle(new Point(385, 175), blockWidth, blockHeight), Color.red));
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}
