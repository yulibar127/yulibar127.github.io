
import java.awt.Color;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

public class GameLevel implements Animation {
	
   private AnimationRunner runner;
   private boolean running;	
   private KeyboardSensor keyboard;
   private SpriteCollection sprites;
   private GameEnvironment environment;
   private Counter remainingBlocks = new Counter();
   private Counter remainingBalls = new Counter();
   private List<RowOfBlocks> blocksRows = new ArrayList<RowOfBlocks>(); 
   private ScoreTrackingListener scoreTracking = new ScoreTrackingListener(new Counter());
   private ScoreIndicator indicator;
   private LevelInformation level;
   private Paddle paddle;
   
   public GameLevel(LevelInformation level, KeyboardSensor ks, AnimationRunner ar, ScoreIndicator indicator) {
	   
	   this.sprites = new SpriteCollection();
	   this.environment = new GameEnvironment();
	   this.level = level;
	   this.keyboard = ks;
	   this.runner = ar;
	   this.indicator = indicator;
   }

   public void addCollidable(Collidable c) {
	   
	   this.environment.addCollidable(c);
   }
   public void addSprite(Sprite s) {
	   this.sprites.addSprite(s);
   }
   public void increaseRemainedBalls(int number) {
	   this.remainingBalls.increase(number);
   }
   
   
   
