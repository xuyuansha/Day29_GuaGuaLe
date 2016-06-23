package com.example.huaming_lin.day29_guaguale;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Environment;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by HuaMing_Lin on 2016/5/12.
 */
public class GuaGuaLe extends View {

    private float x;
    private Paint mPaint;
    private int width;
    private int height;
    private Bitmap bitmap;
    private Canvas mCanvas;

    public GuaGuaLe(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public GuaGuaLe(Context context) {
        super(context);
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setStrokeWidth(50);
        mPaint.setDither(true);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        setPaintColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        //把画笔转成 橡皮擦
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
    }

    public void setPaintColor(int color) {
        mPaint.setColor(color);
    }

    public void savaCanvas2File() {
        if (bitmap != null) {
            try {
                File dir = Environment.getExternalStorageDirectory();
                File file = new File(dir, "guaguale.png");
                FileOutputStream fos = new FileOutputStream(file);
                boolean compress = bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
                if (compress)
                    Toast.makeText(getContext(), "图片保存成功！" + file.getAbsolutePath(), Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (bitmap == null) {
            bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            //创建一个画布，告诉这个画布绘制在 bitmap 上
            mCanvas = new Canvas(bitmap);
            mCanvas.drawColor(Color.BLUE);
        }
        mCanvas.drawLine(lastX, lastY, currentX, currentY, mPaint);
        //直接用canvas绘制一张已经挖过的Bitmap
        canvas.drawBitmap(bitmap, 0, 0, null);


//        mCanvas.drawCircle(200,200,100,mPaint);
//        canvas.drawColor(Color.RED);
//        canvas.drawLine(0, 0, 700, 400, mPaint);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int sizeW = MeasureSpec.getSize(widthMeasureSpec);
        int sizeH = MeasureSpec.getSize(heightMeasureSpec);
        int modeW = MeasureSpec.getMode(widthMeasureSpec);
        int modeH = MeasureSpec.getMode(heightMeasureSpec);
        width = modeW == MeasureSpec.EXACTLY ? sizeW : 300;
        height = modeH == MeasureSpec.EXACTLY ? sizeH : 100;
        setMeasuredDimension(width, height);
    }


    private float lastX, lastY, currentX, currentY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        lastX = currentX;
        lastY = currentY;
        currentX = event.getX();
        currentY = event.getY();
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            invalidate();
        }
        return true;
    }
}
