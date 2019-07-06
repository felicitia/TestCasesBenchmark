package com.contextlogic.wish.activity.merchantprofile;

import android.view.View;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.activity.feed.BaseProductFeedFragment;
import com.contextlogic.wish.activity.feed.BaseProductFeedView;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.ui.bottomnavigation.BottomNavigationHelper;
import com.contextlogic.wish.ui.viewpager.BasePagerHelper;

public class MerchantProfileLatestView extends BaseProductFeedView {
    private MerchantProfilePagerHelper mPagerHelper;

    public int getNewMargins() {
        return 16;
    }

    public MerchantProfileLatestView(int i, DrawerActivity drawerActivity, BaseProductFeedFragment baseProductFeedFragment) {
        super(i, drawerActivity, baseProductFeedFragment);
        this.mPagerHelper = new MerchantProfilePagerHelper(baseProductFeedFragment, this, i);
        if (ExperimentDataCenter.getInstance().shouldShowBottomNavigation() && this.mFragment != null) {
            this.mPagerHelper.setBottomNavigationHelper(new BottomNavigationHelper(this.mFragment));
        }
    }

    public void initializeLoadingContentView(View view) {
        super.initializeLoadingContentView(view);
        updateTabAreaOffset();
    }

    public void onPagerScrollUnsettled() {
        this.mPagerHelper.onPagerScrollUnsettled();
    }

    public void onPagerScrollSettled() {
        this.mPagerHelper.onPagerScrollSettled();
    }

    public void handleScrollChanged(int i, int i2) {
        this.mPagerHelper.handleScrollChanged(i, i2);
    }

    /* access modifiers changed from: protected */
    public BasePagerHelper getPagerHelper() {
        return new MerchantProfilePagerHelper(this.mFragment, this, getDataIndex());
    }

    public void scrollOffset(int i) {
        this.mGridView.smoothScrollBy(0, -i);
    }
}
