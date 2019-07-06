package com.contextlogic.wish.activity.feed.recentlyviewed;

import android.annotation.SuppressLint;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.view.View;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.feed.BaseProductFeedServiceFragment;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.MerchantFeedItem;
import com.contextlogic.wish.ui.bottomnavigation.BottomNavigationHelper;
import com.contextlogic.wish.ui.bottomnavigation.BottomNavigationInterface;
import com.contextlogic.wish.ui.image.ImageRestorable;
import com.contextlogic.wish.ui.loading.LoadingPageView;
import com.contextlogic.wish.ui.loading.LoadingPageView.LoadingPageManager;
import com.contextlogic.wish.ui.recyclerview.infinitescroll.FixedInfiniteDataPager;
import com.contextlogic.wish.ui.recyclerview.infinitescroll.InfiniteDataFetcher;
import com.contextlogic.wish.ui.recyclerview.infinitescroll.InfiniteDataPager;
import com.contextlogic.wish.ui.recyclerview.infinitescroll.InfiniteDataPagerCallback;
import com.contextlogic.wish.ui.recyclerview.infinitescroll.InfiniteScrollListener;
import com.contextlogic.wish.ui.recyclerview.infinitescroll.InfiniteScrollListener.LoadMoreCallback;
import java.util.List;

@SuppressLint({"ViewConstructor"})
public abstract class BaseMerchantFeedView extends LoadingPageView implements ImageRestorable, LoadingPageManager {
    /* access modifiers changed from: private */
    public MerchantCellAdapter mAdapter;
    /* access modifiers changed from: private */
    public BottomNavigationHelper mBottomNavigationHelper;
    private InfiniteDataPager mDataPager;
    private RecyclerView mRecyclerView;
    private BaseProductFeedServiceFragment mServiceFragment;

    public boolean canPullToRefresh() {
        return true;
    }

    /* access modifiers changed from: protected */
    public abstract InfiniteDataFetcher<MerchantFeedItem> createPagingFetcher(BaseProductFeedServiceFragment baseProductFeedServiceFragment);

    /* access modifiers changed from: protected */
    public abstract int getEmptyText();

    public int getLoadingContentLayoutResourceId() {
        return R.layout.recently_viewed_merchant_view;
    }

    public void releaseImages() {
    }

    public void restoreImages() {
    }

    public BaseMerchantFeedView(BaseProductFeedServiceFragment baseProductFeedServiceFragment) {
        super(baseProductFeedServiceFragment.getContext());
        this.mServiceFragment = baseProductFeedServiceFragment;
        init();
    }

    private void init() {
        setErrorOffset(0);
        setLoadingOffset(0);
        setNoItemsMessage(getResources().getString(getEmptyText()));
        setupPaging();
        setLoadingPageManager(this);
        reload();
    }

    private void setupPaging() {
        FixedInfiniteDataPager fixedInfiniteDataPager = new FixedInfiniteDataPager();
        fixedInfiniteDataPager.setCallback(new InfiniteDataPagerCallback<MerchantFeedItem>() {
            public void onLoadMoreComplete(boolean z, int i, int i2, List<MerchantFeedItem> list) {
                if (BaseMerchantFeedView.this.getContext() != null && BaseMerchantFeedView.this.getHandler() != null) {
                    BaseMerchantFeedView.this.mAdapter.setLoading(false);
                    if (i == 0) {
                        BaseMerchantFeedView.this.mAdapter.setValues(MerchantCellAdapter.transformToItemModels(list));
                        if (z) {
                            BaseMerchantFeedView.this.markLoadingErrored();
                        } else {
                            BaseMerchantFeedView.this.markLoadingComplete();
                        }
                    } else {
                        BaseMerchantFeedView.this.mAdapter.appendValues(MerchantCellAdapter.transformToItemModels(list));
                    }
                    if (list.isEmpty()) {
                        BaseMerchantFeedView.this.getHandler().post(new Runnable() {
                            public void run() {
                                BaseMerchantFeedView.this.loadMore();
                            }
                        });
                    }
                    if (!list.isEmpty()) {
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_MERCHANT_FEED_ITEM_PAGE);
                    }
                }
            }
        });
        fixedInfiniteDataPager.setDataFetcher(createPagingFetcher(this.mServiceFragment));
        this.mDataPager = fixedInfiniteDataPager;
    }

    public void handleReload() {
        this.mAdapter.setLoading(false);
        this.mDataPager.reset();
        this.mDataPager.fetchMore();
    }

    /* access modifiers changed from: private */
    public void loadMore() {
        if (!this.mDataPager.isLoading() && !this.mDataPager.hasNoMoreItems()) {
            this.mAdapter.setLoading(true);
            this.mDataPager.fetchMore();
        }
    }

    public void initializeLoadingContentView(View view) {
        this.mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        this.mAdapter = new MerchantCellAdapter();
        this.mRecyclerView.setAdapter(this.mAdapter);
        InfiniteScrollListener infiniteScrollListener = new InfiniteScrollListener(InfiniteScrollListener.LINEAR_LAYOUT_HELPER);
        infiniteScrollListener.setLoadMoreCallback(new LoadMoreCallback() {
            public void onLoadMore() {
                BaseMerchantFeedView.this.loadMore();
            }
        });
        this.mRecyclerView.addOnScrollListener(infiniteScrollListener);
    }

    public void setBottomNavigationInterface(BottomNavigationInterface bottomNavigationInterface) {
        if (ExperimentDataCenter.getInstance().shouldShowBottomNavigation() && bottomNavigationInterface != null) {
            this.mBottomNavigationHelper = new BottomNavigationHelper(bottomNavigationInterface);
            this.mRecyclerView.addOnScrollListener(new OnScrollListener() {
                public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                    BaseMerchantFeedView.this.mBottomNavigationHelper.handleScrollChanged(i2);
                }
            });
        }
    }

    public boolean hasItems() {
        return !this.mDataPager.hasNoMoreItems();
    }
}
