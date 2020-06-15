/**
 * Rebecca Tashman.
 * 336423124
 */

import gamesetup.GameLevel;
import levels.LevelOne;
import levels.LevelTwo;

/**
 * this class has a main function to start and run the game.
 */

public class Ass5Game {
    /**
     * main function to start and run the game.
     * @param args not used
     */
    public static void main(String[] args) {
        GameLevel gameLevel = new GameLevel(new LevelTwo());
        gameLevel.initialize();
        gameLevel.run();
    }
}
