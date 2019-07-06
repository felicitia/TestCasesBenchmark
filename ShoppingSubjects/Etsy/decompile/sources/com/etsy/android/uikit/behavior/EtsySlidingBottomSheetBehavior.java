package com.etsy.android.uikit.behavior;

import android.content.Context;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import com.etsy.android.lib.util.l;

public class EtsySlidingBottomSheetBehavior<V extends View> extends BottomSheetBehavior<V> {
    public static final int MIN_HEIGHT = 200;
    private int mMinHeight = -1;

    public EtsySlidingBottomSheetBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public EtsySlidingBottomSheetBehavior() {
    }

    private int getMinHeight(Context context) {
        if (this.mMinHeight == -1) {
            this.mMinHeight = (int) new l(context).a(200.0f);
        }
        return this.mMinHeight;
    }

    public boolean onLayoutChild(CoordinatorLayout coordinatorLayout, V v, int i) {
        if (!ViewCompat.isLaidOut(v)) {
            setState(5);
        }
        if (getState() == 4) {
            setState(3);
        }
        return super.onLayoutChild(coordinatorLayout, v, i);
    }

    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, V v, View view) {
        int height = coordinatorLayout.getHeight() - v.getTop();
        setSkipCollapsed(height < getMinHeight(v.getContext()));
        setPeekHeight(height);
        super.onStopNestedScroll(coordinatorLayout, v, view);
    }

    public static boolean isEtsyBottomSheet(View view) {
        if (view == null) {
            return false;
        }
        LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof CoordinatorLayout.LayoutParams) {
            return ((CoordinatorLayout.LayoutParams) layoutParams).getBehavior() instanceof EtsySlidingBottomSheetBehavior;
        }
        return false;
    }
}
