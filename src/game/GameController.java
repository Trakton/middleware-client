package game;

import game.constants.GameConstants;
import game.entities.Player;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class GameController extends JPanel implements ActionListener {

    Player[] players;
    Timer timer;

    public GameController() {
        players = new Player[2];
        players[0] = new Player(GameConstants.INITIAL_PLAYER_1_X, GameConstants.INITIAL_PLAYER_Y);
        players[1] = new Player(GameConstants.INITIAL_PLAYER_2_X, GameConstants.INITIAL_PLAYER_Y);

        addKeyListener(new Controller());
        setBackground(Color.black);
        setFocusable(true);
        setPreferredSize(new Dimension(GameConstants.BOARD_WIDTH, GameConstants.BOARD_HEIGHT));
        timer = new Timer(0, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for(Player player : players){
            g.drawImage(player.sprite, player.x, player.y, this);
        }

        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    private class Controller extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            if (key == KeyEvent.VK_UP) {

            }

            if (key == KeyEvent.VK_DOWN) {

            }
        }
    }
}