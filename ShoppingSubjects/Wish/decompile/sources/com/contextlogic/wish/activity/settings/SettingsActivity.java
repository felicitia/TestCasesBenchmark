package com.contextlogic.wish.activity.settings;

import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.activity.menu.MenuFragment;
import com.contextlogic.wish.analytics.GoogleAnalyticsLogger.PageViewType;

public class SettingsActivity extends DrawerActivity {
    public int getBottomNavigationTabIndex() {
        return 4;
    }

    /* access modifiers changed from: protected */
    public UiFragment createMainContentFragment() {
        return new SettingsFragment();
    }

    /* access modifiers changed from: protected */
    public ServiceFragment createServiceFragment() {
        return new SettingsServiceFragment();
    }

    public String getActionBarTitle() {
        return getString(R.string.settings);
    }

    public String getMenuKey() {
        return MenuFragment.MENU_KEY_SETTINGS;
    }

    public PageViewType getGoogleAnalyticsPageViewType() {
        return PageViewType.SETTINGS;
    }
}
