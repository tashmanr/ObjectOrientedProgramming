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
 * Ball remover class, implements hit listener.
 */
public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter balls;

    /**
     * Constructor.
     * @param gameLevel that the balls are in
     * @param balls counter to keep track
     */
    public BallRemover(GameLevel gameLevel, Counter balls) {
        this.gameLevel = gameLevel;
        this.balls = balls;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.gameLevel);
        balls.decrease(1);
    }
}
