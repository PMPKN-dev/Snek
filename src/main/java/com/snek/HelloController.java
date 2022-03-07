package com.snek;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class HelloController {

    @FXML
    Canvas canvas;

    @FXML
    private Text txScore;


    private void setTxScore(int no){
        txScore.setText(Integer.toString(no));
    }




    @FXML
    private void initialize(){

    }
}