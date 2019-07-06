package com.contextlogic.wish.activity.feed.outlet;

import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.activity.feed.BaseProductFeedServiceFragment;
import com.contextlogic.wish.activity.feed.filter.FilterFragment;
import com.contextlogic.wish.analytics.GoogleAnalyticsLogger.PageViewType;
import com.contextlogic.wish.api.model.WishCategory;

public class BrandedFeedActivity extends DrawerActivity {
    public static String BRANDED_FEED_FILTER = "brand_";
    public static String EXTRA_CATEGORY = "ExtraCategory";

    public int getBottomNavigationTabIndex() {
        return 1;
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
        return new BrandedFeedFragment();
    }

    /* access modifiers changed from: protected */
    public UiFragment createRightDrawerFragment() {
        return new FilterFragment();
    }

    public String getActionBarTitle() {
        String name = ((WishCategory) getIntent().getParcelableExtra(EXTRA_CATEGORY)).getName();
        return name == null ? getString(R.string.app_name) : name;
    }

    public PageViewType getGoogleAnalyticsPageViewType() {
        return PageViewType.BRANDED_FEED;
    }

    public String getCategoryId() {
        return ((WishCategory) getIntent().getParcelableExtra(EXTRA_CATEGORY)).getFilterId();
    }
}
