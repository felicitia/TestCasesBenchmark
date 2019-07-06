package com.etsy.android.stylekit.views.ratings;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.support.annotation.FloatRange;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.DrawableCompat;
import com.etsy.android.stylekit.a.b;
import com.etsy.android.stylekit.d;

public class RatingDrawable extends Drawable {
    private static final int STATE_EMPTY_ICON = 0;
    private static final int STATE_FILLED_ICON = 2;
    private static final int STATE_HALF_ICON = 1;
    private Drawable mEmptyIcon;
    private Drawable mFilledIcon;
    private Drawable mHalfIcon;
    private float mIconSize;
    private float mIconSpacing;
    private int[] mIconStates;
    private float mIconWidthRatio;
    private int mMaxRating;
    private float mRating;

    public int getOpacity() {
        return -1;
    }

    public void setAlpha(@IntRange(from = 0, to = 255) int i) {
    }

    public void setColorFilter(@Nullable ColorFilter colorFilter) {
    }

    public RatingDrawable(Context context) {
        this(context, d.a(context, b.token_rating_view__max_rating, 5), 0.0f, d.c(context, b.token_rating_view__icon_size), d.c(context, b.token_rating_view__icon_spacing));
    }

    public RatingDrawable(Context context, int i, float f, int i2, int i3) {
        this.mMaxRating = i;
        this.mIconStates = new int[this.mMaxRating];
        this.mRating = f;
        assignIconStates();
        this.mIconSize = (float) i2;
        this.mIconSpacing = (float) i3;
        ColorStateList b = d.b(context, b.token_rating_view__filled__color);
        ColorStateList b2 = d.b(context, b.token_rating_view__empty__color);
        this.mFilledIcon = d.a(context, b.token_rating_view__filled__icon);
        this.mEmptyIcon = d.a(context, b.token_rating_view__filled__icon);
        this.mHalfIcon = d.a(context, b.token_rating_view__half_filled__icon);
        DrawableCompat.setTint(this.mFilledIcon.mutate(), b.getDefaultColor());
        DrawableCompat.setTint(this.mEmptyIcon.mutate(), b2.getDefaultColor());
        DrawableCompat.setTint(this.mHalfIcon, b.getDefaultColor());
        this.mIconWidthRatio = ((float) this.mFilledIcon.getIntrinsicWidth()) / ((float) this.mFilledIcon.getIntrinsicHeight());
    }

    public void setRating(@FloatRange(from = 0.0d) float f) {
        boolean z = getHalfStepCount(f) != getHalfStepCount(this.mRating);
        this.mRating = f;
        if (z) {
            assignIconStates();
            invalidateSelf();
        }
    }

    public float getRating() {
        return this.mRating;
    }

    public int getMaxRating() {
        return this.mMaxRating;
    }

    public void setIconSize(int i) {
        this.mIconSize = (float) i;
        invalidateSelf();
    }

    public float getIconSize() {
        return this.mIconSize;
    }

    public float getIconVisualRating() {
        float f = 0.0f;
        int i = 0;
        while (i < this.mIconStates.length && this.mIconStates[i] != 0) {
            f = (float) (((double) f) + (this.mIconStates[i] == 1 ? 0.5d : 1.0d));
            i++;
        }
        return f;
    }

    private void assignIconStates() {
        int halfStepCount = getHalfStepCount(this.mRating);
        for (int i = 0; i < this.mMaxRating; i++) {
            int i2 = i * 2;
            if (i2 + 2 <= halfStepCount) {
                this.mIconStates[i] = 2;
            } else if (i2 > halfStepCount) {
                this.mIconStates[i] = 0;
            } else if (i2 + 1 == halfStepCount) {
                this.mIconStates[i] = 1;
            } else {
                this.mIconStates[i] = 0;
            }
        }
    }

    private int getHalfStepCount(float f) {
        return Math.round(f * 2.0f);
    }

    public void draw(@NonNull Canvas canvas) {
        int round = Math.round(this.mIconSize * this.mIconWidthRatio);
        for (int i = 0; i < this.mIconStates.length; i++) {
            int i2 = i * round;
            if (i != 0) {
                i2 = (int) (((float) i2) + (this.mIconSpacing * ((float) i)));
            }
            switch (this.mIconStates[i]) {
                case 1:
                    int i3 = i2 + round;
                    this.mEmptyIcon.setBounds(i2, 0, i3, (int) this.mIconSize);
                    this.mEmptyIcon.draw(canvas);
                    this.mHalfIcon.setBounds(i2, 0, i3, (int) this.mIconSize);
                    this.mHalfIcon.draw(canvas);
                    break;
                case 2:
                    this.mFilledIcon.setBounds(i2, 0, i2 + round, (int) this.mIconSize);
                    this.mFilledIcon.draw(canvas);
                    break;
                default:
                    this.mEmptyIcon.setBounds(i2, 0, i2 + round, (int) this.mIconSize);
                    this.mEmptyIcon.draw(canvas);
                    break;
            }
        }
    }

    public int getIntrinsicWidth() {
        return (int) ((this.mIconSize * this.mIconWidthRatio * ((float) this.mMaxRating)) + (this.mIconSpacing * ((float) (this.mMaxRating - 1))));
    }

    public int getIntrinsicHeight() {
        return (int) this.mIconSize;
    }

    public int getMinimumWidth() {
        return getIntrinsicWidth();
    }

    public int getMinimumHeight() {
        return getIntrinsicHeight();
    }
}
