package com.contextlogic.wish.activity.promocode;

import android.support.v4.content.ContextCompat;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.activity.actionbar.ActionBarManager;
import com.contextlogic.wish.activity.actionbar.ActionBarManager.Theme;
import com.contextlogic.wish.activity.menu.MenuFragment;
import com.contextlogic.wish.application.WishApplication;

public class PromoCodeActivity extends DrawerActivity {
    public int getBottomNavigationTabIndex() {
        return 4;
    }

    /* access modifiers changed from: protected */
    public UiFragment createMainContentFragment() {
        return new PromoCodeFragment();
    }

    /* access modifiers changed from: protected */
    public ServiceFragment createServiceFragment() {
        return new PromoCodeServiceFragment();
    }

    public String getMenuKey() {
        return MenuFragment.MENU_KEY_PROMO_CODE;
    }

    public String getActionBarTitle() {
        return getString(R.string.apply_promo);
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
        actionBarManager.setFadeToTheme(Theme.APP_COLOR_BACKGROUND, 0);
        actionBarManager.setTheme(Theme.TRANSPARENT_BACKGROUND_LIGHT_BUTTONS);
    }
}
