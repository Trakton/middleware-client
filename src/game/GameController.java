package game;

import game.events.EventsProducer;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameController extends KeyAdapter {

    @Override
    public void keyPressed(KeyEvent e) {
        if(GameLoop.state.state != GameStates.STARTED) return;

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            EventsProducer.handleMoveUp(GameConstants.PLAYER_ONE);
        }

        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            EventsProducer.handleMoveDown(GameConstants.PLAYER_ONE);
        }

        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            EventsProducer.handleFire(GameConstants.PLAYER_ONE);
        }
    }
}