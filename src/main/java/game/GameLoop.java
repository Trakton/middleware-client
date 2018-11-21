package game;

import game.entities.Player;
import game.entities.events.Event;
import game.events.EventsConsumer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Queue;
import javax.swing.*;

public class GameLoop extends JPanel implements ActionListener {

  static Player[] players;
  public static Queue<Event> events;
  public static GameState state;

  static long lastFrameTime;
  static long currentFrameTime;
  Timer timer;

  public static double deltaTime() {
    return (double) (currentFrameTime - lastFrameTime) / 1_000_000_000.0;
  }

  public static Player getPlayer(int id) {
    for (Player player : players) {
      if (player.id == id) return player;
    }
    return null;
  }

  public static Player getEnemy(int id) {
    for (Player player : players) {
      if (player.id != id) return player;
    }
    return null;
  }

  public static boolean isPlayerOne(int id) {
    return players[0].id == id;
  }

  public static Player getPlayerOne() {
    return players[0];
  }

  public GameLoop() {
    state = new GameState();

    players = new Player[2];

    events = new LinkedList<Event>();

    addKeyListener(new GameController());
    setBackground(Color.black);
    setFocusable(true);
    setPreferredSize(new Dimension(GameConstants.BOARD_WIDTH, GameConstants.BOARD_HEIGHT));

    timer = new Timer(0, this);
    timer.start();

    lastFrameTime = currentFrameTime = System.nanoTime();
  }

  public void start(int playerOneID, int playerTwoID) {
    players[0] =
        new Player(playerOneID, GameConstants.INITIAL_PLAYER_1_X, GameConstants.INITIAL_PLAYER_Y);
    players[1] =
        new Player(playerTwoID, GameConstants.INITIAL_PLAYER_2_X, GameConstants.INITIAL_PLAYER_Y);
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    switch (state.state) {
      case PENDING:
        drawText(g, "Waiting for other player...");
        break;
      case TO_START:
        for (Player player : players) player.draw(g, this);
        drawText(g, String.format("Game will start in %.2f seconds!", state.countdown));
      case STARTED:
        for (Player player : players) player.draw(g, this);
        break;
      case OVER:
        for (Player player : players) player.draw(g, this);
        drawText(g, String.format("Game Over! Player %d won!", state.winner));
        break;
    }

    Toolkit.getDefaultToolkit().sync();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    EventsConsumer.consume();

    switch (state.state) {
      case TO_START:
        state.updateCountdown();
      case STARTED:
        for (Player player : players) player.update();
        break;
      default:
        break;
    }

    repaint();

    lastFrameTime = currentFrameTime;
    currentFrameTime = System.nanoTime();
  }

  void drawText(Graphics g, String msg) {
    Font small = new Font("Helvetica", Font.BOLD, 14);
    FontMetrics metr = getFontMetrics(small);
    g.setColor(Color.white);
    g.setFont(small);
    g.drawString(
        msg,
        (GameConstants.BOARD_WIDTH - metr.stringWidth(msg)) / 2,
        GameConstants.BOARD_HEIGHT / 2);
  }
}
