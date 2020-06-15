/**
 * 336423124
 * Rebecca Tashman
 */
package hitlisteners;

import gamesetup.Counter;
import gamesetup.GameLevel;
import interfaces.HitListener;
import sprites.Ball;
import sprites.Block;

/**
 * BlockRemover class removes blocks from the game,and keeps count of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * Constructor.
     * @param gameLevel that has the blocks to keep track of.
     * @param removedBlocks - the total blocks currently in the game
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = removedBlocks;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(this.gameLevel);
        beingHit.removeHitListener(this);
        remainingBlocks.decrease(1);
    }
}
