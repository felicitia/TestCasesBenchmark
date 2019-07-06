package com.otaliastudios.cameraview;

import android.content.Context;
import android.graphics.PointF;
import android.widget.FrameLayout;

abstract class GestureLayout extends FrameLayout {
    protected boolean mEnabled;
    protected PointF[] mPoints;
    protected Gesture mType;

    /* access modifiers changed from: protected */
    public void onInitialize(Context context) {
    }

    public abstract float scaleValue(float f, float f2, float f3);

    public GestureLayout(Context context) {
        super(context);
        onInitialize(context);
    }

    public void enable(boolean z) {
        this.mEnabled = z;
    }

    public final Gesture getGestureType() {
        return this.mType;
    }

    public final PointF[] getPoints() {
        return this.mPoints;
    }
}
