import items.*;
import lists.*;

import java.io.InputStream;
import java.util.Scanner;

public class FileLoader {
    public static void loadData() {
        InputStream datafile = FileLoader.class.getResourceAsStream("smol.db");
        Scanner reader = new Scanner(datafile);
        reader.nextLine();

        AthleteList athleteList = new AthleteList();
        EventList eventList = new EventList();
        NOCList nocList = new NOCList();
        OlympicGameList olympicGameList = new OlympicGameList();
        SportList sportList = new SportList();
        TeamList teamList = new TeamList();
        MedalList medalList = new MedalList();

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
            olympicGame = olympicGameList.addWhenMissing(olympicGame);

            NOC nocObject = new NOC(noc);
            nocObject = nocList.addWhenMissing(nocObject);

            Team teamObject = new Team(team);
            teamObject = teamList.addWhenMissing(teamObject);
            teamObject.setNoc(nocObject);

            Sport sportObject = new Sport(sport);
            sportObject = sportList.addWhenMissing(sportObject);
            olympicGame.addSport(sportObject);

            Event eventObject = new Event(event);
            eventObject = eventList.addWhenMissing(eventObject);
            olympicGame.addEvent(eventObject);
            eventObject.setOlympicGame(olympicGame);
            sportObject.addEvent(eventObject);
            eventObject.setSport(sportObject);

            Athlete athlete = new Athlete(id, name, age, year, gender, height, weight);
            athlete = athleteList.addWhenMissing(athlete);
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
    }
}
