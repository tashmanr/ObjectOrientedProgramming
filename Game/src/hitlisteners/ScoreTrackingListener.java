/**
 * 336423124
 * Rebecca Tashman
 */
package hitlisteners;

import gamesetup.Counter;
import interfaces.HitListener;
import sprites.Ball;
import sprites.Block;

/**
 * Score tracking listener class, implements hit listener.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Constructor.
     * @param scoreCounter counter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeHitListener(this);
        currentScore.increase(5);
    }
}
