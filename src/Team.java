import java.util.ArrayList;

public class Team implements HasAthletes{
    private final String team;
    ArrayList<Athlete> athletes = new ArrayList<>();

    Team(String team) {
        this.team = team;
    }

    @Override
    public void addAthlete(Athlete athlete) {
        athletes.add(athlete);
    }

    @Override
    public String toString() {
        return "Team{" +
                "team='" + team + '\'' +
                ", athletes=" + athletes +
                '}';
    }
}
