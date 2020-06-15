/**
 * 336423124
 * Rebecca Tashman
 */
package gamesetup;

import biuoop.KeyboardSensor;
import interfaces.LevelInformation;

import java.util.List;

public class GameFlow {
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private Counter score;

    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        animationRunner = ar;
        keyboardSensor = ks;
        score = new Counter();
    }

    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, keyboardSensor, animationRunner, score);
            level.initialize();
            while (level.getRunning()) {
                level.run();
            }
        }
    }
}
