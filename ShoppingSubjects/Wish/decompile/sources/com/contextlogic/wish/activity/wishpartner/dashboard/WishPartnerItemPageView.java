package com.contextlogic.wish.activity.wishpartner.dashboard;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseFragment;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.wishpartner.learnmore.WishPartnerLearnMoreActivity;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.WishPartnerSummary.WishPartnerEvent;
import com.contextlogic.wish.api.model.WishPartnerSummary.WishPartnerMainEmptyStateSpec;
import com.contextlogic.wish.ui.text.ThemedTextView;
import java.util.ArrayList;

public class WishPartnerItemPageView extends LinearLayout {
    /* access modifiers changed from: private */
    public WishPartnerDashboardItemAdapter mAdapter;
    /* access modifiers changed from: private */
    public ThemedTextView mEmptyState;
    private ThemedTextView mEmptyStateButton;
    private ThemedTextView mEmptyStateSubtitle;
    private ThemedTextView mEmptyStateTitle;
    /* access modifiers changed from: private */
    public BaseFragment mFragment;
    private Handler mHandler;
    /* access modifiers changed from: private */
    public LinearLayoutManager mLayoutManager;
    /* access modifiers changed from: private */
    public WishPartnerFeedInterface mListener;
    /* access modifiers changed from: private */
    public View mMainEmptyState;
    /* access modifiers changed from: private */
    public View mProgressBar;
    /* access modifiers changed from: private */
    public RecyclerView mRecyclerView;
    private boolean mReload;
    private boolean mShowMainEmptyState;

    public interface WishPartnerFeedInterface {
        void loadTabData();
    }

