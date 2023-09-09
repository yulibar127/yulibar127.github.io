 /**
 * @author Yuli Bar id-206841611
 */
public class Velocity {

    private double dx;
    private double dy;

    // constructor
    public Velocity(double dx, double dy) {

        this.dx = dx;
        this.dy = dy;
    }

    // Take a point with position (x,y) and return a new point
    // with position (x+dx, y+dy)
    public Point applyToPoint(Point p) {

        Point point = new Point(p.getX() + this.dx, p.getY() + this.dy);
        return point;
    }

    public double getDx() {

        return this.dx;
    }

    public double getDy() {

        return this.dy;
    }

    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx, dy;
        
            double angleInRadians = Math.toRadians(angle);
         
            dx = Math.sin(angleInRadians) * speed;
            dy = Math.cos(angleInRadians) * speed;
        
        
        return new Velocity(dx, dy);
    }
    public double getSpeed() {
        double speed = Math.sqrt(this.dx * this.dx + this.dy * this.dy);
        return speed;
    }
}