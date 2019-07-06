package com.etsy.android.stylekit;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.AttrRes;
import android.support.annotation.Dimension;
import android.support.v7.content.res.AppCompatResources;
import android.util.TypedValue;

/* compiled from: ThemeAttributeResolver */
public class d {
    public static Drawable a(Context context, @AttrRes int i) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(i, typedValue, true);
        return AppCompatResources.getDrawable(context, typedValue.resourceId);
    }

    public static ColorStateList b(Context context, @AttrRes int i) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(new int[]{i});
        ColorStateList colorStateList = obtainStyledAttributes.getColorStateList(0);
        obtainStyledAttributes.recycle();
        return colorStateList;
    }

    public static boolean a(Context context, @AttrRes int i, boolean z) {
        TypedValue typedValue = new TypedValue();
        boolean z2 = true;
        context.getTheme().resolveAttribute(i, typedValue, true);
        if (typedValue.type == 1) {
            return a(context, typedValue.resourceId, z);
        }
        if (typedValue.type != 18) {
            return z;
        }
        if (typedValue.data == 0) {
            z2 = false;
        }
        return z2;
    }

    @Dimension
    public static int c(Context context, @AttrRes int i) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(i, typedValue, true);
        if (typedValue.type == 1) {
            return c(context, typedValue.resourceId);
        }
        if (typedValue.type == 5) {
            return TypedValue.complexToDimensionPixelSize(typedValue.data, context.getResources().getDisplayMetrics());
        }
        return -1;
    }

    public static int a(Context context, @AttrRes int i, int i2) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(new int[]{i});
        int i3 = obtainStyledAttributes.getInt(0, i2);
        obtainStyledAttributes.recycle();
        return i3;
    }

    public static int d(Context context, @AttrRes int i) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(i, typedValue, true);
        return typedValue.resourceId;
    }
}
