
public class RowOfBlocks implements HitListener {
	
	private int remainingBlocks;
	
	public RowOfBlocks() {
		remainingBlocks = 0;
	}
	
	public void hitEvent(Block beingHit, Ball hitter) {
		remainingBlocks = remainingBlocks - 1;
		
	}
	public void increaseReminingBlocks(int number) {
		remainingBlocks = remainingBlocks + number;
	}
	public int getReminingBlocks() {
	    return remainingBlocks;
	}
	
	public boolean noBlocksLeft() {
		if (remainingBlocks == 0) {
			remainingBlocks--;
			return true;
		}
		else {
			return false;
		}
	}
	

}
