package com.contextlogic.wish.activity.settings.changecurrency;

import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.analytics.GoogleAnalyticsLogger.PageViewType;

public class ChangeCurrencyActivity extends DrawerActivity {
    public int getBottomNavigationTabIndex() {
        return 4;
    }

    /* access modifiers changed from: protected */
    public ServiceFragment createServiceFragment() {
        return new ChangeCurrencyServiceFragment();
    }

    /* access modifiers changed from: protected */
    public UiFragment createMainContentFragment() {
        return new ChangeCurrencyFragment();
    }

    public String getActionBarTitle() {
        return getString(R.string.change_currency_actionbar_title);
    }

    public PageViewType getGoogleAnalyticsPageViewType() {
        return PageViewType.CHANGE_CURRENCY;
    }
}
