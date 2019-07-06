package com.contextlogic.wish.activity.rewards.redesign;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.rewards.RewardsActivity;
import com.contextlogic.wish.activity.rewards.redesign.DashboardRewardsAdapter.RedeemableRewardsType;
import com.contextlogic.wish.activity.rewards.redesign.RedeemableRewardsAdapter.Callback;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishRedeemableRewardItem;
import com.contextlogic.wish.api.model.WishRewardsDashboardInfo;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.cache.StateStoreCache;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment;
import com.contextlogic.wish.ui.listview.ListViewTabStrip.OnTabClickListener;
import com.contextlogic.wish.ui.listview.ListeningListView;
import com.contextlogic.wish.ui.listview.ListeningListView.ScrollViewListener;
import com.contextlogic.wish.ui.loading.LoadingFooterView;
import com.contextlogic.wish.ui.loading.LoadingFooterView.VisibilityMode;
import com.contextlogic.wish.util.FontUtil;
import java.util.ArrayList;
import java.util.Iterator;

public class RewardsDashboardView extends RewardsPagerView {
    private final int DASHBOARD_AVAILABLE_REWARDS_POSITION = 0;
    private final int DASHBOARD_USED_REWARDS_POSITION = 1;
    private boolean mAnimateDashboard;
    /* access modifiers changed from: private */
    public int mCurrentOffset;
    /* access modifiers changed from: private */
    public boolean mDashboardInfoLoaded;
    /* access modifiers changed from: private */
    public RewardsFragment mFragment;
    /* access modifiers changed from: private */
    public RewardsDashboardHeaderView mHeaderView;
    /* access modifiers changed from: private */
    public ArrayList<RewardsAdapter> mListAdapters;
    /* access modifiers changed from: private */
    public ListeningListView mListView;
    /* access modifiers changed from: private */
    public RewardState mRewardState;
    /* access modifiers changed from: private */
    public int mSelectedListAdapter;
    /* access modifiers changed from: private */
    public ArrayList<WishRedeemableRewardItem> mWishRewardItems;
    private WishRewardsDashboardInfo mWishRewardsDashboardInfo;

    public enum RewardState {
        COUPON_AVAILABLE(1),
        COUPON_USED(2),
        COUPON_EXPIRED(3),
        COMMERCE_USER_CREDIT_AVAILABLE(4);
        
        private int mValue;

        private RewardState(int i) {
            this.mValue = i;
        }

        public int getValue() {
            return this.mValue;
        }

        public static RewardState fromInteger(int i) {
            switch (i) {
                case 1:
                    return COUPON_AVAILABLE;
                case 2:
                    return COUPON_USED;
                case 3:
                    return COUPON_EXPIRED;
                case 4:
                    return COMMERCE_USER_CREDIT_AVAILABLE;
                default:
                    return null;
            }
        }

        public static int getNextRewardState(boolean z, RewardState rewardState) {
            if (rewardState == null) {
                return -1;
            }
            if (rewardState == COUPON_AVAILABLE && z) {
                return COMMERCE_USER_CREDIT_AVAILABLE.getValue();
            }
            if (rewardState != COUPON_USED || z) {
                return -1;
            }
            return COUPON_EXPIRED.getValue();
        }
    }

    public int getLoadingContentLayoutResourceId() {
        return R.layout.rewards_fragment_dashboard;
    }

    public void releaseImages() {
    }

    public RewardsDashboardView(Context context) {
        super(context);
    }

