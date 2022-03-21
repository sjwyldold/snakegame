package com.example.new1;

import android.drm.DrmStore;

public class SnakeElement {

    private int body_len;
    private int body_x[];
    private int body_y[];

    public char head_symbol;
    public char body_symbol;
    private boolean eat;
    private Direction direction;

    public enum Direction{
        LEFT, RIGHT, UP, DOWN
    }


    public SnakeElement(char symbol, int xStartingLocation, int yStartingLocation) {
        this.body_len = 1;
        this.body_x = new int[50];
        this.body_y = new int[50];

        this.head_symbol = 'h';
        this.body_symbol = 'b';
        this.eat = false;
        this.direction = Direction.RIGHT;
        this.body_x[0] = xStartingLocation;
        this.body_y[0] = yStartingLocation;
    }

    public int getHeadLocX(){
        return body_x[0];
    }

    public int getHeadLocY(){
        return body_y[0];
    }

    public void eat_apple() {
        this.body_len += 1;
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

    public void moveLeft(Board screen, SnakeElement snake) {
        // check direction
        if (snake.direction == Direction.RIGHT) {
            return;
        }
        snake.direction = Direction.LEFT;

        int[] new_location_x = new int[snake.body_len];
        int[] new_location_y = new int[snake.body_len];

        new_location_x[0] = snake.body_x[0] - 1;
        new_location_y[0] = snake.body_y[0];
        screen.setObjectOnLocation(snake.head_symbol, new_location_x[0], new_location_y[0]);

        for(int i=1;i<snake.body_len;i++) {
            new_location_x[i] = snake.body_x[i-1];
            new_location_y[i] = snake.body_y[i-1];
            screen.setObjectOnLocation(snake.body_symbol, new_location_x[i], new_location_y[i]);
        }

        if (this.eat == true) {
            this.eat = false;
        }
        else {
            screen.ClearScreenLocation(snake.body_x[snake.body_len-1], snake.body_y[snake.body_len-1]);
        }
        this.body_x = new_location_x;
        this.body_y = new_location_y;
    }

    public void moveRight(Board screen, SnakeElement snake) {
        // check direction
        if (snake.direction == Direction.LEFT) {
            return;
        }
        snake.direction = Direction.RIGHT;

        int[] new_location_x = new int[snake.body_len];
        int[] new_location_y = new int[snake.body_len];

        new_location_x[0] = snake.body_x[0] + 1;
        new_location_y[0] = snake.body_y[0];
        screen.setObjectOnLocation(snake.head_symbol, new_location_x[0], new_location_y[0]);

        for(int i=1;i<snake.body_len;i++) {
            new_location_x[i] = snake.body_x[i-1];
            new_location_y[i] = snake.body_y[i-1];
            screen.setObjectOnLocation(snake.body_symbol, new_location_x[i], new_location_y[i]);
        }

        if (this.eat == true) {
            this.eat = false;
        }
        else {
            screen.ClearScreenLocation(snake.body_x[snake.body_len-1], snake.body_y[snake.body_len-1]);
        }
        this.body_x = new_location_x;
        this.body_y = new_location_y;
    }

    public void moveUp(Board screen, SnakeElement snake) {
        // check direction
        if (snake.direction == Direction.DOWN) {
            return;
        }
        snake.direction = Direction.UP;

        int[] new_location_x = new int[snake.body_len];
        int[] new_location_y = new int[snake.body_len];

        new_location_x[0] = snake.body_x[0];
        new_location_y[0] = snake.body_y[0] - 1;
        screen.setObjectOnLocation(snake.head_symbol, new_location_x[0], new_location_y[0]);

        for(int i=1;i<snake.body_len;i++) {
            new_location_x[i] = snake.body_x[i-1];
            new_location_y[i] = snake.body_y[i-1];
            screen.setObjectOnLocation(snake.body_symbol, new_location_x[i], new_location_y[i]);
        }

        if (this.eat == true) {
            this.eat = false;
        }
        else {
            screen.ClearScreenLocation(snake.body_x[snake.body_len-1], snake.body_y[snake.body_len-1]);
        }
        this.body_x = new_location_x;
        this.body_y = new_location_y;
    }

    public void moveDown(Board screen, SnakeElement snake) {
        // check direction
        if (snake.direction == Direction.UP) {
            return;
        }
        snake.direction = Direction.DOWN;

        int[] new_location_x = new int[snake.body_len];
        int[] new_location_y = new int[snake.body_len];

        new_location_x[0] = snake.body_x[0];
        new_location_y[0] = snake.body_y[0] + 1;
        screen.setObjectOnLocation(snake.head_symbol, new_location_x[0], new_location_y[0]);

        for(int i=1;i<snake.body_len;i++) {
            new_location_x[i] = snake.body_x[i-1];
            new_location_y[i] = snake.body_y[i-1];
            screen.setObjectOnLocation(snake.body_symbol, new_location_x[i], new_location_y[i]);
        }

        if (this.eat == true) {
            this.eat = false;
        }
        else {
            screen.ClearScreenLocation(snake.body_x[snake.body_len-1], snake.body_y[snake.body_len-1]);
        }
        this.body_x = new_location_x;
        this.body_y = new_location_y;
    }
}