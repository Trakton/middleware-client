package game.callables;

import com.caio.middleware.Callable;
import com.caio.middleware.MiddlewareException;
import com.caio.middleware.proto.Fire;
import game.events.EventsProducer;
import java.io.IOException;

public class FireCallable implements Callable<Fire> {
  public void process(Fire fire) {
    try {
      EventsProducer.handleFire(fire.getPlayer());
    } catch (MiddlewareException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
