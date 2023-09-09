import biuoop.GUI;
import biuoop.KeyboardSensor;
import java.util.List;
import java.util.ArrayList;

public class AssGame {

    public static void main (String[]args) {
    	
    	AnimationRunner runner = new AnimationRunner (new GUI("Arkanoid", 800, 600), 60);
    	KeyboardSensor keyboard = runner.getGui().getKeyboardSensor();
    	
    	LevelInformation level1 = new DirectHit();
    	LevelInformation level2 = new WideEasy();
    	LevelInformation level3 = new Green3();
    	LevelInformation level4 = new FinalFour();
		
    	List<LevelInformation> levelList = new ArrayList<LevelInformation>();
    	levelList.add(level1);
    	levelList.add(level2);
    	levelList.add(level3);
    	levelList.add(level4);
    	
    	GameFlow flow = new GameFlow (runner, keyboard);
    	flow.runLevels(levelList);
		
	}
}
