package com.contextlogic.wish.activity.wishpartner.dashboard;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import com.contextlogic.wish.activity.wishpartner.dashboard.WishPartnerItemPageView.WishPartnerFeedInterface;
import com.contextlogic.wish.api.model.WishPartnerSummary.WishPartnerEvent;
import com.contextlogic.wish.api.model.WishPartnerSummary.WishPartnerMainEmptyStateSpec;
import com.contextlogic.wish.ui.viewpager.BasePagerScrollingObserver;
import java.util.ArrayList;

public class WishPartnerPagerAdapter extends PagerAdapter implements WishPartnerFeedInterface {
    private WishPartnerDashboardActivity mActivity;
    private String mCommunityTitle;
    private WishPartnerItemPageView mCommunityView;
    private WishPartnerDashboardFragment mFragment;
    private boolean mLoaded;
    private SparseArray<WishPartnerItemPageView> mPagerViews = new SparseArray<>();
    private String mRecentEarningsTitle;
    private WishPartnerItemPageView mRecentEarningsView;
    private ViewPager mViewPager;

    public enum DashboardSections {
        RECENT_EARNINGS,
        COMMUNITY;

        public static DashboardSections fromInt(int i) {
            switch (i) {
                case 0:
                    return RECENT_EARNINGS;
                case 1:
                    return COMMUNITY;
                default:
                    return RECENT_EARNINGS;
            }
        }
    }

    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public WishPartnerPagerAdapter(WishPartnerDashboardActivity wishPartnerDashboardActivity, WishPartnerDashboardFragment wishPartnerDashboardFragment, ViewPager viewPager, String str, String str2) {
        this.mActivity = wishPartnerDashboardActivity;
        this.mFragment = wishPartnerDashboardFragment;
        this.mViewPager = viewPager;
        this.mCommunityTitle = str;
        this.mRecentEarningsTitle = str2;
        this.mLoaded = false;
    }

    public int getCount() {
        return DashboardSections.values().length;
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        WishPartnerItemPageView wishPartnerItemPageView;
        switch (DashboardSections.fromInt(i)) {
            case RECENT_EARNINGS:
                wishPartnerItemPageView = new WishPartnerItemPageView(this.mActivity);
                wishPartnerItemPageView.setup(true, this, true, this.mFragment);
                this.mRecentEarningsView = wishPartnerItemPageView;
                break;
            case COMMUNITY:
                wishPartnerItemPageView = new WishPartnerItemPageView(this.mActivity);
                wishPartnerItemPageView.setup(true, this, false, this.mFragment);
                this.mCommunityView = wishPartnerItemPageView;
                break;
            default:
                wishPartnerItemPageView = null;
                break;
        }
        this.mPagerViews.put(i, wishPartnerItemPageView);
        viewGroup.addView(wishPartnerItemPageView);
        if (!this.mLoaded) {
            this.mFragment.setAdapterLoaded();
            this.mLoaded = true;
        }
        return wishPartnerItemPageView;
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        WishPartnerItemPageView wishPartnerItemPageView = (WishPartnerItemPageView) obj;
        ((WishPartnerItemPageView) this.mPagerViews.get(i)).cleanup();
        viewGroup.removeView(wishPartnerItemPageView);
        this.mPagerViews.delete(i);
    }

    public void reload() {
        for (int i = 0; i < this.mPagerViews.size(); i++) {
            ((WishPartnerItemPageView) this.mPagerViews.get(this.mPagerViews.keyAt(i))).reload();
        }
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
    }

    public void handleRecentEarningsLoaded(ArrayList<WishPartnerEvent> arrayList, WishPartnerMainEmptyStateSpec wishPartnerMainEmptyStateSpec) {
        this.mRecentEarningsView.handleLoadingSuccess(arrayList, wishPartnerMainEmptyStateSpec);
    }

    public void handleRecentCommunityLoaded(ArrayList<WishPartnerEvent> arrayList) {
        this.mCommunityView.handleLoadingSuccess(arrayList);
    }

    public CharSequence getPageTitle(int i) {
        if (i == DashboardSections.COMMUNITY.ordinal()) {
            return this.mCommunityTitle;
        }
        return this.mRecentEarningsTitle;
    }

    public void loadTabData() {
        this.mFragment.loadTabData();
    }

    public void setNoMoreCommunityItems(boolean z) {
        if (this.mCommunityView != null) {
            this.mCommunityView.setNoMoreItems(z);
        }
    }

    public void setNoMoreResentEarningsItems(boolean z) {
        if (this.mRecentEarningsView != null) {
            this.mRecentEarningsView.setNoMoreItems(z);
        }
    }

    public void setLoadingFailed() {
        this.mRecentEarningsView.setLoadingFailed();
        this.mCommunityView.setLoadingFailed();
    }
}
