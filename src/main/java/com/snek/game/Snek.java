package com.snek.game;

import javafx.scene.shape.Rectangle;

public class Snek {

    int size;

    Rectangle rect = new Rectangle();

    //aka. Snake.
    public Snek(int size){
        this.size =size;

    }
    //returns
    public Rectangle getSnake(){
        rect.setWidth(size);
        rect.setHeight(size);
        return rect;
    }
    //sets the position of the snake to given coordinates in pixels
    public void moveSnake(int X,int Y){
        rect.setX(X);
        rect.setY(Y);
    }

    //loads an intArray with current coordinates
    public int[] getPos(){
        int[] pos = new int[2];
        pos[0]= (int) rect.getX();
        pos[1]= (int) rect.getY();
        return pos;
    }


}
