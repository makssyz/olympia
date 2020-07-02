import java.util.ArrayList;

public class OlympicGame implements HasEvents, HasTeams{
    String game;
    int year;
    String season;
    String city;
    ArrayList<Event> events = new ArrayList<>();
    ArrayList<Team> teams = new ArrayList<>();

    OlympicGame(int year, String season, String city) {
        this.game = "" + year + " " + season;
        this.year = year;
        this.season = season;
        this.city = city;
    }

    @Override
    public void addEvent(Event event) {
        this.events.add(event);
    }

    @Override
    public void addTeams(Team team) {
        this.teams.add(team);
    }
}
