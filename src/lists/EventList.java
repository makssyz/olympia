package lists;

import items.Event;
import items.OlympicGame;

import java.util.ArrayList;

public class EventList {
    ArrayList<Event> events = new ArrayList<>();

    public void add(Event event) {
        events.add(event);
    }

    public Event addWhenMissing(Event event) {
        int index = events.indexOf(event);
        if (index == -1) {
            events.add(event);
            return event;
        } else {
            return events.get(index);
        }
    }
}
