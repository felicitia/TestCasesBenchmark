package com.contextlogic.wish.activity.feed;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.SpannableString;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.BaseFragment.UiTask;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.activity.actionbar.ActionBarItem;
import com.contextlogic.wish.activity.actionbar.ActionBarManager;
import com.contextlogic.wish.activity.actionbar.ActionBarManager.Theme;
import com.contextlogic.wish.activity.browse.BrowseActivity;
import com.contextlogic.wish.activity.camera.camerapreview.CameraActivity;
import com.contextlogic.wish.activity.camera.dialog.CameraDialogFragment;
import com.contextlogic.wish.activity.cart.CartActivity;
import com.contextlogic.wish.activity.exampleugc.exampleugcintro.ExampleUgcIntroActivity;
import com.contextlogic.wish.activity.feed.dealdash.DealDashHelpDialogFragment;
import com.contextlogic.wish.activity.feed.filter.FilterFragment;
import com.contextlogic.wish.activity.feed.freegifts.FreeGiftsTabHeaderView;
import com.contextlogic.wish.activity.feed.home.HomePageView;
import com.contextlogic.wish.activity.feed.merchant.MerchantFeedBannerView;
import com.contextlogic.wish.activity.feed.outlet.BrandedFeedActivity;
import com.contextlogic.wish.activity.feed.outlet.BrandedFeedHeaderView;
import com.contextlogic.wish.activity.feed.wishexpress.WishExpressBannerView;
import com.contextlogic.wish.activity.merchantprofile.merchanttopcategory.MerchantTopCategoryActivity;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.datacenter.StatusDataCenter;
import com.contextlogic.wish.api.model.WishBrandFilter;
import com.contextlogic.wish.api.model.WishCurrentDailyGiveawayInfo;
import com.contextlogic.wish.api.model.WishDealDashInfo;
import com.contextlogic.wish.api.model.WishFilter;
import com.contextlogic.wish.api.model.WishFreeGiftTabInfo;
import com.contextlogic.wish.api.model.WishInfoDailyGiveawayInfo;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.api.model.WishUpcomingDailyGiveawayInfo;
import com.contextlogic.wish.api.service.standalone.BaseFeedApiService.FeedExtraDataBundle;
import com.contextlogic.wish.api.service.standalone.GetFeedService;
import com.contextlogic.wish.api.service.standalone.GetFilteredFeedService.FeedContext;
import com.contextlogic.wish.api.service.standalone.GetFilteredFeedService.FeedExtraInfo;
import com.contextlogic.wish.api.service.standalone.GetFilteredFeedService.RequestSourceType;
import com.contextlogic.wish.api.service.standalone.GetMerchantFeedService;
import com.contextlogic.wish.api.service.standalone.ProductSearchService;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.cache.StateStoreCache;
import com.contextlogic.wish.dialog.BaseDialogFragment;
import com.contextlogic.wish.dialog.BaseDialogFragment.BaseDialogCallback;
import com.contextlogic.wish.dialog.addtocart.Source;
import com.contextlogic.wish.dialog.bottomsheet.SuccessBottomSheetDialog;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogChoice;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogChoice.BackgroundType;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogChoice.ChoiceType;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment.ImageSize;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment.MultiButtonDialogFragmentBuilder;
import com.contextlogic.wish.ui.text.BoldBorderSpan;
import com.contextlogic.wish.ui.text.ThemedTextView;
import com.contextlogic.wish.ui.timer.BaseCountdownTimerView.CountCallback;
import com.contextlogic.wish.ui.timer.BaseCountdownTimerView.DoneCallback;
import com.contextlogic.wish.ui.timer.CountdownTimerView;
import com.contextlogic.wish.ui.timer.TimerTextView;
import com.contextlogic.wish.ui.timer.TimerTextView.TimerWatcherAdapter;
import com.contextlogic.wish.ui.view.SlidingTabStrip.OnTabClickListener;
import com.contextlogic.wish.ui.view.SlidingTabStrip.TabType;
import com.contextlogic.wish.ui.viewpager.PagerSlidingTabStrip;
import com.contextlogic.wish.util.ClipboardUtil;
import com.contextlogic.wish.util.DateUtil;
import com.contextlogic.wish.util.PreferenceUtil;
import com.contextlogic.wish.util.ValueUtil;
import com.crashlytics.android.Crashlytics;
import com.otaliastudios.cameraview.CameraUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

public abstract class ProductFeedFragment<A extends DrawerActivity> extends BaseProductFeedFragment<A> {
    /* access modifiers changed from: protected */
    public BaseProductFeedPagerAdapter mAdapter;
    /* access modifiers changed from: private */
    public int mBrandedPosition;
    private Runnable mCameraTooltipDialogRunnable;
    /* access modifiers changed from: private */
    public View mCountdownContainer;
    private Date mCountdownTargetDate;
    private ThemedTextView mCountdownText;
    private CountdownTimerView mCountdownView;
    protected int mCurrentIndex;
    protected int mDailyGiveawayPosition;
    protected int mDealDashPosition;
    private FeedExtraDataBundle mFeedExtraDataBundle;
    private FeedExtraInfo mFeedExtraInfo;
    /* access modifiers changed from: private */
    public WishFilter mFilter;
    private View mFreeGiftsBanner;
    private TextView mFreeGiftsBannerDaysLeft;
    private int mFreeGiftsTabPosition;
    private HomePageView mHomePageView;
    private String mHomePageViewTitle;
    private SparseArray<BaseInitialProductWrapper> mInitialProducts;
    private FloatingActionButton mInviteCouponFloatingButton;
    /* access modifiers changed from: private */
    public RequestSourceType mLastestRequestSourceType;
    /* access modifiers changed from: private */
    public int mLatestPosition;
    /* access modifiers changed from: private */
    public ArrayList<WishFilter> mMainCategories;
    private int mMerchantFeedTabPosition;
    private TimerTextView mNewCountdownView;
    private int mPromotionPosition;
    private int mRecentWishlistTabPosition;
    private int mRecentlyViewedTabPosition;
    /* access modifiers changed from: private */
    public ArrayList<String> mScreenshotFilters;
    /* access modifiers changed from: private */
    public String mScreenshotXParam;
    protected LinearLayout mTabAreaContainer;
    /* access modifiers changed from: private */
    public PagerSlidingTabStrip mTabStrip;
    private View mTabStripShadow;
    private long mTimestamp;
    /* access modifiers changed from: private */
    public ViewPager mViewPager;
    private int mWishExpressPosition;

    public enum DataMode {
        FilteredFeed,
        Search,
        Tag,
        Brand,
        Merchant,
        Wishlist,
        Branded,
        MerchantTopCategory,
        WishExpress,
        RelatedExpressProducts,
        RelatedProductBoostProducts,
        VisuallySimilar,
        RecentWishlist
    }

