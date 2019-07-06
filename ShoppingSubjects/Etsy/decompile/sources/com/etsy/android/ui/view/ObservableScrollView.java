package com.etsy.android.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

public class ObservableScrollView extends ScrollView {
    private a mCallbacks;
    private float mLastX;
    private float mLastY;
    private float mXDistance;
    private float mYDistance;

    public interface a {
        void a(int i);
    }

    public ObservableScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            this.mYDistance = 0.0f;
            this.mXDistance = 0.0f;
            this.mLastX = motionEvent.getX();
            this.mLastY = motionEvent.getY();
        } else if (action == 2) {
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            this.mXDistance += Math.abs(x - this.mLastX);
            this.mYDistance += Math.abs(y - this.mLastY);
            this.mLastX = x;
            this.mLastY = y;
            if (this.mXDistance > this.mYDistance) {
                return false;
            }
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    /* access modifiers changed from: protected */
    public void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        if (this.mCallbacks != null) {
            this.mCallbacks.a(i2);
        }
    }

    public int computeVerticalScrollRange() {
        return super.computeVerticalScrollRange();
    }

    public void setCallbacks(a aVar) {
        this.mCallbacks = aVar;
    }
}
