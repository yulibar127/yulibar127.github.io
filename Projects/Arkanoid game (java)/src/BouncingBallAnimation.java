/**
 * @author Yuli Bar id-206841611
 */
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

public class BouncingBallAnimation {
	  public static void main(String[] args) {

	        if (args.length > 0) {
	            Point p = new Point(Double.parseDouble(args[0]), Double.parseDouble(args[1]));
	            drawAnimation(p, Double.parseDouble(args[2]), Double.parseDouble(args[3]));
	        }
	    }
	        
	    
    private static void drawAnimation(Point start, double dx, double dy) {
        GUI gui = new GUI("title", 200, 200);
        Sleeper sleeper = new Sleeper();
        Ball ball = new Ball(start.getX(), start.getY(), 3, java.awt.Color.BLACK);
        ball.setVelocity(dx, dy);
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            ball.moveOneStep(d);
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(50); // wait for 50 milliseconds.
        }
    }


}
