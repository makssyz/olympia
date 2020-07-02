public class Main {

    public static void main(String[] args) {
        Athlete athlete = new Athlete(1, "Jimmy Bob",
                1975, "M", 178, 81);

        System.out.println(athlete);

        Team team = new Team("Germany");

        team.addAthlete(athlete);

        System.out.println(team);
    }

    private static void loadData() {

    }
}
