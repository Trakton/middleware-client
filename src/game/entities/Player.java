package game.entities;

import game.constants.GameConstants;

import javax.swing.*;
import java.awt.*;

public class Player {

    public int x;
    public int y;
    public int lives;
    public Image sprite;

    public Player(int x, int y){
        this.x = x;
        this.y = y;

        lives = GameConstants.INITIAL_LIVES;
        sprite = (new ImageIcon(GameConstants.PLAYER_SPRITE)).getImage();
    }

    public void moveUp(){
        y -= GameConstants.PLAYER_SPEED;
    }

    public void moveDown(){
        y += GameConstants.PLAYER_SPEED;
    }
}
