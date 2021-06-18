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

    static int xEndR , yEndR;
    static int xEndU , yEndU;
    static int xEndD , yEndD;
    static int xEndRU , yEndRU;
    static int xEndRD , yEndRD;
    static int xEndRRU , yEndRRU;
    static int xEndRRD , yEndRRD;
     static final int row = 8;
     static final int column = 8;
    static int countOfColoredBTN = 4;
    static Turn turn = Turn.BLACK;


    static boolean endOfGame = false;


     // images for animation :) -------------------------------------------------------------
    final Image onePicture = new Image(String.valueOf(
            this.getClass().getResource("../images/photo_2021-06-11_16-00-58.jpg")));
    final Image twoPicture = new Image(String.valueOf(this.getClass().getResource
            ("../images/photo_2021-06-11_16-00-53.jpg")));
    final Image threePicture = new Image(String.valueOf(this.getClass().getResource
            ("../images/photo_2021-06-11_16-01-03.jpg")));

    //------------------------------------------------------------------------------------images

    // imageView for 3 images to design our project -------
    private final ImageView pic1 = new ImageView(onePicture);
    private final ImageView pic2 = new ImageView(twoPicture);
    private final ImageView pic3 = new ImageView(threePicture);
   // --------------------------------------------------------
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

       Color[] color  = new Color[2];
        color[0] = Color.BLACK;
        color[1] = Color.WHITE;

        Color[] color1  = new Color[2];
        color1[0] = Color.WHITE;
        color1[1] = Color.BLACK;
            if (turn.equals(Turn.WHITE)) {
                moveInAllButtons(color1);
            }else {
                coloredTheGrayBTN(color);
            }




    }

    //This function make 8*8 table that contain all buttons that we call each button cell
    private void setUpButtons(){
        thisCell = new Cell[row][column];
        for (int i = 0; i < row; i++) {
            HBox field = new HBox();
            for (int j = 0; j < column; j++) {
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

        int n = row/2 -1;
        int m = row/2;
        thisCell[n][n].setBlack(true);
        thisCell[n][n].setColor(Color.BLACK);
        thisCell[n][n].setVisible(true);


        thisCell[m][n].setColor(Color.WHITE);
        thisCell[m][n].setWhite(true);
        thisCell[m][n].setVisible(true);

        thisCell[n][m].setColor(Color.WHITE);
        thisCell[n][m].setWhite(true);
        thisCell[n][m].setVisible(true);

        thisCell[m][m].setBlack(true);
        thisCell[m][m].setColor(Color.BLACK);
        thisCell[m][m].setVisible(true);



    }

     private void setEnd(){

        int count = 0;
         for (int i = 0; i < row; i++) {
             for (int j = 0; j <column ; j++) {
                 if (thisCell[i][j].isBlack() || thisCell[i][j].isWhite()){
                     ++count;
                 }

             }

         }

         if (count==64){
             endOfGame = true;
         }


     }


    private void moveInAllButtons(Color [] color){

        for (int i = 0; i < row; i++) {

            for (int j = 0; j < column; j++) {

                if (thisCell[i][j].isBlack()&&turn.equals(Turn.BLACK)) {
                    boolean[] result = searchAllBTN(color, i, j);

                    if (result[0]) {
                        thisCell[xEndR][yEndR].setColor(Color.GRAY);
                        thisCell[xEndR][yEndR].setSelectable(true);

                    }

                    if (result[1]) {
                        thisCell[xEnd][yEnd].setColor(Color.GRAY);
                        thisCell[xEnd][yEnd].setSelectable(true);

                    }

                    if (result[2]) {
                        thisCell[xEndU][yEndU].setColor(Color.GRAY);
                        thisCell[xEndU][yEndU].setSelectable(true);
                    }

                    if (result[3]) {
                        thisCell[xEndD][yEndD].setColor(Color.GRAY);
                        thisCell[xEndD][yEndD].setSelectable(true);
                    }


                    if (result[4]) {
                        thisCell[xEndRU][yEndRU].setColor(Color.GRAY);
                        thisCell[xEndRU][yEndRU].setSelectable(true);


                    }

                    if (result[5]) {
                        thisCell[xEndRD][yEndRD].setColor(Color.GRAY);
                        thisCell[xEndRD][yEndRD].setSelectable(true);
                    }


                    if (result[6]) {
                        thisCell[xEndRRU][yEndRRU].setColor(Color.GRAY);
                        thisCell[xEndRRU][yEndRRU].setSelectable(true);
                    }


                    if (result[7]) {
                        thisCell[xEndRRD][yEndRRD].setColor(Color.GRAY);
                        thisCell[xEndRRD][yEndRRD].setSelectable(true);
                    }

                }

            }
        }




    }

    private void mvm(Color [] color ){

        for (int i = 0; i < row; i++) {

            for (int j = 0; j < column; j++) {

                if (thisCell[i][j].isWhite()) {
                    boolean[] result = searchAllBTN(color, i, j);
                    if (result[0]) {
                        thisCell[xEndR][yEndR].setColor(Color.GRAY);
                        thisCell[xEndR][yEndR].setSelectable(true);
                        thisCell[xEndR][yEndR].setBlack(false);
                        thisCell[xEndR][yEndR].setWhite(false);

                    }

                    if (result[1]) {
                        thisCell[xEnd][yEnd].setColor(Color.GRAY);
                        thisCell[xEnd][yEnd].setSelectable(true);
                        thisCell[xEnd][yEnd].setWhite(false);
                        thisCell[xEnd][yEnd].setBlack(false);

                    }

                    if (result[2]) {
                        thisCell[xEndU][yEndU].setColor(Color.GRAY);
                        thisCell[xEndU][yEndU].setSelectable(true);
                        thisCell[xEndU][yEndU].setWhite(false);
                        thisCell[xEndU][yEndU].setBlack(false);
                    }

                    if (result[3]) {
                        thisCell[xEndD][yEndD].setColor(Color.GRAY);
                        thisCell[xEndD][yEndD].setSelectable(true);
                        thisCell[xEndD][yEndD].setWhite(false);
                        thisCell[xEndD][yEndD].setBlack(false);
                    }


                    if (result[4]) {
                        thisCell[xEndRU][yEndRU].setColor(Color.GRAY);
                        thisCell[xEndRU][yEndRU].setSelectable(true);
                        thisCell[xEndRU][yEndRU].setWhite(false);
                        thisCell[xEndRU][yEndRU].setBlack(false);


                    }

                    if (result[5]) {
                        thisCell[xEndRD][yEndRD].setColor(Color.GRAY);
                        thisCell[xEndRD][yEndRD].setSelectable(true);
                        thisCell[xEndRD][yEndRD].setWhite(false);
                        thisCell[xEndRD][yEndRD].setBlack(false);
                    }


                    if (result[6]) {
                        thisCell[xEndRRU][yEndRRU].setColor(Color.GRAY);
                        thisCell[xEndRRU][yEndRRU].setSelectable(true);
                        thisCell[xEndRRU][yEndRRU].setBlack(false);
                        thisCell[xEndRRU][yEndRRU].setWhite(false);
                    }


                    if (result[7]) {
                        thisCell[xEndRRD][yEndRRD].setColor(Color.GRAY);
                        thisCell[xEndRRD][yEndRRD].setSelectable(true);
                        thisCell[xEndRRD][yEndRRD].setWhite(false);
                        thisCell[xEndRRD][yEndRRD].setBlack(false);
                    }

                }
            }

            }
    }

    private  void  coloredTheGrayBTN(Color[] color){

        if (turn.equals(Turn.BLACK)) {
            moveInAllButtons(color);
        }else {
            mvm(color);
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {

                if (thisCell[i][j].isSelectable() && thisCell[i][j].getColor().equals(Color.GRAY)){


                        onActionForGrayBTN(thisCell[i][j] , color);





                }

            }
        }



    }


    private boolean[] foundRange(Cell cell , Color [] color){

        boolean [] result = new boolean[8];

        result[0] = rightOfBlackCellGRA(thisCell , cell.getxPosition() , cell.getyPosition() , color);
        result[1] = leftOfBlackCellGRA(thisCell , cell.getxPosition() , cell.getyPosition() , color);
        result[2] =  downOfBlackCellGRA(thisCell , cell.getxPosition() , cell.getyPosition() , color);
        result[3] = UpOfBlackCellGRA(thisCell , cell.getxPosition() , cell.getyPosition() , color);
        result[4] = RRUOfBlackCellGRA(thisCell , cell.getxPosition() , cell.getyPosition() , color);
        result[5] = RRDOfBlackCellGRA(thisCell , cell.getxPosition() , cell.getyPosition() , color);
        result[6] = URRUOfBlackCellGRA(thisCell , cell.getxPosition() , cell.getyPosition() , color);
        result[7] = URRDOfBlackCellGRA(thisCell , cell.getxPosition() , cell.getyPosition() , color);
        return  result;
    }

    private  void  addUn(){

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (thisCell[i][j].isSelectable()){
                    thisCell[i][j].setSelectable(false);
                    thisCell[i][j].setColor(Color.GREEN);
                }
            }

        }


    }
    private  void  onActionForGrayBTN(Cell cell , Color [] color){


            cell.setOnAction(event -> {

                if ((cell.isSelectable() && cell.getColor().equals(Color.GRAY))) {
                    boolean[] result = foundRange(thisCell[cell.getxPosition()][cell.getyPosition()], color);

                    if (result[0]) {


                        for (int i = cell.getyPosition(); i <= yEndR; i++) {
                            thisCell[cell.getxPosition()][i].setColor(color[0]);
                            thisCell[cell.getxPosition()][i].setVisible(true);
                            thisCell[cell.getxPosition()][i].setSelectable(false);
                            if (turn.equals(Turn.BLACK)) {
                                thisCell[cell.getxPosition()][i].setBlack(true);
                                thisCell[cell.getxPosition()][i].setWhite(false);
                            } else {

                                thisCell[cell.getxPosition()][i].setBlack(false);
                                thisCell[cell.getxPosition()][i].setWhite(true);
                            }
                        }

                        addUn();
                    }
                    if (result[1]) {


                        for (int i = cell.getyPosition(); i >= yEnd; i--) {
                            thisCell[cell.getxPosition()][i].setColor(color[0]);
                            thisCell[cell.getxPosition()][i].setVisible(true);
                            thisCell[cell.getxPosition()][i].setSelectable(false);
                            if (turn.equals(Turn.BLACK)) {
                                thisCell[cell.getxPosition()][i].setBlack(true);
                                thisCell[cell.getxPosition()][i].setWhite(false);
                            } else {

                                thisCell[cell.getxPosition()][i].setBlack(false);
                                thisCell[cell.getxPosition()][i].setWhite(true);
                            }
                        }

                        addUn();
                    }


                    if (result[2]) {

                        for (int i = cell.getxPosition(); i <= xEndD; i++) {
                            thisCell[i][cell.getyPosition()].setColor(color[0]);
                            thisCell[i][cell.getyPosition()].setVisible(true);
                            thisCell[i][cell.getyPosition()].setSelectable(false);
                            if (turn.equals(Turn.BLACK)) {
                                thisCell[i][cell.getyPosition()].setBlack(true);
                                thisCell[i][cell.getyPosition()].setWhite(false);
                            } else {

                                thisCell[i][cell.getyPosition()].setBlack(false);
                                thisCell[i][cell.getyPosition()].setWhite(true);
                            }
                        }

                        addUn();

                    }


                    if (result[3]) {

                        for (int i = cell.getxPosition(); i >= xEndU; i--) {
                            thisCell[i][cell.getyPosition()].setColor(color[0]);
                            thisCell[i][cell.getyPosition()].setVisible(true);
                            thisCell[i][cell.getyPosition()].setSelectable(false);
                            if (turn.equals(Turn.BLACK)) {
                                thisCell[i][cell.getyPosition()].setBlack(true);
                                thisCell[i][cell.getyPosition()].setWhite(false);
                            } else {

                                thisCell[i][cell.getyPosition()].setBlack(false);
                                thisCell[i][cell.getyPosition()].setWhite(true);
                            }
                        }

                        addUn();
                    }

                    if (result[4]) {

                        for (int i = cell.getxPosition(), j = cell.getyPosition(); i >= xEndRRU && j >= yEndRRU; i--, j--) {
                            thisCell[i][j].setColor(color[0]);
                            thisCell[i][j].setVisible(true);
                            thisCell[i][j].setSelectable(false);
                            if (turn.equals(Turn.BLACK)) {
                                thisCell[i][j].setBlack(true);
                                thisCell[i][j].setWhite(false);
                            } else {

                                thisCell[i][j].setBlack(false);
                                thisCell[i][j].setWhite(true);
                            }
                        }
                        addUn();

                    }

                    if (result[5]) {
                        for (int i = cell.getxPosition(), j = cell.getyPosition(); i <= xEndRRD && j <= yEndRRD; i++, j++) {
                            thisCell[i][j].setColor(color[0]);
                            thisCell[i][j].setVisible(true);
                            thisCell[i][j].setSelectable(false);
                            if (turn.equals(Turn.BLACK)) {
                                thisCell[i][j].setBlack(true);
                                thisCell[i][j].setWhite(false);
                            } else {

                                thisCell[i][j].setBlack(false);
                                thisCell[i][j].setWhite(true);
                            }
                        }
                        addUn();

                    }
                    if (result[6]) {
                        for (int i = cell.getxPosition(), j = cell.getyPosition(); i >= xEndRU && j <= yEndRU; i--, j++) {
                            thisCell[i][j].setColor(color[0]);
                            thisCell[i][j].setVisible(true);
                            thisCell[i][j].setSelectable(false);
                            if (turn.equals(Turn.BLACK)) {
                                thisCell[i][j].setBlack(true);
                                thisCell[i][j].setWhite(false);
                            } else {

                                thisCell[i][j].setBlack(false);
                                thisCell[i][j].setWhite(true);
                            }
                        }
                        addUn();

                    }

                    if (result[7]) {

                        for (int i = cell.getxPosition(), j = cell.getyPosition(); i <= xEndRD && j >= yEndRD; i++, j--) {
                            thisCell[i][j].setColor(color[0]);
                            thisCell[i][j].setVisible(true);
                            thisCell[i][j].setSelectable(false);
                            if (turn.equals(Turn.BLACK)) {
                                thisCell[i][j].setBlack(true);
                                thisCell[i][j].setWhite(false);
                            } else {

                                thisCell[i][j].setBlack(false);
                                thisCell[i][j].setWhite(true);
                            }
                        }

                        addUn();
                    }


                    if (turn.equals(Turn.BLACK)) {
                        turn = Turn.WHITE;
                        color[0] = Color.WHITE;
                        color[1] = Color.BLACK;
                        mvm(color);
                        coloredTheGrayBTN(color);

                    } else if (turn.equals(Turn.WHITE)) {

                        turn = Turn.BLACK;
                        color[0] = Color.BLACK;
                        color[1] = Color.WHITE;
                        moveInAllButtons(color);
                        coloredTheGrayBTN(color);
                    }
                }

            });


    }



    private boolean[] searchAllBTN(Color [] color , int i , int j){

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




    private boolean rightOfBlackCell(Cell[][] cell , int x , int y, Color[] color){
        if (y == 7)
            return false;
        else {
            for (y = y +1; y < column; y++) {
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

    private boolean rightOfBlackCellGRA(Cell[][] cell , int x , int y, Color[] color) {
         try {
             if (cell[x][y + 1].getColor().equals(color[1])) {

                 return rightCheckOfBlackCellGR(cell, x, y + 1, color);
             } else if (cell[x][y].getColor().equals(color[0])) {

                 return false;

             }

         }catch (Exception e){

         }
       return  false;
    }
    private boolean leftOfBlackCellGRA(Cell[][] cell , int x , int y, Color[] color) {
            try {


                if (cell[x][y - 1].getColor().equals(color[1])) {

                    return leftCheckOfBlackCellGR(cell, x, y - 1, color);
                } else if (cell[x][y].getColor().equals(color[0])) {

                    return false;

                }
            }catch (Exception e){

            }
        return  false;
    }

    private boolean downOfBlackCellGRA(Cell[][] cell , int x , int y, Color[] color) {
        try {


            if (cell[x + 1][y].getColor().equals(color[1])) {

                return downCheckOfBlackCellGR(cell, x + 1, y, color);
            } else if (cell[x][y].getColor().equals(color[0])) {

                return false;

            }
        }catch (Exception e){

        }
        return  false;
    }

    //******************
    private boolean RRUOfBlackCellGRA(Cell[][] cell , int x , int y, Color[] color) {
        try {
                    //  y = y-1;
            //            for (x = x -1; x >=0&&y>=0; x-- , y--)

            if (cell[x - 1][y-1].getColor().equals(color[1])) {

                return RRUCheckOfBlackCellGR(cell, x - 1, y-1, color);
            } else if (cell[x][y].getColor().equals(color[0])) {

                return false;

            }
        }catch (Exception e){

        }
        return  false;
    }

    //x = x - 1; x >= 0&&y<=8; x-- , y++
    private boolean URRUOfBlackCellGRA(Cell[][] cell , int x , int y, Color[] color) {
        try {

            if (cell[x - 1][y+1].getColor().equals(color[1])) {

                return URRUCheckOfBlackCellGR(cell, x - 1, y+1, color);
            } else if (cell[x][y].getColor().equals(color[0])) {

                return false;

            }
        }catch (Exception e){

        }
        return  false;
    }
//x = x + 1; x <=8&&y>=0; x++ , y--
    private boolean URRDOfBlackCellGRA(Cell[][] cell , int x , int y, Color[] color) {
        try {

            if (cell[x +1][y-1].getColor().equals(color[1])) {

                return URRDCheckOfBlackCellGR(cell, x + 1, y-1, color);
            } else if (cell[x][y].getColor().equals(color[0])) {

                return false;

            }
        }catch (Exception e){

        }
        return  false;
    }

    private boolean RRDOfBlackCellGRA(Cell[][] cell , int x , int y,Color[] color) {
        try {
            //  y = y-1;
            //            for (x = x -1; x >=0&&y>=0; x-- , y--)

            if (cell[x +1][y+1].getColor().equals(color[1])) {

                return RRDCheckOfBlackCellGR(cell, x + 1, y+1, color);
            } else if (cell[x][y].getColor().equals(color[0])) {

                return false;

            }
        }catch (Exception e){

        }
        return  false;
    }
    private boolean UpOfBlackCellGRA(Cell[][] cell , int x , int y, Color[] color) {

        try {

            if (cell[x - 1][y].getColor().equals(color[1])) {

                return UpCheckOfBlackCellGR(cell, x - 1, y, color);
            } else if (cell[x][y].getColor().equals(color[0])) {

                return false;

            }
        }catch (Exception e){

        }

        return  false;
    }
    private boolean leftOfBlackCell(Cell [][] cell , int x , int y  , Color [] color){
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
    private boolean UPOfBlackCell(Cell [][] cell , int x , int y , Color [] color){
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


    private boolean DownOfBlackCell(Cell [][] cell , int x , int y , Color [] color){
        if (x == 7)
            return false;
        else {
            for (x = x +1; x <=row; x++) {
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
    private boolean URealRUOfBlackCell(Cell [][] cell , int x , int y , Color [] color ){
        if (x == 7 || y==7)
            return false;
        else {
             y = y+1;
            for (x = x -1; x >=0&&y<=column; x-- , y++) {



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

    private boolean RealRUOfBlackCell(Cell [][] cell , int x , int y , Color [] color){
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

    private boolean RealRDOfBlackCell(Cell [][] cell , int x , int y , Color [] color){
        if (x == 7 || y==7)
            return false;
        else {
            y = y+1;
            for (x = x +1; x <=row&&y<=column; x++ , y++) {



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

    private boolean URealRDOfBlackCell(Cell [][] cell , int x , int y , Color[] color){
        if (x == 7 || y==7)
            return false;
        else {
            y = y-1;
            for (x = x +1; x <row&&y>=0; x++ , y--) {



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
    private boolean rightCheckOfBlackCellGR(Cell[][] cells , int x , int y , Color [] color) {
        for (y = y + 1; y < column; y++) {

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

    private boolean leftCheckOfBlackCellGR(Cell[][] cells , int x , int y , Color [] color) {
        for (y = y - 1; y >=0; y--) {

            if (cells[x][y].getColor().equals(color[1]))
                continue;
//
            else if (cells[x][y].getColor().equals(color[0])) {
                xEnd = x;
                yEnd = y;
                return true;

            }



        }

        return false;
    }
    private boolean downCheckOfBlackCellGR(Cell[][] cells , int x , int y , Color [] color) {
        for (x =x+1 ; x<row; x++) {

            if (cells[x][y].getColor().equals(color[1]))
                continue;
//
            else if (cells[x][y].getColor().equals(color[0])) {
                xEndD = x;
                yEndD = y;
                return true;

            }



        }

        return false;
    }

    // y = y-1;
    //            //            for (x = x -1; x >=0&&y>=0; x-- , y--)
    private boolean RRUCheckOfBlackCellGR(Cell[][] cells , int x , int y , Color [] color) {
        for (x =x-1 , y = y-1; x>=0 && y>=0; x-- , y--) {

            if (cells[x][y].getColor().equals(color[1]))
                continue;
//
            else if (cells[x][y].getColor().equals(color[0])) {
                xEndRRU = x;
                yEndRRU = y;
                return true;

            }



        }

        return false;
    }
    private boolean URRUCheckOfBlackCellGR(Cell[][] cells , int x , int y , Color[] color) {
        for (x =x-1 , y = y+1; x>=0 && y<=column; x-- , y++) {

            if (cells[x][y].getColor().equals(color[1]))
                continue;
//
            else if (cells[x][y].getColor().equals(color[0])) {
                xEndRU = x;
                yEndRU = y;
                return true;

            }



        }

        return false;
    }

    private boolean URRDCheckOfBlackCellGR(Cell[][] cells , int x , int y , Color [] color) {
        for (x =x+1 , y = y-1; x<=row && y>=0; x++ , y--) {

            if (cells[x][y].getColor().equals(color[1]))
                continue;
//
            else if (cells[x][y].getColor().equals(color[0])) {
                xEndRD = x;
                yEndRD = y;
                return true;

            }



        }

        return false;
    }


    private boolean RRDCheckOfBlackCellGR(Cell[][] cells , int x , int y , Color [] color) {
        for (x =x+1 , y = y+1; x>=0 && y>=0; x++ , y++) {

            if (cells[x][y].getColor().equals(color[1]))
                continue;
            else if (cells[x][y].getColor().equals(color[0])) {
                xEndRRD = x;
                yEndRRD = y;
                return true;

            }



        }

        return false;
    }

    private boolean UpCheckOfBlackCellGR(Cell[][] cells , int x , int y ,Color [] color) {
        for (x =x-1 ; x>=0; x--) {

            if (cells[x][y].getColor().equals(color[1]))
                continue;
            else if (cells[x][y].getColor().equals(color[0])) {
                xEndU = x;
                yEndU = y;
                return true;

            }



        }

        return false;
    }
    private boolean rightCheckOfBlackCell(Cell[][] cells , int x , int y , Color[] color){
        for (y = y + 1; y < column; y++){
            if (!cells[x][y].getColor().equals(Color.GREEN)) {
                if (cells[x][y].getColor().equals(color[1]))
                    continue;
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

    private boolean leftCheckOfBlackCell(Cell[][] cells , int x , int y , Color[] color){
        for (y = y-1; y >=0; y--){
            if (!cells[x][y].getColor().equals(Color.GREEN)) {
                if (cells[x][y].getColor().equals(color[1]))
                    continue;
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
    private boolean UPCheckOfBlackCell(Cell[][] cells , int x , int y  ,Color [] color){
        for (x = x-1; x>=0 ; x--){
            try {
                if (!cells[x][y].getColor().equals(Color.GREEN)) {
                    if (cells[x][y].getColor().equals(color[1]))
                        continue;
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

    private boolean DCheckOfBlackCell(Cell[][] cells , int x , int y, Color [] color){
        for (x = x+1; x<=row ; x++){
            try {
                if (!cells[x][y].getColor().equals(Color.GREEN)) {
                    if (cells[x][y].getColor().equals(color[1]))
                        continue;
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


    private boolean URealRUCheckOfBlackCell(Cell[][] cells , int x , int y , Color[] color) {
        y = y+1;
        for (x = x - 1; x >= 0&&y<=column; x-- , y++) {

                try {


                    if (!cells[x][y].getColor().equals(Color.GREEN)) {
                        if (cells[x][y].getColor().equals(color[1]))
                            continue;
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


    private boolean RealRUCheckOfBlackCell(Cell[][] cells , int x , int y , Color [] color) {
        y = y-1;
        for (x = x - 1; x >= 0&&y>=0; x-- , y--) {

           try {
               if (!cells[x][y].getColor().equals(Color.GREEN)) {
                   if (cells[x][y].getColor().equals(color[1]))
                       continue;
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

    private boolean RealRDCheckOfBlackCell(Cell[][] cells , int x , int y , Color [] color) {
        y = y+1;
        for (x = x + 1; x <=row&&y<=8; x++ , y++) {


            try {
                if (!cells[x][y].getColor().equals(Color.GREEN)) {
                    if (cells[x][y].getColor().equals(color[1]))
                        continue;
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

    private boolean URealRDCheckOfBlackCell(Cell[][] cells , int x , int y , Color [] color) {
        y = y-1;
        for (x = x + 1; x <=row&&y>=0; x++ , y--) {


            try {
                if (!cells[x][y].getColor().equals(Color.GREEN)) {
                    if (cells[x][y].getColor().equals(color[1]))
                        continue;
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



  /*  private void callSetOnActionButton(Cell[][] cells , int xStart , int yStart , String[] color){
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

*/
}
enum Turn { BLACK , WHITE}
