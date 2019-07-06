package com.etsy.android.stylekit;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.AttributeSet;
import android.widget.TextView;
import com.etsy.android.stylekit.a.j;

public class CompoundVectorTextView extends TextView {
    @ColorInt
    private int mColor;
    @Nullable
    private Drawable mDrawableBottom;
    @DrawableRes
    private int mDrawableBottomRes;
    @Nullable
    private Drawable mDrawableLeft;
    @DrawableRes
    private int mDrawableLeftRes;
    @Nullable
    private Drawable mDrawableRight;
    @DrawableRes
    private int mDrawableRightRes;
    @Nullable
    private Drawable mDrawableTop;
    @DrawableRes
    private int mDrawableTopRes;
    private int mPadding;
    private int mSize;
    private int mTopPadding;

    public CompoundVectorTextView(Context context) {
        super(context);
    }

    public CompoundVectorTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setAttributes(context, attributeSet);
        setupDrawable();
    }

    public CompoundVectorTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setAttributes(context, attributeSet);
        setupDrawable();
    }

    private void setAttributes(Context context, AttributeSet attributeSet) {
        if (!isInEditMode()) {
            TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, j.sk_CompoundVectorTextView, 0, 0);
            try {
                this.mDrawableLeftRes = obtainStyledAttributes.getResourceId(j.sk_CompoundVectorTextView_sk_drawableLeft, -1);
                this.mDrawableRightRes = obtainStyledAttributes.getResourceId(j.sk_CompoundVectorTextView_sk_drawableRight, -1);
                this.mDrawableTopRes = obtainStyledAttributes.getResourceId(j.sk_CompoundVectorTextView_sk_drawableTop, -1);
                this.mDrawableBottomRes = obtainStyledAttributes.getResourceId(j.sk_CompoundVectorTextView_sk_drawableBottom, -1);
                this.mSize = obtainStyledAttributes.getDimensionPixelSize(j.sk_CompoundVectorTextView_sk_drawableSize, -1);
                this.mTopPadding = obtainStyledAttributes.getDimensionPixelSize(j.sk_CompoundVectorTextView_sk_drawableExtraTopPadding, 0);
                this.mPadding = obtainStyledAttributes.getDimensionPixelSize(j.sk_CompoundVectorTextView_sk_drawablePadding, 0);
                this.mColor = obtainStyledAttributes.getColor(j.sk_CompoundVectorTextView_sk_drawableTint, -1);
            } finally {
                obtainStyledAttributes.recycle();
            }
        }
    }

    public void setDrawableTint(int i) {
        Drawable[] compoundDrawables;
        for (Drawable drawable : getCompoundDrawables()) {
            if (drawable != null) {
                DrawableCompat.setTint(drawable, i);
            }
        }
    }

    private void setupDrawable() {
        if (this.mDrawableLeftRes != -1) {
            this.mDrawableLeft = styleDrawableWithBounds(this.mDrawableLeftRes);
        }
        if (this.mDrawableRightRes != -1) {
            this.mDrawableRight = styleDrawableWithBounds(this.mDrawableRightRes);
        }
        if (this.mDrawableBottomRes != -1) {
            this.mDrawableBottom = styleDrawableWithBounds(this.mDrawableBottomRes);
        }
        if (this.mDrawableTopRes != -1) {
            this.mDrawableTop = styleDrawableWithBounds(this.mDrawableTopRes);
        }
        if (this.mSize != -1) {
            setCompoundDrawables(this.mDrawableLeft, this.mDrawableTop, this.mDrawableRight, this.mDrawableBottom);
        } else {
            setCompoundDrawablesWithIntrinsicBounds(this.mDrawableLeft, this.mDrawableTop, this.mDrawableRight, this.mDrawableBottom);
        }
    }

    private Drawable styleDrawableWithBounds(@DrawableRes int i) {
        Drawable mutate = VectorDrawableCompat.create(getContext().getResources(), i, getContext().getTheme()).mutate();
        if (this.mColor != -1) {
            DrawableCompat.setTint(mutate, this.mColor);
        }
        if (this.mSize != -1) {
            mutate.setBounds(0, this.mTopPadding, this.mSize, this.mSize + this.mTopPadding);
        }
        setCompoundDrawablePadding(this.mPadding);
        return mutate;
    }
}
