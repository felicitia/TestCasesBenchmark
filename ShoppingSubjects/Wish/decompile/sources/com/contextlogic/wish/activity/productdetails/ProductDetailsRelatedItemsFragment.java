package com.contextlogic.wish.activity.productdetails;

import android.view.View;
import com.contextlogic.wish.activity.feed.ProductFeedFragment;
import com.contextlogic.wish.activity.feed.ProductFeedFragment.DataMode;

public class ProductDetailsRelatedItemsFragment extends ProductFeedFragment<ProductDetailsRelatedItemsActivity> {
    public boolean canShowFeedCategories() {
        return false;
    }

    public boolean isFeedFilterable() {
        return false;
    }

    public void initializeLoadingContentView(View view) {
        super.initializeLoadingContentView(view);
    }

    public DataMode getDataMode() {
        return getBaseActivity() == null ? DataMode.RelatedExpressProducts : ((ProductDetailsRelatedItemsActivity) getBaseActivity()).getDataMode();
    }

    /* access modifiers changed from: protected */
    public String getMainRequestId() {
        return getBaseActivity() != null ? ((ProductDetailsRelatedItemsActivity) getBaseActivity()).getRelatedProductId() : "";
    }
}
