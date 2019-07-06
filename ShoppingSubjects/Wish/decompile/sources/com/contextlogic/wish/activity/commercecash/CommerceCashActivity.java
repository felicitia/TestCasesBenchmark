package com.contextlogic.wish.activity.commercecash;

import android.os.Bundle;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.activity.menu.MenuFragment;
import com.contextlogic.wish.analytics.GoogleAnalyticsLogger.PageViewType;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.util.StringUtil;

public class CommerceCashActivity extends DrawerActivity {
    public int getBottomNavigationTabIndex() {
        return 4;
    }

    /* access modifiers changed from: protected */
    public void handleOnCreate(Bundle bundle) {
        super.handleOnCreate(bundle);
        if (!ExperimentDataCenter.getInstance().canSeeCommerceCash() && !ExperimentDataCenter.getInstance().shouldSeePayNearMe() && !ExperimentDataCenter.getInstance().shouldSeeReferralProgram()) {
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public ServiceFragment createServiceFragment() {
        return new CommerceCashServiceFragment();
    }

    /* access modifiers changed from: protected */
    public UiFragment createMainContentFragment() {
        return new CommerceCashFragment();
    }

    public String getActionBarTitle() {
        WishApplication.getInstance();
        String capitalize = StringUtil.capitalize(WishApplication.getAppType());
        return WishApplication.getInstance().getString(R.string.commerce_cash, new Object[]{capitalize});
    }

    public String getMenuKey() {
        return MenuFragment.MENU_KEY_COMMERCE_CASH;
    }

    public PageViewType getGoogleAnalyticsPageViewType() {
        return PageViewType.COMMERCE_CASH;
    }
}
