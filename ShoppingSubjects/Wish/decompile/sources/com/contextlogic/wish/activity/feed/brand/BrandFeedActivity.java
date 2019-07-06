package com.contextlogic.wish.activity.feed.brand;

import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.activity.feed.BaseProductFeedServiceFragment;
import com.contextlogic.wish.analytics.GoogleAnalyticsLogger.PageViewType;
import com.contextlogic.wish.api.model.WishBrandFilter;
import com.contextlogic.wish.util.IntentUtil;

public class BrandFeedActivity extends DrawerActivity {
    public static String EXTRA_BRAND_FILTER = "ExtraBrandFilter";

    public boolean canBeTaskRoot() {
        return true;
    }

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
        return new BrandFeedFragment();
    }

    public String getActionBarTitle() {
        return getString(R.string.app_name);
    }

    public PageViewType getGoogleAnalyticsPageViewType() {
        return PageViewType.BRAND;
    }

    public WishBrandFilter getBrandFilter() {
        return (WishBrandFilter) IntentUtil.getParcelableExtra(getIntent(), EXTRA_BRAND_FILTER);
    }
}