   // Initialize a new game: create the Blocks and Ball (and Paddle) 
   // and add them to the game.
   public void initialize() {
	   
	  
	   Random rand = new Random();
	   
	      Rectangle backgroundRec = new Rectangle (new Point (0,25), 800, 575);
	      Rectangle rec1 = new Rectangle (new Point (0,25), 800, 25);
		  Rectangle rec2 = new Rectangle (new Point (0,50), 25, 550);
		  Rectangle rec3 = new Rectangle (new Point (775,50), 25, 550);
		  Rectangle rec4 = new Rectangle (new Point (25,599), 750, 1);
		  
			Block block1 = new Block (rec1, Color.GRAY);
			Block block2 = new Block (rec2, Color.GRAY);
			Block block3 = new Block (rec3, Color.GRAY);
			Block block4 = new Block (rec4, Color.GRAY);
			
			
			Block [] arrBlock = {block1,block2,block3,block4};
			BallRemover ballRemover = new BallRemover(this, new Counter());
		    arrBlock[3].addHitListener(ballRemover);
		    PrintingHitListener hitMessage = new PrintingHitListener();
		    BlockRemover remover = new BlockRemover (this, new Counter(0));
			
		    //level 1
			if (this.level instanceof DirectHit) {
								
			    
			    Block background = new Block (backgroundRec, Color.BLACK);
			    List<Block> blocks = new ArrayList<Block>();
			    blocks.add(background);
			    background.addToGame(this);
			    
			    for (int i = 0; i < arrBlock.length; i++) {
			    	 
			    	 blocks.add(arrBlock[i]);
			         Block block = arrBlock[i];
			         block.addToGame(this);
			      }
			    
			    Rectangle redRec = new Rectangle (new Point (395,150), 20, 20);
			    Block red = new Block (redRec, Color.RED);
			    remover.increaseRemainingBlocks(1);
		    	remainingBlocks.increase(1);
			    red.addHitListener(remover);
			    red.addHitListener(hitMessage);
			    red.addHitListener(scoreTracking);
			    blocks.add(red);
			    red.addToGame(this);
			    
			    Point paddleUpperLeft = new Point (365,570);
				this.paddle = new Paddle (keyboard, new Rectangle (paddleUpperLeft, 70, 13), Color.YELLOW, 5, paddleUpperLeft);
				this.paddle.addToGame(this);
				
				Ball ball = new Ball(new Point (400, 550), 5, Color.WHITE);
				ball.setVelocity(0,5);
			    ball.setGameEnvironment(environment);
			    ball.addToGame(this);
			    List<Velocity> ballVelocities = new ArrayList<Velocity>();
			    ballVelocities.add(ball.getVelocity());
				
				this.level = new DirectHit(paddle, ballVelocities, blocks);
				
			}
			
		 //level 2
			if (this.level instanceof WideEasy) {
				
				Block background = new Block (backgroundRec, Color.WHITE);
			    List<Block> blocks = new ArrayList<Block>();
			    blocks.add(background);
			    background.addToGame(this);
			    
			    for (int i = 0; i < arrBlock.length; i++) {
			    	 
			    	 blocks.add(arrBlock[i]);
			         Block block = arrBlock[i];
			         block.addToGame(this);
			      }
			    blocksRows.add(new RowOfBlocks());
			    
			    for (int i = 0; i < 6; i = i + 2) {
			    	float r = rand.nextFloat();
			    	float g = rand.nextFloat();
			    	float s = rand.nextFloat();
			    	Color randomColor = new Color(r, g, s);
			    	Rectangle firstRec = new Rectangle (new Point (25 + i * (750 / 15),250), (750 / 15), 18);
			    	Rectangle secondRec = new Rectangle (new Point (25 + (i + 1) * (750 / 15),250), (750 / 15), 18);
				    Block firstBlock = new Block (firstRec, randomColor);
				    firstBlock.addHitListener(remover);
				    firstBlock.addHitListener(hitMessage);
				    firstBlock.addHitListener(scoreTracking);
				    blocks.add(firstBlock);
				    firstBlock.addToGame(this);
				    Block secondBlock = new Block (secondRec, randomColor);
				    secondBlock.addHitListener(remover);
				    secondBlock.addHitListener(hitMessage);
				    secondBlock.addHitListener(scoreTracking);
				    blocks.add(secondBlock);
				    secondBlock.addToGame(this);
				    remover.increaseRemainingBlocks(2);
			    	remainingBlocks.increase(2);
			    	blocksRows.get(0).increaseReminingBlocks(2);
			    	firstBlock.addHitListener(blocksRows.get(0));
			    	secondBlock.addHitListener(blocksRows.get(0));
			    	
			    }
			    float r = rand.nextFloat();
		    	float g = rand.nextFloat();
		    	float s = rand.nextFloat();
		    	Color randomColor = new Color(r, g, s);
		    	
			    for (int i = 6; i < 9; i++) {
			    	
			    	Rectangle rec = new Rectangle (new Point (25 + i * (750 / 15),250), (750 / 15), 18);
				    Block block = new Block (rec, randomColor);
				    block.addHitListener(remover);
				    block.addHitListener(hitMessage);
				    block.addHitListener(scoreTracking);
				    blocks.add(block);
				    block.addToGame(this);
				    remover.increaseRemainingBlocks(1);
			    	remainingBlocks.increase(1);
			    	blocksRows.get(0).increaseReminingBlocks(1);
			    	block.addHitListener(blocksRows.get(0));
			    }
			    for (int i = 9; i < 15; i = i + 2) {
			    	
			    	float k = rand.nextFloat();
			    	float q = rand.nextFloat();
			    	float p = rand.nextFloat();
			    	Color randColor = new Color(k, q, p);
			    	Rectangle firstRec = new Rectangle (new Point (25 + i * (750 / 15),250), (750 / 15), 18);
			    	Rectangle secondRec = new Rectangle (new Point (25 + (i + 1) * (750 / 15),250), (750 / 15), 18);
				    Block firstBlock = new Block (firstRec, randColor);
				    firstBlock.addHitListener(remover);
				    firstBlock.addHitListener(hitMessage);
				    firstBlock.addHitListener(scoreTracking);
				    blocks.add(firstBlock);
				    firstBlock.addToGame(this);
				    Block secondBlock = new Block (secondRec, randColor);
				    secondBlock.addHitListener(remover);
				    secondBlock.addHitListener(hitMessage);
				    secondBlock.addHitListener(scoreTracking);
				    blocks.add(secondBlock);
				    secondBlock.addToGame(this);
				    remover.increaseRemainingBlocks(2);
			    	remainingBlocks.increase(2);
			    	blocksRows.get(0).increaseReminingBlocks(2);
			    	firstBlock.addHitListener(blocksRows.get(0));
			    	secondBlock.addHitListener(blocksRows.get(0));
			    	
			    }
			    Point paddleUpperLeft = new Point (100,570);
			    this.paddle = new Paddle (keyboard, new Rectangle (paddleUpperLeft, 600, 13), Color.YELLOW, 5, paddleUpperLeft);
			    this.paddle.addToGame(this);
				
                List<Velocity> ballVelocities = new ArrayList<Velocity>();
				
				for (int i = 0; i < 5; i++) {
					Ball ball1 = new Ball(new Point (370 - i * 30, 550), 5, Color.WHITE);
					Ball ball2 = new Ball(new Point (430 + i * 30, 550), 5, Color.WHITE);
					ball1.setVelocity(0,5);
				    ball1.setGameEnvironment(environment);
				    ball1.addToGame(this);
				    ballVelocities.add(ball1.getVelocity());
				    
				    ball2.setVelocity(0,5);
				    ball2.setGameEnvironment(environment);
				    ball2.addToGame(this);
				    ballVelocities.add(ball2.getVelocity());					
				}
				
				this.level = new WideEasy(paddle, ballVelocities, blocks);
			    
			    
				
				
			}
			
			//level 3
			if (this.level instanceof Green3) {
				
				Block background = new Block (backgroundRec, Color.green);
			    List<Block> blocks = new ArrayList<Block>();
			    blocks.add(background);
			    background.addToGame(this);
			    
			    for (int i = 0; i < arrBlock.length; i++) {
			    	 
			    	 blocks.add(arrBlock[i]);
			         Block block = arrBlock[i];
			         block.addToGame(this);
			      }
			    for (int j = 0; j < 5; j++) { 
			    	  
			    	  blocksRows.add(new RowOfBlocks());
			    	  float r = rand.nextFloat();
			    	  float g = rand.nextFloat();
			    	  float s = rand.nextFloat();
			    	  Color randomColor = new Color(r, g, s);
			      
			          for (int i = 0; i < (5 * 2 - j); i++) {
			    	  
			    	  
			    	  remover.increaseRemainingBlocks(1);
			    	  remainingBlocks.increase(1);
			    	  Rectangle rec = new Rectangle (new Point (725 - 50 * i,135 + j * 18), 50, 18);
			    	  
			    	  Block b = new Block(rec, randomColor);
			    	  blocksRows.get(j).increaseReminingBlocks(1);
			    	  b.addHitListener(hitMessage);
			    	  b.addHitListener(remover);
			    	  b.addHitListener(blocksRows.get(j));
			    	  b.addHitListener(scoreTracking);
			    	  b.addToGame(this);
			    	  
			      }
			      }
			    
			
			Point paddleUpperLeft = new Point (365,570);
			this.paddle = new Paddle (keyboard, new Rectangle (paddleUpperLeft, 70, 13), Color.YELLOW, 5, paddleUpperLeft);
			this.paddle.addToGame(this);
			
			List<Velocity> ballVelocities = new ArrayList<Velocity>();
			
			Ball ball1 = new Ball(new Point (385, 550), 5, Color.WHITE);
			Ball ball2 = new Ball(new Point (415, 550), 5, Color.WHITE);
			ball1.setVelocity(0,5);
		    ball1.setGameEnvironment(environment);
		    ball1.addToGame(this);
		    ballVelocities.add(ball1.getVelocity());
		    
		    ball2.setVelocity(0,5);
		    ball2.setGameEnvironment(environment);
		    ball2.addToGame(this);
		    ballVelocities.add(ball2.getVelocity());
			
			this.level = new Green3(paddle, ballVelocities, blocks);
   }
			
			//level 4
			if (this.level instanceof FinalFour) {
				    
				    Block background = new Block (backgroundRec, Color.blue);
				    List<Block> blocks = new ArrayList<Block>();
				    blocks.add(background);
				    background.addToGame(this);
				    
				    for (int i = 0; i < arrBlock.length; i++) {
				    	 
				    	 blocks.add(arrBlock[i]);
				         Block block = arrBlock[i];
				         block.addToGame(this);
				      }
				    for (int j = 0; j < 7; j++) { 
				    	  
				    	  blocksRows.add(new RowOfBlocks());
				    	  float r = rand.nextFloat();
				    	  float g = rand.nextFloat();
				    	  float s = rand.nextFloat();
				    	  Color randomColor = new Color(r, g, s);
				      
				          for (int i = 0; i < 15; i++) {
				    	  
				    	  
				    	  remover.increaseRemainingBlocks(1);
				    	  remainingBlocks.increase(1);
				    	  Rectangle rec = new Rectangle (new Point (25 + i * (750 / 15), 115 + j * 18),
				    			  (750 / 15), 18);
				    	  
				    	  Block b = new Block(rec, randomColor);
				    	  blocksRows.get(j).increaseReminingBlocks(1);
				    	  b.addHitListener(hitMessage);
				    	  b.addHitListener(remover);
				    	  b.addHitListener(blocksRows.get(j));
				    	  b.addHitListener(scoreTracking);
				    	  b.addToGame(this);
				    	  
				      }
				      }
				    Point paddleUpperLeft = new Point (365,570);
				    this.paddle = new Paddle (keyboard, new Rectangle (paddleUpperLeft, 70, 13), Color.YELLOW, 5, paddleUpperLeft);
				    this.paddle.addToGame(this);
					
                    List<Velocity> ballVelocities = new ArrayList<Velocity>();
					
					Ball ball1 = new Ball(new Point (385, 550), 5, Color.WHITE);
					Ball ball2 = new Ball(new Point (415, 550), 5, Color.WHITE);
					Ball ball3 = new Ball(new Point (400, 550), 5, Color.WHITE);
					ball1.setVelocity(0,5);
				    ball1.setGameEnvironment(environment);
				    ball1.addToGame(this);
				    ballVelocities.add(ball1.getVelocity());
				    
				    ball2.setVelocity(0,5);
				    ball2.setGameEnvironment(environment);
				    ball2.addToGame(this);
				    ballVelocities.add(ball2.getVelocity());
				    
				    ball3.setVelocity(0,5);
				    ball3.setGameEnvironment(environment);
				    ball3.addToGame(this);
				    ballVelocities.add(ball3.getVelocity());
					
					this.level = new FinalFour(paddle, ballVelocities, blocks);
				    
				    
				
			}
			
			
	      addSprite(indicator);
	      indicator.setLevel(this.level);
	   }
	   
  
   
