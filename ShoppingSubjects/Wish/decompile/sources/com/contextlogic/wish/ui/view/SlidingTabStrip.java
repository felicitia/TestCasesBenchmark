package com.contextlogic.wish.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Typeface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.ui.text.ThemedTextView;
import com.contextlogic.wish.util.FontUtil;
import java.util.Locale;

public abstract class SlidingTabStrip extends HorizontalScrollView {
    private boolean alignJustify;
    /* access modifiers changed from: private */
    public boolean canClickTabs;
    /* access modifiers changed from: protected */
    public int currentPosition;
    /* access modifiers changed from: protected */
    public float currentPositionOffset;
    private LayoutParams defaultTabLayoutParams;
    public OnPageChangeListener delegatePageListener;
    protected int dividerColor;
    protected int dividerPadding;
    protected Paint dividerPaint;
    private int dividerWidth;
    private LayoutParams expandedTabLayoutParams;
    protected int indicatorColor;
    protected int indicatorHeight;
    private int lastScrollX;
    private Locale locale;
    /* access modifiers changed from: private */
    public OnTabClickListener mOnTabClickListener;
    protected Paint rectPaint;
    private int scrollOffset;
    private boolean shouldExpand;
    private int tabBackgroundResId;
    protected boolean[] tabBadged;
    protected int tabCount;
    private int tabPadding;
    private int tabTextColor;
    private int tabTextSize;
    private Typeface tabTypeface;
    private int tabTypefaceStyle;
    protected TabType[] tabTypes;
    /* access modifiers changed from: protected */
    public LinearLayout tabsContainer;
    private boolean textAllCaps;
    protected int underlineColor;
    protected int underlineHeight;

    public interface IconTabProvider {
        int getPageIconResId(int i);
    }

    public interface OnTabClickListener {
        void onTabSelected(int i);
    }

