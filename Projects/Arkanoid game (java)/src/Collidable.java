
public interface Collidable {
	
	Rectangle getCollisionRectangle();
		
	Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
	
}
