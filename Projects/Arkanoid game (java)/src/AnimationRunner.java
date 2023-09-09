import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
public class AnimationRunner {

	 private GUI gui;
	 private int framesPerSecond;
	 private Sleeper sleeper = new Sleeper();

	   
	 public AnimationRunner (GUI gui, int framesPerSecond) {
		this.framesPerSecond = framesPerSecond;
		this.gui = gui;
	 }
	 public GUI getGui() {
		 return gui;
	 }
	 public void run(Animation animation) {
		 
		  if (animation instanceof CountdownAnimation) {
			  double numOfSeconds = ((CountdownAnimation) animation).getNumOfSeconds(); 
			  while (!animation.shouldStop()) {
				  DrawSurface d = this.gui.getDrawSurface();
				  animation.doOneFrame(d);
				  gui.show(d);
				  this.sleeper.sleepFor((long)(numOfSeconds * 1000 / 4));
			  }
		  }
		  else {
	          int millisecondsPerFrame = 1000 / framesPerSecond;
	          while (!animation.shouldStop()) {
	             long startTime = System.currentTimeMillis(); // timing
	             
	             DrawSurface d = this.gui.getDrawSurface();
 	             animation.doOneFrame(d);
	         
	             gui.show(d);
	             long usedTime = System.currentTimeMillis() - startTime;
	             long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
	             if (milliSecondLeftToSleep > 0) {
	                 this.sleeper.sleepFor(milliSecondLeftToSleep);
	         }
	      }
	      
	   }
}
}
