package com.contextlogic.wish.activity.merchantprofile;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.ui.bottomnavigation.BottomNavigationHelper;
import com.contextlogic.wish.ui.bottomnavigation.BottomNavigationInterface;
import com.contextlogic.wish.ui.loading.LoadingPageView;
import com.contextlogic.wish.ui.loading.LoadingPageView.LoadingPageManager;
import com.contextlogic.wish.ui.scrollview.ScrollRestorable;
import com.contextlogic.wish.ui.viewpager.BasePagerScrollingObserver;
import com.contextlogic.wish.ui.viewpager.BasePagerViewInterface;

public abstract class MerchantProfilePagerView extends LoadingPageView implements LoadingPageManager, ScrollRestorable, BasePagerScrollingObserver, BasePagerViewInterface {
    protected MerchantProfileFragment mFragment;
    protected int mIndex;
    protected MerchantProfilePagerHelper mPagerHelper;
    protected View mRootLayout;

    public boolean canPullToRefresh() {
        return false;
    }

    public abstract int getLoadingContentLayoutResourceId();

    public void handleReload() {
    }

    public boolean hasItems() {
        return true;
    }

    /* access modifiers changed from: protected */
    public void init() {
    }

    public void scrollOffset(int i) {
    }

    public MerchantProfilePagerView(Context context) {
        this(context, null);
    }

    public MerchantProfilePagerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MerchantProfilePagerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    public void setup(int i, MerchantProfileFragment merchantProfileFragment) {
        this.mIndex = i;
        this.mFragment = merchantProfileFragment;
        this.mPagerHelper = new MerchantProfilePagerHelper(merchantProfileFragment, this, i);
        if (ExperimentDataCenter.getInstance().shouldShowBottomNavigation() && (this.mFragment instanceof BottomNavigationInterface)) {
            this.mPagerHelper.setBottomNavigationHelper(new BottomNavigationHelper(this.mFragment));
        }
        setLoadingPageManager(this);
        if (hasItems()) {
            markLoadingComplete();
        }
    }

    public int getIndex() {
        return this.mIndex;
    }

    public void onPagerScrollUnsettled() {
        this.mPagerHelper.onPagerScrollUnsettled();
    }

    public void onPagerScrollSettled() {
        this.mPagerHelper.onPagerScrollSettled();
    }

    public void queuePagerSettledTask(Runnable runnable) {
        this.mPagerHelper.queuePagerSettledTask(runnable);
    }

    public void initializeLoadingContentView(View view) {
        this.mRootLayout = view;
    }

    public void postDelayedTask(Runnable runnable, int i) {
        postDelayed(runnable, (long) i);
    }

    public void handleScrollChanged(int i, int i2) {
        this.mPagerHelper.handleScrollChanged(i, i2);
    }

    /* access modifiers changed from: protected */
    public void setupScroller(View view) {
        this.mPagerHelper.setupScroller(view);
    }
}
