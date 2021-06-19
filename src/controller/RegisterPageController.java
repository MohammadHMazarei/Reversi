package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterPageController implements Initializable {

    @FXML
    private TextField usernameField;

    @FXML
    private Button cancelBTN;

    @FXML
    private Button registerBTN;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField nameField;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        onActions();
    }

    private void onActions(){
        registerBTN.setOnAction(e -> {});

        cancelBTN.setOnAction(e -> {
            ((Stage)cancelBTN.getScene().getWindow()).close();

            LoginPageController.registerStage = null;
        });
    }
}
