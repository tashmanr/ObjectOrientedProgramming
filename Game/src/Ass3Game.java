/**
 * Rebecca Tashman.
 * 336423124
 */

import gamesetup.Game;

/**
 * this class has a main function to start and run the game.
 */

public class Ass3Game {
    /**
     * main function to start and run the game.
     * @param args not used
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }
}
