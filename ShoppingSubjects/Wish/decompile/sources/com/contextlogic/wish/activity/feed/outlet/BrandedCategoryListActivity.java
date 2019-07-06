package com.contextlogic.wish.activity.feed.outlet;

import android.os.Bundle;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.analytics.GoogleAnalyticsLogger.PageViewType;
import com.contextlogic.wish.api.model.WishCategory;
import java.util.ArrayList;

public class BrandedCategoryListActivity extends DrawerActivity {
    public int getBottomNavigationTabIndex() {
        return 1;
    }

    public String getMenuKey() {
        return null;
    }

    public void handleOnCreate(Bundle bundle) {
        super.handleOnCreate(bundle);
    }

    /* access modifiers changed from: protected */
    public UiFragment createMainContentFragment() {
        return new BrandedCategoryListFragment();
    }

    public String getActionBarTitle() {
        return getString(R.string.categories);
    }

    public PageViewType getGoogleAnalyticsPageViewType() {
        return PageViewType.BRANDED_CATEGORIES;
    }

    public ArrayList<WishCategory> getCategories() {
        return getIntent().getParcelableArrayListExtra("ExtraCategories");
    }
}
