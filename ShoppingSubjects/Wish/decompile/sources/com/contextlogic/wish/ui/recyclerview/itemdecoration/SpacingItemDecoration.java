package com.contextlogic.wish.ui.recyclerview.itemdecoration;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.State;
import android.view.View;

public class SpacingItemDecoration extends ItemDecoration {
    private int mItemOffset;

    public SpacingItemDecoration(int i) {
        this.mItemOffset = i;
    }

    public SpacingItemDecoration(Context context, int i) {
        this(context.getResources().getDimensionPixelSize(i));
    }

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, State state) {
        super.getItemOffsets(rect, view, recyclerView, state);
        rect.set(this.mItemOffset, this.mItemOffset, this.mItemOffset, this.mItemOffset);
    }
}
