import java.awt.Color;

import biuoop.DrawSurface;

public class ScoreIndicator implements Sprite {
	
	private int score;
	private LevelInformation currentLevel;
	private int lives = 7;
	
	public ScoreIndicator() {
		this.score = 0;
	}
	public void drawOn(DrawSurface d) {
		
		
		d.setColor(Color.BLACK);
	    d.drawText(100, 15,"Lives: " + lives + "                                        " +
		"Score: " + this.score + "                                        " + "Level Name: " + currentLevel.levelName(),18);
	}
	
	public void timePassed() {
		
		
	}
	
	public void increaseScore(int number) {
		this.score = this.score + number;
	}
	
	public void setLevel (LevelInformation level) {
		
		this.currentLevel = level;
	}
	public int getScore() {
		return this.score;
	}
	public void lostLife() {
		this.lives--;
	}
	public int livesLeft() {
		return this.lives;
	}
	
	
	

}
