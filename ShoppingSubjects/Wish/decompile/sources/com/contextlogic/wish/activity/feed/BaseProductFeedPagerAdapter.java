package com.contextlogic.wish.activity.feed;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.activity.feed.ProductFeedFragment.DataMode;
import com.contextlogic.wish.activity.feed.dailygiveaway.DailyGiveawayFeedView;
import com.contextlogic.wish.activity.feed.dailygiveaway.DailyGiveawayFeedViewInterface;
import com.contextlogic.wish.activity.feed.dailyraffle.DailyRaffleFeedView;
import com.contextlogic.wish.activity.feed.dealdash.DealDashProductFeedView;
import com.contextlogic.wish.activity.feed.dealdash.DealDashProductFeedView.DealDashState;
import com.contextlogic.wish.activity.feed.freegifts.FreeGiftsTabProductFeedView;
import com.contextlogic.wish.activity.feed.merchanttab.MerchantFeedTabView;
import com.contextlogic.wish.activity.feed.recentlyviewed.RecentlyViewedTabView;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.WishDealDashInfo;
import com.contextlogic.wish.api.model.WishFreeGiftTabInfo;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.api.service.standalone.GetFilteredFeedService.FeedExtraInfo;
import com.contextlogic.wish.ui.view.SlidingTabStrip.IconTabProvider;
import java.util.ArrayList;
import java.util.Iterator;

public class BaseProductFeedPagerAdapter extends PagerAdapter implements IconTabProvider {
    protected DrawerActivity mBaseActivity;
    private SparseArray<ArrayList<BaseFeedHeaderView>> mCustomHeaders;
    private DailyGiveawayFeedView mDailyGiveawayFeedView;
    private boolean mDailyGiveawayIsOngoing;
    private int mDailyGiveawayPosition;
    private DailyRaffleFeedView mDailyRaffleFeedView;
    private WishDealDashInfo mDealDashInfo;
    private int mDealDashPosition;
    private SparseArray<BaseProductFeedView> mFeedViews = new SparseArray<>();
    protected ProductFeedFragment mFragment;
    private WishFreeGiftTabInfo mFreeGiftTabInfo;
    private int mFreeGiftsTabPosition;
    private int mLatestPosition;
    private int mMerchantFeedTabPosition;
    private MerchantFeedTabView mMerchantFeedTabView;
    private int mRecentWishlistTabPosition;
    private int mRecentlyViewedTabPosition;
    private RecentlyViewedTabView mRecentlyViewedTabView;
    private int mSearchWishExpressTabPosition;
    private int mWishExpressTabPosition;

    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public BaseProductFeedPagerAdapter(DrawerActivity drawerActivity, ProductFeedFragment productFeedFragment) {
        this.mBaseActivity = drawerActivity;
        this.mFragment = productFeedFragment;
        this.mDealDashPosition = -1;
        this.mFreeGiftsTabPosition = -1;
        this.mWishExpressTabPosition = -1;
        this.mSearchWishExpressTabPosition = -1;
        this.mDailyGiveawayPosition = -1;
        this.mLatestPosition = -1;
        this.mRecentlyViewedTabPosition = -1;
        this.mMerchantFeedTabPosition = -1;
        this.mRecentWishlistTabPosition = -1;
        this.mCustomHeaders = new SparseArray<>();
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        BaseProductFeedView baseProductFeedView;
        int currentIndex = this.mFragment.getCurrentIndex();
        if (i == this.mDailyGiveawayPosition) {
            if (ExperimentDataCenter.getInstance().shouldShowDailyRaffle()) {
                DailyRaffleFeedView dailyRaffleFeedView = new DailyRaffleFeedView(this.mBaseActivity, this.mFragment);
                viewGroup.addView(dailyRaffleFeedView);
                this.mDailyRaffleFeedView = dailyRaffleFeedView;
                if (currentIndex == i) {
                    this.mDailyRaffleFeedView.reload();
                }
                return dailyRaffleFeedView;
            }
            DailyGiveawayFeedView dailyGiveawayFeedView = new DailyGiveawayFeedView(i, this.mBaseActivity, this.mFragment);
            viewGroup.addView(dailyGiveawayFeedView);
            this.mDailyGiveawayFeedView = dailyGiveawayFeedView;
            if (currentIndex == i) {
                this.mDailyGiveawayFeedView.reload();
            }
            return dailyGiveawayFeedView;
        } else if (i == this.mRecentlyViewedTabPosition) {
            this.mRecentlyViewedTabView = new RecentlyViewedTabView(i, this.mBaseActivity, this.mFragment);
            viewGroup.addView(this.mRecentlyViewedTabView);
            if (currentIndex == i) {
                this.mRecentlyViewedTabView.handleResume();
            }
            return this.mRecentlyViewedTabView;
        } else if (i == this.mMerchantFeedTabPosition) {
            this.mMerchantFeedTabView = new MerchantFeedTabView(i, this.mBaseActivity, this.mFragment);
            viewGroup.addView(this.mMerchantFeedTabView);
            return this.mMerchantFeedTabView;
        } else {
            if (i == this.mFreeGiftsTabPosition) {
                baseProductFeedView = new FreeGiftsTabProductFeedView(i, this.mBaseActivity, this.mFragment, this.mFreeGiftTabInfo);
            } else if (i == this.mDealDashPosition) {
                baseProductFeedView = new DealDashProductFeedView(i, this.mBaseActivity, this.mFragment, this.mDealDashInfo);
            } else {
                baseProductFeedView = new BaseProductFeedView(i, this.mBaseActivity, this.mFragment);
            }
            baseProductFeedView.setRequestId(this.mFragment.getRequestId(i));
            baseProductFeedView.setLayoutParams(new LayoutParams(-1, -1));
            if (this.mCustomHeaders.get(i) != null) {
                ArrayList arrayList = new ArrayList();
                Iterator it = ((ArrayList) this.mCustomHeaders.get(i)).iterator();
                while (it.hasNext()) {
                    arrayList.add((BaseFeedHeaderView) it.next());
                }
                baseProductFeedView.setCustomHeaderViews(arrayList);
            }
            viewGroup.addView(baseProductFeedView);
            this.mFeedViews.put(i, baseProductFeedView);
            if (currentIndex == i) {
                baseProductFeedView.handleResume();
            }
            return baseProductFeedView;
        }
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        if (obj instanceof BaseProductFeedView) {
            ((BaseProductFeedView) obj).handleDestroy();
            this.mFeedViews.remove(i);
        }
        viewGroup.removeView((View) obj);
    }

