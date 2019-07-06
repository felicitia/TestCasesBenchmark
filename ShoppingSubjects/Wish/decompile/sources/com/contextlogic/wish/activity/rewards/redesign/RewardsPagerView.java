package com.contextlogic.wish.activity.rewards.redesign;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import com.contextlogic.wish.ui.loading.LoadingPageView;
import com.contextlogic.wish.ui.loading.LoadingPageView.LoadingPageManager;
import com.contextlogic.wish.ui.scrollview.ScrollRestorable;
import com.contextlogic.wish.ui.viewpager.BasePagerScrollingObserver;

public abstract class RewardsPagerView extends LoadingPageView implements RewardsPagerViewInterface, LoadingPageManager, ScrollRestorable, BasePagerScrollingObserver {
    private RewardsFragment mFragment;
    private int mIndex;
    protected RewardsPagerHelper mPagerHelper;
    protected View mRootLayout;

    public boolean canPullToRefresh() {
        return false;
    }

    public abstract int getCurrentScrollY();

    public abstract int getLoadingContentLayoutResourceId();

    public void handleReload() {
    }

    public boolean hasItems() {
        return true;
    }

    /* access modifiers changed from: protected */
    public void init() {
    }

    public RewardsPagerView(Context context) {
        this(context, null);
    }

    public RewardsPagerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RewardsPagerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    public void setup(int i, RewardsFragment rewardsFragment) {
        this.mIndex = i;
        this.mFragment = rewardsFragment;
        this.mPagerHelper = new RewardsPagerHelper(rewardsFragment, this, i);
        setLoadingPageManager(this);
        if (hasItems()) {
            markLoadingComplete();
        }
    }

    public int getIndex() {
        return this.mIndex;
    }

    public void initializeLoadingContentView(View view) {
        this.mRootLayout = view;
    }

    public void postDelayedTask(Runnable runnable, int i) {
        postDelayed(runnable, (long) i);
    }

    public void onPagerScrollUnsettled() {
        this.mPagerHelper.onPagerScrollUnsettled();
    }

    public void queuePagerSettledTask(Runnable runnable) {
        this.mPagerHelper.queuePagerSettledTask(runnable);
    }

    public void handleScrollChanged(int i, int i2) {
        this.mPagerHelper.handleScrollChanged(i, i2);
    }

    public void onPagerScrollSettled() {
        this.mPagerHelper.onPagerScrollSettled();
    }
}
