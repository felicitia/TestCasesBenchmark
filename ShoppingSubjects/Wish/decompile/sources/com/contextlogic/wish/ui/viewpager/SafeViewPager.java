package com.contextlogic.wish.ui.viewpager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.contextlogic.wish.ui.image.ZoomingImageView;

public class SafeViewPager extends ViewPager {
    private boolean mDisableViewPager;

    public SafeViewPager(Context context) {
        super(context);
    }

    public SafeViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (isFakeDragging() || this.mDisableViewPager) {
            return false;
        }
        try {
            return super.onInterceptTouchEvent(motionEvent);
        } catch (Exception unused) {
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public boolean canScroll(View view, boolean z, int i, int i2, int i3) {
        if (view instanceof ZoomingImageView) {
            return ((ZoomingImageView) view).canScrollHorizontallyFroyo(-i);
        }
        return super.canScroll(view, z, i, i2, i3);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        try {
            if (!this.mDisableViewPager) {
                return super.onTouchEvent(motionEvent);
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    public void disableViewPager() {
        this.mDisableViewPager = true;
    }
}