    public void onDestroy() {
        for (int i = 0; i < this.mFeedViews.size(); i++) {
            ((BaseProductFeedView) this.mFeedViews.valueAt(i)).handleDestroy();
        }
    }

    public void restoreImages() {
        for (int i = 0; i < this.mFeedViews.size(); i++) {
            ((BaseProductFeedView) this.mFeedViews.valueAt(i)).restoreImages();
        }
        if (this.mDailyGiveawayFeedView != null) {
            this.mDailyGiveawayFeedView.restoreImages();
        }
        if (this.mRecentlyViewedTabView != null) {
            this.mRecentlyViewedTabView.restoreImages();
        }
        if (this.mDailyRaffleFeedView != null) {
            this.mDailyRaffleFeedView.restoreImages();
        }
        for (int i2 = 0; i2 < this.mCustomHeaders.size(); i2++) {
            Iterator it = ((ArrayList) this.mCustomHeaders.valueAt(i2)).iterator();
            while (it.hasNext()) {
                ((BaseFeedHeaderView) it.next()).restoreImages();
            }
        }
    }

    public void releaseImages() {
        for (int i = 0; i < this.mFeedViews.size(); i++) {
            ((BaseProductFeedView) this.mFeedViews.valueAt(i)).releaseImages();
        }
        if (this.mDailyGiveawayFeedView != null) {
            this.mDailyGiveawayFeedView.releaseImages();
        }
        if (this.mRecentlyViewedTabView != null) {
            this.mRecentlyViewedTabView.releaseImages();
        }
        if (this.mDailyRaffleFeedView != null) {
            this.mDailyRaffleFeedView.releaseImages();
        }
        for (int i2 = 0; i2 < this.mCustomHeaders.size(); i2++) {
            Iterator it = ((ArrayList) this.mCustomHeaders.valueAt(i2)).iterator();
            while (it.hasNext()) {
                ((BaseFeedHeaderView) it.next()).releaseImages();
            }
        }
    }

    public BaseProductFeedView getFeedView(int i) {
        for (int i2 = 0; i2 < this.mFeedViews.size(); i2++) {
            BaseProductFeedView baseProductFeedView = (BaseProductFeedView) this.mFeedViews.valueAt(i2);
            if (baseProductFeedView.getDataIndex() == i) {
                return baseProductFeedView;
            }
        }
        return null;
    }

    public void handleSaveInstanceState(Bundle bundle) {
        for (int i = 0; i < this.mFeedViews.size(); i++) {
            BaseProductFeedView baseProductFeedView = (BaseProductFeedView) this.mFeedViews.valueAt(i);
            Bundle savedInstanceState = baseProductFeedView.getSavedInstanceState();
            if (savedInstanceState != null) {
                bundle.putBundle(this.mFragment.getPagedDataSavedInstanceStateKey(baseProductFeedView.getDataIndex()), savedInstanceState);
            }
        }
    }

