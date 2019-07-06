package com.etsy.android.uikit.behavior;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.AppBarLayout.Behavior;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

public class EtsyAppBarBehavior extends Behavior {
    public EtsyAppBarBehavior() {
    }

    public EtsyAppBarBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, View view2, int i) {
        return !EtsySlidingBottomSheetBehavior.isEtsyBottomSheet(view);
    }
}
