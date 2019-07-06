package com.contextlogic.wish.activity.feed.search;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.feed.ProductFeedFragment;
import com.contextlogic.wish.activity.feed.ProductFeedFragment.DataMode;
import com.contextlogic.wish.activity.search.SearchActivity;
import com.contextlogic.wish.activity.search.SearchBarCallback;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.WishFilter;
import com.contextlogic.wish.api.service.standalone.GetFilteredFeedService.FeedExtraInfo;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.util.KeyboardUtil;
import java.util.ArrayList;
import java.util.Arrays;

public class SearchFeedFragment extends ProductFeedFragment implements SearchBarCallback {
    /* access modifiers changed from: private */
    public String mQuery;
    private boolean[] mReloaded;
    private boolean mShowTabs;

    public CursorAdapter getSearchTypeaheadAdapter() {
        return null;
    }

    public void handleSearchTypeaheadClick(int i) {
    }

    public boolean isFeedFilterable() {
        return false;
    }

    public void onQueryChanged(String str) {
    }

    public void initializeLoadingContentView(View view) {
        this.mQuery = ((SearchFeedActivity) getBaseActivity()).getQuery();
        this.mShowTabs = ExperimentDataCenter.getInstance().shouldSeeWishExpressSearchTabs();
        this.mReloaded = new boolean[2];
        super.initializeLoadingContentView(view);
    }

    /* access modifiers changed from: protected */
    public void initializeValues() {
        super.initializeValues();
        withActivity(new ActivityTask<SearchFeedActivity>() {
            public void performTask(SearchFeedActivity searchFeedActivity) {
                ActionBar supportActionBar = searchFeedActivity.getSupportActionBar();
                if (supportActionBar != null) {
                    View customView = supportActionBar.getCustomView();
                    if (customView != null) {
                        customView.setOnClickListener(new OnClickListener() {
                            public void onClick(View view) {
                                Intent intent = new Intent();
                                intent.setClass(SearchFeedFragment.this.getBaseActivity(), SearchActivity.class);
                                intent.setFlags(67108864);
                                intent.putExtra(SearchActivity.EXTRA_QUERY, SearchFeedFragment.this.mQuery);
                                SearchFeedFragment.this.getBaseActivity().startActivity(intent);
                            }
                        });
                    }
                }
            }
        });
        if (this.mShowTabs) {
            FeedExtraInfo feedExtraInfo = new FeedExtraInfo();
            feedExtraInfo.mainCategories = new ArrayList<>();
            feedExtraInfo.mainCategories.add(new WishFilter("all_results__tab", WishApplication.getInstance().getString(R.string.all_results)));
            feedExtraInfo.mainCategories.add(new WishFilter("wish_express__tab", ""));
            updateMainCategories(feedExtraInfo);
        }
    }

    /* access modifiers changed from: protected */
    public void handleTabSelected(int i, boolean z) {
        super.handleTabSelected(i, z);
        applyNewQuery(i);
    }

    public boolean canShowFeedCategories() {
        return this.mShowTabs;
    }

    public DataMode getDataMode() {
        return DataMode.Search;
    }

    /* access modifiers changed from: protected */
    public String getMainRequestId() {
        return this.mQuery;
    }

    public void onSearchSubmit(String str) {
        if (str != null && !TextUtils.isEmpty(str.trim())) {
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_SEARCH);
            if (!this.mQuery.equals(str)) {
                this.mQuery = str;
                Arrays.fill(this.mReloaded, false);
            }
            applyNewQuery(getCurrentIndex());
        }
    }

    private void applyNewQuery(int i) {
        KeyboardUtil.hideKeyboard((Activity) getBaseActivity());
        if (!this.mReloaded[i]) {
            applyFilter();
            this.mReloaded[i] = true;
        }
    }
}
