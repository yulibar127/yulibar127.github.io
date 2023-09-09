/**
 * @author Yuli Bar id-206841611
 */
import java.awt.Color;
import java.util.Random;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
public class MultipleFramesBouncingBallsAnimation {
	
	public static void main (String[]args) {
		
	GUI gui = new GUI("MultipleFramesBouncingBallsAnimation", 800, 600);
	
	Random rand = new Random();
    double angle;
    double x, y;

    Ball[] ballArr = new Ball[args.length];
    int yellowFrameSpace = args.length / 2; 
    String [] frameColor = new String [args.length];
    
    
     for (int i = 0; i < ballArr.length; i++) {
        angle = rand.nextDouble(360);
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        Color randomColor = new Color(r, g, b);
        if (Integer.parseInt(args[i]) > 75 || yellowFrameSpace == 0) {
            x = rand.nextDouble(451) + 50;
            y = rand.nextDouble(451) + 50;
            frameColor [i] = "gray";
        }
        else {
            x = rand.nextDouble(151) + 450;
            y = rand.nextDouble(151) + 450;
            yellowFrameSpace = yellowFrameSpace - 1;
            frameColor [i] = "yellow";
     }
        Velocity v;
        ballArr[i] = new Ball(x, y, Integer.parseInt(args[i]), randomColor);
        if (Integer.parseInt(args[i]) > 50) {
            v = Velocity.fromAngleAndSpeed(angle, 1);
        } else {
            int diff = 52 - Integer.parseInt(args[i]);
            v = Velocity.fromAngleAndSpeed(angle, diff);
        }
        ballArr[i].setVelocity(v); }
       
     drawAnimation(ballArr, gui, frameColor);

}
static private void drawAnimation(Ball[] ballArr, GUI gui, String [] frameColor) {
        
        Sleeper sleeper = new Sleeper();
        while (true) {
        	DrawSurface d = gui.getDrawSurface();
        	d.setColor(Color.GRAY);
        	d.fillRectangle(50, 50, 450, 450);
        	d.setColor(Color.YELLOW);
        	d.fillRectangle(450, 450, 150, 150);
        	for (int i = 0; i < ballArr.length; i++) {
        	    if (frameColor[i] == "gray") {
        	        ballArr[i].moveOneStepInBounds(50,50,500,500);
        	    }
        	    else {
        	        ballArr[i].moveOneStepInBounds(450,450,600,600);
        	    }
            ballArr[i].drawOn(d);
        	}
            gui.show(d);
            sleeper.sleepFor(50); // wait for 50 milliseconds.
        }
    }
}


