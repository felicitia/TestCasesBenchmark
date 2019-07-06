package com.etsy.android.uikit.util;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

public class HardwareAnimatorListener extends AnimatorListenerAdapter {
    private View mAnimationView;
    private int mOriginalLayerType;

    public HardwareAnimatorListener(View view) {
        this.mAnimationView = view;
    }

    public void onAnimationStart(Animator animator) {
        super.onAnimationStart(animator);
        if (this.mAnimationView != null) {
            this.mOriginalLayerType = this.mAnimationView.getLayerType();
            this.mAnimationView.setLayerType(2, null);
        }
    }

    public void onAnimationEnd(Animator animator) {
        super.onAnimationEnd(animator);
        if (this.mAnimationView != null) {
            this.mAnimationView.setLayerType(this.mOriginalLayerType, null);
        }
    }
}
