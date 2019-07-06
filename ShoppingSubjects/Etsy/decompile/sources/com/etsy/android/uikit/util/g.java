package com.etsy.android.uikit.util;

import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.Adapter;
import com.etsy.android.lib.a.i;
import com.etsy.android.uikit.view.CustomViewPageIndicator;
import com.etsy.android.uikit.view.CustomViewPageIndicator.b;
import com.viewpagerindicator.CirclePageIndicator;

/* compiled from: ImagePageIndicatorHelper */
public class g {
    /* access modifiers changed from: private */
    public CustomViewPageIndicator a;
    private CirclePageIndicator b;

    public g(View view) {
        this.a = (CustomViewPageIndicator) view.findViewById(i.custom_page_indicator);
        this.b = (CirclePageIndicator) view.findViewById(i.circle_page_indicator);
        if (this.a == null && this.b == null) {
            throw new RuntimeException("No Indicator found in this View hierarchy");
        }
    }

    public void a(ViewPager viewPager, OnPageChangeListener onPageChangeListener, Adapter adapter) {
        if (this.a != null) {
            this.a.setViewPager(viewPager);
            this.a.setOnPageChangeListener(onPageChangeListener);
            this.a.setIndicatorClickListener(new b() {
                public void a(int i) {
                    g.this.a.setCurrentItem(i);
                }
            });
            this.a.setAdapter(adapter);
        }
        if (this.b != null) {
            this.b.setViewPager(viewPager);
            this.b.setOnPageChangeListener(onPageChangeListener);
        }
        a(viewPager.getChildCount());
    }

    public void a() {
        if (this.a != null) {
            this.a.setAdapter(null);
        }
        if (this.b != null) {
            this.b.setOnPageChangeListener(null);
        }
    }

    public void a(int i) {
        int i2 = i <= 1 ? 8 : 0;
        if (this.a != null) {
            this.a.setVisibility(i2);
        }
        if (this.b != null) {
            this.b.setVisibility(i2);
        }
    }
}
