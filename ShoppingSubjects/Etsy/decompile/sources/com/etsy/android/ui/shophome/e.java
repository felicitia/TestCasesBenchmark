package com.etsy.android.ui.shophome;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;

final /* synthetic */ class e implements AnimatorUpdateListener {
    private final ShopHomeMainFragment a;

    e(ShopHomeMainFragment shopHomeMainFragment) {
        this.a = shopHomeMainFragment;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.a.lambda$showCouponBanner$1$ShopHomeMainFragment(valueAnimator);
    }
}