    public BaseProductFeedView getCurrentFeedView() {
        return getFeedView(this.mFragment.getCurrentIndex());
    }

    public DealDashProductFeedView getDealDashFeedView() {
        return (DealDashProductFeedView) getFeedView(this.mDealDashPosition);
    }

    public DailyGiveawayFeedViewInterface getDailyGiveawayFeedView() {
        if (ExperimentDataCenter.getInstance().shouldShowDailyRaffle()) {
            return this.mDailyRaffleFeedView;
        }
        return this.mDailyGiveawayFeedView;
    }

    public ArrayList<WishProduct> getSelectedProducts() {
        ArrayList<WishProduct> arrayList = new ArrayList<>();
        for (int i = 0; i < this.mFeedViews.size(); i++) {
            arrayList.addAll(((BaseProductFeedView) this.mFeedViews.valueAt(i)).getSelectedProducts());
        }
        return arrayList;
    }

    public void reloadAll() {
        for (int i = 0; i < this.mFeedViews.size(); i++) {
            ((BaseProductFeedView) this.mFeedViews.valueAt(i)).markNeedsReload();
        }
        BaseProductFeedView currentFeedView = getCurrentFeedView();
        if (currentFeedView != null) {
            currentFeedView.reload();
        }
    }

    public void handleResume() {
        BaseProductFeedView currentFeedView = getCurrentFeedView();
        if (currentFeedView != null) {
            currentFeedView.handleResume();
        }
        if (this.mRecentlyViewedTabView != null && this.mFragment.getCurrentIndex() == this.mRecentlyViewedTabPosition) {
            this.mRecentlyViewedTabView.handleResume();
        }
    }

    public void handleLoadingErrored(int i) {
        BaseProductFeedView feedView = getFeedView(i);
        if (feedView != null) {
            feedView.handleLoadingErrored();
        }
    }

    public void handleLoadingSuccess(int i, ArrayList<WishProduct> arrayList, int i2, boolean z) {
        handleLoadingSuccess(i, arrayList, i2, z, null);
    }

    public void handleLoadingSuccess(int i, ArrayList<WishProduct> arrayList, int i2, boolean z, FeedExtraInfo feedExtraInfo) {
        BaseProductFeedView feedView = getFeedView(i);
        if (feedView != null) {
            feedView.handleLoadingSuccess(arrayList, i2, z);
        } else if (i == this.mRecentlyViewedTabPosition && this.mRecentlyViewedTabView != null) {
            this.mRecentlyViewedTabView.handleLoadingProductsSuccess(arrayList, i2, z);
        }
    }

    public void setEditModeEnabled(boolean z) {
        for (int i = 0; i < this.mFeedViews.size(); i++) {
            ((BaseProductFeedView) this.mFeedViews.valueAt(i)).setEditModeEnabled(z);
        }
    }

    public int getCount() {
        return this.mFragment.getFeedTypeCount();
    }

    public String getPageTitle(int i) {
        return this.mFragment.getFeedTitle(i);
    }

    public void setDealDash(int i, WishDealDashInfo wishDealDashInfo) {
        this.mDealDashInfo = wishDealDashInfo;
        this.mDealDashPosition = i;
    }

    public void setDailyGiveawayTab(int i, boolean z) {
        this.mDailyGiveawayPosition = i;
        this.mDailyGiveawayIsOngoing = z;
    }

    public boolean getDailyGiveawayIsOngoing() {
        return this.mDailyGiveawayIsOngoing;
    }

    public void setFreeGiftsTab(int i, WishFreeGiftTabInfo wishFreeGiftTabInfo) {
        this.mFreeGiftsTabPosition = i;
        this.mFreeGiftTabInfo = wishFreeGiftTabInfo;
    }

    public void setWishExpressTab(int i) {
        this.mWishExpressTabPosition = i;
    }

    public void setRecentWishlistTab(int i) {
        this.mRecentWishlistTabPosition = i;
    }

    public void setRecentlyViewedTabPosition(int i) {
        this.mRecentlyViewedTabPosition = i;
    }

    public void setMerchantFeedTabPosition(int i) {
        this.mMerchantFeedTabPosition = i;
    }

    public void setSearchWishExpressTab(int i) {
        this.mSearchWishExpressTabPosition = i;
    }

