package com.snek.game;

import javafx.scene.shape.Rectangle;

public class Food {
    int size;
    long time;
    int[] pos= new int[2];
    Rectangle rectangle = new Rectangle();

    public Food (int size){
        time = System.currentTimeMillis();
        this.size= size;
    }
    public Rectangle getFood (){
        rectangle.setWidth(size);
        rectangle.setHeight(size);
        return rectangle;
    }

    public long getTime(){
        return time;
    }

    public int[] getPos(){
        pos[0]= (int) rectangle.getX();
        pos[1]= (int) rectangle.getY();
        return pos;
    }
}
