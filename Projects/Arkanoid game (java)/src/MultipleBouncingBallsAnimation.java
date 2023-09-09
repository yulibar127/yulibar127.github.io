/**
 * @author Yuli Bar id-206841611
 */
import java.awt.Color;
import java.util.Random;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

public class MultipleBouncingBallsAnimation {

    public static void main(String[] args) {
    	GUI gui = new GUI("MultipleBouncingBallsAnimation", 200, 200);
        Random rand = new Random();
        double angle;
        double x, y;

        Ball[] ballArr = new Ball[args.length];
        
        
         for (int i = 0; i < ballArr.length; i++) {
            angle = rand.nextDouble(360);
            float r = rand.nextFloat();
            float g = rand.nextFloat();
            float b = rand.nextFloat();
            Color randomColor = new Color(r, g, b);
            x = rand.nextDouble(201);
            y = rand.nextDouble(201);
            Velocity v;
            ballArr[i] = new Ball(x, y, Integer.parseInt(args[i]), randomColor);
            if (Integer.parseInt(args[i]) > 50) {
                v = Velocity.fromAngleAndSpeed(angle, 1);
            } else {
                int diff = 52 - Integer.parseInt(args[i]);
                v = Velocity.fromAngleAndSpeed(angle, diff);
            }
            ballArr[i].setVelocity(v);
            
        }
         drawAnimation(ballArr, gui);

    }

    static private void drawAnimation(Ball[] ballArr, GUI gui) {
        
        Sleeper sleeper = new Sleeper();
        while (true) {
        	DrawSurface d = gui.getDrawSurface();
        	for (int i = 0; i < ballArr.length; i++) {
            ballArr[i].moveOneStep(d);
            ballArr[i].drawOn(d);
            }
            gui.show(d);
            sleeper.sleepFor(50); // wait for 50 milliseconds.
        }
    }
}
