package com.contextlogic.wish.activity.merchantprofile;

import android.os.Bundle;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.activity.actionbar.ActionBarItem;
import com.contextlogic.wish.activity.feed.BaseInitialProductWrapper;
import com.contextlogic.wish.activity.feed.BaseProductFeedFragment;
import com.contextlogic.wish.activity.feed.BaseProductFeedServiceFragment;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishBrandFilter;
import com.contextlogic.wish.api.model.WishMerchant;
import com.contextlogic.wish.api.model.WishMerchantTopCategory;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.api.model.WishRating;
import com.contextlogic.wish.api.service.standalone.BaseFeedApiService.FeedExtraDataBundle;
import com.contextlogic.wish.api.service.standalone.GetMerchantFeedService.FeedContext;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.cache.StateStoreCache;
import com.contextlogic.wish.http.ImageHttpPrefetcher;
import com.contextlogic.wish.ui.viewpager.PagerSlidingTabStrip;
import com.contextlogic.wish.ui.viewpager.SafeViewPager;
import com.contextlogic.wish.util.FontUtil;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class MerchantProfileFragment extends BaseProductFeedFragment<MerchantProfileActivity> {
    private boolean isInitialProductsFetch;
    private int mBannerHeight;
    private boolean mBannerTabsSet;
    private MerchantProfileBannerView mBannerView;
    private WishBrandFilter mBrandFilter;
    private ImageHttpPrefetcher mCategoriesImagePrefetcher;
    private int mCurrentOffset;
    private FeedExtraDataBundle mFeedExtraDataBundle;
    private BaseInitialProductWrapper mInitialProducts;
    private HashSet<String> mLastSeenProductIds;
    private ImageHttpPrefetcher mMerchantRatingsImagePrefetcher;
    /* access modifiers changed from: private */
    public SafeViewPager mMerchantViewPager;
    /* access modifiers changed from: private */
    public OnPageChangeListener mPageScrollListener;
    /* access modifiers changed from: private */
    public MerchantProfilePagerAdapter mPagerAdapter;
    private int mRestoredIndex;
    private int mTabBarHeight;
    /* access modifiers changed from: private */
    public PagerSlidingTabStrip mTabStrip;
    private View mTabStripContainer;
    /* access modifiers changed from: private */
    public View mTabStripShadow;

    public static class BooleanWrapper {
        public boolean state;

        public BooleanWrapper(boolean z) {
            this.state = z;
        }
    }

    public boolean canFeedViewPullToRefresh() {
        return false;
    }

    public boolean canPullToRefresh() {
        return false;
    }

    public int getLoadingContentLayoutResourceId() {
        return R.layout.merchant_profile_fragment;
    }

    public String getProductId() {
        return null;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mPageScrollListener = new OnPageChangeListener() {
            public void onPageScrolled(int i, float f, int i2) {
            }

            public void onPageSelected(int i) {
                MerchantProfileFragment.this.handlePageSelected(i);
            }

            public void onPageScrollStateChanged(int i) {
                if (i == 0) {
                    MerchantProfileFragment.this.onPagerScrollSettled();
                } else {
                    MerchantProfileFragment.this.onPagerScrollUnsettled();
                }
            }
        };
        this.mMerchantRatingsImagePrefetcher = new ImageHttpPrefetcher();
        this.mCategoriesImagePrefetcher = new ImageHttpPrefetcher();
        this.mBannerHeight = ((MerchantProfileActivity) getBaseActivity()).getResources().getDimensionPixelOffset(R.dimen.merchant_profile_banner_statistics_section_height) + ((MerchantProfileActivity) getBaseActivity()).getResources().getDimensionPixelOffset(R.dimen.merchant_profile_banner_store_section_height);
        this.mTabBarHeight = this.mBannerHeight + ((MerchantProfileActivity) getBaseActivity()).getResources().getDimensionPixelOffset(R.dimen.tab_bar_height);
        this.mRestoredIndex = -1;
        this.isInitialProductsFetch = true;
        this.mLastSeenProductIds = new HashSet<>();
        if (bundle != null) {
            this.mRestoredIndex = bundle.getInt("SavedStateTabIndex");
        } else {
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_MOBILE_NATIVE_MERCHANT_PAGE);
        }
    }

    public void handleRatingsLoaded(ArrayList<WishRating> arrayList, boolean z, int i) {
        if (this.mPagerAdapter != null) {
            this.mPagerAdapter.handleRatingsLoaded(arrayList, z, i);
        }
    }

    public void handleRatingsFailed() {
        if (this.mPagerAdapter != null) {
            this.mPagerAdapter.handleRatingsFailed();
        }
    }

    public void handleMerchantTopCategoriesLoaded(ArrayList<WishMerchantTopCategory> arrayList, String str) {
        if (this.mPagerAdapter != null) {
            this.mPagerAdapter.handleMerchantTopCategoriesLoaded(arrayList, str);
        }
    }

    public void handleMerchantTopCategoriesFailed() {
        if (this.mPagerAdapter != null) {
            this.mPagerAdapter.handleTopMerchantCategoriesFailed();
        }
    }

    public void handleSaveInstanceState(Bundle bundle) {
        super.handleSaveInstanceState(bundle);
        bundle.putInt("SavedStateTabIndex", getCurrentIndex());
        bundle.putString("SavedStateFeedExtraDataBundle", StateStoreCache.getInstance().storeParcelable(this.mFeedExtraDataBundle));
        this.mPagerAdapter.handleSaveInstanceState(bundle);
    }

    public String getPagedDataSavedInstanceStateKey(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append("SavedStatePagedData_");
        sb.append(i);
        return sb.toString();
    }

    public Bundle getSavedInstanceState(int i) {
        if (getSavedInstanceState() != null) {
            return getSavedInstanceState().getBundle(getPagedDataSavedInstanceStateKey(i));
        }
        return null;
    }

    public void onStop() {
        super.onStop();
    }

    public void handleResume() {
        super.handleResume();
        if (!getLoadingPageView().isLoadingComplete()) {
            getLoadingPageView().reload();
        }
    }

    public void onPause() {
        super.onPause();
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.mPagerAdapter != null) {
            this.mPagerAdapter.cleanup();
        }
        if (this.mMerchantRatingsImagePrefetcher != null) {
            this.mMerchantRatingsImagePrefetcher.cancelPrefetching();
        }
        if (this.mCategoriesImagePrefetcher != null) {
            this.mCategoriesImagePrefetcher.cancelPrefetching();
        }
    }

    public void releaseImages() {
        if (this.mPagerAdapter != null) {
            this.mPagerAdapter.releaseImages();
        }
        if (this.mBannerView != null) {
            this.mBannerView.releaseImages();
        }
        if (this.mMerchantRatingsImagePrefetcher != null) {
            this.mMerchantRatingsImagePrefetcher.pausePrefetching();
        }
        if (this.mCategoriesImagePrefetcher != null) {
            this.mCategoriesImagePrefetcher.pausePrefetching();
        }
    }

    public void restoreImages() {
        if (this.mPagerAdapter != null) {
            this.mPagerAdapter.restoreImages();
        }
        if (this.mBannerView != null) {
            this.mBannerView.restoreImages();
        }
        if (this.mMerchantRatingsImagePrefetcher != null) {
            this.mMerchantRatingsImagePrefetcher.resumePrefetching();
        }
        if (this.mCategoriesImagePrefetcher != null) {
            this.mCategoriesImagePrefetcher.resumePrefetching();
        }
    }

    public void switchToPosition(int i, boolean z) {
        if (i >= 0 && i < this.mPagerAdapter.getCount()) {
            this.mMerchantViewPager.setCurrentItem(i, z);
        }
    }

    public void initializeTabs(final boolean z) {
        withActivity(new ActivityTask<MerchantProfileActivity>() {
            public void performTask(MerchantProfileActivity merchantProfileActivity) {
                MerchantProfileFragment.this.mPagerAdapter.updatePages(z);
                MerchantProfileFragment.this.customizeTabStrip();
                MerchantProfileFragment.this.mTabStrip.setViewPager(MerchantProfileFragment.this.mMerchantViewPager);
                MerchantProfileFragment.this.mTabStrip.setOnPageChangeListener(MerchantProfileFragment.this.mPageScrollListener);
                MerchantProfileFragment.this.refreshTabStripFontColors();
                MerchantProfileFragment.this.goToRestoredTab();
                MerchantProfileFragment.this.getLoadingPageView().markLoadingComplete();
            }
        });
    }

    private void initializeNavigationBar() {
        setupBaseActionBar();
        withActivity(new ActivityTask<MerchantProfileActivity>() {
            public void performTask(MerchantProfileActivity merchantProfileActivity) {
                merchantProfileActivity.getActionBarManager().addActionBarItem(ActionBarItem.createSearchActionBarItem(merchantProfileActivity.getActionBarManager()));
            }
        });
    }

    /* access modifiers changed from: private */
    public void customizeTabStrip() {
        withActivity(new ActivityTask<MerchantProfileActivity>() {
            public void performTask(MerchantProfileActivity merchantProfileActivity) {
                int dimensionPixelOffset = WishApplication.getInstance().getResources().getDimensionPixelOffset(R.dimen.tab_strip_indicator_height);
                int dimensionPixelOffset2 = WishApplication.getInstance().getResources().getDimensionPixelOffset(R.dimen.tab_strip_text_size);
                MerchantProfileFragment.this.mTabStrip.setBackgroundResource(R.color.white);
                MerchantProfileFragment.this.mTabStrip.setIndicatorColorResource(R.color.main_primary);
                MerchantProfileFragment.this.mTabStrip.setDividerColorResource(R.color.white);
                MerchantProfileFragment.this.mTabStrip.setTextColorResource(R.color.main_primary);
                MerchantProfileFragment.this.mTabStrip.setUnderlineHeight(merchantProfileActivity.getResources().getDimensionPixelOffset(R.dimen.divider));
                MerchantProfileFragment.this.mTabStrip.setUnderlineColorResource(R.color.cool_gray5);
                MerchantProfileFragment.this.mTabStripShadow.setVisibility(8);
                MerchantProfileFragment.this.mTabStrip.setIndicatorHeight(dimensionPixelOffset);
                MerchantProfileFragment.this.mTabStrip.setTextSize(dimensionPixelOffset2);
            }
        });
    }

    /* access modifiers changed from: private */
    public void refreshTabStripFontColors() {
        LinearLayout linearLayout = (LinearLayout) this.mTabStrip.getChildAt(0);
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            TextView textView = (TextView) linearLayout.getChildAt(i);
            if (i == this.mMerchantViewPager.getCurrentItem()) {
                textView.setTextColor(WishApplication.getInstance().getResources().getColor(R.color.main_primary));
                textView.setTypeface(FontUtil.getTypefaceForStyle(1), 1);
            } else {
                textView.setTextColor(WishApplication.getInstance().getResources().getColor(R.color.cool_gray3));
                textView.setTypeface(FontUtil.getTypefaceForStyle(0), 0);
            }
        }
    }

    public void handleLoadingSuccess(int i, ArrayList<WishProduct> arrayList, int i2, boolean z) {
        if (!this.isInitialProductsFetch || this.mInitialProducts != null) {
            clearInitialProductInfo(i);
        } else {
            this.mInitialProducts = new BaseInitialProductWrapper();
            this.mInitialProducts.initialProducts = arrayList;
            this.mInitialProducts.noMoreItems = z;
            this.mInitialProducts.nextOffset = i2;
            this.isInitialProductsFetch = false;
        }
        if (z) {
            getLoadingPageView().markNoMoreItems();
        }
        if (this.mPagerAdapter != null) {
            this.mPagerAdapter.handleLatestLoadingSuccess(arrayList, i2, z);
        }
    }

    public void loadProducts(int i, String str, final int i2) {
        if (i2 == 0) {
            this.mLastSeenProductIds.clear();
        }
        withServiceFragment(new ServiceTask<BaseActivity, BaseProductFeedServiceFragment>() {
            public void performTask(BaseActivity baseActivity, BaseProductFeedServiceFragment baseProductFeedServiceFragment) {
                ArrayList<String> arrayList = new ArrayList<>();
                if (!(MerchantProfileFragment.this.mPagerAdapter == null || MerchantProfileFragment.this.mPagerAdapter.getLatestProductsView() == null)) {
                    Iterator it = MerchantProfileFragment.this.mPagerAdapter.getLatestProductsView().getProducts().iterator();
                    while (it.hasNext()) {
                        Object next = it.next();
                        if (next instanceof WishProduct) {
                            arrayList.add(((WishProduct) next).getProductId());
                        }
                    }
                }
                FeedContext feedContext = new FeedContext();
                feedContext.lastProductIds = arrayList;
                baseProductFeedServiceFragment.loadMerchantFeed(MerchantProfileFragment.this.getBrandFilter(), i2, feedContext);
            }
        });
    }

    public int getBannerOffset() {
        return this.mBannerHeight * -1;
    }

    public int getTabAreaOffset() {
        return ((LayoutParams) this.mTabStripContainer.getLayoutParams()).topMargin;
    }

    public void setTabAreaOffset(int i) {
        this.mTabStripContainer.clearAnimation();
        LayoutParams layoutParams = (LayoutParams) this.mTabStripContainer.getLayoutParams();
        layoutParams.topMargin = Math.min(Math.max(i, getBannerOffset()), 0);
        this.mTabStripContainer.setLayoutParams(layoutParams);
    }

    public void hideTabArea(boolean z) {
        scrollOffset();
    }

    public void scrollOffset() {
        int i = this.mCurrentOffset;
        this.mCurrentOffset = getTabAreaOffset();
        if (this.mCurrentOffset - i == 0) {
            return;
        }
        if (this.mCurrentOffset >= 0) {
            this.mPagerAdapter.scrollOffset(Math.max(0 - i, 0), getCurrentIndex());
            this.mCurrentOffset = 0;
        } else if (this.mCurrentOffset <= getBannerOffset()) {
            this.mPagerAdapter.scrollOffset(Math.min(getBannerOffset() - i, 0), getCurrentIndex());
            this.mCurrentOffset = getBannerOffset();
        } else {
            this.mPagerAdapter.scrollOffset(this.mCurrentOffset - i, getCurrentIndex());
        }
    }

    public void showTabArea(boolean z) {
        scrollOffset();
    }

    public int getTabAreaSize() {
        return this.mTabBarHeight;
    }

    public BaseInitialProductWrapper getInitialProductInfo(int i) {
        return this.mInitialProducts;
    }

    /* access modifiers changed from: protected */
    public void handlePageSelected(int i) {
        refreshTabStripFontColors();
        this.mCurrentOffset = getTabAreaOffset();
        this.mPagerAdapter.scrollOffset(-1, -1);
    }

    public int getCurrentIndex() {
        return this.mMerchantViewPager.getCurrentItem();
    }

    /* access modifiers changed from: private */
    public void onPagerScrollUnsettled() {
        if (this.mPagerAdapter != null) {
            this.mPagerAdapter.onPagerScrollUnsettled();
        }
    }

    /* access modifiers changed from: private */
    public void onPagerScrollSettled() {
        if (this.mPagerAdapter != null) {
            this.mPagerAdapter.onPagerScrollSettled();
        }
    }

    /* access modifiers changed from: private */
    public void goToRestoredTab() {
        if (this.mPagerAdapter != null) {
            switchToPosition(this.mRestoredIndex, false);
        }
    }

    public void handleReload() {
        loadProducts(0, "", 0);
    }

    public void initializeLoadingContentView(View view) {
        this.mBannerView = (MerchantProfileBannerView) view.findViewById(R.id.merchant_profile_fragment_banner);
        this.mTabStripContainer = view.findViewById(R.id.merchant_profile_fragment_viewpager_tab_container);
        this.mTabStripShadow = view.findViewById(R.id.merchant_profile_fragment_viewpager_shadow);
        this.mTabStrip = (PagerSlidingTabStrip) view.findViewById(R.id.merchant_profile_fragment_viewpager_tabs);
        this.mTabStrip.setAllCaps(false);
        this.mTabStrip.setTabPaddingLeftRight((int) getResources().getDimension(R.dimen.sixteen_padding));
        this.mMerchantViewPager = (SafeViewPager) view.findViewById(R.id.merchant_profile_fragment_viewpager);
        this.mMerchantViewPager.setOffscreenPageLimit(2);
        this.mPagerAdapter = new MerchantProfilePagerAdapter((DrawerActivity) getActivity(), this, this.mMerchantViewPager);
        this.mMerchantViewPager.setAdapter(this.mPagerAdapter);
        this.mPagerAdapter.setMerchantRatingsImagePrefetcher(this.mMerchantRatingsImagePrefetcher);
        this.mPagerAdapter.setCategoriesImagePrefetcher(this.mCategoriesImagePrefetcher);
        this.mBrandFilter = new WishBrandFilter(((MerchantProfileActivity) getBaseActivity()).getMerchant());
        initializeNavigationBar();
        initializeValues();
    }

    /* access modifiers changed from: protected */
    public void initializeValues() {
        if (getSavedInstanceState() != null) {
            this.mFeedExtraDataBundle = (FeedExtraDataBundle) StateStoreCache.getInstance().getParcelable(getSavedInstanceState(), "SavedStateFeedExtraDataBundle", FeedExtraDataBundle.class);
            if (this.mFeedExtraDataBundle != null) {
                updateFeedExtraDataBundle(this.mFeedExtraDataBundle);
            }
        }
    }

    public void updateFeedExtraDataBundle(FeedExtraDataBundle feedExtraDataBundle) {
        this.mFeedExtraDataBundle = feedExtraDataBundle;
        if (!this.mBannerTabsSet && feedExtraDataBundle.merchant != null) {
            this.mBannerView.setMerchantHeader(this, feedExtraDataBundle.merchant);
            initializeTabs(feedExtraDataBundle.merchant.getHasCategories());
            this.mBannerTabsSet = true;
        }
    }

    public void handleLoadingErrored(int i) {
        getLoadingPageView().markLoadingErrored();
    }

    public boolean hasItems() {
        return this.mPagerAdapter.getCount() > 0;
    }

    public void clearInitialProductInfo(int i) {
        this.mInitialProducts = null;
    }

    public WishBrandFilter getBrandFilter() {
        return this.mBrandFilter;
    }

    public WishMerchant getWishMerchant() {
        if (this.mFeedExtraDataBundle != null) {
            return this.mFeedExtraDataBundle.merchant;
        }
        return null;
    }
}
