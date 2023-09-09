import biuoop.DrawSurface;


public class GameOverScreen implements Animation {
	
	
	private boolean stop;
	private int finalScore;
	
	   public GameOverScreen(int finalScore) {
	      
	      this.stop = false;
	      this.finalScore = finalScore;
	   }
	   public void doOneFrame(DrawSurface d) {
		  
		  d.drawText(10, d.getHeight() / 2, "Game Over. Your score is " + this.finalScore, 32);
		  
	   }
	   public boolean shouldStop() {
		   return this.stop; 
		   }

}
