package items;

import java.util.TreeMap;

public class Event {
    String name;
    Sport sport;
    OlympicGame olympicGame;
    TreeMap<Integer, Athlete> athletes = new TreeMap<>();
    TreeMap<Integer, Athlete> medalists = new TreeMap<>();

    public Event(String event) {
        this.name = event;
    }

    public void addAthlete(Athlete athlete) {
        athletes.putIfAbsent(athlete.getId(), athlete);
    }

    public void addMedalist(Athlete athlete) {
        medalists.put(athlete.getId(), athlete);
    }

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public OlympicGame getOlympicGame() {
        return olympicGame;
    }

    public void setOlympicGame(OlympicGame olympicGame) {
        this.olympicGame = olympicGame;
    }

    public TreeMap<Integer, Athlete> getAthletes() {
        return athletes;
    }

    public TreeMap<Integer, Athlete> getMedalists() {
        return medalists;
    }
}
