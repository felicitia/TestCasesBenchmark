package com.contextlogic.wish.activity.rewards.redesign;

import com.contextlogic.wish.ui.viewpager.BasePagerHelper;
import com.contextlogic.wish.ui.viewpager.BasePagerScrollingObserver;

public class RewardsPagerHelper extends BasePagerHelper {
    public RewardsPagerHelper(RewardsFragment rewardsFragment, BasePagerScrollingObserver basePagerScrollingObserver, int i) {
        super(rewardsFragment, basePagerScrollingObserver, i);
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
        }
    }
}
