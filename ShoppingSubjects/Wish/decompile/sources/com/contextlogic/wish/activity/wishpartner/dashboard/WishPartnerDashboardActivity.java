package com.contextlogic.wish.activity.wishpartner.dashboard;

import android.support.v4.content.ContextCompat;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.activity.actionbar.ActionBarManager;
import com.contextlogic.wish.activity.actionbar.ActionBarManager.HomeButtonMode;
import com.contextlogic.wish.activity.actionbar.ActionBarManager.Theme;
import com.contextlogic.wish.activity.menu.MenuFragment;
import com.contextlogic.wish.application.WishApplication;

public class WishPartnerDashboardActivity extends DrawerActivity {
    public int getBottomNavigationTabIndex() {
        return 4;
    }

    /* access modifiers changed from: protected */
    public UiFragment createMainContentFragment() {
        return new WishPartnerDashboardFragment();
    }

    /* access modifiers changed from: protected */
    public ServiceFragment createServiceFragment() {
        return new WishPartnerServiceFragment();
    }

    public String getMenuKey() {
        return MenuFragment.MENU_KEY_WISH_PARTNER;
    }

    public String getActionBarTitle() {
        return getString(R.string.wish_partner);
    }

    public int getBackgroundColor() {
        return ContextCompat.getColor(WishApplication.getInstance(), R.color.gray7);
    }

    /* access modifiers changed from: protected */
    public ActivityAnimationTypes getDefaultActivityAnimation() {
        return ActivityAnimationTypes.NONE;
    }

    /* access modifiers changed from: protected */
    public void initializeActionBarManager(ActionBarManager actionBarManager) {
        super.initializeActionBarManager(actionBarManager);
        if (getIntent().getBooleanExtra("ExtraShowBackButton", false)) {
            actionBarManager.setHomeButtonMode(HomeButtonMode.BACK_ARROW);
        }
        actionBarManager.setTheme(Theme.WISH_PARTNER_BACKGROUND);
    }
}
