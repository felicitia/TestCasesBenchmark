package com.etsy.android.uikit.text.typeface;

import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.style.TypefaceSpan;

public class CustomTypefaceSpan extends TypefaceSpan {
    private final Typeface mTypeface;

    public CustomTypefaceSpan(String str, Typeface typeface) {
        super(str);
        this.mTypeface = typeface;
    }

    public void updateDrawState(TextPaint textPaint) {
        applyCustomTypeface(textPaint, this.mTypeface);
    }

    public void updateMeasureState(TextPaint textPaint) {
        applyCustomTypeface(textPaint, this.mTypeface);
    }

    private static void applyCustomTypeface(Paint paint, Typeface typeface) {
        int i;
        Typeface typeface2 = paint.getTypeface();
        if (typeface2 == null) {
            i = 0;
        } else {
            i = typeface2.getStyle();
        }
        int style = i & (typeface.getStyle() ^ -1);
        if ((style & 1) != 0) {
            paint.setFakeBoldText(true);
        }
        if ((style & 2) != 0) {
            paint.setTextSkewX(-0.25f);
        }
        paint.setTypeface(typeface);
    }
}
