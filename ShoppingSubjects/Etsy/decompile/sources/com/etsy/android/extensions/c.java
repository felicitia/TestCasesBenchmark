package com.etsy.android.extensions;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import com.etsy.android.lib.util.aj;
import kotlin.jvm.internal.p;

/* compiled from: Context.kt */
public final class c {
    public static final void a(Context context, @StringRes int i) {
        if (context != null) {
            aj.b(context, i);
        }
    }

    @ColorInt
    public static final int b(Context context, @ColorRes int i) {
        p.b(context, "$receiver");
        return ContextCompat.getColor(context, i);
    }

    public static final Drawable c(Context context, @DrawableRes int i) {
        p.b(context, "$receiver");
        Drawable drawable = ContextCompat.getDrawable(context, i);
        p.a((Object) drawable, "ContextCompat.getDrawable(this, drawableRes)");
        return drawable;
    }
}
