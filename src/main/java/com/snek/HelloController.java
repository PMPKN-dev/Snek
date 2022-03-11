package com.snek;

import com.snek.game.Board;
import com.snek.game.Main;
import com.snek.game.Snek;
import com.snek.game.Square;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.Timer;
import java.util.TimerTask;

public class HelloController {

    @FXML
    Canvas canvas;

    @FXML
    private Text txScore;

    @FXML
    AnchorPane anchorPane;

    Snek snek;
    int movementOffset = 19;
    int moveX = 19;
    int moveY = 0;
    int movementFrequency = 2000;

    void setTxScore(int no){
        txScore.setText(Integer.toString(no));
    }

    @FXML
    Button button = new Button();

    //draws the background board
    @FXML
    public void drawBoard(){

        Board board = Main.getBoard();
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.GRAY);

        Square[][] squares = board.getSquares();

        for (int i = 0; i< board.getAmountOfFields(); i++){
            for(int j = 0; j<board.getAmountOfFields(); j++){
                Square square= squares[i][j];
                gc.setFill(square.getColor());
                gc.fillRect(square.getX(),square.getY(),square.getSize(),square.getSize());

            }
        }

    }

    //loads in the snake/rectangle and puts it in the center
    public void spawnSnake(){
        snek = new Snek(20);

        Rectangle snake = snek.getSnake();
        anchorPane.getChildren().add(snake);
        snek.moveSnake(190,190);
    }

    private void drawMisc(){

        //will move the snake in the interval movementFrequency by reading current pos and adding/removing the moveX and moveY
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                int[] snekPos = snek.getPos();
                snek.moveSnake(snekPos[0]+moveX,snekPos[1]+moveY);
            }
        },0,movementFrequency);

        //creates the button. the button does nothing
        button.setLayoutX(100);
        button.setLayoutY(450);
        button.setText("Do Something!");
        anchorPane.getChildren().add(button);
    }

    @FXML
    private void initialize(){
        spawnSnake();
        drawBoard();
        drawMisc();
    }
}