    public void setup(int i, RewardsFragment rewardsFragment) {
        super.setup(i, rewardsFragment);
        this.mAnimateDashboard = false;
        this.mListView = (ListeningListView) this.mRootLayout.findViewById(R.id.rewards_fragment_dashboard_coupons);
        this.mFragment = rewardsFragment;
        this.mHeaderView = new RewardsDashboardHeaderView(getContext());
        this.mListView.addHeaderView(this.mHeaderView);
        this.mListView.setScrollViewListener(new ScrollViewListener() {
            public void onScrollChanged(int i, int i2) {
                RewardsDashboardView.this.handleScrollChanged(i, i2);
            }
        });
        this.mListView.setOnScrollListener(new OnScrollListener() {
            public void onScrollStateChanged(AbsListView absListView, int i) {
            }

            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                RewardsDashboardView.this.handleScrollLoad(i, i2, i3);
            }
        });
        LoadingFooterView loadingFooterView = new LoadingFooterView(getContext());
        loadingFooterView.setReserveSpaceWhenHidden(false);
        loadingFooterView.setVisibilityMode(VisibilityMode.HIDDEN);
        loadingFooterView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                RewardsDashboardView.this.loadNextPage();
            }
        });
        setLoadingFooter(loadingFooterView);
        setLoadingPageManager(this);
        this.mListView.addFooterView(loadingFooterView);
        this.mListAdapters = new ArrayList<>();
        DashboardRewardsAdapter dashboardRewardsAdapter = new DashboardRewardsAdapter(this.mFragment, WishApplication.getInstance().getResources().getString(R.string.available), RedeemableRewardsType.DASHBOARD_AVAILABLE_REWARDS);
        dashboardRewardsAdapter.setOnClickListener(new Callback() {
            public void onClickListener(final WishRedeemableRewardItem wishRedeemableRewardItem) {
                RewardsDashboardView.this.mFragment.withServiceFragment(new ServiceTask<BaseActivity, RewardsServiceFragment>() {
                    public void performTask(BaseActivity baseActivity, RewardsServiceFragment rewardsServiceFragment) {
                        if (TextUtils.isEmpty(wishRedeemableRewardItem.getPromoCode())) {
                            rewardsServiceFragment.showAutomaticApplyDialog();
                        }
                    }
                });
            }
        });
        this.mListAdapters.add(0, dashboardRewardsAdapter);
        this.mListAdapters.add(1, new DashboardRewardsAdapter(this.mFragment, WishApplication.getInstance().getResources().getString(R.string.used), RedeemableRewardsType.DASHBOARD_USED_REWARDS));
        this.mListView.setAdapter((ListAdapter) this.mListAdapters.get(this.mSelectedListAdapter));
        ((RewardsAdapter) this.mListAdapters.get(this.mSelectedListAdapter)).notifyDataSetChanged();
        this.mRewardState = RewardState.COUPON_AVAILABLE;
        this.mWishRewardItems = new ArrayList<>();
        this.mDashboardInfoLoaded = false;
        setBackgroundColor(WishApplication.getInstance().getResources().getColor(R.color.white));
        customizeTabStrip();
        loadNextPage();
    }

    public void handleScrollLoad(final int i, final int i2, final int i3) {
        this.mFragment.withServiceFragment(new ServiceTask<BaseActivity, RewardsServiceFragment>() {
            public void performTask(BaseActivity baseActivity, RewardsServiceFragment rewardsServiceFragment) {
                if ((!RewardsDashboardView.this.isLoadingErrored() && !RewardsDashboardView.this.getNoMoreItems() && !rewardsServiceFragment.isLoadingRewards() && RewardsDashboardView.this.isLoadingComplete()) && i > i3 - (i2 * 2)) {
                    RewardsDashboardView.this.loadNextPage();
                }
            }
        });
    }

    public void loadNextPage() {
        this.mFragment.withServiceFragment(new ServiceTask<BaseActivity, RewardsServiceFragment>() {
            public void performTask(BaseActivity baseActivity, RewardsServiceFragment rewardsServiceFragment) {
                if (!RewardsDashboardView.this.getNoMoreItems()) {
                    rewardsServiceFragment.getRedeemableRewardsDashboardInfo(RewardsDashboardView.this.mCurrentOffset, RewardsDashboardView.this.mRewardState, !RewardsDashboardView.this.mDashboardInfoLoaded);
                    RewardsDashboardView.this.mHeaderView.setNoCouponState(false, RewardsDashboardView.this.mSelectedListAdapter);
                }
            }
        });
    }

    private boolean isViewingAvailableRewards() {
        return this.mSelectedListAdapter == 0;
    }

    private void setHeaderContent(WishRewardsDashboardInfo wishRewardsDashboardInfo) {
        this.mListView.setVisibility(0);
        this.mHeaderView.setup(this.mFragment, this.mListAdapters, wishRewardsDashboardInfo, new OnTabClickListener() {
            public void onTabSelected(int i) {
                if (i != RewardsDashboardView.this.mSelectedListAdapter) {
                    RewardsDashboardView.this.mFragment.setTabAreaOffset(0);
                    RewardsDashboardView.this.mSelectedListAdapter = i;
                    RewardsDashboardView.this.mCurrentOffset = 0;
                    if (i == 0) {
                        RewardsDashboardView.this.mRewardState = RewardState.COUPON_AVAILABLE;
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_REWARDS_DASHBOARD_TAB_AVAILABLE_COUPONS);
                    } else {
                        RewardsDashboardView.this.mRewardState = RewardState.COUPON_USED;
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_REWARDS_DASHBOARD_TAB_USED_COUPONS);
                    }
                    RewardsDashboardView.this.mListView.setAdapter((ListAdapter) RewardsDashboardView.this.mListAdapters.get(RewardsDashboardView.this.mSelectedListAdapter));
                    RewardsDashboardView.this.mWishRewardItems.clear();
                    ((RewardsAdapter) RewardsDashboardView.this.mListAdapters.get(RewardsDashboardView.this.mSelectedListAdapter)).setWishRewardItems(RewardsDashboardView.this.mWishRewardItems);
                    ((RewardsAdapter) RewardsDashboardView.this.mListAdapters.get(RewardsDashboardView.this.mSelectedListAdapter)).notifyDataSetChanged();
                    RewardsDashboardView.this.resetNoMoreItems();
                    RewardsDashboardView.this.loadNextPage();
                    RewardsDashboardView.this.refreshTabStripFontColors();
                }
            }
        });
        refreshTabStripFontColors();
    }

    public void handleLoadingSuccess(WishRewardsDashboardInfo wishRewardsDashboardInfo, ArrayList<WishRedeemableRewardItem> arrayList, boolean z, int i) {
        if (arrayList != null && this.mListAdapters.get(this.mSelectedListAdapter) != null) {
            if (wishRewardsDashboardInfo != null) {
                this.mDashboardInfoLoaded = true;
                this.mWishRewardsDashboardInfo = wishRewardsDashboardInfo;
                setHeaderContent(wishRewardsDashboardInfo);
            }
            markLoadingComplete();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                this.mWishRewardItems.add((WishRedeemableRewardItem) it.next());
            }
            boolean z2 = false;
            if (z) {
                int nextRewardState = RewardState.getNextRewardState(isViewingAvailableRewards(), this.mRewardState);
                if (nextRewardState == -1) {
                    markNoMoreItems();
                } else {
                    this.mRewardState = RewardState.fromInteger(nextRewardState);
                    this.mCurrentOffset = 0;
                    resetNoMoreItems();
                }
            } else {
                this.mCurrentOffset = i;
                resetNoMoreItems();
            }
            if ((!getNoMoreItems() && this.mWishRewardItems.size() < 10) || (arrayList.size() == 0 && this.mRewardState != null)) {
                loadNextPage();
            }
            ((RewardsAdapter) this.mListAdapters.get(this.mSelectedListAdapter)).setWishRewardItems(this.mWishRewardItems);
            ((RewardsAdapter) this.mListAdapters.get(0)).setAnimateDashboard(this.mAnimateDashboard);
            if (this.mAnimateDashboard) {
                this.mAnimateDashboard = false;
            }
            ((RewardsAdapter) this.mListAdapters.get(this.mSelectedListAdapter)).notifyDataSetChanged();
            RewardsDashboardHeaderView rewardsDashboardHeaderView = this.mHeaderView;
            if (this.mWishRewardItems.size() == 0 && getNoMoreItems()) {
                z2 = true;
            }
            rewardsDashboardHeaderView.setNoCouponState(z2, this.mSelectedListAdapter);
            markLoadingComplete();
        }
    }

    public int getCurrentScrollY() {
        if (this.mListView != null) {
            return this.mListView.getCurrentScrollY();
        }
        return 0;
    }

    public int getFirstItemPosition() {
        return this.mListView.getFirstVisiblePosition();
    }

    private void customizeTabStrip() {
        this.mFragment.withActivity(new ActivityTask<RewardsActivity>() {
            public void performTask(RewardsActivity rewardsActivity) {
                int dimensionPixelOffset = WishApplication.getInstance().getResources().getDimensionPixelOffset(R.dimen.tab_strip_indicator_height);
                int dimensionPixelOffset2 = WishApplication.getInstance().getResources().getDimensionPixelOffset(R.dimen.tab_strip_text_size);
                RewardsDashboardView.this.mHeaderView.getTabStrip().setBackgroundResource(R.color.white);
                RewardsDashboardView.this.mHeaderView.getTabStrip().setIndicatorColorResource(R.color.main_primary);
                RewardsDashboardView.this.mHeaderView.getTabStrip().setDividerColorResource(R.color.white);
                RewardsDashboardView.this.mHeaderView.getTabStrip().setTextColorResource(R.color.main_primary);
                RewardsDashboardView.this.mHeaderView.getTabStrip().setUnderlineHeight(0);
                RewardsDashboardView.this.mHeaderView.getTabStrip().setIndicatorHeight(dimensionPixelOffset);
                RewardsDashboardView.this.mHeaderView.getTabStrip().setTextSize(dimensionPixelOffset2);
            }
        });
    }

    /* access modifiers changed from: private */
    public void refreshTabStripFontColors() {
        LinearLayout linearLayout = (LinearLayout) this.mHeaderView.getTabStrip().getChildAt(0);
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            TextView textView = (TextView) linearLayout.getChildAt(i);
            if (i == this.mSelectedListAdapter) {
                textView.setTextColor(WishApplication.getInstance().getResources().getColor(R.color.text_primary));
                textView.setTypeface(FontUtil.getTypefaceForStyle(1));
            } else {
                textView.setTextColor(WishApplication.getInstance().getResources().getColor(R.color.gray4));
                textView.setTypeface(FontUtil.getTypefaceForStyle(0));
            }
        }
    }

    public Bundle getSavedInstanceState() {
        if (!isLoadingComplete()) {
            return null;
        }
        Bundle bundle = new Bundle();
        bundle.putString("SavedStateData", StateStoreCache.getInstance().storeParcelableList(this.mWishRewardItems));
        bundle.putBoolean("SavedStateNoMoreItems", getNoMoreItems());
        bundle.putInt("SavedStateOffset", this.mCurrentOffset);
        bundle.putLong("SavedStateSelectedListAdapter", (long) this.mSelectedListAdapter);
        bundle.putParcelable("SavedStateDashboardInfo", this.mWishRewardsDashboardInfo);
        bundle.putInt("SavedStateRewardState", this.mRewardState.getValue());
        return bundle;
    }

    /* access modifiers changed from: protected */
    public void cancelNetworkRequest() {
        this.mFragment.withServiceFragment(new ServiceTask<BaseActivity, RewardsServiceFragment>() {
            public void performTask(BaseActivity baseActivity, RewardsServiceFragment rewardsServiceFragment) {
                rewardsServiceFragment.cancelLoadingDashboardInfo();
            }
        });
    }

    public void cleanup() {
        releaseImages();
        cancelNetworkRequest();
    }

    public void handleReload() {
        this.mSelectedListAdapter = 0;
        this.mDashboardInfoLoaded = false;
        this.mCurrentOffset = 0;
        this.mRewardState = RewardState.COUPON_AVAILABLE;
        this.mWishRewardItems.clear();
        this.mHeaderView.switchToTab(0);
        this.mListView.setAdapter((ListAdapter) this.mListAdapters.get(this.mSelectedListAdapter));
        loadNextPage();
    }

    public void onFailure() {
        queuePagerSettledTask(new Runnable() {
            public void run() {
                RewardsDashboardView.this.handleLoadingFailure();
            }
        });
    }

    /* access modifiers changed from: private */
    public void handleLoadingFailure() {
        markLoadingErrored();
        this.mFragment.withActivity(new ActivityTask<RewardsActivity>() {
            public void performTask(RewardsActivity rewardsActivity) {
                rewardsActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(rewardsActivity.getString(R.string.rewards_error_message)));
            }
        });
    }

    public boolean hasItems() {
        return isLoadingComplete();
    }

    public void setDashboardAnimation(boolean z) {
        this.mAnimateDashboard = z;
    }
}
