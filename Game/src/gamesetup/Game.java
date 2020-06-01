/**
 * Rebecca Tashman
 * 336423124
 */
package gamesetup;

import hitlisteners.BallRemover;
import hitlisteners.BlockRemover;
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

import java.awt.Color;
import java.util.Random;

/**
 * Class for setting up and running the game.
 */
public class Game {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Counter blocks;
    private Counter balls;
    private Counter score;

    /**
     * Constructor - creates new gamesetup.SpriteCollection, new gamesetup.GameEnvironment and new GUI.
     */
    public Game() {
        sprites = new SpriteCollection();
        environment = new GameEnvironment();
        gui = new biuoop.GUI("Game", 800, 600);
        blocks = new Counter();
        balls = new Counter();
        score = new Counter();
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
     * Function to initialize a new game: create the Blocks and Sprites.Ball (and Sprites.Paddle) and add them to the game.
     */
    public void initialize() {
        Paddle paddle = new Paddle(gui);
        paddle.addToGame(this);
        BlockRemover blockRemover = new BlockRemover(this, blocks);
        BallRemover ballRemover = new BallRemover(this, balls);
        //Creating the border blocks of the game
        int borderDepth = 25;
        Rectangle top = new Rectangle((new Point(0, 0)), 800, borderDepth); // top
        Rectangle left = new Rectangle(new Point(0, 0), borderDepth, 600); // left
        Rectangle right = new Rectangle(new Point(800 - borderDepth, 0), borderDepth, 600); // right
        Rectangle[] borders = new Rectangle[]{top, left, right};
        for (Rectangle r : borders) {
            Block b = new Block(r, Color.gray);
            b.addToGame(this);
        }
        Rectangle bottom = new Rectangle(new Point(0, 610),800 - borderDepth, 0); // bottom
        Block bottomBlock = new Block(bottom, Color.blue);
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
                Rectangle rectangle = new Rectangle(new Point(firstBlock.getCollisionRectangle().getUpperLeft().getX()
                        - blockWidth, firstBlock.getCollisionRectangle().getUpperLeft().getY()),
                        blockWidth, blockHeight);
                Block block = new Block(rectangle, firstBlock.getColor());
                block.addToGame(this);
                blocks.increase(1);
                block.addHitListener(blockRemover);
                firstBlock = block;
            }
            max--;
        }
        Random random = new Random();
        for (int i = 0; i < 2; i++) { // loop to create two balls
            int x = random.nextInt(100) + borderDepth; // start the ball within the game screen, not on blocks
            int y = random.nextInt(100) + borderDepth; // start the ball within the game screen, not on blocks
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
        Sleeper sleeper = new biuoop.Sleeper();
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (blocks.getValue() > 0 && balls.getValue() > 0) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            d.setColor(Color.blue); // filling the background
            d.fillRectangle(0, 0, gui.getDrawSurface().getWidth(), gui.getDrawSurface().getHeight());
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();
            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
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
}