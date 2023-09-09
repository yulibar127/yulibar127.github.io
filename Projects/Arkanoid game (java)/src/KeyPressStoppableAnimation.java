import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

public class KeyPressStoppableAnimation implements Animation {
	
	 private KeyboardSensor sensor;
	 private String key;
	 private Animation animation;
	 private boolean stop;
	 private boolean isAlreadyPressed;
	 
	 public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
		 
		 this.sensor = sensor;
		 this.key = key;
		 this.animation = animation;
		 this.stop = false;
		 this.isAlreadyPressed = sensor.isPressed(key);
	   
	}
	 public void doOneFrame(DrawSurface d) {
		 this.animation.doOneFrame(d);
		 if (isAlreadyPressed == false && this.sensor.isPressed(key)) {
			 this.stop = true; 
		 }
		 isAlreadyPressed = false;
		 
		 if (this.sensor.isPressed(KeyboardSensor.SPACE_KEY)) {
			 this.stop = true;
			 
		 }
	 }
	 
		
	 public boolean shouldStop() {
		 return this.stop;
	 }
	 

}
