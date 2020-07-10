package items;

import lists.EventList;
import lists.SportList;
import lists.TeamList;

import static java.lang.Integer.parseInt;

public class OlympicGame{
    String name;
    int year;
    String season;
    String city;
    EventList events = new EventList();
    TeamList teams = new TeamList();
    SportList sports = new SportList();

    public OlympicGame(String game, String year, String season, String city) {
        this.name = game;
        this.year = parseInt(year);
        this.season = season;
        this.city = city;
    }

    public void addEvent(Event event) {
        events.addWhenMissing(event);
    }

    public void addSport(Sport sport) {
        sports.addWhenMissing(sport); }

    public void addTeam(Team team) {
        teams.addWhenMissing(team); }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object == null || object.getClass() != this.getClass()) {
            return false;
        }
        OlympicGame olympicGame = (OlympicGame) object;
        return this.name.equals(olympicGame.name);
    }

    @Override
    public String toString() {
        return "OlympicGame{" +
                "name='" + name + '\'' +
                ", year=" + year +
                ", season='" + season + '\'' +
                ", city='" + city + '\'' +
                ", events=" + events +
                ", teams=" + teams +
                ", sports=" + sports +
                '}';
    }
}
