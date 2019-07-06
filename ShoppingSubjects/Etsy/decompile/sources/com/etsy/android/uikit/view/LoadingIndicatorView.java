package com.etsy.android.uikit.view;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

public class LoadingIndicatorView extends AppCompatImageView {
    private ValueAnimator mDegreeAnimator;
    /* access modifiers changed from: private */
    public int mDegreeOffset = 0;
    private AnimatorListener mListener;
    private float mPivotX = 0.0f;
    private float mPivotY = 0.0f;

    public LoadingIndicatorView(Context context) {
        super(context);
    }

    public LoadingIndicatorView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public LoadingIndicatorView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.mPivotX = (float) (getMeasuredWidth() / 2);
        this.mPivotY = (float) (getMeasuredHeight() / 2);
    }

    /* access modifiers changed from: protected */
    public void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        if (this.mDegreeAnimator == null) {
            return;
        }
        if (i != 0) {
            this.mDegreeAnimator.cancel();
        } else {
            this.mDegreeAnimator.start();
        }
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mDegreeAnimator = ValueAnimator.ofInt(new int[]{0, 1});
        this.mListener = new AnimatorListener() {
            public void onAnimationCancel(Animator animator) {
            }

            public void onAnimationEnd(Animator animator) {
            }

            public void onAnimationStart(Animator animator) {
            }

            public void onAnimationRepeat(Animator animator) {
                LoadingIndicatorView.this.mDegreeOffset = LoadingIndicatorView.this.mDegreeOffset + 1;
                if (LoadingIndicatorView.this.mDegreeOffset >= 9) {
                    LoadingIndicatorView.this.mDegreeOffset = 1;
                }
                LoadingIndicatorView.this.invalidate();
            }
        };
        this.mDegreeAnimator.addListener(this.mListener);
        this.mDegreeAnimator.setDuration(75);
        this.mDegreeAnimator.setRepeatCount(-1);
        this.mDegreeAnimator.setInterpolator(new LinearInterpolator());
        if (getVisibility() == 0) {
            this.mDegreeAnimator.start();
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        this.mDegreeAnimator.cancel();
        this.mDegreeAnimator.removeListener(this.mListener);
        this.mListener = null;
        super.onDetachedFromWindow();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        canvas.rotate(45.0f * ((float) this.mDegreeOffset), this.mPivotX, this.mPivotY);
        super.onDraw(canvas);
    }
}
