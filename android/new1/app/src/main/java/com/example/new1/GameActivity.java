package com.example.new1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {

    private Button start_button;
    private TextView score_view;
    private Button stop_button;
    private Button left_button;
    private Button up_button;
    private Button down_button;
    private Button right_button;

    GameEngine gameEngine;
    SnakeGameView snakeGameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        start_button = findViewById(R.id.start_button);
        score_view = findViewById(R.id.score_text);
        stop_button = findViewById(R.id.stop_button);

        left_button = findViewById(R.id.left_button);
        up_button = findViewById(R.id.up_button);
        down_button = findViewById(R.id.down_button);
        right_button = findViewById(R.id.right_button);

        gameEngine = new GameEngine();
        gameEngine.init_game();
        snakeGameView = findViewById(R.id.snake_view);
        snakeGameView.setGameMap(gameEngine.getBoard());

        start_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                gameEngine.init_game();
                snakeGameView.setGameMap(gameEngine.getBoard());
                score_view.setText("Score: "+Integer.toString(gameEngine.getScore()));
                snakeGameView.invalidate();
            }
        });

        stop_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                gameEngine.finish_game();
                snakeGameView.setGameMap(gameEngine.getBoard());
                score_view.setText("Score: "+Integer.toString(gameEngine.getScore()));
                snakeGameView.invalidate();
            }
        });

        left_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                gameEngine.update_game('l');
                snakeGameView.setGameMap(gameEngine.getBoard());
                score_view.setText("Score: "+Integer.toString(gameEngine.getScore()));
                snakeGameView.invalidate();
            }
        });

        up_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                gameEngine.update_game('u');
                snakeGameView.setGameMap(gameEngine.getBoard());
                score_view.setText("Score: "+Integer.toString(gameEngine.getScore()));
                snakeGameView.invalidate();
            }
        });

        down_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                gameEngine.update_game('d');
                snakeGameView.setGameMap(gameEngine.getBoard());
                score_view.setText("Score: "+Integer.toString(gameEngine.getScore()));
                snakeGameView.invalidate();
            }
        });

        right_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                gameEngine.update_game('r');
                snakeGameView.setGameMap(gameEngine.getBoard());
                score_view.setText("Score: "+Integer.toString(gameEngine.getScore()));
                snakeGameView.invalidate();
            }
        });

    }
}