    static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR = new Creator<SavedState>() {
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        int currentPosition;

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.currentPosition = parcel.readInt();
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.currentPosition);
        }
    }

    public enum TabType {
        TEXT_TAB,
        ICON_TAB
    }

    public SlidingTabStrip(Context context) {
        this(context, null);
    }

    public SlidingTabStrip(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SlidingTabStrip(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.currentPosition = 0;
        this.currentPositionOffset = 0.0f;
        this.indicatorColor = -10066330;
        this.underlineColor = 436207616;
        this.dividerColor = 436207616;
        this.shouldExpand = false;
        this.textAllCaps = false;
        this.alignJustify = false;
        this.scrollOffset = 52;
        this.indicatorHeight = 8;
        this.underlineHeight = 2;
        this.dividerPadding = 12;
        this.tabPadding = 24;
        this.dividerWidth = 1;
        this.tabTextSize = 12;
        this.tabTextColor = -10066330;
        this.tabTypeface = null;
        this.tabTypefaceStyle = 1;
        this.lastScrollX = 0;
        this.tabBackgroundResId = R.drawable.background_tab;
        setFillViewport(true);
        setWillNotDraw(false);
        this.tabsContainer = new LinearLayout(context);
        this.tabsContainer.setOrientation(0);
        this.tabsContainer.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        addView(this.tabsContainer);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.scrollOffset = (int) TypedValue.applyDimension(1, (float) this.scrollOffset, displayMetrics);
        this.indicatorHeight = (int) TypedValue.applyDimension(1, (float) this.indicatorHeight, displayMetrics);
        this.underlineHeight = (int) TypedValue.applyDimension(1, (float) this.underlineHeight, displayMetrics);
        this.dividerPadding = (int) TypedValue.applyDimension(1, (float) this.dividerPadding, displayMetrics);
        this.tabPadding = (int) TypedValue.applyDimension(1, (float) this.tabPadding, displayMetrics);
        this.dividerWidth = (int) TypedValue.applyDimension(1, (float) this.dividerWidth, displayMetrics);
        this.tabTextSize = (int) TypedValue.applyDimension(2, (float) this.tabTextSize, displayMetrics);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{16842901});
        this.tabTextSize = obtainStyledAttributes.getDimensionPixelSize(0, this.tabTextSize);
        obtainStyledAttributes.recycle();
        TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, new int[]{16842904});
        this.tabTextColor = obtainStyledAttributes2.getColor(0, this.tabTextColor);
        obtainStyledAttributes2.recycle();
        TypedArray obtainStyledAttributes3 = context.obtainStyledAttributes(attributeSet, R.styleable.PagerSlidingTabStrip);
        this.indicatorColor = obtainStyledAttributes3.getColor(3, this.indicatorColor);
        this.underlineColor = obtainStyledAttributes3.getColor(10, this.underlineColor);
        this.dividerColor = obtainStyledAttributes3.getColor(1, this.dividerColor);
        this.indicatorHeight = obtainStyledAttributes3.getDimensionPixelSize(4, this.indicatorHeight);
        this.underlineHeight = obtainStyledAttributes3.getDimensionPixelSize(11, this.underlineHeight);
        this.dividerPadding = obtainStyledAttributes3.getDimensionPixelSize(2, this.dividerPadding);
        this.tabPadding = obtainStyledAttributes3.getDimensionPixelSize(8, this.tabPadding);
        this.tabBackgroundResId = obtainStyledAttributes3.getResourceId(7, this.tabBackgroundResId);
        this.shouldExpand = obtainStyledAttributes3.getBoolean(6, this.shouldExpand);
        this.scrollOffset = obtainStyledAttributes3.getDimensionPixelSize(5, this.scrollOffset);
        this.textAllCaps = obtainStyledAttributes3.getBoolean(9, this.textAllCaps);
        this.alignJustify = obtainStyledAttributes3.getBoolean(0, this.alignJustify);
        obtainStyledAttributes3.recycle();
        this.rectPaint = new Paint();
        this.rectPaint.setAntiAlias(true);
        this.rectPaint.setStyle(Style.FILL);
        this.dividerPaint = new Paint();
        this.dividerPaint.setAntiAlias(true);
        this.dividerPaint.setStrokeWidth((float) this.dividerWidth);
        this.defaultTabLayoutParams = new LayoutParams(-2, -1);
        this.expandedTabLayoutParams = new LayoutParams(0, -1, 1.0f);
        if (this.locale == null) {
            this.locale = getResources().getConfiguration().locale;
        }
        this.canClickTabs = true;
        setTypeface(FontUtil.getTypefaceForStyle(1), 1);
    }

    public void setOnTabClickListener(OnTabClickListener onTabClickListener) {
        this.mOnTabClickListener = onTabClickListener;
    }

    public void setTabTypes(TabType[] tabTypeArr) {
        this.tabTypes = tabTypeArr;
    }

    public TabType getTabType(int i) {
        if (this.tabTypes == null || i < 0 || i >= this.tabTypes.length) {
            return TabType.TEXT_TAB;
        }
        return this.tabTypes[i];
    }

    public void setTabBadged(boolean[] zArr) {
        this.tabBadged = zArr;
    }

    public boolean getTabBadged(int i) {
        if (this.tabBadged == null || i < 0 || i >= this.tabBadged.length) {
            return false;
        }
        return this.tabBadged[i];
    }

    /* access modifiers changed from: protected */
    public void addTextTab(int i, String str) {
        ThemedTextView themedTextView = new ThemedTextView(getContext());
        themedTextView.setText(str);
        themedTextView.setGravity(17);
        themedTextView.setSingleLine();
        addTab(i, themedTextView);
    }

    /* access modifiers changed from: protected */
    public void addTextTabWithIcon(int i, String str, boolean z) {
        View inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.tab_bar_text_active_icon, null);
        ((TextView) inflate.findViewById(R.id.tab_bar_text)).setText(str);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.tab_bar_active_icon_indicator);
        if (!z || !ExperimentDataCenter.getInstance().shouldShowRedDot()) {
            imageView.setVisibility(8);
        } else {
            imageView.setVisibility(0);
        }
        addTab(i, inflate);
    }

    /* access modifiers changed from: protected */
    public void addIconTab(int i, int i2, boolean z) {
        if (!z || !ExperimentDataCenter.getInstance().shouldShowRedDot()) {
            ImageButton imageButton = new ImageButton(getContext());
            imageButton.setImageResource(i2);
            addTab(i, imageButton);
            return;
        }
        View inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.tab_bar_active_icon, null);
        ((ImageButton) inflate.findViewById(R.id.tab_bar_active_icon_image)).setImageResource(i2);
        addTab(i, inflate);
    }

    /* access modifiers changed from: protected */
    public void addIconTab(int i, int i2) {
        addIconTab(i, i2, false);
    }

    private void addTab(final int i, View view) {
        view.setFocusable(true);
        view.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (SlidingTabStrip.this.mOnTabClickListener != null && SlidingTabStrip.this.canClickTabs) {
                    SlidingTabStrip.this.mOnTabClickListener.onTabSelected(i);
                }
            }
        });
        view.setPadding(this.tabPadding, 0, this.tabPadding, 0);
        this.tabsContainer.addView(view, i, this.shouldExpand ? this.expandedTabLayoutParams : this.defaultTabLayoutParams);
    }

    /* access modifiers changed from: protected */
    public void updateTabStyles() {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        for (int i = 0; i < this.tabCount; i++) {
            View childAt = this.tabsContainer.getChildAt(i);
            childAt.setBackgroundResource(this.tabBackgroundResId);
            if (childAt instanceof TextView) {
                TextView textView = (TextView) childAt;
                textView.setTextSize(0, (float) this.tabTextSize);
                textView.setTypeface(this.tabTypeface, this.tabTypefaceStyle);
                textView.setTextColor(this.tabTextColor);
                if (this.alignJustify && displayMetrics != null) {
                    textView.setWidth(displayMetrics.widthPixels / this.tabCount);
                }
                if (this.textAllCaps) {
                    textView.setAllCaps(true);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void scrollToChild(int i, int i2) {
        if (this.tabCount != 0 && i < this.tabsContainer.getChildCount()) {
            int left = this.tabsContainer.getChildAt(i).getLeft() + i2;
            if (i > 0 || i2 > 0) {
                left -= this.scrollOffset;
            }
            if (left != this.lastScrollX) {
                this.lastScrollX = left;
                scrollTo(left, 0);
            }
        }
    }

    public void setIndicatorColor(int i) {
        this.indicatorColor = i;
        invalidate();
    }

    public void setIndicatorColorResource(int i) {
        this.indicatorColor = getResources().getColor(i);
        invalidate();
    }

    public int getIndicatorColor() {
        return this.indicatorColor;
    }

    public void setIndicatorHeight(int i) {
        this.indicatorHeight = i;
        invalidate();
    }

    public int getIndicatorHeight() {
        return this.indicatorHeight;
    }

    public void setUnderlineColor(int i) {
        this.underlineColor = i;
        invalidate();
    }

    public void setUnderlineColorResource(int i) {
        this.underlineColor = getResources().getColor(i);
        invalidate();
    }

    public int getUnderlineColor() {
        return this.underlineColor;
    }

    public void setDividerColor(int i) {
        this.dividerColor = i;
        invalidate();
    }

    public void setDividerColorResource(int i) {
        this.dividerColor = getResources().getColor(i);
        invalidate();
    }

    public int getDividerColor() {
        return this.dividerColor;
    }

    public void setUnderlineHeight(int i) {
        this.underlineHeight = i;
        invalidate();
    }

    public int getUnderlineHeight() {
        return this.underlineHeight;
    }

    public void setDividerPadding(int i) {
        this.dividerPadding = i;
        invalidate();
    }

    public int getDividerPadding() {
        return this.dividerPadding;
    }

    public void setScrollOffset(int i) {
        this.scrollOffset = i;
        invalidate();
    }

    public int getScrollOffset() {
        return this.scrollOffset;
    }

    public void setShouldExpand(boolean z) {
        this.shouldExpand = z;
        requestLayout();
    }

    public void setAlignJustify(boolean z) {
        this.alignJustify = z;
        updateTabStyles();
    }

    public boolean getShouldExpand() {
        return this.shouldExpand;
    }

    public void setAllCaps(boolean z) {
        this.textAllCaps = z;
    }

    public void setTextSize(int i) {
        this.tabTextSize = i;
        updateTabStyles();
    }

    public int getTextSize() {
        return this.tabTextSize;
    }

    public void setTextColor(int i) {
        this.tabTextColor = i;
        updateTabStyles();
    }

    public void setTextColorResource(int i) {
        this.tabTextColor = getResources().getColor(i);
        updateTabStyles();
    }

    public int getTextColor() {
        return this.tabTextColor;
    }

    public void setTypeface(Typeface typeface, int i) {
        this.tabTypeface = typeface;
        this.tabTypefaceStyle = i;
        updateTabStyles();
    }

    public void setTabBackground(int i) {
        this.tabBackgroundResId = i;
    }

    public int getTabBackground() {
        return this.tabBackgroundResId;
    }

    public void setTabPaddingLeftRight(int i) {
        this.tabPadding = i;
        updateTabStyles();
    }

    public int getTabPaddingLeftRight() {
        return this.tabPadding;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.currentPosition = savedState.currentPosition;
        requestLayout();
    }

    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.currentPosition = this.currentPosition;
        return savedState;
    }

    public void switchToTab(int i) {
        if (this.tabsContainer != null && this.tabsContainer.getChildCount() > i) {
            this.tabsContainer.getChildAt(i).callOnClick();
        }
    }
}
