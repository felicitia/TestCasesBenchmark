package com.etsy.android.stylekit;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.annotation.StyleRes;
import android.support.v4.widget.TextViewCompat;
import android.text.style.MetricAffectingSpan;
import android.widget.TextView;
import com.etsy.android.stylekit.a.b;
import uk.co.chrisjenx.calligraphy.CalligraphyTypefaceSpan;
import uk.co.chrisjenx.calligraphy.h;

/* compiled from: TypefaceUtil */
public class e {
    public static boolean a(TextView textView, @StringRes int i) {
        return uk.co.chrisjenx.calligraphy.e.a(textView.getContext(), textView, textView.getResources().getString(i));
    }

    public static Paint a(Context context, Paint paint, @StringRes int i) {
        paint.setTypeface(h.a(context.getAssets(), context.getString(i)));
        return paint;
    }

    public static MetricAffectingSpan a(Context context, @StringRes int i) {
        return new CalligraphyTypefaceSpan(h.a(context.getAssets(), context.getString(i)));
    }

    public static Boolean b(@NonNull TextView textView, @StyleRes int i) {
        Context context = textView.getContext();
        TextViewCompat.setTextAppearance(textView, i);
        TypedArray typedArray = null;
        try {
            TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(i, new int[]{b.fontPath});
            try {
                String string = obtainStyledAttributes.getString(0);
                obtainStyledAttributes.recycle();
                if (string != null) {
                    return Boolean.valueOf(uk.co.chrisjenx.calligraphy.e.a(context, textView, string));
                }
                return Boolean.valueOf(false);
            } catch (Throwable th) {
                th = th;
                typedArray = obtainStyledAttributes;
                typedArray.recycle();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            typedArray.recycle();
            throw th;
        }
    }
}
