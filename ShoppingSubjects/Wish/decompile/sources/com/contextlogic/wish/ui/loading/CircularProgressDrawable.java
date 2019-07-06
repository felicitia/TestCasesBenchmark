package com.contextlogic.wish.ui.loading;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ObjectAnimator;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Keep;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

public class CircularProgressDrawable extends Drawable implements Animatable {
    private static final Interpolator ANGLE_INTERPOLATOR = new LinearInterpolator();
    private static final Interpolator SWEEP_INTERPOLATOR = new DecelerateInterpolator();
    private int angleAnimatorDuration = 2000;
    private final RectF fBounds = new RectF();
    private float mCurrentGlobalAngle;
    private float mCurrentGlobalAngleOffset;
    private float mCurrentSweepAngle;
    private boolean mModeAppearing;
    private ObjectAnimator mObjectAnimatorAngle;
    private ObjectAnimator mObjectAnimatorSweep;
    private Paint mPaint;
    private boolean mRunning;
    private float mStrokeWidth;
    private int sweepAnimatorDuration = 600;

    public int getOpacity() {
        return -2;
    }

    public CircularProgressDrawable(int i, float f, int i2, int i3) {
        this.mStrokeWidth = f;
        this.mPaint = new Paint();
        this.mPaint.setAntiAlias(true);
        this.mPaint.setStyle(Style.STROKE);
        this.mPaint.setColor(i);
        this.mPaint.setStrokeWidth(f);
        setCircleAnimationDuration(i2);
        setSweepAnimationDuration(i3);
        setupAnimations();
    }

    public void setCircleAnimationDuration(int i) {
        this.angleAnimatorDuration = i;
    }

    public void setSweepAnimationDuration(int i) {
        this.sweepAnimatorDuration = i;
    }

    public void draw(Canvas canvas) {
        float f;
        float f2 = this.mCurrentGlobalAngle - this.mCurrentGlobalAngleOffset;
        float f3 = this.mCurrentSweepAngle;
        if (!this.mModeAppearing) {
            f2 += f3;
            f = (360.0f - f3) - 30.0f;
        } else {
            f = f3 + 30.0f;
        }
        canvas.drawArc(this.fBounds, f2, f, false, this.mPaint);
    }

    public void setAlpha(int i) {
        this.mPaint.setAlpha(i);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.mPaint.setColorFilter(colorFilter);
    }

    /* access modifiers changed from: private */
    public void toggleAppearingMode() {
        this.mModeAppearing = !this.mModeAppearing;
        if (this.mModeAppearing) {
            this.mCurrentGlobalAngleOffset = (this.mCurrentGlobalAngleOffset + 60.0f) % 360.0f;
        }
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.fBounds.left = ((float) rect.left) + (this.mStrokeWidth / 2.0f) + 0.5f;
        this.fBounds.right = (((float) rect.right) - (this.mStrokeWidth / 2.0f)) - 0.5f;
        this.fBounds.top = ((float) rect.top) + (this.mStrokeWidth / 2.0f) + 0.5f;
        this.fBounds.bottom = (((float) rect.bottom) - (this.mStrokeWidth / 2.0f)) - 0.5f;
    }

    @Keep
    public void setAngle(float f) {
        setCurrentGlobalAngle(f);
    }

    @Keep
    public void setArc(float f) {
        setCurrentSweepAngle(f);
    }

    private void setupAnimations() {
        this.mObjectAnimatorAngle = ObjectAnimator.ofFloat(this, "angle", new float[]{360.0f});
        this.mObjectAnimatorAngle.setInterpolator(ANGLE_INTERPOLATOR);
        this.mObjectAnimatorAngle.setDuration((long) this.angleAnimatorDuration);
        this.mObjectAnimatorAngle.setRepeatMode(1);
        this.mObjectAnimatorAngle.setRepeatCount(-1);
        this.mObjectAnimatorSweep = ObjectAnimator.ofFloat(this, "arc", new float[]{300.0f});
        this.mObjectAnimatorSweep.setInterpolator(SWEEP_INTERPOLATOR);
        this.mObjectAnimatorSweep.setDuration((long) this.sweepAnimatorDuration);
        this.mObjectAnimatorSweep.setRepeatMode(1);
        this.mObjectAnimatorSweep.setRepeatCount(-1);
        this.mObjectAnimatorSweep.addListener(new AnimatorListener() {
            public void onAnimationCancel(Animator animator) {
            }

            public void onAnimationEnd(Animator animator) {
            }

            public void onAnimationStart(Animator animator) {
            }

            public void onAnimationRepeat(Animator animator) {
                CircularProgressDrawable.this.toggleAppearingMode();
            }
        });
    }

    public void start() {
        if (!isRunning()) {
            this.mRunning = true;
            this.mObjectAnimatorAngle.start();
            this.mObjectAnimatorSweep.start();
            invalidateSelf();
        }
    }

    public void stop() {
        if (isRunning()) {
            this.mRunning = false;
            this.mObjectAnimatorAngle.cancel();
            this.mObjectAnimatorSweep.cancel();
            invalidateSelf();
        }
    }

    public boolean isRunning() {
        return this.mRunning;
    }

    public void setCurrentGlobalAngle(float f) {
        this.mCurrentGlobalAngle = f;
        invalidateSelf();
    }

    public void setCurrentSweepAngle(float f) {
        this.mCurrentSweepAngle = f;
        invalidateSelf();
    }
}
