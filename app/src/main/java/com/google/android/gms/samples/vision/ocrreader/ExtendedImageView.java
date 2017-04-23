package com.google.android.gms.samples.vision.ocrreader;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by ajitesh on 22/4/17.
 */

public class ExtendedImageView extends android.support.v7.widget.AppCompatImageView {
    public ExtendedImageView(Context context) {
        super(context);
    }

    public ExtendedImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ExtendedImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(3.0f);
        paint.setStyle(Paint.Style.STROKE);
        for(int i=0;i<MainActivity.rects.size();i++) {
            canvas.drawRect(MainActivity.rects.get(i), paint);
        }
    }
}