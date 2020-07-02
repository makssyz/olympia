public class Athlete {

    private final int id;
    private final String name;
    private final int birthyear;
    private final String gender;
    private final int height;
    private final int weight;

    Athlete(int id, String name, int birthyear, String gender, int height, int weight) {
        this.id = id;
        this.name = name;
        this.birthyear = birthyear;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Athlete{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthyear=" + birthyear +
                ", gender='" + gender + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                '}';
    }
}
