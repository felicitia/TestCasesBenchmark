package com.onfido.android.sdk.capture.utils;

import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.style.TypefaceSpan;
import kotlin.jvm.internal.Intrinsics;

public final class CustomTypefaceSpan extends TypefaceSpan {
    private final Typeface a;

    public CustomTypefaceSpan(String str, Typeface typeface) {
        Intrinsics.checkParameterIsNotNull(str, "family");
        Intrinsics.checkParameterIsNotNull(typeface, "newType");
        super(str);
        this.a = typeface;
    }

    private final void a(Paint paint, Typeface typeface) {
        Typeface typeface2 = paint.getTypeface();
        int style = (typeface2 == null ? 0 : typeface2.getStyle()) & (typeface.getStyle() ^ -1);
        if ((style & 1) != 0) {
            paint.setFakeBoldText(true);
        }
        if ((style & 2) != 0) {
            paint.setTextSkewX(-0.25f);
        }
        paint.setTypeface(typeface);
    }

    public void updateDrawState(TextPaint textPaint) {
        Intrinsics.checkParameterIsNotNull(textPaint, "ds");
        a(textPaint, this.a);
    }

    public void updateMeasureState(TextPaint textPaint) {
        Intrinsics.checkParameterIsNotNull(textPaint, "paint");
        a(textPaint, this.a);
    }
}
