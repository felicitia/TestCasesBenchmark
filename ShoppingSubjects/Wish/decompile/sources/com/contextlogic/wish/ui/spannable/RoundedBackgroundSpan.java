package com.contextlogic.wish.ui.spannable;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.RectF;
import android.text.style.ReplacementSpan;
import com.contextlogic.wish.R;
import com.contextlogic.wish.application.WishApplication;

public class RoundedBackgroundSpan extends ReplacementSpan {
    private static final int CORNER_RADIUS = WishApplication.getInstance().getResources().getDimensionPixelOffset(R.dimen.corner_radius);
    private static final float PADDING_X = ((float) WishApplication.getInstance().getResources().getDimensionPixelOffset(R.dimen.eight_padding));
    private static final float PADDING_Y = ((float) WishApplication.getInstance().getResources().getDimensionPixelOffset(R.dimen.four_padding));
    private int mBackgroundColor;
    private int mTextColor;
    private float mTextSize;

    public RoundedBackgroundSpan(int i, int i2, float f) {
        this.mBackgroundColor = i;
        this.mTextColor = i2;
        this.mTextSize = f;
    }

    public void draw(Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, Paint paint) {
        float f2 = f;
        Paint paint2 = paint;
        paint2.setTextSize(this.mTextSize);
        paint2.setColor(this.mBackgroundColor);
        float f3 = (float) i3;
        float f4 = (PADDING_Y * 2.0f) + f3 + this.mTextSize;
        CharSequence charSequence2 = charSequence;
        int i6 = i;
        int i7 = i2;
        Canvas canvas2 = canvas;
        canvas2.drawRoundRect(new RectF(f2, f3, ((float) getTagWidth(charSequence2, i6, i7, paint2)) + f2, f4), (float) CORNER_RADIUS, (float) CORNER_RADIUS, paint2);
        paint2.setColor(this.mTextColor);
        canvas2.drawText(charSequence2, i6, i7, f2 + PADDING_X, (f4 - (PADDING_Y * 2.0f)) + (PADDING_Y / 2.0f), paint2);
    }

    private int getTagWidth(CharSequence charSequence, int i, int i2, Paint paint) {
        return Math.round(PADDING_X + paint.measureText(charSequence.subSequence(i, i2).toString()) + PADDING_X);
    }

    public int getSize(Paint paint, CharSequence charSequence, int i, int i2, FontMetricsInt fontMetricsInt) {
        paint.setTextSize(this.mTextSize);
        return getTagWidth(charSequence, i, i2, paint);
    }
}
