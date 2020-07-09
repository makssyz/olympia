package lists;

import items.OlympicGame;

import java.util.ArrayList;

public class OlympicGameList {
    ArrayList<OlympicGame> olympicGames = new ArrayList<>();

    public void add(OlympicGame olympicGame) {
        olympicGames.add(olympicGame);
    }

    public OlympicGame addWhenMissing(OlympicGame olympicGame) {
        int index = olympicGames.indexOf(olympicGame);
        if (index == -1) {
            olympicGames.add(olympicGame);
            return olympicGame;
        } else {
            return olympicGames.get(index);
        }
    }

    public ArrayList<OlympicGame> getOlympicGames() {
        return olympicGames;
    }
}
