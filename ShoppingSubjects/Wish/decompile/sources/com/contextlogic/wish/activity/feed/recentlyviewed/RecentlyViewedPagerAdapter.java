package com.contextlogic.wish.activity.feed.recentlyviewed;

import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.activity.feed.BaseProductFeedServiceFragment;
import com.contextlogic.wish.activity.feed.BaseProductFeedView;
import com.contextlogic.wish.activity.feed.ProductFeedFragment;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.ui.image.ImageRestorable;
import com.contextlogic.wish.ui.viewpager.ViewPagerAdapter;
import java.util.Arrays;
import java.util.List;

public class RecentlyViewedPagerAdapter extends ViewPagerAdapter implements OnPageChangeListener, ImageRestorable {
    private int mDataIndex;
    private DrawerActivity mDrawerActivity;
    private ProductFeedFragment mFragment;
    private RecentlyViewedMerchantView mRecentlyViewedMerchantsView;
    private BaseProductFeedView mRecentlyViewedProductsView;
    private List<Tab> tabs = Arrays.asList(new Tab[]{Tab.RECENTLY_VIEWED_PRODUCT, Tab.RECENTLY_VIEWED_STORE});

    private enum Tab {
        RECENTLY_VIEWED_PRODUCT,
        RECENTLY_VIEWED_STORE
    }

    public void onPageScrollStateChanged(int i) {
    }

    public void onPageScrolled(int i, float f, int i2) {
    }

    public RecentlyViewedPagerAdapter(int i, DrawerActivity drawerActivity, ProductFeedFragment productFeedFragment) {
        this.mDataIndex = i;
        this.mDrawerActivity = drawerActivity;
        this.mFragment = productFeedFragment;
    }

    public int getCount() {
        return this.tabs.size();
    }

    public BaseProductFeedView getRecentlyViewedProductsView() {
        return this.mRecentlyViewedProductsView;
    }

    public void handleResume() {
        initializeRecentlyViewedProductFeedView();
        this.mRecentlyViewedProductsView.handleResume();
    }

    private void initializeRecentlyViewedProductFeedView() {
        if (this.mRecentlyViewedProductsView == null) {
            this.mRecentlyViewedProductsView = new RecentlyViewedProductFeedView(this.mDataIndex, this.mDrawerActivity, this.mFragment);
            this.mRecentlyViewedProductsView.setRequestId("recently_viewed__tab");
        }
    }

    public View getView(ViewPager viewPager, int i) {
        Tab tab = (Tab) this.tabs.get(i);
        if (tab == Tab.RECENTLY_VIEWED_PRODUCT) {
            initializeRecentlyViewedProductFeedView();
            return this.mRecentlyViewedProductsView;
        } else if (tab == Tab.RECENTLY_VIEWED_STORE) {
            if (this.mRecentlyViewedMerchantsView == null) {
                this.mRecentlyViewedMerchantsView = new RecentlyViewedMerchantView((BaseProductFeedServiceFragment) this.mDrawerActivity.getServiceFragment());
                this.mRecentlyViewedMerchantsView.setBottomNavigationInterface(this.mFragment);
            }
            return this.mRecentlyViewedMerchantsView;
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Cannot return recently viewed tab for position at ");
            sb.append(i);
            throw new IllegalArgumentException(sb.toString());
        }
    }

    public void releaseImages() {
        if (this.mRecentlyViewedProductsView != null) {
            this.mRecentlyViewedProductsView.releaseImages();
        }
        if (this.mRecentlyViewedMerchantsView != null) {
            this.mRecentlyViewedMerchantsView.releaseImages();
        }
    }

    public void restoreImages() {
        if (this.mRecentlyViewedProductsView != null) {
            this.mRecentlyViewedProductsView.restoreImages();
        }
        if (this.mRecentlyViewedMerchantsView != null) {
            this.mRecentlyViewedMerchantsView.restoreImages();
        }
    }

    public CharSequence getPageTitle(int i) {
        if (i == 0) {
            return this.mDrawerActivity.getString(R.string.recently_viewed_products_tab);
        }
        return this.mDrawerActivity.getString(R.string.recently_viewed_stores_tab);
    }

    public void onPageSelected(int i) {
        Tab tab = (Tab) this.tabs.get(i);
        if (tab == Tab.RECENTLY_VIEWED_PRODUCT) {
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_RECENTLY_VIEWED_PRODUCT_TAB);
        } else if (tab == Tab.RECENTLY_VIEWED_STORE) {
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_RECENTLY_VIEWED_STORES_TAB);
        }
    }
}
