package view;

import exceptions.InvalidInputError;
import items.Athlete;
import items.NOC;
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
    Database database = FileHandler.loadData();

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

    /* Create New Entry */
    @FXML private TextField createName;
    @FXML private ChoiceBox<String> createGender;
    @FXML private TextField createBirthyear;
    @FXML private TextField createHeight;
    @FXML private TextField createWeight;
    @FXML private TextField createTeam;
    @FXML private ChoiceBox<String> createNoc;
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
        buildFrom();
    }

    public void athleteSearch() {
        FilteredList<Athlete> filteredData = new FilteredList<>(athleteList, p -> true);
        String searchTerm = athleteSearchField.getText();
            filteredData.setPredicate(athlete -> {
                if (searchTerm == null || searchTerm.isEmpty()) {
                    return true;
                }
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
                quotes(createGender.getValue()), age, createHeight.getText(), createWeight.getText(), quotes(createTeam.getText()),
                createNoc.getValue(), quotes(game), createYear.getText(), quotes(createSeason.getValue()), quotes(createCity.getText()),
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

    private void buildFrom() {
        ArrayList<String> nocList = new ArrayList<>();
        for (Map.Entry<String, NOC> entry : database.getNocList().entrySet()) {
            nocList.add(entry.getValue().getName());
        }
        createGender.setItems(FXCollections.observableArrayList("M", "F"));
        createGender.setValue(createGender.getItems().get(0));
        createNoc.setItems(FXCollections.observableArrayList(nocList));
        createNoc.setValue(createNoc.getItems().get(0));
        createSeason.setItems(FXCollections.observableArrayList("Summer", "Winter"));
        createSeason.setValue(createSeason.getItems().get(0));
        createMedal.setItems(FXCollections.observableArrayList("NA", "Gold", "Silver", "Bronze"));
        createMedal.setValue(createMedal.getItems().get(0));
    }

    private void validateInput() throws InvalidInputError {
        FormValidator.isInputFieldFilled(createName);
        FormValidator.isInputFieldFilled(createTeam);
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
