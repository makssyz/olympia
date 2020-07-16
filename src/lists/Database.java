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

    public Database(TreeMap<Integer, Athlete> athleteList,
                    TreeMap<String, Event> eventList,
                    TreeMap<String, NOC> nocList,
                    TreeMap<String, OlympicGame> olympicGameList,
                    TreeMap<String, Sport> sportList,
                    TreeMap<String, Team> teamList,
                    MedalList medalList) {
        this.athleteList = athleteList;
        this.eventList = eventList;
        this.nocList = nocList;
        this.olympicGameList = olympicGameList;
        this.sportList = sportList;
        this.teamList = teamList;
        this.medalList = medalList;
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
