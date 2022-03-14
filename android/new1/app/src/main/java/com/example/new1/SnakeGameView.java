package com.example.new1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class SnakeGameView extends View {

    private Paint mPaint = new Paint();
    private float mSize;
    private char[][] gameMap;

    public SnakeGameView(Context context, AttributeSet attrs){
        super(context, attrs);
    }

    public void setGameMap(char[][] inputMap){
        gameMap = inputMap;
    }

    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        float locSizeX = canvas.getWidth() / gameMap[0].length;
        float locSIzeY = canvas.getHeight() / gameMap.length;
        mSize = (float) (locSizeX * 0.45);
        for (int i=0;i<gameMap.length;i++){
            for (int j=0;j<gameMap[0].length;j++){
                switch(gameMap[i][j]){
                    case 'w':
                        mPaint.setColor(Color.BLACK);
                        break;
                    case 'b':
                        mPaint.setColor(Color.GRAY);
                        break;
                    case 'h':
                        mPaint.setColor(Color.GREEN);
                        break;
                    case 'a':
                        mPaint.setColor(Color.RED);
                        break;
                    case 'e':
                        mPaint.setColor(Color.WHITE);
                        break;
                }
                canvas.drawCircle((j*locSizeX)+(locSizeX/2),
                        (i*locSIzeY)+(locSIzeY/2),mSize,mPaint);
            }
        }
    }
}
