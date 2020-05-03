/**
 * 336423124
 * Rebecca Tashman
 */

import biuoop.GUI;
import biuoop.DrawSurface;
import java.util.Random;
import java.awt.Color;

/**
 * This class creates an abstract drawing with lines.
 */
public class AbstractArtDrawing {
    /**
    * This function creates a random line.
    *
    * @return line
    */
    public static Line generateRandomLine() {
        Random rand = new Random();
        int x1 = rand.nextInt(400) + 1; // get integer in range 1-400
        int x2 = rand.nextInt(400) + 1; // get integer in range 1-400
        int y1 = rand.nextInt(300) + 1; // get integer in range 1-300
        int y2 = rand.nextInt(300) + 1; // get integer in range 1-300
        Line line = new Line(x1, y1, x2, y2);
        return line;
    }

    /**
     * This function draws a black line and marks the middle point blue.
     *
     * @param l - line to draw
     * @param d - surface to draw on
     */
    public void drawLine(Line l, DrawSurface d) {
        d.setColor(Color.BLACK);
        double x1 = l.start().getX();
        double y1 = l.start().getY();
        double x2 = l.end().getX();
        double y2 = l.end().getY();
        d.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
        double x = l.middle().getX();
        double y = l.middle().getY();
        d.setColor(Color.BLUE);
        d.fillCircle((int) x, (int) y, 3); // fill in middle point with radius of 3
    }

    /**
     * This function receives two lines, and marks the intersection (if exists) red.
     *
     * @param l1 - first line
     * @param l2 - second line
     * @param d - surface to draw on
     */
    public void drawIntersection(Line l1, Line l2, DrawSurface d) {
        if (l1.isIntersecting(l2)) {
            Point intersection = l1.intersectionWith(l2);
            double x = intersection.getX();
            double y = intersection.getY();
            d.setColor(Color.RED);
            d.fillCircle((int) x, (int) y, 3); // fill in intersection point with radius of 3
        }
    }

    /**
     * Main function - creates a new GUI and draws 10 random lines.
     *
     * @param args ignored
     */
    public static void main(String[] args) {
        AbstractArtDrawing drawing = new AbstractArtDrawing();
        // Create a window with the title "Abstract Art Drawing"
        // which is 400 pixels wide and 300 pixels high.
        GUI gui = new GUI("Abstract Art Drawing", 400, 300);
        Line[] lines = new Line[10]; // create array to hold 10 lines
        DrawSurface d = gui.getDrawSurface();
        /**
         * Loop that generates 10 random lines.
         */
        for (int i = 0; i < 10; ++i) {
            lines[i] = generateRandomLine();
        }
        /**
         * Loop that draws the lines and any intersections with the other lines in the array.
         */
        for (int i = 0; i < 10; i++) {
            drawing.drawLine(lines[i], d);
            for (int j = 0; j < i; j++) {
                drawing.drawIntersection(lines[i], lines[j], d);
            }
        }
        gui.show(d);
    }
}