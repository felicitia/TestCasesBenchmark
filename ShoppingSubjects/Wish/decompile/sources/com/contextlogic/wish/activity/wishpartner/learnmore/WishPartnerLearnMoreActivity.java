package com.contextlogic.wish.activity.wishpartner.learnmore;

import android.os.Bundle;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.FullScreenActivity;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.activity.actionbar.ActionBarManager;
import com.contextlogic.wish.activity.actionbar.ActionBarManager.HomeButtonMode;
import com.contextlogic.wish.activity.actionbar.ActionBarManager.Theme;
import com.contextlogic.wish.analytics.GoogleAnalyticsLogger.PageViewType;

public class WishPartnerLearnMoreActivity extends FullScreenActivity {
    /* access modifiers changed from: protected */
    public void handleOnCreate(Bundle bundle) {
        super.handleOnCreate(bundle);
    }

    public String getActionBarTitle() {
        return getString(R.string.learn_more);
    }

    /* access modifiers changed from: protected */
    public UiFragment createMainContentFragment() {
        return new WishPartnerLearnMoreFragment();
    }

    /* access modifiers changed from: protected */
    public ServiceFragment createServiceFragment() {
        return new WishPartnerLearnMoreServiceFragment();
    }

    public PageViewType getGoogleAnalyticsPageViewType() {
        return PageViewType.WISH_PARTNER_LEARN_MORE;
    }

    /* access modifiers changed from: protected */
    public void initializeActionBarManager(ActionBarManager actionBarManager) {
        super.initializeActionBarManager(actionBarManager);
        actionBarManager.setTheme(Theme.TRANSPARENT_BACKGROUND_LIGHT_BUTTONS);
        getActionBarManager().setHomeButtonMode(HomeButtonMode.BACK_ARROW);
        getActionBarManager().setBadgeVisible(false);
    }
}
