package com.jnu.student.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.jnu.student.R;

import java.util.ArrayList;

public class GameView extends SurfaceView implements SurfaceHolder.Callback  {

    public static final int NOT_A_VALIDATE_POSITION = -1;
    private final Paint paint = new Paint();

    private final int width;
    private final int height;

    private final int holeWidth = 100;
    private final int holeHeight = 100;



    private boolean isGameOver = false;

    // touchX/Y：用户点击屏幕时，鼠标位置
    private float touchX= NOT_A_VALIDATE_POSITION;
    private float touchY=NOT_A_VALIDATE_POSITION;

    /**
     * 1.在构造器中初始化SurfaceHolder，并自己作自己的监听者添加自己的回调函数
     * */
    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 1.自己作自己的监听者添加自己的回调函数
        getHolder().addCallback(this);

        // 2.初始化相关属性
        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);

        width = getWidth();
        height = getHeight();
    }

    /**
     * SurfaceHolder的回调函数surfaceCreated():
     *   创建一个线程，启动游戏
     * */
    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        startGame();
    }

    /**
     * SurfaceHolder的回调函数surfaceChanged():
     *   处理 SurfaceView 尺寸改变
     * */
    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }
    /**
     * SurfaceHolder的回调函数surfaceDestroyed():
     *   释放游戏资源
     * */
    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

    }

    /**
     * SurfaceHolder的回调函数onTouchEvent():
     *   获取玩家鼠标点击的位置
     * */
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            // 如果发生鼠标按下事件
            case MotionEvent.ACTION_DOWN:
                // 当松开鼠标按击键时才记录鼠标所在位置
                this.touchX=NOT_A_VALIDATE_POSITION;
                this.touchY=NOT_A_VALIDATE_POSITION;
                break;
            // 如果发生鼠标松开事件：记录当前鼠标位置
            case MotionEvent.ACTION_UP:
                this.touchX=event.getRawX()/getWidth();
                this.touchY=event.getRawY()/getHeight();
                break;
            // 如果发生鼠标移动事件
            case MotionEvent.ACTION_MOVE:
                break;
        }
        return true;
    }

    private void startGame() {
        isGameOver = false;

        // 启动一个新线程来运行游戏
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!isGameOver) {
                    // 绘制游戏画面
                    draw();
                    try {
                        // 避免游戏刷新过快
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private void draw() {

        /**
         * 1.将要绘制的地鼠图片封装成一个内部类
         * */
        ArrayList<GameSpriter> gameSpriters=new ArrayList<GameSpriter>();
        for (int i = 0; i < 3; i++) {
            gameSpriters.add(new GameSpriter(Math.random(),Math.random(),R.drawable.dishu));
        }

        /**
         * 2.创建画布
         * */

        // 2.1.创建一个 Canvas 对象【画布】，并将其设置为 Bitmap 的 Canvas
        Canvas canvas = getHolder().lockCanvas();
        // 2.2.创建画布的背景避免刷新的图片视觉残留
        Bitmap background = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.background),
                canvas.getWidth(), canvas.getHeight(), true);
        canvas.drawBitmap(background,0,0,null);

        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);
        paint.setTextSize(24);
        paint.setStyle(Paint.Style.FILL);

        // 记录玩家击中的数量
        int hitNumber=0;

        // 2.3.在画布上绘制地鼠图片
        for (GameSpriter gameSpriter:gameSpriters) {
            // 2.4.检测鼠标是否有触碰到已经绘制完的图画,如果有击中的数量+1
            if(gameSpriter.detectCollision(canvas)) {
                hitNumber++;
                canvas.drawText("击中了"+hitNumber+"个",100,100,paint);
            }

            // 让地鼠图片位置随机移动
            gameSpriter.move(canvas);
            // 绘制地鼠图片
            gameSpriter.draw(canvas);
        }
        // 2.4.保存 Canvas 对象的状态
        canvas.save();
        // 2.5.恢复 Canvas 对象的状态
        canvas.restore();

        getHolder().unlockCanvasAndPost(canvas);

    }



    /**
     * 将要绘制的地鼠图片封装成GameSpriter类
     * */

    private class GameSpriter {
        private double relatedX;
        private double relatedY;
        private final int resourceID;
        private double direction;
        private final double radius;



        public GameSpriter(double relatedX, double relatedY, int resourceID) {
            this.relatedX=relatedX;
            this.relatedY=relatedY;
            this.resourceID=resourceID;
            //  direction为随机的一个角度(π/n)，π=180°
            this.direction=Math.random()*Math.PI;
            this.radius=Math.random();
        }

        // 绘制图片，且绘制的位置随机
        public void draw(Canvas canvas) {
            // 创建一个 Bitmap 对象，并将图片加载到其中
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), resourceID);
            // 设置图片大小
            Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, 200, 200, true);
            // 使用 null 作为 paint 参数绘制图片，并让图片随机出现在画布的某个位置
            canvas.drawBitmap(scaledBitmap, (int)(canvas.getWidth()*relatedX) ,(int)(canvas.getHeight()*relatedY) , null);
        }

        // 让绘制的图片移动
        public void move(Canvas canvas) {
            // 让X、Y沿随机某个角度移动: direction为随机的一个角度(π/n)
            relatedY+= this.radius*Math.sin(this.direction);
            relatedX+= this.radius*Math.cos(this.direction);
            // 图片的位置为画布的大小*relatedX/Y ==》 0< relatedX/Y < 1，不能让图片超出画布
            if(relatedX>1) relatedX=0;
            if(relatedY>1) relatedY=0;
            if(relatedX<0) relatedX=1;
            if(relatedY<0) relatedY=1;

            // 再设置一个随机数，随机改变移动的方向 ==》 direction为随机的一个角度(π/n)，π=180°
            if(Math.random()<0.1)direction=Math.random()*Math.PI;
        }

        // 判断用户是否击中图像
        public boolean detectCollision(Canvas canvas) {
            // 因为relatedX/Y都*canvas.getWidth/getHeight()
            double distanceX= Math.abs(this.relatedX-GameView.this.touchX);
            double distanceY= Math.abs(this.relatedY-GameView.this.touchY);
            if(distanceX < 0.01 && distanceY < 0.01)return true;
            return false;
        }
    }
}
