package com.contextlogic.wish.activity.feed.recentlyviewed;

import android.annotation.SuppressLint;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.activity.feed.ProductFeedFragment;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.ui.image.ImageRestorable;
import com.contextlogic.wish.ui.viewpager.CollapsibleTabStripLayout;
import java.util.ArrayList;

@SuppressLint({"ViewConstructor"})
public class RecentlyViewedTabView extends LinearLayout implements ImageRestorable {
    private RecentlyViewedPagerAdapter mAdapter;

    public RecentlyViewedTabView(int i, DrawerActivity drawerActivity, ProductFeedFragment productFeedFragment) {
        super(drawerActivity);
        init(i, drawerActivity, productFeedFragment);
    }

    private void init(int i, DrawerActivity drawerActivity, ProductFeedFragment productFeedFragment) {
        inflate(drawerActivity, R.layout.recently_viewed_tab_view, this);
        setOrientation(1);
        setLayoutParams(new LayoutParams(-1, -1));
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        this.mAdapter = new RecentlyViewedPagerAdapter(i, drawerActivity, productFeedFragment);
        viewPager.setAdapter(this.mAdapter);
        ((TabLayout) findViewById(R.id.tab_bar)).setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(this.mAdapter);
        CollapsibleTabStripLayout collapsibleTabStripLayout = (CollapsibleTabStripLayout) findViewById(R.id.collapsible_tab_strip_layout);
        collapsibleTabStripLayout.setupWithTabStripInterface(productFeedFragment);
        collapsibleTabStripLayout.setParentViewPagerPosition(i);
    }

    public void releaseImages() {
        this.mAdapter.releaseImages();
    }

    public void restoreImages() {
        this.mAdapter.restoreImages();
    }

    public void handleResume() {
        this.mAdapter.handleResume();
    }

    public void handleLoadingProductsSuccess(ArrayList<WishProduct> arrayList, int i, boolean z) {
        if (this.mAdapter.getRecentlyViewedProductsView() != null) {
            this.mAdapter.getRecentlyViewedProductsView().handleLoadingSuccess(arrayList, i, z);
        }
    }
}
