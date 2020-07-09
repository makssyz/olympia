package items;

import lists.MedalList;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class Athlete {

    private final int id;
    private final String name;
    private final int birthyear;
    private final String gender;
    private final double height;
    private final double weight;
    public MedalList medals = new MedalList();

    public Athlete(String id, String name, String age, String year, String gender, String height, String weight) {
        this.id = parseInt(id);
        this.name = name;
        if(age.equals("NA")) {
            this.birthyear = -1;
        } else {
            this.birthyear = parseInt(year) - parseInt(age);
        }
        this.gender = gender;
        if(height.equals("NA")) {
            this.height = -1;
        } else {
            this.height = parseDouble(height);
        }
        if(weight.equals("NA")) {
            this.weight = -1;
        } else {
            this.weight = parseDouble(weight);
        }
    }

    public void addMedal(Medal medal) {
        medals.add(medal);
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
                ", medals=" + medals +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object == null || object.getClass() != this.getClass()) {
            return false;
        }
        Athlete athlete = (Athlete) object;
        return this.id == athlete.id;
    }

    @Override
    public int hashCode() {
        return this.id;
    }

    public int getId() {
        return id;
    }
}
