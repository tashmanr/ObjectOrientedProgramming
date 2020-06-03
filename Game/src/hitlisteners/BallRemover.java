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
 * Ball remover class, implements hit listener.
 */
public class BallRemover implements HitListener {
    private Game game;
    private Counter balls;

    /**
     * Constructor.
     * @param game that the balls are in
     * @param balls counter to keep track
     */
    public BallRemover(Game game, Counter balls) {
        this.game = game;
        this.balls = balls;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        balls.decrease(1);
    }
}
