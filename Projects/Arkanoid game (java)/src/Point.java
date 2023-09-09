/**
 * @author Yuli Bar id-206841611
 */
/**
 * the class represents a point on the coordinate system.
 */
public class Point {

    private double x;
    private double y;

    /**
     * the constructor of the class.
     * @param x - x coordinate
     * @param y - y coordinate
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    /**
     * returns the distance between two points.
     * @param other - another point
     * @return the distance between the two points
     */
    public double distance(Point other) {

        double distance;
        double xDistance = this.x - other.getX();
        double yDistance = this.y - other.getY();
        distance = Math.sqrt(xDistance * xDistance + yDistance * yDistance);
        return distance;
    }
    /**
     * return weather if two points are equal.
     * @param other - another point
     * @return true if two points are equal, false if not
     */

    public boolean equals(Point other) {

        if (this.x == other.getX() && this.y == other.getY()) {

            return true;
        }

        return false;

    }
    /**
     * return the x coordinate.
     * @return x coordinate
     */
    public double getX() {

        return this.x;
    }
    /**
     * return the y coordinate.
     * @return y coordinate
     */
    public double getY() {

        return this.y;
    }
}
