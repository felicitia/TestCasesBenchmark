package com.contextlogic.wish.activity.crosspromo;

import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.activity.menu.MenuFragment;
import com.contextlogic.wish.analytics.GoogleAnalyticsLogger.PageViewType;

public class CrossPromoActivity extends DrawerActivity {
    public int getBottomNavigationTabIndex() {
        return 4;
    }

    /* access modifiers changed from: protected */
    public ServiceFragment createServiceFragment() {
        return new CrossPromoServiceFragment();
    }

    /* access modifiers changed from: protected */
    public UiFragment createMainContentFragment() {
        return new CrossPromoFragment();
    }

    public String getActionBarTitle() {
        return getString(R.string.more_apps);
    }

    public String getMenuKey() {
        return MenuFragment.MENU_KEY_MORE_APPS;
    }

    public PageViewType getGoogleAnalyticsPageViewType() {
        return PageViewType.CROSS_PROMO;
    }
}
