/* DISCLAIMER
 * This code has been fully designed and written by Makss Golubs during the second semester of the Computational
 * Data Science course at the Cooperative State University Baden Württemberg. A few code snippets from the internet
 * have been used as inspiration. Any resemblance to the work of other students from the same course is either a
 * coincidence, or (in case it's not credited) an act of plagiarism of the other party. The whole development process
 * can be looked up and retraced on https://github.com/makssyz/olympia as proof in case of doubt.
 */

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
