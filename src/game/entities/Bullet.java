package game.entities;

import game.constants.GameConstants;

public class Bullet extends Sprite {

    public int direction;

    public Bullet(int id, int x, int y, int direction){
        super(id, x, y, GameConstants.BULLET_SPRITE);

        this.direction = direction;
    }

    public void update(){
        x += GameConstants.BULLET_SPEED * direction;
    }
}
