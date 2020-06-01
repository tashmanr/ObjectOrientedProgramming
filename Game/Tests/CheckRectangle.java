import biuoop.DrawSurface;
import biuoop.GUI;
import geometryprimatives.Line;
import geometryprimatives.Point;
import geometryprimatives.Rectangle;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.awt.Color;

public class CheckRectangle {


    static final int WIDTH = 800;
    static final int HEIGHT = 600;
    static final int LINE_ARRAY_SIZE = 5;
    static final int RECTANGLE_ARRAY_SIZE = 3;
    static final int RADIUS = 3;
    static final Color RECTANGLE_COLOR = Color.RED;
    static final Color LINE_COLOR = Color.BLACK;
    static final Color POINTS_COLOR = Color.BLUE;


    public static void main(String[] args) {
        GUI gui = new GUI("rectangle check", WIDTH, HEIGHT);
        DrawSurface d = gui.getDrawSurface();
        drawOn(d);
        gui.show(d);
    }

    public static Line generateRandomLine() {
        Random rand = new Random(); // create a random-number generator
        int x1 = rand.nextInt(WIDTH) + 1;
        int y1 = rand.nextInt(HEIGHT) + 1;
        int x2 = rand.nextInt(WIDTH) + 1;
        int y2 = rand.nextInt(HEIGHT) + 1;
        return new Line(x1, y1, x2, y2);
    }

    public static Rectangle generateRandomRectangle() {
        Random rand = new Random(); // create a random-number generator
        int x = rand.nextInt(WIDTH - 2) + 1;
        int y = rand.nextInt(HEIGHT - 2) + 1;
        Point upperLeft = new Point(x, y);
        int width = rand.nextInt(WIDTH - x - 1) + 1;
        int height = rand.nextInt(HEIGHT - y - 1) + 1;
        return new Rectangle(upperLeft, width, height);
    }

    public static void drawLine(Line l, DrawSurface d) {
        int x1 = (int) l.start().getX();
        int y1 = (int) l.start().getY();
        int x2 = (int) l.end().getX();
        int y2 = (int) l.end().getY();
        d.setColor(LINE_COLOR);
        d.drawLine(x1, y1, x2, y2);
    }

    public static void drawRectangle(Rectangle rectangle, DrawSurface d) {
        d.setColor(RECTANGLE_COLOR);
        Point upperLeft = rectangle.getUpperLeft();
        int width = (int) rectangle.getWidth();
        int height = (int) rectangle.getHeight();
        d.drawRectangle((int) upperLeft.getX(), (int) upperLeft.getY(), width, height);
    }

    public static List<Point> findIntersectionWithLine(Line l, Rectangle[] rectangles, DrawSurface d) {
        List<Point> intersectionPoints = new LinkedList<>();
        for (int i = 0; i < RECTANGLE_ARRAY_SIZE; i++) {
            if (rectangles[i].intersectionPoints(l) != null) {
                intersectionPoints.addAll(rectangles[i].intersectionPoints(l));
            }
        }
        return intersectionPoints;
    }

    public static void drawOn(DrawSurface d) {
        Line[] lineArray = new Line[LINE_ARRAY_SIZE];
        Rectangle[] rectangleArray = new Rectangle[RECTANGLE_ARRAY_SIZE];
        //creating and drawing lines
        for (int i = 0; i < LINE_ARRAY_SIZE; i++) {
            lineArray[i] = generateRandomLine();
        }
        for (int i = 0; i < LINE_ARRAY_SIZE; i++) {
            drawLine(lineArray[i], d);
        }
        //creating and drawing rectangles
        for (int i = 0; i < RECTANGLE_ARRAY_SIZE; i++) {
            rectangleArray[i] = generateRandomRectangle();
        }
        for (int i = 0; i < RECTANGLE_ARRAY_SIZE; i++) {
            drawRectangle(rectangleArray[i], d);
        }
        //drawing intersection point
        List<Point> intersectionPoints = new LinkedList<>();
        for (int i = 0; i < LINE_ARRAY_SIZE; i++) {
            intersectionPoints.addAll(findIntersectionWithLine(lineArray[i], rectangleArray, d));
        }
        d.setColor(POINTS_COLOR);
        while (!intersectionPoints.isEmpty()) {
            Point point = ((LinkedList<Point>) intersectionPoints).removeLast();
            d.fillCircle((int) point.getX(), (int) point.getY(), RADIUS);
        }
    }

}

