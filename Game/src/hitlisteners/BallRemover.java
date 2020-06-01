package hitlisteners;

import gamesetup.Counter;
import gamesetup.Game;
import interfaces.HitListener;
import sprites.Ball;
import sprites.Block;

public class BallRemover implements HitListener {
    private Game game;
    private Counter balls;

    public BallRemover(Game game, Counter balls) {
        this.game = game;
        this.balls = balls;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        game.removeSprite(hitter);
        balls.decrease(1);
    }
}
