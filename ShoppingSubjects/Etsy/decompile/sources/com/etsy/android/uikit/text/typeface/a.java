package com.etsy.android.uikit.text.typeface;

import android.content.Context;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;
import java.util.HashMap;

/* compiled from: TypefaceCache */
public class a {
    private static a b = new a();
    private final HashMap<String, Typeface> a = new HashMap<>();

    /* renamed from: com.etsy.android.uikit.text.typeface.a$a reason: collision with other inner class name */
    /* compiled from: TypefaceCache */
    private static class C0112a extends MetricAffectingSpan {
        private Typeface a;

        public C0112a(Typeface typeface) {
            this.a = typeface;
        }

        public void updateMeasureState(TextPaint textPaint) {
            textPaint.setTypeface(this.a);
            textPaint.setFlags(textPaint.getFlags() | 128);
        }

        public void updateDrawState(TextPaint textPaint) {
            textPaint.setTypeface(this.a);
            textPaint.setFlags(textPaint.getFlags() | 128);
        }
    }

    private a() {
    }

    public static a a() {
        return b;
    }

    public Typeface a(String str) {
        Typeface typeface = (Typeface) this.a.get(str);
        if (typeface != null) {
            return typeface;
        }
        Typeface create = Typeface.create(str, 0);
        this.a.put(str, create);
        return create;
    }

    public Typeface a(Context context, String str) {
        Typeface typeface = (Typeface) this.a.get(str);
        if (typeface != null) {
            return typeface;
        }
        Typeface createFromAsset = Typeface.createFromAsset(context.getAssets(), str);
        this.a.put(str, createFromAsset);
        return createFromAsset;
    }

    public SpannableString a(Context context, String str, String str2) {
        SpannableString spannableString = new SpannableString(str2);
        spannableString.setSpan(new C0112a(a(context, str)), 0, spannableString.length(), 33);
        return spannableString;
    }
}
