import gamesetup.GameEnvironment;
import sprites.Ball;
import sprites.Block;
import ballinfo.Velocity;
import geometryprimatives.Point;
import geometryprimatives.Rectangle;

import java.util.*;

public class CollideTest {

    public static void main(String[] args) {
        biuoop.GUI gui = new biuoop.GUI("test", 700, 700);
        Random random = new Random();
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        biuoop.KeyboardSensor keyboardSensor = gui.getKeyboardSensor();
        int x = random.nextInt(200) + 120;
        int y = random.nextInt(200) + 120;
        Ball ball = new Ball(x, y, 20, java.awt.Color.PINK);
        double speed = 12;
        double angle = 50;//random.nextDouble();
        Velocity v = Velocity.fromAngleAndSpeed(angle, speed);
        ball.setVelocity(v);
        GameEnvironment gameEnvironment = new GameEnvironment();
        ball.setGameEnvironment(gameEnvironment);
        Rectangle rectangle = new Rectangle(new Point(0, 0), 600, 100);
        Block block1 = new Block(rectangle, java.awt.Color.GREEN);
        Rectangle rectangle2 = new Rectangle(new Point(0, 0), 100, 600);
        Block block2 = new Block(rectangle2, java.awt.Color.RED);
        Rectangle rectangle3 = new Rectangle(new Point(500, 0), 100, 600);
        Block block3 = new Block(rectangle3, java.awt.Color.BLUE);
        Rectangle rectangle4 = new Rectangle(new Point(0, 500), 600, 100);
        Block block4 = new Block(rectangle4, java.awt.Color.BLACK);
        Block[] blocks = new Block[] { block1, block2, block3, block4 };
        for (Block block : blocks) {
            ball.getGameEnvironment().addCollidable(block);
        }
        while (true) {
            if (keyboardSensor.isPressed(keyboardSensor.SPACE_KEY)) {
                gui.close();
            }
            biuoop.DrawSurface drawSurface = gui.getDrawSurface();
            for (Block block : blocks) {
                block.drawOn(drawSurface);
            }
            ball.moveOneStep();
            ball.drawOn(drawSurface);
            gui.show(drawSurface);
            sleeper.sleepFor(50);
        }

    }
}