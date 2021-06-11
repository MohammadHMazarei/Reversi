import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


import java.io.IOException;

public class MainClass extends Application {


    //comment !


    public static void main(String[] args) {

      launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/GamePage.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            //
        }
        Parent root = loader.getRoot();

        // Add image to icon of primaryStage Which is in the photos folder (in src)!!
        Image icon = new Image("images/imgbin_circle-png.png");

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.getIcons().add(icon);
        primaryStage.show();
    }
}
