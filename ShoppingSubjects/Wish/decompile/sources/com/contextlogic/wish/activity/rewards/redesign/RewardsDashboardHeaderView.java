package com.contextlogic.wish.activity.rewards.redesign;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishRewardsDashboardInfo;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.ui.button.ThemedButton;
import com.contextlogic.wish.ui.listview.ListViewTabStrip;
import com.contextlogic.wish.ui.listview.ListViewTabStrip.OnTabClickListener;
import com.contextlogic.wish.ui.text.ThemedTextView;
import com.contextlogic.wish.ui.view.SlidingTabStrip.TabType;
import java.util.ArrayList;

public class RewardsDashboardHeaderView extends LinearLayout {
    private LinearLayout mNoCouponsContainer;
    private RewardsDashboardHeaderPointsView mPointsView;
    private ThemedButton mRewardDashboardNoCouponButton;
    private ThemedTextView mRewardDashboardNoCouponText;
    private ListViewTabStrip mTabStrip;

    public RewardsDashboardHeaderView(Context context) {
        this(context, null);
    }

    public RewardsDashboardHeaderView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RewardsDashboardHeaderView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.rewards_dashboard_header_view, this);
        this.mPointsView = (RewardsDashboardHeaderPointsView) inflate.findViewById(R.id.rewards_dashboard_header_points_view);
        this.mPointsView.setVisibility(0);
        this.mTabStrip = (ListViewTabStrip) inflate.findViewById(R.id.reward_dashboard_header_view_tab_strip);
        this.mTabStrip.setTabTypes(new TabType[]{TabType.TEXT_TAB, TabType.TEXT_TAB});
        this.mNoCouponsContainer = (LinearLayout) inflate.findViewById(R.id.reward_dashboard_no_coupons_container);
        this.mRewardDashboardNoCouponText = (ThemedTextView) inflate.findViewById(R.id.reward_dashboard_no_coupon_message);
        this.mRewardDashboardNoCouponButton = (ThemedButton) inflate.findViewById(R.id.reward_dashboard_no_coupon_button);
        setBackgroundColor(WishApplication.getInstance().getResources().getColor(R.color.white));
    }

    public void setup(final RewardsFragment rewardsFragment, ArrayList<? extends BaseAdapter> arrayList, WishRewardsDashboardInfo wishRewardsDashboardInfo, OnTabClickListener onTabClickListener) {
        setPadding(0, rewardsFragment.getTabAreaSize(), 0, 0);
        this.mTabStrip.setup(arrayList, onTabClickListener);
        this.mPointsView.setup(wishRewardsDashboardInfo);
        this.mRewardDashboardNoCouponButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_REWARDS_DASHBOARD_TAB_LEARN_MORE);
                rewardsFragment.switchToPosition(2, true);
            }
        });
    }

    public void setNoCouponState(boolean z, int i) {
        if (z) {
            this.mNoCouponsContainer.setVisibility(0);
            if (i == 0) {
                this.mRewardDashboardNoCouponText.setText(WishApplication.getInstance().getResources().getString(R.string.you_have_no_available_coupons));
            } else if (i == 1) {
                this.mRewardDashboardNoCouponText.setText(WishApplication.getInstance().getResources().getString(R.string.you_have_no_used_coupons));
            }
        } else {
            this.mNoCouponsContainer.setVisibility(8);
        }
    }

    public ListViewTabStrip getTabStrip() {
        return this.mTabStrip;
    }

    public void switchToTab(int i) {
        this.mTabStrip.switchToTab(i);
    }
}
