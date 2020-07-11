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

    public String getName() {
        return name;
    }

    public void setNoc(NOC noc) {
        this.noc = noc;
    }

    @Override
    public String toString() {
        return "Team{" +
                "name='" + name + '\'' +
                ", noc=" + noc +
                ", athletes=" + athletes.size() +
                '}';
    }
}
