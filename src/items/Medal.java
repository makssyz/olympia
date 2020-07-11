package items;

public class Medal {
    Event event;
    Athlete medalist;
    String metal;

    public Medal(Event event, String metal, Athlete medalist) {
        this.event = event;
        this.medalist = medalist;
        this.metal = metal;
    }

    @Override
    public String toString() {
        return "Medal{event=" + event + ", medalist=" + medalist.getName() + ", metal='" + metal + "'}";
    }
}
