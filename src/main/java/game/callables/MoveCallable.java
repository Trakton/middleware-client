package game.callables;

import com.caio.middleware.Callable;
import com.caio.middleware.MiddlewareException;
import com.caio.middleware.proto.Move;
import game.events.EventsProducer;
import java.io.IOException;

public class MoveCallable implements Callable<Move> {
  public void process(Move move) {
    if (Move.Direction.UP.equals(move.getDirection())) {
      try {
        EventsProducer.handleMoveUp(move.getPlayer());
      } catch (MiddlewareException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      }
    } else {
      try {
        EventsProducer.handleMoveDown(move.getPlayer());
      } catch (MiddlewareException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
