package com.contextlogic.wish.activity.commercecash;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import com.contextlogic.wish.ui.loading.LoadingPageView;
import com.contextlogic.wish.ui.loading.LoadingPageView.LoadingPageManager;
import com.contextlogic.wish.ui.viewpager.BasePagerScrollingObserver;

public abstract class CommerceCashPagerView extends LoadingPageView implements LoadingPageManager, BasePagerScrollingObserver {
    private CommerceCashFragment mFragment;
    private int mIndex;
    protected CommerceCashPagerHelper mPagerHelper;
    protected View mRootLayout;

    public boolean canPullToRefresh() {
        return false;
    }

    public abstract int getCurrentScrollY();

    public abstract int getLoadingContentLayoutResourceId();

    public void onPagerScrollSettled() {
    }

    public void onPagerScrollUnsettled() {
    }

    public abstract void scrollPage(int i);

    public CommerceCashPagerView(Context context) {
        this(context, null);
    }

    public CommerceCashPagerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CommerceCashPagerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setup(int i, CommerceCashFragment commerceCashFragment) {
        this.mIndex = i;
        this.mFragment = commerceCashFragment;
        this.mPagerHelper = new CommerceCashPagerHelper(commerceCashFragment, this, i);
        setLoadingPageManager(this);
        if (hasItems()) {
            markLoadingComplete();
        }
    }

    public void handleReload() {
        prepareForReload();
    }

    public int getIndex() {
        return this.mIndex;
    }

    public void initializeLoadingContentView(View view) {
        this.mRootLayout = view;
    }

    public void handleScrollChanged(int i, int i2) {
        this.mPagerHelper.handleScrollChanged(i, i2);
    }

    public void postDelayedTask(Runnable runnable, int i) {
        postDelayed(runnable, (long) i);
    }
}
