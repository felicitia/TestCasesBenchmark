package com.etsy.android.uikit.view;

import android.content.Context;
import android.database.DataSetObserver;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Adapter;
import android.widget.LinearLayout;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.viewpagerindicator.PageIndicator;

public class CustomViewPageIndicator extends LinearLayout implements PageIndicator {
    private Adapter mAdapter;
    private int mCurrentPage;
    /* access modifiers changed from: private */
    public b mIndicatorClickListener;
    private a mObserver;
    private OnPageChangeListener mPageChangeListener;
    private int mScrollState;
    private ViewPager mViewPager;

    class a extends DataSetObserver {
        a() {
        }

        public void onChanged() {
            super.onChanged();
            CustomViewPageIndicator.this.notifyDataSetChanged();
        }
    }

    public interface b {
        void a(int i);
    }

    public CustomViewPageIndicator(Context context) {
        super(context);
        setupLayout();
    }

    public CustomViewPageIndicator(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setupLayout();
    }

    public CustomViewPageIndicator(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setupLayout();
    }

    private void setupLayout() {
        this.mObserver = new a();
    }

    public void setOnPageChangeListener(OnPageChangeListener onPageChangeListener) {
        this.mPageChangeListener = onPageChangeListener;
    }

    public void setIndicatorClickListener(b bVar) {
        this.mIndicatorClickListener = bVar;
    }

    public void setViewPager(ViewPager viewPager) {
        if (this.mViewPager != viewPager) {
            if (this.mViewPager != null) {
                this.mViewPager.setOnPageChangeListener(null);
            }
            if (viewPager.getAdapter() == null) {
                throw new IllegalStateException("ViewPager does not have adapter instance.");
            }
            this.mViewPager = viewPager;
            this.mViewPager.setOnPageChangeListener(this);
            invalidate();
        }
    }

    public void setViewPager(ViewPager viewPager, int i) {
        setViewPager(viewPager);
        setCurrentItem(i);
    }

    public void setAdapter(Adapter adapter) {
        if (this.mAdapter != null) {
            this.mAdapter.unregisterDataSetObserver(this.mObserver);
        }
        this.mAdapter = adapter;
        if (this.mAdapter != null) {
            this.mAdapter.registerDataSetObserver(this.mObserver);
        }
        this.mObserver.onChanged();
    }

    public void setCurrentItem(int i) {
        if (this.mViewPager == null) {
            throw new IllegalStateException("ViewPager has not been bound.");
        }
        this.mViewPager.setCurrentItem(i);
        this.mCurrentPage = i;
        int childCount = getChildCount();
        int i2 = 0;
        while (i2 < childCount) {
            getChildAt(i2).setSelected(i2 == i);
            i2++;
        }
        invalidate();
    }

    public void notifyDataSetChanged() {
        updateIndicators();
        invalidate();
    }

    private void updateIndicators() {
        if (this.mAdapter != null) {
            removeAllViews();
            final int i = 0;
            while (i < this.mAdapter.getCount()) {
                View view = this.mAdapter.getView(i, null, this);
                if (view != null) {
                    view.setSelected(i == this.mCurrentPage);
                    view.setOnClickListener(new TrackingOnClickListener(AnalyticsLogAttribute.POSITION, Integer.valueOf(i)) {
                        public void onViewClick(View view) {
                            if (CustomViewPageIndicator.this.mIndicatorClickListener != null) {
                                CustomViewPageIndicator.this.mIndicatorClickListener.a(i);
                            }
                        }
                    });
                    addView(view);
                }
                i++;
            }
            requestLayout();
        }
    }

    public void onPageScrolled(int i, float f, int i2) {
        this.mCurrentPage = i;
        invalidate();
        if (this.mPageChangeListener != null) {
            this.mPageChangeListener.onPageScrolled(i, f, i2);
        }
    }

    public void onPageSelected(int i) {
        setCurrentItem(i);
        if (this.mScrollState == 0) {
            this.mCurrentPage = i;
            invalidate();
        }
        if (this.mPageChangeListener != null) {
            this.mPageChangeListener.onPageSelected(i);
        }
    }

    public void onPageScrollStateChanged(int i) {
        this.mScrollState = i;
        if (this.mPageChangeListener != null) {
            this.mPageChangeListener.onPageScrollStateChanged(i);
        }
    }
}
