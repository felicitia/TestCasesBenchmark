package com.contextlogic.wish.activity.rewards.redesign;

import android.os.Bundle;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout.LayoutParams;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.activity.rewards.RewardsActivity;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishRedeemableRewardItem;
import com.contextlogic.wish.api.model.WishRewardsDashboardInfo;
import com.contextlogic.wish.api.model.WishRewardsHelpInfo;
import com.contextlogic.wish.api.model.WishRewardsRedeemableInfo;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.ui.viewpager.BaseTabStripInterface;
import com.contextlogic.wish.ui.viewpager.PagerSlidingTabStrip;
import com.contextlogic.wish.ui.viewpager.SafeViewPager;
import java.util.ArrayList;

public class RewardsFragment extends UiFragment<RewardsActivity> implements BaseTabStripInterface {
    private boolean mFirstTimeInformation;
    private boolean mFirstTimeRedeem;
    private OnPageChangeListener mPageScrollListener;
    private int mRestoredIndex;
    private RewardsPagerAdapter mRewardsPagerAdapter;
    private SafeViewPager mRewardsViewPager;
    private int mTabBarHeight;
    private PagerSlidingTabStrip mTabStrip;
    /* access modifiers changed from: private */
    public View mTabStripContainer;

    /* access modifiers changed from: protected */
    public int getLayoutResourceId() {
        return R.layout.rewards_redesign_fragment;
    }

    public void releaseImages() {
    }

