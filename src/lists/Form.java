package lists;

import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;

public class Form {
    final Database database;

    final TextField nameInputField;
    final TextField birthyearInputField;
    final TextField heightInputField;
    final TextField weightInputField;
    final TextField teamInputField;
    final TextField nocInputField;
    final TextField yearInputField;
    final TextField cityInputField;
    final TextField sportInputField;
    final TextField eventInputField;
    final TextField idInputField;
    final ChoiceBox<String> genderInputField;
    final ChoiceBox<String> seasonInputField;
    final ChoiceBox<String> medalInputField;
    final CheckBox existsCheckbox;

    final String name;
    final String gender;
    String birthyear;
    String height;
    String weight;
    final String team;
    final String noc;
    final String year;
    final String season;
    final String city;
    final String sport;
    final String event;
    final String medal;
    final String id;
    final boolean exists;

    public Form(Control[] input, Database database) {
        this.database = database;
        nameInputField = (TextField) input[0];
        genderInputField = (ChoiceBox<String>) input[1];
        birthyearInputField = (TextField) input[2];
        heightInputField = (TextField) input[3];
        weightInputField = (TextField) input[4];
        teamInputField = (TextField) input[5];
        nocInputField = (TextField) input[6];
        yearInputField = (TextField) input[7];
        seasonInputField = (ChoiceBox<String>) input[8];
        cityInputField = (TextField) input[9];
        sportInputField = (TextField) input[10];
        eventInputField = (TextField) input[11];
        medalInputField = (ChoiceBox<String>) input[12];
        idInputField = (TextField) input[13];
        existsCheckbox = (CheckBox) input[14];

        name = nameInputField.getText();
        gender = genderInputField.getValue();
        birthyear = birthyearInputField.getText();
        height = heightInputField.getText();
        weight = weightInputField.getText();
        team = teamInputField.getText();
        noc = nocInputField.getText();
        year = yearInputField.getText();
        season = seasonInputField.getValue();
        city = cityInputField.getText();
        sport = sportInputField.getText();
        event = eventInputField.getText();
        medal = medalInputField.getValue();
        id = idInputField.getText();
        exists = existsCheckbox.isSelected();
    }

    public Database getDatabase() {
        return database;
    }

    public void setBirthyear(String birthyear) {
        this.birthyear = birthyear;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public TextField getNameInputField() {
        return nameInputField;
    }

    public TextField getBirthyearInputField() {
        return birthyearInputField;
    }

    public TextField getHeightInputField() {
        return heightInputField;
    }

    public TextField getWeightInputField() {
        return weightInputField;
    }

    public TextField getTeamInputField() {
        return teamInputField;
    }

    public TextField getNocInputField() {
        return nocInputField;
    }

    public TextField getYearInputField() {
        return yearInputField;
    }

    public TextField getCityInputField() {
        return cityInputField;
    }

    public TextField getSportInputField() {
        return sportInputField;
    }

    public TextField getEventInputField() {
        return eventInputField;
    }

    public TextField getIdInputField() {
        return idInputField;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getBirthyear() {
        return birthyear;
    }

    public String getHeight() {
        return height;
    }

    public String getWeight() {
        return weight;
    }

    public String getTeam() {
        return team;
    }

    public String getNoc() {
        return noc;
    }

    public String getYear() {
        return year;
    }

    public String getSeason() {
        return season;
    }

    public String getCity() {
        return city;
    }

    public String getSport() {
        return sport;
    }

    public String getEvent() {
        return event;
    }

    public String getMedal() {
        return medal;
    }

    public String getId() {
        return id;
    }

    public boolean isExists() {
        return exists;
    }
}
