package com.contextlogic.wish.activity.feed.recentlyviewed;

import android.annotation.SuppressLint;
import android.view.ViewGroup.LayoutParams;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.activity.feed.BaseProductFeedFragment;
import com.contextlogic.wish.activity.feed.BaseProductFeedView;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.ui.viewpager.BasePagerHelper;

@SuppressLint({"ViewConstructor"})
public class RecentlyViewedProductFeedView extends BaseProductFeedView {
    public RecentlyViewedProductFeedView(int i, DrawerActivity drawerActivity, BaseProductFeedFragment baseProductFeedFragment) {
        super(i, drawerActivity, baseProductFeedFragment);
    }

    /* access modifiers changed from: protected */
    public void updateSpacerHeight() {
        int tabAreaSize = this.mFragment.getTabAreaSize() + 0;
        LayoutParams layoutParams = this.mSpacerView.getLayoutParams();
        layoutParams.height = tabAreaSize;
        this.mSpacerView.setLayoutParams(layoutParams);
        this.mSpacerView.setVisibility(8);
    }

    /* access modifiers changed from: protected */
    public void updateRefresherOffset() {
        setErrorOffset(0);
        setLoadingOffset(0);
    }

    /* access modifiers changed from: protected */
    public BasePagerHelper getPagerHelper() {
        if (ExperimentDataCenter.getInstance().shouldShowBottomNavigation()) {
            return super.getPagerHelper();
        }
        return null;
    }
}
