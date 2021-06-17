package controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
import model.Cell;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class GamePageController implements Initializable {

    static int xEnd , yEnd;

    static int xEndR , yEndR;
    static int xEndU , yEndU;
    static int xEndD , yEndD;
    static int xEndRU , yEndRU;
    static int xEndRD , yEndRD;
    static int xEndRRU , yEndRRU;
    static int xEndRRD , yEndRRD;
    static int countOfColoredBTN = 4;
    static Turn turn = Turn.BLACK;


    static boolean endOfGame = false;
     // images for animation :)
    final Image onePicture = new Image(String.valueOf(
            this.getClass().getResource("../images/photo_2021-06-11_16-00-58.jpg")));
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

    static Cell[][] thisCell;

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

        String[] color  = new String[2];
        color[0] = "black";
        color[1] = "white";
        String[] color1  = new String[2];
        color1[0] = "white";
        color1[1] = "black";

            if (turn.equals(Turn.WHITE)) {
                moveInAllButtons(color1);
            }else {
                coloredTheGrayBTN(color);
            }




    }

    //This function make 8*8 table that contain all buttons that we call each button cell
    private void setUpButtons(){
        thisCell = new Cell[8][8];
        for (int i = 0; i < 8; i++) {
            HBox field = new HBox();
            for (int j = 0; j < 8; j++) {
                thisCell[i][j] = new Cell(i , j);
                field.setStyle("-fx-border-color: green");
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
        thisCell[3][3].setVisible(true);
        thisCell[4][3].setStyle("-fx-background-color: white");
        thisCell[4][3].setColor("white");
        thisCell[4][3].setWhite(true);
        thisCell[4][3].setVisible(true);
        thisCell[3][4].setStyle("-fx-background-color: white");
        thisCell[3][4].setColor("white");
        thisCell[3][4].setWhite(true);
        thisCell[3][4].setVisible(true);
        thisCell[4][4].setStyle("-fx-background-color: black");
        thisCell[4][4].setBlack(true);
        thisCell[4][4].setColor("black");
        thisCell[4][4].setVisible(true);



    }

     private void setEnd(){

        int count = 0;
         for (int i = 0; i < 8; i++) {
             for (int j = 0; j <8 ; j++) {
                 if (thisCell[i][j].isBlack() || thisCell[i][j].isWhite()){
                     ++count;
                 }

             }

         }

         if (count==64){
             endOfGame = true;
         }


     }


    private void moveInAllButtons(String [] color){

        for (int i = 0; i < 8; i++) {

            for (int j = 0; j < 8; j++) {

                if (thisCell[i][j].isBlack()&&turn.equals(Turn.BLACK)) {
                    boolean[] result = searchAllBTN(color, i, j);

                    if (result[0]) {
                        thisCell[xEndR][yEndR].setColor("gray");
                        thisCell[xEndR][yEndR].setStyle("-fx-background-color: gray");
                        thisCell[xEndR][yEndR].setSelectable(true);

                    }

                    if (result[1]) {
                        thisCell[xEnd][yEnd].setColor("gray");
                        thisCell[xEnd][yEnd].setStyle("-fx-background-color: gray");
                        thisCell[xEnd][yEnd].setSelectable(true);

                    }

                    if (result[2]) {
                        thisCell[xEndU][yEndU].setColor("gray");
                        thisCell[xEndU][yEndU].setStyle("-fx-background-color: gray");
                        thisCell[xEndU][yEndU].setSelectable(true);
                    }

                    if (result[3]) {
                        thisCell[xEndD][yEndD].setColor("gray");
                        thisCell[xEndD][yEndD].setStyle("-fx-background-color: gray");
                        thisCell[xEndD][yEndD].setSelectable(true);
                    }


                    if (result[4]) {
                        thisCell[xEndRU][yEndRU].setColor("gray");
                        thisCell[xEndRU][yEndRU].setStyle("-fx-background-color: gray");
                        thisCell[xEndRU][yEndRU].setSelectable(true);


                    }

                    if (result[5]) {
                        thisCell[xEndRD][yEndRD].setColor("gray");
                        thisCell[xEndRD][yEndRD].setStyle("-fx-background-color: gray");
                        thisCell[xEndRD][yEndRD].setSelectable(true);
                    }


                    if (result[6]) {
                        thisCell[xEndRRU][yEndRRU].setColor("gray");
                        thisCell[xEndRRU][yEndRRU].setStyle("-fx-background-color: gray");
                        thisCell[xEndRRU][yEndRRU].setSelectable(true);
                    }


                    if (result[7]) {
                        thisCell[xEndRRD][yEndRRD].setColor("gray");
                        thisCell[xEndRRD][yEndRRD].setStyle("-fx-background-color: gray");
                        thisCell[xEndRRD][yEndRRD].setSelectable(true);
                    }

                }

            }
        }




    }

    private void mvm(String [] color ){

        for (int i = 0; i < 8; i++) {

            for (int j = 0; j < 8; j++) {

                if (thisCell[i][j].isWhite()) {
                    boolean[] result = searchAllBTN(color, i, j);
                    if (result[0]) {
                        thisCell[xEndR][yEndR].setColor("gray");
                        thisCell[xEndR][yEndR].setStyle("-fx-background-color: gray");
                        thisCell[xEndR][yEndR].setSelectable(true);

                    }

                    if (result[1]) {
                        thisCell[xEnd][yEnd].setColor("gray");
                        thisCell[xEnd][yEnd].setStyle("-fx-background-color: gray");
                        thisCell[xEnd][yEnd].setSelectable(true);

                    }

                    if (result[2]) {
                        thisCell[xEndU][yEndU].setColor("gray");
                        thisCell[xEndU][yEndU].setStyle("-fx-background-color: gray");
                        thisCell[xEndU][yEndU].setSelectable(true);
                    }

                    if (result[3]) {
                        thisCell[xEndD][yEndD].setColor("gray");
                        thisCell[xEndD][yEndD].setStyle("-fx-background-color: gray");
                        thisCell[xEndD][yEndD].setSelectable(true);
                    }


                    if (result[4]) {
                        thisCell[xEndRU][yEndRU].setColor("gray");
                        thisCell[xEndRU][yEndRU].setStyle("-fx-background-color: gray");
                        thisCell[xEndRU][yEndRU].setSelectable(true);


                    }

                    if (result[5]) {
                        thisCell[xEndRD][yEndRD].setColor("gray");
                        thisCell[xEndRD][yEndRD].setStyle("-fx-background-color: gray");
                        thisCell[xEndRD][yEndRD].setSelectable(true);
                    }


                    if (result[6]) {
                        thisCell[xEndRRU][yEndRRU].setColor("gray");
                        thisCell[xEndRRU][yEndRRU].setStyle("-fx-background-color: gray");
                        thisCell[xEndRRU][yEndRRU].setSelectable(true);
                    }


                    if (result[7]) {
                        thisCell[xEndRRD][yEndRRD].setColor("gray");
                        thisCell[xEndRRD][yEndRRD].setStyle("-fx-background-color: gray");
                        thisCell[xEndRRD][yEndRRD].setSelectable(true);
                    }

                }
            }

            }
    }

    private  void  coloredTheGrayBTN(String[] color){

        if (turn.equals(Turn.BLACK)) {
            moveInAllButtons(color);
        }else {
            mvm(color);
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                if (thisCell[i][j].isSelectable()){


                        onActionForGrayBTN(thisCell[i][j] , color);





                }

            }
        }



    }


    private boolean[] foundRange(Cell cell , String [] color){

        boolean [] result = new boolean[8];

        result[0] = rightOfBlackCellGRA(thisCell , cell.getxPosition() , cell.getyPosition() , color);




        return  result;
    }

    private  void  onActionForGrayBTN(Cell cell , String [] color){

        cell.setOnAction(event -> {

            boolean [] result = foundRange(thisCell[cell.getxPosition()][cell.getyPosition()] , color);

            if (result[0]){

                for (int i = cell.getyPosition()  ; i<= yEndR; i++){
                    thisCell[cell.getxPosition()][i].setColor(color[0]);
                   thisCell[cell.getxPosition()][i].setVisible(true);
                   thisCell[cell.getxPosition()][i].setStyle("-fx-background-color: "+color[0]);
                   thisCell[cell.getxPosition()][i].setBlack(true);
                    thisCell[cell.getxPosition()][i].setSelectable(false);
                   thisCell[cell.getxPosition()][i].setWhite(false);
                }



                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if (thisCell[i][j].isSelectable()){
                            thisCell[i][j].setSelectable(false);
                            thisCell[i][j].setStyle("-fx-background-color: green");
                        }
                    }

                }



                if (turn.equals(Turn.BLACK)){
                    turn = Turn.WHITE;
                    color[0] ="white";
                    color[1] ="black";
                    mvm(color);
                }else {

                    turn = Turn.BLACK;
                    color[0] ="black";
                    color[1] ="white";
                    moveInAllButtons(color);
                }



            }



        });

    }





    private boolean[] searchAllBTN(String [] color , int i , int j){

        boolean [] result = new boolean[8];

          result[0] = rightOfBlackCell(thisCell , i , j , color);
          result[1] = leftOfBlackCell(thisCell , i , j, color);
          result[2] = UPOfBlackCell(thisCell , i , j , color);
          result[3] = DownOfBlackCell(thisCell , i , j, color );
          result[4] = URealRUOfBlackCell(thisCell , i , j , color);
          result[5] = URealRDOfBlackCell(thisCell , i ,j , color);
          result[6] = RealRUOfBlackCell(thisCell , i , j , color);
          result[7] =RealRDOfBlackCell(thisCell , i ,j , color);


          return  result;
    }




    private boolean rightOfBlackCell(Cell[][] cell , int x , int y, String[] color){
        if (y == 7)
            return false;
        else {
            for (y = y +1; y < 8; y++) {
                if (cell[x][y].getColor().equals(color[1])){
                    return rightCheckOfBlackCell(cell , x , y , color);
                }
                else if (cell[x][y].getColor().equals(color[0]))
                    return false;
                else
                    return false;
            }
        }
        return false;
    }

    private boolean rightOfBlackCellGRA(Cell[][] cell , int x , int y, String[] color) {

            if (cell[x][y+1].getColor().equals(color[1])) {
                return rightCheckOfBlackCellGR(cell, x, y+1, color);
            } else if (cell[x][y].getColor().equals(color[0]))
                return false;



       return  false;
    }


    private boolean leftOfBlackCell(Cell [][] cell , int x , int y  , String [] color){
        if (y == 7)
        return false;
        else {
            for (y = y -1; y >=0; y--) {
                if (cell[x][y].getColor().equals(color[1])){
                    return leftCheckOfBlackCell(cell , x , y , color);
                }
                else if (cell[x][y].getColor().equals(color[0]))
                    return false;
                else
                    return false;
            }
        }
        return false;





    }

    // check Up of Black btn
    private boolean UPOfBlackCell(Cell [][] cell , int x , int y , String [] color){
        if (x == 7)
            return false;
        else {
            for (x = x -1; x >=0; x--) {
                if (cell[x][y].getColor().equals(color[1])){
                    return UPCheckOfBlackCell(cell , x , y , color);
                }
                else if (cell[x][y].getColor().equals(color[0]))
                    return false;
                else
                    return false;
            }
        }
        return false;

    }


    private boolean DownOfBlackCell(Cell [][] cell , int x , int y , String [] color){
        if (x == 7)
            return false;
        else {
            for (x = x +1; x <=8; x++) {
                if (cell[x][y].getColor().equals(color[1])){
                    return DCheckOfBlackCell(cell , x , y , color);
                }
                else if (cell[x][y].getColor().equals(color[0]))
                    return false;
                else
                    return false;
            }
        }
        return false;

    }
    private boolean URealRUOfBlackCell(Cell [][] cell , int x , int y , String [] color ){
        if (x == 7 || y==7)
            return false;
        else {
             y = y+1;
            for (x = x -1; x >=0&&y<=8; x-- , y++) {



                    if (cell[x][y].getColor().equals(color[1])) {
                        return URealRUCheckOfBlackCell(cell, x, y , color);
                    } else if (cell[x][y].getColor().equals(color[0]))
                        return false;
                    else {
                        return false;
                    }

            }
        }
        return false;

    }

    private boolean RealRUOfBlackCell(Cell [][] cell , int x , int y , String [] color){
        if (x == 7 || y==7)
            return false;
        else {
            y = y-1;
            for (x = x -1; x >=0&&y>=0; x-- , y--) {



                if (cell[x][y].getColor().equals(color[1])) {
                    return RealRUCheckOfBlackCell(cell, x, y , color);
                } else if (cell[x][y].getColor().equals(color[0]))
                    return false;
                else {
                    return false;
                }

            }
        }
        return false;

    }

    private boolean RealRDOfBlackCell(Cell [][] cell , int x , int y , String [] color){
        if (x == 7 || y==7)
            return false;
        else {
            y = y+1;
            for (x = x +1; x <=8&&y<=8; x++ , y++) {



                if (cell[x][y].getColor().equals(color[1])) {
                    return RealRDCheckOfBlackCell(cell, x, y ,color);
                } else if (cell[x][y].getColor().equals(color[0]))
                    return false;
                else {
                    return false;
                }

            }
        }
        return false;

    }

    private boolean URealRDOfBlackCell(Cell [][] cell , int x , int y , String [] color){
        if (x == 7 || y==7)
            return false;
        else {
            y = y-1;
            for (x = x +1; x <8&&y>=0; x++ , y--) {



                if (cell[x][y].getColor().equals(color[1])) {
                    return URealRDCheckOfBlackCell(cell, x, y , color);
                } else if (cell[x][y].getColor().equals(color[0]))
                    return false;
                else {
                    return false;
                }

            }
        }
        return false;

    }
    private boolean rightCheckOfBlackCellGR(Cell[][] cells , int x , int y , String [] color) {
        for (y = y + 1; y < 8; y++) {

            if (cells[x][y].getColor().equals(color[1]))
                continue;
//
            else if (cells[x][y].getColor().equals(color[0])) {
                xEndR = x;
                yEndR = y;
                return true;

            }



        }

        return false;
    }



    private boolean rightCheckOfBlackCell(Cell[][] cells , int x , int y , String [] color){
        for (y = y + 1; y < 8; y++){
            if (!cells[x][y].getColor().equals("")) {
                if (cells[x][y].getColor().equals(color[1]))
                    continue;
//                else if (cells[x][y].getColor().equals("black"))
                else
                    return false;
            }
            else {
                xEndR = x;
                yEndR = y;
                return true;
            }
        }
        return false;
    }

    private boolean leftCheckOfBlackCell(Cell[][] cells , int x , int y , String[] color){
        for (y = y-1; y >=0; y--){
            if (!cells[x][y].getColor().equals("")) {
                if (cells[x][y].getColor().equals(color[1]))
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

    // for up off black btn
    private boolean UPCheckOfBlackCell(Cell[][] cells , int x , int y  ,String [] color){
        for (x = x-1; x>=0 ; x--){
            try {
                if (!cells[x][y].getColor().equals("")) {
                    if (cells[x][y].getColor().equals(color[1]))
                        continue;
//                else if (cells[x][y].getColor().equals("black"))
                    else
                        return false;
                } else {
                    xEndU = x;
                    yEndU = y;
                    return true;
                }
            }catch (Exception ignore){

            }
        }
        return false;
    }

    private boolean DCheckOfBlackCell(Cell[][] cells , int x , int y, String [] color){
        for (x = x+1; x<=8 ; x++){
            try {
                if (!cells[x][y].getColor().equals("")) {
                    if (cells[x][y].getColor().equals(color[1]))
                        continue;
//                else if (cells[x][y].getColor().equals("black"))
                    else
                        return false;
                } else {
                    xEndD = x;
                    yEndD = y;
                    return true;
                }
            }catch (Exception ignore){
                
            }
        }
        return false;
    }


    private boolean URealRUCheckOfBlackCell(Cell[][] cells , int x , int y , String [] color) {
        y = y+1;
        for (x = x - 1; x >= 0&&y<=8; x-- , y++) {

                try {


                    if (!cells[x][y].getColor().equals("")) {
                        if (cells[x][y].getColor().equals(color[1]))
                            continue;
//                else if (cells[x][y].getColor().equals("black"))
                        else
                            return false;
                    } else {
                        xEndRU = x;
                        yEndRU = y;
                        return true;
                    }
                }catch (Exception ignore){

                }

        }
        return false;
    }


    private boolean RealRUCheckOfBlackCell(Cell[][] cells , int x , int y , String [] color) {
        y = y-1;
        for (x = x - 1; x >= 0&&y>=0; x-- , y--) {

           try {
               if (!cells[x][y].getColor().equals("")) {
                   if (cells[x][y].getColor().equals(color[1]))
                       continue;
//                else if (cells[x][y].getColor().equals("black"))
                   else
                       return false;
               } else {
                   xEndRRU = x;
                   yEndRRU = y;
                   return true;
               }
           }catch (Exception ignore){

           }

        }
        return false;
    }

    private boolean RealRDCheckOfBlackCell(Cell[][] cells , int x , int y , String [] color) {
        y = y+1;
        for (x = x + 1; x <=8&&y<=8; x++ , y++) {


            try {
                if (!cells[x][y].getColor().equals("")) {
                    if (cells[x][y].getColor().equals(color[1]))
                        continue;
//                else if (cells[x][y].getColor().equals("black"))
                    else
                        return false;
                } else {
                    xEndRRD = x;
                    yEndRRD = y;
                    return true;
                }

            }catch (Exception ignore){

            }

        }
        return false;
    }


    private boolean URealRDCheckOfBlackCell(Cell[][] cells , int x , int y , String [] color) {
        y = y-1;
        for (x = x + 1; x <=8&&y>=0; x++ , y--) {


            try {
                if (!cells[x][y].getColor().equals("")) {
                    if (cells[x][y].getColor().equals(color[1]))
                        continue;
//                else if (cells[x][y].getColor().equals("black"))
                    else
                        return false;
                } else {
                    xEndRD = x;
                    yEndRD = y;
                    return true;
                }
            }catch (Exception ignore){

            }

        }
        return false;
    }



    private void callSetOnActionButton(Cell[][] cells , int xStart , int yStart , String[] color){
        cells[xEndR][yEndR].setStyle("-fx-background-color: #9e9e9e");
        cells[xEndR][yEndR].setVisible(true);
        final int[] i = {xStart};
        final int[] j = {yStart};
        cells[xEndR][yEndR].setOnAction(e -> {

            for (; j[0] <= yEndR; j[0]++){
                cells[i[0]][j[0]].setColor(color[0]);
                cells[i[0]][j[0]].setVisible(true);
                cells[i[0]][j[0]].setStyle("-fx-background-color: "+color[0]);
            }

            if (turn.equals(Turn.BLACK)){
                turn = Turn.WHITE;
                color[0] ="white";
                color[1] ="black";
                moveInAllButtons(color);
            }else {

                turn = Turn.BLACK;
                color[0] ="black";
                color[1] ="white";
                moveInAllButtons(color);
            }
        });
    }
    private void callSetOnActionButtonL(Cell[][] cells , int xStart , int yStart , String[] color){
        cells[xEnd][yEnd].setStyle("-fx-background-color: #9e9e9e");
        cells[xEnd][yEnd].setVisible(true);
        final int[] i = {xStart};
        final int[] j = {yStart};
        cells[xEnd][yEnd].setOnAction(e -> {
            for (; j[0] >= yEnd; j[0]--){
                cells[i[0]][j[0]].setColor(color[0]);
                cells[i[0]][j[0]].setVisible(true);
                cells[i[0]][j[0]].setStyle("-fx-background-color:" + color[0]);
            }

            if (turn.equals(Turn.BLACK)){
                turn = Turn.WHITE;
                color[0] ="white";
                color[1] ="black";

                moveInAllButtons(color);
            }else {

                turn = Turn.BLACK;
                color[0] ="black";
                color[1] ="white";
                moveInAllButtons(color);
            }
        });
    }

    private void callSetOnActionButtonU(Cell[][] cells , int xStart , int yStart , String[] color){
        cells[xEndU][yEndU].setStyle("-fx-background-color: #9e9e9e");
        cells[xEndU][yEndU].setVisible(true);
        final int[] i = {xStart};
        final int[] j = {yStart};
        cells[xEndU][yEndU].setOnAction(e -> {

            for (; i[0] >=xEndU; i[0]--){
                cells[i[0]][j[0]].setColor(color[0]);
                cells[i[0]][j[0]].setVisible(true);
                cells[i[0]][j[0]].setStyle("-fx-background-color: "+color[0]);
            }


            if (turn.equals(Turn.BLACK)){
                turn = Turn.WHITE;
                color[0] ="white";
                color[1] ="black";

                moveInAllButtons(color);
            }else {

                turn = Turn.BLACK;
                color[0] ="black";
                color[1] ="white";
                moveInAllButtons(color);
            }

        });
    }

    private void callSetOnActionButtonD(Cell[][] cells , int xStart , int yStart , String [] color){
        cells[xEndD][yEndD].setStyle("-fx-background-color: #9e9e9e");
        cells[xEndD][yEndD].setVisible(true);
        final int[] i = {xStart};
        final int[] j = {yStart};
        cells[xEndD][yEndD].setOnAction(e -> {

            for (; i[0] <=xEndD; i[0]++){
                cells[i[0]][j[0]].setColor(color[0]);
                cells[i[0]][j[0]].setVisible(true);
                cells[i[0]][j[0]].setStyle("-fx-background-color: " + color[0]);
            }

            if (turn.equals(Turn.BLACK)){
                turn = Turn.WHITE;
                color[0] ="white";
                color[1] ="black";

                moveInAllButtons(color);
            }else {

                turn = Turn.BLACK;
                color[0] ="black";
                color[1] ="white";
                moveInAllButtons(color);
            }

        });
    }

    private void callSetOnActionButtonURU(Cell[][] cells , int xStart , int yStart , String [] color){
        cells[xEndRU][yEndRU].setStyle("-fx-background-color: #9e9e9e");
        final int[] i = {xStart};
        final int[] j = {yStart};
        cells[xEndRU][yEndRU].setOnAction(e -> {

            for (; i[0] >=xEndRU&&j[0]<=yEndRU; i[0]-- , j[0]++){
                cells[i[0]][j[0]].setColor(color[0]);
                cells[i[0]][j[0]].setStyle("-fx-background-color: "+color[0]);
            }

            if (turn.equals(Turn.BLACK)){
                turn = Turn.WHITE;
                color[0] ="white";
                color[1] ="black";

                moveInAllButtons(color);
            }else {

                turn = Turn.BLACK;
                color[0] ="black";
                color[1] ="white";
                moveInAllButtons(color);
            }

        });
    }
    private void callSetOnActionButtonURD(Cell[][] cells , int xStart , int yStart , String [] color){
        cells[xEndRD][yEndRD].setStyle("-fx-background-color: #9e9e9e");
        cells[xEndRD][yEndRD].setVisible(true);
        final int[] i = {xStart};
        final int[] j = {yStart};
        cells[xEndRD][yEndRD].setOnAction(e -> {

            for (; i[0] <=xEndRD&&j[0]>=yEndRD; i[0]++ , j[0]--){
                cells[i[0]][j[0]].setColor(color[0]);
                cells[i[0]][j[0]].setVisible(true);
                cells[i[0]][j[0]].setStyle("-fx-background-color: " + color[0]);
            }


            if (turn.equals(Turn.BLACK)){
                turn = Turn.WHITE;
                color[0] ="white";
                color[1] ="black";

                moveInAllButtons(color);
            }else {

                turn = Turn.BLACK;
                color[0] ="black";
                color[1] ="white";
                moveInAllButtons(color);
            }

        });
    }

    private void callSetOnActionButtonRU(Cell[][] cells , int xStart , int yStart , String [] color){
        cells[xEndRRU][yEndRRU].setStyle("-fx-background-color: #9e9e9e");
        cells[xEndRRU][yEndRRU].setVisible(true);
        final int[] i = {xStart};
        final int[] j = {yStart};
        cells[xEndRRU][yEndRRU].setOnAction(e -> {

            for (; i[0] >=xEndRRU&&j[0]>=yEndRRU; i[0]-- , j[0]--){
                cells[i[0]][j[0]].setColor(color[0]);
                cells[i[0]][j[0]].setVisible(true);
                cells[i[0]][j[0]].setStyle("-fx-background-color: " + color[0]);
            }

            if (turn.equals(Turn.BLACK)){
                turn = Turn.WHITE;
                color[0] ="white";
                color[1] ="black";

                moveInAllButtons(color);
            }else {

                turn = Turn.BLACK;
                color[0] ="black";
                color[1] ="white";
                moveInAllButtons(color);
            }

        });
    }

    private void callSetOnActionButtonRD(Cell[][] cells , int xStart , int yStart , String[] color){
        cells[xEndRRD][yEndRRD].setStyle("-fx-background-color: #9e9e9e");
        cells[xEndRRD][yEndRRD].setVisible(true);
        final int[] i = {xStart};
        final int[] j = {yStart};
        cells[xEndRRD][yEndRRD].setOnAction(e -> {

            for (; i[0] <=xEndRRD&&j[0]<=yEndRRD; i[0]++ , j[0]++){
                cells[i[0]][j[0]].setColor(color[0]);
                cells[i[0]][j[0]].setVisible(true);
                cells[i[0]][j[0]].setStyle("-fx-background-color: " + color[0]);
            }

            if (turn.equals(Turn.BLACK)){
                turn = Turn.WHITE;
                color[0] ="white";
                color[1] ="black";

                moveInAllButtons(color);
            }else {

                turn = Turn.BLACK;
                color[0] ="black";
                color[1] ="white";
                moveInAllButtons(color);
            }

        });
    }


    private void setColorOfButton(String color ,Cell cell){
        cell.setStyle("-fx-background-color: " + color);
    }


}
enum Turn { BLACK , WHITE}