package items;

import java.util.TreeMap;

public class Sport {
    String name;
    TreeMap<String, Event> events = new TreeMap<>();

    public Sport(String sport) {
        this.name = sport;
    }

    public void addEvent(Event event) {
        events.putIfAbsent(event.getName(), event);
    }

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    public TreeMap<String, Event> getEvents() {
        return events;
    }
}
