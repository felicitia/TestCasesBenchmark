package com.contextlogic.wish.activity.profile.wishlist;

import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.activity.feed.BaseProductFeedServiceFragment;
import com.contextlogic.wish.analytics.GoogleAnalyticsLogger.PageViewType;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.WishWishlist;
import com.contextlogic.wish.util.IntentUtil;

public class WishlistActivity extends DrawerActivity {
    public static String EXTRA_CAN_EDIT_WISHLIST = "ExtraCanEditWishlist";
    public static String EXTRA_CAN_RENAME_WISHLIST = "ExtraCanRenameWishlist";
    public static String EXTRA_WISHLIST = "ExtraWishlist";
    public static String EXTRA_WISHLIST_ID = "ExtraWishlistId";

    public int getBottomNavigationTabIndex() {
        return 4;
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
        return new WishlistFragment();
    }

    public WishAnalyticsEvent getClickImpressionActionBarCartButton() {
        return WishAnalyticsEvent.CLICK_ACTION_BAR_CART_BUTTON_ON_WISHLIST;
    }

    public String getActionBarTitle() {
        return getWishlistName();
    }

    public PageViewType getGoogleAnalyticsPageViewType() {
        return PageViewType.WISHLIST;
    }

    public WishWishlist getWishlist() {
        return (WishWishlist) IntentUtil.getParcelableExtra(getIntent(), EXTRA_WISHLIST);
    }

    public String getWishlistId() {
        return getIntent().getStringExtra(EXTRA_WISHLIST_ID);
    }

    public String getWishlistName() {
        WishWishlist wishlist = getWishlist();
        return wishlist != null ? wishlist.getName() : getString(R.string.wishlist);
    }

    public boolean canRenameWishlist() {
        return getIntent().getBooleanExtra(EXTRA_CAN_RENAME_WISHLIST, canEditWishlist());
    }

    public boolean canEditWishlist() {
        return getIntent().getBooleanExtra(EXTRA_CAN_EDIT_WISHLIST, false);
    }

    /* access modifiers changed from: protected */
    public void showAuthenticatingView() {
        if (ExperimentDataCenter.getInstance().shouldShowFeedToProductDetailTransition()) {
            lockDrawers(true);
        } else {
            super.showAuthenticatingView();
        }
    }
}
