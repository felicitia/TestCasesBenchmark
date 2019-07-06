package com.contextlogic.wish.activity.productdetails;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.ui.loading.LoadingPageView;
import com.contextlogic.wish.ui.loading.LoadingPageView.LoadingPageManager;
import com.contextlogic.wish.ui.scrollview.ScrollRestorable;
import com.contextlogic.wish.ui.viewpager.BasePagerHelper;
import com.contextlogic.wish.ui.viewpager.BasePagerScrollingObserver;
import com.contextlogic.wish.ui.viewpager.BasePagerViewInterface;

public abstract class ProductDetailsPagerView extends LoadingPageView implements LoadingPageManager, ScrollRestorable, BasePagerScrollingObserver, BasePagerViewInterface {
    protected ProductDetailsFragment mFragment;
    protected int mIndex;
    protected BasePagerHelper mPagerHelper;
    protected WishProduct mProduct;
    protected View mRootLayout;
    protected View mSpacerView;

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

    public ProductDetailsPagerView(Context context) {
        this(context, null);
    }

    public ProductDetailsPagerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ProductDetailsPagerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    public void setup(WishProduct wishProduct, int i, ProductDetailsFragment productDetailsFragment) {
        this.mProduct = wishProduct;
        this.mIndex = i;
        this.mFragment = productDetailsFragment;
        this.mPagerHelper = new BasePagerHelper(productDetailsFragment, this, i);
        if (ExperimentDataCenter.getInstance().shouldShowProductDetailTransition()) {
            this.mPagerHelper.setHideWithinTabArea(false);
        }
        setLoadingPageManager(this);
        if (hasItems()) {
            markLoadingComplete();
        }
    }

    public int getIndex() {
        return this.mIndex;
    }

    /* access modifiers changed from: protected */
    public void setupScroller(View view) {
        this.mPagerHelper.setupScroller(view);
    }

    public void postDelayedTask(Runnable runnable, int i) {
        postDelayed(runnable, (long) i);
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

    public void handleScrollChanged(int i, int i2) {
        this.mPagerHelper.handleScrollChanged(i, i2);
    }

    public void initializeLoadingContentView(View view) {
        this.mRootLayout = view;
    }

    public void updateSpacerHeight() {
        int tabAreaSize = this.mFragment.getTabAreaSize();
        if (this.mSpacerView != null) {
            LayoutParams layoutParams = this.mSpacerView.getLayoutParams();
            layoutParams.height = tabAreaSize;
            this.mSpacerView.setLayoutParams(layoutParams);
        }
    }
}
