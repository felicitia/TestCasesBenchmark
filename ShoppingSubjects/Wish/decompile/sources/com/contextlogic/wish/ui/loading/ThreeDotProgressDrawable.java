package com.contextlogic.wish.ui.loading;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import android.support.annotation.Keep;
import android.view.animation.AccelerateDecelerateInterpolator;
import com.contextlogic.wish.R;
import com.contextlogic.wish.application.WishApplication;
import java.util.ArrayList;
import java.util.Iterator;

public class ThreeDotProgressDrawable extends Drawable implements Animatable, Callback {
    /* access modifiers changed from: private */
    public AnimatorSet mAnimatorSet;
    public int mBouncingDotRadius;
    private final Rect mBounds = new Rect();
    private int mColor;
    public int mDistanceBetweenDots;
    public int mDotRadius;
    private ArrayList<GrowableDotDrawable> mDots;
    /* access modifiers changed from: private */
    public boolean mShowing;

    public int getOpacity() {
        return -2;
    }

    @Keep
    public ThreeDotProgressDrawable(int i) {
        this.mColor = i;
        init();
    }

    @Keep
    public void init() {
        Resources resources = WishApplication.getInstance().getResources();
        this.mDotRadius = resources.getDimensionPixelSize(R.dimen.loading_spinner_dot_diameter_medium) / 2;
        this.mBouncingDotRadius = resources.getDimensionPixelSize(R.dimen.loading_spinner_bouncing_dot_diameter_medium) / 2;
        this.mDistanceBetweenDots = resources.getDimensionPixelSize(R.dimen.loading_spinner_distance_between_dots_medium);
        this.mShowing = false;
        this.mAnimatorSet = new AnimatorSet();
        this.mDots = new ArrayList<>();
        initDots();
    }

    public void hide() {
        this.mShowing = false;
        stopAnimations();
    }

    public void show() {
        if (!this.mShowing) {
            startAnimations();
            this.mShowing = true;
        }
    }

    public void draw(Canvas canvas) {
        if (this.mShowing) {
            Iterator it = this.mDots.iterator();
            while (it.hasNext()) {
                ((GrowableDotDrawable) it.next()).draw(canvas);
            }
        }
    }

    public void setAlpha(int i) {
        Iterator it = this.mDots.iterator();
        while (it.hasNext()) {
            ((GrowableDotDrawable) it.next()).setAlpha(i);
        }
    }

    public void setColorFilter(ColorFilter colorFilter) {
        Iterator it = this.mDots.iterator();
        while (it.hasNext()) {
            ((GrowableDotDrawable) it.next()).setColorFilter(colorFilter);
        }
    }

    public void setDotBounds() {
        int i = (this.mBouncingDotRadius - this.mDotRadius) + (this.mDotRadius * 2);
        int i2 = this.mBouncingDotRadius * 2;
        for (int i3 = 1; i3 <= 3; i3++) {
            ((GrowableDotDrawable) this.mDots.get(i3 - 1)).setBounds(this.mBounds.left, this.mBounds.top + 0, this.mBounds.right + ((this.mDistanceBetweenDots + i) * i3), this.mBounds.bottom - i2);
        }
    }

    public void start() {
        if (!this.mShowing) {
            show();
            invalidateSelf();
        }
    }

    public void stop() {
        if (this.mShowing) {
            hide();
            invalidateSelf();
        }
    }

    public boolean isRunning() {
        return this.mShowing;
    }

    private void initDots() {
        this.mDots.clear();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 3; i++) {
            GrowableDotDrawable growableDotDrawable = new GrowableDotDrawable(this.mColor, (float) this.mDotRadius, (float) this.mBouncingDotRadius);
            growableDotDrawable.setCallback(this);
            growableDotDrawable.setRadius((float) this.mDotRadius);
            this.mDots.add(growableDotDrawable);
            long j = (long) (i * 100);
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(growableDotDrawable, "radius", new float[]{(float) this.mDotRadius, (float) this.mBouncingDotRadius, (float) this.mDotRadius});
            ofFloat.setDuration(500);
            ofFloat.setInterpolator(new AccelerateDecelerateInterpolator());
            ofFloat.setStartDelay(j);
            ofFloat.setFloatValues(new float[]{(float) this.mDotRadius, ((float) this.mDotRadius) * 2.0f, (float) this.mDotRadius});
            arrayList.add(ofFloat);
        }
        this.mAnimatorSet.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                if (ThreeDotProgressDrawable.this.isVisible() && ThreeDotProgressDrawable.this.mAnimatorSet != null && animator != null && ThreeDotProgressDrawable.this.mShowing) {
                    ThreeDotProgressDrawable.this.mAnimatorSet.start();
                }
            }
        });
        this.mAnimatorSet.playTogether(arrayList);
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.mBounds.left = rect.left - ((((this.mBouncingDotRadius * 2) + this.mDistanceBetweenDots) * 3) / 2);
        this.mBounds.right = rect.right;
        this.mBounds.top = rect.top;
        this.mBounds.bottom = rect.bottom;
        setDotBounds();
    }

    public void invalidateDrawable(Drawable drawable) {
        super.invalidateSelf();
    }

    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        super.unscheduleSelf(runnable);
    }

    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        super.scheduleSelf(runnable, j);
    }

    private void startAnimations() {
        if (this.mAnimatorSet != null) {
            this.mAnimatorSet.start();
        }
    }

    private void stopAnimations() {
        if (this.mAnimatorSet != null) {
            this.mAnimatorSet.cancel();
        }
    }
}
