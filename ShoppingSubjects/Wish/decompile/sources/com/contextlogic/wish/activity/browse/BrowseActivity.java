package com.contextlogic.wish.activity.browse;

import android.content.Intent;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.activity.feed.BaseProductFeedServiceFragment;
import com.contextlogic.wish.activity.feed.ProductFeedFragment;
import com.contextlogic.wish.activity.feed.filter.FilterFragment;
import com.contextlogic.wish.activity.menu.MenuFragment;
import com.contextlogic.wish.analytics.GoogleAnalyticsLogger.PageViewType;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.api.model.WishShippingInfo;
import com.contextlogic.wish.api.model.WishSignupFreeGiftCart;
import com.contextlogic.wish.util.IntentUtil;
import com.contextlogic.wish.util.TestUtil;
import java.util.ArrayList;

public class BrowseActivity extends DrawerActivity {
    public boolean canBeTaskRoot() {
        return true;
    }

    public int getBottomNavigationTabIndex() {
        return 1;
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
        return new BrowseFragment();
    }

    /* access modifiers changed from: protected */
    public UiFragment createRightDrawerFragment() {
        return new FilterFragment();
    }

    public String getActionBarTitle() {
        return getString(R.string.browse);
    }

    public String getMenuKey() {
        return MenuFragment.MENU_KEY_BROWSE;
    }

    public PageViewType getGoogleAnalyticsPageViewType() {
        return PageViewType.BROWSE;
    }

    public String getCategoryId() {
        return getIntent().getStringExtra("ExtraCategoryId");
    }

    public boolean getPlaceholderMode() {
        return getIntent().getBooleanExtra("ExtraPlaceholderMode", false);
    }

    public boolean canShowGiftInCartDialog() {
        if (getGiftConfirmedProduct() == null || getSignupCart() == null || !getIntent().getBooleanExtra("ExtraShowGiftinCartDialog", false)) {
            return false;
        }
        return true;
    }

    public boolean canShowFreeGiftDialog() {
        return getGiftConfirmedProduct() != null && getSignupCart() != null && !canShowGiftInCartDialog() && !canShowOrderConfirmedDialog();
    }

    /* access modifiers changed from: protected */
    public void showAuthenticatingView() {
        if (ExperimentDataCenter.getInstance().shouldShowFeedToProductDetailTransition()) {
            lockDrawers(true);
        } else {
            super.showAuthenticatingView();
        }
    }

    public WishProduct getGiftConfirmedProduct() {
        return (WishProduct) IntentUtil.getLargeParcelableExtra(getIntent(), "ExtraGiftConfirmedProduct", WishProduct.class);
    }

    public WishSignupFreeGiftCart getSignupCart() {
        return (WishSignupFreeGiftCart) IntentUtil.getParcelableExtra(getIntent(), "ExtraGiftConfirmedSignupCart");
    }

    public boolean canShowOrderConfirmedDialog() {
        return getIntent().getBooleanExtra("ExtraOrderConfirmedDialog", false);
    }

    public String getScreenshotXparam() {
        if (TestUtil.isRunningTest()) {
            return getIntent().getStringExtra("ExtraScreenshotXparam");
        }
        return null;
    }

    public ArrayList<String> getScreenshotFilters() {
        if (TestUtil.isRunningTest()) {
            return getIntent().getStringArrayListExtra("ExtraScreenshotFilters");
        }
        return null;
    }

    public WishShippingInfo getShippingInfo() {
        return (WishShippingInfo) IntentUtil.getParcelableExtra(getIntent(), "ExtraOrderConfirmedShippingInfo");
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (IntentUtil.safeToUnparcel(intent) && intent.getBooleanExtra("ExtraRequiresReload", false)) {
            ((ProductFeedFragment) getUiFragment("FragmentTagMainContent")).handleReload(true);
        }
    }
}
