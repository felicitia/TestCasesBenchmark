package com.etsy.android.ui.local;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager.SpanSizeLookup;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.State;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import com.etsy.android.R;
import com.etsy.android.ui.cardview.viewholders.LocalBrowseMarketViewHolder;
import com.etsy.android.uikit.adapter.BaseRecyclerViewAdapter;

/* compiled from: LocalMarketCardMarginDecoration */
class f extends ItemDecoration {
    private final int a;
    private final int b;
    private final int c;
    private final int d;
    private final boolean e;
    private final SpanSizeLookup f;

    public f(Context context, SpanSizeLookup spanSizeLookup, boolean z) {
        this.f = spanSizeLookup;
        this.a = context.getResources().getInteger(R.integer.local_browse_list_columns);
        this.b = context.getResources().getDimensionPixelOffset(R.dimen.local_browse_list_side_padding);
        this.c = context.getResources().getDimensionPixelOffset(R.dimen.fixed_medium);
        this.d = context.getResources().getDimensionPixelOffset(R.dimen.local_browse_list_start_end_padding);
        this.e = z;
    }

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, State state) {
        super.getItemOffsets(rect, view, recyclerView, state);
        ViewHolder childViewHolder = recyclerView.getChildViewHolder(view);
        if (childViewHolder instanceof LocalBrowseMarketViewHolder) {
            a((LocalBrowseMarketViewHolder) childViewHolder, recyclerView);
        }
    }

    private void a(LocalBrowseMarketViewHolder localBrowseMarketViewHolder, RecyclerView recyclerView) {
        BaseRecyclerViewAdapter baseRecyclerViewAdapter = (BaseRecyclerViewAdapter) recyclerView.getAdapter();
        int dataItemCount = baseRecyclerViewAdapter.getDataItemCount();
        int childAdapterPosition = recyclerView.getChildAdapterPosition(localBrowseMarketViewHolder.itemView);
        int headerCount = childAdapterPosition - baseRecyclerViewAdapter.getHeaderCount();
        int spanIndex = this.f.getSpanIndex(childAdapterPosition, this.a);
        int i = this.c;
        int i2 = this.c;
        int i3 = this.c;
        int i4 = this.c;
        if (spanIndex == 0) {
            i = this.b;
        }
        if (spanIndex == this.a - 1) {
            i3 = this.b;
        }
        if (this.e) {
            if (headerCount < this.a) {
                i2 = this.d;
            }
            if (headerCount >= dataItemCount - this.a) {
                i4 = this.d;
            }
        }
        localBrowseMarketViewHolder.setCardBgMargins(i, i2, i3, i4);
    }
}
