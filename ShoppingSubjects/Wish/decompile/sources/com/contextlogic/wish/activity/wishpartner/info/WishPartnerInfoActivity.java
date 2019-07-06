package com.contextlogic.wish.activity.wishpartner.info;

import android.os.Bundle;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.FullScreenActivity;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.activity.actionbar.ActionBarManager;
import com.contextlogic.wish.activity.actionbar.ActionBarManager.HomeButtonMode;
import com.contextlogic.wish.activity.actionbar.ActionBarManager.Theme;
import com.contextlogic.wish.analytics.GoogleAnalyticsLogger.PageViewType;

public class WishPartnerInfoActivity extends FullScreenActivity {
    /* access modifiers changed from: protected */
    public void handleOnCreate(Bundle bundle) {
        super.handleOnCreate(bundle);
    }

    public String getActionBarTitle() {
        return getString(R.string.wish_partner);
    }

    /* access modifiers changed from: protected */
    public String getProductId() {
        return getIntent().getStringExtra("ExtraProductId");
    }

    /* access modifiers changed from: protected */
    public String getProductImage() {
        return getIntent().getStringExtra("ExtraProductImage");
    }

    /* access modifiers changed from: protected */
    public String getProductPrice() {
        return getIntent().getStringExtra("ExtraProductPrice");
    }

    /* access modifiers changed from: protected */
    public UiFragment createMainContentFragment() {
        return new WishPartnerInfoFragment();
    }

    /* access modifiers changed from: protected */
    public ServiceFragment createServiceFragment() {
        return new WishPartnerInfoServiceFragment();
    }

    public PageViewType getGoogleAnalyticsPageViewType() {
        return PageViewType.WISH_PARTNER_INFO;
    }

    /* access modifiers changed from: protected */
    public void initializeActionBarManager(ActionBarManager actionBarManager) {
        super.initializeActionBarManager(actionBarManager);
        actionBarManager.setHomeButtonMode(HomeButtonMode.X_ICON);
        actionBarManager.setFadeToTheme(Theme.WISH_PARTNER_BACKGROUND, 0);
        actionBarManager.setTheme(Theme.TRANSPARENT_BACKGROUND_LIGHT_BUTTONS);
        getActionBarManager().setBadgeVisible(false);
    }
}
