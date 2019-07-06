package com.onfido.android.sdk.capture.ui.country_selection;

import android.content.Context;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import kotlin.jvm.internal.Intrinsics;

public final class CountrySelectionBottomSheetBehavior<V extends View> extends BottomSheetBehavior<V> {
    public CountrySelectionBottomSheetBehavior(Context context, AttributeSet attributeSet) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(attributeSet, "attrs");
        super(context, attributeSet);
    }

    public boolean onInterceptTouchEvent(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        Intrinsics.checkParameterIsNotNull(v, "child");
        return false;
    }

    public boolean onNestedPreFling(CoordinatorLayout coordinatorLayout, V v, View view, float f, float f2) {
        Intrinsics.checkParameterIsNotNull(coordinatorLayout, "coordinatorLayout");
        Intrinsics.checkParameterIsNotNull(v, "child");
        Intrinsics.checkParameterIsNotNull(view, "target");
        return false;
    }

    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, V v, View view, int i, int i2, int[] iArr, int i3) {
        Intrinsics.checkParameterIsNotNull(coordinatorLayout, "coordinatorLayout");
        Intrinsics.checkParameterIsNotNull(v, "child");
        Intrinsics.checkParameterIsNotNull(view, "target");
        Intrinsics.checkParameterIsNotNull(iArr, "consumed");
    }

    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, V v, View view, View view2, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(coordinatorLayout, "coordinatorLayout");
        Intrinsics.checkParameterIsNotNull(v, "child");
        Intrinsics.checkParameterIsNotNull(view, "directTargetChild");
        Intrinsics.checkParameterIsNotNull(view2, "target");
        return false;
    }

    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, V v, View view, int i) {
        Intrinsics.checkParameterIsNotNull(coordinatorLayout, "coordinatorLayout");
        Intrinsics.checkParameterIsNotNull(v, "child");
        Intrinsics.checkParameterIsNotNull(view, "target");
    }

    public boolean onTouchEvent(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        Intrinsics.checkParameterIsNotNull(v, "child");
        return false;
    }
}
