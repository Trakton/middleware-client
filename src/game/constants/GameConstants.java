package game.constants;

public class GameConstants {
    // Board
    static public final int BOARD_WIDTH = 300;
    static public final int BOARD_HEIGHT = 300;

    //Player
    static public final int PLAYER__SIZE = 12;
    static public final int INITIAL_PLAYER_1_X = PLAYER__SIZE;
    static public final int INITIAL_PLAYER_2_X = BOARD_HEIGHT - PLAYER__SIZE * 2;
    static public final int INITIAL_PLAYER_Y = BOARD_HEIGHT / 2 - PLAYER__SIZE;
    static public final int INITIAL_LIVES = 3;

    //Resources
    static public final String PLAYER_SPRITE = "src/resources/player.png";
}
