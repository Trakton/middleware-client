package game.callables;

import com.caio.middleware.Callable;
import com.caio.middleware.proto.GameStatus;
import com.caio.middleware.proto.GameStatus.State;
import game.Game;
import game.events.EventsProducer;

public class GameStatusCallable implements Callable<GameStatus> {
  public void process(GameStatus gameStatus) {
    if (State.TO_START.equals(gameStatus.getState())) {
      int playerone = Game.playerMe;
      int playertwo = gameStatus.getPlayerOne();
      if(playerone == playertwo) playertwo = gameStatus.getPlayerTwo();

      Game.gameLoop.start(playerone, playertwo);
      EventsProducer.handleToStart();
    } else if (State.STARTED.equals(gameStatus.getState())) {
      EventsProducer.handleStarted();
    } else if (State.OVER.equals(gameStatus.getState())) {
      EventsProducer.handleOver(gameStatus.getWinner());
    }
  }
}
