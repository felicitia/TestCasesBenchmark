package com.onfido.android.sdk.capture.utils;

import android.animation.LayoutTransition;
import android.view.View;
import android.view.ViewGroup;

public class ViewUtil {
    public static void setViewVisibility(View view, boolean z) {
        view.setVisibility(z ? 0 : 4);
    }

    public static void setViewVisibilityWithoutAnimation(View view, boolean z) {
        LayoutTransition layoutTransition = ((ViewGroup) view.getParent()).getLayoutTransition();
        int i = 3;
        layoutTransition.disableTransitionType(z ? 2 : 3);
        view.setVisibility(z ? 0 : 4);
        if (z) {
            i = 2;
        }
        layoutTransition.enableTransitionType(i);
    }
}