    public void restoreImages() {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mTabBarHeight = WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.tab_bar_height);
        this.mRestoredIndex = -1;
        if (bundle != null) {
            this.mRestoredIndex = bundle.getInt("SavedStateCurrentTab");
            this.mFirstTimeRedeem = bundle.getBoolean("SavedStateFirstTimeRedeem");
            this.mFirstTimeInformation = bundle.getBoolean("SavedStateFirstTimeInfo");
        }
        this.mPageScrollListener = new OnPageChangeListener() {
            public void onPageScrolled(int i, float f, int i2) {
                if (RewardsFragment.this.mTabStripContainer.getAnimation() == null) {
                    RewardsFragment.this.showTabArea(true);
                }
            }

            public void onPageSelected(int i) {
                RewardsFragment.this.handlePageSelected(i);
            }

            public void onPageScrollStateChanged(int i) {
                if (i == 0) {
                    RewardsFragment.this.onPagerScrollSettled();
                } else {
                    RewardsFragment.this.onPagerScrollUnsettled();
                }
            }
        };
        this.mFirstTimeInformation = true;
        this.mFirstTimeRedeem = true;
    }

    /* access modifiers changed from: protected */
    public void initialize() {
        this.mTabStrip = (PagerSlidingTabStrip) findViewById(R.id.rewards_fragment_viewpager_tabs);
        this.mTabStripContainer = findViewById(R.id.rewards_fragment_viewpager_tab_container);
        this.mRewardsViewPager = (SafeViewPager) findViewById(R.id.rewards_fragment_viewpager);
        this.mRewardsPagerAdapter = new RewardsPagerAdapter((DrawerActivity) getActivity(), this, this.mRewardsViewPager);
        this.mRewardsViewPager.setAdapter(this.mRewardsPagerAdapter);
        this.mRewardsPagerAdapter.updatePages();
        this.mTabStrip.setHasBadges();
        this.mTabStrip.setViewPager(this.mRewardsViewPager);
        this.mTabStrip.setOnPageChangeListener(this.mPageScrollListener);
        customizeTabStrip();
        refreshTabStripFontColors();
        goToRestoredTab();
    }

    public void handleResume() {
        super.handleResume();
        if (this.mRewardsPagerAdapter != null) {
            this.mRewardsPagerAdapter.handleResume();
        }
    }

    public void handleSaveInstanceState(Bundle bundle) {
        super.handleSaveInstanceState(bundle);
        bundle.putInt("SavedStateCurrentTab", getCurrentIndex());
        bundle.putBoolean("SavedStateFirstTimeRedeem", this.mFirstTimeRedeem);
        bundle.putBoolean("SavedStateFirstTimeInfo", this.mFirstTimeInformation);
        this.mRewardsPagerAdapter.handleSaveInstanceState(bundle);
    }

    public String getPagedDataSavedInstanceStateKey(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append("SavedStatePagedData_");
        sb.append(i);
        return sb.toString();
    }

    private void customizeTabStrip() {
        if (getBaseActivity() != null && ((RewardsActivity) getBaseActivity()).getActionBarManager() != null) {
            ((RewardsActivity) getBaseActivity()).getActionBarManager().stylizeTabStrip(this.mTabStrip);
        }
    }

    private void refreshTabStripFontColors() {
        if (getBaseActivity() != null && ((RewardsActivity) getBaseActivity()).getActionBarManager() != null) {
            ((RewardsActivity) getBaseActivity()).getActionBarManager().refreshTabStripFontColors(this.mTabStrip, this.mRewardsViewPager.getCurrentItem());
        }
    }

    /* access modifiers changed from: protected */
    public void handlePageSelected(int i) {
        refreshTabStripFontColors();
        switch (i) {
            case 1:
                if (this.mFirstTimeRedeem) {
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_REWARDS_REDEEM_TAB);
                    this.mFirstTimeRedeem = false;
                    return;
                }
                return;
            case 2:
                if (this.mFirstTimeInformation) {
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_REWARDS_INFORMATION_TAB);
                    this.mFirstTimeInformation = false;
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void showTabArea(boolean z) {
        int tabAreaOffset = getTabAreaOffset();
        if (tabAreaOffset != 0) {
            this.mTabStripContainer.clearAnimation();
            int i = 0 - tabAreaOffset;
            TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, 0.0f, (float) i);
            translateAnimation.setDuration(z ? (long) ((int) ((((double) Math.abs(i)) / ((double) getTabAreaSize())) * 250.0d)) : 0);
            translateAnimation.setFillAfter(false);
            translateAnimation.setAnimationListener(new AnimationListener() {
                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationStart(Animation animation) {
                }

                public void onAnimationEnd(Animation animation) {
                    RewardsFragment.this.setTabAreaOffset(0);
                }
            });
            this.mTabStripContainer.startAnimation(translateAnimation);
        }
    }

    public void hideTabArea(boolean z) {
        final int hiddenTabBarStripOffset = getHiddenTabBarStripOffset();
        int tabAreaOffset = getTabAreaOffset();
        if (tabAreaOffset != hiddenTabBarStripOffset) {
            this.mTabStripContainer.clearAnimation();
            int i = hiddenTabBarStripOffset - tabAreaOffset;
            TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, 0.0f, (float) i);
            translateAnimation.setDuration(z ? (long) ((int) ((((double) Math.abs(i)) / ((double) getTabAreaSize())) * 250.0d)) : 0);
            translateAnimation.setFillAfter(false);
            translateAnimation.setAnimationListener(new AnimationListener() {
                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationStart(Animation animation) {
                }

                public void onAnimationEnd(Animation animation) {
                    RewardsFragment.this.setTabAreaOffset(hiddenTabBarStripOffset);
                }
            });
            this.mTabStripContainer.startAnimation(translateAnimation);
        }
    }

    public int getTabAreaOffset() {
        return ((LayoutParams) this.mTabStripContainer.getLayoutParams()).topMargin;
    }

    public int getTabAreaSize() {
        return this.mTabBarHeight;
    }

    public void setTabAreaOffset(int i) {
        this.mTabStripContainer.clearAnimation();
        LayoutParams layoutParams = (LayoutParams) this.mTabStripContainer.getLayoutParams();
        layoutParams.topMargin = Math.min(Math.max(i, getHiddenTabBarStripOffset()), 0);
        this.mTabStripContainer.setLayoutParams(layoutParams);
    }

    public int getHiddenTabBarStripOffset() {
        return getTabAreaSize() * -1;
    }

    /* access modifiers changed from: private */
    public void onPagerScrollUnsettled() {
        if (this.mRewardsPagerAdapter != null) {
            this.mRewardsPagerAdapter.onPagerScrollUnsettled();
        }
    }

    /* access modifiers changed from: private */
    public void onPagerScrollSettled() {
        if (this.mRewardsPagerAdapter != null) {
            this.mRewardsPagerAdapter.onPagerScrollSettled();
        }
    }

    public void switchToPosition(int i, boolean z) {
        if (i >= 0 && i < this.mRewardsPagerAdapter.getCount()) {
            this.mRewardsViewPager.setCurrentItem(i, z);
        }
    }

    public int getCurrentIndex() {
        return this.mRewardsViewPager.getCurrentItem();
    }

    public void handleLoadingDashboardInfoSuccess(WishRewardsDashboardInfo wishRewardsDashboardInfo, ArrayList<WishRedeemableRewardItem> arrayList, boolean z, int i, String str) {
        if (this.mRewardsPagerAdapter != null) {
            this.mRewardsPagerAdapter.handleLoadingDashboardInfoSuccess(wishRewardsDashboardInfo, arrayList, z, i);
        }
    }

    public void handleLoadingDashboardInfoFailure() {
        if (this.mRewardsPagerAdapter != null) {
            this.mRewardsPagerAdapter.handleLoadingDashboardInfoFailure();
        }
    }

    public void handleLoadingRedeemableRewardsSuccess(WishRewardsRedeemableInfo wishRewardsRedeemableInfo) {
        if (this.mRewardsPagerAdapter != null) {
            this.mTabStrip.setTabBadged(null);
            refreshTabs();
            this.mRewardsPagerAdapter.handleLoadingRedeemableRewardsSuccess(wishRewardsRedeemableInfo);
        }
    }

    public void handleLoadingRedeemableRewardsFailure() {
        if (this.mRewardsPagerAdapter != null) {
            this.mRewardsPagerAdapter.handleLoadingRedeemableRewardsFailure();
        }
    }

    public void handleLoadingHelpInfoSuccess(WishRewardsHelpInfo wishRewardsHelpInfo) {
        if (this.mRewardsPagerAdapter != null) {
            this.mRewardsPagerAdapter.handleLoadingHelpInfoSuccess(wishRewardsHelpInfo);
        }
    }

    public void handleLoadingHelpInfoFailure() {
        if (this.mRewardsPagerAdapter != null) {
            this.mRewardsPagerAdapter.handleLoadingHelpInfoFailure();
        }
    }

    private void goToRestoredTab() {
        if (this.mRewardsPagerAdapter != null) {
            switchToPosition(this.mRestoredIndex, false);
        }
    }

    /* access modifiers changed from: 0000 */
    public void reloadDashboardView() {
        this.mRewardsPagerAdapter.reloadDashboardView();
    }

    public void refreshTabs() {
        this.mTabStrip.notifyDataSetChanged();
        customizeTabStrip();
        refreshTabStripFontColors();
    }
}
