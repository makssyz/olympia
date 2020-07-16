package items;

import lists.MedalList;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class Athlete {

    private final int id;
    private final String name;
    private final String birthyear;
    private final String gender;
    private final String height;
    private final String weight;
    public MedalList medals = new MedalList();

    public Athlete(String id, String name, String age, String year, String gender, String height, String weight) {
        this.id = parseInt(id);
        this.name = name;
        if(age.equals("NA")) {
            this.birthyear = "NA";
        } else {
            int birthyear = parseInt(year) - parseInt(age);
            this.birthyear = "" + birthyear;
        }
        this.gender = gender;
        this.height = height;
        this.weight = weight;
    }

    public void addMedal(Medal medal) {
        medals.add(medal);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBirthyear() {
        return birthyear;
    }

    public String getGender() {
        return gender;
    }

    public String getHeight() {
        return height;
    }

    public String getWeight() {
        return weight;
    }

    public MedalList getMedals() {
        return medals;
    }

    @Override
    public String toString() {
        return "Athlete{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
