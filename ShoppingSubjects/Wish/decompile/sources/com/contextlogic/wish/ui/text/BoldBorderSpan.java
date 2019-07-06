package com.contextlogic.wish.ui.text;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.style.ReplacementSpan;

public class BoldBorderSpan extends ReplacementSpan {
    private Drawable mDrawable;
    private float mMultiplier;
    private int mPadding;

    public BoldBorderSpan(Drawable drawable, int i, float f) {
        this.mDrawable = drawable;
        this.mPadding = i;
        this.mMultiplier = f;
    }

    public void draw(Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, Paint paint) {
        Paint paint2 = paint;
        paint2.setTypeface(Typeface.create(Typeface.DEFAULT, 1));
        CharSequence charSequence2 = charSequence;
        int i6 = i;
        int i7 = i2;
        RectF rectF = new RectF(f - ((float) this.mPadding), (float) (i3 - this.mPadding), f + measureText(paint2, charSequence2, i6, i7) + ((float) this.mPadding), (float) (i5 + this.mPadding));
        int i8 = (int) rectF.bottom;
        if (((double) this.mMultiplier) != 1.0d) {
            i8 = (int) (rectF.top + ((rectF.bottom - rectF.top) / this.mMultiplier) + ((float) this.mPadding));
        }
        this.mDrawable.setBounds((int) rectF.left, (int) rectF.top, (int) rectF.right, i8);
        canvas.drawText(charSequence2, i6, i7, f, (float) i4, paint2);
        this.mDrawable.draw(canvas);
    }

    public int getSize(Paint paint, CharSequence charSequence, int i, int i2, FontMetricsInt fontMetricsInt) {
        return Math.round(paint.measureText(charSequence, i, i2) + ((float) this.mPadding));
    }

    private float measureText(Paint paint, CharSequence charSequence, int i, int i2) {
        return paint.measureText(charSequence, i, i2);
    }
}
