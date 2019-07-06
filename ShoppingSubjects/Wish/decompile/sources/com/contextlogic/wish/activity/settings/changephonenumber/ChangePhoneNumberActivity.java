package com.contextlogic.wish.activity.settings.changephonenumber;

import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.analytics.GoogleAnalyticsLogger.PageViewType;

public class ChangePhoneNumberActivity extends DrawerActivity {
    public int getBottomNavigationTabIndex() {
        return 4;
    }

    /* access modifiers changed from: protected */
    public ServiceFragment createServiceFragment() {
        return new ChangePhoneNumberServiceFragment();
    }

    /* access modifiers changed from: protected */
    public UiFragment createMainContentFragment() {
        return new ChangePhoneNumberFragment();
    }

    public String getActionBarTitle() {
        return getString(R.string.change_phone_number);
    }

    public PageViewType getGoogleAnalyticsPageViewType() {
        return PageViewType.CHANGE_PHONE_NUMBER;
    }
}
