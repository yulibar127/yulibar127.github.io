
public class ScoreTrackingListener implements HitListener {
	
	 private Counter currentScore;

	   public ScoreTrackingListener(Counter scoreCounter) {
	      this.currentScore = scoreCounter;
	   }

	   public void hitEvent(Block beingHit, Ball hitter) {
	       currentScore.increase(5);
	   }
	   
	   public int getScore() {
		   return currentScore.getValue();
	   }
	   public void rowOfBlocksScore() {
		   currentScore.increase(100);
	   }
	   public void newRound() {
		   
		   this.currentScore = new Counter();
	   }

}
