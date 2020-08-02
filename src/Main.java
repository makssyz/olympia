import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        TabPane root = FXMLLoader.load(getClass().getResource("view/tableGUI.fxml"));

        Scene scene = new Scene(root);

        stage.getIcons().add(new Image("https://image.flaticon.com/icons/svg/3048/3048379.svg"));
        stage.setTitle("Olympia");
        stage.setScene(scene);
        stage.show();
    }
}
