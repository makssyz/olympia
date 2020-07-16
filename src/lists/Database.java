package lists;

import items.*;

import java.io.Serializable;
import java.util.TreeMap;

public class Database implements Serializable {
    TreeMap<Integer, Athlete> athleteList;
    TreeMap<String, Event> eventList;
    TreeMap<String, NOC> nocList;
    TreeMap<String, OlympicGame> olympicGameList;
    TreeMap<String, Sport> sportList;
    TreeMap<String, Team> teamList;
    MedalList medalList;

    public Database() {
        athleteList = new TreeMap<>();
        eventList = new TreeMap<>();
        nocList = new TreeMap<>();
        olympicGameList = new TreeMap<>();
        sportList = new TreeMap<>();
        teamList = new TreeMap<>();
        medalList = new MedalList();
    }

    public void addEntry(Entry entry) {
        Object value;
        value = olympicGameList.putIfAbsent(entry.getGameObject().getName(), entry.getGameObject());
        if (value != null) entry.setGameObject((OlympicGame) value);

        value = nocList.putIfAbsent(entry.getNocObject().getName(), entry.getNocObject());
        if (value != null) entry.setNocObject((NOC) value);

        value = teamList.putIfAbsent(entry.getTeamObject().getName(), entry.getTeamObject());
        if (value != null) entry.setTeamObject((Team) value);

        value = sportList.putIfAbsent(entry.getSportObject().getName(), entry.getSportObject());
        if (value != null) entry.setSportObject((Sport) value);

        value = eventList.putIfAbsent(entry.getEventObject().getName(), entry.getEventObject());
        if (value != null) entry.setEventObject((Event) value);

        value = athleteList.putIfAbsent(entry.getAthleteObject().getId(), entry.getAthleteObject());
        if (value != null) entry.setAthleteObject((Athlete) value);

        if (entry.getMedalObject() != null) medalList.add(entry.getMedalObject());

    }

    public TreeMap<Integer, Athlete> getAthleteList() {
        return athleteList;
    }

    public TreeMap<String, Event> getEventList() {
        return eventList;
    }

    public TreeMap<String, NOC> getNocList() {
        return nocList;
    }

    public TreeMap<String, OlympicGame> getOlympicGameList() {
        return olympicGameList;
    }

    public TreeMap<String, Sport> getSportList() {
        return sportList;
    }

    public TreeMap<String, Team> getTeamList() {
        return teamList;
    }

    public MedalList getMedalList() {
        return medalList;
    }
}
