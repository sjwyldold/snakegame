package com.example.new1;

import android.widget.Toast;

public class GameEngine {

    private final int BOARD_WIDTH = 15;
    private final int BOARD_HEIGHT = 15;
    private final int START_X = BOARD_WIDTH / 2;
    private final int START_Y = BOARD_HEIGHT / 2;

    private RoomWall wall;
    private SnakeElement snake;
    private AppleElement apple;
    private int score;
    private Board board;
    private GameStatus gameStatus;
    private boolean pushed;

    public enum GameStatus {
        RUN, FIN, STOP
    }


    public GameStatus getGameStatus(){
        return this.gameStatus;
    }

    public void run_game(){
        if (this.gameStatus == GameStatus.FIN){
            return;
        }
        this.pushed = false;
        update_game();
    }

    public void stop_game(){
        this.gameStatus = GameStatus.STOP;
        remove_game();
    }

    public void finish_game(){
        this.gameStatus = GameStatus.FIN;
        remove_game();
    }

    public void remove_game(){
        this.score = 0;
        this.board = new Board(BOARD_WIDTH, BOARD_HEIGHT);
        this.board.initBoard();
    }

    public void init_game() {

        this.score = 0;
        this.gameStatus = GameStatus.RUN;

        this.board = new Board(BOARD_WIDTH, BOARD_HEIGHT);
        board.initBoard();

        this.wall = new RoomWall('w');
        this.wall.addRoomWallRow(this.board, this.wall, 0);
        this.wall.addRoomWallRow(this.board, this.wall, this.board.getBoardHeight() - 1);
        this.wall.addRoomWallColumn(this.board, this.wall, 0);
        this.wall.addRoomWallColumn(this.board, this.wall, this.board.getBoardWidth() - 1);

        this.snake = new SnakeElement('b', START_X, START_Y);
        this.board.setObjectOnLocation(this.snake.head_symbol, this.snake.getHeadLocX(), this.snake.getHeadLocY());

        this.apple = new AppleElement('a');
        this.apple.addRandomApple(this.board, this.apple, this.snake);
    }

    public void change_snake_direction(SnakeElement.Direction direction){
        if (this.pushed == true){
            return;
        }
        this.snake.changeDirection(direction);
        this.pushed = true;
    }

    public void update_game() {

        this.snake.move(this.board);

        // eat
        if (check_eat(this.snake, this.apple)) {
            this.apple.addRandomApple(this.board, this.apple, this.snake);
            this.snake.eat_apple();
            this.score += 10;
        }
        // not eat
        else {
            if (check_conflict_wall(snake, board) || check_conflict_body(snake)) {
                finish_game();
            }
        }
    }

    public static boolean check_eat(SnakeElement snake, AppleElement apple) {
        if ((snake.getHeadLocX() == apple.getX()) && (snake.getHeadLocY() == apple.getY())) {
            return true;
        }
        else {
            return false;
        }
    }

    public static boolean check_conflict_wall(SnakeElement snake, Board board) {
        if ((snake.getHeadLocX() == 0) || (snake.getHeadLocX() == (board.getBoardWidth() - 1)) ||
                (snake.getHeadLocY() == 0) || (snake.getHeadLocY() == (board.getBoardHeight() -1))) {
            return true;
        }
        else {
            return false;
        }
    }

    public static boolean check_conflict_body(SnakeElement snake) {
        return snake.conflict_body();
    }

    public char[][] getBoard(){
        return this.board.getBoardMatrix();
    }

    public int getScore(){
        return score;
    }
}