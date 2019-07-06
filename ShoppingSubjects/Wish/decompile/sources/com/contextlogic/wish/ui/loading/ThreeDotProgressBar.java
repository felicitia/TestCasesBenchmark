package com.contextlogic.wish.ui.loading;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.annotation.Keep;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;
import android.view.animation.AccelerateDecelerateInterpolator;
import com.contextlogic.wish.R;
import com.contextlogic.wish.application.WishApplication;
import java.util.ArrayList;
import java.util.Iterator;

public class ThreeDotProgressBar extends View {
    /* access modifiers changed from: private */
    public AnimatorSet mAnimatorSet;
    public int mBouncingDotRadius;
    private int mColor = WishApplication.getInstance().getResources().getColor(R.color.main_primary);
    public int mDistanceBetweenDots;
    public int mDotRadius;
    private ArrayList<GrowableDotDrawable> mDots;
    private int mOffset = WishApplication.getInstance().getResources().getDimensionPixelOffset(R.dimen.loading_spinner_dot_offset);
    private boolean mShowing;
    private boolean mSmallSize = false;

    @Keep
    public ThreeDotProgressBar(Context context) {
        super(context);
        init();
    }

    @Keep
    public ThreeDotProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(attributeSet);
    }

    public ThreeDotProgressBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(attributeSet);
    }

    @Keep
    public void init(AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(attributeSet, R.styleable.ThreeDotProgressBar, 0, 0);
            try {
                this.mColor = obtainStyledAttributes.getInt(0, WishApplication.getInstance().getResources().getColor(R.color.main_primary));
                this.mSmallSize = obtainStyledAttributes.getBoolean(1, false);
            } finally {
                obtainStyledAttributes.recycle();
            }
        }
        init();
    }

    @Keep
    public void init() {
        if (this.mSmallSize) {
            this.mDotRadius = WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.loading_spinner_dot_diameter_small) / 2;
            this.mBouncingDotRadius = WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.loading_spinner_bouncing_dot_diameter_small) / 2;
            this.mDistanceBetweenDots = WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.loading_spinner_distance_between_dots_small);
        } else {
            this.mDotRadius = WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.loading_spinner_dot_diameter_medium) / 2;
            this.mBouncingDotRadius = WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.loading_spinner_bouncing_dot_diameter_medium) / 2;
            this.mDistanceBetweenDots = WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.loading_spinner_distance_between_dots_medium);
        }
        this.mShowing = false;
        this.mAnimatorSet = new AnimatorSet();
        this.mDots = new ArrayList<>();
        initDots();
        show();
    }

    public void onDetachedFromWindow() {
        hide();
        super.onDetachedFromWindow();
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        show();
    }

    public void hide() {
        stopAnimations();
        this.mShowing = false;
    }

    public void show() {
        if (!this.mShowing) {
            this.mShowing = true;
            startAnimations();
        }
    }

    public void reset() {
        this.mAnimatorSet.cancel();
        this.mShowing = false;
        initDots();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        if (isVisible()) {
            Iterator it = this.mDots.iterator();
            while (it.hasNext()) {
                ((GrowableDotDrawable) it.next()).draw(canvas);
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean verifyDrawable(Drawable drawable) {
        if (isVisible()) {
            return this.mDots.contains(drawable);
        }
        return super.verifyDrawable(drawable);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        setMeasuredDimension(((this.mDistanceBetweenDots + this.mDotRadius) * 3) - this.mOffset, this.mBouncingDotRadius * 2);
    }

    private void initDots() {
        this.mDots.clear();
        ArrayList arrayList = new ArrayList();
        int i = this.mBouncingDotRadius - this.mDotRadius;
        int i2 = this.mBouncingDotRadius * 2;
        int i3 = (this.mDotRadius * 2) + i;
        int i4 = i;
        for (int i5 = 1; i5 <= 3; i5++) {
            GrowableDotDrawable growableDotDrawable = new GrowableDotDrawable(this.mColor, (float) this.mDotRadius, (float) this.mBouncingDotRadius);
            growableDotDrawable.setCallback(this);
            growableDotDrawable.setRadius((float) this.mDotRadius);
            growableDotDrawable.setBounds(i4, 0, i3, i2);
            this.mDots.add(growableDotDrawable);
            long j = (long) ((i5 - 1) * 100);
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(growableDotDrawable, "radius", new float[]{(float) this.mDotRadius, (float) this.mBouncingDotRadius, (float) this.mDotRadius});
            ofFloat.setDuration(500);
            ofFloat.setInterpolator(new AccelerateDecelerateInterpolator());
            ofFloat.setStartDelay(j);
            ofFloat.setFloatValues(new float[]{(float) this.mDotRadius, ((float) this.mDotRadius) * 2.0f, (float) this.mDotRadius});
            i4 += this.mDistanceBetweenDots;
            i3 += this.mDistanceBetweenDots;
            arrayList.add(ofFloat);
        }
        this.mAnimatorSet.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                if (ThreeDotProgressBar.this.isVisible()) {
                    ThreeDotProgressBar.this.mAnimatorSet.start();
                }
            }
        });
        this.mAnimatorSet.playTogether(arrayList);
    }

    /* access modifiers changed from: protected */
    public void startAnimations() {
        if (this.mAnimatorSet != null) {
            this.mAnimatorSet.start();
        }
    }

    /* access modifiers changed from: protected */
    public void stopAnimations() {
        if (this.mAnimatorSet != null) {
            this.mAnimatorSet.cancel();
        }
    }

    /* access modifiers changed from: protected */
    public void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        if (isVisible()) {
            show();
        } else {
            hide();
        }
    }

    public boolean isVisible() {
        return getVisibility() == 0 && getWindowVisibility() == 0 && isShowing();
    }

    private boolean isShowing() {
        View view = this;
        while (view.getVisibility() == 0) {
            ViewParent parent = view.getParent();
            if (parent == null) {
                return ViewCompat.isAttachedToWindow(view);
            }
            if (!(parent instanceof View)) {
                return true;
            }
            view = (View) parent;
            if (view == null) {
                return true;
            }
        }
        return false;
    }
}
