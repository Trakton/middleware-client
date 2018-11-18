package game.entities;

import game.GameLoop;
import game.GameConstants;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class Player extends Sprite {

    public int lives;
    public int enemy;
    public int bulletCount;
    public List<Bullet> bullets;


    public Player(int id, int x, int y){
        super(id, x, y, GameConstants.PLAYER_SPRITE);

        enemy = id == GameConstants.PLAYER_ONE? GameConstants.PLAYER_TWO : GameConstants.PLAYER_ONE;
        lives = GameConstants.INITIAL_LIVES;
        bullets = new ArrayList<Bullet>();
        bulletCount = 0;
    }

    public void update(){
        List<Bullet> bullets = new ArrayList<Bullet>();

        for(Bullet bullet : this.bullets){
            bullet.update();

            if(!bullet.outOfBounds() && !bullet.intersects(GameLoop.players[enemy])){
                bullets.add(bullet);
            }
        }

        this.bullets = bullets;
    }

    public void fire(){
        int direction = id == GameConstants.PLAYER_ONE? 1 : -1;
        int x = this.x + this.sprite.getWidth(null) * direction;
        int y = this.y + this.sprite.getHeight(null) / 2;
        bullets.add(new Bullet(bulletCount++, x, y, direction));
    }

    public void moveUp(){
        y -= GameConstants.PLAYER_SPEED * GameLoop.deltaTime();
    }

    public void moveDown(){
        y += GameConstants.PLAYER_SPEED * GameLoop.deltaTime();
    }

    @Override
    public void draw(Graphics g, JPanel target){
        super.draw(g, target);

        for(Bullet bullet: bullets){
            bullet.draw(g, target);
        }

        for(int i = 0; i < lives; i++){
            int x = id == GameConstants.PLAYER_ONE? GameConstants.LIVE_PLAYER_1_X : GameConstants.LIVE_PLAYER_2_X;
            Sprite live = new Sprite(i, x + i*GameConstants.LIVE_SIZE, GameConstants.LIVE_Y, GameConstants.LIVE_SPRITE);
            live.draw(g, target);
        }
    }
}
