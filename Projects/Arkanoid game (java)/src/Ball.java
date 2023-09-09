/**
 * @author Yuli Bar id-206841611
 */
import biuoop.DrawSurface;

public class Ball implements Sprite {

    private Point center;
    private int radius;
    private java.awt.Color color;
    private Velocity vel;
    private GameEnvironment environment;

    // constructor
    public Ball(Point center, int r, java.awt.Color color) {

        this.center = center;
        this.radius = r;
        this.color = color;
    }

    public Ball(double x, double y, int r, java.awt.Color color) {

        Point p = new Point(x, y);
        this.center = p;
        this.radius = r;
        this.color = color;
    }
    

    // accessors
    public int getX() {

        return (int) this.center.getX();
    }

    public int getY() {

        return (int) this.center.getY();
    }

    public int getSize() {

        return this.radius;
    }

    public java.awt.Color getColor() {

        return this.color;
    }
    public Point getCenter() {
        
        return this.center;
    }

    // draw the ball on the given DrawSurface
    public void drawOn(DrawSurface surface) {

        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.radius);
        surface.setColor(java.awt.Color.BLACK);
        surface.drawCircle(this.getX(), this.getY(), this.radius);
    }

    public void setVelocity(Velocity v) {

        this.vel = v;

    }

    public void setVelocity(double dx, double dy) {

        this.vel = new Velocity(dx, dy);

    }

    public Velocity getVelocity() {

        return this.vel;

    }
  //only relevant to task 2 
    public void moveOneStep(DrawSurface d) {

        if ((this.center.getX() + this.radius + this.vel.getDx()) <= d.getWidth() && (this.center.getY() + this.radius + this.vel.getDy()) <= d.getHeight()
                && (this.center.getX() - this.radius + this.vel.getDx()) >= 0 && (this.center.getY() - this.radius + this.vel.getDy()) >= 0) {

            this.center = this.getVelocity().applyToPoint(this.center);
            
        }
        else { if ((this.center.getX() + this.radius) == d.getWidth() || (this.center.getX() - this.radius) == 0 
        		|| (this.center.getY() + this.radius) == d.getHeight() || (this.center.getY() - this.radius) == 0) {
                	if ((this.center.getX() + this.radius) == d.getWidth() || (this.center.getX() - this.radius) == 0) {
        	          	double newDx = -1 * this.vel.getDx();
                        this.setVelocity(newDx, this.vel.getDy());
                        this.center = this.getVelocity().applyToPoint(this.center);
        	}
        	if ((this.center.getY() + this.radius) == d.getHeight() || (this.center.getY() - this.radius) == 0) {
        		double newDy = -1 * this.vel.getDy();
        		this.setVelocity(this.vel.getDx(), newDy);
                this.center = this.getVelocity().applyToPoint(this.center);
        	}
        	
        }
        
               else  {
                  if ((this.center.getX() + this.radius + this.vel.getDx()) > d.getWidth()) {
                this.center = new Point(d.getWidth() - radius, center.getY());
                double newDx = -1 * this.vel.getDx();
                this.setVelocity(newDx, this.vel.getDy());
                

            }
            if ((this.center.getY() + this.radius + this.vel.getDy()) > d.getHeight()) {
                this.center = new Point(center.getX(), d.getHeight() - radius);
                double newDy = (-1) * this.vel.getDy();
                this.setVelocity(this.vel.getDx(), newDy);
                

            }
            if ((this.center.getX() - this.radius + this.vel.getDx()) < 0) {
                this.center = new Point(0 + radius, center.getY());
                double newDx = -1 * this.vel.getDx();
                this.setVelocity(newDx, this.vel.getDy());
                

            }
            if ((this.center.getY() - this.radius + this.vel.getDy()) < 0) {
                this.center = new Point(center.getX(), 0 + radius);
                double newDy = (-1) * this.vel.getDy();
                this.setVelocity(this.vel.getDx(), newDy);
                

            }
        }
        
} 
        }
    //only relevant to task 2 
    public void moveOneStepInBounds(int x1, int y1, int x2, int y2) {

        if ((this.center.getX() + this.radius + this.vel.getDx()) <= x2 && (this.center.getY() + this.radius + this.vel.getDy()) <= y2
                && (this.center.getX() - this.radius + this.vel.getDx()) >= x1 && (this.center.getY() - this.radius + this.vel.getDy()) >= y1) {

            this.center = this.getVelocity().applyToPoint(this.center);
        }
        else { if ((this.center.getX() + this.radius) == (x2) || (this.center.getX() - this.radius) == (x1) 
        		|| (this.center.getY() + this.radius) == (y2) || (this.center.getY() - this.radius) == (y1)) {
        	if ((this.center.getX() + this.radius) == (x2) || (this.center.getX() - this.radius) == (x1)) {
        		double newDx = -1 * this.vel.getDx();
                this.setVelocity(newDx, this.vel.getDy());
                this.center = this.getVelocity().applyToPoint(this.center);
        	}
        	if ((this.center.getY() + this.radius) == (y2) || (this.center.getY() - this.radius) == (y1)) {
        		double newDy = -1 * this.vel.getDy();
        		this.setVelocity(this.vel.getDx(), newDy);
                this.center = this.getVelocity().applyToPoint(this.center);
        	}
        }
        else  {
            if ((this.center.getX() + this.radius + this.vel.getDx()) > x2) {
                this.center = new Point(x2 - radius, center.getY());
                double newDx = -1 * this.vel.getDx();
                this.setVelocity(newDx, this.vel.getDy());
                

            }
            if ((this.center.getY() + this.radius + this.vel.getDy()) > y2) {
                this.center = new Point(center.getX(), y2 - radius);
                double newDy = (-1) * this.vel.getDy();
                this.setVelocity(this.vel.getDx(), newDy);
                

            }
            if ((this.center.getX() - this.radius + this.vel.getDx()) < x1) {
                this.center = new Point(x1 + radius, center.getY());
                double newDx = -1 * this.vel.getDx();
                this.setVelocity(newDx, this.vel.getDy());
                

            }
            if ((this.center.getY() - this.radius + this.vel.getDy()) < y1) {
                this.center = new Point(center.getX(), y1 + radius);
                double newDy = (-1) * this.vel.getDy();
                this.setVelocity(this.vel.getDx(), newDy);
                

            }
        }
        }
        
}
    public Line trajectory() {
        Point start = new Point (this.getX(), this.getY());
        Point end = new Point (this.getX() + this.getVelocity().getDx(), this.getY() + this.getVelocity().getDy());
        Line line = new Line (start,end);
        return line;
    }
    
    public void movement() {
        Line trajectory = this.trajectory();
        
        if (this.environment.getClosestCollision(trajectory).collisionPoint() == null) {
            this.center = trajectory.end();          
        }
        else {
            CollisionInfo info = this.environment.getClosestCollision(trajectory);
            
            Point collisionPoint = info.collisionPoint();
            Velocity newVelocity = info.collisionObject().hit(this, collisionPoint, this.vel); 
            
                               
            Point newCenter = this.newCenterLocation (collisionPoint);
            
            this.center = newCenter;
            this.vel = newVelocity; 
             
        }
    }
    
   public void setGameEnvironment (GameEnvironment environment) {
       this.environment = environment;
   }
    
   public void timePassed() {
       this.movement();
   }
   public void addToGame(GameLevel g) {
       g.addSprite(this);
       g.increaseRemainedBalls(1);
   }
   public Point newCenterLocation (Point collisionPoint) {
       double newX = collisionPoint.getX();
       double newY = collisionPoint.getY();
       double angle;
       if (this.vel.getDx() == 0) {
           if (this.vel.getDy() > 0) {
               newY = newY - this.radius;
           }
           else {
               newY = newY + this.radius;
           }
       }
       if (this.vel.getDy() == 0) {
           if (this.vel.getDx() > 0) {
               newX = newX - this.radius;
           }
           else {
               newX = newX + this.radius;
           }
       }
       if (this.vel.getDx() > 0 && this.vel.getDy() > 0) {
          angle = Math.atan(Math.abs(this.vel.getDx()) / Math.abs (this.vel.getDy()));
          newX = newX - Math.sin(angle) * radius;
          newY = newY - Math.cos(angle) * radius;
       }
       if (this.vel.getDx() > 0 && this.vel.getDy() < 0) {
           angle = Math.atan(Math.abs(this.vel.getDy()) / Math.abs(this.vel.getDx()));
           newX = newX - Math.cos(angle) * radius;
           newY = newY + Math.sin(angle) * radius;
   }
       if (this.vel.getDx() < 0 && this.vel.getDy() < 0) {
           angle = Math.atan(Math.abs(this.vel.getDy()) / Math.abs (this.vel.getDx()));
           newX = newX + Math.cos(angle) * radius;
           newY = newY + Math.sin(angle) * radius;
        }
       if (this.vel.getDx() < 0 && this.vel.getDy() > 0) {
           angle = Math.atan(Math.abs(this.vel.getDx()) / Math.abs (this.vel.getDy()));
           newX = newX + Math.sin(angle) * radius;
           newY = newY - Math.cos(angle) * radius;
        }
       Point newCenter = new Point (newX, newY);
       return newCenter;
       
    
}
   public void removeFromGame(GameLevel game) {
       game.removeSprite(this);
   }
   
}