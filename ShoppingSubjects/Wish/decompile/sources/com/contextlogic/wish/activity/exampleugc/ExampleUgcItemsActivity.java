package com.contextlogic.wish.activity.exampleugc;

import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.analytics.GoogleAnalyticsLogger.PageViewType;
import com.contextlogic.wish.application.WishApplication;

public class ExampleUgcItemsActivity extends DrawerActivity {
    public int getBottomNavigationTabIndex() {
        return 0;
    }

    /* access modifiers changed from: protected */
    public UiFragment createMainContentFragment() {
        return new ExampleUgcItemsFragment();
    }

    /* access modifiers changed from: protected */
    public ServiceFragment createServiceFragment() {
        return new ExampleUgcItemsServiceFragment();
    }

    public String getActionBarTitle() {
        return WishApplication.getInstance().getString(R.string.share_your_purchase);
    }

    public PageViewType getGoogleAnalyticsPageViewType() {
        return PageViewType.EXAMPLE_UGC_ITEMS;
    }
}
