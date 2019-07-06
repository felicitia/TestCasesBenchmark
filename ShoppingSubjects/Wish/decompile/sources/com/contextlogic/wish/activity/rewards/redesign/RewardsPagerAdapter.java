package com.contextlogic.wish.activity.rewards.redesign;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishRedeemableRewardItem;
import com.contextlogic.wish.api.model.WishRewardsDashboardInfo;
import com.contextlogic.wish.api.model.WishRewardsDashboardInfo.RewardSection;
import com.contextlogic.wish.api.model.WishRewardsHelpInfo;
import com.contextlogic.wish.api.model.WishRewardsRedeemableInfo;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.ui.viewpager.BasePagerScrollingObserver;
import java.util.ArrayList;

public class RewardsPagerAdapter extends PagerAdapter {
    private final DrawerActivity mDrawerActivity;
    private boolean mInfoTracked;
    private ArrayList<RewardsPagerView> mPagerViews = new ArrayList<>();
    private boolean mRedeemTracked;
    private boolean mRedirectedAlready = false;
    private RewardsDashboardView mRewardsDashboardView;
    private final RewardsFragment mRewardsFragment;
    private RewardsHelpView mRewardsHelpView;
    private RewardsRedeemView mRewardsRedeemView;
    private ArrayList<RewardSection> mSections;
    private final ViewPager mViewPager;

    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    RewardsPagerAdapter(DrawerActivity drawerActivity, RewardsFragment rewardsFragment, ViewPager viewPager) {
        this.mDrawerActivity = drawerActivity;
        this.mRewardsFragment = rewardsFragment;
        this.mViewPager = viewPager;
    }

    public int getCount() {
        if (this.mSections == null) {
            return 0;
        }
        return this.mSections.size();
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        RewardsPagerView rewardsPagerView;
        switch ((RewardSection) this.mSections.get(i)) {
            case DASHBOARD:
                rewardsPagerView = new RewardsDashboardView(this.mDrawerActivity);
                rewardsPagerView.setup(i, this.mRewardsFragment);
                this.mRewardsDashboardView = (RewardsDashboardView) rewardsPagerView;
                break;
            case REDEEM:
                rewardsPagerView = new RewardsRedeemView(this.mDrawerActivity);
                rewardsPagerView.setup(i, this.mRewardsFragment);
                this.mRewardsRedeemView = (RewardsRedeemView) rewardsPagerView;
                break;
            case INFORMATION:
                rewardsPagerView = new RewardsHelpView(this.mDrawerActivity);
                rewardsPagerView.setup(i, this.mRewardsFragment);
                this.mRewardsHelpView = (RewardsHelpView) rewardsPagerView;
                break;
            default:
                rewardsPagerView = null;
                break;
        }
        if (this.mViewPager != null) {
            this.mPagerViews.add(rewardsPagerView);
        }
        rewardsPagerView.setTag(Integer.valueOf(i));
        viewGroup.addView(rewardsPagerView, new LayoutParams(-1, -1));
        return rewardsPagerView;
    }

    public String getPageTitle(int i) {
        if (this.mSections == null || i >= this.mSections.size()) {
            return "";
        }
        switch ((RewardSection) this.mSections.get(i)) {
            case DASHBOARD:
                return WishApplication.getInstance().getResources().getString(R.string.dashboard);
            case REDEEM:
                return WishApplication.getInstance().getResources().getString(R.string.redeem);
            case INFORMATION:
                return WishApplication.getInstance().getResources().getString(R.string.information);
            default:
                return "";
        }
    }

    /* access modifiers changed from: 0000 */
    public void updatePages() {
        this.mSections = new ArrayList<>();
        this.mSections.add(RewardSection.DASHBOARD);
        this.mSections.add(RewardSection.REDEEM);
        this.mSections.add(RewardSection.INFORMATION);
        notifyDataSetChanged();
    }

    public void onPagerScrollUnsettled() {
        for (int i = 0; i < getCount(); i++) {
            BasePagerScrollingObserver basePagerScrollingObserver = (BasePagerScrollingObserver) this.mViewPager.findViewWithTag(Integer.valueOf(i));
            if (basePagerScrollingObserver != null) {
                basePagerScrollingObserver.onPagerScrollUnsettled();
            }
        }
    }

