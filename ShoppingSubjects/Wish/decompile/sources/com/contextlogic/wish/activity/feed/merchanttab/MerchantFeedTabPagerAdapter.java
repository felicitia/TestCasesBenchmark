package com.contextlogic.wish.activity.feed.merchanttab;

import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.activity.feed.BaseProductFeedServiceFragment;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.ui.bottomnavigation.BottomNavigationInterface;
import com.contextlogic.wish.ui.image.ImageRestorable;
import com.contextlogic.wish.ui.viewpager.ViewPagerAdapter;
import java.util.Arrays;
import java.util.List;

public class MerchantFeedTabPagerAdapter extends ViewPagerAdapter implements OnPageChangeListener, ImageRestorable {
    private BottomNavigationInterface mBottomNavigationInterface;
    private DrawerActivity mDrawerActivity;
    private BaseProductFeedServiceFragment mServiceFragment;
    private TopRatedMerchantFeedView mTopRatedMerchantFeedView;
    private TrendingMerchantFeedView mTrendingMerchantFeedView;
    private List<Tab> tabs = Arrays.asList(new Tab[]{Tab.TOP_RATED_MERCHANTS, Tab.TRENDING_MERCHANTS});

    private enum Tab {
        TOP_RATED_MERCHANTS,
        TRENDING_MERCHANTS
    }

    public void onPageScrollStateChanged(int i) {
    }

    public void onPageScrolled(int i, float f, int i2) {
    }

    public MerchantFeedTabPagerAdapter(DrawerActivity drawerActivity) {
        this.mDrawerActivity = drawerActivity;
        this.mServiceFragment = (BaseProductFeedServiceFragment) drawerActivity.getServiceFragment();
        if (ExperimentDataCenter.getInstance().shouldShowBottomNavigation()) {
            UiFragment uiFragment = this.mDrawerActivity.getUiFragment("FragmentTagMainContent");
            if (uiFragment instanceof BottomNavigationInterface) {
                this.mBottomNavigationInterface = (BottomNavigationInterface) uiFragment;
            }
        }
    }

    public int getCount() {
        return this.tabs.size();
    }

    public View getView(ViewPager viewPager, int i) {
        Tab tab = (Tab) this.tabs.get(i);
        if (tab == Tab.TOP_RATED_MERCHANTS) {
            if (this.mTopRatedMerchantFeedView == null) {
                this.mTopRatedMerchantFeedView = new TopRatedMerchantFeedView(this.mServiceFragment);
                if (this.mBottomNavigationInterface != null) {
                    this.mTopRatedMerchantFeedView.setBottomNavigationInterface(this.mBottomNavigationInterface);
                }
            }
            return this.mTopRatedMerchantFeedView;
        } else if (tab == Tab.TRENDING_MERCHANTS) {
            if (this.mTrendingMerchantFeedView == null) {
                this.mTrendingMerchantFeedView = new TrendingMerchantFeedView(this.mServiceFragment);
                if (this.mBottomNavigationInterface != null) {
                    this.mTrendingMerchantFeedView.setBottomNavigationInterface(this.mBottomNavigationInterface);
                }
            }
            return this.mTrendingMerchantFeedView;
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Cannot return merchant feed tab for position at ");
            sb.append(i);
            throw new IllegalArgumentException(sb.toString());
        }
    }

    public void releaseImages() {
        if (this.mTopRatedMerchantFeedView != null) {
            this.mTopRatedMerchantFeedView.releaseImages();
        }
        if (this.mTrendingMerchantFeedView != null) {
            this.mTrendingMerchantFeedView.releaseImages();
        }
    }

    public void restoreImages() {
        if (this.mTopRatedMerchantFeedView != null) {
            this.mTopRatedMerchantFeedView.restoreImages();
        }
        if (this.mTrendingMerchantFeedView != null) {
            this.mTrendingMerchantFeedView.restoreImages();
        }
    }

    public CharSequence getPageTitle(int i) {
        Tab tab = (Tab) this.tabs.get(i);
        if (tab == Tab.TOP_RATED_MERCHANTS) {
            return this.mDrawerActivity.getString(R.string.merchant_feed_top_rated_tab);
        }
        if (tab == Tab.TRENDING_MERCHANTS) {
            return this.mDrawerActivity.getString(R.string.merchant_feed_trending_tab);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Cannot return merchant page title for position at ");
        sb.append(i);
        throw new IllegalArgumentException(sb.toString());
    }

    public void onPageSelected(int i) {
        Tab tab = (Tab) this.tabs.get(i);
        if (tab == Tab.TOP_RATED_MERCHANTS) {
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MERCHANTS_FEED_TAB_TOP_RATED_TAB);
        } else if (tab == Tab.TRENDING_MERCHANTS) {
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MERCHANTS_FEED_TAB_TRENDING_TAB);
        }
    }
}
