import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.UFile;
import model.User;

import java.io.IOException;

public class MainClass extends Application {


    //comment !

    private  static UFile uFile = new UFile();

    static {

        try {
            User.users = uFile.readVector("userFile.sam");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


    }





    public static void main(String[] args) throws IOException, ClassNotFoundException {

      launch(args);
      uFile.writeVector(User.users , "userFile.sam");

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
