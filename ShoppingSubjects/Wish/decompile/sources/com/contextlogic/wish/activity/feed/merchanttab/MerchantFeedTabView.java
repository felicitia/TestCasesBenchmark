package com.contextlogic.wish.activity.feed.merchanttab;

import android.annotation.SuppressLint;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.activity.feed.ProductFeedFragment;
import com.contextlogic.wish.ui.viewpager.CollapsibleTabStripLayout;

@SuppressLint({"ViewConstructor"})
public class MerchantFeedTabView extends LinearLayout {
    public MerchantFeedTabView(int i, DrawerActivity drawerActivity, ProductFeedFragment productFeedFragment) {
        super(drawerActivity);
        init(i, drawerActivity, productFeedFragment);
    }

    private void init(int i, DrawerActivity drawerActivity, ProductFeedFragment productFeedFragment) {
        inflate(drawerActivity, R.layout.merchant_feed_tab_view, this);
        setOrientation(1);
        setLayoutParams(new LayoutParams(-1, -1));
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        MerchantFeedTabPagerAdapter merchantFeedTabPagerAdapter = new MerchantFeedTabPagerAdapter(drawerActivity);
        viewPager.setAdapter(merchantFeedTabPagerAdapter);
        ((TabLayout) findViewById(R.id.tab_bar)).setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(merchantFeedTabPagerAdapter);
        CollapsibleTabStripLayout collapsibleTabStripLayout = (CollapsibleTabStripLayout) findViewById(R.id.collapsible_tab_strip_layout);
        collapsibleTabStripLayout.setupWithTabStripInterface(productFeedFragment);
        collapsibleTabStripLayout.setParentViewPagerPosition(i);
    }
}
