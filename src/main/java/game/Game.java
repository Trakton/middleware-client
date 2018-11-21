package game;

import com.caio.middleware.MiddlewareException;
import com.caio.middleware.MiddlewareProxy;
import com.caio.middleware.proto.Fire;
import com.caio.middleware.proto.GameStatus;
import com.caio.middleware.proto.Move;
import com.caio.middleware.proto.SignIn;
import com.caio.middleware.proto.User;
import game.callables.FireCallable;
import game.callables.GameStatusCallable;
import game.callables.MoveCallable;
import game.callables.UserCallable;
import java.awt.EventQueue;
import java.io.IOException;
import java.util.Random;
import javax.swing.JFrame;

public class Game extends JFrame {
  public static GameLoop gameLoop;
  public static MiddlewareProxy proxy;
  public static int playerMe;

  public Game() {
    gameLoop = new GameLoop();
    playerMe = new Random().nextInt(1000000000);
    add(gameLoop);
    setResizable(false);
    pack();
    setTitle("Middleware Game Client");
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  public static void main(String[] args) throws IOException, MiddlewareException {
    EventQueue.invokeLater(
        () -> {
          JFrame ex = new Game();
          ex.setVisible(true);
        });

    proxy = new MiddlewareProxy("localhost", 8080, 1);
    proxy.createTopic(GameConstants.SIGN_IN_TOPIC, SignIn.parser());
    proxy.createTopic(GameConstants.MOVE_TOPIC, Move.parser()).onMessage(new MoveCallable());
    proxy.createTopic(GameConstants.FIRE_TOPIC, Fire.parser()).onMessage(new FireCallable());
    proxy
        .createTopic(GameConstants.GAME_STATUS_TOPIC, GameStatus.parser())
        .onMessage(new GameStatusCallable());
    proxy.createTopic(GameConstants.USER_TOPIC, User.parser()).onMessage(new UserCallable());
    proxy.start();
    proxy.getTopic(GameConstants.SIGN_IN_TOPIC).produce(SignIn.newBuilder().setPlayer(playerMe).build());
  }
}
