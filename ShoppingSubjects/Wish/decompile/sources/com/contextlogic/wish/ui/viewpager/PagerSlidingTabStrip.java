package com.contextlogic.wish.ui.viewpager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Build.VERSION;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import com.contextlogic.wish.ui.view.SlidingTabStrip;
import com.contextlogic.wish.ui.view.SlidingTabStrip.IconTabProvider;
import com.contextlogic.wish.ui.view.SlidingTabStrip.OnTabClickListener;
import com.contextlogic.wish.ui.view.SlidingTabStrip.TabType;

public class PagerSlidingTabStrip extends SlidingTabStrip {
    private boolean mHasBadges;
    private int mIndicatorPadding;
    private int mTextColorSelected;
    private int mTextColorUnselected;
    private final PageListener pageListener;
    /* access modifiers changed from: private */
    public ViewPager pager;

    private class PageListener implements OnPageChangeListener {
        private PageListener() {
        }

        public void onPageScrolled(int i, float f, int i2) {
            PagerSlidingTabStrip.this.currentPosition = i;
            PagerSlidingTabStrip.this.currentPositionOffset = f;
            PagerSlidingTabStrip.this.scrollToChild(i, (int) (((float) PagerSlidingTabStrip.this.tabsContainer.getChildAt(i).getWidth()) * f));
            PagerSlidingTabStrip.this.invalidate();
            if (PagerSlidingTabStrip.this.delegatePageListener != null) {
                PagerSlidingTabStrip.this.delegatePageListener.onPageScrolled(i, f, i2);
            }
        }

        public void onPageScrollStateChanged(int i) {
            if (i == 0) {
                PagerSlidingTabStrip.this.scrollToChild(PagerSlidingTabStrip.this.pager.getCurrentItem(), 0);
            }
            if (PagerSlidingTabStrip.this.delegatePageListener != null) {
                PagerSlidingTabStrip.this.delegatePageListener.onPageScrollStateChanged(i);
            }
        }

        public void onPageSelected(int i) {
            if (PagerSlidingTabStrip.this.delegatePageListener != null) {
                PagerSlidingTabStrip.this.delegatePageListener.onPageSelected(i);
            }
        }
    }

    public PagerSlidingTabStrip(Context context) {
        this(context, null);
    }

    public PagerSlidingTabStrip(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PagerSlidingTabStrip(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.pageListener = new PageListener();
        this.mHasBadges = false;
        this.mIndicatorPadding = 0;
    }

    public void setViewPager(ViewPager viewPager) {
        this.pager = viewPager;
        setOnTabClickListener(new OnTabClickListener() {
            public void onTabSelected(int i) {
                PagerSlidingTabStrip.this.pager.setCurrentItem(i);
            }
        });
        if (viewPager.getAdapter() == null) {
            throw new IllegalStateException("ViewPager does not have adapter instance.");
        }
        viewPager.setOnPageChangeListener(this.pageListener);
        notifyDataSetChanged();
    }

    public void setOnPageChangeListener(OnPageChangeListener onPageChangeListener) {
        this.delegatePageListener = onPageChangeListener;
    }

    public void notifyDataSetChanged() {
        this.tabsContainer.removeAllViews();
        this.tabCount = this.pager.getAdapter().getCount();
        for (int i = 0; i < this.tabCount; i++) {
            if ((this.pager.getAdapter() instanceof IconTabProvider) && this.tabTypes != null && getTabType(i) == TabType.ICON_TAB) {
                addIconTab(i, ((IconTabProvider) this.pager.getAdapter()).getPageIconResId(i), getTabBadged(i));
            } else if (this.mHasBadges) {
                addTextTabWithIcon(i, this.pager.getAdapter().getPageTitle(i).toString(), getTabBadged(i));
            } else {
                addTextTab(i, this.pager.getAdapter().getPageTitle(i).toString());
            }
        }
        updateTabStyles();
        getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
            @SuppressLint({"NewApi"})
            public void onGlobalLayout() {
                if (VERSION.SDK_INT < 16) {
                    PagerSlidingTabStrip.this.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                } else {
                    PagerSlidingTabStrip.this.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
                PagerSlidingTabStrip.this.currentPosition = PagerSlidingTabStrip.this.pager.getCurrentItem();
                PagerSlidingTabStrip.this.scrollToChild(PagerSlidingTabStrip.this.currentPosition, 0);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!isInEditMode() && this.tabCount != 0) {
            int height = getHeight();
            this.rectPaint.setColor(this.underlineColor);
            float f = (float) height;
            canvas.drawRect(0.0f, (float) (height - this.underlineHeight), (float) this.tabsContainer.getWidth(), f, this.rectPaint);
            this.rectPaint.setColor(this.indicatorColor);
            View childAt = this.tabsContainer.getChildAt(this.currentPosition);
            float left = (float) childAt.getLeft();
            float right = (float) childAt.getRight();
            if (this.currentPositionOffset > 0.0f && this.currentPosition < this.tabCount - 1) {
                View childAt2 = this.tabsContainer.getChildAt(this.currentPosition + 1);
                float left2 = (float) childAt2.getLeft();
                left = (this.currentPositionOffset * left2) + ((1.0f - this.currentPositionOffset) * left);
                right = (this.currentPositionOffset * ((float) childAt2.getRight())) + ((1.0f - this.currentPositionOffset) * right);
            }
            canvas.drawRect(left + ((float) this.mIndicatorPadding), (float) (height - this.indicatorHeight), right - ((float) this.mIndicatorPadding), f, this.rectPaint);
            this.dividerPaint.setColor(this.dividerColor);
            for (int i = 0; i < this.tabCount - 1; i++) {
                View childAt3 = this.tabsContainer.getChildAt(i);
                canvas.drawLine((float) childAt3.getRight(), (float) this.dividerPadding, (float) childAt3.getRight(), (float) (height - this.dividerPadding), this.dividerPaint);
            }
        }
    }

    public void setHasBadges() {
        this.mHasBadges = true;
    }

    public void setupRefreshColors(int i, int i2) {
        this.mTextColorSelected = i;
        this.mTextColorUnselected = i2;
    }

    public void setIndicatorPadding(int i) {
        this.mIndicatorPadding = i;
    }
}
