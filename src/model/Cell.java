package model;

import javafx.scene.control.Button;

public class Cell extends Button {
    //This class Extends button , this is button our material to make a 8*8 table
    private int xPosition;
    private int yPosition;

    private boolean isSelected;


    public Cell (int xPosition , int yPosition){
        super(" ");

        this.xPosition = xPosition;
        this.yPosition = yPosition;

        // set preHeight and preWidth
        setPrefHeight(500);
        setPrefWidth(500);
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

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
