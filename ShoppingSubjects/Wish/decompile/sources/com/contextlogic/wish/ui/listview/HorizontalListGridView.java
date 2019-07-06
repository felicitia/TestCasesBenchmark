package com.contextlogic.wish.ui.listview;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.LinearLayout.LayoutParams;
import com.contextlogic.wish.util.ValueUtil;

public class HorizontalListGridView extends HorizontalListView {
    private OnItemClickListener mOnItemClickListener;

    public static abstract class Adapter extends com.contextlogic.wish.ui.listview.HorizontalListView.Adapter {
        protected int mRowCount;

        public long getItemId(int i) {
            return 0;
        }

        public boolean includeLeadingMargin() {
            return true;
        }

        public boolean includeTrailingMargin() {
            return true;
        }

        public int getRowCount() {
            return this.mRowCount;
        }

        public int getHorizontalMargin() {
            return (int) ValueUtil.convertDpToPx(16.0f);
        }

        public int getVerticalMargin() {
            return (int) ValueUtil.convertDpToPx(16.0f);
        }
    }

    public interface OnItemClickListener {
    }

    public HorizontalListGridView(Context context) {
        this(context, null);
    }

    public HorizontalListGridView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public HorizontalListGridView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    /* access modifiers changed from: protected */
    public void addListViewCells(int i) {
        Adapter adapter = (Adapter) this.mAdapter;
        int count = adapter.getCount();
        int rowCount = adapter.getRowCount();
        int itemHeight = adapter.getItemHeight(0);
        int itemWidth = adapter.getItemWidth(0);
        if (i >= 0) {
            while (i < count) {
                int i2 = i % rowCount;
                int i3 = i2 == 0 ? this.mContentWidth : this.mContentWidth - itemWidth;
                int verticalMargin = (adapter.getVerticalMargin() + itemHeight) * i2;
                if (i2 == 0) {
                    i3 += adapter.getHorizontalMargin();
                }
                Rect rect = new Rect(i3, verticalMargin, i3 + itemWidth, verticalMargin + itemHeight);
                this.mItemPositions.put(i, rect);
                this.mContentWidth = rect.right;
                i++;
            }
        }
        LayoutParams layoutParams = (LayoutParams) this.mListViewContentHolder.getLayoutParams();
        int horizontalMargin = itemWidth + adapter.getHorizontalMargin();
        int i4 = count / rowCount;
        if (((float) i4) != ((float) count) / ((float) rowCount)) {
            i4++;
        }
        int i5 = horizontalMargin * i4;
        int verticalMargin2 = rowCount * (itemHeight + adapter.getVerticalMargin());
        if (adapter.includeTrailingMargin()) {
            i5 += adapter.getHorizontalMargin();
            verticalMargin2 += adapter.getVerticalMargin();
        }
        layoutParams.width = i5;
        layoutParams.height = verticalMargin2;
        this.mListViewContentHolder.setLayoutParams(layoutParams);
        postInvalidateListView();
    }

    public void setAdapter(Adapter adapter) {
        this.mAdapter = adapter;
        notifyDataSetChanged(true);
    }
}
