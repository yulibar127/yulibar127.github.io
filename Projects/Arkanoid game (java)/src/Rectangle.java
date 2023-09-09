import java.util.ArrayList;
import java.util.List;

public class Rectangle {
	
	private Point upperLeft;
	private double width;
	private double height;

	public Rectangle(Point upperLeft, double width, double height) {
		
		this.upperLeft = upperLeft;
		this.width = width;
		this.height = height;
	}
	//copy constructor
	public Rectangle(Rectangle r) {
		this.upperLeft = new Point (r.getUpperLeft().getX(), r.getUpperLeft().getY());
		this.width = r.getWidth();
		this.height = r.getHeight();
	}
	 public List<Point> intersectionPoints(Line line) {
		 
		 List<Point> pointList = new ArrayList<Point>();
		 
		 Line[] rectangleFrame = new Line[4];
		 Point p;
		 rectangleFrame[0] = new Line(this.upperLeft.getX(),this.upperLeft.getY()
				 , this.upperLeft.getX() + this.width, this.upperLeft.getY());
		 rectangleFrame[1] = new Line(this.upperLeft.getX(),this.upperLeft.getY()
				 , this.upperLeft.getX(), this.upperLeft.getY() + this.height);
		 rectangleFrame[2] = new Line(this.upperLeft.getX() + this.width, this.upperLeft.getY()
				 , this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height);
		 rectangleFrame[3] = new Line(this.upperLeft.getX(), this.upperLeft.getY() + this.height
				 , this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height);
		 for (int i = 0; i < 4; i++) {
	          p = rectangleFrame[i].intersectionWith(line);
	          if (p != null) {
	        	  pointList.add(p);
	          }	          
}
		
			 return pointList;
		 
	 }
	 public double getWidth() {
		return this.width; 
	 }
	 public double getHeight() {
		return this.height; 
	 }
	 public Point getUpperLeft() {
		 return this.upperLeft;
	 }
	 public Point getUpperRight() {
		 Point p = new Point (upperLeft.getX() + width, upperLeft.getY());
		 return p;
	 }
	 public Point getLowerLeft() {
		 Point p = new Point (upperLeft.getX(), upperLeft.getY() + height);
		 return p;
	 }
	 public Point getLowerRight() {
		 Point p = new Point (upperLeft.getX() + width, upperLeft.getY() + height);
		 return p;
	 }
	 
	 
	 
}
