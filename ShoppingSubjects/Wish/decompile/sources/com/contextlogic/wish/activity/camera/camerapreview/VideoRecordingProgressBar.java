package com.contextlogic.wish.activity.camera.camerapreview;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import com.contextlogic.wish.ui.loading.CircleProgressBar;

public class VideoRecordingProgressBar extends CircleProgressBar {
    private int mCycle = 0;

    public VideoRecordingProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        float f = (this.mProgress * 360.0f) / ((float) this.mMax);
        if (this.mCycle == 0) {
            canvas.drawArc(this.mRectF, -90.0f, f, false, this.mForegroundPaint);
            return;
        }
        canvas.drawArc(this.mRectF, f - 0.049804688f, 360.0f - f, false, this.mForegroundPaint);
    }

    public void onCycleCompleted() {
        this.mCycle = (this.mCycle + 1) % 2;
    }

    public void reset() {
        setProgress(0.0f);
        this.mCycle = 0;
    }
}
