import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import biuoop.*;

public class Test {
    public static void main(String[] args) {
        //Test.lineTest();
        //Test.rectangleTest();
        //Test.hitTest();
        //Test.collidableTest();;
        //Test.collisionInfoTest();
        Test.paddleTest();
        //Test.GameTest();
    }
    public static void lineTest(){
        Random rand = new Random();
        //first test - parallel to X aix - intersect in the start point of both line
        Line l1 = new Line(10, 20, 10, 30);
        Line l2 = new Line(10, 20, 10, 0);
        Point p1 = l1.intersectionWith(l2);
        System.out.println(p1);
        //second test - parallel to X aix - intersect in the this.start point and other.end point
        Line l3 = new Line(10, 20, 10, 30);
        Line l4 = new Line(10, 0, 10, 20);
        Point p2 = l3.intersectionWith(l4);
        System.out.println(p2);
        //third test - parallel to X aix - intersect in (20,20)
        Line l5 = new Line(20, 20, 20, 30);
        Line l6 = new Line(0, 0, 40, 40);
        Point p3 = l5.intersectionWith(l6);
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
        Point p4 = l7.intersectionWith(l8);
        System.out.println(p4);
    }
    public static void rectangleTest(){
        Random rand = new Random();
        int recX = rand.nextInt(150);
        int recY = rand.nextInt(150);
        int width = rand.nextInt(50);
        int height = rand.nextInt(50);
        Rectangle rec = new Rectangle(new Point( recX, recY), width, height);
        while(true){
            int x1 = rand.nextInt(150);
            int x2 = rand.nextInt(150);
            int y1 = rand.nextInt(150);
            int y2 = rand.nextInt(150);
            Line l = new Line(x1, y1, x2, y2);
            List<Point> pointlist = rec.intersectionPoints(l);
            for (Point p: pointlist) {
                System.out.println(p);
            }
        }
    }
    public static void hitTest(){
        Random rand = new Random();
        int x = rand.nextInt(100);
        int y = rand.nextInt(100);
        Rectangle randomRectangle = new Rectangle((new Point(x, y)), 50, 10 );
        Block block1 = new Block(randomRectangle);
        Velocity v = new Velocity(5, 5);
        int collisionPointX = rand.nextInt(100);
        int collisionPointY = rand.nextInt(100);
        Point collisionPoint = new Point(collisionPointX , collisionPointY);
        block1.hit(collisionPoint, v);
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
        //Velocity v = Velocity.fromAngleAndSpeed(angle, speed);
        ball.setVelocity(15, 20);
        GameEnvironment gameEnvironment = new GameEnvironment();
        ball.setGameEnvironment(gameEnvironment);
        //Blocks

        Rectangle rectangle = new Rectangle((new Point(20, 30)), 10, 400);
        Block block1 = new Block(rectangle, Color.GREEN);
        Rectangle rectangle2 = new Rectangle(new Point(102, 98), 50, 10);
        Block block2 = new Block(rectangle2, Color.GREEN);
        Rectangle rectangle3 = new Rectangle(new Point(300, 150), 10, 400);
        Block block3 = new Block(rectangle3, Color.GREEN);
        Rectangle rectangle4 = new Rectangle(new Point(550, 500), 50, 10);
        Block block4 = new Block(rectangle4, Color.GREEN);
        ball.setGameEnvironment(gameEnvironment);
        //Borders
        Rectangle rectangle5 = new Rectangle((new Point(0, 0)), 600, 5);
        Block block5 = new Block(rectangle5, Color.BLACK);
        Rectangle rectangle6 = new Rectangle(new Point(0, 0), 5, 600);
        Block block6 = new Block(rectangle6, Color.BLACK);
        Rectangle rectangle7 = new Rectangle(new Point(595, 0), 5, 600);
        Block block7 = new Block(rectangle7, Color.BLACK);
        Rectangle rectangle8 = new Rectangle(new Point(0, 595), 600, 5);
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
    public static void collisionInfoTest(){
        //first test - one point of intersection
        List<Collidable> colli1 = new ArrayList<>();
        Line ballLine1 = new Line(30, 10, 40, 25);
        int x1 = 0 , y1 = 0;
        for(int i = 0; i < 10; i++){
            Block block = new Block(new Rectangle(new Point(x1,y1),25,10));
            colli1.add(block);
            x1 += 25;
            y1 += 10;
        }
        CollisionInfo coll = new CollisionInfo(ballLine1, colli1);
        Point p = coll.collisionPoint();
        System.out.println(p);
        //second test - two points of intersection
        List<Collidable> colli2 = new ArrayList<>();
        Line ballLine2 = new Line(20, 10, 40, 20);
        int x2 = 25 , y2 = 10;
        Block block2 = new Block(new Rectangle(new Point(x2,y2),25,10));
        colli2.add(block2);
        CollisionInfo coll2 = new CollisionInfo(ballLine2, colli2);
        Point p2 = coll2.collisionPoint();
        System.out.println(p2);
        //third test - random
        Random random = new Random();
        List<Collidable> colli3 = new ArrayList<>();
        int bx31 = random.nextInt(250);
        int by31 = random.nextInt(250);
        int bx32 = random.nextInt(250);
        int by32 = random.nextInt(250);
        Line ballLine3 = new Line(bx31, by31, bx32, by32);
        int x3 = random.nextInt(250);
        int y3 = random.nextInt(250);
        for(int i = 0; i < 10; i++){
            Block block = new Block(new Rectangle(new Point(x3,y3),25,10));
            colli3.add(block);
            x1 += 25;
            y1 += 10;
        }
        CollisionInfo coll3 = new CollisionInfo(ballLine3, colli3);
        Point p3 = coll3.collisionPoint();
        System.out.println(p3);
    }
    public static void paddleTest(){
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
            pd.hit(new Point(390, 570), new Velocity(5,5));
            pd.hit(new Point(415, 570), new Velocity(10,5));
            pd.hit(new Point(440, 570), new Velocity(1,8));
            pd.hit(new Point(465, 570), new Velocity(-10,3));
            pd.hit(new Point(490, 570), new Velocity(4,4));
            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
    public static void GameTest(){
        Game game = new Game();
        game.initialize();
        game.run();
    }
}

