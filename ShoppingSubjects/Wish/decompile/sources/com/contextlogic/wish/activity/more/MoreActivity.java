package com.contextlogic.wish.activity.more;

import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.activity.menu.MenuFragment;
import com.contextlogic.wish.analytics.GoogleAnalyticsLogger.PageViewType;

public class MoreActivity extends DrawerActivity {
    /* access modifiers changed from: protected */
    public boolean canHaveActionBar() {
        return false;
    }

    public int getBottomNavigationTabIndex() {
        return 4;
    }

    /* access modifiers changed from: protected */
    public UiFragment createMainContentFragment() {
        return new MenuFragment();
    }

    public PageViewType getGoogleAnalyticsPageViewType() {
        return PageViewType.MORE;
    }

    public String getMenuKey() {
        return MenuFragment.MENU_KEY_MORE;
    }
}
