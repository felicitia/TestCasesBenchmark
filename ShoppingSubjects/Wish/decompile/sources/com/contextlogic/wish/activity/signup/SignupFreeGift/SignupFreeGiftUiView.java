package com.contextlogic.wish.activity.signup.SignupFreeGift;

import android.os.Bundle;
import android.widget.LinearLayout;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.ui.image.ImageRestorable;
import com.contextlogic.wish.ui.view.Recyclable;

public abstract class SignupFreeGiftUiView extends LinearLayout implements ImageRestorable, Recyclable {
    private SignupFreeGiftFragment mFreeGiftFragment;

    public WishAnalyticsEvent getWishAnalyticImpressionEvent() {
        return null;
    }

    public void handleSaveInstanceState(Bundle bundle) {
    }

    /* access modifiers changed from: protected */
    public abstract void initializeUi(Bundle bundle);

    public abstract boolean onBackPressed();

    public void onKeyboardVisiblityChanged(boolean z) {
    }

    public abstract void refreshUi();

    public SignupFreeGiftUiView(SignupFreeGiftFragment signupFreeGiftFragment, SignupFreeGiftActivity signupFreeGiftActivity, Bundle bundle) {
        super(signupFreeGiftActivity);
        this.mFreeGiftFragment = signupFreeGiftFragment;
        setOrientation(1);
        initializeUi(bundle);
    }

    public SignupFreeGiftFragment getFreeGiftFragment() {
        return this.mFreeGiftFragment;
    }
}
