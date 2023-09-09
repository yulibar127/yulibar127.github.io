
public class BlockRemover implements HitListener {
	
	private GameLevel game;
	private Counter remainingBlocks = new Counter();
	

	   public BlockRemover(GameLevel game, Counter removedBlocks) { 
		   
		   this.game = game;
		   int removed = removedBlocks.getValue();
		   this.remainingBlocks.decrease(removed);
		   
	   }
	   

	   // Blocks that are hit should be removed
	   // from the game. Remember to remove this listener from the block
	   // that is being removed from the game.
	   public void hitEvent(Block beingHit, Ball hitter) { 
		   
		   beingHit.removeFromGame(this.game);
		   this.game.decreaseRemainedBlocks(1);
		   this.remainingBlocks.decrease(1);
	   }
	   
	   public void increaseRemainingBlocks(int number) {
		   this.remainingBlocks.increase(number);
	   }

}
