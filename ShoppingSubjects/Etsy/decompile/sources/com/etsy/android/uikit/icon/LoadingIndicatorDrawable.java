package com.etsy.android.uikit.icon;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class LoadingIndicatorDrawable extends Drawable {
    public void draw(@NonNull Canvas canvas) {
    }

    public int getOpacity() {
        return -1;
    }

    public void setAlpha(@IntRange(from = 0, to = 255) int i) {
    }

    public void setColorFilter(@Nullable ColorFilter colorFilter) {
    }
}
