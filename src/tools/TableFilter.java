package tools;

import items.*;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import lists.ObjectSet;

public class TableFilter {

    public static void search(ObservableList<Object> list, TextField searchField,
                              ChoiceBox<String> searchCategory, TableView<Object> tableView) {
        FilteredList<Object> filteredData = new FilteredList<>(list, p -> true);
        String searchTerm = searchField.getText();
        filteredData.setPredicate(object -> {
            if (searchTerm == null || searchTerm.isEmpty()) return true;
            String lowerCaseFilter = searchTerm.toLowerCase();

            return switch (searchField.getId()) {
                case "athleteSearchField" -> switch (searchCategory.getValue()) {
                    case "Name" -> ((Athlete) object).getName().toLowerCase().contains(lowerCaseFilter);
                    case "Medals" -> ((Athlete) object).getMedals().toString().toLowerCase().contains(lowerCaseFilter);
                    default -> false;
                };
                case "teamSearchField" -> switch (searchCategory.getValue()) {
                    case "NOC" -> ((Team) object).getNoc().toString().toLowerCase().contains(lowerCaseFilter);
                    case "Name" -> ((Team) object).getName().toLowerCase().contains(lowerCaseFilter);
                    case "Athletes" -> ((Team) object).getAthletes().toString().toLowerCase().contains(lowerCaseFilter);
                    default -> false;
                };
                case "sportSearchField" -> switch (searchCategory.getValue()) {
                    case "Name" -> ((Sport) object).getName().toLowerCase().contains(lowerCaseFilter);
                    case "Events" -> ((Sport) object).getEvents().toString().toLowerCase().contains(lowerCaseFilter);
                    default -> false;
                };
                case "eventSearchField" -> switch (searchCategory.getValue()) {
                    case "Name" -> ((Event) object).getName().toLowerCase().contains(lowerCaseFilter);
                    case "Sport" -> ((Event) object).getSport().toString().toLowerCase().contains(lowerCaseFilter);
                    case "Athletes" -> ((Event) object).getAthletes().toString().toLowerCase().contains(lowerCaseFilter);
                    default -> false;
                };
                case "gameSearchField" -> switch (searchCategory.getValue()) {
                    case "City" -> ((OlympicGame) object).getCity().toLowerCase().contains(lowerCaseFilter);
                    case "Teams" -> ((OlympicGame) object).getName().toLowerCase().contains(lowerCaseFilter);
                    case "Events" -> ((OlympicGame) object).getEvents().toString().toLowerCase().contains(lowerCaseFilter);
                    case "Sports" -> ((OlympicGame) object).getSports().toString().toLowerCase().contains(lowerCaseFilter);
                    default -> false;
                };
                default -> false;
            };
        });
        tableView.setItems(filteredData);
    }
}
