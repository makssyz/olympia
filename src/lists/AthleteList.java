package lists;

import items.Athlete;

import java.util.ArrayList;

public class AthleteList {
    ArrayList<Athlete> athletes = new ArrayList<>();

    public void add(Athlete athlete) {
        athletes.add(athlete);
    }


    public Athlete addWhenMissing(Athlete athlete) {
        int index = athletes.indexOf(athlete);
        if (index == -1) {
            athletes.add(athlete);
            return athlete;
        } else {
            return athletes.get(index);
        }
    }

    @Override
    public String toString() {
        return "AthleteList{" +
                "athletes=" + athletes +
                "\n}";
    }
}
