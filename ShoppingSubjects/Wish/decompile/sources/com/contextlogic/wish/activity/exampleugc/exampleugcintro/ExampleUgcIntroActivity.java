package com.contextlogic.wish.activity.exampleugc.exampleugcintro;

import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.activity.actionbar.ActionBarManager;
import com.contextlogic.wish.activity.actionbar.ActionBarManager.HomeButtonMode;
import com.contextlogic.wish.analytics.GoogleAnalyticsLogger.PageViewType;
import com.contextlogic.wish.application.WishApplication;

public class ExampleUgcIntroActivity extends DrawerActivity {
    public static String EXTRA_OPENED_FROM_CAMERA = "ExtraOpenedFromCamera";

    public int getBottomNavigationTabIndex() {
        return 0;
    }

    /* access modifiers changed from: protected */
    public UiFragment createMainContentFragment() {
        return new ExampleUgcIntroFragment();
    }

    public ServiceFragment createServiceFragment() {
        return new ExampleUgcIntroServiceFragment();
    }

    public String getActionBarTitle() {
        return WishApplication.getInstance().getString(R.string.share_your_purchase);
    }

    public PageViewType getGoogleAnalyticsPageViewType() {
        return PageViewType.EXAMPLE_UGC_INTRO;
    }

    /* access modifiers changed from: protected */
    public ActivityAnimationTypes getDefaultActivityAnimation() {
        return ActivityAnimationTypes.SLIDE_UP;
    }

    /* access modifiers changed from: protected */
    public void initializeActionBarManager(ActionBarManager actionBarManager) {
        getActionBarManager().setHomeButtonMode(HomeButtonMode.X_ICON);
        getActionBarManager().setBadgeVisible(false);
    }
}
