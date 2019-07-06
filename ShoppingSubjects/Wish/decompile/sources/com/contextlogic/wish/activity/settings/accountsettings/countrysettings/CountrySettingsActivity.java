package com.contextlogic.wish.activity.settings.accountsettings.countrysettings;

import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.analytics.GoogleAnalyticsLogger.PageViewType;

public class CountrySettingsActivity extends DrawerActivity {
    public int getBottomNavigationTabIndex() {
        return 4;
    }

    /* access modifiers changed from: protected */
    public UiFragment createMainContentFragment() {
        return new CountrySettingsFragment();
    }

    /* access modifiers changed from: protected */
    public ServiceFragment createServiceFragment() {
        return new CountrySettingsServiceFragment();
    }

    public String getActionBarTitle() {
        return getString(R.string.country_or_region);
    }

    public PageViewType getGoogleAnalyticsPageViewType() {
        return PageViewType.COUNTRY_SETTINGS;
    }
}
