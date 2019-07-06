package com.etsy.android.extensions;

import android.support.design.widget.BottomSheetBehavior;

/* compiled from: BottomSheetBehavior.kt */
public final class a {
    public static final boolean a(BottomSheetBehavior<?> bottomSheetBehavior) {
        boolean z = false;
        if (bottomSheetBehavior == null) {
            return false;
        }
        switch (bottomSheetBehavior.getState()) {
            case 5:
                z = true;
                break;
        }
        return z;
    }
}
