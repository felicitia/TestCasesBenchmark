package com.contextlogic.wish.activity.feed.merchant;

import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.activity.feed.BaseProductFeedServiceFragment;
import com.contextlogic.wish.analytics.GoogleAnalyticsLogger.PageViewType;

public class MerchantFeedActivity extends DrawerActivity {
    public static String EXTRA_MERCHANT = "ExtraMerchant";

    public int getBottomNavigationTabIndex() {
        return 1;
    }

    public String getMenuKey() {
        return null;
    }

    /* access modifiers changed from: protected */
    public ServiceFragment createServiceFragment() {
        return new BaseProductFeedServiceFragment();
    }

    /* access modifiers changed from: protected */
    public UiFragment createMainContentFragment() {
        return new MerchantFeedFragment();
    }

    public String getActionBarTitle() {
        return getString(R.string.store);
    }

    public PageViewType getGoogleAnalyticsPageViewType() {
        return PageViewType.MERCHANT;
    }

    public String getMerchant() {
        return getIntent().getStringExtra(EXTRA_MERCHANT);
    }
}
