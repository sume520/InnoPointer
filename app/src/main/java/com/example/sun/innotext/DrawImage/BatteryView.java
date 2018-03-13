package com.example.sun.innotext.DrawImage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.BatteryManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by sun on 2018/3/10.
 */

public class BatteryView extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        DrawBattery drawBattery=new DrawBattery(getContext());
        return drawBattery;
    }

    class DrawBattery extends View{
        private Paint mBatteryPait;
        private Paint mPowerPaint;
        private float mBatteryStroke = 2.0f;

        /*
        *起始位置
         */

        private float mStartXPoint;
        private float mStartYPoint;

        /**
         * 屏幕的高宽
         *
         * @param context
         */
        private int measureWidth;
        private int measureHeight;

        /**
         * 电池参数
         *
         * @param context
         */
        private float mBatteryHeight = 80.0f;// 电池高度
        private float mBatteryWidth = 40.0f;// 电池的宽度
        private float mCapHeight = 6.6f;
        private float mCapWidth = 20.0f;

        /**
         * 电池电量
         *
         * @param context
         */
        private float mPowerPadding = 5.0f;
        private float mPowerHeight = mBatteryHeight+mCapHeight-mBatteryStroke
                - mPowerPadding;
        private float mPowerWidth = mBatteryWidth - mPowerPadding-mBatteryStroke;// 电池身体的总宽度
        private float mPower = 0;
        private float powerPercent=0;

        /**
         * 矩形
         */
        private RectF mBatteryRectF;
        private RectF mCapRectF;
        private RectF mPowerRectF;

        private boolean mIsCharging;    //是否在充电

        //广播接收器
        private BroadcastReceiver mPowerConnectionReceiver=new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
                mIsCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                        status == BatteryManager.BATTERY_STATUS_FULL;

                int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
                int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

                setPower(((float) level)/scale);
            }
        };

        public DrawBattery(Context context){
            super(context);
            initView();
        }

        public DrawBattery(Context context, AttributeSet attrs) {
            super(context, attrs);
            initView();
        }

        public DrawBattery(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);
            initView();
        }

        private void initView() {


            /**
             * 设置电池画笔
             */
            mBatteryPait = new Paint();
            mBatteryPait.setColor(Color.GRAY);
            mBatteryPait.setStrokeWidth(mBatteryStroke);
            mBatteryPait.setStyle(Paint.Style.STROKE);
            mBatteryPait.setAntiAlias(true);
            /**
             * 电量画笔
             */
            mPowerPaint = new Paint();
            mPowerPaint.setColor(Color.GREEN);
            mPowerPaint.setStyle(Paint.Style.FILL);
            mPowerPaint.setStrokeWidth(mBatteryStroke);
            mPowerPaint.setAntiAlias(true);

            if (mIsCharging) {
                mPowerPaint.setColor(Color.BLUE);
            } else {
                if (powerPercent > 0.9) {
                    mPowerPaint.setColor(Color.RED);

                } else {
                    mPowerPaint.setColor(Color.GREEN);
                }
            }

            /*
            if(mBatteryHeight*(1-powerPercent)>=mPowerHeight){
                mPower=mPowerHeight;
            }else if(mBatteryHeight*(1-powerPercent)<=0){
                mPower=0;
            }else{
                mPower=mBatteryHeight*(1-powerPercent);
            }*/

            /**
             * 设置电池矩形
             */
            mBatteryRectF = new RectF(0-mBatteryWidth/2, mCapHeight, mBatteryWidth/2,
                    mBatteryHeight+mCapHeight);

            /**
             * 设置电池盖矩形
             */

            mCapRectF = new RectF(mBatteryWidth/2-mCapWidth/2-mBatteryWidth/2,0,
                    mBatteryHeight/2-mCapWidth/2-mBatteryWidth/2,  mCapHeight);

            /**
             * 设置电量的矩形
             */

            mPowerRectF = new RectF(mBatteryStroke+mPowerPadding-mBatteryWidth/2,
                    mCapHeight+mPowerPadding+mPower+mBatteryStroke,
                    mPowerWidth-mBatteryWidth/2, mPowerHeight);


        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            measureHeight = MeasureSpec.getSize(heightMeasureSpec);
            measureWidth = MeasureSpec.getSize(widthMeasureSpec);

            setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.save();
            canvas.translate(measureWidth / 2, measureHeight / 2);
            canvas.drawRoundRect(mBatteryRectF, 2.0f, 2.0f, mBatteryPait);
            canvas.drawRoundRect(mCapRectF, 2.0f, 2.0f, mBatteryPait);
            canvas.drawRect(mPowerRectF, mPowerPaint);
            canvas.restore();
        }

        @Override
        protected void onAttachedToWindow() {
            //注册电量监听
            getContext().registerReceiver(mPowerConnectionReceiver,new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
            super.onAttachedToWindow();
        }

        @Override
        protected void onDetachedFromWindow() {
            getContext().unregisterReceiver(mPowerConnectionReceiver);
            super.onDetachedFromWindow();
        }

        private void setPower(float power){
            powerPercent=power;
            invalidate();
        }
    }
}


