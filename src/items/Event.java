package items;

import lists.ObjectSet;

public class Event implements Comparable<Event> {
    String name;
    Sport sport;
    OlympicGame olympicGame;
    ObjectSet athletes = new ObjectSet();
    ObjectSet winners = new ObjectSet();

    public Event(String event) {
        this.name = event;
    }

    public void addAthleteIfAbsent(Athlete athlete) {
        athletes.add(athlete);
    }

    public void addWinner(Athlete athlete) {
        winners.add(athlete);
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) return true;
        if (object == null || object.getClass() != this.getClass()) return false;
        return this.name.equals(((Event) object).name);
    }

    @Override
    public int compareTo(Event event) {
        return this.name.compareTo(event.name);
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

    public ObjectSet getAthletes() {
        return athletes;
    }

    public ObjectSet getWinners() {
        return winners;
    }
}
