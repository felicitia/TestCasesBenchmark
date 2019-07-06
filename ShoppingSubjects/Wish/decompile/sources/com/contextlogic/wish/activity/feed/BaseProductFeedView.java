package com.contextlogic.wish.activity.feed;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.activity.feed.BaseProductFeedAdapter.DataProvider;
import com.contextlogic.wish.activity.feed.ProductFeedFragment.DataMode;
import com.contextlogic.wish.activity.feed.wishlist.WishlistProductFeedAdapter;
import com.contextlogic.wish.activity.profile.wishlist.WishlistFragment;
import com.contextlogic.wish.analytics.FeedTileLogger;
import com.contextlogic.wish.analytics.FeedTileLogger.Action;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.cache.StateStoreCache;
import com.contextlogic.wish.http.ImageHttpPrefetcher;
import com.contextlogic.wish.ui.bottomnavigation.BottomNavigationHelper;
import com.contextlogic.wish.ui.bottomnavigation.BottomNavigationInterface;
import com.contextlogic.wish.ui.grid.StaggeredGridView;
import com.contextlogic.wish.ui.grid.StaggeredGridView.OnScrollListener;
import com.contextlogic.wish.ui.grid.StaggeredGridView.OnViewVisibleListener;
import com.contextlogic.wish.ui.image.ImageRestorable;
import com.contextlogic.wish.ui.loading.LoadingFooterView;
import com.contextlogic.wish.ui.loading.LoadingFooterView.LoadingFooterViewCallback;
import com.contextlogic.wish.ui.loading.LoadingPageView;
import com.contextlogic.wish.ui.loading.LoadingPageView.LoadingPageManager;
import com.contextlogic.wish.ui.view.WishStateRefresher;
import com.contextlogic.wish.ui.viewpager.BasePagerHelper;
import com.contextlogic.wish.ui.viewpager.BasePagerScrollingObserver;
import com.contextlogic.wish.ui.viewpager.BasePagerViewInterface;
import com.contextlogic.wish.util.ValueUtil;
import com.crashlytics.android.Crashlytics;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class BaseProductFeedView extends LoadingPageView implements ImageRestorable, LoadingPageManager, BasePagerScrollingObserver, BasePagerViewInterface {
    /* access modifiers changed from: protected */
    public BaseProductFeedAdapter mAdapter;
    protected DrawerActivity mBaseActivity;
    private CollapsableFeedHeaderView mCollapsableFeedHeaderView;
    private int mDataIndex;
    protected BaseProductFeedFragment mFragment;
    /* access modifiers changed from: protected */
    public StaggeredGridView mGridView;
    private ImageHttpPrefetcher mImagePrefetcher;
    private LoadingFooterView mLoadingFooter;
    private boolean mNeedsReload;
    /* access modifiers changed from: private */
    public boolean mNoMoreItems;
    private int mNumTimesNoMoreItems;
    private int mOffset;
    private boolean mOrderConfirmedInfoUpdated = false;
    private BasePagerHelper mPagerHelper;
    private HashSet<String> mProductIds;
    /* access modifiers changed from: private */
    public ArrayList<WishProduct> mProducts;
    protected String mRequestId;
    protected View mSpacerView;
    /* access modifiers changed from: private */
    public int mTapToLoadCount;
    /* access modifiers changed from: private */
    public int mTapToLoadThreshold;
    private boolean mTimedRefreshed;
    private long mTimestamp;
    /* access modifiers changed from: private */
    public HashSet<String> mVisibleProducts;

    public int getLoadingContentLayoutResourceId() {
        return R.layout.product_feed;
    }

    public int getNewMargins() {
        return -1;
    }

    public BaseProductFeedView(int i, DrawerActivity drawerActivity, BaseProductFeedFragment baseProductFeedFragment) {
        super(drawerActivity);
        this.mDataIndex = i;
        this.mBaseActivity = drawerActivity;
        this.mFragment = baseProductFeedFragment;
        setLoadingPageManager(this);
    }

    public void setRequestId(String str) {
        this.mRequestId = str;
    }

    public void initializeLoadingContentView(View view) {
        this.mProducts = new ArrayList<>();
        this.mProductIds = new HashSet<>();
        this.mVisibleProducts = new HashSet<>();
        this.mLoadingFooter = new LoadingFooterView(this.mBaseActivity);
        this.mLoadingFooter.setCallback(new LoadingFooterViewCallback() {
            public void onTapToLoad() {
                if (BaseProductFeedView.this.mTapToLoadCount < BaseProductFeedView.this.mTapToLoadThreshold) {
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_SHOW_MORE_RELATED_PRODUCTS);
                }
                BaseProductFeedView.this.mTapToLoadCount = BaseProductFeedView.this.mTapToLoadCount + 1;
                BaseProductFeedView.this.setForceTapToLoad(false);
                if (!BaseProductFeedView.this.mNoMoreItems && BaseProductFeedView.this.mProducts.size() != 0) {
                    BaseProductFeedView.this.mFragment.showBackToTopButton();
                    BaseProductFeedView.this.loadNextPage();
                }
            }
        });
        this.mGridView = (StaggeredGridView) view.findViewById(R.id.product_feed_gridview);
        this.mAdapter = initializeAdapter();
        this.mAdapter.setDataProvider(new DataProvider() {
            public ArrayList<WishProduct> getData() {
                return BaseProductFeedView.this.mProducts;
            }
        });
        this.mImagePrefetcher = new ImageHttpPrefetcher();
        this.mAdapter.setImagePrefetcher(this.mImagePrefetcher);
        this.mSpacerView = new View(this.mBaseActivity);
        this.mLoadingFooter.setReserveSpaceWhenHidden(false);
        this.mGridView.setFooterView(this.mLoadingFooter);
        setLoadingFooter(this.mLoadingFooter);
        this.mGridView.setAdapter(this.mAdapter);
        this.mAdapter.attachDefaultClickListener(this.mGridView, this.mFragment.getProductDetailsCallback());
        this.mGridView.setOnScrollListener(defaultGridViewOnScrollListener());
        this.mPagerHelper = getPagerHelper();
        if (this.mPagerHelper != null) {
            this.mPagerHelper.setupScroller(this.mGridView);
            if (ExperimentDataCenter.getInstance().shouldShowBottomNavigation() && (this.mFragment instanceof BottomNavigationInterface)) {
                this.mPagerHelper.setBottomNavigationHelper(new BottomNavigationHelper(this.mFragment));
            }
        }
        this.mGridView.setOnLongClickListener(new OnLongClickListener() {
            public boolean onLongClick(View view) {
                if (BaseProductFeedView.this.mFragment.getDataMode() != DataMode.Wishlist || !((WishlistFragment) BaseProductFeedView.this.mFragment).isCurrentUser()) {
                    return false;
                }
                if (view instanceof ProductFeedTileView) {
                    ProductFeedTileView productFeedTileView = (ProductFeedTileView) view;
                    if (productFeedTileView.isSelectable()) {
                        BaseProductFeedView.this.mFragment.setEditModeEnabled(true);
                        productFeedTileView.setProductSelected(true);
                        BaseProductFeedView.this.mAdapter.setSelectedIndex(productFeedTileView.getPosition());
                    }
                }
                return true;
            }
        });
        this.mGridView.setHeaderView(this.mSpacerView);
        this.mGridView.setOnViewVisibleListener(new OnViewVisibleListener() {
            public void onViewVisible(int i, View view) {
                WishProduct wishProduct = (WishProduct) BaseProductFeedView.this.mProducts.get(i);
                String productId = wishProduct.getProductId();
                if (!BaseProductFeedView.this.mVisibleProducts.contains(productId)) {
                    FeedTileLogger.getInstance().addToQueue(wishProduct.getLoggingFields(), Action.IMPRESSION, i, wishProduct.getVideoStatus().ordinal());
                    BaseProductFeedView.this.mVisibleProducts.add(productId);
                }
            }
        });
        setNoItemsMessage(WishApplication.getInstance().getString(R.string.no_products_found));
        updateRefresherOffset();
        updateSpacerHeight();
        initializeValues();
    }

    /* access modifiers changed from: protected */
    public BaseProductFeedAdapter initializeAdapter() {
        if (this.mFragment.getDataMode() != DataMode.Wishlist || (!ExperimentDataCenter.getInstance().shouldSeeProductDetailsInWishlist() && !ExperimentDataCenter.getInstance().shouldSeeAddToCartInWishlistTile())) {
            return new BaseProductFeedAdapter(this.mBaseActivity, this.mFragment, getNewMargins());
        }
        return new WishlistProductFeedAdapter(this.mBaseActivity, this.mFragment, getNewMargins());
    }

    /* access modifiers changed from: private */
    public void delegateScrollChanged(int i, int i2, int i3, int i4) {
        handleScrollChanged(i, i2);
        handleScrollLoad(i, i3, i4);
    }

    /* access modifiers changed from: protected */
    public void initializeValues() {
        Bundle savedInstanceState = this.mFragment.getSavedInstanceState(getDataIndex());
        if (savedInstanceState != null) {
            this.mFragment.clearSavedInstanceState(getDataIndex());
            if (savedInstanceState.getLong("SavedStateTimestamp") + 1800000 > System.currentTimeMillis()) {
                int i = savedInstanceState.getInt("SavedStateOffset");
                boolean z = savedInstanceState.getBoolean("SavedStateNoMoreItems");
                ArrayList parcelableList = StateStoreCache.getInstance().getParcelableList(savedInstanceState, "SavedStateData", WishProduct.class);
                this.mGridView.restorePosition(savedInstanceState.getInt("SavedStateFirstVisiblePosition"));
                if (parcelableList != null) {
                    handleLoadingSuccess(parcelableList, i, z);
                }
            }
            this.mTapToLoadCount = savedInstanceState.getInt("SavedStateTapToLoadCount");
            this.mTimedRefreshed = savedInstanceState.getBoolean("SavedStateTimedRefreshed", false);
        }
    }

    public Bundle getSavedInstanceState() {
        if (!isLoadingComplete() || this.mNeedsReload) {
            return null;
        }
        Bundle bundle = new Bundle();
        bundle.putString("SavedStateData", StateStoreCache.getInstance().storeParcelableList(this.mProducts));
        bundle.putBoolean("SavedStateNoMoreItems", this.mNoMoreItems);
        bundle.putInt("SavedStateOffset", this.mOffset);
        bundle.putLong("SavedStateTimestamp", this.mTimestamp);
        bundle.putInt("SavedStateFirstVisiblePosition", this.mGridView.getFirstItemPosition());
        bundle.putInt("SavedStateTapToLoadCount", this.mTapToLoadCount);
        bundle.putBoolean("SavedStateTimedRefreshed", this.mTimedRefreshed);
        return bundle;
    }

    public void handleReload() {
        clearView();
        loadNextPage();
    }

    public void clearView() {
        this.mNeedsReload = false;
        this.mProducts.clear();
        this.mProductIds.clear();
        this.mNoMoreItems = false;
        this.mNumTimesNoMoreItems = 0;
        this.mTimestamp = 0;
        this.mOffset = 0;
        if (this.mGridView != null) {
            this.mGridView.notifyDataSetChanged(true);
            this.mGridView.scrollTo(0, 0);
        }
    }

    /* access modifiers changed from: protected */
    public void loadNextPage() {
        clearError();
        BaseInitialProductWrapper initialProductInfo = this.mFragment.getInitialProductInfo(this.mDataIndex);
        if (initialProductInfo != null) {
            ArrayList arrayList = new ArrayList();
            Iterator it = initialProductInfo.initialProducts.iterator();
            while (it.hasNext()) {
                arrayList.add((WishProduct) it.next());
            }
            this.mFragment.handleLoadingSuccess(getDataIndex(), arrayList, initialProductInfo.nextOffset, initialProductInfo.noMoreItems);
            this.mFragment.clearInitialProductInfo(this.mDataIndex);
            return;
        }
        this.mFragment.loadProducts(getDataIndex(), this.mRequestId, this.mOffset);
    }

    public void handleResume() {
        boolean z = false;
        if (!isLoadingComplete() || this.mNeedsReload || this.mTimestamp + 1800000 < System.currentTimeMillis()) {
            if (isLoadingComplete() && !this.mNeedsReload && this.mTimestamp + 1800000 < System.currentTimeMillis()) {
                z = true;
            }
            this.mTimedRefreshed = z;
            reload();
            return;
        }
        this.mTimedRefreshed = false;
        loadMoreIfNecessary();
    }

    public void handleDestroy() {
        if (this.mImagePrefetcher != null) {
            this.mImagePrefetcher.cancelPrefetching();
        }
        if (this.mImagePrefetcher != null) {
            this.mImagePrefetcher.cancelPrefetching();
        }
        if (this.mGridView != null) {
            this.mGridView.teardown();
        }
    }

    public void cleanup() {
        handleDestroy();
    }

    public void refreshWishStates(boolean z) {
        if (this.mGridView != null && this.mGridView.getHeaderView() != null && (this.mGridView.getHeaderView() instanceof WishStateRefresher)) {
            ((WishStateRefresher) this.mGridView.getHeaderView()).refreshWishStates(z);
        }
    }

    public void releaseImages() {
        if (this.mGridView != null) {
            this.mGridView.releaseImages();
        }
        if (this.mImagePrefetcher != null) {
            this.mImagePrefetcher.pausePrefetching();
        }
        if (this.mImagePrefetcher != null) {
            this.mImagePrefetcher.pausePrefetching();
        }
    }

    public void restoreImages() {
        if (this.mGridView != null) {
            this.mGridView.restoreImages();
        }
        if (this.mImagePrefetcher != null) {
            this.mImagePrefetcher.resumePrefetching();
        }
        if (this.mImagePrefetcher != null) {
            this.mImagePrefetcher.resumePrefetching();
        }
    }

    public boolean hasItems() {
        return this.mProducts.size() > 0;
    }

    public boolean canPullToRefresh() {
        return this.mFragment.canFeedViewPullToRefresh();
    }

    public int getDataIndex() {
        return this.mDataIndex;
    }

    public void markNeedsReload() {
        this.mNeedsReload = true;
        prepareForReload();
    }

    private void handleScrollLoad(final int i, final int i2, final int i3) {
        this.mFragment.withServiceFragment(new ServiceTask<BaseActivity, BaseProductFeedServiceFragment>() {
            public void performTask(BaseActivity baseActivity, BaseProductFeedServiceFragment baseProductFeedServiceFragment) {
                if ((BaseProductFeedView.this.isLoadingComplete() && !BaseProductFeedView.this.isLoadingErrored() && !BaseProductFeedView.this.getNoMoreItems() && !baseProductFeedServiceFragment.isLoadingProducts(BaseProductFeedView.this.getDataIndex()) && BaseProductFeedView.this.mTapToLoadThreshold <= BaseProductFeedView.this.mTapToLoadCount) && i > i3 - (i2 * 4)) {
                    BaseProductFeedView.this.loadNextPage();
                }
            }
        });
    }

    public void loadMoreIfNecessary() {
        this.mFragment.withServiceFragment(new ServiceTask<BaseActivity, BaseProductFeedServiceFragment>() {
            public void performTask(BaseActivity baseActivity, BaseProductFeedServiceFragment baseProductFeedServiceFragment) {
                if (BaseProductFeedView.this.isLoadingComplete() && BaseProductFeedView.this.mProducts.size() < 10 && !BaseProductFeedView.this.getNoMoreItems() && !baseProductFeedServiceFragment.isLoadingProducts(BaseProductFeedView.this.getDataIndex())) {
                    BaseProductFeedView.this.loadNextPage();
                }
            }
        });
    }

    public void handleLoadingErrored() {
        markLoadingErrored();
    }

    public void handleLoadingSuccess(ArrayList<WishProduct> arrayList, int i, boolean z) {
        if (this.mTapToLoadCount < this.mTapToLoadThreshold) {
            setForceTapToLoad(true);
        }
        this.mNoMoreItems = z;
        if (arrayList.size() <= 0) {
            this.mNumTimesNoMoreItems++;
        } else {
            this.mNumTimesNoMoreItems = 0;
        }
        if (this.mNumTimesNoMoreItems >= 3) {
            this.mNoMoreItems = true;
        }
        if (this.mNoMoreItems) {
            markNoMoreItems();
        }
        this.mOffset = i;
        addProducts(arrayList);
        loadMoreIfNecessary();
    }

    public void addProducts(ArrayList<WishProduct> arrayList) {
        ArrayList filterProducts = filterProducts(arrayList);
        if (filterProducts != null) {
            this.mTimestamp = System.currentTimeMillis();
            Iterator it = filterProducts.iterator();
            while (it.hasNext()) {
                WishProduct wishProduct = (WishProduct) it.next();
                this.mProducts.add(wishProduct);
                this.mProductIds.add(wishProduct.getProductId());
                this.mImagePrefetcher.queueImage(wishProduct.getImage());
            }
            markLoadingComplete();
        }
        if (this.mGridView != null) {
            this.mGridView.notifyDataSetChanged();
        }
    }

    private ArrayList<WishProduct> filterProducts(ArrayList<WishProduct> arrayList) {
        if (arrayList == null || arrayList.size() == 0 || ExperimentDataCenter.getInstance().shouldDisableFilterDuplicateProducts()) {
            return arrayList;
        }
        HashSet hashSet = new HashSet();
        ArrayList<WishProduct> arrayList2 = new ArrayList<>();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            WishProduct wishProduct = (WishProduct) it.next();
            if (this.mProductIds.contains(wishProduct.getProductId()) || hashSet.contains(wishProduct.getProductId())) {
                Crashlytics.logException(new Exception("Duplicate product filtered"));
            } else {
                arrayList2.add(wishProduct);
                hashSet.add(wishProduct.getProductId());
            }
        }
        return arrayList2;
    }

    public void updateTabAreaOffset() {
        updateSpacerHeight();
        updateRefresherOffset();
    }

    /* access modifiers changed from: protected */
    public void updateSpacerHeight() {
        int tabAreaSize = this.mFragment.getTabAreaSize();
        LayoutParams layoutParams = this.mSpacerView.getLayoutParams();
        layoutParams.height = tabAreaSize;
        this.mSpacerView.setLayoutParams(layoutParams);
    }

    /* access modifiers changed from: protected */
    public void updateRefresherOffset() {
        int max = (int) Math.max(ValueUtil.convertDpToPx(45.0f), (float) (this.mFragment.getTabAreaSize() + getResources().getDimensionPixelSize(R.dimen.screen_padding)));
        setRefresherOffset(max);
        setErrorOffset(max);
        setLoadingOffset(max);
    }

    public void setEditModeEnabled(boolean z) {
        this.mGridView.setEditModeEnabled(z);
        this.mAdapter.setEditModeEnabled(z);
    }

    private void removeCollapsedViewIfNecessary() {
        View collapsedView = this.mCollapsableFeedHeaderView != null ? this.mCollapsableFeedHeaderView.getCollapsedView() : null;
        if (collapsedView != null) {
            removeView(collapsedView);
        }
    }

    private void setupCollapsedHeader(CollapsableFeedHeaderView collapsableFeedHeaderView) {
        removeCollapsedViewIfNecessary();
        this.mCollapsableFeedHeaderView = collapsableFeedHeaderView;
        View collapsedView = collapsableFeedHeaderView.getCollapsedView();
        if (collapsedView.getParent() == null) {
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2);
            layoutParams.gravity = 48;
            collapsedView.setLayoutParams(layoutParams);
            addView(collapsedView);
        }
    }

    public void setCustomHeaderViews(ArrayList<View> arrayList) {
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            View view = (View) it.next();
            if (view instanceof CollapsableFeedHeaderView) {
                CollapsableFeedHeaderView collapsableFeedHeaderView = (CollapsableFeedHeaderView) view;
                setupCollapsedHeader(collapsableFeedHeaderView);
                arrayList2.add(0, collapsableFeedHeaderView.getExpandedView());
            } else {
                arrayList2.add(view);
            }
        }
        this.mGridView.setHeaderView(this.mSpacerView, arrayList2);
    }

    public void setCustomHeaderView(View view) {
        if (view instanceof CollapsableFeedHeaderView) {
            CollapsableFeedHeaderView collapsableFeedHeaderView = (CollapsableFeedHeaderView) view;
            setupCollapsedHeader(collapsableFeedHeaderView);
            this.mGridView.setHeaderView(this.mSpacerView, collapsableFeedHeaderView.getExpandedView());
            return;
        }
        this.mGridView.setHeaderView(this.mSpacerView, view);
    }

    public View getCustomHeaderView() {
        if (this.mGridView != null) {
            return this.mGridView.getHeaderView();
        }
        return null;
    }

    public ArrayList<WishProduct> getSelectedProducts() {
        return this.mAdapter.getSelectedItems();
    }

    public ArrayList<WishProduct> getProducts() {
        return this.mProducts;
    }

    public void onPagerScrollUnsettled() {
        if (this.mPagerHelper != null) {
            this.mPagerHelper.onPagerScrollUnsettled();
        }
    }

    public void onPagerScrollSettled() {
        if (this.mPagerHelper != null) {
            this.mPagerHelper.onPagerScrollSettled();
        }
    }

    public void handleScrollChanged(int i, int i2) {
        if (this.mPagerHelper != null) {
            this.mPagerHelper.handleScrollChanged(i, i2);
        }
        if (this.mCollapsableFeedHeaderView != null) {
            this.mCollapsableFeedHeaderView.interpolate(Math.min((((float) getCurrentScrollY()) * -1.0f) / (((float) this.mCollapsableFeedHeaderView.getExpandedView().getHeight()) * 1.0f), 1.0f));
        }
    }

    public int getCurrentScrollY() {
        if (this.mGridView != null) {
            return this.mGridView.getScrollY();
        }
        return 0;
    }

    public void postDelayedTask(Runnable runnable, int i) {
        this.mGridView.postDelayed(runnable, (long) i);
    }

    public void setTapToLoadThreshold(int i) {
        this.mTapToLoadThreshold = i;
    }

    public void smoothScrollToTopAndReload() {
        this.mGridView.fling(0);
        this.mGridView.smoothScrollTo(0, 0);
        this.mGridView.setOnScrollListener(new OnScrollListener() {
            public void onScrollChanged(int i, int i2, int i3, int i4) {
                if (i == 0) {
                    BaseProductFeedView.this.reload();
                    BaseProductFeedView.this.mGridView.setOnScrollListener(BaseProductFeedView.this.defaultGridViewOnScrollListener());
                }
            }
        });
    }

    public OnScrollListener defaultGridViewOnScrollListener() {
        return new OnScrollListener() {
            public void onScrollChanged(int i, int i2, int i3, int i4) {
                BaseProductFeedView.this.delegateScrollChanged(i, i2, i3, i4);
            }
        };
    }

    /* access modifiers changed from: protected */
    public BasePagerHelper getPagerHelper() {
        return new BasePagerHelper(this.mFragment, this, getDataIndex());
    }

    public boolean isTimeRefreshed(boolean z) {
        boolean z2 = this.mTimedRefreshed;
        if (z) {
            this.mTimedRefreshed = false;
        }
        return z2;
    }

    public void notifyTabOffsetUpdated() {
        if (this.mCollapsableFeedHeaderView != null && this.mCollapsableFeedHeaderView.getCollapsedView().getLayoutParams() != null) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.mCollapsableFeedHeaderView.getCollapsedView().getLayoutParams();
            layoutParams.topMargin = this.mFragment.getTabAreaSize() + this.mFragment.getTabAreaOffset();
            this.mCollapsableFeedHeaderView.getCollapsedView().setLayoutParams(layoutParams);
        }
    }
}
