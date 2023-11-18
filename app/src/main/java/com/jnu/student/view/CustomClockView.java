package com.jnu.student.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

import java.util.Calendar;

public class CustomClockView extends View {
    private Paint mPaint;
    private int mWidth, mHeight;
    private int mCenterX, mCenterY;
    private int mRadius;

    private Handler mHandler;
    private Runnable mRunnable;

    public CustomClockView(Context context) {
        super(context);
        init();
    }

    public CustomClockView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mHandler = new Handler();
        mRunnable = new Runnable() {
            @Override
            public void run() {
                invalidate();
                mHandler.postDelayed(this, 1000); // 每隔一秒刷新一次
            }
        };
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        mHandler.post(mRunnable);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mHandler.removeCallbacks(mRunnable);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        mCenterX = mWidth / 2;
        mCenterY = mHeight / 2;
        mRadius = Math.min(mWidth, mHeight) / 2 - 20;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawClock(canvas);
        drawHourHand(canvas);
        drawMinuteHand(canvas);
        drawSecondHand(canvas);
    }

    private void drawClock(Canvas canvas) {
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(8);
        mPaint.setColor(Color.BLACK);
        canvas.drawCircle(mCenterX, mCenterY, mRadius, mPaint);
    }

    private void drawHourHand(Canvas canvas) {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);

        float angle = (hour + minute / 60f + second / 3600f) * 30;
        float hourHandLength = mRadius * 0.5f;
        int hourHandThickness = 12;

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(hourHandThickness);
        mPaint.setColor(Color.BLACK);
        canvas.save();
        canvas.rotate(angle, mCenterX, mCenterY);
        canvas.drawLine(mCenterX, mCenterY, mCenterX, mCenterY - hourHandLength, mPaint);
        canvas.restore();
    }

    private void drawMinuteHand(Canvas canvas) {
        Calendar calendar = Calendar.getInstance();
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);

        float angle = (minute + second / 60f) * 6;
        float minuteHandLength = mRadius * 0.7f;
        int minuteHandThickness = 8;

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(minuteHandThickness);
        mPaint.setColor(Color.BLACK);
        canvas.save();
        canvas.rotate(angle, mCenterX, mCenterY);
        canvas.drawLine(mCenterX, mCenterY, mCenterX, mCenterY - minuteHandLength, mPaint);
        canvas.restore();
    }

    private void drawSecondHand(Canvas canvas) {
        Calendar calendar = Calendar.getInstance();
        int second = calendar.get(Calendar.SECOND);

        float angle = second * 6;
        float secondHandLength = mRadius * 0.9f;
        int secondHandThickness = 4;

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(secondHandThickness);
        mPaint.setColor(Color.RED);
        canvas.save();
        canvas.rotate(angle, mCenterX, mCenterY);
        canvas.drawLine(mCenterX, mCenterY, mCenterX, mCenterY - secondHandLength, mPaint);
        canvas.restore();
    }
}