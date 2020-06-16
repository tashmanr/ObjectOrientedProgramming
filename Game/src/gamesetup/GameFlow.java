/**
 * 336423124
 * Rebecca Tashman
 */
package gamesetup;

import animations.EndScreen;
import animations.KeyPressStoppableAnimation;
import biuoop.KeyboardSensor;
import interfaces.LevelInformation;
import java.util.List;

/**
 * Gameflow class to run the game in the proper order.
 */
public class GameFlow {
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private Counter score;

    /**
     * Constructor.
     * @param ar animation runner to run the game
     * @param ks keyboard sensor for the paddle, animation screens, etc
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        animationRunner = ar;
        keyboardSensor = ks;
        score = new Counter();
    }

    /**
     * Function to run the game, in the order of the levels received in the list.
     * @param levels list of levels, order to play the game
     */
    public void runLevels(List<LevelInformation> levels) {
        int lastLevel = levels.size();
        int currentLevel = 1;
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, keyboardSensor, animationRunner, score);
            level.initialize();
            while (level.getRunning()) {
                level.run();
            }
            currentLevel++;
        }
        animationRunner.run(new KeyPressStoppableAnimation(keyboardSensor, KeyboardSensor.SPACE_KEY,
                new EndScreen(score, false)));
    }
}
