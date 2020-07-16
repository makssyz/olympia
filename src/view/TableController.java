package view;

import items.Athlete;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lists.Database;
import lists.MedalList;
import tools.FileLoader;

import java.net.URL;
import java.util.*;

public class TableController implements Initializable {
    Database database = FileLoader.loadData();

    @FXML private TableView<Athlete> tableViewAthlete;
    @FXML private TableColumn<Athlete, Integer> idColumn;
    @FXML private TableColumn<Athlete, String> nameColumn;
    @FXML private TableColumn<Athlete, Integer> birthyearColumn;
    @FXML private TableColumn<Athlete, String> genderColumn;
    @FXML private TableColumn<Athlete, Integer> heightColumn;
    @FXML private TableColumn<Athlete, Integer> weightColumn;
    @FXML private TableColumn<Athlete, MedalList> medalsColumn;
    @FXML private TextField search;
    @FXML private ChoiceBox<String> choice;

    ObservableList<Athlete> athleteList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        birthyearColumn.setCellValueFactory(new PropertyValueFactory<>("birthyear"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        heightColumn.setCellValueFactory(new PropertyValueFactory<>("height"));
        weightColumn.setCellValueFactory(new PropertyValueFactory<>("weight"));
        medalsColumn.setCellValueFactory(new PropertyValueFactory<>("medals"));

        athleteList.addAll(database.getAthleteList().values());
        tableViewAthlete.getItems().addAll(athleteList);
    }

    public void search() {
        FilteredList<Athlete> filteredData = new FilteredList<>(athleteList, p -> true);
        search.textProperty().addListener((observable, content, searchTerm) -> {
            filteredData.setPredicate(athlete -> {
                if (searchTerm == null || searchTerm.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = searchTerm.toLowerCase();

                switch (choice.getValue())
                {
                    case "Name":
                        if (athlete.getName().toLowerCase().contains(lowerCaseFilter)) return true;
                    case "Medals":
                        if (athlete.getMedals().toString().toLowerCase().contains(lowerCaseFilter)) return true;
                    default:
                        return false;
                }
            });
        });
        tableViewAthlete.setItems(filteredData);
    }
}
