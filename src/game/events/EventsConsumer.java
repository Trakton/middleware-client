package game.events;

import game.GameLoop;
import game.entities.events.Event;
import game.entities.events.EventData;

public class EventsConsumer {
    static public void consume(){
        Event event = GameLoop.events.peek();

        if(event == null) return;

        GameLoop.events.remove();

        switch (event.type){
            case MOVE_UP:
                handleMoveUp(event);
                break;
            case MOVE_DOWN:
                handleMoveDown(event);
                break;
            default:
                break;
        }
    }

    static void handleMoveUp(Event event){
        int player = Integer.parseInt(event.data.get(EventData.PLAYER));
        GameLoop.players[player].moveUp();
    }

    static void handleMoveDown(Event event){
        int player = Integer.parseInt(event.data.get(EventData.PLAYER));
        GameLoop.players[player].moveDown();
    }
}
