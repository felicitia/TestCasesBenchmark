package com.contextlogic.wish.activity.settings.changepassword;

import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.analytics.GoogleAnalyticsLogger.PageViewType;

public class ChangePasswordActivity extends DrawerActivity {
    public int getBottomNavigationTabIndex() {
        return 4;
    }

    /* access modifiers changed from: protected */
    public ServiceFragment createServiceFragment() {
        return new ChangePasswordServiceFragment();
    }

    /* access modifiers changed from: protected */
    public UiFragment createMainContentFragment() {
        return new ChangePasswordFragment();
    }

    public String getActionBarTitle() {
        return getString(R.string.change_password);
    }

    public PageViewType getGoogleAnalyticsPageViewType() {
        return PageViewType.CHANGE_PASSWORD;
    }
}
