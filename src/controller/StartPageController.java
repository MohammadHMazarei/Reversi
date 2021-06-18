package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class StartPageController {

    @FXML
   private JFXButton startBTN;



    @FXML
    private void startBA(ActionEvent event) throws IOException {

        Stage stage = ((Stage) startBTN.getScene().getWindow());

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/GamePage.fxml"));
        loader.load();
        Scene scene = new Scene(loader.getRoot());
        stage.setScene(scene);

    }


}
