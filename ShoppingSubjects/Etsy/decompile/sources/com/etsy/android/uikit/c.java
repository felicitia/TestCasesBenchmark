package com.etsy.android.uikit;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.graphics.drawable.DrawableCompat;

/* compiled from: EtsyCompat */
public class c {
    public static Drawable a(Activity activity, @DrawableRes int i) {
        return VectorDrawableCompat.create(activity.getResources(), i, activity.getTheme()).mutate();
    }

    public static Drawable a(Context context, @DrawableRes int i) {
        return VectorDrawableCompat.create(context.getResources(), i, context.getTheme()).mutate();
    }

    public static Drawable a(Context context, @DrawableRes int i, @ColorRes int i2) {
        Drawable mutate = VectorDrawableCompat.create(context.getResources(), i, context.getTheme()).mutate();
        DrawableCompat.setTint(mutate, context.getResources().getColor(i2));
        return mutate;
    }
}
