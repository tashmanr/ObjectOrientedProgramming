/**
 * Rebecca Tashman
 * 336423124
 */
package gamesetup;

import hitlisteners.BallRemover;
import hitlisteners.BlockRemover;
import hitlisteners.ScoreTrackingListener;
import interfaces.Animation;
import sprites.Ball;
import sprites.Block;
import sprites.Paddle;
import ballinfo.Velocity;
import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;
import interfaces.Collidable;
import geometryprimatives.Point;
import geometryprimatives.Rectangle;
import interfaces.Sprite;
import sprites.ScoreIndicator;

import java.awt.Color;
import java.util.Random;

/**
 * Class for setting up and running the game.
 */
public class Game implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Counter blocks;
    private Counter balls;
    private Counter score;
    private AnimationRunner runner;
    private boolean running;

    /**
     * Constructor.
     */
    public Game() {
        sprites = new SpriteCollection();
        environment = new GameEnvironment();
        gui = new biuoop.GUI("Arkanoid", 800, 600);
        blocks = new Counter();
        balls = new Counter();
        score = new Counter();
        runner = new AnimationRunner(gui);
    }

    /**
     * Function to add collidable to the environment.
     *
     * @param c collidable to add
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * Function to add sprite to the environment.
     *
     * @param s sprite to add
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * Function to initialize a new game: create the Blocks and Ball (and Paddle) and add them to the game.
     */
    public void initialize() {
        Paddle paddle = new Paddle(gui);
        paddle.addToGame(this);
        BlockRemover blockRemover = new BlockRemover(this, blocks);
        BallRemover ballRemover = new BallRemover(this, balls);
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(score);
        int scorePanelHeight = 20; // to ensure that there is no overlap between the border blocks and the score panel
        ScoreIndicator scoreIndicator = new ScoreIndicator(scorePanelHeight, score);
        sprites.addSprite(scoreIndicator);
        //Creating the border blocks of the game
        int borderDepth = 25;
        Rectangle top = new Rectangle((new Point(0, scorePanelHeight)), 800, borderDepth); // top border
        Rectangle left = new Rectangle(new Point(0, scorePanelHeight),
                borderDepth, 600 - scorePanelHeight); // left border
        Rectangle right = new Rectangle(new Point(800 - borderDepth, scorePanelHeight),
                borderDepth, 600 - scorePanelHeight); // right border
        Rectangle[] borders = new Rectangle[]{top, left, right};
        for (Rectangle r : borders) {
            Block b = new Block(r, Color.gray);
            b.addToGame(this);
        }
        Rectangle bottom = new Rectangle(new Point(0, 600), 800 - borderDepth, 0); // death region
        Block bottomBlock = new Block(bottom);
        bottomBlock.addHitListener(ballRemover);
        bottomBlock.addToGame(this);
        //loop to make blocks
        int max = 12; // number of blocks in top row
        int blockWidth = 55;
        int blockHeight = 20;
        Rectangle firstRectangle = new Rectangle(new Point(right.getUpperLeft().getX(),
                right.getUpperLeft().getY() + 150), blockWidth, blockHeight);
        Block firstBlock = new Block(firstRectangle);
        for (int i = 0; i < 6; i++) { // outer loop is for the number of rows
            if (i != 0) {
                firstBlock = new Block(new Rectangle(new Point(right.getUpperLeft().getX(),
                        firstBlock.getCollisionRectangle().getUpperLeft().getY() + blockHeight),
                        blockWidth, blockHeight));
            }
            for (int j = 1; j < max; j++) { // inner loop is for the number of blocks per row
                Rectangle rect = new Rectangle(new Point(firstBlock.getCollisionRectangle().getUpperLeft().getX()
                        - blockWidth, firstBlock.getCollisionRectangle().getUpperLeft().getY()),
                        blockWidth, blockHeight);
                Block block = new Block(rect, firstBlock.getColor());
                block.addToGame(this);
                blocks.increase(1);
                block.addHitListener(blockRemover);
                block.addHitListener(scoreTrackingListener);
                firstBlock = block;
            }
            max--;
        }
        Random random = new Random();
        for (int i = 0; i < 3; i++) { // loop to create three balls
            /**
             * start the ball within the game screen, not on blocks.
             */
            int x = random.nextInt(100) + borderDepth;
            int y = random.nextInt(300) + borderDepth + scorePanelHeight;
            Ball b = new Ball(x, y, 5, Color.black);
            double speed = 6;
            double angle = random.nextInt(360);
            Velocity v = Velocity.fromAngleAndSpeed(angle, speed);
            b.setVelocity(v);
            b.setGameEnvironment(environment);
            b.addToGame(this);
            balls.increase(1);
        }
    }

    /**
     * Function to run the game -- start the animation loop.
     */
    public void run() {
       // this.createBallsOnTopOfPaddle();
        this.running=true;
        this.runner.run(this);
    }

    /**
     * Function to remove collidable from the environment.
     *
     * @param c collidable to remove
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * Function to remove sprite from the environment.
     *
     * @param s sprite to remove
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        // game-specific logic
        d.setColor(Color.blue); // filling the background
        d.fillRectangle(0, 0, gui.getDrawSurface().getWidth(), gui.getDrawSurface().getHeight());
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();

        // stopping condition
        if (this.balls.getValue() == 0) {
            this.running=false;
        }
        if (this.blocks.getValue() == 0) {
            score.increase(100);
            this.running=false;
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }
}