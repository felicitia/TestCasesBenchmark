package com.contextlogic.wish.activity.feed.tag;

import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.activity.feed.BaseProductFeedServiceFragment;
import com.contextlogic.wish.analytics.GoogleAnalyticsLogger.PageViewType;

public class TagFeedActivity extends DrawerActivity {
    public static String EXTRA_TAG_ID = "ExtraTagId";

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
        return new TagFeedFragment();
    }

    public String getActionBarTitle() {
        return getString(R.string.app_name);
    }

    public PageViewType getGoogleAnalyticsPageViewType() {
        return PageViewType.TAG;
    }

    public String getTagId() {
        return getIntent().getStringExtra(EXTRA_TAG_ID);
    }
}
