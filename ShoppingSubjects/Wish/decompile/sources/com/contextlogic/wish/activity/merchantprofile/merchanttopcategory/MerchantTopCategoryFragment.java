package com.contextlogic.wish.activity.merchantprofile.merchanttopcategory;

import android.view.View;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.feed.ProductFeedFragment;
import com.contextlogic.wish.activity.feed.ProductFeedFragment.DataMode;

public class MerchantTopCategoryFragment extends ProductFeedFragment {
    /* access modifiers changed from: private */
    public String mRequestId;

    public boolean canShowFeedCategories() {
        return false;
    }

    public boolean isFeedFilterable() {
        return false;
    }

    public void initializeLoadingContentView(View view) {
        super.initializeLoadingContentView(view);
        withActivity(new ActivityTask<MerchantTopCategoryActivity>() {
            public void performTask(MerchantTopCategoryActivity merchantTopCategoryActivity) {
                MerchantTopCategoryFragment.this.mRequestId = merchantTopCategoryActivity.getRequestId();
            }
        });
    }

    public DataMode getDataMode() {
        return DataMode.MerchantTopCategory;
    }

    /* access modifiers changed from: protected */
    public String getMainRequestId() {
        return this.mRequestId;
    }
}
