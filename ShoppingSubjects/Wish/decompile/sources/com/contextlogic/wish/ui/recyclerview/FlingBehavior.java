package com.contextlogic.wish.ui.recyclerview;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.AppBarLayout.Behavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

public final class FlingBehavior extends Behavior {
    private boolean isPositive;

    public FlingBehavior() {
    }

    public FlingBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean onNestedFling(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, float f, float f2, boolean z) {
        if ((f2 > 0.0f && !this.isPositive) || (f2 < 0.0f && this.isPositive)) {
            f2 *= -1.0f;
        }
        float f3 = f2;
        if ((view instanceof SwipeRefreshLayout) && f3 < 0.0f) {
            view = ((SwipeRefreshLayout) view).getChildAt(0);
        }
        View view2 = view;
        if ((view2 instanceof RecyclerView) && f3 < 0.0f) {
            RecyclerView recyclerView = (RecyclerView) view2;
            z = recyclerView.getChildAdapterPosition(recyclerView.getChildAt(0)) > 3;
        }
        return super.onNestedFling(coordinatorLayout, appBarLayout, view2, f, f3, z);
    }

    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, int i, int i2, int[] iArr) {
        super.onNestedPreScroll(coordinatorLayout, appBarLayout, view, i, i2, iArr);
        this.isPositive = i2 > 0;
    }
}
