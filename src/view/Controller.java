package view;

import exceptions.InvalidInputError;
import items.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lists.Database;
import lists.Entry;
import lists.MedalList;
import tools.FileHandler;
import tools.FormValidator;

import java.net.URL;
import java.util.*;

public class Controller implements Initializable {
    Database database = new Database();

    /* Welcome Page */
    @FXML private Button loadDataButton;
    @FXML private ProgressBar loadDataBar;

    /* Athlete Table View */
    @FXML private TableView<Athlete> athleteTableView;
    @FXML private TableColumn<Athlete, Integer> athleteIdColumn;
    @FXML private TableColumn<Athlete, String> athleteNameColumn;
    @FXML private TableColumn<Athlete, Integer> athleteBirthyearColumn;
    @FXML private TableColumn<Athlete, String> athleteGenderColumn;
    @FXML private TableColumn<Athlete, Integer> athleteHeightColumn;
    @FXML private TableColumn<Athlete, Integer> athleteWeightColumn;
    @FXML private TableColumn<Athlete, MedalList> athleteMedalsColumn;
    @FXML private TextField athleteSearchField;
    @FXML private ChoiceBox<String> athleteSearchCategory;
    ObservableList<Athlete> athleteList = FXCollections.observableArrayList();

    /* Team Table View */
    @FXML private TableView<Team> teamTableView;
    @FXML private TableColumn<Team, String> teamNocColumn;
    @FXML private TableColumn<Team, String> teamNameColumn;
    @FXML private TableColumn<Team, String> teamAthletesColumn;
    @FXML private TextField teamSearchField;
    @FXML private ChoiceBox<String> teamSearchCategory;
    ObservableList<Team> teamList = FXCollections.observableArrayList();

    /* Sport Table View */
    @FXML private TableView<Sport> sportTableView;
    @FXML private TableColumn<Sport, String> sportNameColumn;
    @FXML private TableColumn<Sport, String> sportEventsColumn;
    @FXML private TextField sportSearchField;
    @FXML private ChoiceBox<String> sportSearchCategory;
    ObservableList<Sport> sportList = FXCollections.observableArrayList();

    /* Event Table View */
    @FXML private TableView<Event> eventTableView;
    @FXML private TableColumn<Event, String> eventNameColumn;
    @FXML private TableColumn<Event, String> eventSportColumn;
    @FXML private TableColumn<Event, String> eventGameColumn;
    @FXML private TableColumn<Event, String> eventMedalistsColumn;
    @FXML private TableColumn<Event, String> eventAthletesColumn;
    @FXML private TextField eventSearchField;
    @FXML private ChoiceBox<String> eventSearchCategory;
    ObservableList<Event> eventList = FXCollections.observableArrayList();

    /* Olympic Game Table View */
    @FXML private TableView<OlympicGame> gameTableView;
    @FXML private TableColumn<OlympicGame, Integer> gameYearColumn;
    @FXML private TableColumn<OlympicGame, String> gameSeasonColumn;
    @FXML private TableColumn<OlympicGame, String> gameCityColumn;
    @FXML private TableColumn<OlympicGame, String> gameTeamColumn;
    @FXML private TableColumn<OlympicGame, String> gameEventColumn;
    @FXML private TableColumn<OlympicGame, String> gameSportColumn;
    @FXML private TextField gameSearchField;
    @FXML private ChoiceBox<String> gameSearchCategory;
    ObservableList<OlympicGame> gameList = FXCollections.observableArrayList();

