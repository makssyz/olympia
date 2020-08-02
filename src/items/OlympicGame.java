package items;

import lists.ObjectSet;

import static java.lang.Integer.parseInt;

public class OlympicGame {
    final String name;
    final int year;
    final String season;
    final String city;
    final ObjectSet events = new ObjectSet();
    final ObjectSet teams = new ObjectSet();
    final ObjectSet sports = new ObjectSet();

    public OlympicGame(String game, String year, String season, String city) {
        this.name = game;
        this.year = parseInt(year);
        this.season = season;
        this.city = city;
    }

    public void addEventIfAbsent(Event event) {
        events.add(event);
    }

    public void addTeamIfAbsent(Team team) {
        teams.add(team);
    }

    public void addSportIfAbsent(Sport sport) {
        sports.add(sport);
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) return true;
        if (object == null || object.getClass() != this.getClass()) return false;
        return this.name.equals(((OlympicGame) object).name);
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

    public ObjectSet getEvents() {
        return events;
    }

    public ObjectSet getTeams() {
        return teams;
    }

    public ObjectSet getSports() {
        return sports;
    }
}
