package controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
import model.Cell;

import java.net.URL;
import java.util.ResourceBundle;

public class GamePageController implements Initializable {

    static int xEnd , yEnd;

     // images for animation :)
    final Image onePicture = new Image(String.valueOf(this.getClass().getResource("../images/photo_2021-06-11_16-00-58.jpg")));
    final Image twoPicture = new Image(String.valueOf(this.getClass().getResource
            ("../images/photo_2021-06-11_16-00-53.jpg")));
    final Image threePicture = new Image(String.valueOf(this.getClass().getResource
            ("../images/photo_2021-06-11_16-01-03.jpg")));


    // imageView for 3 images to design our project
    private final ImageView pic1 = new ImageView(onePicture);
    private final ImageView pic2 = new ImageView(twoPicture);
    private final ImageView pic3 = new ImageView(threePicture);

    public static Group othello;


    @FXML
    private VBox playGround;
    @FXML
    private HBox hbX;
    @FXML
    private Label label;

    Cell[][] thisCell;

    private void labelW() {
        label.setTextAlignment(TextAlignment.CENTER);
        label.setStyle("-fx-background-color: transparent");
        label.setFont(Font.font("Algerian" , 80) );

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       label.setText("REVERSI");
        setUpButtons();
        labelW();
        myAnimation();
        hbX.getChildren().addAll(othello);

        tableButtons();
        moveInAllButtons();
    }

    //This function make 8*8 table that contain all buttons that we call each button cell
    private void setUpButtons(){
        thisCell = new Cell[8][8];
        for (int i = 0; i < 8; i++) {
            HBox field = new HBox();
            for (int j = 0; j < 8; j++) {
                thisCell[i][j] = new Cell(i , j);
                field.getChildren().add(thisCell[i][j]);
            }
            playGround.getChildren().add(field);
        }
    }

    private void myAnimation(){

        // set size of image view
        pic1.setFitWidth(100);
        pic1.setFitHeight(100);
        pic2.setFitWidth(100);
        pic2.setFitHeight(100);
        pic3.setFitWidth(100);
        pic3.setFitHeight(100);


        othello= new Group(pic1 , pic2 , pic3);

        Timeline t = new Timeline();
        t.setCycleCount(Timeline.INDEFINITE);
        t.getKeyFrames().add(new KeyFrame(
                        Duration.millis(400),(ActionEvent)->{
                    othello.getChildren().setAll(pic1);
                }   )
        );

        t.getKeyFrames().add(new KeyFrame(
                        Duration.millis(700),(ActionEvent)->{
                    othello.getChildren().setAll(pic2);
                }   )
        );
        t.getKeyFrames().add(new KeyFrame(
                        Duration.millis(1000),(ActionEvent)->{
                    othello.getChildren().setAll(pic3);
                }   )
        );

        othello.setLayoutX(0);
        othello.setLayoutY(0);
        t.play();


    }

    //Prepare Primary Buttons
    private void tableButtons(){

        thisCell[3][3].setStyle("-fx-background-color: black");
        thisCell[3][3].setBlack(true);
        thisCell[3][3].setColor("black");
        thisCell[4][3].setStyle("-fx-background-color: white");
        thisCell[4][3].setColor("white");
        thisCell[3][4].setStyle("-fx-background-color: white");
        thisCell[3][4].setColor("white");
        thisCell[4][4].setStyle("-fx-background-color: black");
        thisCell[4][4].setBlack(true);
        thisCell[4][4].setColor("black");

    }


    private void moveInAllButtons(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (thisCell[i][j].isBlack()){
                    if (rightOfBlackCell(thisCell , i , j))
                        callSetOnActionButton(thisCell , i , j);
                }
                break;
            }
            break;
        }
    }

    private boolean rightOfBlackCell(Cell[][] cell , int x , int y){
        if (x == 8)
            return false;
        else {
            for (x = x +1; x < 8; x++) {
                if (cell[x][y].getColor().equals("white")){
                    return rightCheckOfBlackCell(cell , x , y);
                }
                else if (cell[x][y].getColor().equals("black"))
                    return false;
                else
                    return false;
            }
        }
        return false;
    }


    private boolean rightCheckOfBlackCell(Cell[][] cells , int x , int y){
        for (x = x + 1; x < 8; x++){
            if (cells[x][y].getColor() != null) {
                if (cells[x][y].getColor().equals("white"))
                    continue;
//                else if (cells[x][y].getColor().equals("black"))
                else
                    return false;
            }
            else {
                xEnd = x;
                yEnd = y;
                return true;
            }
        }
        return false;
    }




    private void callSetOnActionButton(Cell[][] cells , int xStart , int yStart){
        cells[xEnd][yEnd].setStyle("-fx-background-color: #9e9e9e");
        final int[] i = {xStart};
        final int[] j = {yStart};
        cells[xEnd][yEnd].setOnAction(e -> {
            for (; i[0] <= xEnd; i[0]++){
                for (; j[0] <= yEnd; j[0]++)
                    cells[i[0]][j[0]].setColor("black");
                    cells[i[0]][j[0]].setStyle("-fx-background-color: black");
            }
        });
    }


    private void setColorOfButton(String color ,Cell cell){
        cell.setStyle("-fx-background-color: " + color);
    }


}
