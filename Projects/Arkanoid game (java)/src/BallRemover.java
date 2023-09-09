
public class BallRemover implements HitListener {
	
	private GameLevel game;
	private Counter remainingBalls = new Counter();
	
	public BallRemover (GameLevel game, Counter removedBalls) {
		this.game = game;
		int removed = removedBalls.getValue();
		this.remainingBalls.decrease(removed);
	}
	public void hitEvent(Block beingHit, Ball hitter) {
		
		hitter.removeFromGame(this.game);
		remainingBalls.decrease(1);
		this.game.decreaseRemainedBalls(1);
		
		
		
	}
	
	public void increaseAvailableBalls (int number) {
		
		remainingBalls.increase(number);
	}
    

}
