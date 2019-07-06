package com.otaliastudios.cameraview;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.graphics.PointF;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

class TapGestureLayout extends GestureLayout {
    private GestureDetector mDetector;
    /* access modifiers changed from: private */
    public FrameLayout mFocusMarkerContainer;
    private ImageView mFocusMarkerFill;
    /* access modifiers changed from: private */
    public final Runnable mFocusMarkerHideRunnable = new Runnable() {
        public void run() {
            TapGestureLayout.this.onFocusEnd(false);
        }
    };
    /* access modifiers changed from: private */
    public boolean mNotify;

    public float scaleValue(float f, float f2, float f3) {
        return 0.0f;
    }

    public TapGestureLayout(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public void onInitialize(Context context) {
        super.onInitialize(context);
        this.mPoints = new PointF[]{new PointF(0.0f, 0.0f)};
        this.mDetector = new GestureDetector(context, new SimpleOnGestureListener() {
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                TapGestureLayout.this.mNotify = true;
                TapGestureLayout.this.mType = Gesture.TAP;
                return true;
            }

            public void onLongPress(MotionEvent motionEvent) {
                TapGestureLayout.this.mNotify = true;
                TapGestureLayout.this.mType = Gesture.LONG_TAP;
            }
        });
        this.mDetector.setIsLongpressEnabled(true);
        LayoutInflater.from(getContext()).inflate(R.layout.layout_focus_marker, this);
        this.mFocusMarkerContainer = (FrameLayout) findViewById(R.id.focusMarkerContainer);
        this.mFocusMarkerFill = (ImageView) findViewById(R.id.fill);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.mEnabled) {
            return false;
        }
        if (motionEvent.getAction() == 0) {
            this.mNotify = false;
        }
        this.mDetector.onTouchEvent(motionEvent);
        if (!this.mNotify) {
            return false;
        }
        this.mPoints[0].x = motionEvent.getX();
        this.mPoints[0].y = motionEvent.getY();
        return true;
    }

    public void onFocusStart(PointF pointF) {
        PointF pointF2 = pointF;
        removeCallbacks(this.mFocusMarkerHideRunnable);
        this.mFocusMarkerContainer.clearAnimation();
        this.mFocusMarkerFill.clearAnimation();
        float width = (float) ((int) (pointF2.x - ((float) (this.mFocusMarkerContainer.getWidth() / 2))));
        float width2 = (float) ((int) (pointF2.y - ((float) (this.mFocusMarkerContainer.getWidth() / 2))));
        this.mFocusMarkerContainer.setTranslationX(width);
        this.mFocusMarkerContainer.setTranslationY(width2);
        this.mFocusMarkerContainer.setScaleX(1.36f);
        this.mFocusMarkerContainer.setScaleY(1.36f);
        this.mFocusMarkerContainer.setAlpha(1.0f);
        this.mFocusMarkerFill.setScaleX(0.0f);
        this.mFocusMarkerFill.setScaleY(0.0f);
        this.mFocusMarkerFill.setAlpha(1.0f);
        animate(this.mFocusMarkerContainer, 1.0f, 1.0f, 300, 0, null);
        animate(this.mFocusMarkerFill, 1.0f, 1.0f, 300, 0, new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                TapGestureLayout.this.postDelayed(TapGestureLayout.this.mFocusMarkerHideRunnable, 2000);
            }
        });
    }

    public void onFocusEnd(boolean z) {
        if (z) {
            animate(this.mFocusMarkerContainer, 1.0f, 0.0f, 500, 0, null);
            animate(this.mFocusMarkerFill, 1.0f, 0.0f, 500, 0, null);
            return;
        }
        animate(this.mFocusMarkerFill, 0.0f, 0.0f, 500, 0, null);
        animate(this.mFocusMarkerContainer, 1.36f, 1.0f, 500, 0, new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                TapGestureLayout.animate(TapGestureLayout.this.mFocusMarkerContainer, 1.36f, 0.0f, 200, 1000, null);
            }
        });
    }

    /* access modifiers changed from: private */
    public static void animate(View view, float f, float f2, long j, long j2, AnimatorListener animatorListener) {
        view.animate().scaleX(f).scaleY(f).alpha(f2).setDuration(j).setStartDelay(j2).setListener(animatorListener).start();
    }
}
