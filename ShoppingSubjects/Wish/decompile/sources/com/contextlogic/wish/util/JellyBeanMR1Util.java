package com.contextlogic.wish.util;

import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v4.text.TextUtilsCompat;
import android.widget.TextView;
import java.util.Locale;

public final class JellyBeanMR1Util {
    public static void setCompoundDrawablesRelativeWithIntrinsicBounds(TextView textView, Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        if (VERSION.SDK_INT >= 17) {
            textView.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
            return;
        }
        boolean z = TextUtilsCompat.getLayoutDirectionFromLocale(Locale.getDefault()) == 0;
        Drawable drawable5 = z ? drawable : drawable3;
        if (z) {
            drawable = drawable3;
        }
        textView.setCompoundDrawablesWithIntrinsicBounds(drawable5, drawable2, drawable, drawable4);
    }
}