   // Run the game -- start the animation loop.
   public void runCountdownAnimation() {
   
	   this.runner.run(new CountdownAnimation(2, 3, this.sprites)); 
   }
   
   public void run() {
	   
	   this.running = true;
	      // use our runner to run the current animation -- which is one turn of
	      // the game.
	   this.runner.run(this);
	   
	   }
   public void doOneFrame(DrawSurface d) {
	      
	   this.sprites.drawAllOn(d);
	   
	   for (int i = 0; i < blocksRows.size(); i++) {
		      if (blocksRows.get(i).noBlocksLeft()) 
		      {
		    	  scoreTracking.rowOfBlocksScore();	   
		    	  
		      }
		     }
	   indicator.increaseScore(scoreTracking.getScore());
	   this.scoreTracking.newRound();
	   
	   if (this.getLevelInformation() instanceof FinalFour && this.getRemainingBlocks().getValue() == 0) {
		   Animation b1 = new YouWinScreen(this.indicator.getScore());
		   Animation b1k = new KeyPressStoppableAnimation(keyboard, "m", b1);
		   this.runner.run(b1k);
		   this.running = false;
		   this.runner.getGui().close();
		   
	   }
	   if (remainingBalls.getValue() == 0) {
		   this.indicator.lostLife();
		   
		   if (this.indicator.livesLeft() == 0) {
			   this.running = false;
			   Animation c1 = new GameOverScreen(this.indicator.getScore());
			   Animation c1k = new KeyPressStoppableAnimation(keyboard, "m", c1);
			   this.runner.run(c1k);
			   this.runner.getGui().close();
		   }
		   if (this.level instanceof DirectHit) {
			   Paddle newPaddle = new Paddle (keyboard, new Rectangle (this.paddle.getStartUpperLeft(), 70, 13),
					   Color.YELLOW, 5, this.paddle.getStartUpperLeft());
			   this.paddle.removeFromGame(this);
			   this.paddle = newPaddle;
			   this.paddle.addToGame(this);
			   
		       this.createBallsOfDirectHitLevel();
		   }
		   if (this.level instanceof WideEasy) {
			   Paddle newPaddle = new Paddle (keyboard, new Rectangle (this.paddle.getStartUpperLeft(), 600, 13),
					   Color.YELLOW, 5, this.paddle.getStartUpperLeft());
			   this.paddle.removeFromGame(this);
			   this.paddle = newPaddle;
			   this.paddle.addToGame(this);
			   
			   this.createBallsOfWideEasyLevel();
		   }
		   if (this.level instanceof Green3) {
			   Paddle newPaddle = new Paddle (keyboard, new Rectangle (this.paddle.getStartUpperLeft(), 70, 13),
					   Color.YELLOW, 5, this.paddle.getStartUpperLeft());
			   this.paddle.removeFromGame(this);
			   this.paddle = newPaddle;
			   this.paddle.addToGame(this);
			   
			   this.createBallsOfGreen3Level();
		   }
		   if (this.level instanceof FinalFour) {
			   Paddle newPaddle = new Paddle (keyboard, new Rectangle (this.paddle.getStartUpperLeft(), 70, 13),
					   Color.YELLOW, 5, this.paddle.getStartUpperLeft());
			   this.paddle.removeFromGame(this);
			   this.paddle = newPaddle;
			   this.paddle.addToGame(this);
			   
			   this.createBallsOfFinalFourLevel();
		   }
		  
		   this.runCountdownAnimation();
	   }
	   
	   if (remainingBlocks.getValue() == 0) {	       
	       this.running = false;
	      
	   }	
		      
	 
	   if (this.keyboard.isPressed("p")) {
		   Animation a1 = new PauseScreen();
		   Animation a1k = new KeyPressStoppableAnimation(this.keyboard, "m", a1);
		   this.runner.run(a1k);
		   
	      }
	  
	   this.sprites.notifyAllTimePassed();
	   
   }
   public void createBallsOnTopOfPaddle(int numOfBalls) {
	   Random rand = new Random();
	   Ball ball;
	   for (int i = 0; i < numOfBalls; i++) {
	       int randomX = rand.nextInt(60) + 370;
	       ball = new Ball(new Point (randomX, 565), 5, Color.WHITE);
	       ball.setVelocity(0,5);
	       ball.setGameEnvironment(environment);
	       ball.addToGame(this);
	   }
	   
	   
   }
   public boolean shouldStop() {
	  
	   return !this.running;
	  
   }
 
