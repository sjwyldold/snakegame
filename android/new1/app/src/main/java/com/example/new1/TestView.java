package com.example.new1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class TestView extends View {

    private Paint mPaint = new Paint();
    private int mSize = 100;

    public TestView(Context context, AttributeSet attrs){
        super(context, attrs);
    }

    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        mPaint.setColor(Color.BLACK);
        int x = canvas.getWidth() / 2;
        int y = canvas.getHeight() / 2;
        canvas.drawCircle(x,y,mSize,mPaint);
    }
}
