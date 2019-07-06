package com.contextlogic.wish.ui.loading;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

public class GrowableDotDrawable extends Drawable {
    final Rect mBounds = new Rect(0, 0, 0, 0);
    private float mMaxRadius;
    private Paint mPaint = new Paint();
    private float mRadius;

    public int getOpacity() {
        return -3;
    }

    public void setColorFilter(ColorFilter colorFilter) {
    }

    public GrowableDotDrawable(int i, float f, float f2) {
        this.mPaint.setColor(i);
        this.mRadius = f;
        setMaxRadius(f2);
    }

    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        canvas.drawCircle((float) bounds.centerX(), (float) bounds.centerY(), this.mRadius - 1.0f, this.mPaint);
    }

    public void setAlpha(int i) {
        if (i != this.mPaint.getAlpha()) {
            this.mPaint.setAlpha(i);
            invalidateSelf();
        }
    }

    public void setRadius(float f) {
        this.mRadius = f;
        invalidateSelf();
    }

    public int getIntrinsicWidth() {
        return (int) (this.mMaxRadius * 2.0f);
    }

    public int getIntrinsicHeight() {
        return (int) (this.mMaxRadius * 2.0f);
    }

    public void setMaxRadius(float f) {
        this.mMaxRadius = f;
        int i = (int) (f * 2.0f);
        this.mBounds.bottom = i;
        this.mBounds.right = i;
    }

    public Rect getDirtyBounds() {
        return this.mBounds;
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.mBounds.offsetTo(rect.left, rect.top);
    }
}
