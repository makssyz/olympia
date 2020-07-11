package lists;

import items.*;

import java.util.TreeMap;

public class MasterList {
    TreeMap<Integer, Athlete> athleteList;
    TreeMap<String, Event> eventList;
    TreeMap<String, NOC> nocList;
    TreeMap<String, OlympicGame> olympicGameList;
    TreeMap<String, Sport> sportList;
    TreeMap<String, Team> teamList;
    MedalList medalList;

    public MasterList(TreeMap<Integer, Athlete> athleteList,
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
}
