package com.contextlogic.wish.activity.feed.outlet;

import android.view.View;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.actionbar.ActionBarManager.Theme;
import com.contextlogic.wish.activity.feed.ProductFeedFragment;
import com.contextlogic.wish.activity.feed.ProductFeedFragment.DataMode;

public class BrandedFeedFragment extends ProductFeedFragment<BrandedFeedActivity> {
    private String mCategoryId;

    public boolean canShowFeedCategories() {
        return false;
    }

    public boolean isFeedFilterable() {
        return true;
    }

    public void initializeLoadingContentView(View view) {
        super.initializeLoadingContentView(view);
        this.mCategoryId = ((BrandedFeedActivity) getBaseActivity()).getCategoryId();
        withActivity(new ActivityTask<BrandedFeedActivity>() {
            public void performTask(BrandedFeedActivity brandedFeedActivity) {
                brandedFeedActivity.getActionBarManager().setTheme(Theme.WHITE_BACKGROUND);
            }
        });
    }

    public DataMode getDataMode() {
        return DataMode.Branded;
    }

    /* access modifiers changed from: protected */
    public String getMainRequestId() {
        return this.mCategoryId;
    }
}
