package com.example.sun.innotext.expansion;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.sun.innotext.R;

public class ExpansionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expansion);
    }

    class DrawCircle extends View {

        private Paint paint;
        private double screenWidth;
        private double screenHeight;

        public DrawCircle(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
        }

        private void initPaint(){
            paint=new Paint();
            paint.setAntiAlias(true);
            paint.setDither(true);
            paint.setStrokeWidth(10);
            paint.setColor(0xADFF2F);
            paint.setStyle(Paint.Style.STROKE);
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            screenHeight=heightMeasureSpec;
            screenWidth=widthMeasureSpec;
        }
    }
}
