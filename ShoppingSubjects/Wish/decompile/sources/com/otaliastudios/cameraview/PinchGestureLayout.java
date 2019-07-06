package com.otaliastudios.cameraview;

import android.content.Context;
import android.graphics.PointF;
import android.os.Build.VERSION;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener;

class PinchGestureLayout extends GestureLayout {
    /* access modifiers changed from: private */
    public float mAdditionFactor = 0.0f;
    ScaleGestureDetector mDetector;
    /* access modifiers changed from: private */
    public boolean mNotify;

    public PinchGestureLayout(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public void onInitialize(Context context) {
        super.onInitialize(context);
        this.mPoints = new PointF[]{new PointF(0.0f, 0.0f), new PointF(0.0f, 0.0f)};
        this.mDetector = new ScaleGestureDetector(context, new SimpleOnScaleGestureListener() {
            public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
                PinchGestureLayout.this.mNotify = true;
                PinchGestureLayout.this.mAdditionFactor = (scaleGestureDetector.getScaleFactor() - 1.0f) * 2.0f;
                return true;
            }
        });
        if (VERSION.SDK_INT >= 19) {
            this.mDetector.setQuickScaleEnabled(false);
        }
        this.mType = Gesture.PINCH;
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
        this.mPoints[0].x = motionEvent.getX(0);
        this.mPoints[0].y = motionEvent.getY(0);
        if (motionEvent.getPointerCount() > 1) {
            this.mPoints[1].x = motionEvent.getX(1);
            this.mPoints[1].y = motionEvent.getY(1);
        }
        return true;
    }

    public float scaleValue(float f, float f2, float f3) {
        float f4 = f + (this.mAdditionFactor * (f3 - f2));
        if (f4 < f2) {
            f4 = f2;
        }
        return f4 > f3 ? f3 : f4;
    }
}
