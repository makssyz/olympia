import java.util.ArrayList;

public class Sport {
    String sport;
    ArrayList<Event> events = new ArrayList<>();

    Sport(String sport) {
        this.sport = sport;
    }

    public void addEvent(Event event) {
        events.add(event);
    }
}
