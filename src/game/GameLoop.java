package game;

import game.entities.Player;
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
    static public GameState state;

    static long lastFrameTime;
    static long currentFrameTime;
    Timer timer;

    static public double deltaTime(){
        return (double)(currentFrameTime - lastFrameTime)/1_000_000_000.0;
    }

    public GameLoop() {
        state = new GameState();

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

        switch(state.state){
            case PENDING:
                players[0].draw(g, this);
                drawText(g, "Waiting for other player...");
                break;
            case TO_START:
                for(Player player: players) player.draw(g, this);
                drawText(g, String.format("Game will start in %.2f seconds!", state.countdown));
            case STARTED:
                for(Player player: players) player.draw(g, this);
                break;
            case OVER:
                for(Player player: players) player.draw(g, this);
                drawText(g, String.format("Game Over! Player %d won!", state.winner));
                break;

        }

        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        EventsConsumer.consume();

        switch(state.state){
            case TO_START:
                state.updateCountdown();
            case STARTED:
                for(Player player : players) player.update();
                break;
            default:
                break;
        }

        repaint();

        lastFrameTime = currentFrameTime;
        currentFrameTime = System.nanoTime();
    }

    void drawText(Graphics g, String msg){
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);
        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (GameConstants.BOARD_WIDTH - metr.stringWidth(msg)) / 2, GameConstants.BOARD_HEIGHT / 2);
    }
}