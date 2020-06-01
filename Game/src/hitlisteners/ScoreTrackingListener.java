package hitlisteners;

import gamesetup.Counter;
import interfaces.HitListener;
import sprites.Ball;
import sprites.Block;

public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeHitListener(this);
        currentScore.increase(5);
    }
}
