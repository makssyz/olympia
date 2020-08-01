package lists;

import items.*;

import java.util.TreeMap;

public class Database {
    TreeMap<Integer, Athlete> athleteMap;
    TreeMap<String, Event> eventMap;
    TreeMap<String, NOC> nocMap;
    TreeMap<String, OlympicGame> olympicGameMap;
    TreeMap<String, Sport> sportMap;
    TreeMap<String, Team> teamMap;
    MedalList medalList;

    public Database() {
        athleteMap = new TreeMap<>();
        eventMap = new TreeMap<>();
        nocMap = new TreeMap<>();
        olympicGameMap = new TreeMap<>();
        sportMap = new TreeMap<>();
        teamMap = new TreeMap<>();
        medalList = new MedalList();
    }

    public void addEntry(Entry entry) {
        entry.addToDatabase(this);
    }

    public TreeMap<Integer, Athlete> getAthleteMap() {
        return athleteMap;
    }

    public TreeMap<String, Event> getEventMap() {
        return eventMap;
    }

    public TreeMap<String, NOC> getNocMap() {
        return nocMap;
    }

    public TreeMap<String, OlympicGame> getOlympicGameMap() {
        return olympicGameMap;
    }

    public TreeMap<String, Sport> getSportMap() {
        return sportMap;
    }

    public TreeMap<String, Team> getTeamMap() {
        return teamMap;
    }

    public MedalList getMedalList() {
        return medalList;
    }
}
