package tools;

import lists.Database;
import lists.Form;

public class Serializer {
    Form form;
    Database database;

    public Serializer(Form form) {
        this.form = form;
        this.database = form.getDatabase();
    }

    public String createString() {

        String id;
        if (!form.isExists()) {
            try {
                id = Integer.toString(database.getAthleteMap().lastEntry().getValue().getId() + 1);
            } catch (NullPointerException e) {
                id = "1";
            }
        } else {
            id = form.getId();
        }

        String age;
        if (!form.getBirthyear().equals("NA")) {
            age = Integer.toString(Integer.parseInt(form.getYear())
                    - Integer.parseInt(form.getBirthyear()));
        } else {
            age = "NA";
        }

        String game = form.getYear() + " " + form.getSeason();

        String attributesAsString = quotes(id) + ","
                + quotes(form.getName()) + ","
                + quotes(form.getGender()) + ","
                + age + ","
                + form.getHeight() + ","
                + form.getWeight() + ","
                + quotes(form.getTeam()) + ","
                + quotes(form.getNoc()) + ","
                + quotes(game) + ","
                + form.getYear() + ","
                + quotes(form.getSeason()) + ","
                + quotes(form.getCity()) + ","
                + quotes(form.getSport()) + ","
                + quotes(form.getEvent()) + ",";
        if (!(form.getMedal()).equals("NA")) {
            attributesAsString += (quotes(form.getMedal()));
        } else {
            attributesAsString += (form.getMedal());
        }
        return attributesAsString;
    }

    private String quotes(String string) {
        return "\"" + string + "\"";
    }
}
