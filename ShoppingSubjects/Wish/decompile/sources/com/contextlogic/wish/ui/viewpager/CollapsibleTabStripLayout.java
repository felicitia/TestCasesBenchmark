package com.contextlogic.wish.ui.viewpager;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.AppBarLayout.OnOffsetChangedListener;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.view.ViewPager.SimpleOnPageChangeListener;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;
import android.widget.LinearLayout;
import com.contextlogic.wish.R;

public class CollapsibleTabStripLayout extends LinearLayout {
    /* access modifiers changed from: private */
    public View mBanner;
    /* access modifiers changed from: private */
    public int mCurrentOffset = 0;
    /* access modifiers changed from: private */
    public boolean mCurrentPageSelected = true;
    private OnOffsetChangedListener mOnOffsetChangedListener;
    private OnPageChangeListener mPageChangeListener;
    private ViewPager mParentViewPager;
    /* access modifiers changed from: private */
    public int mParentViewPagerPosition = -1;
    /* access modifiers changed from: private */
    public View mTabStrip;
    /* access modifiers changed from: private */
    public BaseTabStripInterface mTabStripInterface;

    private class OffsetUpdateListener implements OnOffsetChangedListener {
        private OffsetUpdateListener() {
        }

        public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
            boolean z;
            int i2;
            int height = (CollapsibleTabStripLayout.this.getHeight() + CollapsibleTabStripLayout.this.mTabStripInterface.getTabAreaSize()) - CollapsibleTabStripLayout.this.mTabStrip.getHeight();
            CollapsibleTabStripLayout.getViewOffsetHelper(CollapsibleTabStripLayout.this.mTabStrip).setTopAndBottomOffset(Math.max(0, Math.abs(i) - height));
            if (Math.abs(i) != height || CollapsibleTabStripLayout.this.mBanner == null || CollapsibleTabStripLayout.this.mBanner.getVisibility() == 8) {
                z = false;
            } else {
                CollapsibleTabStripLayout.this.mBanner.setVisibility(8);
                z = true;
            }
            if (CollapsibleTabStripLayout.this.mCurrentPageSelected) {
                if (z) {
                    i2 = -CollapsibleTabStripLayout.this.mTabStripInterface.getTabAreaSize();
                    CollapsibleTabStripLayout.this.mCurrentOffset = -CollapsibleTabStripLayout.this.mTabStripInterface.getTabAreaSize();
                } else {
                    i2 = Math.min(Math.max(CollapsibleTabStripLayout.this.mTabStripInterface.getTabAreaOffset() + (i - CollapsibleTabStripLayout.this.mCurrentOffset), -CollapsibleTabStripLayout.this.mTabStripInterface.getTabAreaSize()), 0);
                    CollapsibleTabStripLayout.this.mCurrentOffset = i;
                }
                if (CollapsibleTabStripLayout.this.mTabStripInterface != null) {
                    CollapsibleTabStripLayout.this.mTabStripInterface.setTabAreaOffset(i2);
                }
            }
        }
    }

    private static class ViewOffsetHelper {
        private int mLayoutLeft;
        private int mLayoutTop;
        private int mOffsetLeft;
        private int mOffsetTop;
        private final View mView;

        private ViewOffsetHelper(View view) {
            this.mView = view;
        }

        /* access modifiers changed from: private */
        public void onViewLayout() {
            this.mLayoutTop = this.mView.getTop();
            this.mLayoutLeft = this.mView.getLeft();
            updateOffsets();
        }

        private void updateOffsets() {
            ViewCompat.offsetTopAndBottom(this.mView, this.mOffsetTop - (this.mView.getTop() - this.mLayoutTop));
            ViewCompat.offsetLeftAndRight(this.mView, this.mOffsetLeft - (this.mView.getLeft() - this.mLayoutLeft));
        }

        /* access modifiers changed from: private */
        public boolean setTopAndBottomOffset(int i) {
            if (this.mOffsetTop == i) {
                return false;
            }
            this.mOffsetTop = i;
            updateOffsets();
            return true;
        }
    }

    public CollapsibleTabStripLayout(Context context) {
        super(context);
        init();
    }

    public CollapsibleTabStripLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public CollapsibleTabStripLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        setOrientation(1);
    }

    public void setupWithTabStripInterface(BaseTabStripInterface baseTabStripInterface) {
        this.mTabStripInterface = baseTabStripInterface;
    }

    public void setParentViewPagerPosition(int i) {
        this.mParentViewPagerPosition = i;
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        ensureTarget();
        getViewOffsetHelper(this.mTabStrip).onViewLayout();
        setMinimumHeight(this.mTabStrip.getHeight());
    }

    private void ensureTarget() {
        if (this.mTabStrip == null) {
            int i = 0;
            while (true) {
                if (i >= getChildCount()) {
                    break;
                }
                View childAt = getChildAt(i);
                if (childAt instanceof TabLayout) {
                    this.mTabStrip = childAt;
                    break;
                }
                i++;
            }
        }
        if (this.mTabStrip == null) {
            throw new RuntimeException("CollapsibleTabStripLayout must include a TabLayout as one of its nested children.");
        } else if (this.mBanner == null && getChildCount() > 1 && !(getChildAt(0) instanceof TabLayout)) {
            this.mBanner = getChildAt(0);
        }
    }

    private void findParentViewPager(View view) {
        if (this.mParentViewPager == null) {
            ViewParent parent = view.getParent();
            if (parent instanceof ViewPager) {
                this.mParentViewPager = (ViewPager) parent;
            } else if (parent instanceof View) {
                findParentViewPager((View) parent);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        final ViewParent parent = getParent();
        if (parent instanceof AppBarLayout) {
            if (this.mOnOffsetChangedListener == null) {
                this.mOnOffsetChangedListener = new OffsetUpdateListener();
            }
            ((AppBarLayout) parent).addOnOffsetChangedListener(this.mOnOffsetChangedListener);
        }
        if (this.mParentViewPagerPosition != -1) {
            findParentViewPager(this);
            if (this.mParentViewPager != null) {
                if (this.mPageChangeListener == null) {
                    this.mPageChangeListener = new SimpleOnPageChangeListener() {
                        public void onPageSelected(int i) {
                            CollapsibleTabStripLayout.this.mCurrentPageSelected = CollapsibleTabStripLayout.this.mParentViewPagerPosition == i;
                            if (CollapsibleTabStripLayout.this.mCurrentPageSelected && CollapsibleTabStripLayout.this.mTabStrip != null && (parent instanceof AppBarLayout)) {
                                ((AppBarLayout) parent).setExpanded(true);
                            }
                        }
                    };
                }
                this.mParentViewPager.addOnPageChangeListener(this.mPageChangeListener);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ViewParent parent = getParent();
        if (this.mOnOffsetChangedListener != null && (parent instanceof AppBarLayout)) {
            ((AppBarLayout) parent).removeOnOffsetChangedListener(this.mOnOffsetChangedListener);
        }
        if (this.mParentViewPager != null && this.mPageChangeListener != null) {
            this.mParentViewPager.removeOnPageChangeListener(this.mPageChangeListener);
            this.mParentViewPager = null;
        }
    }

    /* access modifiers changed from: private */
    public static ViewOffsetHelper getViewOffsetHelper(View view) {
        ViewOffsetHelper viewOffsetHelper = (ViewOffsetHelper) view.getTag(R.id.view_offset_helper);
        if (viewOffsetHelper != null) {
            return viewOffsetHelper;
        }
        ViewOffsetHelper viewOffsetHelper2 = new ViewOffsetHelper(view);
        view.setTag(R.id.view_offset_helper, viewOffsetHelper2);
        return viewOffsetHelper2;
    }
}
