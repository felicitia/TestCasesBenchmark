package com.contextlogic.wish.util;

import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.widget.TextView;

public final class DrawableUtil {
    public static Drawable tintDrawable(Drawable drawable, int i) {
        Drawable wrap = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(wrap, i);
        DrawableCompat.setTintMode(wrap, Mode.SRC_IN);
        return wrap;
    }

    public static void tintCompoundDrawables(TextView textView, int i) {
        Drawable[] compoundDrawables = textView.getCompoundDrawables();
        Drawable[] drawableArr = new Drawable[compoundDrawables.length];
        for (int i2 = 0; i2 < compoundDrawables.length; i2++) {
            Drawable drawable = compoundDrawables[i2];
            if (drawable != null) {
                Drawable wrap = DrawableCompat.wrap(drawable);
                DrawableCompat.setTint(wrap, i);
                DrawableCompat.setTintMode(wrap, Mode.SRC_IN);
                drawableArr[i2] = wrap;
            }
        }
        textView.setCompoundDrawables(drawableArr[0], drawableArr[1], drawableArr[2], drawableArr[3]);
    }
}
