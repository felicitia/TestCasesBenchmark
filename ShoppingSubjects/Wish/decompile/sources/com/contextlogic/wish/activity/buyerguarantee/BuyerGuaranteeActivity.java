package com.contextlogic.wish.activity.buyerguarantee;

import com.contextlogic.wish.activity.FullScreenActivity;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.activity.actionbar.ActionBarManager;
import com.contextlogic.wish.activity.actionbar.ActionBarManager.HomeButtonMode;
import com.contextlogic.wish.analytics.GoogleAnalyticsLogger.PageViewType;

public class BuyerGuaranteeActivity extends FullScreenActivity {
    /* access modifiers changed from: protected */
    public UiFragment createMainContentFragment() {
        return new BuyerGuaranteeFragment();
    }

    /* access modifiers changed from: protected */
    public ServiceFragment createServiceFragment() {
        return new BuyerGuaranteeServiceFragment();
    }

    /* access modifiers changed from: protected */
    public void initializeActionBarManager(ActionBarManager actionBarManager) {
        actionBarManager.setHomeButtonMode(HomeButtonMode.BACK_ARROW);
    }

    public PageViewType getGoogleAnalyticsPageViewType() {
        return PageViewType.BUYER_GUARANTEE;
    }

    /* access modifiers changed from: protected */
    public ActivityAnimationTypes getDefaultActivityAnimation() {
        return ActivityAnimationTypes.SLIDE_UP;
    }
}
