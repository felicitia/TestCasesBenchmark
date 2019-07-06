package com.etsy.android.uikit.util;

import android.os.Build.VERSION;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

public class HardwareAnimationListener implements AnimationListener {
    private final View mAnimationView;
    private int mOriginalLayerType;

    public void onAnimationRepeat(Animation animation) {
    }

    public HardwareAnimationListener(View view) {
        this.mAnimationView = view;
    }

    public void onAnimationStart(Animation animation) {
        if (this.mAnimationView != null && isValidOs()) {
            this.mOriginalLayerType = this.mAnimationView.getLayerType();
            this.mAnimationView.setLayerType(2, null);
        }
    }

    public void onAnimationEnd(Animation animation) {
        if (this.mAnimationView != null && isValidOs()) {
            this.mAnimationView.setLayerType(this.mOriginalLayerType, null);
        }
    }

    /* access modifiers changed from: protected */
    public boolean isValidOs() {
        return (VERSION.SDK_INT == 15 || VERSION.SDK_INT == 14) ? false : true;
    }
}
