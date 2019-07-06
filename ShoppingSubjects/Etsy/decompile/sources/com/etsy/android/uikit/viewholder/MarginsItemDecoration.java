package com.etsy.android.uikit.viewholder;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.State;
import android.view.View;

public class MarginsItemDecoration extends ItemDecoration {
    protected int mMarginBottom;
    protected int mMarginLeft;
    protected int mMarginRight;
    protected int mMarginTop;

    /* access modifiers changed from: protected */
    public boolean isDecorated(View view, RecyclerView recyclerView) {
        return true;
    }

    public MarginsItemDecoration(int i, int i2, int i3, int i4) {
        this.mMarginLeft = i;
        this.mMarginTop = i2;
        this.mMarginRight = i3;
        this.mMarginBottom = i4;
    }

    public MarginsItemDecoration(int i) {
        this(i, i, i, i);
    }

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, State state) {
        if (isDecorated(view, recyclerView)) {
            rect.left = this.mMarginLeft;
            rect.top = this.mMarginTop;
            rect.right = this.mMarginRight;
            rect.bottom = this.mMarginBottom;
            return;
        }
        super.getItemOffsets(rect, view, recyclerView, state);
    }
}
