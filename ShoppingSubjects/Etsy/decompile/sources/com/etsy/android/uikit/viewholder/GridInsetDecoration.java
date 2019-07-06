package com.etsy.android.uikit.viewholder;

import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager.LayoutParams;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.State;
import android.view.View;

public class GridInsetDecoration extends ItemDecoration {
    private int insetHorizontal;
    private int insetVertical;

    public GridInsetDecoration(int i) {
        this(i, i);
    }

    public GridInsetDecoration(int i, int i2) {
        this.insetHorizontal = i;
        this.insetVertical = i2;
    }

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, State state) {
        int i;
        int i2;
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int viewLayoutPosition = layoutParams.getViewLayoutPosition();
        if (viewLayoutPosition == -1) {
            rect.set(0, 0, 0, 0);
            return;
        }
        int spanIndex = layoutParams.getSpanIndex();
        if (spanIndex == 0) {
            i = 0;
        } else {
            i = this.insetHorizontal;
        }
        rect.left = i;
        if (spanIndex == viewLayoutPosition) {
            i2 = 0;
        } else {
            i2 = this.insetVertical;
        }
        rect.top = i2;
        rect.right = 0;
        rect.bottom = 0;
    }
}
