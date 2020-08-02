package items;

import lists.ObjectSet;

public class Sport implements Comparable<Sport> {
    final String name;
    final ObjectSet events = new ObjectSet();

    public Sport(String sport) {
        this.name = sport;
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) return true;
        if (object == null || object.getClass() != this.getClass()) return false;
        return this.name.equals(((Sport) object).name);
    }

    @Override
    public int compareTo(Sport sport) {
        return this.name.compareTo(sport.name);
    }

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    public ObjectSet getEvents() {
        return events;
    }
}
