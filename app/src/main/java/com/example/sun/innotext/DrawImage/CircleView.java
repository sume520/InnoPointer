package com.example.sun.innotext.DrawImage;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by sun on 2018/3/14.
 */

class CircleView extends View {

    private Paint paint;
    private float width;
    private float height;

    public CircleView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        initPaint();
        canvas.drawCircle(width/2,height/2,100,paint);
    }

    private void initPaint(){
        paint=new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setStrokeWidth(10);
        paint.setColor(0xADFF2F);
        paint.setStyle(Paint.Style.STROKE);

        width=getMeasuredWidth();
        height=getMeasuredHeight();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
