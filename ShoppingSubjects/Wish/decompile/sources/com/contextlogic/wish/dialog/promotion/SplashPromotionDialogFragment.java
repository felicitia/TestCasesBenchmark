package com.contextlogic.wish.dialog.promotion;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishPromotionBaseSpec;
import com.contextlogic.wish.dialog.BaseDialogFragment;
import com.contextlogic.wish.ui.image.ImageRestorable;

public class SplashPromotionDialogFragment<A extends BaseActivity> extends BaseDialogFragment<A> {
    private View mSplashView;

    public int getDialogHeight() {
        return -1;
    }

    public int getDialogWidth() {
        return -1;
    }

    public int getDimColor() {
        return R.color.white_dialog_dim;
    }

    public boolean isCancelable() {
        return false;
    }

    public static SplashPromotionDialogFragment<BaseActivity> createDialog(WishPromotionBaseSpec wishPromotionBaseSpec) {
        SplashPromotionDialogFragment<BaseActivity> splashPromotionDialogFragment = new SplashPromotionDialogFragment<>();
        Bundle bundle = new Bundle();
        if (wishPromotionBaseSpec == null) {
            return null;
        }
        bundle.putParcelable("ArgumentPromotion", wishPromotionBaseSpec);
        splashPromotionDialogFragment.setArguments(bundle);
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_PROMO_SPLASH);
        return splashPromotionDialogFragment;
    }

    public View getContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        WishPromotionBaseSpec wishPromotionBaseSpec = (WishPromotionBaseSpec) getArguments().getParcelable("ArgumentPromotion");
        if (wishPromotionBaseSpec == null) {
            Log.e(getClass().getName(), "getContentView: promotion was null");
            return null;
        }
        this.mSplashView = wishPromotionBaseSpec.getSplashView(this);
        return this.mSplashView;
    }

    public void onResume() {
        super.onResume();
    }

    public void releaseImages() {
        if (this.mSplashView != null && (this.mSplashView instanceof ImageRestorable)) {
            ((ImageRestorable) this.mSplashView).releaseImages();
        }
    }

    public void dismiss() {
        releaseImages();
        cancel();
    }
}
