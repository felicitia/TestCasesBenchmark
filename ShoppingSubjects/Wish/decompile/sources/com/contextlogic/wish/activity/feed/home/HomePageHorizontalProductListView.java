package com.contextlogic.wish.activity.feed.home;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.feed.ProductFeedFragment;
import com.contextlogic.wish.activity.productdetails.ProductDetailsActivity;
import com.contextlogic.wish.analytics.FeedTileLogger;
import com.contextlogic.wish.analytics.FeedTileLogger.Action;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.WishHomePageInfo.HomePageProductListItemHolder;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.http.ImageHttpPrefetcher;
import com.contextlogic.wish.link.DeepLink;
import com.contextlogic.wish.link.DeepLinkManager;
import com.contextlogic.wish.ui.image.ImageRestorable;
import com.contextlogic.wish.ui.listview.HorizontalListView;
import com.contextlogic.wish.ui.listview.HorizontalListView.OnItemClickListener;
import com.contextlogic.wish.ui.listview.HorizontalListView.OnViewVisibleListener;
import com.contextlogic.wish.ui.text.ThemedTextView;
import java.util.HashMap;
import java.util.HashSet;

public class HomePageHorizontalProductListView extends RelativeLayout implements ImageRestorable {
    private HashMap<String, String> mExtraInfo;
    /* access modifiers changed from: private */
    public int mFilteredTabPosition;
    /* access modifiers changed from: private */
    public final ProductFeedFragment mFragment;
    /* access modifiers changed from: private */
    public HomePageProductAdapter mHomePageProductAdapter;
    private HorizontalListView mHorizontalListView;
    private View mLoadingView;
    private boolean mShowDetailedView;
    private ThemedTextView mTitle;
    private ThemedTextView mViewAll;
    /* access modifiers changed from: private */
    public HashSet<String> mVisibleProducts;

    public HomePageHorizontalProductListView(Context context, ProductFeedFragment productFeedFragment, boolean z) {
        this(context, null, productFeedFragment, z);
    }

    public HomePageHorizontalProductListView(Context context, AttributeSet attributeSet, ProductFeedFragment productFeedFragment, boolean z) {
        super(context, attributeSet);
        this.mFragment = productFeedFragment;
        this.mShowDetailedView = z;
        this.mVisibleProducts = new HashSet<>();
        init();
    }

