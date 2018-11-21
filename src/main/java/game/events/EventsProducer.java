package game.events;

import com.caio.middleware.MiddlewareException;
import com.caio.middleware.proto.Fire;
import com.caio.middleware.proto.Move;
import com.caio.middleware.proto.Move.Direction;
import game.Game;
import game.GameConstants;
import game.GameLoop;
import game.entities.events.Event;
import game.entities.events.EventData;
import game.entities.events.EventTypes;
import java.io.IOException;

public class EventsProducer {
  public static void handleMoveUp(int player) throws MiddlewareException, IOException {
    Event event = new Event(EventTypes.MOVE_UP);
    event.data.put(EventData.PLAYER, Integer.toString(player));
    GameLoop.events.add(event);

    // Midleware connection
    if (player == Game.playerMe) {

      Move message = Move.newBuilder().setPlayer(player).setDirection(Direction.UP).build();
      Game.proxy.getTopic(GameConstants.MOVE_TOPIC).produce(message);
    }
  }

  public static void handleMoveDown(int player) throws MiddlewareException, IOException {
    Event event = new Event(EventTypes.MOVE_DOWN);
    event.data.put(EventData.PLAYER, Integer.toString(player));
    GameLoop.events.add(event);

    // Midleware connection
    if (player == Game.playerMe) {

      Move message = Move.newBuilder().setPlayer(player).setDirection(Direction.DOWN).build();
      Game.proxy.getTopic(GameConstants.MOVE_TOPIC).produce(message);
    }
  }

  public static void handleFire(int player) throws MiddlewareException, IOException {
    Event event = new Event(EventTypes.FIRE);
    event.data.put(EventData.PLAYER, Integer.toString(player));
    GameLoop.events.add(event);

    // Midleware connection
    if (player == Game.playerMe) {

      Fire message = Fire.newBuilder().setPlayer(player).build();
      Game.proxy.getTopic(GameConstants.MOVE_TOPIC).produce(message);
    }
  }

  public static void handleToStart() {
    Event event = new Event(EventTypes.TO_START);
    GameLoop.events.add(event);
  }

  public static void handleStarted() {
    Event event = new Event(EventTypes.STARTED);
    GameLoop.events.add(event);
  }

  public static void handleOver(int winner) {
    Event event = new Event(EventTypes.OVER);
    event.data.put(EventData.WINNER, Integer.toString(winner));
    GameLoop.events.add(event);
  }

  public static void handleUser(int id, int lives) {
    Event event = new Event(EventTypes.USER);
    event.data.put(EventData.ID, Integer.toString(id));
    event.data.put(EventData.LIVES, Integer.toString(lives));
    GameLoop.events.add(event);
  }
}
