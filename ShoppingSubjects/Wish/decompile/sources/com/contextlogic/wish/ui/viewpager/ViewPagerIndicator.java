package com.contextlogic.wish.ui.viewpager;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.contextlogic.wish.R;

public class ViewPagerIndicator extends LinearLayout {
    private int mIndicatorHeight;
    private int mIndicatorWidth;
    /* access modifiers changed from: private */
    public Drawable mOffDrawable;
    /* access modifiers changed from: private */
    public Drawable mOnDrawable;
    private ViewPager mPager;

    public ViewPagerIndicator(Context context) {
        this(context, null);
    }

    public ViewPagerIndicator(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ViewPagerIndicator(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mIndicatorWidth = -1;
        this.mIndicatorHeight = -1;
        init();
    }

    private void init() {
        setOrientation(0);
        setLayoutParams(new LayoutParams(-2, -2));
        setSpacing(getResources().getDimensionPixelSize(R.dimen.four_padding));
        setIndicatorSize(getResources().getDimensionPixelSize(R.dimen.view_pager_indicator), getResources().getDimensionPixelSize(R.dimen.view_pager_indicator));
    }

    public void setup(ViewPager viewPager) {
        setup(viewPager, -1);
    }

    public void setup(ViewPager viewPager, int i) {
        this.mPager = viewPager;
        setupDefaultIndicatorDrawables(i);
        addIndicatorViews();
        viewPager.addOnPageChangeListener(getOnPageChangeListener());
    }

    private void addIndicatorViews() {
        if (this.mPager != null && this.mPager.getAdapter() != null) {
            removeAllViews();
            int i = 0;
            while (i < this.mPager.getAdapter().getCount()) {
                View view = new View(getContext());
                view.setLayoutParams(new LayoutParams(this.mIndicatorWidth, this.mIndicatorHeight));
                view.setBackground(i == this.mPager.getCurrentItem() ? this.mOnDrawable : this.mOffDrawable);
                addView(view);
                i++;
            }
        }
    }

    private OnPageChangeListener getOnPageChangeListener() {
        return new OnPageChangeListener() {
            public void onPageScrollStateChanged(int i) {
            }

            public void onPageScrolled(int i, float f, int i2) {
            }

            public void onPageSelected(int i) {
                int i2 = 0;
                while (i2 < ViewPagerIndicator.this.getChildCount()) {
                    ViewPagerIndicator.this.getChildAt(i2).setBackground(i2 == i ? ViewPagerIndicator.this.mOnDrawable : ViewPagerIndicator.this.mOffDrawable);
                    i2++;
                }
            }
        };
    }

    private void setupDefaultIndicatorDrawables(int i) {
        if (this.mOffDrawable == null) {
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setShape(1);
            gradientDrawable.setColor(0);
            gradientDrawable.setStroke(getResources().getDimensionPixelSize(R.dimen.view_pager_indicator_stroke), i);
            this.mOffDrawable = gradientDrawable;
        }
        if (this.mOnDrawable == null) {
            GradientDrawable gradientDrawable2 = new GradientDrawable();
            gradientDrawable2.setShape(1);
            gradientDrawable2.setColor(i);
            this.mOnDrawable = gradientDrawable2;
        }
    }

    private Drawable createDividerDrawable(int i) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(0);
        gradientDrawable.setShape(0);
        gradientDrawable.setSize(i, i);
        return gradientDrawable;
    }

    public void setSpacing(int i) {
        setDividerDrawable(createDividerDrawable(i));
        setShowDividers(2);
    }

    public void setIndicatorSize(int i, int i2) {
        this.mIndicatorWidth = i;
        this.mIndicatorHeight = i2;
        addIndicatorViews();
    }
}
