package com.contextlogic.wish.activity.profile.update;

import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.FullScreenActivity;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.analytics.GoogleAnalyticsLogger.PageViewType;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;

public class UpdateProfileActivity extends FullScreenActivity {
    public int getBottomNavigationTabIndex() {
        return 4;
    }

    /* access modifiers changed from: protected */
    public boolean requiresNoInterruption() {
        return true;
    }

    /* access modifiers changed from: protected */
    public ServiceFragment createServiceFragment() {
        return new UpdateProfileServiceFragment();
    }

    /* access modifiers changed from: protected */
    public UiFragment createMainContentFragment() {
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_UPDATE_PROFILE);
        return new UpdateProfileFragment();
    }

    public String getActionBarTitle() {
        return getString(R.string.update_profile);
    }

    public PageViewType getGoogleAnalyticsPageViewType() {
        return PageViewType.UPDATE_PROFILE;
    }
}
