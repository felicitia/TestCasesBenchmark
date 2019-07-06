package com.etsy.android.uikit.view;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewParent;

public class SwipeRefreshObeyRequestDisallowInterceptTouchEventLayout extends SwipeRefreshLayout {
    private boolean mInterceptTouchEvents = true;

    public SwipeRefreshObeyRequestDisallowInterceptTouchEventLayout(Context context) {
        super(context);
    }

    public SwipeRefreshObeyRequestDisallowInterceptTouchEventLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 1 || action == 3) {
            this.mInterceptTouchEvents = true;
            return super.onInterceptTouchEvent(motionEvent);
        } else if (!this.mInterceptTouchEvents) {
            return false;
        } else {
            return super.onInterceptTouchEvent(motionEvent);
        }
    }

    public void requestDisallowInterceptTouchEvent(boolean z) {
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(z);
        }
        this.mInterceptTouchEvents = !z;
    }
}
