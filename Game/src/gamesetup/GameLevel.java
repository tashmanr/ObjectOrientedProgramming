/**
 * Rebecca Tashman
 * 336423124
 */
package gamesetup;

import animations.CountdownAnimation;
import animations.EndScreen;
import animations.KeyPressStoppableAnimation;
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
 * Class for setting up and running each gme level.
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
    private int borderDepth;
    private boolean blocksGone = false;

    /**
     * Constructor.
     * @param levelInformation the level to create
     * @param ks keyboard sensor to control the paddle, pause/end screens, etc
     * @param ar animation runner to animate the game
     * @param score score that is passed on through all the levels
     */
    public GameLevel(LevelInformation levelInformation, KeyboardSensor ks, AnimationRunner ar, Counter score) {
        this.levelInformation = levelInformation;
        borderDepth = levelInformation.getBorderDepth();
        sprites = new SpriteCollection();
        sprites.addSprite(levelInformation.getBackground());
        environment = new GameEnvironment();
        gui = ar.getGui();
        blocks = new Counter();
        blocksMaxAmount = 0;
        balls = new Counter();
        this.score = score;
        runner = ar;
        keyboard = ks;
        running = true;
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
        Paddle paddle = new Paddle(gui, levelInformation.paddleWidth(), levelInformation.paddleSpeed(),
                levelInformation.getBorderDepth());
        paddle.addToGame(this);
        BlockRemover blockRemover = new BlockRemover(this, blocks);
        BallRemover ballRemover = new BallRemover(this, balls);
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(score);
        int scorePanelHeight = 20; // to ensure that there is no overlap between the border blocks and the score panel
        ScoreIndicator scoreIndicator = new ScoreIndicator(scorePanelHeight, score, levelInformation.levelName());
        sprites.addSprite(scoreIndicator);
        //Creating the border blocks of the game
        Rectangle top = new Rectangle((new Point(0, scorePanelHeight)), gui.getDrawSurface().getWidth(),
                borderDepth); // top border
        Rectangle left = new Rectangle(new Point(0, scorePanelHeight),
                borderDepth, gui.getDrawSurface().getHeight() - scorePanelHeight); // left border
        Rectangle right = new Rectangle(new Point(gui.getDrawSurface().getWidth() - borderDepth, scorePanelHeight),
                borderDepth, gui.getDrawSurface().getHeight() - scorePanelHeight); // right border
        Rectangle[] borders = new Rectangle[]{top, left, right};
        for (Rectangle r : borders) {
            Block b = new Block(r, Color.darkGray);
            b.addToGame(this);
        }
        Rectangle bottom = new Rectangle(new Point(0, gui.getDrawSurface().getHeight()),
                gui.getDrawSurface().getWidth() - borderDepth, 0); // death region
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
            double x = (double) (gui.getDrawSurface().getWidth()) / 2;
            int y = 600 - (borderDepth + paddle.getPaddleHeight() + 10); // start right above the paddle
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
        this.runner.run(new CountdownAnimation(2, 3, sprites)); // countdown before turn starts
        this.running = true;
        this.runner.run(this);
    }

    /**
     * Function to access the running boolean.
     *
     * @return boolean if running
     */
    public boolean getRunning() {
        return running;
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
        this.sprites.notifyAllTimePassed();

        // stopping condition
        if (this.balls.getValue() == 0) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY,
                    new EndScreen(score, true)));
            gui.close();
            this.running = false;
        }
        if (blocksGone) {
            this.running = false;
        }
        if (blocksMaxAmount - this.blocks.getValue() >= levelInformation.numberOfBlocksToRemove()) {
            score.increase(100);
            blocksGone = true;
        }
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY, new PauseScreen()));
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }
}