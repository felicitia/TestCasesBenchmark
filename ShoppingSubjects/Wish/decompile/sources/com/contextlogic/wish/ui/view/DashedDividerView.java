package com.contextlogic.wish.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;
import com.contextlogic.wish.R;

public class DashedDividerView extends View {
    public static int ORIENTATION_HORIZONTAL = 0;
    public static int ORIENTATION_VERTICAL = 1;
    private int mOrientation;
    private Paint mPaint;

    /* JADX INFO: finally extract failed */
    public DashedDividerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.DividerView, 0, 0);
        try {
            int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(1, 5);
            int dimensionPixelSize2 = obtainStyledAttributes.getDimensionPixelSize(2, 5);
            int dimensionPixelSize3 = obtainStyledAttributes.getDimensionPixelSize(3, 3);
            int color = obtainStyledAttributes.getColor(0, -16777216);
            this.mOrientation = obtainStyledAttributes.getInt(4, ORIENTATION_HORIZONTAL);
            obtainStyledAttributes.recycle();
            this.mPaint = new Paint();
            this.mPaint.setAntiAlias(true);
            this.mPaint.setColor(color);
            this.mPaint.setStyle(Style.STROKE);
            this.mPaint.setStrokeWidth((float) dimensionPixelSize3);
            this.mPaint.setPathEffect(new DashPathEffect(new float[]{(float) dimensionPixelSize2, (float) dimensionPixelSize}, 0.0f));
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }

    public DashedDividerView(Context context) {
        this(context, null);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        if (this.mOrientation == ORIENTATION_HORIZONTAL) {
            float height = ((float) getHeight()) * 0.5f;
            canvas.drawLine(0.0f, height, (float) getWidth(), height, this.mPaint);
            return;
        }
        float width = ((float) getWidth()) * 0.5f;
        canvas.drawLine(width, 0.0f, width, (float) getHeight(), this.mPaint);
    }
}
