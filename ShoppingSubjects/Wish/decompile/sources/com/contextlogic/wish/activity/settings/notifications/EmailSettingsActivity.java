package com.contextlogic.wish.activity.settings.notifications;

import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.analytics.GoogleAnalyticsLogger.PageViewType;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishNotificationPreference.PreferenceType;

public class EmailSettingsActivity extends NotificationSettingsActivity {
    public int getBottomNavigationTabIndex() {
        return 4;
    }

    /* access modifiers changed from: protected */
    public UiFragment createMainContentFragment() {
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_MOBILE_EMAIL_NOTIFICATION_SETTINGS);
        return NotificationSettingsFragment.create(PreferenceType.EMAIL);
    }

    public String getActionBarTitle() {
        return getString(R.string.email_settings);
    }

    public PageViewType getGoogleAnalyticsPageViewType() {
        return PageViewType.EMAIL_NOTIFICATION_SETTINGS;
    }
}
