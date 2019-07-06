package com.contextlogic.wish.activity.pricewatch;

import android.view.View;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.activity.actionbar.ActionBarManager;
import com.contextlogic.wish.activity.actionbar.ActionBarManager.HomeButtonMode;
import com.contextlogic.wish.activity.menu.MenuFragment;
import com.contextlogic.wish.analytics.GoogleAnalyticsLogger.PageViewType;
import com.contextlogic.wish.application.WishApplication;

public class PriceWatchActivity extends DrawerActivity {
    public int getBottomNavigationTabIndex() {
        return 4;
    }

    /* access modifiers changed from: protected */
    public UiFragment createMainContentFragment() {
        return new PriceWatchFragment();
    }

    /* access modifiers changed from: protected */
    public PriceWatchServiceFragment createServiceFragment() {
        return new PriceWatchServiceFragment();
    }

    public String getActionBarTitle() {
        return WishApplication.getInstance().getString(R.string.price_watch);
    }

    public String getMenuKey() {
        return MenuFragment.MENU_KEY_PRICE_WATCH;
    }

    public PageViewType getGoogleAnalyticsPageViewType() {
        return PageViewType.PRICE_WATCH;
    }

    public void updateToolBarPadding() {
        View toolbarContainer = getToolbarContainer();
        if (toolbarContainer != null) {
            toolbarContainer.setBackgroundColor(WishApplication.getInstance().getResources().getColor(R.color.wish_blue));
        }
    }

    /* access modifiers changed from: protected */
    public void initializeActionBarManager(ActionBarManager actionBarManager) {
        super.initializeActionBarManager(actionBarManager);
        if (getIntent().getBooleanExtra("ExtraShowBackButton", false)) {
            actionBarManager.setHomeButtonMode(HomeButtonMode.BACK_ARROW);
        }
    }

    public int getBackgroundColor() {
        return getResources().getColor(R.color.gray7);
    }
}
