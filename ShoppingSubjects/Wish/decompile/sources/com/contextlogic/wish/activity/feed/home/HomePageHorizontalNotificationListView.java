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
import com.contextlogic.wish.api.datacenter.StatusDataCenter;
import com.contextlogic.wish.api.model.WishHomePageInfo;
import com.contextlogic.wish.api.model.WishHomePageInfo.HomePageNotificationItemHolder;
import com.contextlogic.wish.api.service.standalone.ViewNotificationService;
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

public class HomePageHorizontalNotificationListView extends RelativeLayout implements ImageRestorable {
    /* access modifiers changed from: private */
    public HashMap<String, String> mExtraInfo;
    private ProductFeedFragment mFragment;
    private HomePageNotificationAdapter mHomePageNotificationAdapter;
    private HorizontalListView mHorizontalListView;
    private View mLoadingView;
    /* access modifiers changed from: private */
    public ArrayList<HomePageNotificationItemHolder> mNotifications;
    private ThemedTextView mTitle;
    private View mViewAll;

    public HomePageHorizontalNotificationListView(Context context, ProductFeedFragment productFeedFragment) {
        this(context, null, productFeedFragment);
    }

    public HomePageHorizontalNotificationListView(Context context, AttributeSet attributeSet, ProductFeedFragment productFeedFragment) {
        super(context, attributeSet);
        this.mFragment = productFeedFragment;
        init();
    }

    private void init() {
        View inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.home_page_horizontal_list_view, this);
        this.mHorizontalListView = (HorizontalListView) inflate.findViewById(R.id.home_page_row_horizontal_list_view);
        LayoutParams layoutParams = new LayoutParams(-1, WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.home_page_notification_list_view_height));
        layoutParams.addRule(3, R.id.home_page_row_title);
        this.mHorizontalListView.setLayoutParams(layoutParams);
        this.mTitle = (ThemedTextView) inflate.findViewById(R.id.home_page_row_title);
        this.mTitle.setOnClickListener(handleRowTitleClick());
        this.mLoadingView = inflate.findViewById(R.id.home_page_row_loading_view);
        if (ExperimentDataCenter.getInstance().shouldSeeNewProgressBar()) {
            View findViewById = this.mLoadingView.findViewById(R.id.home_page_row_loading_progress_bar);
            View findViewById2 = this.mLoadingView.findViewById(R.id.home_page_row_loading_three_dot_progress_bar);
            if (!(findViewById2 == null || findViewById == null)) {
                findViewById.setVisibility(8);
                findViewById2.setVisibility(0);
            }
        }
        this.mViewAll = inflate.findViewById(R.id.home_page_row_view_all);
        this.mViewAll.setOnClickListener(handleRowTitleClick());
        LayoutParams layoutParams2 = new LayoutParams(-1, -2);
        setBackgroundColor(WishApplication.getInstance().getResources().getColor(R.color.white));
        layoutParams2.setMargins(0, WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.eight_padding), 0, 0);
        setLayoutParams(layoutParams2);
    }

    public void hideAllUiElements() {
        this.mLoadingView.setVisibility(8);
        this.mTitle.setVisibility(8);
        this.mHorizontalListView.setVisibility(8);
    }

    public void setup(WishHomePageInfo wishHomePageInfo, ImageHttpPrefetcher imageHttpPrefetcher) {
        hideAllUiElements();
        this.mNotifications = wishHomePageInfo.getNotifications();
        this.mTitle.setText(wishHomePageInfo.getNotificationsTitle());
        this.mHomePageNotificationAdapter = new HomePageNotificationAdapter(getContext(), this.mNotifications, this.mHorizontalListView, this.mFragment);
        this.mHomePageNotificationAdapter.setImagePrefetcher(imageHttpPrefetcher);
        this.mHorizontalListView.setAdapter(this.mHomePageNotificationAdapter);
        this.mHorizontalListView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(int i, View view) {
                HomePageHorizontalNotificationListView.this.handleCellClick(i, HomePageHorizontalNotificationListView.this.mNotifications);
            }
        });
        this.mHorizontalListView.notifyDataSetChanged();
        this.mExtraInfo = new HashMap<>();
        this.mExtraInfo.put("row_id", Integer.toString(wishHomePageInfo.getNotificationRowId()));
        if (wishHomePageInfo.getNotificationViewAllDeepLink() == null || wishHomePageInfo.getNotificationViewAllDeepLink().trim().equals("")) {
            this.mViewAll.setVisibility(8);
        }
        this.mTitle.setVisibility(0);
        this.mHorizontalListView.setVisibility(0);
    }

    public void startLoading(WishHomePageInfo wishHomePageInfo) {
        this.mTitle.setText(wishHomePageInfo.getNotificationsTitle());
        LayoutParams layoutParams = (LayoutParams) this.mLoadingView.getLayoutParams();
        layoutParams.height = WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.home_page_notification_list_view_height) + HomePageView.getRowTitleHeight() + WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.double_screen_padding) + WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.four_padding);
        this.mLoadingView.setLayoutParams(layoutParams);
        this.mLoadingView.setVisibility(0);
        this.mHorizontalListView.setVisibility(0);
    }

    public void processDeepLink(final String str) {
        this.mFragment.withActivity(new ActivityTask<BaseActivity>() {
            public void performTask(BaseActivity baseActivity) {
                DeepLinkManager.processDeepLink(baseActivity, new DeepLink(str));
            }
        });
    }

    private OnClickListener handleRowTitleClick() {
        return new OnClickListener() {
            public void onClick(View view) {
                HomePageHorizontalNotificationListView.this.processDeepLink(WishHomePageInfo.getInstance().getNotificationViewAllDeepLink());
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_HOME_PAGE_ROW_TITLE, (String) null, HomePageHorizontalNotificationListView.this.mExtraInfo);
            }
        };
    }

    /* access modifiers changed from: private */
    public void handleCellClick(int i, ArrayList<HomePageNotificationItemHolder> arrayList) {
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_HOME_PAGE_ROW_CELL, (String) null, this.mExtraInfo);
        processDeepLink(((HomePageNotificationItemHolder) arrayList.get(i)).getDeepLink());
        new ViewNotificationService().requestService(((HomePageNotificationItemHolder) arrayList.get(i)).getNotificationId(), ((HomePageNotificationItemHolder) arrayList.get(i)).getBucketId(), null, null);
        StatusDataCenter.getInstance().decrementUnviewedNotificationCount();
        StatusDataCenter.getInstance().refresh();
    }

    public void releaseImages() {
        if (this.mHomePageNotificationAdapter != null) {
            this.mHomePageNotificationAdapter.releaseImages();
        }
    }

    public void restoreImages() {
        if (this.mHomePageNotificationAdapter != null) {
            this.mHomePageNotificationAdapter.restoreImages();
        }
    }
}
