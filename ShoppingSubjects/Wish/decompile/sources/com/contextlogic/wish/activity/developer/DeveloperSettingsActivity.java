package com.contextlogic.wish.activity.developer;

import android.os.Bundle;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.FullScreenActivity;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.analytics.GoogleAnalyticsLogger.PageViewType;
import com.contextlogic.wish.api.datacenter.AuthenticationDataCenter;
import com.contextlogic.wish.api.datacenter.ProfileDataCenter;

public class DeveloperSettingsActivity extends FullScreenActivity {
    public int getBottomNavigationTabIndex() {
        return 4;
    }

    public boolean requiresAuthentication() {
        return false;
    }

    public void handleOnCreate(Bundle bundle) {
        super.handleOnCreate(bundle);
        withVerifiedAuthentication(new Runnable() {
            public void run() {
                if (!AuthenticationDataCenter.getInstance().isLoggedIn() || !ProfileDataCenter.getInstance().isAdmin()) {
                    DeveloperSettingsActivity.this.finishActivity();
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public ServiceFragment createServiceFragment() {
        return new DeveloperSettingsServiceFragment();
    }

    /* access modifiers changed from: protected */
    public UiFragment createMainContentFragment() {
        return new DeveloperSettingsFragment();
    }

    public String getActionBarTitle() {
        return getString(R.string.developer_settings);
    }

    public PageViewType getGoogleAnalyticsPageViewType() {
        return PageViewType.DEVELOPER_SETTINGS;
    }
}
