package items;

import lists.ObjectSet;

public class NOC implements Comparable<NOC>{
    final String name;
    final ObjectSet teams = new ObjectSet();

    public NOC(String noc) {
        this.name = noc;
    }

    public void addTeamIfAbsent(Team team) {
        teams.add(team);
    }

    public String getName() {
        return name;
    }

    public ObjectSet getTeams() {
        return teams;
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) return true;
        if (object == null || object.getClass() != this.getClass()) return false;
        return this.name.equals(((NOC) object).name);
    }

    @Override
    public int compareTo(NOC noc) {
        return this.name.compareTo(noc.name);
    }

    @Override
    public String toString() {
        return name;
    }
}
