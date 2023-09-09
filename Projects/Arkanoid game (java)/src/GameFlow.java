import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.List;
import biuoop.DrawSurface;

public class GameFlow {

	 private AnimationRunner animationRunner;
	 private KeyboardSensor keyboardSensor;
	 private ScoreIndicator indicator = new ScoreIndicator();
	
	 public GameFlow(AnimationRunner ar, KeyboardSensor ks) { 
		 
		 this.animationRunner = ar;
		 this.keyboardSensor = ks;
	 }

	   
	   public void runLevels(List<LevelInformation> levels) {
	      // ...
	      for (LevelInformation levelInfo : levels) {

	         GameLevel level = new GameLevel(levelInfo, this.keyboardSensor, this.animationRunner, this.indicator);

	         level.initialize();
	         level.runCountdownAnimation();
	         
	         while (level.getRemainingBalls().getValue() > 0 && level.getRemainingBlocks().getValue() > 0) {
	            level.run();
	         }

	         if (level.getRemainingBalls().getValue() == 0) {
	        	 DrawSurface d = this.animationRunner.getGui().getDrawSurface();
	        	 d.setColor(Color.GRAY);
	        	 d.drawText(200, 320, "Game Over. Your score is " + this.indicator.getScore(), 200);
	        	 this.animationRunner.getGui().close();
	             break; 
	         }
	         if (levelInfo instanceof FinalFour && level.getRemainingBlocks().getValue() == 0) {
	        	 DrawSurface d = this.animationRunner.getGui().getDrawSurface();
	        	 d.setColor(Color.GRAY);
	        	 d.drawText(200, 320, "You Win! Your score is " + this.indicator.getScore(), 200);
	        	 this.animationRunner.getGui().close();
	             break;
	         }
	      }
	   } 
	        
	   
	         

	      
	   
}
