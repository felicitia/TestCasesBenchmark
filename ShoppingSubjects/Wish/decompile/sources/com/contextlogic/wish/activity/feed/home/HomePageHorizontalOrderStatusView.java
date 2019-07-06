package com.contextlogic.wish.activity.feed.home;

import android.content.Context;
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
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.WishHomePageInfo;
import com.contextlogic.wish.api.model.WishHomePageInfo.HomePageOrderStatusItemHolder;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.http.ImageHttpPrefetcher;
import com.contextlogic.wish.link.DeepLink;
import com.contextlogic.wish.link.DeepLinkManager;
import com.contextlogic.wish.ui.image.ImageRestorable;
import com.contextlogic.wish.ui.listview.HorizontalListView;
import com.contextlogic.wish.ui.listview.HorizontalListView.OnItemClickListener;
import com.contextlogic.wish.ui.text.ThemedTextView;
import java.util.ArrayList;
import java.util.HashMap;

public class HomePageHorizontalOrderStatusView extends RelativeLayout implements ImageRestorable {
    private String mDeeplink;
    private HashMap<String, String> mExtraInfo;
    private ProductFeedFragment mFragment;
    private HomePageOrderStatusAdapter mHomePageOrderStatusAdapter;
    private HorizontalListView mHorizontalListView;
    private View mLoadingView;
    /* access modifiers changed from: private */
    public ArrayList<HomePageOrderStatusItemHolder> mOrderStatuses;
    private ThemedTextView mTitle;

    public HomePageHorizontalOrderStatusView(Context context, ProductFeedFragment productFeedFragment) {
        this(context, null, productFeedFragment);
    }

    public HomePageHorizontalOrderStatusView(Context context, AttributeSet attributeSet, ProductFeedFragment productFeedFragment) {
        super(context, attributeSet);
        this.mFragment = productFeedFragment;
        init();
    }

    private OnClickListener handleRowTitleClick() {
        return new OnClickListener() {
            public void onClick(View view) {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_HOME_PAGE_ROW_TITLE, (String) null, HomePageHorizontalOrderStatusView.this.getExtraInfo());
                HomePageHorizontalOrderStatusView.this.processDeepLink(HomePageHorizontalOrderStatusView.this.getViewAllDeepLink());
            }
        };
    }

    private void init() {
        View inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.home_page_horizontal_list_view, this);
        this.mHorizontalListView = (HorizontalListView) inflate.findViewById(R.id.home_page_row_horizontal_list_view);
        LayoutParams layoutParams = new LayoutParams(-1, WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.home_page_order_status_list_view_height));
        layoutParams.addRule(3, R.id.home_page_row_title);
        this.mHorizontalListView.setLayoutParams(layoutParams);
        this.mTitle = (ThemedTextView) inflate.findViewById(R.id.home_page_row_title);
        this.mTitle.setOnClickListener(handleRowTitleClick());
        inflate.findViewById(R.id.home_page_row_view_all).setOnClickListener(handleRowTitleClick());
        this.mLoadingView = inflate.findViewById(R.id.home_page_row_loading_view);
        if (ExperimentDataCenter.getInstance().shouldSeeNewProgressBar()) {
            View findViewById = this.mLoadingView.findViewById(R.id.home_page_row_loading_progress_bar);
            View findViewById2 = this.mLoadingView.findViewById(R.id.home_page_row_loading_three_dot_progress_bar);
            if (!(findViewById2 == null || findViewById == null)) {
                findViewById.setVisibility(8);
                findViewById2.setVisibility(0);
            }
        }
        LayoutParams layoutParams2 = new LayoutParams(-1, -2);
        setBackgroundColor(WishApplication.getInstance().getResources().getColor(R.color.white));
        layoutParams2.setMargins(0, WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.four_padding), 0, 0);
        setLayoutParams(layoutParams2);
    }

    public void setup(WishHomePageInfo wishHomePageInfo, ImageHttpPrefetcher imageHttpPrefetcher) {
        hideAllUiElements();
        this.mOrderStatuses = wishHomePageInfo.getOrderStatuses();
        this.mTitle.setText(wishHomePageInfo.getOrderStatusesTitle());
        this.mHomePageOrderStatusAdapter = new HomePageOrderStatusAdapter(getContext(), this.mOrderStatuses, this.mHorizontalListView);
        this.mHomePageOrderStatusAdapter.setImagePrefetcher(imageHttpPrefetcher);
        this.mHorizontalListView.setAdapter(this.mHomePageOrderStatusAdapter);
        this.mHorizontalListView.notifyDataSetChanged();
        this.mDeeplink = wishHomePageInfo.getOrderStatusesDeepLink();
        this.mExtraInfo = new HashMap<>();
        this.mExtraInfo.put("row_id", Integer.toString(wishHomePageInfo.getOrderStatusRowId()));
        this.mTitle.setVisibility(0);
        this.mHorizontalListView.setVisibility(0);
        this.mHorizontalListView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(int i, View view) {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_HOME_PAGE_ROW_CELL, (String) null, HomePageHorizontalOrderStatusView.this.getExtraInfo());
                HomePageHorizontalOrderStatusView.this.processDeepLink(((HomePageOrderStatusItemHolder) HomePageHorizontalOrderStatusView.this.mOrderStatuses.get(i)).getDeepLink());
            }
        });
    }

    public void processDeepLink(final String str) {
        if (str != null) {
            this.mFragment.withActivity(new ActivityTask<BaseActivity>() {
                public void performTask(BaseActivity baseActivity) {
                    DeepLinkManager.processDeepLink(baseActivity, new DeepLink(str));
                }
            });
        }
    }

    public String getViewAllDeepLink() {
        return this.mDeeplink;
    }

    public HashMap<String, String> getExtraInfo() {
        return this.mExtraInfo;
    }

    public void hideAllUiElements() {
        this.mLoadingView.setVisibility(8);
        this.mTitle.setVisibility(8);
        this.mHorizontalListView.setVisibility(8);
    }

    public void startLoading(WishHomePageInfo wishHomePageInfo) {
        this.mTitle.setText(wishHomePageInfo.getOrderStatusesTitle());
        LayoutParams layoutParams = (LayoutParams) this.mLoadingView.getLayoutParams();
        layoutParams.height = WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.home_page_order_status_list_view_height) + HomePageView.getRowTitleHeight();
        this.mLoadingView.setLayoutParams(layoutParams);
        this.mLoadingView.setVisibility(0);
        this.mHorizontalListView.setVisibility(0);
        this.mDeeplink = wishHomePageInfo.getOrderStatusesDeepLink();
    }

    public void releaseImages() {
        if (this.mHomePageOrderStatusAdapter != null) {
            this.mHomePageOrderStatusAdapter.releaseImages();
        }
    }

    public void restoreImages() {
        if (this.mHomePageOrderStatusAdapter != null) {
            this.mHomePageOrderStatusAdapter.restoreImages();
        }
    }
}