    public void addCustomHeaders(ArrayList<BaseFeedHeaderView> arrayList, int i) {
        BaseProductFeedView feedView = getFeedView(i);
        this.mCustomHeaders.append(i, arrayList);
        if (feedView != null) {
            ArrayList arrayList2 = new ArrayList();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                arrayList2.add((BaseFeedHeaderView) it.next());
            }
            feedView.setCustomHeaderViews(arrayList2);
        }
    }

    public void addCustomHeader(BaseFeedHeaderView baseFeedHeaderView, int i) {
        BaseProductFeedView feedView = getFeedView(i);
        if (this.mCustomHeaders.get(i) == null) {
            this.mCustomHeaders.append(i, new ArrayList());
        } else {
            ((ArrayList) this.mCustomHeaders.get(i)).clear();
        }
        ((ArrayList) this.mCustomHeaders.get(i)).add(baseFeedHeaderView);
        if (feedView != null) {
            feedView.setCustomHeaderView(baseFeedHeaderView);
        }
    }

    public void resetInfo() {
        this.mFeedViews.clear();
        this.mDealDashPosition = -1;
        this.mDealDashInfo = null;
        this.mFreeGiftsTabPosition = -1;
        this.mFreeGiftTabInfo = null;
        this.mWishExpressTabPosition = -1;
        this.mSearchWishExpressTabPosition = -1;
        this.mRecentWishlistTabPosition = -1;
        this.mCustomHeaders.clear();
    }

    public int getPageIconResId(int i) {
        if (i == this.mDailyGiveawayPosition) {
            return R.drawable.giveaway_icon;
        }
        if (i == this.mDealDashPosition) {
            return ExperimentDataCenter.getInstance().shouldShowOutletIcon() ? R.drawable.deal_dash_icon_v2 : R.drawable.deal_dash_icon;
        }
        if (i == this.mWishExpressTabPosition || i == this.mSearchWishExpressTabPosition) {
            return ExperimentDataCenter.getInstance().shouldShowOutletIcon() ? R.drawable.fast_shipping_tab_icon_v2 : R.drawable.fast_shipping_tab_icon;
        }
        if (i == this.mMerchantFeedTabPosition) {
            return R.drawable.merchant_icon;
        }
        return 0;
    }

    public void notifyTabOffsetUpdated() {
        for (int i = 0; i < this.mFeedViews.size(); i++) {
            ((BaseProductFeedView) this.mFeedViews.valueAt(i)).notifyTabOffsetUpdated();
        }
    }

    public boolean onBackPressed() {
        boolean z = false;
        if (this.mFragment.getDataMode() != DataMode.FilteredFeed) {
            return false;
        }
        if (getCurrentFeedView() != null && (!(getCurrentFeedView() instanceof DealDashProductFeedView) || ((DealDashProductFeedView) getCurrentFeedView()).getState() != DealDashState.ACCESS_GRANTED)) {
            if (ExperimentDataCenter.getInstance().shouldReloadAllFeedOnBackPress()) {
                if (getCurrentFeedView().getCurrentScrollY() > 0) {
                    z = smoothScrollToTop();
                }
            } else if (ExperimentDataCenter.getInstance().shouldReloadFeedOrGoBackToBrowse()) {
                z = getCurrentFeedView().getCurrentScrollY() > 0 ? smoothScrollToTop() : switchToBrowseAndGoToTop();
            } else if (ExperimentDataCenter.getInstance().shouldGoBackToBrowseAndReload()) {
                z = switchToBrowseAndGoToTop();
            }
            return z;
        } else if (ExperimentDataCenter.getInstance().shouldReloadFeedOrGoBackToBrowse() || ExperimentDataCenter.getInstance().shouldGoBackToBrowseAndReload()) {
            return switchToBrowseAndGoToTop();
        } else {
            return false;
        }
    }

    public boolean smoothScrollToTop() {
        this.mFragment.showTabArea(true);
        if (getCurrentFeedView() == null) {
            return false;
        }
        getCurrentFeedView().smoothScrollToTopAndReload();
        if (getCurrentFeedView().getDataIndex() == this.mLatestPosition) {
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_MOBILE_BACK_TO_TOP_BROWSE);
        } else {
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_MOBILE_BACK_TO_TOP_TABBED_FEED);
        }
        if (ExperimentDataCenter.getInstance().shouldShowBottomNavigation()) {
            this.mFragment.popInBottomNavigation(true);
        }
        return true;
    }

    public boolean switchToBrowseAndGoToTop() {
        boolean z = true;
        if ((getCurrentFeedView() == null || this.mLatestPosition != getCurrentFeedView().getDataIndex()) && this.mLatestPosition != -1) {
            this.mFragment.showTabArea(true);
            this.mFragment.setSelectedTab(this.mLatestPosition);
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_MOBILE_BACK_TO_BROWSE);
        } else {
            z = false;
        }
        return (getCurrentFeedView() == null || getCurrentFeedView().getCurrentScrollY() <= 0) ? z : smoothScrollToTop();
    }
}
