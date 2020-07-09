package items;

import lists.AthleteList;

public class Team{
    private final String name;
    private NOC noc;
    AthleteList athletes = new AthleteList();

    public Team(String team) {
        this.name = team;
    }

    public void addAthlete(Athlete athlete) {
        athletes.addWhenMissing(athlete);
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object == null || object.getClass() != this.getClass()) {
            return false;
        }
        Team team = (Team) object;
        return this.name.equals(team.name);
    }

    public void setNoc(NOC noc) {
        this.noc = noc;
    }

    @Override
    public String toString() {
        return "Team{" +
                "name='" + name + '\'' +
                ", noc=" + noc +
                ", athletes=" + athletes +
                '}';
    }
}
