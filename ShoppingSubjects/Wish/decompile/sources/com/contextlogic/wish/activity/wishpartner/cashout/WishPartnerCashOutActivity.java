package com.contextlogic.wish.activity.wishpartner.cashout;

import android.os.Bundle;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.FullScreenActivity;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.activity.actionbar.ActionBarManager;
import com.contextlogic.wish.activity.actionbar.ActionBarManager.HomeButtonMode;
import com.contextlogic.wish.activity.actionbar.ActionBarManager.Theme;
import com.contextlogic.wish.analytics.GoogleAnalyticsLogger.PageViewType;
import com.contextlogic.wish.api.model.WishPartnerSummary.WishPartnerCashOutInfo;
import com.contextlogic.wish.util.IntentUtil;

public class WishPartnerCashOutActivity extends FullScreenActivity {
    /* access modifiers changed from: protected */
    public void handleOnCreate(Bundle bundle) {
        super.handleOnCreate(bundle);
    }

    public String getActionBarTitle() {
        if (getPartnerCashOutInfo() != null) {
            return getPartnerCashOutInfo().getCashOutTitle();
        }
        return getString(R.string.cash_out);
    }

    /* access modifiers changed from: protected */
    public WishPartnerCashOutInfo getPartnerCashOutInfo() {
        return (WishPartnerCashOutInfo) IntentUtil.getParcelableExtra(getIntent(), "ExtraCashOutInfo");
    }

    /* access modifiers changed from: protected */
    public UiFragment createMainContentFragment() {
        return new WishPartnerCashOutFragment();
    }

    /* access modifiers changed from: protected */
    public ServiceFragment createServiceFragment() {
        return new WishPartnerCashOutServiceFragment();
    }

    public PageViewType getGoogleAnalyticsPageViewType() {
        return PageViewType.WISH_PARTNER_CASH_OUT_INFO;
    }

    /* access modifiers changed from: protected */
    public void initializeActionBarManager(ActionBarManager actionBarManager) {
        super.initializeActionBarManager(actionBarManager);
        actionBarManager.setTheme(Theme.TRANSPARENT_BACKGROUND_LIGHT_BUTTONS);
        getActionBarManager().setHomeButtonMode(HomeButtonMode.BACK_ARROW);
        getActionBarManager().setBadgeVisible(false);
    }
}
