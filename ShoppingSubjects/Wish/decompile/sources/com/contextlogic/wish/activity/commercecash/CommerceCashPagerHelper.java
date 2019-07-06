package com.contextlogic.wish.activity.commercecash;

import com.contextlogic.wish.ui.viewpager.BasePagerHelper;
import com.contextlogic.wish.ui.viewpager.BasePagerScrollingObserver;

public class CommerceCashPagerHelper extends BasePagerHelper {
    CommerceCashPagerHelper(CommerceCashFragment commerceCashFragment, BasePagerScrollingObserver basePagerScrollingObserver, int i) {
        super(commerceCashFragment, basePagerScrollingObserver, i);
    }

    public void handleScrollChanged(int i, int i2) {
        if (this.mFragment.getCurrentIndex() == this.mIndex) {
            super.handleScrollChanged(i, i2);
            this.mFragment.showTabArea(true);
        }
    }
}