    /* Create New Entry */
    @FXML private TextField createName;
    @FXML private ChoiceBox<String> createGender;
    @FXML private TextField createBirthyear;
    @FXML private TextField createHeight;
    @FXML private TextField createWeight;
    @FXML private TextField createTeam;
    @FXML private TextField createNoc;
    @FXML private TextField createYear;
    @FXML private ChoiceBox<String> createSeason;
    @FXML private TextField createCity;
    @FXML private TextField createSport;
    @FXML private TextField createEvent;
    @FXML private ChoiceBox<String> createMedal;
    @FXML private CheckBox createExists;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buildView();
    }

    public void reloadData() {
        loadDataBar.setProgress(0);
        database = FileHandler.loadData();
        loadDataBar.setProgress(50);
        buildView();
        loadDataBar.setProgress(100);
    }

    public void buildView() {
        buildAthleteTable();
        buildTeamTable();
        buildSportTable();
        buildEventTable();
        buildOlympicGameTable();
        buildFrom();
    }

    public String createString() {
        String id;
        if (!createExists.isSelected()) {
            id = Integer.toString(database.getAthleteList().lastEntry().getValue().getId() + 1);
        } else {
//            athleteList.contains(createName.getText());
//            database.getAthleteList().putIfAbsent(createName.getText());
            id = "-1"; //search for the athlete in the list
        }
        String age;
        if (!createBirthyear.getText().equals("NA")) {
            age = Integer.toString(Integer.parseInt(createYear.getText()) - Integer.parseInt(createBirthyear.getText()));
        } else {
            age = "NA";
        }
        String game = createYear.getText() + " " + createSeason.getValue();

        List<String> attributes = new ArrayList<>(Arrays.asList(quotes(id), quotes(createName.getText()),
                quotes(createGender.getValue()), age, createHeight.getText(), createWeight.getText(),
                quotes(createTeam.getText()), createNoc.getText().toUpperCase(), quotes(game),
                createYear.getText(), quotes(createSeason.getValue()), quotes(createCity.getText()),
                quotes(createSport.getText()), quotes(createEvent.getText())));
        if (!(createMedal.getValue().equals("NA"))) attributes.add(quotes(createMedal.getValue()));
        else attributes.add(createMedal.getValue());

        return String.join(",", attributes) + "\n";
    }

    public void create() {
        try {
            validateInput();
        } catch (InvalidInputError error) {
            Alert invalid = new Alert(Alert.AlertType.WARNING);
            invalid.setHeaderText("Invalid Input");
            invalid.setContentText(error.getMessage());
            invalid.show();
            return;
        }

        String entryLine = createString();
        Entry entry = new Entry(entryLine);
        System.out.println(entry);

        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setHeaderText("Are you sure?");
        confirmation.setContentText("The following will be permanently added to the database:\n" + entry.toString());

        Optional<ButtonType> result = confirmation.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK){
            System.out.println("Entry was confirmed!");
            database.addEntry(entry);
            FileHandler.addToFile(entryLine);
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

        athleteList.setAll(database.getAthleteList().values());
        athleteTableView.setItems(athleteList);
    }

    public void athleteSearch() {
        FilteredList<Athlete> filteredData = new FilteredList<>(athleteList, p -> true);
        String searchTerm = athleteSearchField.getText();
        filteredData.setPredicate(athlete -> {
            if (searchTerm == null || searchTerm.isEmpty()) return true;
            String lowerCaseFilter = searchTerm.toLowerCase();

            switch (athleteSearchCategory.getValue())
            {
                case "Name":
                    if (athlete.getName().toLowerCase().contains(lowerCaseFilter)) return true;
                case "Medals":
                    if (athlete.getMedals().toString().toLowerCase().contains(lowerCaseFilter)) return true;
                default:
                    return false;
            }
        });
        athleteTableView.setItems(filteredData);
    }

    private void buildTeamTable() {
        teamNocColumn.setCellValueFactory(new PropertyValueFactory<>("noc"));
        teamNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        teamAthletesColumn.setCellValueFactory(new PropertyValueFactory<>("athletes"));

        teamSearchCategory.setItems(FXCollections.observableArrayList("NOC", "Name", "Athletes"));
        teamSearchCategory.setValue(teamSearchCategory.getItems().get(0));

        teamList.setAll(database.getTeamList().values());
        teamTableView.setItems(teamList);
    }

    public void teamSearch() {
        FilteredList<Team> filteredData = new FilteredList<>(teamList, p -> true);
        String searchTerm = teamSearchField.getText();
        filteredData.setPredicate(team -> {
            if (searchTerm == null || searchTerm.isEmpty()) return true;
            String lowerCaseFilter = searchTerm.toLowerCase();

            switch (teamSearchCategory.getValue())
            {
                case "NOC":
                    if (team.getNoc().toString().toLowerCase().contains(lowerCaseFilter)) return true;
                case "Medals":
                    if (team.getName().toLowerCase().contains(lowerCaseFilter)) return true;
                case "Athletes":
                    if (team.getName().toLowerCase().contains(lowerCaseFilter)) return true;
                default:
                    return false;
            }
        });
        teamTableView.setItems(filteredData);
    }

    private void buildSportTable() {
        sportNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        sportEventsColumn.setCellValueFactory(new PropertyValueFactory<>("events"));

        sportSearchCategory.setItems(FXCollections.observableArrayList("Name", "Events"));
        sportSearchCategory.setValue(sportSearchCategory.getItems().get(0));

        sportList.setAll(database.getSportList().values());
        sportTableView.setItems(sportList);
    }

    public void sportSearch() {
        FilteredList<Sport> filteredData = new FilteredList<>(sportList, p -> true);
        String searchTerm = sportSearchField.getText();
        filteredData.setPredicate(sport -> {
            if (searchTerm == null || searchTerm.isEmpty()) return true;
            String lowerCaseFilter = searchTerm.toLowerCase();

            switch (sportSearchCategory.getValue())
            {
                case "Name":
                    if (sport.getName().toLowerCase().contains(lowerCaseFilter)) return true;
                case "Events":
                    if (sport.getEvents().toString().toLowerCase().contains(lowerCaseFilter)) return true;
                default:
                    return false;
            }
        });
        sportTableView.setItems(filteredData);
    }

    private void buildEventTable() {
        eventNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        eventSportColumn.setCellValueFactory(new PropertyValueFactory<>("sport"));
        eventGameColumn.setCellValueFactory(new PropertyValueFactory<>("olympicGame"));
        eventMedalistsColumn.setCellValueFactory(new PropertyValueFactory<>("medalists"));
        eventAthletesColumn.setCellValueFactory(new PropertyValueFactory<>("athletes"));

        eventSearchCategory.setItems(FXCollections.observableArrayList("Name", "Sport", "Athletes"));
        eventSearchCategory.setValue(eventSearchCategory.getItems().get(0));

        eventList.setAll(database.getEventList().values());
        eventTableView.setItems(eventList);
    }

    public void eventSearch() {
        FilteredList<Event> filteredData = new FilteredList<>(eventList, p -> true);
        String searchTerm = eventSearchField.getText();
        filteredData.setPredicate(event -> {
            if (searchTerm == null || searchTerm.isEmpty()) return true;
            String lowerCaseFilter = searchTerm.toLowerCase();

            switch (eventSearchCategory.getValue())
            {
                case "Name":
                    if (event.getName().toLowerCase().contains(lowerCaseFilter)) return true;
                case "Sport":
                    if (event.getSport().toString().toLowerCase().contains(lowerCaseFilter)) return true;
                case "Athletes":
                    if (event.getAthletes().toString().toLowerCase().contains(lowerCaseFilter)) return true;
                default:
                    return false;
            }
        });
        eventTableView.setItems(filteredData);
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

        gameList.setAll(database.getOlympicGameList().values());
        gameTableView.setItems(gameList);
    }

    public void gameSearch() {
        FilteredList<OlympicGame> filteredData = new FilteredList<>(gameList, p -> true);
        String searchTerm = gameSearchField.getText();
        filteredData.setPredicate(game -> {
            if (searchTerm == null || searchTerm.isEmpty()) return true;
            String lowerCaseFilter = searchTerm.toLowerCase();

            switch (gameSearchCategory.getValue())
            {
                case "City":
                    if (game.getCity().toLowerCase().contains(lowerCaseFilter)) return true;
                case "Teams":
                    if (game.getTeams().toString().toLowerCase().contains(lowerCaseFilter)) return true;
                case "Events":
                    if (game.getEvents().toString().toLowerCase().contains(lowerCaseFilter)) return true;
                case "Sports":
                    if (game.getSports().toString().toLowerCase().contains(lowerCaseFilter)) return true;
                default:
                    return false;
            }
        });
        gameTableView.setItems(filteredData);
    }

    private void buildFrom() {
        createGender.setItems(FXCollections.observableArrayList("M", "F"));
        createGender.setValue(createGender.getItems().get(0));
        createSeason.setItems(FXCollections.observableArrayList("Summer", "Winter"));
        createSeason.setValue(createSeason.getItems().get(0));
        createMedal.setItems(FXCollections.observableArrayList("NA", "Gold", "Silver", "Bronze"));
        createMedal.setValue(createMedal.getItems().get(0));
    }

    private void validateInput() throws InvalidInputError {
        FormValidator.isInputFieldFilled(createName);
        FormValidator.isInputFieldFilled(createTeam);
        FormValidator.isInputFieldFilled(createNoc);
        FormValidator.isNocValid(createNoc);
        FormValidator.isInputFieldFilled(createYear);
        FormValidator.isInputFieldFilled(createCity);
        FormValidator.isInputFieldFilled(createSport);
        FormValidator.isInputFieldFilled(createEvent);

        FormValidator.isInputNumber(createYear);
        if (!createBirthyear.getText().equals("")) FormValidator.isInputNumber(createBirthyear);
        else createBirthyear.setText("NA");
        if (!createHeight.getText().equals("")) FormValidator.isInputNumber(createHeight);
        else createHeight.setText("NA");
        if (!createWeight.getText().equals("")) FormValidator.isInputNumber(createWeight);
        else createWeight.setText("NA");
    }

    private String quotes(String string) {
        return "\"" + string + "\"";
    }

    private void clearInput() {
        createName.clear();
        createBirthyear.clear();
        createHeight.clear();
        createWeight.clear();
        createTeam.clear();
        createYear.clear();
        createCity.clear();
        createSport.clear();
        createEvent.clear();
    }

}
