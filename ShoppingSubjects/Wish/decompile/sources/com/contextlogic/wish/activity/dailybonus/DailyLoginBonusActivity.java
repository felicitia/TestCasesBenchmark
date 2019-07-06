package com.contextlogic.wish.activity.dailybonus;

import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.activity.menu.MenuFragment;
import com.contextlogic.wish.analytics.GoogleAnalyticsLogger.PageViewType;
import com.contextlogic.wish.application.WishApplication;

public class DailyLoginBonusActivity extends DrawerActivity {
    public int getBottomNavigationTabIndex() {
        return 4;
    }

    /* access modifiers changed from: protected */
    public UiFragment createMainContentFragment() {
        return new DailyLoginBonusFragment();
    }

    /* access modifiers changed from: protected */
    public DailyLoginBonusServiceFragment createServiceFragment() {
        return new DailyLoginBonusServiceFragment();
    }

    public String getActionBarTitle() {
        return WishApplication.getInstance().getString(R.string.daily_login_bonus);
    }

    public String getMenuKey() {
        return MenuFragment.MENU_KEY_DAILY_LOGIN_BONUS;
    }

    public PageViewType getGoogleAnalyticsPageViewType() {
        return PageViewType.DAILY_LOGIN_BONUS;
    }
}
