package com.onfido.android.sdk.capture.utils;

import android.support.design.widget.BottomSheetBehavior;
import android.view.ViewGroup;
import kotlin.jvm.internal.Intrinsics;

public final class BottomSheetExtensionsKt {
    public static final <T extends ViewGroup> void collapse(BottomSheetBehavior<T> bottomSheetBehavior) {
        Intrinsics.checkParameterIsNotNull(bottomSheetBehavior, "$receiver");
        bottomSheetBehavior.setState(4);
    }

    public static final <T extends ViewGroup> void expand(BottomSheetBehavior<T> bottomSheetBehavior) {
        Intrinsics.checkParameterIsNotNull(bottomSheetBehavior, "$receiver");
        bottomSheetBehavior.setState(3);
    }

    public static final <T extends ViewGroup> void hide(BottomSheetBehavior<T> bottomSheetBehavior) {
        Intrinsics.checkParameterIsNotNull(bottomSheetBehavior, "$receiver");
        bottomSheetBehavior.setState(5);
    }

    public static final <T extends ViewGroup> void toggle(BottomSheetBehavior<T> bottomSheetBehavior) {
        Intrinsics.checkParameterIsNotNull(bottomSheetBehavior, "$receiver");
        switch (bottomSheetBehavior.getState()) {
            case 3:
                collapse(bottomSheetBehavior);
                return;
            case 4:
                expand(bottomSheetBehavior);
                return;
            default:
                return;
        }
    }
}
