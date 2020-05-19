import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;



public class BallsTest1 {
    static private void drawAnimation(Point start, double dx, double dy) {
        GUI gui = new GUI("title",200,200);
        Sleeper sleeper = new Sleeper();
        Ball ball = new Ball(start.getX(), start.getY(), 30, java.awt.Color.BLACK);
        ball.setVelocity(dx, dy);
        while (true) {
            //ball.moveOneStep(d);
            DrawSurface d = gui.getDrawSurface();
            ball.moveOneStep(d.getWidth(), d.getHeight());
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }

    public static void main(String[] args) {
        //GUI gui = new GUI("Balls Test 1", 400, 400);
       // DrawSurface d = gui.getDrawSurface();

        Ball b1 = new Ball(100,100,30,java.awt.Color.RED);
        Ball b2 = new Ball(100,150,10,java.awt.Color.BLUE);
        Ball b3 = new Ball(80,249,50,java.awt.Color.GREEN);

       // b1.drawOn(d);
        //b2.drawOn(d);
        //b3.drawOn(d);

//        gui.show(d);

        drawAnimation(new Point(b1.getX(), b1.getY()), 2, 2);
    }
}