    public void onPagerScrollSettled() {
        for (int i = 0; i < getCount(); i++) {
            BasePagerScrollingObserver basePagerScrollingObserver = (BasePagerScrollingObserver) this.mViewPager.findViewWithTag(Integer.valueOf(i));
            if (basePagerScrollingObserver != null) {
                basePagerScrollingObserver.onPagerScrollSettled();
            }
        }
        int currentIndex = this.mRewardsFragment.getCurrentIndex();
        if (this.mSections != null && currentIndex < this.mSections.size()) {
            switch ((RewardSection) this.mSections.get(currentIndex)) {
                case REDEEM:
                    if (!this.mRedeemTracked) {
                        this.mRedeemTracked = true;
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_MOBILE_NATIVE_REDEEM);
                        return;
                    }
                    return;
                case INFORMATION:
                    if (!this.mInfoTracked) {
                        this.mInfoTracked = true;
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_MOBILE_NATIVE_INFORMATION);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        RewardsPagerViewInterface rewardsPagerViewInterface = (RewardsPagerViewInterface) obj;
        rewardsPagerViewInterface.cleanup();
        viewGroup.removeView((View) rewardsPagerViewInterface);
    }

    /* access modifiers changed from: 0000 */
    public void handleLoadingDashboardInfoSuccess(WishRewardsDashboardInfo wishRewardsDashboardInfo, ArrayList<WishRedeemableRewardItem> arrayList, boolean z, int i) {
        if (this.mRewardsDashboardView != null) {
            this.mRewardsDashboardView.handleLoadingSuccess(wishRewardsDashboardInfo, arrayList, z, i);
            if (wishRewardsDashboardInfo != null && wishRewardsDashboardInfo.getRewardSection() != RewardSection.DASHBOARD && !this.mRedirectedAlready) {
                this.mRedirectedAlready = true;
                this.mViewPager.setCurrentItem(wishRewardsDashboardInfo.getRewardSection().ordinal());
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void handleLoadingDashboardInfoFailure() {
        if (this.mRewardsDashboardView != null) {
            this.mRewardsDashboardView.onFailure();
        }
    }

    /* access modifiers changed from: 0000 */
    public void handleLoadingRedeemableRewardsSuccess(WishRewardsRedeemableInfo wishRewardsRedeemableInfo) {
        if (this.mRewardsRedeemView != null) {
            this.mRewardsRedeemView.handleLoadingSuccess(wishRewardsRedeemableInfo);
        }
    }

    /* access modifiers changed from: 0000 */
    public void handleLoadingRedeemableRewardsFailure() {
        if (this.mRewardsRedeemView != null) {
            this.mRewardsRedeemView.onFailure();
        }
    }

    public void handleLoadingHelpInfoSuccess(WishRewardsHelpInfo wishRewardsHelpInfo) {
        if (this.mRewardsHelpView != null) {
            this.mRewardsHelpView.handleLoadingSuccess(wishRewardsHelpInfo);
        }
    }

    public void handleLoadingHelpInfoFailure() {
        if (this.mRewardsHelpView != null) {
            this.mRewardsHelpView.onFailure();
        }
    }

    public void handleSaveInstanceState(Bundle bundle) {
        if (this.mRewardsDashboardView != null) {
            bundle.putBundle(this.mRewardsFragment.getPagedDataSavedInstanceStateKey(this.mRewardsDashboardView.getIndex()), this.mRewardsDashboardView.getSavedInstanceState());
        }
    }

    public void handleResume() {
        if (this.mRewardsDashboardView != null && !this.mRewardsDashboardView.isLoadingComplete()) {
            this.mRewardsDashboardView.loadNextPage();
        }
    }

    /* access modifiers changed from: 0000 */
    public void reloadDashboardView() {
        if (this.mRewardsDashboardView != null) {
            this.mRewardsDashboardView.reload();
        }
    }
}
