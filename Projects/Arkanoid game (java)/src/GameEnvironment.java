import java.util.ArrayList;
import java.util.List;

public class GameEnvironment {

	private List<Collidable> collidableList;
	
	public GameEnvironment() {
		this.collidableList =  new ArrayList<Collidable>();
	}
	public GameEnvironment (List<Collidable> collidableList) {
		this.collidableList = collidableList;
		
	}
	public List<Collidable> getCollidableList() {
		return this.collidableList;
	}
	
	public void addCollidable(Collidable c) {
		collidableList.add(c);
	}
	public void removeCollidable(Collidable c) {
		collidableList.remove(c);
	}
	public CollisionInfo getClosestCollision(Line trajectory) {
		Collidable closestCollidable = null;
		Point collisionPoint = null;
		Point closestCollisionPoint = null;
		double minDistance = 2000;
		for (int i = 0; i < collidableList.size(); i++) {
			collisionPoint = trajectory.closestIntersectionToStartOfLine(this.collidableList.get(i).getCollisionRectangle());
			if (collisionPoint != null) {
				double distance = collisionPoint.distance(trajectory.start());
				if (distance < minDistance) {
					minDistance = distance;
					closestCollisionPoint = collisionPoint;
					closestCollidable = this.collidableList.get(i);
				}
			}
		}
		CollisionInfo info = new CollisionInfo (closestCollisionPoint, closestCollidable);
		return info;
	}
	
}
