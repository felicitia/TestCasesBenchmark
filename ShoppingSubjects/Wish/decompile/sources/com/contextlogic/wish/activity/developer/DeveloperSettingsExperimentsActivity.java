package com.contextlogic.wish.activity.developer;

import com.contextlogic.wish.activity.FullScreenActivity;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.analytics.GoogleAnalyticsLogger.PageViewType;

public class DeveloperSettingsExperimentsActivity extends FullScreenActivity {
    public String getActionBarTitle() {
        return "Experiments";
    }

    public int getBottomNavigationTabIndex() {
        return 4;
    }

    public boolean requiresAuthentication() {
        return false;
    }

    /* access modifiers changed from: protected */
    public UiFragment createMainContentFragment() {
        return new DeveloperSettingsExperimentsFragment();
    }

    /* access modifiers changed from: protected */
    public DeveloperSettingsServiceFragment createServiceFragment() {
        return new DeveloperSettingsServiceFragment();
    }

    public PageViewType getGoogleAnalyticsPageViewType() {
        return PageViewType.DEVELOPER_SETTINGS_EXPERIMENTS;
    }
}
