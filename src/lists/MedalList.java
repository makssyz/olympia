package lists;

import items.Medal;

import java.util.ArrayList;
import java.util.Collections;

public class MedalList {
    final ArrayList<Medal> medals = new ArrayList<>();

    public void add(Medal medal) {
        medals.add(medal);
        }

    public void sortByMetal() {
        ArrayList<Medal> medalsGold = new ArrayList<>();
        ArrayList<Medal> medalsSilver = new ArrayList<>();
        ArrayList<Medal> medalsBronze = new ArrayList<>();

        for (Medal medal : medals) {
            if (medal.toString().startsWith("Gold")) medalsGold.add(medal);
            else if (medal.toString().startsWith("Silver")) medalsSilver.add(medal);
            else if (medal.toString().startsWith("Bronze")) medalsBronze.add(medal);
        }

        medals.clear();
        Collections.sort(medalsGold);
        medals.addAll(medalsGold);
        Collections.sort(medalsSilver);
        medals.addAll(medalsSilver);
        Collections.sort(medalsBronze);
        medals.addAll(medalsBronze);
    }

    @Override
    public String toString() {
        String string = "";
        for (Medal medal : medals) {
            string = string.concat(medal.toString());
        }

        return string;
    }
}
