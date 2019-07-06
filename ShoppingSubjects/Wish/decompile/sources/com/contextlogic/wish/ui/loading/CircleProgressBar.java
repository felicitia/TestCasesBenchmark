package com.contextlogic.wish.ui.loading;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import com.contextlogic.wish.R;
import com.contextlogic.wish.application.WishApplication;

public class CircleProgressBar extends View {
    protected float mBackgroundAlpha = 0.3f;
    protected Paint mBackgroundPaint;
    protected int mColor;
    protected Paint mForegroundPaint;
    protected int mMax = 100;
    protected int mMin = 0;
    protected float mProgress = 0.0f;
    protected RectF mRectF;
    protected float mStrokeWidth = 15.0f;

    public CircleProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet);
    }

    /* JADX INFO: finally extract failed */
    private void init(Context context, AttributeSet attributeSet) {
        this.mRectF = new RectF();
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.CircleProgressBar, 0, 0);
        try {
            this.mStrokeWidth = obtainStyledAttributes.getDimension(4, this.mStrokeWidth);
            this.mProgress = obtainStyledAttributes.getFloat(3, this.mProgress);
            this.mColor = obtainStyledAttributes.getInt(5, WishApplication.getInstance().getResources().getColor(R.color.white));
            this.mMin = obtainStyledAttributes.getInt(2, this.mMin);
            this.mMax = obtainStyledAttributes.getInt(1, this.mMax);
            this.mBackgroundAlpha = obtainStyledAttributes.getFloat(0, this.mBackgroundAlpha);
            obtainStyledAttributes.recycle();
            this.mBackgroundPaint = new Paint(1);
            this.mBackgroundPaint.setColor(adjustAlpha(this.mColor, this.mBackgroundAlpha));
            this.mBackgroundPaint.setStyle(Style.STROKE);
            this.mBackgroundPaint.setStrokeWidth(this.mStrokeWidth);
            this.mForegroundPaint = new Paint(1);
            this.mForegroundPaint.setColor(this.mColor);
            this.mForegroundPaint.setStyle(Style.STROKE);
            this.mForegroundPaint.setStrokeWidth(this.mStrokeWidth);
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }

    private int adjustAlpha(int i, float f) {
        return Color.argb(Math.round(((float) Color.alpha(i)) * f), Color.red(i), Color.green(i), Color.blue(i));
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int min = Math.min(getDefaultSize(getSuggestedMinimumWidth(), i), getDefaultSize(getSuggestedMinimumHeight(), i2));
        setMeasuredDimension(min, min);
        float f = (float) min;
        this.mRectF.set((this.mStrokeWidth / 2.0f) + 0.0f, (this.mStrokeWidth / 2.0f) + 0.0f, f - (this.mStrokeWidth / 2.0f), f - (this.mStrokeWidth / 2.0f));
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawOval(this.mRectF, this.mBackgroundPaint);
        Canvas canvas2 = canvas;
        canvas2.drawArc(this.mRectF, -90.0f, (this.mProgress * 360.0f) / ((float) this.mMax), false, this.mForegroundPaint);
    }

    public void setProgress(float f) {
        this.mProgress = f;
        invalidate();
    }
}