   public void removeCollidable(Collidable c) {
	   
	   this.environment.removeCollidable(c);
   }
   
   public void removeSprite(Sprite s) {
	   
	   this.sprites.removeSprite(s);
	   
   }
   public void decreaseRemainedBlocks(int number) {
	   
	   this.remainingBlocks.decrease(number);
   }
   public void decreaseRemainedBalls(int number) {
	   this.remainingBalls.decrease(number);
   }
   public LevelInformation getLevelInformation() {
	   
	   return this.level;
   }
   public Counter getRemainingBlocks() {
	   
	   return this.remainingBlocks;
   }
   public Counter getRemainingBalls() {
	   
	   return this.remainingBalls;
   }
   public void createBallsOfDirectHitLevel() {
		
	   Ball ball = new Ball(new Point (400, 550), 5, Color.WHITE);
	   ball.setVelocity(0,5);
	   ball.setGameEnvironment(environment);
	   ball.addToGame(this);
   }
   public void createBallsOfWideEasyLevel() {
	   
   
	   for (int i = 0; i < 5; i++) {
			Ball ball1 = new Ball(new Point (370 - i * 30, 550), 5, Color.WHITE);
			Ball ball2 = new Ball(new Point (430 + i * 30, 550), 5, Color.WHITE);
			ball1.setVelocity(0,5);
		    ball1.setGameEnvironment(environment);
		    ball1.addToGame(this);
		    
		    ball2.setVelocity(0,5);
		    ball2.setGameEnvironment(environment);
		    ball2.addToGame(this); 
	   }
   }
  
   public void createBallsOfGreen3Level() {
	   
	   Ball ball1 = new Ball(new Point (385, 550), 5, Color.WHITE);
		Ball ball2 = new Ball(new Point (415, 550), 5, Color.WHITE);
		ball1.setVelocity(0,5);
	    ball1.setGameEnvironment(environment);
	    ball1.addToGame(this);
	    
	    ball2.setVelocity(0,5);
	    ball2.setGameEnvironment(environment);
	    ball2.addToGame(this);
   }
   public void createBallsOfFinalFourLevel() {
	   
	   Ball ball1 = new Ball(new Point (385, 550), 5, Color.WHITE);
		Ball ball2 = new Ball(new Point (415, 550), 5, Color.WHITE);
		Ball ball3 = new Ball(new Point (400, 550), 5, Color.WHITE);
		ball1.setVelocity(0,5);
	    ball1.setGameEnvironment(environment);
	    ball1.addToGame(this);
	    
	    ball2.setVelocity(0,5);
	    ball2.setGameEnvironment(environment);
	    ball2.addToGame(this);
	    
	    ball3.setVelocity(0,5);
	    ball3.setGameEnvironment(environment);
	    ball3.addToGame(this);

   }
   
   
}