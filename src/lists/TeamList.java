package lists;

import items.NOC;
import items.Team;

import java.util.ArrayList;

public class TeamList {
    ArrayList<Team> teams = new ArrayList<>();

    public Team addWhenMissing(Team team) {
        int index = teams.indexOf(team);
        if (index == -1) {
            teams.add(team);
            return team;
        } else {
            return teams.get(index);
        }
    }

    public void add(Team team) {
        teams.add(team);
    }
}
