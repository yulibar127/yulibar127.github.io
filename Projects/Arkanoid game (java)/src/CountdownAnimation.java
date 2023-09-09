import java.awt.Color;

import biuoop.DrawSurface;

public class CountdownAnimation implements Animation {
	
	 private double numOfSeconds;
	 private int countFrom;
	 private SpriteCollection gameScreen;
	 
	 public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) 
	 { 
		this.numOfSeconds = numOfSeconds;
		this.countFrom = countFrom;
		this.gameScreen = gameScreen;
	 }
	 public double getNumOfSeconds() {
		 return this.numOfSeconds;
	 }
     public void doOneFrame(DrawSurface d) 
     { 
    	 String countFromInString;
    	 switch (this.countFrom) {
    	 case 3:
    		 countFromInString = "3";
    		 break;
    	 case 2:
    		 countFromInString = "2";
    		 break;
    	 case 1:
    		 countFromInString = "1";
    		 break;
    	 case 0:
    		 countFromInString = "GO";
    		 break;
    	 default:
    		 countFromInString = "invalid value";
    	 }
    	 
    	 this.gameScreen.drawAllOn(d);
    	 d.setColor(Color.GRAY);
    	 if (countFromInString != "GO") {
 	         d.drawText(320, 320, countFromInString, 250);
    	 }
    	 else {
    		 d.drawText(220, 350, countFromInString, 250); 
    	 }
    	  
    	 this.countFrom--;
     }
     public boolean shouldStop()
     { 
    	 if (this.countFrom == -1) {
    		 return true;
    	 }
    	 else {
    		 return false;
    	 }
     }

}
