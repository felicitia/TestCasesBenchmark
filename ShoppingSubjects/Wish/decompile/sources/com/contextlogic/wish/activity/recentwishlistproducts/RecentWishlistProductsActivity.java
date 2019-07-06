package com.contextlogic.wish.activity.recentwishlistproducts;

import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.activity.feed.BaseProductFeedServiceFragment;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.util.IntentUtil;
import java.util.ArrayList;

public class RecentWishlistProductsActivity extends DrawerActivity {
    public String getActionBarTitle() {
        return WishApplication.getInstance().getString(R.string.recent_wishlist_items);
    }

    /* access modifiers changed from: protected */
    public UiFragment createMainContentFragment() {
        return new RecentWishlistProductsFragment();
    }

    /* access modifiers changed from: protected */
    public ServiceFragment createServiceFragment() {
        return new BaseProductFeedServiceFragment();
    }

    public void onActivityHandlingBackPress() {
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_RECENT_WISHLIST_ITEMS_MODAL_CLOSE);
        super.onActivityHandlingBackPress();
    }

    public String getCategoryId() {
        return getIntent().getStringExtra("ExtraCategoryId");
    }

    public ArrayList<WishProduct> getRecentWishlistItems() {
        return IntentUtil.getParcelableArrayListExtra(getIntent(), "ExtraWishlistItems");
    }
}
