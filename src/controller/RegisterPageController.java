package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.User;

import java.awt.event.ActionEvent;
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

    @FXML
    private Label errLBL;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        addUser();
    }



    private boolean searchAllField(){
        return !this.lastNameField.getText().isEmpty()&&!this.nameField.getText().isEmpty()
                &&!this.usernameField.getText().isEmpty()&&!this.passwordField.getText().isEmpty();
    }

    private boolean searchInAllUser(String username){

        for (int i = 0; i < User.users.size(); i++) {
            if (User.users.get(i).getUserName().equalsIgnoreCase(username)){
                return false;
            }
        }


        return true;
    }

    private  void addUser(){
        registerBTN.setOnAction(event -> {

            if (searchAllField()) {

                User user = new User();
                if (searchInAllUser(usernameField.getText())) {
                    addDetail(user);
                    User.users.add(user);

                    errLBL.setText("you're sign up successfully !");
                    errLBL.setTextFill(Color.GREEN);
                    cleanAllField();
                }else {
                    errLBL.setText("This username is available!");
                    errLBL.setTextFill(Color.RED);
                }
            } else {

                errLBL.setText("please fill all blanks");
                errLBL.setTextFill(Color.RED);
            }
        });

    }

    protected  void addDetail(User user){

        user.setName(nameField.getText());
        user.setLastname(lastNameField.getText());
        user.setPassword(passwordField.getText());
        user.setUserName(usernameField.getText());
    }

    private void cleanAllField(){
        this.nameField.setText("");
        this.lastNameField.setText("");
        this.passwordField.setText("");
        this.usernameField.setText("");
    }

    public Button getCancelBTN() {
        return cancelBTN;
    }

    public void setCancelBTN(Button cancelBTN) {
        this.cancelBTN = cancelBTN;
    }
}
