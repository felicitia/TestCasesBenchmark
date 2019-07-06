package com.contextlogic.wish.activity.feed.search;

import android.content.Intent;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.activity.feed.BaseProductFeedServiceFragment;
import com.contextlogic.wish.analytics.GoogleAnalyticsLogger.PageViewType;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.util.IntentUtil;

public class SearchFeedActivity extends DrawerActivity {
    public static String EXTRA_QUERY = "ExtraQuery";

    /* access modifiers changed from: protected */
    public UiFragment createRightDrawerFragment() {
        return null;
    }

    public int getBottomNavigationTabIndex() {
        return 2;
    }

    public String getMenuKey() {
        return null;
    }

    public boolean shouldUseDynamicBottomNavigationLayout() {
        return true;
    }

    /* access modifiers changed from: protected */
    public ServiceFragment createServiceFragment() {
        return new BaseProductFeedServiceFragment();
    }

    /* access modifiers changed from: protected */
    public UiFragment createMainContentFragment() {
        return new SearchFeedFragment();
    }

    public String getActionBarTitle() {
        return getQuery();
    }

    public PageViewType getGoogleAnalyticsPageViewType() {
        return PageViewType.SEARCH;
    }

    public String getQuery() {
        return getIntent().getStringExtra(EXTRA_QUERY);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (IntentUtil.safeToUnparcel(intent) && intent.getBooleanExtra("ExtraRequiresReload", false)) {
            ((SearchFeedFragment) getUiFragment("FragmentTagMainContent")).handleReload(true);
        }
    }

    /* access modifiers changed from: protected */
    public void showAuthenticatingView() {
        if (ExperimentDataCenter.getInstance().shouldShowFeedToProductDetailTransition()) {
            lockDrawers(true);
        } else {
            super.showAuthenticatingView();
        }
    }
}
