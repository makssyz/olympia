import items.*;
import lists.*;

import java.io.InputStream;
import java.util.Scanner;
import java.util.TreeMap;

public class FileLoader {
    public static MasterList loadData() {
        InputStream datafile = FileLoader.class.getResourceAsStream("olympic.db");
        Scanner reader = new Scanner(datafile);
        reader.nextLine();

        TreeMap<Integer, Athlete> athleteList = new TreeMap<>();
        TreeMap<String, Event> eventList = new TreeMap<>();
        TreeMap<String, NOC> nocList = new TreeMap<>();
        TreeMap<String, OlympicGame> olympicGameList = new TreeMap<>();
        TreeMap<String, Sport> sportList = new TreeMap<>();
        TreeMap<String, Team> teamList = new TreeMap<>();
        MedalList medalList = new MedalList();
        MasterList masterList = new MasterList(athleteList, eventList, nocList, olympicGameList, sportList, teamList, medalList);
        Object value;

        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            String[] lineArray = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

            String id, name, gender, age, height, weight, team, noc, game, year, season, city, sport, event, medal;

            for(int i = 0; i < lineArray.length; i++) {
                lineArray[i] = lineArray[i].replaceAll("(?=\")\"|\"", "");
            }
            id = lineArray[0];
            name = lineArray[1];
            gender = lineArray[2];
            age = lineArray[3];
            height = lineArray[4];
            weight = lineArray[5];
            team = lineArray[6];
            noc = lineArray[7];
            game = lineArray[8];
            year = lineArray[9];
            season = lineArray[10];
            city = lineArray[11];
            sport = lineArray[12];
            event = lineArray[13];
            medal = lineArray[14];

            OlympicGame olympicGame = new OlympicGame(game, year, season, city);
            value = olympicGameList.putIfAbsent(olympicGame.getName(), olympicGame);
            if (value != null) {
                olympicGame = (OlympicGame) value;
            }

            NOC nocObject = new NOC(noc);
            value = nocList.putIfAbsent(nocObject.getName(), nocObject);
            if (value != null) {
                nocObject = (NOC) value;
            }

            Team teamObject = new Team(team);
            value = teamList.putIfAbsent(teamObject.getName(), teamObject);
            if (value != null) {
                teamObject = (Team) value;
            }
            teamObject.setNoc(nocObject);
            nocObject.addTeam(teamObject);
            olympicGame.addTeam(teamObject);

            Sport sportObject = new Sport(sport);
            value = sportList.putIfAbsent(sportObject.getName(), sportObject);
            if (value != null) {
                sportObject = (Sport) value;
            }
            olympicGame.addSport(sportObject);

            Event eventObject = new Event(event);
            value = eventList.putIfAbsent(eventObject.getName(), eventObject);
            if (value != null) {
                eventObject = (Event) value;
            }
            olympicGame.addEvent(eventObject);
            eventObject.setOlympicGame(olympicGame);
            sportObject.addEvent(eventObject);
            eventObject.setSport(sportObject);

            Athlete athlete = new Athlete(id, name, age, year, gender, height, weight);
            athleteList.putIfAbsent(athlete.getId(), athlete);
            value = athleteList.putIfAbsent(athlete.getId(), athlete);
            if (value != null) {
                athlete = (Athlete) value;
            }
            eventObject.addAthlete(athlete);
            teamObject.addAthlete(athlete);

            if (!medal.equals("NA")) {
                Medal medalObject = new Medal(eventObject, medal, athlete);
                medalList.add(medalObject);
                eventObject.addMedalist(athlete);
                athlete.addMedal(medalObject);
            }
        }
        reader.close();
        return masterList;
    }

//    private static Object putReference(TreeMap<>) {
//        value = nocList.putIfAbsent(nocObject.getName(), nocObject);
//        if (value != null) {
//            nocObject = (NOC) value;
//        }
//    }
}
