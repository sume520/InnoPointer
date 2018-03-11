package com.example.sun.innotext.DrawImage;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by sun on 2018/3/10.
 */

public class DrawBattery extends View{
    public DrawBattery(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint=new Paint();
        paint.setStrokeWidth(10);

    }
}
