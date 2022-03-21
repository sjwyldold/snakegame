package com.example.new1;

import android.drm.DrmStore;
import android.util.Log;

public class SnakeElement {

    private int body_len;
    private int body_x[];
    private int body_y[];
    private boolean eat;

    public char head_symbol;
    public char body_symbol;
    private Direction direction;

    public enum Direction{
        LEFT, RIGHT, UP, DOWN
    }


    public SnakeElement(char symbol, int xStartingLocation, int yStartingLocation) {

        this.head_symbol = 'h';
        this.body_symbol = 'b';
        this.direction = Direction.RIGHT;

        this.body_x = new int[50];
        this.body_y = new int[50];
        this.body_len = 1;
        this.body_x[0] = xStartingLocation;
        this.body_y[0] = yStartingLocation;
        this.eat = false;
    }

    public int getHeadLocX(){
        return body_x[0];
    }

    public int getHeadLocY(){
        return body_y[0];
    }

    public void eat_apple() {
        this.eat = true;
    }

    public boolean conflict_body() {
        int x = this.body_x[0];
        int y = this.body_y[0];

        return isBody(x,y);
    }

    public boolean isHead(int x, int y) {
        if ((x == body_x[0]) && (y == body_y[0])) {
            return true;
        }
        return false;
    }

    public boolean isBody(int x, int y) {
        for(int i=1;i<this.body_len;i++) {
            if ((x == body_x[i]) && (y==body_y[i])) {
                return true;
            }
        }
        return false;
    }

    public Direction getDirection(){
        return direction;
    }

    public void changeDirection(Direction new_direction){
        switch(new_direction){
            case LEFT:
                if (direction == Direction.RIGHT){
                    return;
                }
                direction = Direction.LEFT;
                break;
            case RIGHT:
                if (direction == Direction.LEFT){
                    return;
                }
                direction = Direction.RIGHT;
                break;
            case UP:
                if (direction == Direction.DOWN){
                    return;
                }
                direction = Direction.UP;
                break;
            case DOWN:
                if (direction == Direction.UP){
                    return;
                }
                direction = Direction.DOWN;
                break;
        }
    }

    public void move(Board screen){
        clearSnakeOnScreen(screen);

        if (this.eat == true){
            this.body_len += 1;
            this.eat = false;
        }

        int[] new_location_x = new int[this.body_len];
        int[] new_location_y = new int[this.body_len];

        switch (direction){
            case RIGHT:
                new_location_x[0] = this.body_x[0] + 1;
                new_location_y[0] = this.body_y[0];
                break;

            case LEFT:
                new_location_x[0] = this.body_x[0] - 1;
                new_location_y[0] = this.body_y[0];
                break;

            case UP:
                new_location_x[0] = this.body_x[0];
                new_location_y[0] = this.body_y[0] - 1;
                break;

            case DOWN:
                new_location_x[0] = this.body_x[0];
                new_location_y[0] = this.body_y[0] + 1;
                break;
        }

        for (int i=1;i<this.body_len;i++){
            new_location_x[i] = this.body_x[i-1];
            new_location_y[i] = this.body_y[i-1];
        }

        this.body_x = new_location_x;
        this.body_y = new_location_y;

        drawSnakeOnScreen(screen);

    }

    public void clearSnakeOnScreen(Board screen){
        for (int i=0;i<body_len;i++){
            screen.ClearScreenLocation(body_x[i],body_y[i]);
        }
    }

    public void drawSnakeOnScreen(Board screen){
        for (int i=0;i<this.body_len;i++){
            if (i==0){
                screen.setObjectOnLocation(this.head_symbol, this.body_x[i], this.body_y[i]);
            }
            else{
                screen.setObjectOnLocation(this.body_symbol, this.body_x[i], this.body_y[i]);
            }
        }
    }
}