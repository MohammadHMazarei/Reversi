package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Cell;

import java.net.URL;
import java.util.ResourceBundle;

public class GamePageController implements Initializable {

    @FXML
    private VBox playGround;

    Cell[][] thisCell;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setUpButtons();
    }


    //This function make 8*8 table that contain all buttons that we call each button cell
    private void setUpButtons(){
        for (int i = 0; i < 10; i++) {
            HBox field = new HBox();
            for (int j = 0; j < 10; j++) {
                Cell cell = new Cell(i , j);
                field.getChildren().add(cell);
            }
            playGround.getChildren().add(field);
        }
    }
}
