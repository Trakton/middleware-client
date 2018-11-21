package game.callables;

import com.caio.middleware.Callable;
import com.caio.middleware.proto.User;
import game.events.EventsProducer;

public class UserCallable implements Callable<User> {
  public void process(User user) {
    EventsProducer.handleUser(user.getPlayer(), user.getLives());
  }
}
