package view;

import exceptions.InvalidInputError;
import items.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lists.Database;
import lists.Entry;
import lists.Form;
import lists.MedalList;
import tools.FileHandler;
import tools.FormValidator;
import tools.Serializer;
import tools.TableFilter;

import java.net.URL;
import java.util.*;

public class Controller implements Initializable {
    Database database = new Database();

    /* Welcome Tab */
    @FXML private TextField filenameInputField;
    FileHandler fileHandler;

    /* Athlete Table View */
    @FXML private TableView<Object> athleteTableView;
    @FXML private TableColumn<Athlete, Integer> athleteIdColumn;
    @FXML private TableColumn<Athlete, String> athleteNameColumn;
    @FXML private TableColumn<Athlete, Integer> athleteBirthyearColumn;
    @FXML private TableColumn<Athlete, String> athleteGenderColumn;
    @FXML private TableColumn<Athlete, Integer> athleteHeightColumn;
    @FXML private TableColumn<Athlete, Integer> athleteWeightColumn;
    @FXML private TableColumn<Athlete, MedalList> athleteMedalsColumn;
    @FXML private TextField athleteSearchField;
    @FXML private ChoiceBox<String> athleteSearchCategory;
    final ObservableList<Object> athleteList = FXCollections.observableArrayList();

    /* Team Table View */
    @FXML private TableView<Object> teamTableView;
    @FXML private TableColumn<Team, String> teamNocColumn;
    @FXML private TableColumn<Team, String> teamNameColumn;
    @FXML private TableColumn<Team, String> teamAthletesColumn;
    @FXML private TextField teamSearchField;
    @FXML private ChoiceBox<String> teamSearchCategory;
    final ObservableList<Object> teamList = FXCollections.observableArrayList();

    /* Sport Table View */
    @FXML private TableView<Object> sportTableView;
    @FXML private TableColumn<Sport, String> sportNameColumn;
    @FXML private TableColumn<Sport, String> sportEventsColumn;
    @FXML private TextField sportSearchField;
    @FXML private ChoiceBox<String> sportSearchCategory;
    final ObservableList<Object> sportList = FXCollections.observableArrayList();

    /* Event Table View */
    @FXML private TableView<Object> eventTableView;
    @FXML private TableColumn<Event, String> eventNameColumn;
    @FXML private TableColumn<Event, String> eventSportColumn;
    @FXML private TableColumn<Event, String> eventGameColumn;
    @FXML private TableColumn<Event, String> eventMedalistsColumn;
    @FXML private TableColumn<Event, String> eventAthletesColumn;
    @FXML private TextField eventSearchField;
    @FXML private ChoiceBox<String> eventSearchCategory;
    final ObservableList<Object> eventList = FXCollections.observableArrayList();

    /* Olympic Game Table View */
    @FXML private TableView<Object> gameTableView;
    @FXML private TableColumn<OlympicGame, Integer> gameYearColumn;
    @FXML private TableColumn<OlympicGame, String> gameSeasonColumn;
    @FXML private TableColumn<OlympicGame, String> gameCityColumn;
    @FXML private TableColumn<OlympicGame, String> gameTeamColumn;
    @FXML private TableColumn<OlympicGame, String> gameEventColumn;
    @FXML private TableColumn<OlympicGame, String> gameSportColumn;
    @FXML private TextField gameSearchField;
    @FXML private ChoiceBox<String> gameSearchCategory;
    final ObservableList<Object> gameList = FXCollections.observableArrayList();

