package lists;

import items.*;

public class Entry {

    private OlympicGame gameObject;
    private NOC nocObject;
    private Team teamObject;
    private Sport sportObject;
    private Event eventObject;
    private Athlete athleteObject;
    private Medal medalObject;

    private final String id, name, gender, age, height, weight, team, noc, game, year, season, city, sport, event, medal;

    public Entry(String line) {
        String[] lineArray = lineToArray(line);

        id =lineArray[0];
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

        gameObject = new OlympicGame(game, year, season, city);

        nocObject = new NOC(noc);

        teamObject = new Team(team);
        teamObject.setNoc(nocObject);

        sportObject = new Sport(sport);

        eventObject = new Event(event);

        eventObject.setOlympicGame(gameObject);
        eventObject.setSport(sportObject);

        athleteObject = new Athlete(id, name, age, year, gender, height, weight);

        medalObject = new Medal(eventObject, medal, athleteObject);
    }

    private String[] lineToArray(String line) {
        String[] lineArray = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

        for(int i = 0; i < lineArray.length; i++) {
            lineArray[i] = lineArray[i].replaceAll("(?=\")\"|\"", "");
        }
        return lineArray;
    }

    @Override
    public String toString() {
        return "ID:\t\t\t " + id + "\nName:\t\t " + name + "\nGender:\t " + gender + "\nAge:\t\t " + age
                + "\nHeight:\t\t " + height + "\nWeight:\t " + weight + "\nTeam:\t\t " + team + "\nNOC:\t\t " + noc
                + "\nGame:\t\t " + game + "\nYear:\t\t " + year + "\nSeason:\t " + season + "\nCity:\t\t " + city
                + "\nSport:\t\t " + sport + "\nEvent:\t\t " + event + "\nMedal:\t\t " + medal;
    }

    public void addToDatabase(Database database) {
        Object reference;
        boolean hasMedal = !medal.equals("NA");

        reference = database.getAthleteMap().putIfAbsent(Integer.parseInt(id), athleteObject);
        if (reference == null) reference = athleteObject;
        if (hasMedal) {
            ((Athlete) reference).addMedal(medalObject);
            ((Athlete) reference).getMedals().sortByMetal();
        }

        reference = database.getTeamMap().putIfAbsent(team, teamObject);
        if (reference == null) reference = teamObject;
        ((Team) reference).addAthleteIfAbsent(athleteObject);

        reference = database.getNocMap().putIfAbsent(noc, nocObject);
        if (reference == null) reference = nocObject;
        ((NOC) reference).addTeamIfAbsent(teamObject);

        reference = database.getOlympicGameMap().putIfAbsent(game, gameObject);
        if (reference == null) reference = gameObject;
        ((OlympicGame) reference).addTeamIfAbsent(teamObject);
        ((OlympicGame) reference).addSportIfAbsent(sportObject);
        ((OlympicGame) reference).addEventIfAbsent(eventObject);

        reference = database.getEventMap().putIfAbsent(event, eventObject);
        if (reference == null) reference = eventObject;
        ((Event) reference).addAthleteIfAbsent(athleteObject);
        if (hasMedal) {
            ((Event) reference).addWinner(athleteObject);
        }

        reference = database.getSportMap().putIfAbsent(sport, sportObject);
        if (reference == null) reference = sportObject;
        ((Sport) reference).addEvent(eventObject);

    }

    public OlympicGame getGameObject() {
        return gameObject;
    }

    public void setGameObject(OlympicGame gameObject) {
        this.gameObject = gameObject;
    }

    public NOC getNocObject() {
        return nocObject;
    }

    public void setNocObject(NOC nocObject) {
        this.nocObject = nocObject;
    }

    public Team getTeamObject() {
        return teamObject;
    }

    public void setTeamObject(Team teamObject) {
        this.teamObject = teamObject;
    }

    public Sport getSportObject() {
        return sportObject;
    }

    public void setSportObject(Sport sportObject) {
        this.sportObject = sportObject;
    }

    public Event getEventObject() {
        return eventObject;
    }

    public void setEventObject(Event eventObject) {
        this.eventObject = eventObject;
    }

    public Athlete getAthleteObject() {
        return athleteObject;
    }

    public void setAthleteObject(Athlete athleteObject) {
        this.athleteObject = athleteObject;
    }

    public Medal getMedalObject() {
        return medalObject;
    }

    public void setMedalObject(Medal medalObject) {
        this.medalObject = medalObject;
    }
}
