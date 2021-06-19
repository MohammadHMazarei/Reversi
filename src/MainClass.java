import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.io.IOException;

public class MainClass extends Application {


    //comment !



    public static void main(String[] args) {

      launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/LoginPage.fxml"));

        loader.load();

        // Add image to icon of primaryStage Which is in the photos folder (in src)!!
        Image icon = new Image("images/imgbin_circle-png.png");

        primaryStage.setScene(new Scene(loader.getRoot()));


        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("Othello ~Designed by : SaraNikMehr & MohammadHMazarei~");
        primaryStage.resizableProperty().setValue(Boolean.FALSE);
        primaryStage.show();
    }

}
