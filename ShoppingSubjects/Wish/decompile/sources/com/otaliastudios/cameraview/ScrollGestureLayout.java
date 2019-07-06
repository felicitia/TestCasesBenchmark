package com.otaliastudios.cameraview;

import android.content.Context;
import android.graphics.PointF;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;

class ScrollGestureLayout extends GestureLayout {
    /* access modifiers changed from: private */
    public static final CameraLogger LOG = CameraLogger.create(TAG);
    private static final String TAG = "ScrollGestureLayout";
    private GestureDetector mDetector;
    /* access modifiers changed from: private */
    public float mDistance;
    /* access modifiers changed from: private */
    public boolean mNotify;

    public ScrollGestureLayout(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public void onInitialize(Context context) {
        super.onInitialize(context);
        this.mPoints = new PointF[]{new PointF(0.0f, 0.0f), new PointF(0.0f, 0.0f)};
        this.mDetector = new GestureDetector(context, new SimpleOnGestureListener() {
            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
                boolean z;
                CameraLogger access$000 = ScrollGestureLayout.LOG;
                boolean z2 = false;
                StringBuilder sb = new StringBuilder();
                sb.append("distanceX=");
                sb.append(f);
                StringBuilder sb2 = new StringBuilder();
                sb2.append("distanceY=");
                sb2.append(f2);
                access$000.i("onScroll:", sb.toString(), sb2.toString());
                if (motionEvent.getX() == ScrollGestureLayout.this.mPoints[0].x && motionEvent.getY() == ScrollGestureLayout.this.mPoints[0].y) {
                    if (ScrollGestureLayout.this.mType == Gesture.SCROLL_HORIZONTAL) {
                        z2 = true;
                    }
                    z = z2;
                } else {
                    z = Math.abs(f) >= Math.abs(f2);
                    ScrollGestureLayout.this.mType = z ? Gesture.SCROLL_HORIZONTAL : Gesture.SCROLL_VERTICAL;
                    ScrollGestureLayout.this.mPoints[0].set(motionEvent.getX(), motionEvent.getY());
                }
                ScrollGestureLayout.this.mPoints[1].set(motionEvent2.getX(), motionEvent2.getY());
                ScrollGestureLayout.this.mDistance = z ? f / ((float) ScrollGestureLayout.this.getWidth()) : f2 / ((float) ScrollGestureLayout.this.getHeight());
                ScrollGestureLayout.this.mDistance = z ? -ScrollGestureLayout.this.mDistance : ScrollGestureLayout.this.mDistance;
                ScrollGestureLayout.this.mNotify = true;
                return true;
            }
        });
        this.mDetector.setIsLongpressEnabled(false);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.mEnabled) {
            return false;
        }
        if (motionEvent.getAction() == 0) {
            this.mNotify = false;
        }
        this.mDetector.onTouchEvent(motionEvent);
        if (this.mNotify) {
            LOG.i("Notifying a gesture of type", this.mType.name());
        }
        return this.mNotify;
    }

    public float scaleValue(float f, float f2, float f3) {
        float f4 = (this.mDistance * (f3 - f2) * 2.0f) + f;
        if (f4 < f2) {
            f4 = f2;
        }
        if (f4 > f3) {
            f4 = f3;
        }
        CameraLogger cameraLogger = LOG;
        StringBuilder sb = new StringBuilder();
        sb.append("curr=");
        sb.append(f);
        StringBuilder sb2 = new StringBuilder();
        sb2.append("min=");
        sb2.append(f2);
        StringBuilder sb3 = new StringBuilder();
        sb3.append("max=");
        sb3.append(f3);
        StringBuilder sb4 = new StringBuilder();
        sb4.append("out=");
        sb4.append(f4);
        cameraLogger.i(sb.toString(), sb2.toString(), sb3.toString(), sb4.toString());
        return f4;
    }
}
