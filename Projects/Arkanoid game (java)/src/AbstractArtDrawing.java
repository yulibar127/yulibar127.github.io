/**
 * @author Yuli Bar id-206841611
 */
import java.awt.Color;
import java.util.Random;

import biuoop.DrawSurface;
import biuoop.GUI;

public class AbstractArtDrawing {

    private Line[] linesArr = new Line[12];

    public void drawRandomLines(DrawSurface d) {

        d.setColor(Color.BLACK);
        for (int i = 0; i < 10; ++i) {
            linesArr[i] = generateRandomLine();
            drawLine(linesArr[i], d);

        }
        linesArr[10] = new Line (20, 20, 30, 80);
        linesArr[11] = new Line (20, 20, 20, 80);
        drawLine(linesArr[10], d);
        drawLine(linesArr[11], d);
    }

    public Line generateRandomLine() {

        Random rand = new Random(); // create a random-number generator

        int x1 = rand.nextInt(400) + 1; // get integer in range 1-400
        int y1 = rand.nextInt(300) + 1; // get integer in range 1-300
        int x2 = rand.nextInt(400) + 1; // get integer in range 1-400
        int y2 = rand.nextInt(300) + 1; // get integer in range 1-300

        Line line = new Line(x1, y1, x2, y2);

        return line;
    }

    public void drawLine(Line l, DrawSurface d) {

        d.drawLine((int) l.start().getX(), (int) l.start().getY(), (int) l.end().getX(), (int) l.end().getY());
    }

    public void drawIntersectionPoints(DrawSurface d) {

        d.setColor(Color.RED);
        Point p;
        for (int i = 0; i < 12; i++) {
            for (int j = i + 1; j < 12; j++) {
                p = linesArr[i].intersectionWith(linesArr[j]);
                if (p != null) {
                    d.fillCircle((int) p.getX(), (int) p.getY(), 3);
                }
            }
        }

    }

    public void drawMiddlePoints(DrawSurface d) {

        d.setColor(Color.BLUE);
        Point p;
        for (int i = 0; i < 12; i++) {
            p = this.linesArr[i].middle();
            d.fillCircle((int) p.getX(), (int) p.getY(), 3);

        }

    }

    public static void main(String[] args) {
        // Create a window with the title "Random Lines, their middle points and
        // intersection points"
        // which is 400 pixels wide and 300 pixels high.
        GUI gui = new GUI("Random Lines, their middle points and intersection points", 400, 300);
        DrawSurface d = gui.getDrawSurface();
        AbstractArtDrawing drawing = new AbstractArtDrawing();
        drawing.drawRandomLines(d);
        drawing.drawMiddlePoints(d);
        drawing.drawIntersectionPoints(d);
        gui.show(d);
    }
}
