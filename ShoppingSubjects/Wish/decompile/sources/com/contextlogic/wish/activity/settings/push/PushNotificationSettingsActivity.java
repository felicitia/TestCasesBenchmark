package com.contextlogic.wish.activity.settings.push;

import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.analytics.GoogleAnalyticsLogger.PageViewType;

public class PushNotificationSettingsActivity extends DrawerActivity {
    public int getBottomNavigationTabIndex() {
        return 4;
    }

    /* access modifiers changed from: protected */
    public ServiceFragment createServiceFragment() {
        return new PushNotificationSettingsServiceFragment();
    }

    /* access modifiers changed from: protected */
    public UiFragment createMainContentFragment() {
        return new PushNotificationSettingsFragment();
    }

    public String getActionBarTitle() {
        return getString(R.string.push_notifications);
    }

    public PageViewType getGoogleAnalyticsPageViewType() {
        return PageViewType.PUSH_NOTIFICATION_SETTINGS;
    }
}
