package com.contextlogic.wish.ui.listview;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.widget.BaseAdapter;
import com.contextlogic.wish.ui.view.SlidingTabStrip;
import com.contextlogic.wish.ui.view.SlidingTabStrip.TabType;
import java.util.ArrayList;

public class ListViewTabStrip extends SlidingTabStrip {
    private ArrayList<BaseAdapter> mAdapters;

    public interface IconTabProvider {
        int getPageIconResId();
    }

    public interface OnTabClickListener {
        void onTabSelected(int i);
    }

    public interface TextTabProvider {
        String getPageTitle();
    }

    public ListViewTabStrip(Context context) {
        this(context, null);
    }

    public ListViewTabStrip(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ListViewTabStrip(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setup(ArrayList<? extends BaseAdapter> arrayList, final OnTabClickListener onTabClickListener) {
        setOnTabClickListener(new com.contextlogic.wish.ui.view.SlidingTabStrip.OnTabClickListener() {
            public void onTabSelected(int i) {
                ListViewTabStrip.this.currentPosition = i;
                onTabClickListener.onTabSelected(i);
            }
        });
        this.mAdapters = arrayList;
        notifyDataSetChanged();
    }

    public void notifyDataSetChanged() {
        this.tabsContainer.removeAllViews();
        this.tabCount = this.mAdapters.size();
        for (int i = 0; i < this.tabCount; i++) {
            if ((this.mAdapters.get(i) instanceof IconTabProvider) && this.tabTypes != null && this.tabTypes[i] == TabType.ICON_TAB) {
                addIconTab(i, ((IconTabProvider) this.mAdapters.get(i)).getPageIconResId());
            } else if ((this.mAdapters.get(i) instanceof TextTabProvider) && this.tabTypes != null && this.tabTypes[i] == TabType.TEXT_TAB) {
                addTextTab(i, ((TextTabProvider) this.mAdapters.get(i)).getPageTitle());
            }
        }
        updateTabStyles();
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
            Canvas canvas2 = canvas;
            canvas2.drawRect((float) childAt.getLeft(), (float) (height - this.indicatorHeight), (float) childAt.getRight(), f, this.rectPaint);
            this.dividerPaint.setColor(this.dividerColor);
            for (int i = 0; i < this.tabCount - 1; i++) {
                View childAt2 = this.tabsContainer.getChildAt(i);
                canvas.drawLine((float) childAt2.getRight(), (float) this.dividerPadding, (float) childAt2.getRight(), (float) (height - this.dividerPadding), this.dividerPaint);
            }
        }
    }
}
