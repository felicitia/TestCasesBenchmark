package com.etsy.android.uikit.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.etsy.android.lib.logger.f;

public class FixedViewPager extends ViewPager {
    private static final String TAG = "com.etsy.android.uikit.view.FixedViewPager";

    public FixedViewPager(Context context) {
        super(context);
    }

    public FixedViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (!isFakeDragging()) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        f.c(TAG, "onInterceptTouchEvent would have crashed fakeDragBy");
        return false;
    }
}
