package com.example.new1;

import android.drm.DrmStore;
import android.util.Log;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;


class SnakeLocation {
    public int locX;
    public int locY;

    public SnakeLocation(int x, int y){
        this.locX = x;
        this.locY = y;
    }
}

public class SnakeElement {

    private int body_len;
    private boolean eat;
    private Deque<SnakeLocation> body;

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

        body = new ArrayDeque<SnakeLocation>();
        body.add(new SnakeLocation(xStartingLocation, yStartingLocation));
        this.body_len = 1;
        this.eat = false;
    }


    public int getHeadLocX(){
        return body.getLast().locX;
    }

    public int getHeadLocY(){
        return body.getLast().locY;
    }

    public void eat_apple() {
        this.eat = true;
    }

    public boolean conflict_body() {
        int x = getHeadLocX();
        int y = getHeadLocY();

        return isBody(x,y);
    }

    public boolean isHead(int x, int y) {
        if ((x == getHeadLocX()) && (y == getHeadLocY())) {
            return true;
        }
        return false;
    }

    public boolean isBody(int x, int y) {
        Iterator<SnakeLocation> iter = this.body.descendingIterator();
        boolean isHead = true;
        while(iter.hasNext()){
            SnakeLocation tmp_location = iter.next();
            if (isHead){
                isHead = false;
                continue;
            }
            else { // body
                if ((x == tmp_location.locX) && (y == tmp_location.locY)) {
                    return true;
                }
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

        switch (direction){
            case RIGHT:
                body.add(new SnakeLocation(getHeadLocX() + 1, getHeadLocY()));
                break;

            case LEFT:
                body.add(new SnakeLocation(getHeadLocX() - 1, getHeadLocY()));
                break;

            case UP:
                body.add(new SnakeLocation(getHeadLocX(), getHeadLocY() - 1));
                break;

            case DOWN:
                body.add(new SnakeLocation(getHeadLocX(), getHeadLocY() + 1));
                break;
        }

        if (this.eat == true){
            this.eat = false;
        }
        else {
            body.remove();
        }

        drawSnakeOnScreen(screen);
    }

    public void clearSnakeOnScreen(Board screen){
        Iterator<SnakeLocation> iter = this.body.descendingIterator();
        while (iter.hasNext()){
            SnakeLocation tmp_location = iter.next();
            screen.ClearScreenLocation(tmp_location.locX, tmp_location.locY);
        }
    }

    public void drawSnakeOnScreen(Board screen){
        Iterator<SnakeLocation> iter = this.body.descendingIterator();
        boolean isHead = true;
        while(iter.hasNext()){
            SnakeLocation tmp_location = iter.next();
            if (isHead){
                isHead = false;
                screen.setObjectOnLocation(this.head_symbol, tmp_location.locX, tmp_location.locY);
            }
            else { // body
                screen.setObjectOnLocation(this.body_symbol, tmp_location.locX, tmp_location.locY);
            }
        }
    }
}