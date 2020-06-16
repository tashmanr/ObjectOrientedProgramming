/**
 * Rebecca Tashman.
 * 336423124
 */

import gamesetup.AnimationRunner;
import gamesetup.GameFlow;
import interfaces.LevelInformation;
import levels.LevelFour;
import levels.LevelOne;
import levels.LevelThree;
import levels.LevelTwo;
import java.util.ArrayList;
import java.util.List;

/**
 * this class has a main function to start and run the game.
 */

public class Ass6Game {
    /**
     * main function to start and run the game.
     *
     * @param args not used
     */
    public static void main(String[] args) {
        AnimationRunner animationRunner = new AnimationRunner();
        GameFlow gameFlow = new GameFlow(animationRunner, animationRunner.getGui().getKeyboardSensor());
        List<LevelInformation> levels = new ArrayList<>();
        for (String arg : args) {
            switch (arg) {
                case "1":
                    levels.add(new LevelOne());
                    break;
                case "2":
                    levels.add(new LevelTwo());
                    break;
                case "3":
                    levels.add(new LevelThree());
                    break;
                case "4":
                    levels.add(new LevelFour());
                    break;
                default:
                    break;
            }
        }
        if (levels.size() == 0) {
            levels.add(new LevelOne());
            levels.add(new LevelTwo());
            levels.add(new LevelThree());
            levels.add(new LevelFour());
        }
        gameFlow.runLevels(levels);
        animationRunner.getGui().close();
    }
}
