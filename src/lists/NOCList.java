package lists;

import items.NOC;
import items.OlympicGame;

import java.util.ArrayList;

public class NOCList {
    ArrayList<NOC> committees = new ArrayList<>();

    public NOC addWhenMissing(NOC noc) {
        int index = committees.indexOf(noc);
        if (index == -1) {
            committees.add(noc);
            return noc;
        } else {
            return committees.get(index);
        }
    }

    public void add(NOC noc) {
        committees.add(noc);
    }
}
