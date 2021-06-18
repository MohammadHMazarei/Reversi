package model;

import com.jfoenix.controls.JFXButton;
import controller.Color;
import javafx.scene.control.Button;

public class Cell extends JFXButton {
    //This class Extends button , this is button our material to make a 8*8 table
    private int xPosition;
    private int yPosition;

    private  Color color;
    private boolean isBlack;
    private  boolean isWhite;
    private boolean isSelectable;


    public Cell (int xPosition , int yPosition){
        super(" ");


        this.xPosition = xPosition;
        this.yPosition = yPosition;

        // set preHeight and preWidth
        setPrefHeight(500);
        setPrefWidth(500);
        this.setColor(Color.GREEN);
        this.setStyle("-fx-background-color: green");
    }
    public Cell(){

    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;

        if (color.equals(Color.BLACK)){
            this.setStyle("-fx-background-color: black");
        }else if (color.equals(Color.WHITE)){
            this.setStyle("-fx-background-color: white");
        }else if (color.equals(Color.GRAY)){
            this.setStyle("-fx-background-color: gray");
        }else if (color.equals(Color.GREEN)){
            this.setStyle("-fx-background-color: green");
        }

    }

    public int getxPosition() {
        return xPosition;
    }

    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
    }



    public boolean isBlack() {
        return isBlack;
    }

    public void setBlack(boolean black) {
        isBlack = black;
    }

    public boolean isSelectable() {
        return isSelectable;
    }

    public void setSelectable(boolean selectable) {
        isSelectable = selectable;
    }



    public boolean isWhite() {
        return isWhite;
    }

    public void setWhite(boolean white) {
        isWhite = white;
    }
}
