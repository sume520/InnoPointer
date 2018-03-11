package com.example.sun.innotext.fragments;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sun.innotext.R;


public class FragmentClock extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ClockView clockView=new ClockView(getActivity());
        return clockView;
    }

    class ClockView extends View{

        public ClockView(Context context) {
            super(context);
        }


        public int mWidth;
        public int mHeight;

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            mWidth = getMeasuredWidth();
            mHeight = getMeasuredHeight()-50;

            //绘画半圆
            Paint paintSemicircle=new Paint();
            paintSemicircle.setColor(Color.YELLOW);
            paintSemicircle.setStyle(Paint.Style.STROKE);
            paintSemicircle.setAntiAlias(true);
            paintSemicircle.setStrokeWidth(5);
            RectF rectF=new RectF(0,mHeight-mWidth/2,mWidth,mHeight+mWidth/2);
            canvas.drawArc(rectF,-180,180,false,paintSemicircle);

            //绘制刻度
            Paint paintDegree = new Paint();
            paintDegree.setStrokeWidth(3);


            Paint paintText=new Paint();
            paintText.setStyle(Paint.Style.STROKE);
            paintText.setAntiAlias(true);
            paintText.setStrokeWidth(10);
            paintText.setTextSize(100);
            canvas.drawText("KM/H",mWidth/2-130,mHeight-50,paintText);

            //逆时针旋转180°
            canvas.rotate(-90, mWidth / 2, mHeight);

            for (int i = 0 ;i<=120 ;i++){
                //大点,12点 3点 6点 9点
                if (i == 0 || i == 30 || i==60){
                    paintDegree.setStrokeWidth(12);
                    paintDegree.setTextSize(60);
                    canvas.drawLine(mWidth/2,mHeight-mWidth/2,mWidth/2,mHeight-mWidth/2+80,paintDegree);
                    String degree = String.valueOf(i);
                    if (i == 0){
                        degree = "0";
                    }
                    canvas.drawText(degree,mWidth/2-paintDegree.measureText(degree)/2,mHeight-mWidth/2+150,paintDegree);
                }else if (i % 10 == 0){//整点
                    paintDegree.setStrokeWidth(9);
                    paintDegree.setTextSize(60);
                    String degree = String.valueOf(i);
                    canvas.drawText(degree,mWidth/2-paintDegree.measureText(degree)/2,mHeight-mWidth/2+140,paintDegree);
                    canvas.drawLine(mWidth/2,mHeight-mWidth/2,mWidth/2,mHeight-mWidth/2+60,paintDegree);
                }else if (i % 5 == 0){
                    paintDegree.setStrokeWidth(6);
                    paintDegree.setTextSize(20);
                    canvas.drawLine(mWidth/2,mHeight-mWidth/2,mWidth/2,mHeight-mWidth/2+40,paintDegree);
                }
                else{
                    paintDegree.setStrokeWidth(3);
                    paintDegree.setTextSize(20);
                    canvas.drawLine(mWidth/2,mHeight-mWidth/2,mWidth/2,mHeight-mWidth/2+20,paintDegree);
                }
                //每次绘制完成后将画布旋转3度
                canvas.rotate(1.5f, mWidth / 2, mHeight );
            }
            //保存表盘和刻度的画布
            canvas.save();
            //绘制指针
            Paint paintPoint = new Paint();
            Paint paint = new Paint();
            paint.setStrokeWidth(10);
            paint.setColor(Color.RED);


            int degress=-80;

            //将画布的起点坐标移动到圆心位置
            canvas.translate(mWidth/2,mHeight);
            canvas.drawCircle(0,0,15,paintPoint);

            canvas.drawLine(0,0,0,degress+400,paint);

            //合并图层
            canvas.restore();
        }

    }

}
