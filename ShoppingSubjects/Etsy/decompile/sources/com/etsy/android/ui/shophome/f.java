package com.etsy.android.ui.shophome;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;

final /* synthetic */ class f implements AnimatorUpdateListener {
    private final ShopHomeMainFragment a;

    f(ShopHomeMainFragment shopHomeMainFragment) {
        this.a = shopHomeMainFragment;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.a.lambda$hideCouponBanner$2$ShopHomeMainFragment(valueAnimator);
    }
}
