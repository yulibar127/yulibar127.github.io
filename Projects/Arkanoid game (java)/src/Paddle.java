import biuoop.KeyboardSensor;

import java.awt.Color;

import biuoop.DrawSurface;

public class Paddle implements Sprite, Collidable {
	
   private KeyboardSensor keyboard;
   private Rectangle collisionRectangle;
   private java.awt.Color color;
   private int speed;
   private Point startUpperLeft;
   
   public Paddle (KeyboardSensor keyboard, Rectangle collisionRectangle, java.awt.Color color, int speed, Point startUpperLeft) {
	   this.keyboard = keyboard;
	   this.collisionRectangle = collisionRectangle;
	   this.color = color;
	   this.speed = speed;
	   this.startUpperLeft = startUpperLeft;
   }

   public void moveLeft() {
	   if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
		   System.out.println("the 'left arrow' key is pressed");
		   Point newUpperLeft = new Point (this.collisionRectangle.getUpperLeft().getX() - speed
				   , this.collisionRectangle.getUpperLeft().getY());
		   this.collisionRectangle = new Rectangle (newUpperLeft, this.collisionRectangle.getWidth()
				   , this.collisionRectangle.getHeight());
		 }
   }
   public void moveRight() {
	   if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
		   System.out.println("the 'right arrow' key is pressed");
		   Point newUpperLeft = new Point (this.collisionRectangle.getUpperLeft().getX() + speed
				   , this.collisionRectangle.getUpperLeft().getY());
		   this.collisionRectangle = new Rectangle (newUpperLeft, this.collisionRectangle.getWidth()
				   , this.collisionRectangle.getHeight());
		 }
   }
   
   // Sprite
   public void timePassed() {
	   moveLeft();
	   moveRight();
   }
   public void drawOn(DrawSurface d) {
	   d.setColor(this.color);
	   d.fillRectangle((int)collisionRectangle.getUpperLeft().getX(), (int)collisionRectangle.getUpperLeft().getY()
				 , (int)collisionRectangle.getWidth(), (int)collisionRectangle.getHeight());
	   d.setColor(Color.BLACK);
	   d.drawRectangle((int)collisionRectangle.getUpperLeft().getX(), (int)collisionRectangle.getUpperLeft().getY()
				 , (int)collisionRectangle.getWidth(), (int)collisionRectangle.getHeight());
   }

   // Collidable
   
   public Rectangle getCollisionRectangle() {
	   return this.collisionRectangle;
   }
   
   public Velocity hit(Ball hitBall, Point collisionPoint, Velocity currentVelocity) {
	   double currentSpeed = currentVelocity.getSpeed();
	   Velocity newVelocity = new Velocity (0,0); //initializing
	   double regionLength = this.collisionRectangle.getWidth() / 5;
	   Point upperLeft = this.collisionRectangle.getUpperLeft();
	   Line [] regions = new Line [5];
	   regions[0] = new Line (upperLeft.getX(), upperLeft.getY()
			   ,upperLeft.getX() + regionLength, upperLeft.getY());
	   
	   regions[1] = new Line (upperLeft.getX() + regionLength, upperLeft.getY()
			   ,upperLeft.getX() + 2 * regionLength, upperLeft.getY());
	   
	   regions[2] = new Line (upperLeft.getX() + 2 * regionLength, upperLeft.getY()
			   ,upperLeft.getX() + 3 * regionLength, upperLeft.getY());
	   
	   regions[3] = new Line (upperLeft.getX() + 3 * regionLength, upperLeft.getY()
			   ,upperLeft.getX() + 4 * regionLength, upperLeft.getY());
	   
	   regions[4] = new Line (upperLeft.getX() + 4 * regionLength, upperLeft.getY()
			   ,upperLeft.getX() + this.collisionRectangle.getWidth(), upperLeft.getY());
	   
	   if (collisionPoint.getX() == regions[4].end().getX()) {
		   int angle = 120;
		   newVelocity = Velocity.fromAngleAndSpeed(angle, currentSpeed); 
		   return newVelocity;
	   }
	   
	   for (int i = 0; i < regions.length; i++) {
		   
		   if (collisionPoint.getX() >= regions[i].start().getX() && collisionPoint.getX() < regions[i].end().getX()) {
			   int angle = 240 - i * 30;
			  
			   if (angle == 180) {
				   newVelocity = new Velocity(currentVelocity.getDx(), currentVelocity.getDy() * -1);
			   }
			   else {
			       newVelocity = Velocity.fromAngleAndSpeed(angle, currentSpeed); 
			   }
		   }
	   }
	   return newVelocity;
   } 
   

   // Add this paddle to the game.
   public void addToGame(GameLevel g) {
	   g.addSprite(this);
       g.addCollidable(this);
   }
   public void removeFromGame(GameLevel game) {
		 
		 game.removeCollidable(this);
		 game.removeSprite(this);
	 }
   
   public int getSpeed() {
	   return this.speed;
   }
   public Point getStartUpperLeft() {
	   return this.startUpperLeft;
   }
}