package controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Cell;
import model.User;

import java.io.IOException;
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

    public  static Turn turn = Turn.BLACK;

    static boolean endOfGame = false;

    private User user1 = LoginPageController.user1;
    private  User user2 = LoginPageController.user2;

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
     private Label pointOfWhite , pointOfBlack, label;

    @FXML
    private VBox playGround;
    @FXML
    private HBox hbX;

    @FXML
    private MenuItem gameGuide ,exit , Music3 , Music2,stop ,Music1, about,scoreboard;

     @FXML
     private MenuBar menuBar;

    @FXML
    private MediaView mediaView;

    @FXML
    private Circle redW , redB;

    @FXML
    private Label whiteLBL;

    @FXML
    private Label blackLBL;

    static MediaPlayer mediaPlayer;


    @FXML
    private  void plyMusicOne(ActionEvent event){
        Media media = new Media(
                "file:/C:/Users/NP/Desktop/Assassin_s_Creed_IV_Black_Flag_Assassin_s_Creed_IV_Blac.hd_.mp3");
        if (mediaPlayer!=null){
            mediaPlayer.stop();
        }
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();

    }

    @FXML
    private void ml(MouseEvent event){menuBar.setStyle("-fx-background-color: gray");}

    @FXML
    private void mE(MouseEvent event){menuBar.setStyle("-fx-background-color:#cccccc ");}

    @FXML
    private  void playMusicTwo(ActionEvent event){
        Media media = new Media(
                "file:/C:/Users/NP/Desktop/Epilogue_The_Legend_of_Zelda_Breath_of_the_Wild_OST_.hd_.mp3");
        if (mediaPlayer!=null){mediaPlayer.stop();}
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
        mediaPlayer.play();

    }

    @FXML
    private  void playMusic3(){
        Media media = new Media(
                "file:/C:/Users/NP/Desktop/Moon_Beach_Streets_of_Rage_1_Original_Soundtrack_OST_.hd_.mp3");
        if (mediaPlayer!=null){mediaPlayer.stop();}
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
        mediaPlayer.play();

    }

    @FXML
    private void aboutA(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("../view/About.fxml"));
        fxmlLoader.load();
        Scene scene = new Scene(fxmlLoader.getRoot());
        Stage stage = new Stage();
        Image icon  = new Image("images/imgbin_circle-png.png");
        stage.getIcons().add(icon);
        stage.setTitle("Othello ~Designed by : SaraNikMehr & MohammadHMazarei~");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }

    @FXML
    private void stopF(ActionEvent event){if (mediaPlayer!=null){mediaPlayer.stop();}}

    static Cell[][] thisCell;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        whiteLBL.setText(user2.getUserName() + " :");
        blackLBL.setText(user1.getUserName() + " :");

       label.setText("REVERSI");

        setUpButtons();

        labelW();

        myAnimation();

        hbX.getChildren().addAll(othello);

        tableButtons();

        scoreboardOnAction();

        gameGuideOnAction();

        Color[] color  = new Color[2];
        color[0] = Color.BLACK;
        color[1] = Color.WHITE;

        Color[] color1  = new Color[2];
        color1[0] = Color.WHITE;
        color1[1] = Color.BLACK;
          exitGamePage();

            if (turn.equals(Turn.WHITE)) {coloredTheGrayBTN(color1);
                redB.setVisible(false);
                redW.setVisible(true);
                this.pointOfWhite.setText(String.valueOf(numOfButton(Color.WHITE)));
                user2.setPoint(numOfButton(Color.WHITE));
            }
            else {
                coloredTheGrayBTN(color);
                redB.setVisible(true);
                redW.setVisible(false);
                this.pointOfBlack.setText(String.valueOf(numOfButton(Color.BLACK)));
                user1.setPoint(numOfButton(Color.BLACK));
            }

    }


    private void gameGuideOnAction(){
        gameGuide.setOnAction(e ->{
            FXMLLoader louder = new FXMLLoader(this.getClass().getResource("../view/GameGuide.fxml"));

            try {
                louder.load();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

            Scene scene = new Scene(louder.getRoot());
            Stage stage = new Stage();
            Image icon  = new Image("images/imgbin_circle-png.png");
            stage.getIcons().add(icon);
            stage.setTitle("Othello ~Designed by : SaraNikMehr & MohammadHMazarei~");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        });
    }


    private void scoreboardOnAction(){
        scoreboard.setOnAction(e -> {
            Stage scoreboardStage = ((Stage) label.getScene().getWindow());

            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../view/Scoreboard.fxml"));

            try {
                loader.load();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            Image image = new Image("/images/imgbin_circle-png.png");
            scoreboardStage.getIcons().add(image);
            scoreboardStage.setScene(new Scene(loader.getRoot()));
            scoreboardStage.setTitle("Othello ~Designed by : SaraNikMehr & MohammadHMazarei~");
            scoreboardStage.resizableProperty().setValue(Boolean.FALSE);
            scoreboardStage.show();
            scoreboardOnAction((ScoreboardController) loader.getController());
        });
    }

    private void scoreboardOnAction(ScoreboardController scoreboardController){
        scoreboardController.getBackBTN().setOnAction(e ->{
            Stage stage = ((Stage)scoreboardController.getBackBTN().getScene().getWindow());

            stage.setScene(label.getScene());
            stage.show();
        });
    }


    private void labelW() {
        label.setTextAlignment(TextAlignment.CENTER);
        label.setStyle("-fx-background-color: transparent");
        label.setFont(Font.font("Algerian" , 80) );
    }


    private void exitGamePage(){

        exit.setOnAction(event1 -> {
            ((Stage) playGround.getScene().getWindow()).close();
        });

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

//     private boolean setEnd(){
//
//
//     }


    private void moveInAllButtons(Color [] color){

        for (int i = 0; i < row; i++) {

            for (int j = 0; j < column; j++) {

                if (thisCell[i][j].isBlack()&&turn.equals(Turn.BLACK)) {
                    boolean[] result = searchAllBTN(color, i, j);

                    if (result[0]) {grayBTN(xEndR , yEndR); }

                    if (result[1]) { grayBTN(xEnd , yEnd);}

                    if (result[2]) {grayBTN(xEndU , yEndU);}

                    if (result[3]) {grayBTN(xEndD , yEndD);}

                    if (result[4]) {grayBTN(xEndRU , yEndRU);}

                    if (result[5]) {grayBTN(xEndRD , yEndRD);}

                    if (result[6]) {grayBTN(xEndRRU, yEndRRU);}

                    if (result[7]) {grayBTN(xEndRRD ,yEndRRD );}

                }

            }
        }

    }

  private  void grayBTN(int x , int y){
      thisCell[x][y].setColor(Color.GRAY);
      thisCell[x][y].setSelectable(true);
      thisCell[x][y].setBlack(false);
      thisCell[x][y].setWhite(false);

  }

    private void mvm(Color [] color ){

        for (int i = 0; i < row; i++) {

            for (int j = 0; j < column; j++) {

                if (thisCell[i][j].isWhite()) {
                    boolean[] result = searchAllBTN(color, i, j);
                    if (result[0]) {grayBTN(xEndR , yEndR);}

                    if (result[1]) {grayBTN(xEnd , yEnd);}

                    if (result[2]) {grayBTN(xEndU , yEndU);}

                    if (result[3]) {grayBTN(xEndD , yEndD);}

                    if (result[4]) {grayBTN(xEndRU , yEndRU);}

                    if (result[5]) {grayBTN(xEndRD , yEndRD);}

                    if (result[6]) {grayBTN(xEndRRU, yEndRRU);}

                    if (result[7]) {grayBTN(xEndRRD ,yEndRRD );}

                }
            }

            }
    }

    private  void  coloredTheGrayBTN(Color[] color){

        if (turn.equals(Turn.BLACK)) {moveInAllButtons(color);}
        else
        {
            mvm(color);
        }

        int count = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (thisCell[i][j].isSelectable() && thisCell[i][j].getColor().equals(Color.GRAY)){
                    onActionForGrayBTN(thisCell[i][j] , color);


                    count++;
                }
            }
        }

        pointOfWhite.setText(String.valueOf(numOfButton(Color.WHITE)));
        user2.setPoint(numOfButton(Color.WHITE));
        pointOfBlack.setText(String.valueOf(numOfButton(Color.BLACK)));
        user1.setPoint(numOfButton(Color.BLACK));
        if (turn.equals(Turn.WHITE)){
            redW.setVisible(true);
            redB.setVisible(false);
        }else if (turn.equals(Turn.BLACK)){
            redW.setVisible(false);
            redB.setVisible(true);
        }


        if (count == 0){
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


    }


    private boolean[] foundRange(Cell cell , Color [] color){

        boolean [] result = new boolean[8];

        result[0] = rightOfBlackCellGRA(thisCell , cell.getxPosition() , cell.getyPosition() , color);
        result[1] = leftOfBlackCellGRA(thisCell , cell.getxPosition() , cell.getyPosition() , color);
        result[2] = downOfBlackCellGRA(thisCell , cell.getxPosition() , cell.getyPosition() , color);
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

    private  int numOfButton(Color color){
        int count =0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {

                if (thisCell[i][j].getColor().equals(color)){
                    ++count;
                }

            }
        }



        return count;
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

                    if (result[2]){

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

            for (y = y +1; y < column; y++) {
                if (cell[x][y].getColor().equals(color[1])){
                    return rightCheckOfBlackCell(cell , x , y , color);
                }
                else if (cell[x][y].getColor().equals(color[0]))
                    return false;
                else
                    return false;
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
            if (cell[x - 1][y-1].getColor().equals(color[1])) {

                return RRUCheckOfBlackCellGR(cell, x - 1, y-1, color);
            } else if (cell[x][y].getColor().equals(color[0])) {

                return false;

            }
        }catch (Exception e){

        }
        return  false;
    }


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



                for (y = y - 1; y >= 0; y--) {
                    try {
                        if (cell[x][y].getColor().equals(color[1])) {
                            return leftCheckOfBlackCell(cell, x, y, color);
                        } else if (cell[x][y].getColor().equals(color[0]))
                            return false;
                        else
                            return false;
                    }catch (Exception ignore){

                    }
                }


        return false;
    }

    // check Up of Black btn
    private boolean UPOfBlackCell(Cell [][] cell , int x , int y , Color [] color){


                for (x = x - 1; x >= 0; x--) {
                    try {
                        if (cell[x][y].getColor().equals(color[1])) {
                            return UPCheckOfBlackCell(cell, x, y, color);
                        } else if (cell[x][y].getColor().equals(color[0]))
                            return false;
                        else
                            return false;
                    }catch (Exception ignore){

                    }
                }

        return false;

    }


    private boolean DownOfBlackCell(Cell [][] cell , int x , int y , Color [] color){


               for (x = x + 1; x <= row; x++) {
                   try {
                       if (cell[x][y].getColor().equals(color[1])) {
                           return DCheckOfBlackCell(cell, x, y, color);
                       } else if (cell[x][y].getColor().equals(color[0]))
                           return false;
                       else
                           return false;
                   }catch (Exception ignore){

                   }
               }


        return false;

    }
    private boolean URealRUOfBlackCell(Cell [][] cell , int x , int y , Color [] color ){


            y = y + 1;
            for (x = x - 1; x >= 0 && y <= column; x--, y++) {

                try {
                    if (cell[x][y].getColor().equals(color[1])) {
                        return URealRUCheckOfBlackCell(cell, x, y, color);
                    } else if (cell[x][y].getColor().equals(color[0]))
                        return false;
                    else {
                        return false;
                    }
                }catch (Exception ignore){

                }

            }

        return false;

    }

    private boolean RealRUOfBlackCell(Cell [][] cell , int x , int y , Color [] color){


            y = y - 1;
            for (x = x - 1; x >= 0 && y >= 0; x--, y--) {

                try {
                    if (cell[x][y].getColor().equals(color[1])) {
                        return RealRUCheckOfBlackCell(cell, x, y, color);
                    } else if (cell[x][y].getColor().equals(color[0]))
                        return false;
                    else {
                        return false;
                    }
                }catch (Exception ignore){

                }

            }

        return false;

    }

    private boolean RealRDOfBlackCell(Cell [][] cell , int x , int y , Color [] color){


            y = y + 1;
            for (x = x + 1; x <= row && y <= column; x++, y++) {

                try {
                    if (cell[x][y].getColor().equals(color[1])) {
                        return RealRDCheckOfBlackCell(cell, x, y, color);
                    } else if (cell[x][y].getColor().equals(color[0]))
                        return false;
                    else {
                        return false;
                    }
                }catch (Exception ignore){

                }

            }

        return false;

    }

    private boolean URealRDOfBlackCell(Cell [][] cell , int x , int y , Color[] color){



            y = y - 1;
            for (x = x + 1; x <= row && y >= 0; x++, y--) {


                try {
                    if (cell[x][y].getColor().equals(color[1])) {
                        return URealRDCheckOfBlackCell(cell, x, y, color);
                    } else if (cell[x][y].getColor().equals(color[0]))
                        return false;
                    else {
                        return false;
                    }
                }catch (Exception ignore){

                }
            }

        return false;

    }
    private boolean rightCheckOfBlackCellGR(Cell[][] cells , int x , int y , Color [] color) {

            for (y = y + 1; y < column; y++) {

                try {
                    if (cells[x][y].getColor().equals(color[1]))
                        continue;
//
                    else if (cells[x][y].getColor().equals(color[0])) {
                        xEndR = x;
                        yEndR = y;
                        return true;

                    }
                }catch (Exception ignore){

                }
            }


        return false;
    }

    private boolean leftCheckOfBlackCellGR(Cell[][] cells , int x , int y , Color [] color) {

            for (y = y - 1; y >= 0; y--) {

                try {
                    if (cells[x][y].getColor().equals(color[1]))
                        continue;
//
                    else if (cells[x][y].getColor().equals(color[0])) {
                        xEnd = x;
                        yEnd = y;
                        return true;

                    }
                }catch (Exception ignore){

                }
            }


        return false;
    }
    private boolean downCheckOfBlackCellGR(Cell[][] cells , int x , int y , Color [] color) {

            for (x = x + 1; x <= row; x++) {

                try {
                    if (cells[x][y].getColor().equals(color[1]))
                        continue;
//
                    else if (cells[x][y].getColor().equals(color[0])) {
                        xEndD = x;
                        yEndD = y;
                        return true;

                    }
                }catch (Exception ignore){

                }
            }
        return false;
    }

    private boolean RRUCheckOfBlackCellGR(Cell[][] cells , int x , int y , Color [] color) {

            for (x = x - 1, y = y - 1; x >= 0 && y >= 0; x--, y--) {

                try {
                    if (cells[x][y].getColor().equals(color[1]))
                        continue;
//
                    else if (cells[x][y].getColor().equals(color[0])) {
                        xEndRRU = x;
                        yEndRRU = y;
                        return true;

                    }
                }catch (Exception ignore){

                }


            }

        return false;
    }
    private boolean URRUCheckOfBlackCellGR(Cell[][] cells , int x , int y , Color[] color) {

            for (x = x - 1, y = y + 1; x >= 0 && y <= column; x--, y++) {

                try {
                    if (cells[x][y].getColor().equals(color[1]))
                        continue;
//
                    else if (cells[x][y].getColor().equals(color[0])) {
                        xEndRU = x;
                        yEndRU = y;
                        return true;

                    }
                }catch (Exception ignore){

                }


            }


        return false;
    }

    private boolean URRDCheckOfBlackCellGR(Cell[][] cells , int x , int y , Color [] color) {


            for (x = x + 1, y = y - 1; x <= row && y >= 0; x++, y--) {

                try {
                    if (cells[x][y].getColor().equals(color[1]))
                        continue;
//
                    else if (cells[x][y].getColor().equals(color[0])) {
                        xEndRD = x;
                        yEndRD = y;
                        return true;

                    }
                }catch (Exception ignore){

                }


            }


        return false;
    }


    private boolean RRDCheckOfBlackCellGR(Cell[][] cells , int x , int y , Color [] color) {

            for (x = x + 1, y = y + 1; x >= 0 && y >= 0; x++, y++) {

                try {
                    if (cells[x][y].getColor().equals(color[1]))
                        continue;
                    else if (cells[x][y].getColor().equals(color[0])) {
                        xEndRRD = x;
                        yEndRRD = y;
                        return true;

                    }
                }catch (Exception ignore){

                }

            }

        return false;
    }

    private boolean UpCheckOfBlackCellGR(Cell[][] cells , int x , int y ,Color [] color) {

            for (x = x - 1; x >= 0; x--) {
                try {
                    if (cells[x][y].getColor().equals(color[1]))
                        continue;
                    else if (cells[x][y].getColor().equals(color[0])) {
                        xEndU = x;
                        yEndU = y;
                        return true;

                    }
                }catch (Exception ignore){

                }

            }

        return false;
    }
    private boolean rightCheckOfBlackCell(Cell[][] cells , int x , int y , Color[] color){


            for (y = y + 1; y < column; y++) {
                try {
                    if (!cells[x][y].getColor().equals(Color.GREEN)) {
                        if (cells[x][y].getColor().equals(color[1]))
                            continue;
                        else
                            return false;
                    } else {
                        xEndR = x;
                        yEndR = y;
                        return true;
                    }
                }catch (Exception ignore){

                }
            }

        return false;
    }

    private boolean leftCheckOfBlackCell(Cell[][] cells , int x , int y , Color[] color){

            for (y = y - 1; y >= 0; y--) {
                try {
                    if (!cells[x][y].getColor().equals(Color.GREEN)) {
                        if (cells[x][y].getColor().equals(color[1]))
                            continue;
                        else
                            return false;
                    } else {
                        xEnd = x;
                        yEnd = y;
                        return true;
                    }
                }catch (Exception ignore){

                }
            }

        return false;
    }

    // for up off black btn
    private boolean UPCheckOfBlackCell(Cell[][] cells , int x , int y  ,Color [] color){

            for (x = x - 1; x >= 0; x--) {
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
                } catch (Exception ignore) {

                }
            }

        return false;
    }

    private boolean DCheckOfBlackCell(Cell[][] cells , int x , int y, Color [] color){


            for (x = x + 1; x < row; x++) {
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
                } catch (Exception ignore) {

                }
            }

        return false;
    }


    private boolean URealRUCheckOfBlackCell(Cell[][] cells , int x , int y , Color[] color) {

            y = y + 1;
            for (x = x - 1; x >= 0 && y < column; x--, y++) {

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
                } catch (Exception ignore) {

                }

            }

        return false;
    }


    private boolean RealRUCheckOfBlackCell(Cell[][] cells , int x , int y , Color [] color) {

            y = y - 1;
            for (x = x - 1; x >= 0 && y >= 0; x--, y--) {

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
                } catch (Exception ignore) {

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



}
enum Turn { BLACK , WHITE}
