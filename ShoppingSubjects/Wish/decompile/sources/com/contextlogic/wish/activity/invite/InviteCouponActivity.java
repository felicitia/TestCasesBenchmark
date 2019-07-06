package com.contextlogic.wish.activity.invite;

import android.os.Bundle;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.activity.menu.MenuFragment;
import com.contextlogic.wish.analytics.GoogleAnalyticsLogger.PageViewType;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.ConfigDataCenter;
import com.contextlogic.wish.application.ApplicationEventManager;
import com.contextlogic.wish.application.ApplicationEventManager.EventType;
import com.contextlogic.wish.util.PreferenceUtil;

public class InviteCouponActivity extends DrawerActivity {
    public int getBottomNavigationTabIndex() {
        return 4;
    }

    public void handleOnCreate(Bundle bundle) {
        super.handleOnCreate(bundle);
        withVerifiedAuthentication(new Runnable() {
            public void run() {
                if (ConfigDataCenter.getInstance().getInviteCouponSpec() == null) {
                    InviteCouponActivity.this.finishActivity();
                    return;
                }
                PreferenceUtil.setBoolean("SawInviteCouponScreen", true);
                ApplicationEventManager.getInstance().triggerEvent(EventType.BADGE_SECTION_VIEWED, MenuFragment.MENU_KEY_INVITE_FRIENDS, null);
            }
        });
    }

    /* access modifiers changed from: protected */
    public UiFragment createMainContentFragment() {
        return new InviteCouponFragment();
    }

    public String getActionBarTitle() {
        if (ConfigDataCenter.getInstance().getInviteCouponSpec() != null) {
            return ConfigDataCenter.getInstance().getInviteCouponSpec().getMenuTitle();
        }
        return null;
    }

    public String getMenuKey() {
        return MenuFragment.MENU_KEY_INVITE_FRIENDS;
    }

    public PageViewType getGoogleAnalyticsPageViewType() {
        return PageViewType.INVITE_COUPON;
    }

    public WishAnalyticsEvent getWishAnalyticsPageViewType() {
        return WishAnalyticsEvent.IMPRESSION_INVITE_BY_COUPON;
    }
}
