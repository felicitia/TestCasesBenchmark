package com.etsy.android.uikit.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import com.etsy.android.lib.a.q;

public class ForegroundImageView extends AppCompatImageView {
    @Nullable
    private Drawable foreground;

    public ForegroundImageView(Context context) {
        super(context);
        init(context, null);
    }

    public ForegroundImageView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet);
    }

    public ForegroundImageView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet);
    }

    private void init(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, q.ForegroundImageView);
            Drawable drawable = obtainStyledAttributes.getDrawable(q.ForegroundImageView_foreground);
            if (drawable != null) {
                setForegroundCompat(drawable);
            }
            obtainStyledAttributes.recycle();
        }
    }

    public void setForegroundResource(@DrawableRes int i) {
        setForegroundCompat(ContextCompat.getDrawable(getContext(), i));
    }

    public void setForegroundCompat(@Nullable Drawable drawable) {
        if (VERSION.SDK_INT >= 23) {
            super.setForeground(drawable);
        } else if (this.foreground != drawable) {
            if (this.foreground != null) {
                this.foreground.setCallback(null);
                unscheduleDrawable(this.foreground);
            }
            this.foreground = drawable;
            if (drawable != null) {
                drawable.setCallback(this);
                if (drawable.isStateful()) {
                    drawable.setState(getDrawableState());
                }
            }
            requestLayout();
            invalidate();
        }
    }

    /* access modifiers changed from: protected */
    public boolean verifyDrawable(@NonNull Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.foreground;
    }

    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        if (this.foreground != null) {
            this.foreground.jumpToCurrentState();
        }
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        if (this.foreground != null && this.foreground.isStateful()) {
            this.foreground.setState(getDrawableState());
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.foreground != null) {
            this.foreground.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
            invalidate();
        }
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (this.foreground != null) {
            this.foreground.setBounds(0, 0, i, i2);
            invalidate();
        }
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (this.foreground != null) {
            this.foreground.draw(canvas);
        }
    }
}