    /* Create New Entry */
    @FXML private TextField nameInputField;
    @FXML private ChoiceBox<String> genderInputField;
    @FXML private TextField birthyearInputField;
    @FXML private TextField heightInputField;
    @FXML private TextField weightInputField;
    @FXML private TextField teamInputField;
    @FXML private TextField nocInputField;
    @FXML private TextField yearInputField;
    @FXML private ChoiceBox<String> seasonInputField;
    @FXML private TextField cityInputField;
    @FXML private TextField sportInputField;
    @FXML private TextField eventInputField;
    @FXML private ChoiceBox<String> medalInputField;
    @FXML private TextField idInputField;
    @FXML private CheckBox existsCheckbox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buildView();
    }

    public void reloadData() {
        fileHandler = new FileHandler(filenameInputField.getText());
        database = fileHandler.loadData();
        buildView();
    }

    public void buildView() {
        buildAthleteTable();
        buildTeamTable();
        buildSportTable();
        buildEventTable();
        buildOlympicGameTable();
        buildFrom();
    }

    public void createNewEntry() {

        Control[] input = {nameInputField, genderInputField, birthyearInputField, heightInputField,
                weightInputField, teamInputField, nocInputField, yearInputField, seasonInputField,
                cityInputField, sportInputField, eventInputField, medalInputField, idInputField, existsCheckbox};

        Form form = new Form(input, database);

        try {
            FormValidator.validateInput(form);
        } catch (InvalidInputError error) {
            Alert invalid = new Alert(Alert.AlertType.WARNING);
            invalid.setHeaderText("Invalid Input");
            invalid.setContentText(error.getMessage());
            invalid.show();
            return;
        }

        String entryLine = Serializer.createString(form);
        Entry entry = new Entry(entryLine);

        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setHeaderText("Are you sure?");
        confirmation.setContentText("The following will be permanently added to the database:\n" + entry.toString());

        Optional<ButtonType> result = confirmation.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK){
            System.out.println("Entry was confirmed!");
            database.addEntry(entry);
            fileHandler.addToFile(entryLine);
            clearInput();
            buildView();
        }
    }

    private void buildAthleteTable() {
        athleteIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        athleteNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        athleteBirthyearColumn.setCellValueFactory(new PropertyValueFactory<>("birthyear"));
        athleteGenderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        athleteHeightColumn.setCellValueFactory(new PropertyValueFactory<>("height"));
        athleteWeightColumn.setCellValueFactory(new PropertyValueFactory<>("weight"));
        athleteMedalsColumn.setCellValueFactory(new PropertyValueFactory<>("medals"));

        athleteSearchCategory.setItems(FXCollections.observableArrayList("Name", "Medals"));
        athleteSearchCategory.setValue(athleteSearchCategory.getItems().get(0));

        athleteList.setAll(database.getAthleteMap().values());
        athleteTableView.setItems(athleteList);
    }

    private void buildTeamTable() {
        teamNocColumn.setCellValueFactory(new PropertyValueFactory<>("noc"));
        teamNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        teamAthletesColumn.setCellValueFactory(new PropertyValueFactory<>("athletes"));

        teamSearchCategory.setItems(FXCollections.observableArrayList("NOC", "Name", "Athletes"));
        teamSearchCategory.setValue(teamSearchCategory.getItems().get(0));

        teamList.setAll(database.getTeamMap().values());
        teamTableView.setItems(teamList);
    }

    private void buildSportTable() {
        sportNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        sportEventsColumn.setCellValueFactory(new PropertyValueFactory<>("events"));

        sportSearchCategory.setItems(FXCollections.observableArrayList("Name", "Events"));
        sportSearchCategory.setValue(sportSearchCategory.getItems().get(0));

        sportList.setAll(database.getSportMap().values());
        sportTableView.setItems(sportList);
    }

    private void buildEventTable() {
        eventNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        eventSportColumn.setCellValueFactory(new PropertyValueFactory<>("sport"));
        eventGameColumn.setCellValueFactory(new PropertyValueFactory<>("olympicGame"));
        eventMedalistsColumn.setCellValueFactory(new PropertyValueFactory<>("winners"));
        eventAthletesColumn.setCellValueFactory(new PropertyValueFactory<>("athletes"));

        eventSearchCategory.setItems(FXCollections.observableArrayList("Name", "Sport", "Athletes"));
        eventSearchCategory.setValue(eventSearchCategory.getItems().get(0));

        eventList.setAll(database.getEventMap().values());
        eventTableView.setItems(eventList);
    }

    private void buildOlympicGameTable() {
        gameYearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
        gameSeasonColumn.setCellValueFactory(new PropertyValueFactory<>("season"));
        gameCityColumn.setCellValueFactory(new PropertyValueFactory<>("city"));
        gameTeamColumn.setCellValueFactory(new PropertyValueFactory<>("teams"));
        gameEventColumn.setCellValueFactory(new PropertyValueFactory<>("events"));
        gameSportColumn.setCellValueFactory(new PropertyValueFactory<>("sports"));

        gameSearchCategory.setItems(FXCollections.observableArrayList("City", "Teams", "Events", "Sports"));
        gameSearchCategory.setValue(gameSearchCategory.getItems().get(0));

        gameList.setAll(database.getOlympicGameMap().values());
        gameTableView.setItems(gameList);
    }

    private void buildFrom() {
        genderInputField.setItems(FXCollections.observableArrayList("M", "F"));
        genderInputField.setValue(genderInputField.getItems().get(0));
        seasonInputField.setItems(FXCollections.observableArrayList("Summer", "Winter"));
        seasonInputField.setValue(seasonInputField.getItems().get(0));
        medalInputField.setItems(FXCollections.observableArrayList("NA", "Gold", "Silver", "Bronze"));
        medalInputField.setValue(medalInputField.getItems().get(0));
    }

    public void athleteSearch() {
        TableFilter.search(athleteList, athleteSearchField, athleteSearchCategory, athleteTableView);
    }

    public void teamSearch() {
        TableFilter.search(teamList, teamSearchField, teamSearchCategory, teamTableView);
    }

    public void sportSearch() {
        TableFilter.search(sportList, sportSearchField, sportSearchCategory, sportTableView);
    }

    public void eventSearch() {
        TableFilter.search(eventList, eventSearchField, eventSearchCategory, eventTableView);
    }

    public void gameSearch() {
        TableFilter.search(gameList, gameSearchField, gameSearchCategory, gameTableView);
    }

    public void enableIdField() {
        idInputField.setDisable(!idInputField.isDisable());
    }

    private void clearInput() {
        nameInputField.clear();
        birthyearInputField.clear();
        heightInputField.clear();
        weightInputField.clear();
        teamInputField.clear();
        nocInputField.clear();
        yearInputField.clear();
        cityInputField.clear();
        sportInputField.clear();
        eventInputField.clear();
        idInputField.clear();
        existsCheckbox.setSelected(false);
    }

}
