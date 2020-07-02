import java.util.ArrayList;

public class Event implements HasAthletes{
    String event;
    ArrayList<Athlete> athletes = new ArrayList<>();

    Event(String event) {
        this.event = event;
    }

    @Override
    public void addAthlete(Athlete athlete) {
        athletes.add(athlete);
    }
}
