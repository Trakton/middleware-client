package game;

import game.constants.GameConstants;
import game.entities.Player;
import game.entities.Bullet;
import game.entities.events.Event;
import game.events.EventsConsumer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.Queue;
import java.util.LinkedList;

public class GameLoop extends JPanel implements ActionListener {

    static public Player[] players;
    static public Queue<Event> events;

    static long lastFrameTime;
    static long currentFrameTime;
    Timer timer;

    public GameLoop() {
        players = new Player[2];
        players[0] = new Player(GameConstants.PLAYER_ONE, GameConstants.INITIAL_PLAYER_1_X, GameConstants.INITIAL_PLAYER_Y);
        players[1] = new Player(GameConstants.PLAYER_TWO, GameConstants.INITIAL_PLAYER_2_X, GameConstants.INITIAL_PLAYER_Y);

        events = new LinkedList<Event>();

        addKeyListener(new GameController());
        setBackground(Color.black);
        setFocusable(true);
        setPreferredSize(new Dimension(GameConstants.BOARD_WIDTH, GameConstants.BOARD_HEIGHT));

        timer = new Timer(0, this);
        timer.start();

        lastFrameTime = currentFrameTime = System.nanoTime();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for(Player player : players){
            g.drawImage(player.sprite, player.x, player.y, this);

            for(Bullet bullet : player.bullets){
                g.drawImage(bullet.sprite, bullet.x, bullet.y, this);
            }
        }

        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        EventsConsumer.consume();

        for(Player player : players){
            player.update();
        }

        repaint();

        lastFrameTime = currentFrameTime;
        currentFrameTime = System.nanoTime();
    }

    static public double deltaTime(){
        return (double)(currentFrameTime - lastFrameTime)/1_000_000_000.0;
    }
}