package com.contextlogic.wish.activity.login.landing;

import android.content.Intent;
import com.contextlogic.wish.activity.FullScreenActivity;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.analytics.GoogleAnalyticsLogger.PageViewType;
import com.contextlogic.wish.util.IntentUtil;

public class LandingActivity extends FullScreenActivity {
    public boolean canBeTaskRoot() {
        return true;
    }

    public final boolean canHaveActionBar() {
        return false;
    }

    public boolean canShowDailyGiveawayNotification() {
        return false;
    }

    public boolean requiresAuthentication() {
        return false;
    }

    /* access modifiers changed from: protected */
    public ServiceFragment createServiceFragment() {
        return new LandingServiceFragment();
    }

    /* access modifiers changed from: protected */
    public UiFragment createMainContentFragment() {
        return new LandingFragment();
    }

    public PageViewType getGoogleAnalyticsPageViewType() {
        return PageViewType.LOGIN;
    }

    public Intent getPreLoginIntent() {
        return (Intent) IntentUtil.getParcelableExtra(getIntent(), "ExtraPreLoginIntent");
    }
}
