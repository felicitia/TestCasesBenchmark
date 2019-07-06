package com.contextlogic.wish.activity.productdetails;

import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.activity.feed.BaseProductFeedServiceFragment;
import com.contextlogic.wish.activity.feed.ProductFeedFragment.DataMode;

public class ProductDetailsRelatedItemsActivity extends DrawerActivity {
    public boolean shouldUseDynamicBottomNavigationLayout() {
        return true;
    }

    /* access modifiers changed from: protected */
    public UiFragment createMainContentFragment() {
        return new ProductDetailsRelatedItemsFragment();
    }

    /* access modifiers changed from: protected */
    public ServiceFragment createServiceFragment() {
        return new BaseProductFeedServiceFragment();
    }

    public String getActionBarTitle() {
        return getIntent().getStringExtra("ExtraTitle");
    }

    public String getRelatedProductId() {
        return getIntent().getStringExtra("ExtraId");
    }

    public DataMode getDataMode() {
        return (DataMode) getIntent().getSerializableExtra("ExtraDataMode");
    }
}
