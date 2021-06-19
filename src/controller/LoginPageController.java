package controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginPageController implements Initializable {

    @FXML
    private TextField usernameField;

    @FXML
    private Button loginBTN;

    @FXML
    private Button exitBTN;

    @FXML
    private Button registerBTN;

    @FXML
    private PasswordField passwordField;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        onActions();
    }


    private void onActions(){
        loginBTN.setOnAction(e -> {
            try {
                loadLoginPage();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        registerBTN.setOnAction(e -> {});

        exitBTN.setOnAction(e -> { Platform.exit(); });
    }


    private void loadLoginPage() throws IOException {
        if (usernameField.getText().equals("1") && passwordField.getText().equals("1")){
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../view/GamePage.fxml"));
            loader.load();

            ((Stage)loginBTN.getScene().getWindow()).close();
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.getRoot()));
            stage.show();
        }
    }
}
