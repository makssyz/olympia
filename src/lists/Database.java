package lists;

import items.*;

import java.util.TreeMap;

public class Database {
    final TreeMap<Integer, Athlete> athleteMap;
    final TreeMap<String, Event> eventMap;
    final TreeMap<String, NOC> nocMap;
    final TreeMap<String, OlympicGame> olympicGameMap;
    final TreeMap<String, Sport> sportMap;
    final TreeMap<String, Team> teamMap;
    final MedalList medalList;

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
