package com.etsy.android.uikit.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import com.etsy.android.lib.a.g;

public class LoadingIndicatorDrawable extends Drawable implements Runnable {
    private boolean isRunning;
    private final Drawable loadingDrawable;
    private final int loadingHeight;
    private final int loadingWidth;
    private int mDegreeOffset;
    private float pivotX = 0.0f;
    private float pivotY = 0.0f;

    public int getOpacity() {
        return -1;
    }

    public void setAlpha(@IntRange(from = 0, to = 255) int i) {
    }

    public void setColorFilter(@Nullable ColorFilter colorFilter) {
    }

    public LoadingIndicatorDrawable(Context context) {
        this.loadingDrawable = ContextCompat.getDrawable(context, g.progress_spinner_etsy);
        this.loadingWidth = this.loadingDrawable.getIntrinsicWidth();
        this.loadingHeight = this.loadingDrawable.getIntrinsicHeight();
    }

    public void draw(@NonNull Canvas canvas) {
        if (!this.isRunning) {
            int width = canvas.getWidth();
            int height = canvas.getHeight();
            int i = width / 2;
            this.pivotX = (float) i;
            int i2 = height / 2;
            this.pivotY = (float) i2;
            int i3 = i2 - (this.loadingHeight / 2);
            int i4 = i2 + (this.loadingHeight / 2);
            this.loadingDrawable.setBounds(i - (this.loadingWidth / 2), i4, i + (this.loadingWidth / 2), i3);
        }
        canvas.rotate(45.0f * ((float) this.mDegreeOffset), this.pivotX, this.pivotY);
        this.loadingDrawable.draw(canvas);
        unscheduleSelf(this);
        scheduleSelf(this, 75);
    }

    public void run() {
        this.isRunning = true;
        this.mDegreeOffset++;
        if (this.mDegreeOffset > 7) {
            this.mDegreeOffset = 0;
        }
        invalidateSelf();
    }
}
