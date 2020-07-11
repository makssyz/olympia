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

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Sport{" +
                "name='" + name + '\'' +
                ", events=" + events.size() +
                '}';
    }
}
