package items;

public class Medal implements Comparable<Medal> {
    final Event event;
    final Athlete medalist;
    final String metal;

    public Medal(Event event, String metal, Athlete medalist) {
        this.event = event;
        this.medalist = medalist;
        this.metal = metal;
    }

    @Override
    public String toString() {
        return "" + metal + " Medal ("+ event.getOlympicGame().getYear() + ") for " + event.getSport() + "\n";
    }

    @Override
    public int compareTo(Medal medal) {

        return this.toString().compareTo(medal.toString());
    }
}