    private OnClickListener handleRowTitleClick(final HomePageProductListItemHolder homePageProductListItemHolder) {
        return new OnClickListener() {
            public void onClick(View view) {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_HOME_PAGE_ROW_TITLE, (String) null, HomePageHorizontalProductListView.this.getExtraInfo());
                if (HomePageHorizontalProductListView.this.mFilteredTabPosition != -1) {
                    HomePageHorizontalProductListView.this.mFragment.setSelectedTab(HomePageHorizontalProductListView.this.mFilteredTabPosition);
                } else if (homePageProductListItemHolder.getDeeplink() != null) {
                    HomePageHorizontalProductListView.this.processDeepLink(homePageProductListItemHolder.getDeeplink());
                }
            }
        };
    }

    private void init() {
        View inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.home_page_horizontal_list_view, this);
        this.mHorizontalListView = (HorizontalListView) inflate.findViewById(R.id.home_page_row_horizontal_list_view);
        this.mTitle = (ThemedTextView) inflate.findViewById(R.id.home_page_row_title);
        this.mViewAll = (ThemedTextView) inflate.findViewById(R.id.home_page_row_view_all);
        this.mLoadingView = inflate.findViewById(R.id.home_page_row_loading_view);
        if (ExperimentDataCenter.getInstance().shouldSeeNewProgressBar()) {
            View findViewById = this.mLoadingView.findViewById(R.id.home_page_row_loading_progress_bar);
            View findViewById2 = this.mLoadingView.findViewById(R.id.home_page_row_loading_three_dot_progress_bar);
            if (!(findViewById2 == null || findViewById == null)) {
                findViewById.setVisibility(8);
                findViewById2.setVisibility(0);
            }
        }
        if (this.mShowDetailedView) {
            LayoutParams layoutParams = new LayoutParams(-1, WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.home_page_detailed_product_list_view_height));
            layoutParams.addRule(3, R.id.home_page_row_title);
            this.mHorizontalListView.setLayoutParams(layoutParams);
        } else {
            LayoutParams layoutParams2 = new LayoutParams(-1, WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.home_page_product_cell_view_height));
            layoutParams2.addRule(3, R.id.home_page_row_title);
            this.mHorizontalListView.setLayoutParams(layoutParams2);
        }
        LayoutParams layoutParams3 = new LayoutParams(-1, -2);
        setBackgroundColor(WishApplication.getInstance().getResources().getColor(R.color.white));
        layoutParams3.setMargins(0, WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.eight_padding), 0, 0);
        setLayoutParams(layoutParams3);
    }

    public void setup(final HomePageProductListItemHolder homePageProductListItemHolder, ImageHttpPrefetcher imageHttpPrefetcher) {
        this.mLoadingView.setVisibility(8);
        String filterFeedId = homePageProductListItemHolder.getFilterFeedId();
        if (filterFeedId == null) {
            this.mFilteredTabPosition = -1;
        } else {
            this.mFilteredTabPosition = this.mFragment.findPositionFromFilterId(filterFeedId);
        }
        this.mHomePageProductAdapter = new HomePageProductAdapter(getContext(), homePageProductListItemHolder.getProducts(), this.mHorizontalListView, this.mShowDetailedView);
        this.mHomePageProductAdapter.setImagePrefetcher(imageHttpPrefetcher);
        this.mHorizontalListView.setAdapter(this.mHomePageProductAdapter);
        this.mHorizontalListView.notifyDataSetChanged();
        this.mExtraInfo = new HashMap<>();
        this.mExtraInfo.put("row_id", Integer.toString(homePageProductListItemHolder.getRowId()));
        this.mHorizontalListView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(int i, View view) {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_HOME_PAGE_ROW_CELL, (String) null, HomePageHorizontalProductListView.this.getExtraInfo());
                FeedTileLogger.getInstance().addToQueue(HomePageHorizontalProductListView.this.mHomePageProductAdapter.getItem(i).getLoggingFields(), Action.CLICKED, i);
                if (homePageProductListItemHolder.isTileRedirectsToViewAll()) {
                    HomePageHorizontalProductListView.this.processDeepLink(homePageProductListItemHolder.getDeeplink());
                } else {
                    HomePageHorizontalProductListView.this.showProduct(HomePageHorizontalProductListView.this.mHomePageProductAdapter.getItem(i));
                }
            }
        });
        this.mHorizontalListView.setOnViewVisibleListener(new OnViewVisibleListener() {
            public void onViewVisible(int i, View view) {
                WishProduct item = HomePageHorizontalProductListView.this.mHomePageProductAdapter.getItem(i);
                String productId = item.getProductId();
                if (!HomePageHorizontalProductListView.this.mVisibleProducts.contains(productId)) {
                    FeedTileLogger.getInstance().addToQueue(item.getLoggingFields(), Action.IMPRESSION, i);
                    HomePageHorizontalProductListView.this.mVisibleProducts.add(productId);
                }
            }
        });
        if ((homePageProductListItemHolder.getDeeplink() == null || homePageProductListItemHolder.getDeeplink().trim().equals("")) && this.mFilteredTabPosition == -1) {
            this.mViewAll.setVisibility(8);
            return;
        }
        this.mTitle.setOnClickListener(handleRowTitleClick(homePageProductListItemHolder));
        this.mViewAll.setOnClickListener(handleRowTitleClick(homePageProductListItemHolder));
    }

    public void processDeepLink(final String str) {
        this.mFragment.withActivity(new ActivityTask<BaseActivity>() {
            public void performTask(BaseActivity baseActivity) {
                DeepLinkManager.processDeepLink(baseActivity, new DeepLink(str));
            }
        });
    }

    /* access modifiers changed from: private */
    public void showProduct(WishProduct wishProduct) {
        Intent intent = new Intent();
        intent.setClass(getContext(), ProductDetailsActivity.class);
        ProductDetailsActivity.addInitialProduct(intent, wishProduct);
        getContext().startActivity(intent);
    }

    public HashMap<String, String> getExtraInfo() {
        return this.mExtraInfo;
    }

    public void startLoading(HomePageProductListItemHolder homePageProductListItemHolder) {
        this.mTitle.setText(homePageProductListItemHolder.getTitle());
        if (homePageProductListItemHolder.getViewAllText() == null || homePageProductListItemHolder.getViewAllText().isEmpty()) {
            this.mViewAll.setVisibility(8);
        } else {
            this.mViewAll.setText(homePageProductListItemHolder.getViewAllText());
        }
        LayoutParams layoutParams = (LayoutParams) this.mLoadingView.getLayoutParams();
        layoutParams.height = HomePageView.getRowTitleHeight() + WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.double_screen_padding) + WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.four_padding);
        if (this.mShowDetailedView) {
            layoutParams.height += WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.home_page_detailed_product_list_view_height);
        } else {
            layoutParams.height += WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.home_page_product_list_view_height);
        }
        this.mLoadingView.setLayoutParams(layoutParams);
        this.mLoadingView.setVisibility(0);
        if (homePageProductListItemHolder.getFilterFeedId() != null) {
            this.mFilteredTabPosition = this.mFragment.findPositionFromFilterId(homePageProductListItemHolder.getFilterFeedId());
        }
    }

    public void releaseImages() {
        if (this.mHomePageProductAdapter != null) {
            this.mHomePageProductAdapter.releaseImages();
        }
    }

    public void restoreImages() {
        if (this.mHomePageProductAdapter != null) {
            this.mHomePageProductAdapter.restoreImages();
        }
    }
}
