package com.contextlogic.wish.ui.drawable;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.SystemClock;

public class FadingDrawable extends BitmapDrawable {
    private int mAlpha = 255;
    private boolean mAnimating;
    private Drawable mPlaceholder;
    private long mStartTimeMillis;

    public FadingDrawable(Context context, Bitmap bitmap, Drawable drawable, boolean z) {
        super(context.getResources(), bitmap);
        if (z) {
            this.mPlaceholder = drawable;
            this.mAnimating = true;
            this.mStartTimeMillis = SystemClock.uptimeMillis();
        }
    }

    public void draw(Canvas canvas) {
        if (!this.mAnimating) {
            super.draw(canvas);
            return;
        }
        float uptimeMillis = ((float) (SystemClock.uptimeMillis() - this.mStartTimeMillis)) / 200.0f;
        if (uptimeMillis >= 1.0f) {
            this.mAnimating = false;
            this.mPlaceholder = null;
            super.draw(canvas);
            return;
        }
        if (this.mPlaceholder != null) {
            this.mPlaceholder.draw(canvas);
        }
        super.setAlpha((int) (((float) this.mAlpha) * uptimeMillis));
        super.draw(canvas);
        super.setAlpha(this.mAlpha);
        if (VERSION.SDK_INT <= 10) {
            invalidateSelf();
        }
    }

    public void setAlpha(int i) {
        this.mAlpha = i;
        if (this.mPlaceholder != null) {
            this.mPlaceholder.setAlpha(i);
        }
        super.setAlpha(i);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        if (this.mPlaceholder != null) {
            this.mPlaceholder.setColorFilter(colorFilter);
        }
        super.setColorFilter(colorFilter);
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        if (this.mPlaceholder != null) {
            this.mPlaceholder.setBounds(rect);
        }
        super.onBoundsChange(rect);
    }
}
