package com.etsy.android.stylekit.alerts;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import android.support.annotation.ColorInt;
import android.support.annotation.IntRange;

public class AlertBackgroundDrawable extends Drawable {
    static final int ANCHOR_DOWN = 2;
    static final int ANCHOR_NONE = 0;
    static final int ANCHOR_UP = 1;
    private float anchorCenter;
    private int anchorDirection;
    private float anchorHeight;
    private float anchorWidth;
    private Paint paint = new Paint();
    private float radius;

    public int getOpacity() {
        return 0;
    }

    public void setAlpha(int i) {
    }

    public void setColorFilter(ColorFilter colorFilter) {
    }

    public AlertBackgroundDrawable(@ColorInt int i, float f, float f2, float f3, float f4, int i2) {
        this.radius = f;
        this.anchorWidth = f2;
        this.anchorHeight = f3;
        this.anchorCenter = f4;
        this.anchorDirection = i2;
        this.paint.setColor(i);
        this.paint.setStyle(Style.FILL);
    }

    public void setAnchorDirection(@IntRange(from = 0, to = 2) int i) {
        this.anchorDirection = i;
        Callback callback = getCallback();
        if (callback != null) {
            callback.invalidateDrawable(this);
        }
    }

    public void draw(Canvas canvas) {
        Path path = new Path();
        RectF rectF = new RectF();
        switch (this.anchorDirection) {
            case 0:
                rectF.left = 0.0f;
                rectF.top = 0.0f;
                rectF.right = (float) canvas.getWidth();
                rectF.bottom = (float) canvas.getHeight();
                canvas.drawRoundRect(rectF, this.radius, this.radius, this.paint);
                return;
            case 1:
                rectF.left = 0.0f;
                rectF.top = this.anchorHeight;
                rectF.right = (float) canvas.getWidth();
                rectF.bottom = (float) canvas.getHeight();
                canvas.drawRoundRect(rectF, this.radius, this.radius, this.paint);
                float f = this.anchorCenter - (this.anchorWidth / 2.0f);
                float f2 = this.anchorHeight;
                path.moveTo(f, f2);
                path.lineTo(this.anchorCenter, 0.0f);
                path.lineTo(this.anchorWidth + f, f2);
                path.lineTo(f, f2);
                path.close();
                canvas.drawPath(path, this.paint);
                return;
            default:
                rectF.left = 0.0f;
                rectF.top = 0.0f;
                rectF.right = (float) canvas.getWidth();
                rectF.bottom = ((float) canvas.getHeight()) - this.anchorHeight;
                canvas.drawRoundRect(rectF, this.radius, this.radius, this.paint);
                float f3 = this.anchorCenter - (this.anchorWidth / 2.0f);
                float height = ((float) canvas.getHeight()) - this.anchorHeight;
                path.moveTo(f3, height);
                path.lineTo(this.anchorCenter, this.anchorHeight + height);
                path.lineTo(this.anchorWidth + f3, height);
                path.lineTo(f3, height);
                path.close();
                canvas.drawPath(path, this.paint);
                return;
        }
    }
}
