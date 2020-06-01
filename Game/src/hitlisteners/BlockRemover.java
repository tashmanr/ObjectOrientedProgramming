/**
 * 336423124
 * Rebecca Tashman
 */
package hitlisteners;

import gamesetup.Counter;
import gamesetup.Game;
import interfaces.HitListener;
import sprites.Ball;
import sprites.Block;

/**
 * BlockRemover class is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private Game game;
    private Counter remainingBlocks;

    /**
     * Constructor.
     * @param game that has the blocks to keep track of.
     * @param removedBlocks - the total blocks currently in the game
     */
    public BlockRemover(Game game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(this.game);
        beingHit.removeHitListener(this);
        remainingBlocks.decrease(1);
    }
}