    public boolean canPullToRefresh() {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean canSeeFloatingInviteButton() {
        return false;
    }

    public abstract boolean canShowFeedCategories();

    /* access modifiers changed from: protected */
    public WishBrandFilter getBrandFilter() {
        return null;
    }

    public abstract DataMode getDataMode();

    public int getLoadingContentLayoutResourceId() {
        return R.layout.base_product_feed_fragment;
    }

    /* access modifiers changed from: protected */
    public String getMainRequestId() {
        return null;
    }

    public abstract boolean isFeedFilterable();

    /* access modifiers changed from: protected */
    public boolean isPlaceholderMode() {
        return false;
    }

    public void initializeLoadingContentView(View view) {
        this.mDealDashPosition = -1;
        this.mBrandedPosition = -1;
        this.mFreeGiftsTabPosition = -1;
        this.mWishExpressPosition = -1;
        this.mRecentlyViewedTabPosition = -1;
        this.mMerchantFeedTabPosition = -1;
        this.mRecentWishlistTabPosition = -1;
        this.mLastestRequestSourceType = null;
        if (WishApplication.getInstance().isFirstFeedOpen()) {
            this.mLastestRequestSourceType = RequestSourceType.FIRST_LOAD;
        }
        WishApplication.getInstance().setIsFirstFeedOpen(false);
        this.mViewPager = (ViewPager) view.findViewById(R.id.base_product_feed_fragment_view_pager);
        this.mAdapter = new BaseProductFeedPagerAdapter((DrawerActivity) getBaseActivity(), this);
        this.mViewPager.setAdapter(this.mAdapter);
        this.mTabAreaContainer = (LinearLayout) view.findViewById(R.id.base_product_feed_fragment_tab_area);
        this.mTabStrip = (PagerSlidingTabStrip) view.findViewById(R.id.base_product_feed_fragment_tab_strip);
        if (!canShowFeedCategories() || isPlaceholderMode()) {
            this.mTabStrip.setVisibility(8);
        }
        this.mTabStripShadow = view.findViewById(R.id.base_product_feed_fragment_tab_strip_shadow);
        this.mCountdownContainer = view.findViewById(R.id.base_product_feed_fragment_countdown_container);
        this.mCountdownText = (ThemedTextView) view.findViewById(R.id.base_product_feed_fragment_countdown_text);
        this.mNewCountdownView = (TimerTextView) view.findViewById(R.id.base_product_feed_new_fragment_countdown_view);
        this.mCountdownView = (CountdownTimerView) view.findViewById(R.id.base_product_feed_fragment_countdown_view);
        this.mCountdownView.pauseTimer();
        this.mCountdownView.setCountCallback(new CountCallback() {
            public void onCount(int i) {
                if (i < 180) {
                    ProductFeedFragment.this.setRushCountDownView();
                }
            }
        });
        if (ExperimentDataCenter.getInstance().shouldSeeDealDashCoupon()) {
            this.mCountdownView.setVisibility(8);
            this.mNewCountdownView.setVisibility(0);
        }
        this.mCountdownContainer.setVisibility(8);
        this.mFreeGiftsBanner = view.findViewById(R.id.base_product_feed_fragment_free_gifts_banner_container);
        this.mFreeGiftsBanner.setVisibility(8);
        this.mFreeGiftsBanner.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                WishFreeGiftTabInfo.logFreeGiftTabEvent(WishAnalyticsEvent.CLICK_MOBILE_FREE_GIFT_TAB_BANNER_CLAIM);
                ProductFeedFragment.this.handleClaimFreeGifts();
            }
        });
        this.mFreeGiftsBannerDaysLeft = (TextView) view.findViewById(R.id.base_product_feed_fragment_free_gifts_banner_days_left);
        this.mViewPager.addOnPageChangeListener(new OnPageChangeListener() {
            public void onPageScrolled(int i, float f, int i2) {
                if (((double) f) >= 0.5d) {
                    i++;
                }
                if (i != ProductFeedFragment.this.mCurrentIndex) {
                    ProductFeedFragment.this.handleTabSelected(i, false);
                }
            }

            public void onPageSelected(int i) {
                if (i != ProductFeedFragment.this.mCurrentIndex) {
                    ProductFeedFragment.this.handleTabSelected(i);
                }
            }

            public void onPageScrollStateChanged(int i) {
                if (i == 0) {
                    ProductFeedFragment.this.handleTabSelected(ProductFeedFragment.this.mCurrentIndex);
                    ProductFeedFragment.this.updateTitleView();
                    if (ExperimentDataCenter.getInstance().shouldShowBottomNavigation()) {
                        ProductFeedFragment.this.withActivity(new ActivityTask<A>() {
                            public void performTask(A a) {
                                if (ProductFeedFragment.this.mCurrentIndex == ProductFeedFragment.this.mDealDashPosition && a.isBottomNavigationVisible()) {
                                    ProductFeedFragment.this.popOutBottomNavigation(true);
                                } else if (ProductFeedFragment.this.mCurrentIndex != ProductFeedFragment.this.mDealDashPosition && !a.isBottomNavigationVisible()) {
                                    ProductFeedFragment.this.popInBottomNavigation(true);
                                }
                            }
                        });
                    }
                } else if (i == 1) {
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_SWIPE_MAIN_PAGE);
                }
            }
        });
        this.mInviteCouponFloatingButton = (FloatingActionButton) view.findViewById(R.id.base_product_feed_fragment_invite_coupon_button);
        initializeValues();
        if (canSeeFloatingInviteButton()) {
            showInviteButton();
        }
        trackScrollTabStripEvent();
    }

    /* access modifiers changed from: protected */
    public void showInviteButton() {
        this.mInviteCouponFloatingButton.setVisibility(0);
        this.mInviteCouponFloatingButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ProductFeedFragment.this.withServiceFragment(new ServiceTask<BaseActivity, BaseProductFeedServiceFragment>() {
                    public void performTask(BaseActivity baseActivity, BaseProductFeedServiceFragment baseProductFeedServiceFragment) {
                        baseProductFeedServiceFragment.showInviteCouponDialog();
                    }
                });
            }
        });
    }

    public void hideInviteButton() {
        this.mInviteCouponFloatingButton.setVisibility(8);
    }

    /* access modifiers changed from: protected */
    public void initializeValues() {
        this.mCurrentIndex = 0;
        this.mDailyGiveawayPosition = -1;
        this.mTimestamp = 0;
        this.mMainCategories = new ArrayList<>();
        this.mInitialProducts = new SparseArray<>();
        this.mScreenshotXParam = null;
        if (getBaseActivity() != null && (getBaseActivity() instanceof BrowseActivity)) {
            this.mScreenshotXParam = ((BrowseActivity) getBaseActivity()).getScreenshotXparam();
            this.mScreenshotFilters = ((BrowseActivity) getBaseActivity()).getScreenshotFilters();
        }
        if (getSavedInstanceState() != null) {
            this.mCurrentIndex = getSavedInstanceState().getInt("SavedStateCurrentIndex");
            this.mFilter = (WishFilter) StateStoreCache.getInstance().getParcelable(getSavedInstanceState(), "SavedStateSearchFilter", WishFilter.class);
            this.mFeedExtraInfo = (FeedExtraInfo) StateStoreCache.getInstance().getParcelable(getSavedInstanceState(), "SavedStateFeedExtraInfo", FeedExtraInfo.class);
            this.mFeedExtraDataBundle = (FeedExtraDataBundle) StateStoreCache.getInstance().getParcelable(getSavedInstanceState(), "SavedStateFeedExtraDataBundle", FeedExtraDataBundle.class);
            this.mDealDashPosition = getSavedInstanceState().getInt("SavedStateDealDashPosition");
            this.mLatestPosition = getSavedInstanceState().getInt("SavedStateLatestPosition");
            this.mBrandedPosition = getSavedInstanceState().getInt("SavedStateBrandedPosition");
            this.mFreeGiftsTabPosition = getSavedInstanceState().getInt("SavedStateFreeGiftsTabPosition");
            this.mPromotionPosition = getSavedInstanceState().getInt("SavedStatePromotionPosition");
            this.mRecentlyViewedTabPosition = getSavedInstanceState().getInt("SavedStateRecentlyViewedTabPosition");
            this.mMerchantFeedTabPosition = getSavedInstanceState().getInt("SavedStateMerchantFeedTabPosition");
            this.mWishExpressPosition = getSavedInstanceState().getInt("SavedStateWishExpressPosition");
            this.mTimestamp = getSavedInstanceState().getLong("SavedStateTimeStamp");
            this.mLastestRequestSourceType = (RequestSourceType) getSavedInstanceState().getParcelable("IsFirstFeedOpen");
            this.mRecentWishlistTabPosition = getSavedInstanceState().getInt("SavedStateRecentlWishlistTabPosition");
            if (!(this.mFeedExtraInfo == null || this.mFeedExtraInfo.mainCategories == null || this.mFeedExtraInfo.mainCategories.size() <= 0)) {
                updateMainCategories(this.mFeedExtraInfo);
            }
            if (this.mFeedExtraDataBundle != null) {
                updateFeedExtraDataBundle(this.mFeedExtraDataBundle);
            }
            setSelectedTab(this.mCurrentIndex);
        }
        if (getDataMode() == DataMode.Merchant) {
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_MOBILE_NATIVE_MERCHANT_PAGE);
        }
    }

    public int getCurrentIndex() {
        return this.mCurrentIndex;
    }

    public void handleResume() {
        super.handleResume();
        long j = PreferenceUtil.getLong("LastTransactionTime", 0);
        if (!getLoadingPageView().isLoadingComplete() || j > this.mTimestamp) {
            getLoadingPageView().reload();
        } else if (this.mAdapter != null) {
            this.mAdapter.handleResume();
        }
    }

    public void restoreImages() {
        if (this.mAdapter != null) {
            this.mAdapter.restoreImages();
        }
        if (this.mCountdownView != null && this.mCountdownTargetDate != null && !ExperimentDataCenter.getInstance().shouldSeeDealDashCoupon()) {
            this.mCountdownView.startTimer();
        }
    }

    public void releaseImages() {
        if (this.mAdapter != null && !ExperimentDataCenter.getInstance().shouldShowFeedToProductDetailTransition()) {
            this.mAdapter.releaseImages();
        }
        if (this.mCountdownView != null && this.mCountdownTargetDate != null && !ExperimentDataCenter.getInstance().shouldSeeDealDashCoupon()) {
            this.mCountdownView.pauseTimer();
        }
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.mAdapter != null) {
            this.mAdapter.onDestroy();
        }
    }

    public boolean onBackPressed() {
        if (getBaseActivity() == null || !((DrawerActivity) getBaseActivity()).isTaskRoot() || getDataMode() != DataMode.FilteredFeed || this.mAdapter == null) {
            return false;
        }
        return this.mAdapter.onBackPressed();
    }

    public void handleSaveInstanceState(Bundle bundle) {
        super.handleSaveInstanceState(bundle);
        if (getLoadingPageView() != null && getLoadingPageView().isLoadingComplete() && this.mAdapter != null) {
            bundle.putString("SavedStateSearchFilter", StateStoreCache.getInstance().storeParcelable(this.mFilter));
            bundle.putInt("SavedStateCurrentIndex", this.mCurrentIndex);
            bundle.putString("SavedStateFeedExtraInfo", StateStoreCache.getInstance().storeParcelable(this.mFeedExtraInfo));
            bundle.putString("SavedStateFeedExtraDataBundle", StateStoreCache.getInstance().storeParcelable(this.mFeedExtraDataBundle));
            bundle.putInt("SavedStateDealDashPosition", this.mDealDashPosition);
            bundle.putInt("SavedStateLatestPosition", this.mLatestPosition);
            bundle.putInt("SavedStateBrandedPosition", this.mBrandedPosition);
            bundle.putInt("SavedStateFreeGiftsTabPosition", this.mFreeGiftsTabPosition);
            bundle.putInt("SavedStatePromotionPosition", this.mPromotionPosition);
            bundle.putInt("SavedStateRecentlyViewedTabPosition", this.mRecentlyViewedTabPosition);
            bundle.putInt("SavedStateMerchantFeedTabPosition", this.mMerchantFeedTabPosition);
            bundle.putInt("SavedStateWishExpressPosition", this.mWishExpressPosition);
            bundle.putLong("SavedStateTimeStamp", this.mTimestamp);
            bundle.putParcelable("IsFirstFeedOpen", this.mLastestRequestSourceType);
            bundle.putInt("SavedStateRecentlWishlistTabPosition", this.mRecentWishlistTabPosition);
            this.mAdapter.handleSaveInstanceState(bundle);
        }
    }

    public String getPagedDataSavedInstanceStateKey(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append("SavedStateData_");
        sb.append(i);
        return sb.toString();
    }

    public Bundle getSavedInstanceState(int i) {
        if (getSavedInstanceState() != null) {
            return getSavedInstanceState().getBundle(getPagedDataSavedInstanceStateKey(i));
        }
        return null;
    }

    public void clearSavedInstanceState(int i) {
        if (getSavedInstanceState() != null) {
            getSavedInstanceState().remove(getPagedDataSavedInstanceStateKey(i));
        }
    }

    public void loadProducts(final int i, final String str, final int i2) {
        if (isPlaceholderMode()) {
            getLoadingPageView().markLoadingComplete();
        } else {
            withServiceFragment(new ServiceTask<BaseActivity, BaseProductFeedServiceFragment>() {
                public void performTask(BaseActivity baseActivity, BaseProductFeedServiceFragment baseProductFeedServiceFragment) {
                    if (!(ProductFeedFragment.this.getDataMode() == DataMode.FilteredFeed && ProductFeedFragment.this.mScreenshotXParam == null)) {
                        ProductFeedFragment.this.mLastestRequestSourceType = null;
                    }
                    if (ProductFeedFragment.this.getDataMode() == DataMode.FilteredFeed && ProductFeedFragment.this.mScreenshotXParam == null) {
                        final FeedContext feedContext = new FeedContext();
                        feedContext.requestId = str;
                        ProductFeedFragment.this.withUiFragment(new UiTask<BaseActivity, FilterFragment>() {
                            public void performTask(BaseActivity baseActivity, FilterFragment filterFragment) {
                                feedContext.filters = filterFragment.getSelectedFilters(i);
                            }
                        }, "FragmentTagRightDrawer");
                        if (ProductFeedFragment.this.mAdapter.getCurrentFeedView() != null && ProductFeedFragment.this.mAdapter.getCurrentFeedView().isTimeRefreshed(true) && str != null && str.startsWith("tabbed_feed_latest")) {
                            ProductFeedFragment.this.mLastestRequestSourceType = RequestSourceType.TIMED_REFRESH;
                            feedContext.requestSourceType = ProductFeedFragment.this.mLastestRequestSourceType;
                        } else if (str == null || !str.startsWith("tabbed_feed_latest") || ProductFeedFragment.this.mLastestRequestSourceType == null) {
                            ProductFeedFragment.this.mLastestRequestSourceType = null;
                        } else {
                            if (i2 == 0) {
                                ProductFeedFragment.this.mLastestRequestSourceType = RequestSourceType.USER_FORCE_REFRESH;
                            }
                            feedContext.requestSourceType = ProductFeedFragment.this.mLastestRequestSourceType;
                        }
                        baseProductFeedServiceFragment.loadFilterFeedProducts(i, i2, feedContext);
                    } else if (ProductFeedFragment.this.getDataMode() == DataMode.Tag) {
                        GetFeedService.FeedContext feedContext2 = new GetFeedService.FeedContext();
                        feedContext2.filter = ProductFeedFragment.this.getMainRequestId();
                        feedContext2.sort = "recommended";
                        baseProductFeedServiceFragment.loadFeedProducts(i2, feedContext2);
                    } else if (ProductFeedFragment.this.getDataMode() == DataMode.Search) {
                        final ProductSearchService.FeedContext feedContext3 = new ProductSearchService.FeedContext();
                        feedContext3.requestSearchFilter = ProductFeedFragment.this.mFilter == null;
                        if (ProductFeedFragment.this.isFeedFilterable()) {
                            ProductFeedFragment.this.withUiFragment(new UiTask<BaseActivity, FilterFragment>() {
                                public void performTask(BaseActivity baseActivity, FilterFragment filterFragment) {
                                    feedContext3.filters = filterFragment.getSelectedFilters(i);
                                }
                            }, "FragmentTagRightDrawer");
                        }
                        if (ProductFeedFragment.this.canShowFeedCategories() && ProductFeedFragment.this.findPositionFromFilterId("wish_express__tab") == i) {
                            feedContext3.onlyWishExpress = true;
                        }
                        baseProductFeedServiceFragment.loadSearchResult(i, ProductFeedFragment.this.getMainRequestId(), i2, feedContext3);
                    } else if (ProductFeedFragment.this.getDataMode() == DataMode.Wishlist) {
                        baseProductFeedServiceFragment.loadWishlistProducts(ProductFeedFragment.this.getMainRequestId(), i2);
                    } else if (ProductFeedFragment.this.getDataMode() == DataMode.RecentWishlist) {
                        FeedContext feedContext4 = new FeedContext();
                        feedContext4.requestId = ProductFeedFragment.this.getMainRequestId();
                        baseProductFeedServiceFragment.loadFilterFeedProducts(i, i2, feedContext4);
                    } else if (ProductFeedFragment.this.getDataMode() == DataMode.Brand) {
                        baseProductFeedServiceFragment.loadBrandFeed(ProductFeedFragment.this.getBrandFilter(), i2);
                    } else if (ProductFeedFragment.this.getDataMode() == DataMode.Merchant) {
                        ArrayList<String> arrayList = new ArrayList<>();
                        if (ProductFeedFragment.this.mAdapter != null) {
                            Iterator it = ProductFeedFragment.this.mAdapter.getCurrentFeedView().getProducts().iterator();
                            while (it.hasNext()) {
                                Object next = it.next();
                                if (next instanceof WishProduct) {
                                    arrayList.add(((WishProduct) next).getProductId());
                                }
                            }
                        }
                        GetMerchantFeedService.FeedContext feedContext5 = new GetMerchantFeedService.FeedContext();
                        feedContext5.lastProductIds = arrayList;
                        baseProductFeedServiceFragment.loadMerchantFeed(ProductFeedFragment.this.getBrandFilter(), i2, feedContext5);
                    } else if (ProductFeedFragment.this.getDataMode() == DataMode.FilteredFeed && ProductFeedFragment.this.mScreenshotXParam != null) {
                        GetFeedService.FeedContext feedContext6 = new GetFeedService.FeedContext();
                        feedContext6.filter = ProductFeedFragment.this.mScreenshotXParam;
                        feedContext6.sort = "recommended";
                        baseProductFeedServiceFragment.loadFeedProducts(i2, feedContext6);
                    } else if (ProductFeedFragment.this.getDataMode() == DataMode.Branded) {
                        FeedContext feedContext7 = new FeedContext();
                        feedContext7.requestId = str;
                        feedContext7.requestBrandedFilter = true;
                        final ArrayList<WishFilter> arrayList2 = new ArrayList<>();
                        ProductFeedFragment.this.withUiFragment(new UiTask<BaseActivity, FilterFragment>() {
                            public void performTask(BaseActivity baseActivity, FilterFragment filterFragment) {
                                arrayList2.addAll(filterFragment.getSelectedFilters(i));
                            }
                        }, "FragmentTagRightDrawer");
                        arrayList2.add(new WishFilter(BrandedFeedActivity.BRANDED_FEED_FILTER));
                        feedContext7.filters = arrayList2;
                        baseProductFeedServiceFragment.loadFilterFeedProducts(i, i2, feedContext7);
                    } else if (ProductFeedFragment.this.getDataMode() == DataMode.MerchantTopCategory) {
                        FeedContext feedContext8 = new FeedContext();
                        feedContext8.requestId = ProductFeedFragment.this.getMainRequestId();
                        feedContext8.filters = new ArrayList<>();
                        Iterator it2 = ((MerchantTopCategoryActivity) ProductFeedFragment.this.getBaseActivity()).getTagIds().iterator();
                        while (it2.hasNext()) {
                            feedContext8.filters.add(new WishFilter((String) it2.next()));
                        }
                        baseProductFeedServiceFragment.loadFilterFeedProducts(i, i2, feedContext8);
                    } else if (ProductFeedFragment.this.getDataMode() == DataMode.RelatedExpressProducts) {
                        FeedContext feedContext9 = new FeedContext();
                        feedContext9.requestId = ProductFeedFragment.this.getMainRequestId();
                        baseProductFeedServiceFragment.loadRelatedExpressShippingProducts(i, i2, 30, feedContext9);
                    } else if (ProductFeedFragment.this.getDataMode() == DataMode.RelatedProductBoostProducts) {
                        baseProductFeedServiceFragment.loadRelatedProductBoostProducts(i, ProductFeedFragment.this.getMainRequestId(), i2, 30);
                    } else if (ProductFeedFragment.this.getDataMode() == DataMode.VisuallySimilar) {
                        FeedContext feedContext10 = new FeedContext();
                        feedContext10.requestId = ProductFeedFragment.this.getMainRequestId();
                        baseProductFeedServiceFragment.loadVisuallyRelatedProducts(i, i2, 30, feedContext10);
                    }
                }
            });
        }
    }

    public void handleLoadingErrored(int i) {
        getLoadingPageView().markLoadingErrored();
        if (this.mAdapter != null) {
            this.mAdapter.handleLoadingErrored(i);
        }
    }

    public void handleLoadingSuccess(int i, ArrayList<WishProduct> arrayList, int i2, boolean z) {
        handleLoadingSuccess(i, arrayList, i2, z, false, null, null);
    }

    public void handleLoadingSuccess(int i, ArrayList<WishProduct> arrayList, int i2, boolean z, String str) {
        handleLoadingSuccess(i, arrayList, i2, z, false, null, str);
    }

    public void handleLoadingSuccess(int i, ArrayList<WishProduct> arrayList, int i2, boolean z, boolean z2, FeedExtraInfo feedExtraInfo, String str) {
        if (!z2 || this.mScreenshotXParam != null) {
            clearInitialProductInfo(i);
        } else {
            BaseInitialProductWrapper baseInitialProductWrapper = new BaseInitialProductWrapper();
            baseInitialProductWrapper.initialProducts = arrayList;
            baseInitialProductWrapper.noMoreItems = z;
            baseInitialProductWrapper.nextOffset = i2;
            this.mInitialProducts.put(this.mLatestPosition, baseInitialProductWrapper);
        }
        if (this.mScreenshotFilters != null) {
            withUiFragment(new UiTask<A, FilterFragment>() {
                public void performTask(A a, FilterFragment filterFragment) {
                    filterFragment.setScreenshotSelectedFilters(ProductFeedFragment.this.mScreenshotFilters);
                    a.openRightDrawer();
                }
            }, "FragmentTagRightDrawer");
        }
        if (this.mCameraTooltipDialogRunnable != null) {
            getHandler().post(this.mCameraTooltipDialogRunnable);
            this.mCameraTooltipDialogRunnable = null;
        }
        if (z) {
            getLoadingPageView().markNoMoreItems();
        }
        if (this.mAdapter != null) {
            this.mAdapter.handleLoadingSuccess(i, arrayList, i2, z);
        }
        ArrayList arrayList2 = new ArrayList();
        if (!(this.mLatestPosition == -1 || feedExtraInfo == null || feedExtraInfo.promotionSpec == null)) {
            arrayList2.add(feedExtraInfo.promotionSpec.getFeedBannerView(this, WishAnalyticsEvent.IMPRESSION_PROMO_BANNER_FEED, WishAnalyticsEvent.CLICK_PROMO_BANNER_FEED));
        }
        if (str != null) {
            WishExpressBannerView wishExpressBannerView = new WishExpressBannerView(getContext());
            wishExpressBannerView.setup(str, false);
            arrayList2.add(wishExpressBannerView);
        }
        if (!(feedExtraInfo == null || feedExtraInfo.promotionSpec == null)) {
            this.mPromotionPosition = findPositionFromFilterId(feedExtraInfo.promotionSpec.getFilterId());
        }
        if (!(this.mPromotionPosition == -1 || feedExtraInfo == null || feedExtraInfo.promotionSpec == null)) {
            BaseFeedHeaderView feedBannerSmallHeaderView = feedExtraInfo.promotionSpec.getFeedBannerSmallHeaderView(this);
            if (feedBannerSmallHeaderView != null) {
                this.mAdapter.addCustomHeader(feedBannerSmallHeaderView, this.mPromotionPosition);
            }
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_PROMO_TAB);
        }
        if (!(this.mLatestPosition == -1 || feedExtraInfo == null || feedExtraInfo.homePageInfo == null)) {
            this.mHomePageView = new HomePageView(getContext(), this);
            arrayList2.add(this.mHomePageView);
            if (this.mHomePageViewTitle != null) {
                this.mHomePageView.setProductFeedTitle(this.mHomePageViewTitle);
            }
        }
        if (arrayList2.size() > 0) {
            this.mAdapter.addCustomHeaders(arrayList2, this.mLatestPosition);
        }
    }

    public void saveDealDashSpinResult(final int i) {
        withServiceFragment(new ServiceTask<BaseActivity, BaseProductFeedServiceFragment>() {
            public void performTask(BaseActivity baseActivity, BaseProductFeedServiceFragment baseProductFeedServiceFragment) {
                baseProductFeedServiceFragment.spinDealDash(i);
            }
        });
    }

    public void handleSaveDealDashSpinResultSuccess(WishDealDashInfo wishDealDashInfo) {
        if (this.mAdapter.getDealDashFeedView() != null) {
            if (!ExperimentDataCenter.getInstance().shouldSeeDealDashCoupon() || wishDealDashInfo.getCouponCode() == null) {
                this.mCountdownText.setText(R.string.blitz_buy_countdown_text);
            } else {
                this.mCountdownText.setLineSpacing(0.0f, 1.3f);
                this.mCountdownText.setFontResizable(true);
                this.mCountdownText.setMaxLines(2);
                this.mCountdownText.setText(getDealDashCouponStringSpan(wishDealDashInfo));
            }
            this.mAdapter.getDealDashFeedView().handleSaveSpinResultSuccess(wishDealDashInfo);
        }
    }

    public void handleSaveDealDashSpinResultFailure() {
        if (this.mAdapter.getDealDashFeedView() != null) {
            this.mAdapter.getDealDashFeedView().handleSaveSpinResultFailure();
        }
    }

    public void startDealDash(final int i, final int i2) {
        withServiceFragment(new ServiceTask<BaseActivity, BaseProductFeedServiceFragment>() {
            public void performTask(BaseActivity baseActivity, BaseProductFeedServiceFragment baseProductFeedServiceFragment) {
                baseProductFeedServiceFragment.startDealDash(i, i2);
            }
        });
    }

    public void handleStartDealDashSuccess(WishDealDashInfo wishDealDashInfo) {
        if (this.mAdapter.getDealDashFeedView() != null) {
            this.mAdapter.getDealDashFeedView().handleStartDealDashSuccess(wishDealDashInfo);
        }
    }

    public void handleStartDealDashFailure() {
        if (this.mAdapter.getDealDashFeedView() != null) {
            this.mAdapter.getDealDashFeedView().handleStartDealDashFailure();
        }
    }

    public void showDealDashHelpModal() {
        withActivity(new ActivityTask<A>() {
            public void performTask(A a) {
                a.startDialog(new DealDashHelpDialogFragment());
            }
        });
    }

    public void loadCurrentDailyGiveaway() {
        withServiceFragment(new ServiceTask<BaseActivity, BaseProductFeedServiceFragment>() {
            public void performTask(BaseActivity baseActivity, BaseProductFeedServiceFragment baseProductFeedServiceFragment) {
                baseProductFeedServiceFragment.loadCurrentDailyGiveaway();
            }
        });
    }

    public void handleCurrentDailyGiveawaySuccess(WishCurrentDailyGiveawayInfo wishCurrentDailyGiveawayInfo) {
        if (this.mAdapter.getDailyGiveawayFeedView() != null) {
            this.mAdapter.getDailyGiveawayFeedView().handleCurrentDailyGiveawaySuccess(wishCurrentDailyGiveawayInfo);
        }
    }

    public void handleCurrentDailyGiveawayFailure() {
        if (this.mAdapter.getDailyGiveawayFeedView() != null) {
            this.mAdapter.getDailyGiveawayFeedView().handleCurrentDailyGiveawayFailure();
        }
    }

    public void loadUpcomingDailyGiveaway() {
        withServiceFragment(new ServiceTask<BaseActivity, BaseProductFeedServiceFragment>() {
            public void performTask(BaseActivity baseActivity, BaseProductFeedServiceFragment baseProductFeedServiceFragment) {
                baseProductFeedServiceFragment.loadUpcomingDailyGiveaway();
            }
        });
    }

    public void handleUpcomingDailyGiveawaySuccess(WishUpcomingDailyGiveawayInfo wishUpcomingDailyGiveawayInfo) {
        if (this.mAdapter.getDailyGiveawayFeedView() != null) {
            this.mAdapter.getDailyGiveawayFeedView().handleUpcomingDailyGiveawaySuccess(wishUpcomingDailyGiveawayInfo);
        }
    }

    public void handleUpcomingDailyGiveawayFailure() {
        if (this.mAdapter.getDailyGiveawayFeedView() != null) {
            this.mAdapter.getDailyGiveawayFeedView().handleUpcomingDailyGiveawayFailure();
        }
    }

    public void loadInfoDailyGiveaway() {
        withServiceFragment(new ServiceTask<BaseActivity, BaseProductFeedServiceFragment>() {
            public void performTask(BaseActivity baseActivity, BaseProductFeedServiceFragment baseProductFeedServiceFragment) {
                baseProductFeedServiceFragment.loadInfoDailyGiveaway();
            }
        });
    }

    public void claimGiveaway(final WishProduct wishProduct) {
        withServiceFragment(new ServiceTask<BaseActivity, BaseProductFeedServiceFragment>() {
            public void performTask(BaseActivity baseActivity, BaseProductFeedServiceFragment baseProductFeedServiceFragment) {
                baseProductFeedServiceFragment.claimGiveaway(wishProduct);
            }
        });
    }

    public void handleInfoDailyGiveawaySuccess(WishInfoDailyGiveawayInfo wishInfoDailyGiveawayInfo) {
        if (this.mAdapter.getDailyGiveawayFeedView() != null) {
            this.mAdapter.getDailyGiveawayFeedView().handleInfoDailyGiveawaySuccess(wishInfoDailyGiveawayInfo);
        }
    }

    public void handleInfoDailyGiveawayFailure() {
        if (this.mAdapter.getDailyGiveawayFeedView() != null) {
            this.mAdapter.getDailyGiveawayFeedView().handleInfoDailyGiveawayFailure();
        }
    }

    public void updateFilter(WishFilter wishFilter) {
        if (this.mFilter == null) {
            this.mFilter = wishFilter;
            this.mMainCategories = new ArrayList<>();
            this.mMainCategories.add(this.mFilter);
            refreshFilter();
            refreshActionBar();
        }
    }

    public void updateMainCategories(FeedExtraInfo feedExtraInfo) {
        int i;
        if (feedExtraInfo.mainCategories != null) {
            this.mTimestamp = System.currentTimeMillis();
            this.mFeedExtraInfo = feedExtraInfo;
            this.mMainCategories.clear();
            this.mMainCategories.addAll(feedExtraInfo.mainCategories);
            int findPositionFromFilterId = findPositionFromFilterId("merchant_feed__tab");
            int i2 = -1;
            if (!ExperimentDataCenter.getInstance().showMerchantFeedTabs() && findPositionFromFilterId != -1) {
                this.mMainCategories.remove(findPositionFromFilterId);
            }
            if (hasItems()) {
                getLoadingPageView().markLoadingComplete();
            }
            if (this.mAdapter != null) {
                this.mAdapter.notifyDataSetChanged();
            }
            handleTabSelected(this.mCurrentIndex);
            updateTitleView();
            if (!canShowFeedCategories() || this.mMainCategories.size() <= 0) {
                this.mTabStrip.setVisibility(8);
            } else {
                this.mLatestPosition = findPositionFromFilterId("tabbed_feed_latest");
                this.mBrandedPosition = findPositionFromFilterId("brand__tab");
                this.mFreeGiftsTabPosition = findPositionFromFilterId("free_gift__tab");
                this.mDailyGiveawayPosition = findPositionFromFilterId("daily_giveaway__tab");
                this.mWishExpressPosition = findPositionFromFilterId("express__tab");
                this.mRecentlyViewedTabPosition = findPositionFromFilterId("recently_viewed__tab");
                this.mRecentWishlistTabPosition = findPositionFromFilterId("recent_wishlist__tab");
                TabType[] tabTypeArr = new TabType[this.mMainCategories.size()];
                Arrays.fill(tabTypeArr, TabType.TEXT_TAB);
                boolean[] zArr = new boolean[this.mMainCategories.size()];
                if (feedExtraInfo.initialCategoryId != null) {
                    i = findPositionFromFilterId(feedExtraInfo.initialCategoryId);
                } else {
                    i = this.mLatestPosition;
                }
                if (i == -1) {
                    i = 0;
                }
                this.mMerchantFeedTabPosition = findPositionFromFilterId("merchant_feed__tab");
                if (this.mMerchantFeedTabPosition != -1) {
                    tabTypeArr[this.mMerchantFeedTabPosition] = TabType.ICON_TAB;
                }
                if (this.mBrandedPosition != -1) {
                    BrandedFeedHeaderView brandedFeedHeaderView = new BrandedFeedHeaderView(getContext(), this);
                    brandedFeedHeaderView.setBrandedFeedInfo(((WishFilter) this.mMainCategories.get(this.mBrandedPosition)).getBrandedFeedInfo());
                    this.mAdapter.addCustomHeader(brandedFeedHeaderView, this.mBrandedPosition);
                    if (ExperimentDataCenter.getInstance().shouldShowOutletIcon()) {
                        tabTypeArr[this.mBrandedPosition] = TabType.ICON_TAB;
                    }
                }
                this.mTabStrip.setVisibility(0);
                String mainRequestId = getMainRequestId();
                if (mainRequestId != null) {
                    int findPositionFromFilterId2 = findPositionFromFilterId(mainRequestId);
                    if (findPositionFromFilterId2 != -1) {
                        setSelectedTab(findPositionFromFilterId2);
                    } else {
                        setSelectedTab(i);
                    }
                } else {
                    setSelectedTab(i);
                }
                customizeTabStrip();
                this.mDealDashPosition = findPositionFromFilterId("deal_dash__tab");
                this.mAdapter.setDealDash(this.mDealDashPosition, feedExtraInfo.dealDashInfo);
                if (this.mDailyGiveawayPosition != -1) {
                    this.mAdapter.setDailyGiveawayTab(this.mDailyGiveawayPosition, feedExtraInfo.dailyGiveawayIsOngoing);
                }
                if (!(this.mFreeGiftsTabPosition == -1 || feedExtraInfo.freeGiftTabInfo == null)) {
                    this.mAdapter.setFreeGiftsTab(this.mFreeGiftsTabPosition, feedExtraInfo.freeGiftTabInfo);
                    this.mAdapter.addCustomHeader(new FreeGiftsTabHeaderView(getContext(), this, feedExtraInfo.freeGiftTabInfo), this.mFreeGiftsTabPosition);
                    this.mFreeGiftsBanner.setVisibility(8);
                }
                if (ExperimentDataCenter.getInstance().shouldSeeRecentWishlistAsTab() && this.mRecentWishlistTabPosition != -1) {
                    this.mAdapter.setRecentWishlistTab(this.mRecentWishlistTabPosition);
                }
                if (this.mDealDashPosition >= 0) {
                    tabTypeArr[this.mDealDashPosition] = TabType.ICON_TAB;
                }
                if (this.mDailyGiveawayPosition >= 0) {
                    tabTypeArr[this.mDailyGiveawayPosition] = TabType.ICON_TAB;
                    if (this.mAdapter.getDailyGiveawayIsOngoing() && PreferenceUtil.getLong("LastDailyGiveawayFeedTime", 0) + TimeUnit.HOURS.toMillis(5) < System.currentTimeMillis()) {
                        zArr[this.mDailyGiveawayPosition] = true;
                    }
                }
                if (this.mWishExpressPosition != -1) {
                    tabTypeArr[this.mWishExpressPosition] = TabType.ICON_TAB;
                    WishExpressBannerView wishExpressBannerView = new WishExpressBannerView(getContext());
                    wishExpressBannerView.setup(((WishFilter) this.mMainCategories.get(this.mWishExpressPosition)).getWishExpressBannerInfo(), true);
                    this.mAdapter.addCustomHeader(wishExpressBannerView, this.mWishExpressPosition);
                    this.mAdapter.setWishExpressTab(this.mWishExpressPosition);
                }
                if (ExperimentDataCenter.getInstance().showMerchantFeedTabs() && this.mRecentlyViewedTabPosition != -1) {
                    this.mAdapter.setRecentlyViewedTabPosition(this.mRecentlyViewedTabPosition);
                }
                if (ExperimentDataCenter.getInstance().showMerchantFeedTabs() && this.mMerchantFeedTabPosition != -1) {
                    this.mAdapter.setMerchantFeedTabPosition(this.mMerchantFeedTabPosition);
                }
                if (this.mLatestPosition != -1) {
                    this.mHomePageViewTitle = ((WishFilter) this.mMainCategories.get(this.mLatestPosition)).getName();
                }
                int findPositionFromFilterId3 = findPositionFromFilterId("wish_express__tab");
                if (findPositionFromFilterId3 != -1) {
                    tabTypeArr[findPositionFromFilterId3] = TabType.ICON_TAB;
                    this.mAdapter.setSearchWishExpressTab(findPositionFromFilterId3);
                }
                this.mTabStrip.setTabTypes(tabTypeArr);
                this.mTabStrip.setTabBadged(zArr);
                this.mTabStrip.setViewPager(this.mViewPager);
                this.mTabStrip.setOnPageChangeListener(new OnPageChangeListener() {
                    public void onPageScrolled(int i, float f, int i2) {
                        if (ProductFeedFragment.this.mTabAreaContainer.getAnimation() != null) {
                            ProductFeedFragment.this.showTabArea(true);
                        }
                    }

                    public void onPageSelected(int i) {
                        ProductFeedFragment.this.mCurrentIndex = i;
                        ProductFeedFragment.this.refreshTabStripFontColors();
                        if (ProductFeedFragment.this.mLatestPosition != i) {
                            ProductFeedFragment.this.mLastestRequestSourceType = null;
                        }
                    }

                    public void onPageScrollStateChanged(int i) {
                        if (i == 0 && ProductFeedFragment.this.mAdapter != null) {
                            ProductFeedFragment.this.mAdapter.handleResume();
                        }
                    }
                });
                this.mTabStrip.setOnTabClickListener(new OnTabClickListener() {
                    public void onTabSelected(int i) {
                        ProductFeedFragment.this.mViewPager.setCurrentItem(i);
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_MAIN_TAB_STRIP);
                    }
                });
                refreshTabStripFontColors();
                i2 = i;
            }
            if (i2 > 0) {
                getHandler().postDelayed(new Runnable() {
                    public void run() {
                        ProductFeedFragment.this.mTabStrip.scrollTo((int) ValueUtil.convertDpToPx(-100.0f), 0);
                    }
                }, 100);
            }
        }
    }

    public void updateActionBarTitle(final String str) {
        withActivity(new ActivityTask<A>() {
            public void performTask(A a) {
                a.getActionBarManager().setTitle(str);
            }
        });
    }

    public void updateFeedExtraDataBundle(FeedExtraDataBundle feedExtraDataBundle) {
        this.mFeedExtraDataBundle = feedExtraDataBundle;
        if (feedExtraDataBundle.merchant != null) {
            MerchantFeedBannerView merchantFeedBannerView = new MerchantFeedBannerView(getContext(), this);
            merchantFeedBannerView.setMerchant(feedExtraDataBundle.merchant);
            this.mAdapter.addCustomHeader(merchantFeedBannerView, 0);
        }
    }

    public void updateDealDashInfo(final WishDealDashInfo wishDealDashInfo) {
        this.mFeedExtraInfo.dealDashInfo = wishDealDashInfo;
        this.mAdapter.setDealDash(this.mDealDashPosition, this.mFeedExtraInfo.dealDashInfo);
        if (!ExperimentDataCenter.getInstance().shouldSeeDealDashCoupon() || wishDealDashInfo.getCouponCode() == null) {
            this.mCountdownText.setText(R.string.blitz_buy_countdown_text);
            return;
        }
        this.mCountdownText.setLineSpacing(0.0f, 1.3f);
        this.mCountdownText.setFontResizable(true);
        this.mCountdownText.setMaxLines(2);
        this.mCountdownText.setText(getDealDashCouponStringSpan(wishDealDashInfo));
        this.mCountdownText.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_DEAL_DASH_COUPON_COPY);
                if (ClipboardUtil.copyToClipboard(wishDealDashInfo.getCouponCode())) {
                    ProductFeedFragment.this.withActivity(new ActivityTask<A>() {
                        public void performTask(A a) {
                            SuccessBottomSheetDialog.create(a).setTitle(WishApplication.getInstance().getString(R.string.copied_exclamation)).autoDismiss().show();
                        }
                    });
                }
            }
        });
    }

    public SpannableString getDealDashCouponStringSpan(WishDealDashInfo wishDealDashInfo) {
        String couponCode = wishDealDashInfo.getCouponCode();
        StringBuilder sb = new StringBuilder();
        sb.append("   ");
        sb.append(couponCode);
        sb.append("   ");
        String sb2 = sb.toString();
        String format = String.format(WishApplication.getInstance().getString(R.string.blitz_buy_coupon_countdown_text), new Object[]{sb2, wishDealDashInfo.getDiscountPercentString()});
        int indexOf = format.indexOf(sb2);
        if (indexOf != -1) {
            int length = sb2.length() + indexOf;
            SpannableString spannableString = new SpannableString(format);
            spannableString.setSpan(new BoldBorderSpan(WishApplication.getInstance().getResources().getDrawable(R.drawable.deal_dash_coupon_drawable), WishApplication.getInstance().getResources().getDimensionPixelOffset(R.dimen.two_padding), this.mCountdownText.getLineSpacingMultiplier()), indexOf + 1, length - 1, 33);
            return spannableString;
        }
        Crashlytics.logException(new Exception("DealDash: Invalid index of coupon!"));
        return new SpannableString(format);
    }

    /* access modifiers changed from: protected */
    public void refreshActionBar() {
        withActivity(new ActivityTask<A>() {
            public void performTask(A a) {
                Theme theme = ProductFeedFragment.this.getSource() == Source.BRANDED ? Theme.WHITE_BACKGROUND : Theme.APP_COLOR_BACKGROUND;
                ActionBarManager actionBarManager = a.getActionBarManager();
                actionBarManager.setTheme(theme);
                ProductFeedFragment.this.setupBaseActionBar();
                if (ProductFeedFragment.this.getDataMode() == DataMode.FilteredFeed && ExperimentDataCenter.getInstance().shouldSeeCamera() && CameraUtils.hasCameras(a) && StatusDataCenter.getInstance().userHasPurchasedBefore()) {
                    actionBarManager.addActionBarItem(0, ActionBarItem.createCameraActionBarItem(actionBarManager));
                }
                if (ProductFeedFragment.this.canFilter()) {
                    actionBarManager.addActionBarItem(ActionBarItem.createFilterActionBarItem(actionBarManager));
                }
                if (ProductFeedFragment.this.getDataMode() == DataMode.FilteredFeed || ProductFeedFragment.this.getDataMode() == DataMode.Tag) {
                    actionBarManager.addActionBarItem(ActionBarItem.createSearchActionBarItem(actionBarManager));
                }
            }
        });
    }

    private void refreshFilter() {
        withActivity(new ActivityTask<A>() {
            public void performTask(A a) {
                if (ProductFeedFragment.this.canFilter()) {
                    a.unlockRightDrawer();
                } else {
                    a.lockRightDrawer();
                }
            }
        });
    }

    public void setSelectedTab(int i) {
        this.mViewPager.setCurrentItem(i);
        handleTabSelected(i);
    }

    /* access modifiers changed from: protected */
    public void handleTabSelected(final int i, boolean z) {
        this.mCurrentIndex = i;
        refreshActionBar();
        customizeTabStrip();
        refreshTabStripFontColors();
        refreshFilter();
        showTabArea(true);
        updateFreeGiftsBanner();
        updateCountdownTimer();
        withUiFragment(new UiTask<BaseActivity, FilterFragment>() {
            public void performTask(BaseActivity baseActivity, FilterFragment filterFragment) {
                filterFragment.updatePosition(i);
            }
        }, "FragmentTagRightDrawer");
        if (!z) {
            return;
        }
        if (i == this.mDealDashPosition) {
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_DEAL_DASH_TAB);
            StatusDataCenter.getInstance().refresh();
        } else if (i == this.mBrandedPosition) {
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_BRANDED_PRODUCT_TAB);
        } else if (i == this.mFreeGiftsTabPosition) {
            WishFreeGiftTabInfo.logFreeGiftTabEvent(WishAnalyticsEvent.CLICK_MOBILE_FREE_GIFT_TAB);
        } else if (ExperimentDataCenter.getInstance().showMerchantFeedTabs() && i == this.mMerchantFeedTabPosition) {
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MERCHANTS_FEED_TAB);
        } else if (i == this.mRecentlyViewedTabPosition) {
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_RECENTLY_VIEWED_TAB);
        } else if (ExperimentDataCenter.getInstance().shouldSeeRecentWishlistAsTab() && i == this.mRecentWishlistTabPosition) {
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_RECENT_WISHLIST_ITEMS_TAB);
        }
    }

    /* access modifiers changed from: private */
    public void handleTabSelected(int i) {
        handleTabSelected(i, true);
    }

    /* access modifiers changed from: private */
    public void updateTitleView() {
        withActivity(new ActivityTask<A>() {
            public void performTask(A a) {
                if (ProductFeedFragment.this.mCurrentIndex != ProductFeedFragment.this.mBrandedPosition) {
                    a.getActionBarManager().setTitle(a.getActionBarTitle());
                } else if (ProductFeedFragment.this.mMainCategories != null && ProductFeedFragment.this.mMainCategories.size() > ProductFeedFragment.this.mBrandedPosition) {
                    a.getActionBarManager().setTitle(((WishFilter) ProductFeedFragment.this.mMainCategories.get(ProductFeedFragment.this.mBrandedPosition)).getName());
                }
            }
        });
    }

    private void customizeTabStrip() {
        ActionBarManager actionBarManager = getBaseActivity() == null ? null : ((DrawerActivity) getBaseActivity()).getActionBarManager();
        if (actionBarManager != null) {
            actionBarManager.stylizeTabStrip(this.mTabStrip);
            this.mTabStripShadow.setVisibility(actionBarManager.getTheme() == Theme.WHITE_BACKGROUND ? 0 : 8);
        }
    }

    /* access modifiers changed from: private */
    public void refreshTabStripFontColors() {
        if (canShowFeedCategories()) {
            if (!(getBaseActivity() == null || ((DrawerActivity) getBaseActivity()).getActionBarManager() == null)) {
                ((DrawerActivity) getBaseActivity()).getActionBarManager().refreshTabStripFontColors(this.mTabStrip, this.mCurrentIndex);
            }
            LinearLayout linearLayout = (LinearLayout) this.mTabStrip.getChildAt(0);
            for (int i = 0; i < linearLayout.getChildCount(); i++) {
                if (this.mTabStrip.getTabType(i) == TabType.ICON_TAB) {
                    if (getSource() == Source.BRANDED && i == this.mCurrentIndex) {
                        ((ImageButton) linearLayout.getChildAt(i)).setImageResource(R.drawable.branded_selected_tab_icon);
                    } else if (getSource() != Source.BRANDED && i == this.mBrandedPosition) {
                        ((ImageButton) linearLayout.getChildAt(i)).setImageResource(R.drawable.branded_unselected_tab_icon);
                    }
                }
            }
        }
    }

    public void showTabArea(boolean z) {
        int tabAreaOffset = getTabAreaOffset();
        if (tabAreaOffset != 0) {
            this.mTabAreaContainer.clearAnimation();
            int i = 0 - tabAreaOffset;
            TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, 0.0f, (float) i);
            translateAnimation.setDuration(z ? (long) ((int) ((((double) Math.abs(i)) / ((double) getHiddenTabAreaSize())) * 250.0d)) : 0);
            translateAnimation.setFillAfter(false);
            translateAnimation.setAnimationListener(new AnimationListener() {
                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationStart(Animation animation) {
                }

                public void onAnimationEnd(Animation animation) {
                    ProductFeedFragment.this.setTabAreaOffset(0);
                }
            });
            this.mTabAreaContainer.startAnimation(translateAnimation);
        }
    }

    public void hideTabArea(boolean z) {
        final int i = -getHiddenTabAreaSize();
        int tabAreaOffset = getTabAreaOffset();
        if (tabAreaOffset != i) {
            this.mTabAreaContainer.clearAnimation();
            int i2 = i - tabAreaOffset;
            TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, 0.0f, (float) i2);
            translateAnimation.setDuration(z ? (long) ((int) ((((double) Math.abs(i2)) / ((double) getHiddenTabAreaSize())) * 250.0d)) : 0);
            translateAnimation.setFillAfter(false);
            translateAnimation.setAnimationListener(new AnimationListener() {
                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationStart(Animation animation) {
                }

                public void onAnimationEnd(Animation animation) {
                    ProductFeedFragment.this.setTabAreaOffset(i);
                }
            });
            this.mTabAreaContainer.startAnimation(translateAnimation);
        }
    }

    public int getTabAreaOffset() {
        return ((LayoutParams) this.mTabAreaContainer.getLayoutParams()).topMargin;
    }

    public void setTabAreaOffset(int i) {
        this.mTabAreaContainer.clearAnimation();
        LayoutParams layoutParams = (LayoutParams) this.mTabAreaContainer.getLayoutParams();
        layoutParams.topMargin = Math.min(Math.max(i, -getHiddenTabAreaSize()), 0);
        this.mTabAreaContainer.setLayoutParams(layoutParams);
        if (this.mAdapter != null) {
            this.mAdapter.notifyTabOffsetUpdated();
        }
    }

    public int getHiddenTabAreaSize() {
        int i = 0;
        if (this.mFreeGiftsBanner.getVisibility() == 0) {
            i = 0 + ((int) ValueUtil.convertDpToPx(64.0f));
        }
        return this.mTabStrip.getVisibility() == 0 ? i + ((int) ValueUtil.convertDpToPx(48.0f)) : i;
    }

    public int getTabAreaSize() {
        int i = 0;
        if (this.mFreeGiftsBanner.getVisibility() == 0) {
            i = 0 + ((int) ValueUtil.convertDpToPx(64.0f));
        }
        return this.mTabStrip.getVisibility() == 0 ? i + ((int) ValueUtil.convertDpToPx(48.0f)) : i;
    }

    /* access modifiers changed from: protected */
    public int getFeedTypeCount() {
        if (!getLoadingPageView().isLoadingComplete()) {
            return 0;
        }
        if (canShowFeedCategories()) {
            return this.mMainCategories.size();
        }
        return 1;
    }

    /* access modifiers changed from: protected */
    public String getRequestId(int i) {
        if (!canShowFeedCategories() || i < 0 || i >= this.mMainCategories.size()) {
            return getMainRequestId();
        }
        return ((WishFilter) this.mMainCategories.get(i)).getFilterId();
    }

    /* access modifiers changed from: protected */
    public String getFeedTitle(int i) {
        return (!canShowFeedCategories() || i < 0 || i >= this.mMainCategories.size()) ? "" : ((WishFilter) this.mMainCategories.get(i)).getName();
    }

    /* access modifiers changed from: private */
    public boolean canFilter() {
        boolean z = false;
        if (!isFeedFilterable() || this.mMainCategories == null || this.mMainCategories.size() <= 0 || this.mCurrentIndex > this.mMainCategories.size()) {
            return false;
        }
        ArrayList childFilterGroups = ((WishFilter) this.mMainCategories.get(this.mCurrentIndex)).getChildFilterGroups();
        if (childFilterGroups != null && childFilterGroups.size() > 0) {
            z = true;
        }
        return z;
    }

    public boolean hasCountdownTimer() {
        return this.mCurrentIndex == this.mDealDashPosition && this.mCountdownTargetDate != null && !DateUtil.getTimeDifferenceFromNow(this.mCountdownTargetDate).isExpired();
    }

    public void handleFilterApply() {
        if (getSource() == Source.BRANDED) {
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_BRANDED_PRODUCT_FILTER_SELECT);
        }
        withActivity(new ActivityTask<A>() {
            public void performTask(A a) {
                a.closeRightDrawer();
            }
        });
        applyFilter();
    }

    public void updateFreeGiftsBanner() {
        if (this.mFreeGiftsTabPosition == -1 || this.mCurrentIndex == this.mFreeGiftsTabPosition || this.mCurrentIndex == this.mBrandedPosition || this.mCurrentIndex == this.mDealDashPosition || this.mFeedExtraInfo == null || this.mFeedExtraInfo.freeGiftTabInfo == null || !this.mFeedExtraInfo.freeGiftTabInfo.showBanner()) {
            this.mFreeGiftsBanner.setVisibility(8);
            return;
        }
        this.mFreeGiftsBannerDaysLeft.setText(DateUtil.getFuzzyTimeRemaining(this.mFeedExtraInfo.freeGiftTabInfo.getExpiry()));
        this.mFreeGiftsBanner.setVisibility(0);
    }

    public void setCountdownTargetDate(Date date) {
        this.mCountdownTargetDate = date;
        if (!ExperimentDataCenter.getInstance().shouldSeeDealDashCoupon()) {
            this.mCountdownView.setVisibility(0);
            this.mNewCountdownView.setVisibility(8);
            this.mCountdownView.setup(this.mCountdownTargetDate, getResources().getDimensionPixelSize(R.dimen.product_feed_fragment_timer_height), getResources().getColor(R.color.white), getResources().getColor(R.color.dark_gray_1), getResources().getColor(R.color.white), R.drawable.timer_square, true, !ExperimentDataCenter.getInstance().shouldSeeDealDashCoupon(), new DoneCallback() {
                public void onCountdownEnd() {
                    if (ProductFeedFragment.this.mAdapter.getDealDashFeedView() != null) {
                        ProductFeedFragment.this.mAdapter.getDealDashFeedView().handleTimerEnded();
                    }
                    ProductFeedFragment.this.mCountdownContainer.setVisibility(8);
                    ProductFeedFragment.this.showDealDashCartModal();
                }
            });
            this.mCountdownView.startTimer();
        } else {
            this.mNewCountdownView.setVisibility(0);
            this.mCountdownView.setVisibility(8);
            this.mCountdownContainer.setMinimumHeight(getResources().getDimensionPixelOffset(R.dimen.deal_dash_coupon_minimum_height));
            this.mNewCountdownView.setup(this.mCountdownTargetDate, new TimerWatcherAdapter() {
                public void onCountdownComplete() {
                    if (ProductFeedFragment.this.mAdapter.getDealDashFeedView() != null) {
                        ProductFeedFragment.this.mAdapter.getDealDashFeedView().handleTimerEnded();
                    }
                    ProductFeedFragment.this.mCountdownContainer.setVisibility(8);
                    ProductFeedFragment.this.showDealDashCartModal();
                }
            }, false);
        }
        updateCountdownTimer();
    }

    public Date getCountdownDate() {
        return this.mCountdownTargetDate;
    }

    public void updateCountdownTimer() {
        if (!hasCountdownTimer() || this.mCountdownTargetDate == null) {
            this.mCountdownContainer.setVisibility(8);
        } else {
            this.mCountdownContainer.setVisibility(0);
        }
    }

    /* access modifiers changed from: private */
    public void setRushCountDownView() {
        if (!ExperimentDataCenter.getInstance().shouldSeeDealDashCoupon()) {
            this.mCountdownContainer.setBackgroundColor(WishApplication.getInstance().getResources().getColor(R.color.red));
            this.mCountdownText.setText(WishApplication.getInstance().getString(R.string.blitz_buy_countdown_text_rush));
        }
    }

    /* access modifiers changed from: private */
    public void showDealDashCartModal() {
        withActivity(new ActivityTask<A>() {
            public void performTask(A a) {
                MultiButtonDialogChoice multiButtonDialogChoice;
                final A a2 = a;
                MultiButtonDialogFragmentBuilder multiButtonDialogFragmentBuilder = new MultiButtonDialogFragmentBuilder();
                String string = ProductFeedFragment.this.getString(R.string.blitz_buy_cart_modal_message);
                if (ExperimentDataCenter.getInstance().shouldSeeDealDashCoupon()) {
                    multiButtonDialogFragmentBuilder.hideXButton();
                    string = ProductFeedFragment.this.getString(R.string.blitz_buy_continue_shopping_modal_message);
                    multiButtonDialogChoice = new MultiButtonDialogChoice(1, ProductFeedFragment.this.getString(R.string.continue_shopping), R.color.white, R.color.deal_dash_background, BackgroundType.COLOR, ChoiceType.DEFAULT);
                } else {
                    multiButtonDialogChoice = new MultiButtonDialogChoice(0, ProductFeedFragment.this.getString(R.string.cart), R.color.white, R.color.deal_dash_background, BackgroundType.COLOR, ChoiceType.DEFAULT);
                }
                ArrayList arrayList = new ArrayList();
                arrayList.add(multiButtonDialogChoice);
                a2.startDialog(multiButtonDialogFragmentBuilder.setTitle(ProductFeedFragment.this.getString(R.string.blitz_buy_cart_modal_title)).setSubTitle(string).setImageResource(R.drawable.dealdash_modal_icon).setImageSize(ImageSize.LARGE).setButtons(arrayList).build(), new BaseDialogCallback() {
                    public void onCancel(BaseDialogFragment baseDialogFragment) {
                    }

                    public void onSelection(BaseDialogFragment baseDialogFragment, int i, Bundle bundle) {
                        switch (i) {
                            case 0:
                                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_DEAL_DASH_GO_TO_CART_MODAL);
                                Intent intent = new Intent();
                                intent.setClass(a2, CartActivity.class);
                                a2.startActivity(intent);
                                return;
                            case 1:
                                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_DEAL_DASH_COUPON_TIME_UP_CONTINUE_SHOPPING);
                                Intent intent2 = new Intent();
                                intent2.setClass(a2, BrowseActivity.class);
                                a2.startActivity(intent2);
                                return;
                            default:
                                return;
                        }
                    }
                });
            }
        });
    }

    public void applyFilter() {
        if (this.mAdapter != null && this.mAdapter.getCurrentFeedView() != null) {
            this.mAdapter.getCurrentFeedView().reload();
        }
    }

    public boolean hasItems() {
        boolean z = true;
        if (getDataMode() != DataMode.FilteredFeed) {
            return true;
        }
        if (this.mMainCategories == null || this.mMainCategories.size() <= 0) {
            z = false;
        }
        return z;
    }

    public void handleReload() {
        handleReload(false);
    }

    public void handleReload(boolean z) {
        refreshActionBar();
        if (isPlaceholderMode()) {
            getLoadingPageView().markLoadingComplete();
            return;
        }
        if (z || (getDataMode() == DataMode.FilteredFeed && !getLoadingPageView().isLoadingComplete())) {
            this.mCurrentIndex = 0;
            this.mTimestamp = 0;
            this.mMainCategories = new ArrayList<>();
            this.mInitialProducts = new SparseArray<>();
            if (this.mAdapter != null) {
                this.mAdapter.resetInfo();
                this.mViewPager.setAdapter(this.mAdapter);
            }
            withServiceFragment(new ServiceTask<BaseActivity, BaseProductFeedServiceFragment>() {
                public void performTask(BaseActivity baseActivity, BaseProductFeedServiceFragment baseProductFeedServiceFragment) {
                    FeedContext feedContext = new FeedContext();
                    feedContext.requestCategories = true;
                    feedContext.requestSourceType = ProductFeedFragment.this.mLastestRequestSourceType;
                    baseProductFeedServiceFragment.loadFilterFeedProducts(0, 0, feedContext);
                }
            });
        } else {
            getLoadingPageView().markLoadingComplete();
            if (this.mAdapter != null) {
                this.mAdapter.notifyDataSetChanged();
            }
        }
    }

    public boolean handleActionBarItemSelected(int i) {
        if (i == 2000) {
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_FEED_FILTER_NAV);
            openFilter();
            return true;
        } else if (i != 2001) {
            return super.handleActionBarItemSelected(i);
        } else {
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_ACTION_BAR_CAMERA_BUTTON);
            handleCameraClick();
            return true;
        }
    }

    public void openFilter() {
        if (getSource() == Source.BRANDED) {
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_BRANDED_PRODUCT_FILTER);
        }
        withUiFragment(new UiTask<BaseActivity, FilterFragment>() {
            public void performTask(BaseActivity baseActivity, FilterFragment filterFragment) {
                filterFragment.backToRoot();
            }
        }, "FragmentTagRightDrawer");
        withActivity(new ActivityTask<A>() {
            public void performTask(A a) {
                a.openRightDrawer();
            }
        });
    }

    public void setCameraDialogRunnable() {
        this.mCameraTooltipDialogRunnable = new Runnable() {
            public void run() {
                ProductFeedFragment.this.withActivity(new ActivityTask<A>() {
                    public void performTask(A a) {
                        View findViewById = a.getWindow().getDecorView().findViewById(2001);
                        if (findViewById != null && findViewById.getVisibility() == 0) {
                            int[] iArr = new int[2];
                            findViewById.getLocationOnScreen(iArr);
                            a.getFragmentManager().beginTransaction().add(CameraDialogFragment.createCameraDialogFragment(iArr[0] + findViewById.getPaddingLeft(), iArr[1]), "camera_dialog_tooltip").commitAllowingStateLoss();
                        }
                    }
                });
            }
        };
    }

    private void handleCameraClick() {
        final boolean z = PreferenceUtil.getBoolean("CameraFeatureSeen");
        withActivity(new ActivityTask<A>() {
            public void performTask(A a) {
                if (z) {
                    CameraActivity.startCameraActivityIfPermissionsGranted(a);
                } else {
                    a.startActivity(new Intent(a, ExampleUgcIntroActivity.class));
                }
            }
        });
    }

    public BaseInitialProductWrapper getInitialProductInfo(int i) {
        BaseInitialProductWrapper baseInitialProductWrapper = null;
        if (i != this.mLatestPosition) {
            if (!(this.mInitialProducts == null || this.mInitialProducts.get(i) == null)) {
                StringBuilder sb = new StringBuilder();
                sb.append("initial products on other tab! mLatestPosition: ");
                sb.append(this.mLatestPosition);
                sb.append(", mBrandedPosition: ");
                sb.append(this.mBrandedPosition);
                sb.append(", index: ");
                sb.append(i);
                Crashlytics.logException(new Exception(sb.toString()));
            }
            return null;
        }
        if (this.mInitialProducts != null) {
            baseInitialProductWrapper = (BaseInitialProductWrapper) this.mInitialProducts.get(i);
        }
        return baseInitialProductWrapper;
    }

    public void clearInitialProductInfo(int i) {
        if (this.mInitialProducts != null) {
            this.mInitialProducts.remove(i);
        }
    }

    public int findPositionFromFilterId(String str) {
        for (int i = 0; i < this.mMainCategories.size(); i++) {
            if (((WishFilter) this.mMainCategories.get(i)).getFilterId().equals(str)) {
                return i;
            }
        }
        return -1;
    }

    public ArrayList<WishProduct> getSelectedProducts() {
        return this.mAdapter.getSelectedProducts();
    }

    public ArrayList<String> getSelectedProductIds() {
        ArrayList<String> arrayList = new ArrayList<>();
        Iterator it = getSelectedProducts().iterator();
        while (it.hasNext()) {
            WishProduct wishProduct = (WishProduct) it.next();
            if (wishProduct != null) {
                arrayList.add(wishProduct.getProductId());
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: private */
    public void handleClaimFreeGifts() {
        if (this.mFreeGiftsTabPosition > 0) {
            setSelectedTab(this.mFreeGiftsTabPosition);
        }
    }

    public void handleFreeGiftRemindMeLater() {
        this.mFreeGiftsBanner.setVisibility(0);
        setSelectedTab(this.mLatestPosition);
    }

    public void handleHomeRowLoadingSuccess(int i, int i2, int i3) {
        if (this.mHomePageView != null) {
            this.mHomePageView.handleSuccess(i, i2, i3);
        }
    }

    public void handleHomeRowLoadingFailure(int i) {
        if (this.mHomePageView != null) {
            this.mHomePageView.handleFailure(i);
        }
    }

    public Source getSource() {
        if (this.mCurrentIndex == this.mBrandedPosition || getDataMode() == DataMode.Branded) {
            return Source.BRANDED;
        }
        if (this.mCurrentIndex == this.mDailyGiveawayPosition) {
            return Source.DAILY_GIVEAWAY;
        }
        return Source.DEFAULT;
    }

    private void trackScrollTabStripEvent() {
        this.mTabStrip.setOnTouchListener(new OnTouchListener() {
            int previousX = -1;

            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case 0:
                        this.previousX = ProductFeedFragment.this.mTabStrip.getScrollX();
                        break;
                    case 1:
                        if (this.previousX != ProductFeedFragment.this.mTabStrip.getScrollX()) {
                            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_SWIPE_MAIN_TAB_STRIP);
                            break;
                        }
                        break;
                }
                return false;
            }
        });
    }

    public int getLatestPosition() {
        return this.mLatestPosition;
    }

    public void smoothScrollToTop() {
        this.mAdapter.smoothScrollToTop();
    }

    public void switchToBrowseAndGoToTop() {
        this.mAdapter.switchToBrowseAndGoToTop();
    }
}
