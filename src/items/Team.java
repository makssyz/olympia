package items;

import lists.ObjectSet;

public class Team implements Comparable<Team> {
    private final String name;
    private NOC noc;
    final ObjectSet athletes = new ObjectSet();

    public Team(String team) {
        this.name = team;
    }

    public void addAthleteIfAbsent(Athlete athlete) {
        athletes.add(athlete);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) return true;
        if (object == null || object.getClass() != this.getClass()) return false;
        return this.name.equals(((Team) object).name);
    }

    @Override
    public int compareTo(Team team) {
        return this.name.compareTo(team.name);
    }

    public String getName() {
        return name;
    }

    public NOC getNoc() {
        return noc;
    }

    public void setNoc(NOC noc) {
        this.noc = noc;
    }

    public ObjectSet getAthletes() {
        return athletes;
    }
}