    public WishPartnerItemPageView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(getContext()).inflate(R.layout.wish_partner_dashboard_page_view, this, true);
        this.mReload = true;
        this.mLayoutManager = new LinearLayoutManager(context);
        this.mRecyclerView = (RecyclerView) findViewById(R.id.wish_partner_dashboard_page_recycler_view);
        this.mEmptyState = (ThemedTextView) findViewById(R.id.wish_partner_dashboard_page_empty_state);
        this.mMainEmptyState = findViewById(R.id.wish_partner_dashboard_main_empty_state);
        this.mEmptyStateTitle = (ThemedTextView) findViewById(R.id.wish_partner_dashboard_main_empty_state_title);
        this.mEmptyStateSubtitle = (ThemedTextView) findViewById(R.id.wish_partner_dashboard_main_empty_state_subtitle);
        this.mEmptyStateButton = (ThemedTextView) findViewById(R.id.wish_partner_dashboard_main_empty_state_learn_more_button);
        this.mRecyclerView.setLayoutManager(this.mLayoutManager);
        this.mProgressBar = findViewById(R.id.wish_partner_dashboard_page_progress);
        this.mHandler = new Handler();
        if (ExperimentDataCenter.getInstance().shouldSeeNewProgressBar()) {
            View findViewById = this.mProgressBar.findViewById(R.id.wish_partner_dashboard_page_progress_bar);
            View findViewById2 = this.mProgressBar.findViewById(R.id.wish_partner_dashboard_three_dot_progress);
            if (!(findViewById2 == null || findViewById == null)) {
                findViewById.setVisibility(8);
                findViewById2.setVisibility(0);
            }
        }
        this.mRecyclerView.setOnScrollListener(new OnScrollListener() {
            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                if (i2 > 0) {
                    if (WishPartnerItemPageView.this.mLayoutManager.getChildCount() + WishPartnerItemPageView.this.mLayoutManager.findFirstVisibleItemPosition() >= WishPartnerItemPageView.this.mLayoutManager.getItemCount() && WishPartnerItemPageView.this.mListener != null) {
                        WishPartnerItemPageView.this.setLoading(true);
                        WishPartnerItemPageView.this.mListener.loadTabData();
                    }
                }
            }
        });
    }

    public void setLoading(final boolean z) {
        if (this.mHandler != null) {
            this.mHandler.post(new Runnable() {
                public void run() {
                    WishPartnerItemPageView.this.mAdapter.setLoading(z);
                }
            });
        }
    }

    public void setup(boolean z, WishPartnerFeedInterface wishPartnerFeedInterface, boolean z2, BaseFragment baseFragment) {
        this.mListener = wishPartnerFeedInterface;
        setVisibility(0);
        this.mFragment = baseFragment;
        this.mAdapter = new WishPartnerDashboardItemAdapter();
        this.mRecyclerView.setAdapter(this.mAdapter);
        this.mShowMainEmptyState = z2;
        if (z) {
            this.mAdapter.setLoading(true);
            this.mProgressBar.setVisibility(0);
            this.mRecyclerView.setVisibility(8);
            return;
        }
        this.mAdapter.setLoading(false);
        this.mProgressBar.setVisibility(8);
        this.mRecyclerView.setVisibility(0);
    }

    public void handleLoadingSuccess(ArrayList<WishPartnerEvent> arrayList, WishPartnerMainEmptyStateSpec wishPartnerMainEmptyStateSpec) {
        this.mShowMainEmptyState = this.mShowMainEmptyState && wishPartnerMainEmptyStateSpec != null;
        if (this.mShowMainEmptyState) {
            this.mEmptyStateTitle.setText(wishPartnerMainEmptyStateSpec.getTitle());
            this.mEmptyStateSubtitle.setText(wishPartnerMainEmptyStateSpec.getSubtitle());
            this.mEmptyStateButton.setText(wishPartnerMainEmptyStateSpec.getActionText());
        }
        this.mEmptyStateButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                WishPartnerItemPageView.this.mFragment.withActivity(new ActivityTask<WishPartnerDashboardActivity>() {
                    public void performTask(WishPartnerDashboardActivity wishPartnerDashboardActivity) {
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_WISH_PARTNER_EMPTY_STATE_LEARN_MORE);
                        Intent intent = new Intent();
                        intent.setClass(wishPartnerDashboardActivity, WishPartnerLearnMoreActivity.class);
                        wishPartnerDashboardActivity.startActivity(intent);
                    }
                });
            }
        });
        handleLoadingSuccess(arrayList);
    }

    public void handleLoadingSuccess(ArrayList<WishPartnerEvent> arrayList) {
        this.mAdapter.setLoading(false);
        if (arrayList.size() > 0 || this.mAdapter.getItemCount() > 0) {
            if (this.mReload) {
                this.mAdapter.setValues(arrayList);
                this.mReload = false;
            } else {
                this.mAdapter.appendValues(arrayList);
            }
            this.mProgressBar.setVisibility(8);
            this.mRecyclerView.setVisibility(0);
            this.mAdapter.notifyDataSetChanged();
            this.mEmptyState.setVisibility(8);
            this.mMainEmptyState.setVisibility(8);
            return;
        }
        this.mRecyclerView.setVisibility(8);
        if (this.mShowMainEmptyState) {
            this.mMainEmptyState.setVisibility(0);
            this.mEmptyState.setVisibility(8);
        } else {
            this.mEmptyState.setVisibility(0);
            this.mMainEmptyState.setVisibility(8);
        }
        this.mProgressBar.setVisibility(8);
    }

    public void reload() {
        cleanup();
        this.mReload = true;
    }

    public void cleanup() {
        this.mAdapter.clean();
    }

    public void setNoMoreItems(boolean z) {
        this.mAdapter.setNoMoreItems(z);
    }

    public void setLoadingFailed() {
        getHandler().post(new Runnable() {
            public void run() {
                WishPartnerItemPageView.this.mRecyclerView.setVisibility(8);
                WishPartnerItemPageView.this.mProgressBar.setVisibility(8);
                WishPartnerItemPageView.this.mEmptyState.setVisibility(0);
                WishPartnerItemPageView.this.mMainEmptyState.setVisibility(8);
            }
        });
    }
}
