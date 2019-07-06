package com.contextlogic.wish.activity.referralprogram;

import android.support.v4.content.ContextCompat;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.activity.menu.MenuFragment;
import com.contextlogic.wish.application.WishApplication;

public class ReferralProgramActivity extends DrawerActivity {
    public int getBottomNavigationTabIndex() {
        return 4;
    }

    /* access modifiers changed from: protected */
    public UiFragment createMainContentFragment() {
        return new ReferralProgramFragment();
    }

    /* access modifiers changed from: protected */
    public ServiceFragment createServiceFragment() {
        return new ReferralProgramServiceFragment();
    }

    public String getMenuKey() {
        return MenuFragment.MENU_KEY_REFERRAL_PROGRAM;
    }

    public String getActionBarTitle() {
        return getString(R.string.referral_program);
    }

    public int getBackgroundColor() {
        return ContextCompat.getColor(WishApplication.getInstance(), R.color.gray7);
    }
}
