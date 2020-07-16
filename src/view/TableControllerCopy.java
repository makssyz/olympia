package view;

import items.Athlete;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lists.Database;
import lists.MedalList;
import tools.FileLoader;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.TreeMap;

public class TableControllerCopy implements Initializable {
    Database database = FileLoader.loadData();

    @FXML private TableView<Athlete> tableViewAthlete;
    @FXML private TableColumn<Athlete, Integer> idColumn;
    @FXML private TableColumn<Athlete, String> nameColumn;
    @FXML private TableColumn<Athlete, Integer> birthyearColumn;
    @FXML private TableColumn<Athlete, String> genderColumn;
    @FXML private TableColumn<Athlete, Integer> heightColumn;
    @FXML private TableColumn<Athlete, Integer> weightColumn;
    @FXML private TableColumn<Athlete, MedalList> medalsColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        birthyearColumn.setCellValueFactory(new PropertyValueFactory<>("birthyear"));

        tableViewAthlete.setItems(getData());
    }

    public ObservableList<Athlete> getData() {
        TreeMap<Integer, Athlete> athleteList = database.getAthleteList();



        ObservableList<Athlete> athletes = FXCollections.observableArrayList();
        athletes.add(new Athlete("1", "Makss",  "21", "2019", "M", "174", "60"));
        athletes.add(new Athlete("2", "Patrick",  "42", "1998", "M", "174", "80"));
        System.out.println(athletes.get(0));
        System.out.println(athletes.get(1));
        return athletes;
    }
}
