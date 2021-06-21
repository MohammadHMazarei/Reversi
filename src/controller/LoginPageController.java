package controller;

import com.jfoenix.controls.JFXRadioButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.User;

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

    @FXML
    private Label errLBL;

   public  static User user1;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        onActions();
        exitBTN.setOnAction(e -> { Platform.exit(); });
        try {
            loadRegisterPage();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private boolean checkField(){
        return !this.passwordField.getText().isEmpty()&& !this.usernameField.getText().isEmpty();
    }

  private  void  setTextOfERR(String text , String color) {
        errLBL.setText(text);
        errLBL.setTextFill(Color.valueOf(color));
  }

  private User foundUser(String username , String password){
      for (User u : User.users)
          if (u.getUserName().equalsIgnoreCase(username) && u.getPassword().equals(password)) {
              return u;
          }

    return null;
  }

    private void onActions() {
        loginBTN.setOnAction(event -> {
            if (checkField()) {
                User user = foundUser(this.usernameField.getText(), this.passwordField.getText());
                if (user != null) {

                    setTextOfERR("Your login was successful!", "GREEN");
                    this.usernameField.setText("");
                    this.passwordField.setText("");

                    user1 = user;

                    try {
                        loadLoginPage();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {setTextOfERR("not found !", "RED");}

            } else {setTextOfERR("please fill all blanks", "RED");}
        });

    }



    private void loadLoginPage() throws IOException {

            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../view/GamePage.fxml"));
            loader.load();

            Image icon = new Image("/images/imgbin_circle-png.png");
            ((Stage)loginBTN.getScene().getWindow()).close();
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.getRoot()));
            stage.getIcons().add(icon);
            stage.setTitle("Othello ~Designed by : SaraNikMehr & MohammadHMazarei~");
            stage.show();

    }

    private void loadRegisterPage() throws IOException {

        registerBTN.setOnAction(event -> {
            Stage registerStage = ((Stage) loginBTN.getScene().getWindow());
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../view/RegisterPage.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
             Image image = new Image("/images/imgbin_circle-png.png");
            registerStage.getIcons().add(image);
            registerStage.setScene(new Scene(loader.getRoot()));
            registerStage.setTitle("Othello ~Designed by : SaraNikMehr & MohammadHMazarei~");
            registerStage.resizableProperty().setValue(Boolean.FALSE);
            registerStage.show();
            onActions((RegisterPageController) loader.getController());
        });

    }

    private void onActions(RegisterPageController registerPageController){

       registerPageController. getCancelBTN().setOnAction(e -> {
            Stage stage = ((Stage)registerPageController.getCancelBTN().getScene().getWindow());

            stage.setScene(loginBTN.getScene());
            stage.show();
        });
    }
}
