package lists;

import items.Sport;

import java.util.ArrayList;

public class SportList {
    ArrayList<Sport> sports = new ArrayList<>();

    public void add(Sport sport) {
        sports.add(sport);
    }

    public Sport addWhenMissing(Sport sportObject) {
        int index = sports.indexOf(sportObject);
        if (index == -1) {
            sports.add(sportObject);
            return sportObject;
        } else {
            return sports.get(index);
        }
    }
}
