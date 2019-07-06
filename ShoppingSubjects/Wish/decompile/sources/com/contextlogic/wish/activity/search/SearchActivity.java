package com.contextlogic.wish.activity.search;

import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.activity.menu.MenuFragment;
import com.contextlogic.wish.analytics.GoogleAnalyticsLogger.PageViewType;

public class SearchActivity extends DrawerActivity {
    public static String EXTRA_QUERY = "ExtraQuery";

    public String getActionBarTitle() {
        return null;
    }

    public int getBottomNavigationTabIndex() {
        return 2;
    }

    /* access modifiers changed from: protected */
    public ServiceFragment createServiceFragment() {
        return new SearchServiceFragment();
    }

    /* access modifiers changed from: protected */
    public UiFragment createMainContentFragment() {
        return new SearchFragment();
    }

    public String getMenuKey() {
        return MenuFragment.MENU_KEY_SEARCH;
    }

    public String getQuery() {
        return getIntent().getStringExtra(EXTRA_QUERY);
    }

    public PageViewType getGoogleAnalyticsPageViewType() {
        return PageViewType.SEARCH;
    }

    /* access modifiers changed from: protected */
    public ActivityAnimationTypes getDefaultActivityAnimation() {
        return ActivityAnimationTypes.NONE;
    }
}
