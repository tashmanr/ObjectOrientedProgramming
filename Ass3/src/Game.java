/**
 * Rebecca Tashman
 * 336423124
 */

import biuoop.DrawSurface;
import biuoop.GUI;

import java.awt.*;
import java.util.*;

public class Game {
    private SpriteCollection sprites;
    private GameEnvironment environment;

    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    // Initialize a new game: create the Blocks and Ball (and Paddle)
    // and add them to the game.
    public void initialize() {
        environment = new GameEnvironment();
        sprites = new SpriteCollection();
        Random random = new Random();
        int x = random.nextInt(800);
        int y = random.nextInt(600);
        Ball ball = new Ball(x, y, 3);
        while (ball.getColor() == Color.WHITE) {
            ball = new Ball(x, y, 3);
        }
        double speed = 10;
        double angle = random.nextInt(360);
        Velocity v = Velocity.fromAngleAndSpeed(angle, speed);
        ball.setVelocity(v);
        ball.setGameEnvironment(environment);
        ball.addToGame(this);
        for (int i = 0; i < 20; i++) {
            Rectangle rectangle = new Rectangle(new Point(random.nextInt(700),
                    random.nextInt(590)), 100, 10);
            Block block = new Block(rectangle);
            block.addToGame(this);
        }
        //Borders
        Rectangle r1 = new Rectangle((new Point(0, 0)), 800, 5);
        Rectangle r2 = new Rectangle(new Point(0, 0), 5, 600);
        Rectangle r3 = new Rectangle(new Point(795, 0), 5, 600);
        Rectangle r4 = new Rectangle(new Point(0, 595), 800, 5);
        Rectangle[] borders = new Rectangle[]{r1, r2, r3, r4};
        for (Rectangle r : borders) {
            Block b = new Block(r, Color.BLACK);
            b.addToGame(this);
        }

    }

    // Run the game -- start the animation loop.
    public void run() {
        biuoop.GUI gui = new biuoop.GUI("Game", 800, 600);
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        initialize();
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
            long startTime = System.currentTimeMillis(); // timing

            DrawSurface d = gui.getDrawSurface();
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
    }
}