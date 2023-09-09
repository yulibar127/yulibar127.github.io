import biuoop.DrawSurface;
import java.awt.Color;
import java.util.List;
import java.util.ArrayList;
public class Block implements Collidable, Sprite, HitNotifier {
	
	private Rectangle collisionRectangle;
	private Color color;
	private List<HitListener> hitListeners = new ArrayList<HitListener>();

	public Rectangle getCollisionRectangle() {
		
		return this.collisionRectangle;
	
	}
	public Color getColor() {
		return this.color;
	}
	public Block (Rectangle collisionRectangle, java.awt.Color color) {
		this.collisionRectangle = collisionRectangle;
		this.color = color;
	}
	//copy constructor
	public Block (Block block) {
		this.collisionRectangle = new Rectangle(block.getCollisionRectangle());
		this.color = block.getColor();
	}
	 public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
		 
		 this.notifyHit(hitter);
		 double newDx = currentVelocity.getDx();
		 double newDy = currentVelocity.getDy();
		 if (collisionPoint.equals(this.collisionRectangle.getUpperLeft()) 
				 || collisionPoint.equals(this.collisionRectangle.getUpperRight())) {
			 if (currentVelocity.getDy() > 0) {
				 newDy = newDy * -1;
			 }
			 else {
				 newDx = newDx * -1;
			 }				 
		 }
		 if (collisionPoint.equals(this.collisionRectangle.getLowerLeft()) 
				 || collisionPoint.equals(this.collisionRectangle.getLowerRight())) {
			 if (currentVelocity.getDy() >= 0) {				 
				 newDx = newDx * -1;
			 }
			 else {
				 newDy = newDy * -1;
			 }				 
		 }
		 
		 if (collisionPoint.getX() > this.collisionRectangle.getUpperLeft().getX() 
				 && collisionPoint.getX() < this.collisionRectangle.getUpperLeft().getX() + this.collisionRectangle.getWidth()) {
			 newDy = newDy * -1;
			 
		 }
		 if (collisionPoint.getY() > this.collisionRectangle.getUpperLeft().getY() 
				 && collisionPoint.getY() < this.collisionRectangle.getUpperLeft().getY() + this.collisionRectangle.getHeight()) {
			 newDx = newDx * -1;
		 }
		 Velocity v = new Velocity (newDx, newDy);
		 return v;
	 }
	 public void drawOn (DrawSurface d) {
		 d.setColor(this.color);
		 d.fillRectangle((int)collisionRectangle.getUpperLeft().getX(), (int)collisionRectangle.getUpperLeft().getY()
				 , (int)collisionRectangle.getWidth(), (int)collisionRectangle.getHeight());
		 d.setColor(Color.BLACK);
		 d.drawRectangle((int)collisionRectangle.getUpperLeft().getX(), (int)collisionRectangle.getUpperLeft().getY()
				 , (int)collisionRectangle.getWidth(), (int)collisionRectangle.getHeight());
	 }
	
	 public void timePassed() {
		 
	 }
	 public void addToGame(GameLevel g) {
	       g.addSprite(this);
	       g.addCollidable(this);
	       
	   }
	 public void removeFromGame(GameLevel game) {
		 
		 game.removeCollidable(this);
		 game.removeSprite(this);
	 }
	 public void addHitListener(HitListener hl) {
		 this.hitListeners.add(hl);
	 }
	   // Remove hl from the list of listeners to hit events.
	 public void removeHitListener(HitListener hl) {
		 this.hitListeners.remove(hl);
	 }
	 private void notifyHit(Ball hitter) {
		 
	      // Make a copy of the hitListeners before iterating over them.
	      List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
	      // Notify all listeners about a hit event:
	      for (HitListener hl : listeners) {
	         hl.hitEvent(this, hitter);
	      }
	   }
}
