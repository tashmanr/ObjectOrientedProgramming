package gamesetup;

import interfaces.HitListener;
import sprites.Ball;
import sprites.Block;

public class PrintingHitListener implements HitListener {
    private int becca;

    public PrintingHitListener() {
        becca = 1;
    }
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A Block was hit.");
    }
}