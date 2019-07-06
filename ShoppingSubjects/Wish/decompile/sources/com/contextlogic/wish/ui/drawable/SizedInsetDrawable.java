package com.contextlogic.wish.ui.drawable;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;

public class SizedInsetDrawable extends InsetDrawable {
    private int mHeight;
    private int mWidth;
    private Drawable mWrappedDrawable;

    public SizedInsetDrawable(Drawable drawable, int i, int i2) {
        super(drawable, 0);
        this.mWrappedDrawable = drawable;
        this.mWidth = i;
        this.mHeight = i2;
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        int i = ((rect.right - rect.left) - this.mWidth) / 2;
        int i2 = rect.left + i;
        int i3 = rect.right - i;
        int i4 = ((rect.bottom - rect.top) - this.mHeight) / 2;
        this.mWrappedDrawable.setBounds(i2, rect.top + i4, i3, rect.bottom - i4);
    }
}
