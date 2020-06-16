/**
 * 336423124
 * Rebecca Tashman
 */
package interfaces;

import ballinfo.Velocity;
import sprites.Block;
import java.util.List;

/**
 * Interface for level information, implemented by levels 1-4.
 */
public interface LevelInformation {
    /**
     * Function to access the borderDepth.
     * @return int border depth
     */
    int getBorderDepth();

    /**
     * Function that returns the number of balls.
     * @return int balls
     */
    int numberOfBalls();

    /**
     * Function that returns the initial velocity of each ball.
     * Note that initialBallVelocities().size() == numberOfBalls()
     * @return list of velocities
     */
    List<Velocity> initialBallVelocities();

    /**
     * Function that returns the paddle speed.
     * @return int speed
     */
    int paddleSpeed();

    /**
     * Function that returns the paddle width.
     * @return int width
     */
    int paddleWidth();

    /**
     * Function that returns the level name.
     * It will be displayed at the top of the screen.
     * @return string name
     */
    String levelName();

    /**
     * Function that returns a sprite with the background of the level.
     * @return sprite
     */
    Sprite getBackground();

    /**
     * Function that returns the blocks that make up the level, with each block's size, color, & location.
     * @return list of blocks
     */
    List<Block> blocks();

    /**
     * Function that returns the number of blocks that need to be removed to beat a round.
     * @return int number of blocks
     */
    int numberOfBlocksToRemove();
}
