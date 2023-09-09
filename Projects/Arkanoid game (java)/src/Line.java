import java.util.List;

/**
 * @author Yuli Bar id-206841611
 */
/**
 * the class represents a line on the coordinate system.
 */
public class Line {

    private Point start;
    private Point end;
    /**
     * the constructor of the class.
     * @param start - the start point of the line
     * @param end - the end point of the line
     */
    public Line(Point start, Point end) {

        this.start = start;
        this.end = end;
    }

    public Line(double x1, double y1, double x2, double y2) {

        start = new Point(x1, y1);
        end = new Point(x2, y2);

    }

    public double length() {

        double length = this.start.distance(this.end);
        return length;
    }

    public Point middle() {

        double middleX = (this.start.getX() + this.end.getX()) / 2;
        double middleY = (this.start.getY() + this.end.getY()) / 2;

        Point middle = new Point(middleX, middleY);

        return middle;
    }

    public Point start() {

        return this.start;
    }

    public Point end() {

        return this.end;
    }

    public boolean isIntersecting(Line other) {

        int o1 = orientation(this.start, this.end, other.start());
        int o2 = orientation(this.start, this.end, other.end());
        int o3 = orientation(other.start, other.end, this.start());
        int o4 = orientation(other.start, other.end, this.end());

        if (o1 != o2 && o3 != o4) {
            return true;
        }
        if (o1 == 0 && onSegment(this.start, other.start(), this.end)) {
            return true;
        }
        if (o2 == 0 && onSegment(this.start, other.end(), this.end)) {
            return true;
        }
        if (o2 == 0 && onSegment(other.start, this.start(), other.end)) {
            return true;
        }
        if (o2 == 0 && onSegment(other.start, this.end(), other.end)) {
            return true;
        }
        return false;
    }

    public int orientation(Point p1, Point q1, Point r) {

        double val = (q1.getY() - p1.getY()) * (r.getX() - q1.getX())
                - (r.getY() - q1.getY()) * (q1.getX() - p1.getX());

        if (val == 0) {

            return 0;
        }

        if (val > 0) {

            return 1;
        } else {

            return 2;
        }
    }

    public boolean onSegment(Point p1, Point r, Point q1) {

        if (r.getX() <= Math.max(p1.getX(), q1.getX()) && r.getX() >= Math.min(p1.getX(), q1.getX())
                && r.getY() <= Math.max(p1.getY(), q1.getY()) && r.getY() >= Math.min(p1.getY(), q1.getY())) {

            return true;
        }

        return false;
    }

    public Point intersectionWith(Line other) {

        if (this.isIntersecting(other) == false) {
            return null;
        }
        if (comingTogether(this, other)) {

            return null;
        }
        double x, y;
        double slope1 = this.slopeCalculation();
        double slope2 = other.slopeCalculation();
        double b1 = this.yAxisIntersection();
        double b2 = other.yAxisIntersection();
        if (slope1 != Double.MAX_VALUE && slope2 != Double.MAX_VALUE) {
            x = (b2 - b1) / (slope1 - slope2); // according to the formula of a line
            y = slope1 * x + b1;
    }
        else {
            if (slope1 == Double.MAX_VALUE) {
                x = this.start().getX();
                y = slope2 * x + b2;
            }
            else {
                x = other.start().getX();
                y = slope1 * x + b1;
            }
        }

        Point p = new Point(x, y);

        return p;

    }

    public boolean comingTogether(Line line1, Line line2) {

        if ((orientation(line1.start(), line1.end(), line2.start()) == 0)
                && (orientation(line1.start(), line1.end(), line2.end())) == 0) {

            if (line2.start().getX() < Math.max(line1.start().getX(), line1.end().getX())
                    && line2.start().getX() > Math.min(line1.start().getX(), line1.end().getX())) {

                return true;
            }

            if (line2.end().getX() < Math.max(line1.start().getX(), line1.end().getX())
                    && line2.end().getX() > Math.min(line1.start().getX(), line1.end().getX())) {

                return true;
            }
            if (line2.start().getY() < Math.max(line1.start().getY(), line1.end().getY())
                    && line2.start().getY() > Math.min(line1.start().getY(), line1.end().getY())) {

                return true;
            }
            if (line2.end().getY() < Math.max(line1.start().getY(), line1.end().getY())
                    && line2.end().getY() > Math.min(line1.start().getY(), line1.end().getY())) {

                return true;
            }

        }

        return false;
    }

    public double slopeCalculation() {
        
        if (this.start().getX() != this.end().getX()) {
            double slope = (this.start().getY() - this.end().getY()) / (this.start().getX() - this.end().getX());
            return slope;
        }
        else {
            return Double.MAX_VALUE;
        }
    }

    public double yAxisIntersection() {
        
        if (slopeCalculation() != Double.MAX_VALUE) {
            double b = this.start().getY() - slopeCalculation() * this.start().getX(); // according to the formula of a line
            return b;
        }
        else {
            return Double.MAX_VALUE;
        }

    }

    public boolean equals(Line other) {

        if ((this.start.equals(other.start()) && this.end.equals(other.end()))
                || (this.start.equals(other.end()) && this.end.equals(other.start()))) {

            return true;

        }

        return false;
    }
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> intersectionPoints = rect.intersectionPoints(this); 
        if (intersectionPoints.isEmpty())
            return null;
         double minDistance = (intersectionPoints.get(0)).distance(this.start);
         Point closest = new Point (intersectionPoints.get(0).getX()
                 , intersectionPoints.get(0).getY());
         for (int i = 1; i < intersectionPoints.size(); i++) {
             if ((intersectionPoints.get(i)).distance(this.start) < minDistance) {           
                 minDistance = (intersectionPoints.get(i)).distance(this.start);
                 closest = new Point (intersectionPoints.get(i).getX()
                         , intersectionPoints.get(i).getY());
             }
             
    }
         return closest;
}
}
