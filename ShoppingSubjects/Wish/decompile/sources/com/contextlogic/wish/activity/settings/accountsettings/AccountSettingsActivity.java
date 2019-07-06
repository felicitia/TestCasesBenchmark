package com.contextlogic.wish.activity.settings.accountsettings;

import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.analytics.GoogleAnalyticsLogger.PageViewType;

public class AccountSettingsActivity extends DrawerActivity {
    public int getBottomNavigationTabIndex() {
        return 4;
    }

    /* access modifiers changed from: protected */
    public UiFragment createMainContentFragment() {
        return new AccountSettingsFragment();
    }

    /* access modifiers changed from: protected */
    public ServiceFragment createServiceFragment() {
        return new AccountSettingsServiceFragment();
    }

    public String getActionBarTitle() {
        return getString(R.string.account_settings);
    }

    public PageViewType getGoogleAnalyticsPageViewType() {
        return PageViewType.ACCOUNT_SETTINGS;
    }
}
