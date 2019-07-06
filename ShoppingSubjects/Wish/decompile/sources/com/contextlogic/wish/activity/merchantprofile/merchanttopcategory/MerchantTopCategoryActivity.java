package com.contextlogic.wish.activity.merchantprofile.merchanttopcategory;

import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.activity.feed.BaseProductFeedServiceFragment;
import com.contextlogic.wish.analytics.GoogleAnalyticsLogger.PageViewType;
import java.util.ArrayList;

public class MerchantTopCategoryActivity extends DrawerActivity {
    public static String EXTRA_REQUEST_ID = "ExtraRequestId";
    public static String EXTRA_TAG_IDS = "ExtraTagIds";

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
        return new MerchantTopCategoryFragment();
    }

    public String getActionBarTitle() {
        return getString(R.string.store);
    }

    public PageViewType getGoogleAnalyticsPageViewType() {
        return PageViewType.MERCHANT;
    }

    public String getRequestId() {
        return getIntent().getStringExtra(EXTRA_REQUEST_ID);
    }

    public ArrayList<String> getTagIds() {
        return getIntent().getStringArrayListExtra(EXTRA_TAG_IDS);
    }
}
