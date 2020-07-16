import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import lists.Database;
import tools.FileHandler;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        TabPane root = FXMLLoader.load(getClass().getResource("view/tableGUI.fxml"));

        Scene scene = new Scene(root);

        stage.setTitle("Olympia");
        stage.setScene(scene);
        stage.show();
    }
}
