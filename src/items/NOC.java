package items;

import java.util.TreeMap;

public class NOC{
    String name;
    TreeMap<String, Team> teams = new TreeMap<>();

    public NOC(String noc) {
        this.name = noc;
    }

    public void addTeam(Team team) {
        teams.putIfAbsent(team.getName(), team);
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) return true;
        if (object == null || object.getClass() != this.getClass()) return false;
        return this.name.equals(((NOC) object).name);
    }

    @Override
    public String toString() {
        return "NOC{" +
                "name='" + name + '\'' +
                ", teams=" + teams.size() +
                '}';
    }
}
