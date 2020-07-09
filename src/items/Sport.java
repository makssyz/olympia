package items;

import lists.EventList;

public class Sport {
    String name;
    EventList events = new EventList();

    public Sport(String sport) {
        this.name = sport;
    }

    public void addEvent(Event event) {
        events.addWhenMissing(event);
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object == null || object.getClass() != this.getClass()) {
            return false;
        }
        Sport sport = (Sport) object;
        return this.name.equals(sport.name);
    }

    @Override
    public String toString() {
        return "Sport{" +
                "name='" + name + '\'' +
                ", events=" + events +
                '}';
    }
}
