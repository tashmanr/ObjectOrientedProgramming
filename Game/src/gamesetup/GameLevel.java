/**
 * Rebecca Tashman
 * 336423124
 */
package gamesetup;

import animations.CountdownAnimation;
import animations.PauseScreen;
import biuoop.KeyboardSensor;
import hitlisteners.BallRemover;
import hitlisteners.BlockRemover;
import hitlisteners.ScoreTrackingListener;
import interfaces.Animation;
import interfaces.LevelInformation;
import sprites.Ball;
import sprites.Block;
import sprites.Paddle;
import biuoop.GUI;
import biuoop.DrawSurface;
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
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Counter blocks;
    private int blocksMaxAmount;
    private Counter balls;
    private Counter score;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    private LevelInformation levelInformation;
    private int scorePanelHeight;
    private int borderDepth;


    /**
     * Constructor.
     */
    public GameLevel(LevelInformation levelInformation) {
        this.levelInformation = levelInformation;
        borderDepth = levelInformation.getBorderDepth();
        sprites = new SpriteCollection();
        sprites.addSprite(levelInformation.getBackground());
        environment = new GameEnvironment();
        gui = new biuoop.GUI("Arkanoid", 800, 600);
        blocks = new Counter();
        blocksMaxAmount = 0;
        balls = new Counter();
        score = new Counter();
        runner = new AnimationRunner(gui);
        keyboard = gui.getKeyboardSensor();
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
        Paddle paddle = new Paddle(gui, levelInformation.paddleWidth(), levelInformation.paddleSpeed());
        paddle.addToGame(this);
        BlockRemover blockRemover = new BlockRemover(this, blocks);
        BallRemover ballRemover = new BallRemover(this, balls);
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(score);
        scorePanelHeight = 20; // to ensure that there is no overlap between the border blocks and the score panel
        ScoreIndicator scoreIndicator = new ScoreIndicator(scorePanelHeight, score);
        sprites.addSprite(scoreIndicator);
        //Creating the border blocks of the game
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
        for (Block b : levelInformation.blocks()) {
            b.addToGame(this);
            blocks.increase(1);
            b.addHitListener(blockRemover);
            b.addHitListener(scoreTrackingListener);
        }
        blocksMaxAmount = blocks.getValue();
        Random random = new Random();
        for (int i = 0; i < levelInformation.numberOfBalls(); i++) { // loop to create balls
            /**
             * start the ball on top of the paddle
             */
            double x = (double) (gui.getDrawSurface().getWidth())/2;;
            int y = 600 - borderDepth - 15;
            Ball b = new Ball(x, y, 4, Color.white);
            b.setVelocity(levelInformation.initialBallVelocities().get(i));
            b.setGameEnvironment(environment);
            b.addToGame(this);
            balls.increase(1);
        }
    }

    /**
     * Function to run the game -- start the animation loop.
     */
    public void run() {
        //this.runner.run(new CountdownAnimation(2, 3, sprites)); // countdown before turn starts
        this.running = true;
        this.runner.run(this);
        gui.close();
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
        this.levelInformation.getBackground().drawOn(d);
        this.sprites.drawAllOn(d);
        //draw the level name
        d.setColor(Color.black);
        d.drawText(500, scorePanelHeight - 5, "Level Name: " + levelInformation.levelName(), 15);
        this.sprites.notifyAllTimePassed();

        // stopping condition
        if (this.balls.getValue() == 0) {
            this.running = false;
        }
        if (blocksMaxAmount - this.blocks.getValue() >= levelInformation.numberOfBlocksToRemove()) {
            score.increase(100);
            this.running = false;
        }
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new PauseScreen(this.keyboard));
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }
}