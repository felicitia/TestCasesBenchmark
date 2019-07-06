package com.contextlogic.wish.activity.recentwishlistproducts;

import android.view.View;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.actionbar.ActionBarManager.HomeButtonMode;
import com.contextlogic.wish.activity.feed.ProductFeedFragment;
import com.contextlogic.wish.activity.feed.ProductFeedFragment.DataMode;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishProduct;
import java.util.ArrayList;

public class RecentWishlistProductsFragment extends ProductFeedFragment {
    /* access modifiers changed from: private */
    public String mRequestId;
    /* access modifiers changed from: private */
    public ArrayList<WishProduct> mWishlistItems;

    public boolean canFeedViewPullToRefresh() {
        return false;
    }

    public boolean canShowFeedCategories() {
        return false;
    }

    public boolean isFeedFilterable() {
        return false;
    }

    public void initializeLoadingContentView(View view) {
        super.initializeLoadingContentView(view);
        withActivity(new ActivityTask<RecentWishlistProductsActivity>() {
            public void performTask(RecentWishlistProductsActivity recentWishlistProductsActivity) {
                RecentWishlistProductsFragment.this.mWishlistItems = recentWishlistProductsActivity.getRecentWishlistItems();
                RecentWishlistProductsFragment.this.mRequestId = recentWishlistProductsActivity.getCategoryId();
                recentWishlistProductsActivity.getActionBarManager().setHomeButtonMode(HomeButtonMode.X_ICON);
            }
        });
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_RECENT_WISHLIST_ITEMS_MODAL);
    }

    /* access modifiers changed from: protected */
    public String getMainRequestId() {
        return this.mRequestId;
    }

    public DataMode getDataMode() {
        return DataMode.RecentWishlist;
    }

    public boolean hasItems() {
        return (this.mWishlistItems == null || this.mWishlistItems.size() == 0) ? false : true;
    }

    /* access modifiers changed from: protected */
    public void refreshActionBar() {
        setupBaseActionBar();
    }
}
