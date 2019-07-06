package com.contextlogic.wish.activity.profile;

import android.content.Intent;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.activity.actionbar.ActionBarManager;
import com.contextlogic.wish.activity.actionbar.ActionBarManager.HomeButtonMode;
import com.contextlogic.wish.activity.actionbar.ActionBarManager.Theme;
import com.contextlogic.wish.activity.menu.MenuFragment;
import com.contextlogic.wish.analytics.GoogleAnalyticsLogger.PageViewType;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.ProfileDataCenter;
import com.contextlogic.wish.util.IntentUtil;

public class ProfileActivity extends DrawerActivity {
    public static String EXTRA_CHANGE_PROFILE_PICTURE = "ExtraChangeProfilePicture";
    public static String EXTRA_USER_ID = "ExtraUserId";

    public int getBottomNavigationTabIndex() {
        return 4;
    }

    public boolean shouldUseDynamicBottomNavigationLayout() {
        return true;
    }

    /* access modifiers changed from: protected */
    public ServiceFragment createServiceFragment() {
        return new ProfileServiceFragment();
    }

    /* access modifiers changed from: protected */
    public UiFragment createMainContentFragment() {
        return new ProfileFragment();
    }

    public String getActionBarTitle() {
        return getString(R.string.profile);
    }

    public String getMenuKey() {
        if (getUserId() == null || getUserId().equals(ProfileDataCenter.getInstance().getUserId())) {
            return MenuFragment.MENU_KEY_PROFILE;
        }
        return null;
    }

    public PageViewType getGoogleAnalyticsPageViewType() {
        return PageViewType.PROFILE;
    }

    public boolean getProfilePictureNeedsChanging() {
        return getIntent().getBooleanExtra(EXTRA_CHANGE_PROFILE_PICTURE, false);
    }

    public String getUserId() {
        return getIntent().getStringExtra(EXTRA_USER_ID);
    }

    /* access modifiers changed from: protected */
    public void initializeActionBarManager(ActionBarManager actionBarManager) {
        super.initializeActionBarManager(actionBarManager);
        actionBarManager.setFadeToTheme(actionBarManager.getTheme());
        actionBarManager.setTheme(Theme.TRANSPARENT_BACKGROUND_LIGHT_BUTTONS);
        actionBarManager.setHomeButtonMode(HomeButtonMode.BACK_ARROW);
    }

    public WishAnalyticsEvent getClickImpressionActionBarCartButton() {
        return WishAnalyticsEvent.CLICK_ACTION_BAR_CART_BUTTON_ON_PROFILE;
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (IntentUtil.safeToUnparcel(intent) && intent.getBooleanExtra("ExtraRequiresReload", false)) {
            ((ProfileFragment) getUiFragment("FragmentTagMainContent")).handleReload();
        }
    }
}
