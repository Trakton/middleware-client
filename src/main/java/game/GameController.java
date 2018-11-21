package game;

import com.caio.middleware.MiddlewareException;
import game.events.EventsProducer;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class GameController extends KeyAdapter {

    @Override
    public void keyPressed(KeyEvent e) {
        if(GameLoop.state.state != GameStates.STARTED) return;

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            try {
                EventsProducer.handleMoveUp(GameLoop.getPlayerOne().id);
            } catch (MiddlewareException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            try {
                EventsProducer.handleMoveDown(GameLoop.getPlayerOne().id);
            } catch (MiddlewareException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            try {
                EventsProducer.handleFire(GameLoop.getPlayerOne().id);
            } catch (MiddlewareException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}