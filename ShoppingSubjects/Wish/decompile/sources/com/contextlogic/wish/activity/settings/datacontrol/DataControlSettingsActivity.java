package com.contextlogic.wish.activity.settings.datacontrol;

import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.analytics.GoogleAnalyticsLogger.PageViewType;

public class DataControlSettingsActivity extends DrawerActivity {
    public int getBottomNavigationTabIndex() {
        return 4;
    }

    /* access modifiers changed from: protected */
    public UiFragment createMainContentFragment() {
        return new DataControlSettingsFragment();
    }

    public String getActionBarTitle() {
        return getString(R.string.data_control);
    }

    public PageViewType getGoogleAnalyticsPageViewType() {
        return PageViewType.DATA_CONTROL_SETTINGS;
    }
}
