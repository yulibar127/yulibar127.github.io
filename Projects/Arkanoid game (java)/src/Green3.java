
import java.util.List;

public class Green3 implements LevelInformation {
	
	private int numberOfBalls;
	private Paddle paddle;
	private List<Velocity> ballVelocities;
	private List<Block> blocks;
	private int numberOfBlocksToRemove;
	
    public Green3() {
		
		this.numberOfBalls = 0;
		this.paddle = null;
		this.ballVelocities = null;
		this.blocks = null;
		this.numberOfBlocksToRemove = 0;
		
		
	}
	public Green3(Paddle paddle, List<Velocity> ballVelocities, List<Block> blocks) {
		
		this.numberOfBalls = ballVelocities.size();
		this.paddle = paddle;
		this.ballVelocities = ballVelocities;
		this.blocks = blocks;
		this.numberOfBlocksToRemove = blocks.size() - 5; //the first block is the background block, and another 4 blocks that represent the limits
	}
	
	public int numberOfBalls() {
		
		return this.numberOfBalls;
		
	}
	   // The initial velocity of each ball
	   // Note that initialBallVelocities().size() == numberOfBalls()
	   public List<Velocity> initialBallVelocities() {
		   
		return this.ballVelocities;   
	   }
	   public int paddleSpeed() {
		   
		   return this.paddle.getSpeed();
	   }
	   public int paddleWidth() {
		   int width = (int)this.paddle.getCollisionRectangle().getWidth();
		   return width;
	   }
	   // the level name will be displayed at the top of the screen.
	   public String levelName() {
		   
		   return "Green3";
	   }
	   // Returns a sprite with the background of the level
	   public Sprite getBackground() {
		   
		   return this.blocks.get(0);
		   
	   }
	   // The Blocks that make up this level, each block contains
	   // its size, color and location.
	   public List<Block> blocks() {
		   
		   return this.blocks;
	   }
	   // Number of blocks that should be removed
	   // before the level is considered to be "cleared".
	   // This number should be <= blocks.size();
	   public int numberOfBlocksToRemove() {
		   
		   return this.numberOfBlocksToRemove();
	   }
	   public void decreaseNumberOfBlocksToRemove(int number) {
		   
		   this.numberOfBlocksToRemove = this.numberOfBlocksToRemove - number;
	   }
       public void decreaseNumberOfBalls(int number) {
		   
		   this.numberOfBalls = this.numberOfBalls - number;
	   }

}
