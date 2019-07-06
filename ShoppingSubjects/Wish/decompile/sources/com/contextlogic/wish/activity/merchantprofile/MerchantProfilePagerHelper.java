package com.contextlogic.wish.activity.merchantprofile;

import com.contextlogic.wish.activity.feed.BaseProductFeedFragment;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.ui.viewpager.BasePagerHelper;
import com.contextlogic.wish.ui.viewpager.BasePagerScrollingObserver;

public class MerchantProfilePagerHelper extends BasePagerHelper {
    public MerchantProfilePagerHelper(BaseProductFeedFragment baseProductFeedFragment, BasePagerScrollingObserver basePagerScrollingObserver, int i) {
        super(baseProductFeedFragment, basePagerScrollingObserver, i);
    }

    public void handleScrollEnded() {
        if (this.mFragment.getCurrentIndex() == this.mIndex) {
            super.handleScrollEnded();
        }
    }

    public void handleScrollChanged(int i, int i2) {
        if (this.mFragment.getCurrentIndex() == this.mIndex) {
            int max = this.mStartScrollPosition - Math.max(0, i);
            if (max != 0) {
                this.mFragment.setTabAreaOffset(this.mStartTabBarOffset + max);
            }
            if (ExperimentDataCenter.getInstance().shouldShowBottomNavigation() && this.mBottomNavigationHelper != null) {
                this.mBottomNavigationHelper.handleScrollChanged(i2);
            }
        }
    }
}
