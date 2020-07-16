package items;

import java.util.TreeMap;

import static java.lang.Integer.parseInt;

public class OlympicGame{
    String name;
    int year;
    String season;
    String city;
    TreeMap<String, Event> events = new TreeMap<>();
    TreeMap<String, Team> teams = new TreeMap<>();
    TreeMap<String, Sport> sports = new TreeMap<>();

    public OlympicGame(String game, String year, String season, String city) {
        this.name = game;
        this.year = parseInt(year);
        this.season = season;
        this.city = city;
    }

    public void addEvent(Event event) {
        events.putIfAbsent(event.getName(), event);
    }

    public void addTeam(Team team) {
        teams.putIfAbsent(team.getName(), team);
    }

    public void addSport(Sport sport) {
        sports.putIfAbsent(sport.getName(), sport);
    }

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public String getSeason() {
        return season;
    }

    public String getCity() {
        return city;
    }

    public TreeMap<String, Event> getEvents() {
        return events;
    }

    public TreeMap<String, Team> getTeams() {
        return teams;
    }

    public TreeMap<String, Sport> getSports() {
        return sports;
    }
}
