
import java.awt.Color;

import biuoop.DrawSurface;

public class PauseScreen implements Animation {
	
	private boolean stop;
	
	   public PauseScreen() {
	
		   this.stop = false;
	      
	   }
	   public void doOneFrame(DrawSurface d) {
		  d.setColor(Color.BLACK);
	      d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
	      
	   }
	   public boolean shouldStop() {
		   return this.stop;
		   }
	}


