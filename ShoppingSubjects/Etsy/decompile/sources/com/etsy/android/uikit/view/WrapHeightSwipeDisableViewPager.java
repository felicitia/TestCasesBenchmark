package com.etsy.android.uikit.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import com.etsy.android.lib.a.q;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;

public class WrapHeightSwipeDisableViewPager extends ViewPager {
    private boolean mIsSwipable = true;
    private boolean mMatchHeightOfMaxChild = false;

    public WrapHeightSwipeDisableViewPager(Context context) {
        super(context);
    }

    public WrapHeightSwipeDisableViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initAttributes(attributeSet);
    }

    private void initAttributes(AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, q.WrapHeightSwipeDisableViewPager);
        this.mIsSwipable = obtainStyledAttributes.getBoolean(q.WrapHeightSwipeDisableViewPager_swipeEnabled, true);
        obtainStyledAttributes.recycle();
    }

    public void setLayoutParams(LayoutParams layoutParams) {
        super.setLayoutParams(layoutParams);
        this.mMatchHeightOfMaxChild = layoutParams != null && layoutParams.height == -2;
    }

    public void setSwipable(boolean z) {
        this.mIsSwipable = z;
    }

    public void matchHeightOfMaxChild(boolean z) {
        this.mMatchHeightOfMaxChild = z;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.mIsSwipable) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        return false;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.mIsSwipable) {
            return false;
        }
        return super.onTouchEvent(motionEvent);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.mMatchHeightOfMaxChild) {
            int i3 = 0;
            for (int i4 = 0; i4 < getChildCount(); i4++) {
                View childAt = getChildAt(i4);
                childAt.measure(i, MeasureSpec.makeMeasureSpec(0, 0));
                int measuredHeight = childAt.getMeasuredHeight();
                if (measuredHeight > i3) {
                    i3 = measuredHeight;
                }
            }
            setMeasuredDimension(i, MeasureSpec.makeMeasureSpec(i3, ErrorDialogData.SUPPRESSED));
        }
    }
}
