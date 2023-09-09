import java.util.ArrayList;
import java.util.List;
import biuoop.DrawSurface;

public class SpriteCollection {
	
	private List<Sprite> spriteList;
	
	public SpriteCollection() {
		this.spriteList = new ArrayList<Sprite>();
	}
   public void addSprite(Sprite s) {
	   this.spriteList.add(s);
   }
   public void removeSprite(Sprite s) {
	   this.spriteList.remove(s);
   }
 
   // call timePassed() on all sprites.
   public void notifyAllTimePassed() {
	   for (int i = 0; i < this.spriteList.size(); i++) {
		   this.spriteList.get(i).timePassed();
	   }
   }

   // call drawOn(d) on all sprites.
   public void drawAllOn(DrawSurface d) {
	   for (int i = 0; i < this.spriteList.size(); i++) {
		   this.spriteList.get(i).drawOn(d);
   }
}
   public List<Sprite> getSpriteList() {
	   
	   return this.spriteList;
   }
}