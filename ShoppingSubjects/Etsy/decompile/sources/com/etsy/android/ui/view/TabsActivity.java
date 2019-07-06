package com.etsy.android.ui.view;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;
import android.view.View;
import com.etsy.android.R;
import com.etsy.android.ui.BOENavDrawerActivity;
import com.etsy.android.ui.nav.d;

public abstract class TabsActivity extends BOENavDrawerActivity implements OnPageChangeListener {
    private int mActionBarHeight;
    protected View mEmptyView;
    protected View mErrorView;
    protected View mLoadingView;
    protected d mNavTracker;
    protected TabLayout mSlidingTabLayout;
    protected ViewPager mViewPager;

    /* access modifiers changed from: protected */
    public abstract PagerAdapter getViewPagerAdapter();

    /* access modifiers changed from: protected */
    public abstract void logPageAtPosition(int i);

    public void onPageScrollStateChanged(int i) {
    }

    public void onPageScrolled(int i, float f, int i2) {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mNavTracker = new d();
        setContentView((int) R.layout.activity_tabs);
        this.mLoadingView = findViewById(R.id.loading_view);
        this.mEmptyView = findViewById(R.id.empty_view);
        this.mErrorView = findViewById(R.id.no_internet);
        this.mViewPager = (ViewPager) findViewById(R.id.view_pager);
        this.mActionBarHeight = getResources().getDimensionPixelOffset(R.dimen.actionbar_height);
        this.mSlidingTabLayout = getAppBarHelper().addTabLayout();
        this.mViewPager.addOnPageChangeListener(this);
    }

    /* access modifiers changed from: protected */
    public void setupTabs() {
        this.mViewPager.setAdapter(getViewPagerAdapter());
        this.mSlidingTabLayout.setupWithViewPager(this.mViewPager);
    }

    /* access modifiers changed from: protected */
    public void selectTab(int i) {
        this.mViewPager.setCurrentItem(i, false);
    }

    public boolean onCreateOptionsMenuWithIcons(Menu menu) {
        getMenuInflater().inflate(R.menu.default_action_bar, menu);
        return true;
    }

    /* access modifiers changed from: protected */
    public void showTabsView() {
        this.mViewPager.setVisibility(0);
        this.mErrorView.setVisibility(8);
        this.mEmptyView.setVisibility(8);
        this.mLoadingView.setVisibility(8);
    }

    /* access modifiers changed from: protected */
    public void showLoadingView() {
        this.mViewPager.setVisibility(8);
        this.mErrorView.setVisibility(8);
        this.mEmptyView.setVisibility(8);
        this.mLoadingView.setVisibility(0);
    }

    /* access modifiers changed from: protected */
    public void showErrorView() {
        this.mViewPager.setVisibility(8);
        this.mErrorView.setVisibility(0);
        this.mEmptyView.setVisibility(8);
        this.mLoadingView.setVisibility(8);
    }

    /* access modifiers changed from: protected */
    public void showEmptyView() {
        this.mViewPager.setVisibility(8);
        this.mErrorView.setVisibility(8);
        this.mEmptyView.setVisibility(0);
        this.mLoadingView.setVisibility(8);
    }

    public void onPageSelected(int i) {
        logPageAtPosition(i);
    }
}
