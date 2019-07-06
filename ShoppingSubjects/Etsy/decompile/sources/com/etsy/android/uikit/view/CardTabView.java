package com.etsy.android.uikit.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabContentFactory;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import com.etsy.android.lib.a.i;
import com.etsy.android.lib.a.k;
import com.etsy.android.lib.a.q;
import java.util.Locale;

public class CardTabView extends LinearLayout {
    private LayoutInflater mInflater;
    /* access modifiers changed from: private */
    public TabHost mTabHost;
    private int mTabTextId;
    private int mTabWidgetBackground;
    /* access modifiers changed from: private */
    public WrapHeightSwipeDisableViewPager mViewPager;

    private class a implements TabContentFactory {
        private a() {
        }

        public View createTabContent(String str) {
            return new View(CardTabView.this.getContext());
        }
    }

    public CardTabView(Context context) {
        super(context);
        init();
    }

    public CardTabView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initAttributes(attributeSet);
        init();
    }

    public CardTabView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initAttributes(attributeSet);
        init();
    }

    private void initAttributes(AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, q.EtsyTabView);
        this.mTabTextId = obtainStyledAttributes.getResourceId(q.EtsyTabView_tabLayout, 0);
        obtainStyledAttributes.recycle();
        TypedArray obtainStyledAttributes2 = getContext().obtainStyledAttributes(attributeSet, q.CardTabView);
        this.mTabWidgetBackground = obtainStyledAttributes2.getResourceId(q.CardTabView_tabWidgetBackground, 0);
        obtainStyledAttributes2.recycle();
    }

    private void init() {
        this.mInflater = LayoutInflater.from(getContext());
        LinearLayout linearLayout = (LinearLayout) this.mInflater.inflate(k.card_tabview, this, true);
        this.mTabHost = (TabHost) linearLayout.findViewById(16908306);
        this.mTabHost.setup();
        hideTabDividers(this.mTabHost);
        this.mViewPager = (WrapHeightSwipeDisableViewPager) linearLayout.findViewById(i.viewpager);
        this.mTabHost.setOnTabChangedListener(new OnTabChangeListener() {
            public void onTabChanged(String str) {
                CardTabView.this.mViewPager.setCurrentItem(CardTabView.this.mTabHost.getCurrentTab());
            }
        });
        if (this.mTabWidgetBackground > 0) {
            this.mTabHost.getTabWidget().setBackgroundResource(this.mTabWidgetBackground);
        }
        this.mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
            public void onPageScrollStateChanged(int i) {
            }

            public void onPageScrolled(int i, float f, int i2) {
            }

            public void onPageSelected(int i) {
                CardTabView.this.mTabHost.setCurrentTab(i);
            }
        });
    }

    private void hideTabDividers(TabHost tabHost) {
        tabHost.getTabWidget().setShowDividers(0);
    }

    public void setLayoutParams(LayoutParams layoutParams) {
        super.setLayoutParams(layoutParams);
        if (layoutParams == null) {
            return;
        }
        if (layoutParams.height == -2) {
            this.mViewPager.matchHeightOfMaxChild(true);
        } else {
            this.mViewPager.matchHeightOfMaxChild(false);
        }
    }

    public void setAdapter(PagerAdapter pagerAdapter) {
        this.mViewPager.setAdapter(pagerAdapter);
    }

    public int getCurrentTab() {
        return this.mTabHost.getCurrentTab();
    }

    public void setCurrentTab(int i) {
        this.mTabHost.setCurrentTab(i);
    }

    public void addTab(int i, int i2) {
        addTab(getContext().getString(i), i2);
    }

    public void addTab(String str, int i) {
        LinearLayout linearLayout = (LinearLayout) this.mInflater.inflate(this.mTabTextId, this.mTabHost.getTabWidget(), false);
        TextView textView = (TextView) linearLayout.findViewById(16908308);
        if (str != null) {
            textView.setText(str.toUpperCase(Locale.getDefault()));
        }
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(0, i, 1.0f));
        TabSpec newTabSpec = this.mTabHost.newTabSpec(str);
        newTabSpec.setIndicator(linearLayout);
        newTabSpec.setContent(new a());
        this.mTabHost.addTab(newTabSpec);
    }
}
