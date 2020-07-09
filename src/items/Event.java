package items;

import lists.AthleteList;

public class Event {
    String name;
    Sport sport;
    OlympicGame olympicGame;
    AthleteList athletes = new AthleteList();
    AthleteList medalists = new AthleteList();

    public Event(String event) {
        this.name = event;
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object == null || object.getClass() != this.getClass()) {
            return false;
        }
        Event event = (Event) object;
        return this.name.equals(event.name);
    }

    public void addAthlete(Athlete athlete) {
        athletes.addWhenMissing(athlete);
    }

    public void addMedalist(Athlete athlete) {
        medalists.add(athlete);
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public void setOlympicGame(OlympicGame olympicGame) {
        this.olympicGame = olympicGame;
    }

    @Override
    public String toString() {
        return "Event{" +
                "name='" + name + '\'' +
                ", sport=" + sport +
                ", olympicGame=" + olympicGame +
                ", athletes=" + athletes +
                ", medalists=" + medalists +
                '}';
    }
}
