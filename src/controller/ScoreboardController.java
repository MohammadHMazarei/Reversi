package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import model.User;

import java.net.URL;
import java.util.ResourceBundle;

public class ScoreboardController implements Initializable {

    @FXML
    private ListView<String> listView;

    @FXML
    private Button backBTN;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadData();
    }



    private void loadData(){
        for (int i = 0; i < User.users.size(); i++) {
            listView.getItems().add(User.users.get(i).getUserName() + "        ~~~~~~        Point : " + User.users.get(i).getPoint());
        }
    }



    public Button getBackBTN() {
        return backBTN;
    }
}
