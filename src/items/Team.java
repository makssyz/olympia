package items;

import java.util.TreeMap;

public class Team{
    private final String name;
    private NOC noc;
    TreeMap<Integer, Athlete> athletes = new TreeMap<>();

    public Team(String team) {
        this.name = team;
    }

    public void addAthlete(Athlete athlete) {
        athletes.putIfAbsent(athlete.getId(), athlete);
    }

    @Override
    public String toString() {
        return name;
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

    public TreeMap<Integer, Athlete> getAthletes() {
        return athletes;
    }
}
