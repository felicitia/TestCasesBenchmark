package com.contextlogic.wish.activity.rewards;

import android.content.Intent;
import android.os.Bundle;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.activity.browse.BrowseActivity;
import com.contextlogic.wish.activity.menu.MenuFragment;
import com.contextlogic.wish.activity.rewards.redesign.RewardsFragment;
import com.contextlogic.wish.activity.rewards.redesign.RewardsServiceFragment;
import com.contextlogic.wish.analytics.GoogleAnalyticsLogger.PageViewType;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;

public class RewardsActivity extends DrawerActivity {
    public int getBottomNavigationTabIndex() {
        return 4;
    }

    /* access modifiers changed from: protected */
    public void handleOnCreate(Bundle bundle) {
        if (!ExperimentDataCenter.getInstance().turnOffRewards()) {
            return;
        }
        if (isTaskRoot()) {
            Intent intent = new Intent();
            intent.setClass(this, BrowseActivity.class);
            startActivity(intent, true);
            return;
        }
        finishActivity();
    }

    /* access modifiers changed from: protected */
    public ServiceFragment createServiceFragment() {
        return new RewardsServiceFragment();
    }

    /* access modifiers changed from: protected */
    public UiFragment createMainContentFragment() {
        return new RewardsFragment();
    }

    public String getActionBarTitle() {
        return getString(R.string.rewards);
    }

    public String getMenuKey() {
        return MenuFragment.MENU_KEY_REWARDS;
    }

    public PageViewType getGoogleAnalyticsPageViewType() {
        return PageViewType.REWARDS;
    }
}
