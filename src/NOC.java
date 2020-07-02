import java.util.ArrayList;

public class NOC implements HasTeams{
    String noc;
    ArrayList<Team> teams = new ArrayList<>();

    NOC(String noc) {
        this.noc = noc;
    }

    @Override
    public void addTeams(Team team) {
        teams.add(team);
    }
}
