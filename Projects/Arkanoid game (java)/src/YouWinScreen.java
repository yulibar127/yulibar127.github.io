import biuoop.DrawSurface;


	public class YouWinScreen implements Animation {
		
		
		private boolean stop;
		private int finalScore;
		
		   public YouWinScreen(int finalScore) {
		     
		      this.stop = false;
		      this.finalScore = finalScore;
		   }
		   public void doOneFrame(DrawSurface d) {
			  
			  d.drawText(10, d.getHeight() / 2, "You Win! Your score is " + this.finalScore, 32);
			  
		      
		   }
		   public boolean shouldStop() {
			   return this.stop; 
			   }

	}


