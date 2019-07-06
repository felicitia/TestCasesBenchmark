package com.contextlogic.wish.activity.feed.merchant;

import android.view.View;
import com.contextlogic.wish.activity.feed.ProductFeedFragment;
import com.contextlogic.wish.activity.feed.ProductFeedFragment.DataMode;
import com.contextlogic.wish.api.model.WishBrandFilter;

public class MerchantFeedFragment extends ProductFeedFragment {
    private WishBrandFilter mBrandFilter;

    public boolean canShowFeedCategories() {
        return false;
    }

    public boolean isFeedFilterable() {
        return false;
    }

    public void initializeLoadingContentView(View view) {
        super.initializeLoadingContentView(view);
        this.mBrandFilter = new WishBrandFilter(((MerchantFeedActivity) getBaseActivity()).getMerchant());
    }

    public DataMode getDataMode() {
        return DataMode.Merchant;
    }

    public WishBrandFilter getBrandFilter() {
        return this.mBrandFilter;
    }

    /* access modifiers changed from: protected */
    public String getMainRequestId() {
        return this.mBrandFilter.getQuery();
    }
}
