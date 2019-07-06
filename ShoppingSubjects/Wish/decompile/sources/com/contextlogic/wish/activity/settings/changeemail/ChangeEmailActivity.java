package com.contextlogic.wish.activity.settings.changeemail;

import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.analytics.GoogleAnalyticsLogger.PageViewType;

public class ChangeEmailActivity extends DrawerActivity {
    public int getBottomNavigationTabIndex() {
        return 4;
    }

    /* access modifiers changed from: protected */
    public ServiceFragment createServiceFragment() {
        return new ChangeEmailServiceFragment();
    }

    /* access modifiers changed from: protected */
    public UiFragment createMainContentFragment() {
        return new ChangeEmailFragment();
    }

    public String getActionBarTitle() {
        return getString(R.string.change_email);
    }

    public PageViewType getGoogleAnalyticsPageViewType() {
        return PageViewType.CHANGE_EMAIL;
    }
}
