package com.example.new1;

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

    public void finish_game(){
        this.score=0;
        board.initBoard();
    }

    public void init_game() {

        this.score = 0;

        this.board = new Board(BOARD_WIDTH, BOARD_HEIGHT);
        board.initBoard();

        this.wall = new RoomWall('w');
        this.wall.addRoomWallRow(this.board, this.wall, 0);
        this.wall.addRoomWallRow(this.board, this.wall, this.board.getBoardHeight() - 1);
        this.wall.addRoomWallColumn(this.board, this.wall, 0);
        this.wall.addRoomWallColumn(this.board, this.wall, this.board.getBoardWidth() - 1);

        this.snake = new SnakeElement('b', START_X, START_Y);
        this.board.setObjectOnLocation(this.snake, this.snake.getX(), this.snake.getY());

        this.apple = new AppleElement('a');
        this.apple.addRandomApple(this.board, this.apple, this.snake);
    }


    public void update_game(char input) {

        switch (input) {
            case 'l':
                this.snake.moveLeft(this.board, this.snake);
                break;
            case 'r':
                this.snake.moveRight(this.board, this.snake);
                break;
            case 'u':
                this.snake.moveUp(this.board, this.snake);
                break;
            case 'd':
                this.snake.moveDown(this.board, this.snake);
                break;
        }

        // eat
        if (check_eat(this.snake, this.apple)) {
            this.apple.addRandomApple(this.board, this.apple, this.snake);
            this.snake.eat_apple();
            this.score += 10;
        }
        // not eat
        else {
            if (check_conflict_wall(snake, board) || check_conflict_body(snake)) {
                return;
            }

        }
    }

    public static boolean check_eat(SnakeElement snake, AppleElement apple) {
        if ((snake.getX() == apple.getX()) && (snake.getY() == apple.getY())) {
            return true;
        }
        else {
            return false;
        }
    }

    public static boolean check_conflict_wall(SnakeElement snake, Board board) {
        if ((snake.getX() == 0) || (snake.getX() == (board.getBoardWidth() - 1)) ||
                (snake.getY() == 0) || (snake.getY() == (board.getBoardHeight() -1))) {
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