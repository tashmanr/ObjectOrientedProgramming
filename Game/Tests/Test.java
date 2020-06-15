import java.awt.*;
import java.util.List;
import java.util.Random;

import gamesetup.GameLevel;
import gamesetup.GameEnvironment;
import sprites.Ball;
import sprites.Block;
import sprites.Paddle;
import ballinfo.Velocity;
import biuoop.*;
import geometryprimatives.Line;
import geometryprimatives.Rectangle;
/*
public class Test {
    public static void main(String[] args) {
        Test.lineTest();
        //Test.rectangleTest();
        //Test.hitTest();
        //Test.collidableTest();;
        //Test.collisionInfoTest();
        //Test.paddleTest();
        //Test.GameTest();
    }
    public static void lineTest(){
        Random rand = new Random();
        //first test - parallel to X aix - intersect in the start point of both line
        Line l1 = new Line(10, 20, 10, 30);
        Line l2 = new Line(10, 20, 10, 0);
        geometryprimatives.Point p1 = l1.intersectionWith(l2);
        System.out.println(p1);
        //second test - parallel to X aix - intersect in the this.start point and other.end point
        Line l3 = new Line(10, 20, 10, 30);
        Line l4 = new Line(10, 0, 10, 20);
        geometryprimatives.Point p2 = l3.intersectionWith(l4);
        System.out.println(p2);
        //third test - parallel to X aix - intersect in (20,20)
        Line l5 = new Line(20, 20, 20, 30);
        Line l6 = new Line(0, 0, 40, 40);
        geometryprimatives.Point p3 = l5.intersectionWith(l6);
        System.out.println(p3);
        //four test - random lines
        int x1 = rand.nextInt(25);
        int x2 = rand.nextInt(25);
        int x3 = rand.nextInt(25);
        int x4 = rand.nextInt(25);
        int y1 = rand.nextInt(25);
        int y2 = rand.nextInt(25);
        int y3 = rand.nextInt(25);
        int y4 = rand.nextInt(25);
        Line l7 = new Line(x1, y1, x2, y2);
        Line l8 = new Line(x3, y3, x4, y4);
        geometryprimatives.Point p4 = l7.intersectionWith(l8);
        System.out.println(p4);
    }
    public static void rectangleTest(){
        Random rand = new Random();
        int recX = rand.nextInt(150);
        int recY = rand.nextInt(150);
        int width = rand.nextInt(50);
        int height = rand.nextInt(50);
        geometryprimatives.Rectangle rec = new geometryprimatives.Rectangle(new geometryprimatives.Point( recX, recY), width, height);
        while(true){
            int x1 = rand.nextInt(150);
            int x2 = rand.nextInt(150);
            int y1 = rand.nextInt(150);
            int y2 = rand.nextInt(150);
            Line l = new Line(x1, y1, x2, y2);
            List<geometryprimatives.Point> pointlist = rec.intersectionPoints(l);
            for (geometryprimatives.Point p: pointlist) {
                System.out.println(p);
            }
        }
    }
    public static void hitTest(){
        Random rand = new Random();
        int x = rand.nextInt(100);
        int y = rand.nextInt(100);
        geometryprimatives.Rectangle randomRectangle = new geometryprimatives.Rectangle((new geometryprimatives.Point(x, y)), 50, 10 );
        Block block1 = new Block(randomRectangle);
        Velocity v = new Velocity(5, 5);
        int collisionPointX = rand.nextInt(100);
        int collisionPointY = rand.nextInt(100);
        geometryprimatives.Point collisionPoint = new geometryprimatives.Point(collisionPointX , collisionPointY);
       // block1.hit(collisionPoint, v);
    }
    public static void collidableTest () {
        GUI gui = new GUI("test", 600, 600);
        Random random = new Random();
        Sleeper sleeper = new Sleeper();
        KeyboardSensor keyboardSensor = gui.getKeyboardSensor();
        int x = random.nextInt(200) + 120;
        int y = random.nextInt(200) + 120;
        Ball ball = new Ball(x, y, 10, Color.BLUE);
        //double speed = random.nextInt(50);
        //double angle = random.nextDouble();
        //ballinfo.Velocity v = ballinfo.Velocity.fromAngleAndSpeed(angle, speed);
        ball.setVelocity(15, 20);
        GameEnvironment gameEnvironment = new GameEnvironment();
        ball.setGameEnvironment(gameEnvironment);
        //Blocks

        geometryprimatives.Rectangle rectangle = new geometryprimatives.Rectangle((new geometryprimatives.Point(20, 30)), 10, 400);
        Block block1 = new Block(rectangle, Color.GREEN);
        geometryprimatives.Rectangle rectangle2 = new geometryprimatives.Rectangle(new geometryprimatives.Point(102, 98), 50, 10);
        Block block2 = new Block(rectangle2, Color.GREEN);
        geometryprimatives.Rectangle rectangle3 = new geometryprimatives.Rectangle(new geometryprimatives.Point(300, 150), 10, 400);
        Block block3 = new Block(rectangle3, Color.GREEN);
        geometryprimatives.Rectangle rectangle4 = new geometryprimatives.Rectangle(new geometryprimatives.Point(550, 500), 50, 10);
        Block block4 = new Block(rectangle4, Color.GREEN);
        ball.setGameEnvironment(gameEnvironment);
        //Borders
        geometryprimatives.Rectangle rectangle5 = new geometryprimatives.Rectangle((new geometryprimatives.Point(0, 0)), 600, 5);
        Block block5 = new Block(rectangle5, Color.BLACK);
        Rectangle rectangle6 = new geometryprimatives.Rectangle(new geometryprimatives.Point(0, 0), 5, 600);
        Block block6 = new Block(rectangle6, Color.BLACK);
        geometryprimatives.Rectangle rectangle7 = new geometryprimatives.Rectangle(new geometryprimatives.Point(595, 0), 5, 600);
        Block block7 = new Block(rectangle7, Color.BLACK);
        geometryprimatives.Rectangle rectangle8 = new geometryprimatives.Rectangle(new geometryprimatives.Point(0, 595), 600, 5);
        Block block8 = new Block(rectangle8, Color.BLACK);
        Block[] blocks = new Block[]{block1, block2, block3, block4, block5, block6, block7, block8};
        for (Block block : blocks) {
            ball.getGameEnvironment().addCollidable(block);
        }
        while (true) {
            if (keyboardSensor.isPressed(keyboardSensor.SPACE_KEY)) {
                gui.close();
            }
            DrawSurface drawSurface = gui.getDrawSurface();
            for (Block block : blocks) {
                block.drawOn(drawSurface);
            }
            ball.moveOneStep();
            ball.drawOn(drawSurface);
            gui.show(drawSurface);
            sleeper.sleepFor(50);
        }

    }
    /*public static void collisionInfoTest(){
        //first test - one point of intersection
        List<collisiondetection.Collidable> colli1 = new ArrayList<>();
        geometryprimatives.Line ballLine1 = new geometryprimatives.Line(30, 10, 40, 25);
        int x1 = 0 , y1 = 0;
        for(int i = 0; i < 10; i++){
            Sprites.Block block = new Sprites.Block(new geometryprimatives.Rectangle(new geometryprimatives.Point(x1,y1),25,10));
            colli1.add(block);
            x1 += 25;
            y1 += 10;
        }
        collisiondetection.CollisionInfo coll = new collisiondetection.CollisionInfo(ballLine1, colli1);
        geometryprimatives.Point p = coll.collisionPoint();
        System.out.println(p);
        //second test - two points of intersection
        List<collisiondetection.Collidable> colli2 = new ArrayList<>();
        geometryprimatives.Line ballLine2 = new geometryprimatives.Line(20, 10, 40, 20);
        int x2 = 25 , y2 = 10;
        Sprites.Block block2 = new Sprites.Block(new geometryprimatives.Rectangle(new geometryprimatives.Point(x2,y2),25,10));
        colli2.add(block2);
        collisiondetection.CollisionInfo coll2 = new collisiondetection.CollisionInfo(ballLine2, colli2);
        geometryprimatives.Point p2 = coll2.collisionPoint();
        System.out.println(p2);
        //third test - random
        Random random = new Random();
        List<collisiondetection.Collidable> colli3 = new ArrayList<>();
        int bx31 = random.nextInt(250);
        int by31 = random.nextInt(250);
        int bx32 = random.nextInt(250);
        int by32 = random.nextInt(250);
        geometryprimatives.Line ballLine3 = new geometryprimatives.Line(bx31, by31, bx32, by32);
        int x3 = random.nextInt(250);
        int y3 = random.nextInt(250);
        for(int i = 0; i < 10; i++){
            Sprites.Block block = new Sprites.Block(new geometryprimatives.Rectangle(new geometryprimatives.Point(x3,y3),25,10));
            colli3.add(block);
            x1 += 25;
            y1 += 10;
        }
        collisiondetection.CollisionInfo coll3 = new collisiondetection.CollisionInfo(ballLine3, colli3);
        geometryprimatives.Point p3 = coll3.collisionPoint();
        System.out.println(p3);
    }*/
   /* public static void paddleTest(){
        GUI gui = new GUI("Test", 800, 600);
        Paddle pd = new Paddle(gui);
        //checking borders
        Sleeper sleeper = new Sleeper();
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            long startTime = System.currentTimeMillis();
            pd.drawOn(d);
            gui.show(d);
            // timing - check borders with paddle
            pd.timePassed();
            //checking hit on 5 parts of the paddle
            /*pd.hit(new geometryprimatives.Point(390, 570), new Velocity(5,5));
            pd.hit(new geometryprimatives.Point(415, 570), new Velocity(10,5));
            pd.hit(new geometryprimatives.Point(440, 570), new Velocity(1,8));
            pd.hit(new geometryprimatives.Point(465, 570), new Velocity(-10,3));
            pd.hit(new geometryprimatives.Point(490, 570), new Velocity(4,4));*/
            // timing
    /*        long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
    public static void GameTest(){
        GameLevel gameLevel = new GameLevel();
        gameLevel.initialize();
        gameLevel.run();
    }
}
*/
