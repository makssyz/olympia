package items;

import lists.TeamList;

public class NOC{
    String name;
    TeamList teams = new TeamList();

    public NOC(String noc) {
        this.name = noc;
    }

    public void addTeam(Team team) {
        teams.addWhenMissing(team);
        team.setNoc(this);
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object == null || object.getClass() != this.getClass()) {
            return false;
        }
        NOC noc = (NOC) object;
        return this.name.equals(noc.name);
    }

    @Override
    public String toString() {
        return "NOC{" +
                "name='" + name + '\'' +
                ", teams=" + teams +
                '}';
    }
}
