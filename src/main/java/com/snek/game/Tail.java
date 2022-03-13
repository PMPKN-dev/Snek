package com.snek.game;

import javafx.scene.shape.Rectangle;

public class Tail {
    int size;
    Rectangle rect = new Rectangle();

    public Tail(int size){
        this.size =size;
    }
    public Rectangle getTail(){
        rect.setWidth(size);
        rect.setHeight(size);
        return rect;
    }
    public void moveTail(int X, int Y){
        rect.setX(X);
        rect.setY(Y);
    }
    public int[] getPos(){
        int[] pos = new int[2];
        pos[0]= (int) rect.getX();
        pos[1]= (int) rect.getY();
        return pos;
    }


}
