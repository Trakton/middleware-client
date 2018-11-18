package game;

public class GameConstants {
    // Board
    static public final int BOARD_WIDTH = 800;
    static public final int BOARD_HEIGHT = 600;

    //State
    static public final int TO_START_COUNTDOWN = 7;

    //Player
    static public final int PLAYER_SIZE = 40;
    static public final int INITIAL_PLAYER_1_X = PLAYER_SIZE;
    static public final int INITIAL_PLAYER_2_X = BOARD_WIDTH - PLAYER_SIZE * 2;
    static public final int INITIAL_PLAYER_Y = BOARD_HEIGHT / 2 - PLAYER_SIZE / 2;
    static public final int INITIAL_LIVES = 3;
    static public final int PLAYER_SPEED = 12;

    //Live
    static public final int LIVE_Y = 30;
    static public final int LIVE_PLAYER_1_X = 50;
    static public final int LIVE_SIZE = 25;
    static public final int LIVE_PLAYER_2_X = BOARD_WIDTH - 3*LIVE_SIZE - 50;

    //Bullet
    static public final int BULLET_SPEED = 400;

    //Resources
    static public final String PLAYER_SPRITE = "src/resources/player.png";
    static public final String BULLET_SPRITE = "src/resources/bullet.png";
    static public final String LIVE_SPRITE = "src/resources/live.png";
}
