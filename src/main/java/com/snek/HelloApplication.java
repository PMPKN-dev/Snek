package com.snek;

import com.snek.game.*;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;


import java.util.*;

import static java.lang.Integer.parseInt;

public class HelloController  {

    @FXML
    Canvas canvas;

    @FXML
    private Text txScore;

    @FXML
    AnchorPane anchorPane;

    Random random = new Random();
    Snek snek;
    Tail tail;
    int moveX = 0;
    int moveY = 0;
    int movementFrequency = 100;
    int justanotherincrementingvalue = 0;
    Food food = new Food((Main.boardSize-1)/Main.amountOfFields);


    String heading = "";

    void setTxScore(int no){
        txScore.setText(Integer.toString(no));
    }

    @FXML
    Button button = new Button();

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

    public void spawnSnake(){
        snek = new Snek((Main.boardSize-1)/Main.amountOfFields);
        Rectangle snake = snek.getSnake();
        anchorPane.getChildren().add(snake);
        snek.moveSnake(190,190);
    }

    public void spawnTail(){
        //currently i use the food rectangle as a ghetto tail just to test, the random numbers below randomize its spawnpoint on the map, although there is no check yet whether it spawns on the snake.
        //the sout is just to test if the random numbers are within range, they still need to be +1 to be perfect.
        tail = new Tail((Main.boardSize-1)/Main.amountOfFields);
        Rectangle asd = tail.getTail();
        anchorPane.getChildren().add(asd);

    }

    public void addFood(){
        food = new Food((Main.boardSize-1)/Main.amountOfFields);
        anchorPane.getChildren().add(food.getFood());
        int sizeMultiplier = (Main.boardSize-1)/Main.amountOfFields;
        food.getFood().setX(random.nextInt(Main.amountOfFields)*sizeMultiplier);
        food.getFood().setY(random.nextInt(Main.amountOfFields)*sizeMultiplier);
    }
    public void addTail(){
        int sizeMultiplier = (Main.boardSize-1)/Main.amountOfFields;
        //tail = new Tail((Main.boardSize-1)/Main.amountOfFields);
        //if(parseInt(txScore.getText())>justanotherincrementingvalue){

        //   Rectangle  = tail.getTail();

        // }
        if(parseInt(txScore.getText())>justanotherincrementingvalue) {
            tail = new Tail((Main.boardSize - 1) / Main.amountOfFields);
            Rectangle test = tail.getTail();
            anchorPane.getChildren().add(test);
            test.setX(sizeMultiplier);
            test.setY(sizeMultiplier);
            justanotherincrementingvalue++;
        }
    }


    private void Gameloop(){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                int[] snekPos = snek.getPos();
                int[] foodA = food.getPos();
                long timerman = 0;
                timerman++;
                int sizeMultiplier = (Main.boardSize-1)/Main.amountOfFields;
                if(timerman>20){
                    timerman = 0;
                    food.getFood().setX(random.nextInt(Main.amountOfFields)*sizeMultiplier);
                    food.getFood().setY(random.nextInt(Main.amountOfFields)*sizeMultiplier);
                    System.out.println(food.getFood().getX());
                    System.out.println(food.getFood().getY());
                } else if(snekPos[0]==foodA[0]&&snekPos[1]==foodA[1]){
                    food.getFood().setX(random.nextInt(Main.amountOfFields)*sizeMultiplier);
                    food.getFood().setY(random.nextInt(Main.amountOfFields)*sizeMultiplier);
                    System.out.println(food.getFood().getX());
                    System.out.println(food.getFood().getY());
                    setTxScore(parseInt(txScore.getText())+1);
                }
                //right to left
                if(snekPos[0]>(Main.boardSize-1)-(moveX*2) & heading=="right" & heading!="left") {
                    snek.moveSnake(0, snekPos[1] + moveY);
                    //left to right
                }else if (snekPos[0]==0 & heading=="left" & heading!="right"){
                    snek.moveSnake((Main.boardSize-1)+moveX, snekPos[1] + moveY);
                    //down to up
                }else if (snekPos[1]>(Main.boardSize-1)-(moveY*2) & heading=="down" & heading!="up") {
                    snek.moveSnake(snekPos[0] + moveX, 0);
                    //up to down
                }else if(snekPos[1]==0 & heading=="up" & heading!="down"){
                    snek.moveSnake(snekPos[0]+moveX, (Main.boardSize-1)+moveY);
                    //normal movement
                }else snek.moveSnake(snekPos[0] + moveX, snekPos[1] + moveY);
                for (int i = 0; i <(parseInt(txScore.getText())) ; i++) {
                    tail.moveTail(snekPos[0],snekPos[1]);
                    int[] cursed = tail.getPos();
                    tail.moveTail(cursed[0],cursed[1]);
                }
            }
        },0,movementFrequency);

        button.setLayoutX(100);
        button.setLayoutY(450);
        button.setText("the warden");
        anchorPane.getChildren().add(button);
        // cool button dont remove
    }


    @FXML
    private void initialize(){
        spawnSnake();
        addFood();
        spawnTail();
        drawBoard();
        Gameloop();



    }

    @FXML
    public void handleOnKeyPressed(KeyEvent e) {
        addTail();
        //java: an enum switch case label must be the unqualified name of an enumeration constant | so cant use switch cases
        if(e.getCode().equals(KeyCode.W)){
            moveX = 0;
            moveY = -(Main.boardSize-1)/Main.amountOfFields;
            heading = "up";
        }else if(e.getCode().equals(KeyCode.D)){
            moveX = (Main.boardSize-1)/Main.amountOfFields;
            moveY = 0;
            heading = "right";
        }else if(e.getCode().equals(KeyCode.A)){
            moveX = -(Main.boardSize-1)/Main.amountOfFields;
            moveY = 0;
            heading = "left";
        }else if(e.getCode().equals(KeyCode.S)){
            moveX = 0;
            moveY = (Main.boardSize-1)/Main.amountOfFields;
            heading = "down";
        }
    }
}
