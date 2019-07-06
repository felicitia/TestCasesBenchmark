package com.contextlogic.wish.activity.settings.notifications;

import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.analytics.GoogleAnalyticsLogger.PageViewType;
import com.contextlogic.wish.api.model.WishNotificationPreference.PreferenceType;

public class NotificationSettingsActivity extends DrawerActivity {
    public int getBottomNavigationTabIndex() {
        return 4;
    }

    /* access modifiers changed from: protected */
    public ServiceFragment createServiceFragment() {
        return new NotificationSettingsServiceFragment();
    }

    /* access modifiers changed from: protected */
    public UiFragment createMainContentFragment() {
        return NotificationSettingsFragment.create(PreferenceType.PUSH);
    }

    public String getActionBarTitle() {
        return getString(R.string.notifications);
    }

    public PageViewType getGoogleAnalyticsPageViewType() {
        return PageViewType.NOTIFICATION_SETTINGS;
    }
}
